package menu.camera;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import driver.JLauncher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import system.anim.AnimationHandler;
import system.internal.Controller;
import system.internal.MenuCtrl;

public class ViewScreen extends MenuCtrl {
	
	public ViewScreen() {
		setMainText("View\nPictures", Color.YELLOW, new Font(100));
		setHeader("Camera Menu");
		setStatus("LOADED V-MENU");
		
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(1), event -> JLauncher.getText().setText("Use the navi buttons\nto navigate pics!")),
				new KeyFrame(Duration.seconds(2), event -> JLauncher.getText().setText("Use the navi buttons\\nto navigate pics ")))
		);
		
		AnimationHandler.getTimeline().setAutoReverse(true);
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
		
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
					setStatus("Back -> Menu " + event.getState());
					new MainCameraMenu();
				}
			}
		});
	}

}
