package menu.camera;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import driver.JLauncher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import system.anim.AnimationHandler;
import system.internal.Controller;
import system.internal.FilePath;
import system.internal.MenuCtrl;

public class CaptureScreen extends MenuCtrl {
	
	private ImageView img = new ImageView(new Image(FilePath.topButtonImg));

	public CaptureScreen() {
		setMainText("Camera\nCapture", Color.YELLOW, new Font(100));
		setHeader("Camera Menu");
		setStatus("LOADED C-MENU");
		
		JLauncher.getText().setFont(new Font(50));
		JLauncher.getBox();
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(2), event -> JLauncher.getText().setText("Hit the top red\nbutton to snap\na picture!")),
				new KeyFrame(Duration.seconds(3), event -> imageSequence("")),
				new KeyFrame(Duration.seconds(5), event -> imageSequence("Hit the top red\nbutton to snap\na picture!")))
		);
		
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
		
		//gpioEventHandler();
		
	}
	
	public void imageSequence(String text) {
		JLauncher.getText().setText(text);
		if (JLauncher.getBox().getChildren().contains(img)) {
			JLauncher.getBox().getChildren().remove(img);
		} else {
			JLauncher.getBox().getChildren().add(img);
		}
	}

	@Override
	public void gpioEventHandler() {
		Controller.clearListeners();
		
		Controller.getTopButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					try {
		                String target = new String("./capture.sh");
		                Runtime rt = Runtime.getRuntime();
		                Process proc = rt.exec(target);
		                proc.waitFor();
		                StringBuffer output = new StringBuffer();
		                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		                String line = "";                       
		                while ((line = reader.readLine())!= null) {
		                        output.append(line + "\n");
		                }
		                
		                File path = new File(output.toString());
		                
		                if (!path.isFile()) {	// if we have image folder nad the echo file exists 
		                		setStatus("UNABLE TO CAPTURE");
		                } else {
		                		setStatus("CAPTURING");
		                }
		                System.out.println("###" + output);
			        } catch (Throwable t) {
			                t.printStackTrace();
			        }
				}
			}
		});
		
		Controller.getAltButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					JLauncher.getBox().getChildren().remove(img);
					setStatus("Back -> Menu " + event.getState());
					new MainCameraMenu();
				}
			}
		});
	}
	
	

}
