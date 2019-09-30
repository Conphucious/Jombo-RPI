package menu.base;

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
import system.anim.Database;
import system.internal.Configuration;
import system.internal.Controller;
import system.internal.MenuCtrl;

public class DefaultScreen extends MenuCtrl {
	
	public DefaultScreen() {
		setMainText("Jombo Launcher");
		setHeader("Main Menu");
		setStatus("LOADED A-MENU");
		
		AnimationHandler.setTimeline(new Timeline(
						new KeyFrame(Duration.seconds(5), event -> JLauncher.getText().setText(Database.initExp)),
						new KeyFrame(Duration.seconds(0.10), event -> JLauncher.getText().setText(Database.blinkExp)),
						new KeyFrame(Duration.seconds(0.10), event -> JLauncher.getText().setText(Database.initExp)))
		);
		
		AnimationHandler.getTimeline().setAutoReverse(true);
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
		
		//gpioEventHandler();
	}
	
	public void gpioEventHandler() {
		Controller.clearListeners();
		
		Controller.getTopButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					JLauncher.getEmote().blinkEmote();
					setStatus("Top Button (H) Blink Pressed");
				} else if (event.getState().isLow()) {
					JLauncher.getEmote().blinkEmote();
					setStatus("Top Button (L) Blink Pressed");
				}
			}
		});
		
		Controller.getAltButton().addListener(new GpioPinListenerDigital() {
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
		
		
//		Controller.getConfirmButton().addListener(new GpioPinListenerDigital() {
//			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//				if (event.getState().isHigh()) {
//					menu.open();
//				}
//			}
//		});
//		
//		Controller.getNextButton().addListener(new GpioPinListenerDigital() {
//			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//				if (event.getState().isHigh()) {
//					menu.processIndex("increment");
//				}
//			}
//		});
//		
//		Controller.getPreviousButton().addListener(new GpioPinListenerDigital() {
//			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//				if (event.getState().isHigh()) {
//					menu.processIndex("decrement");
//				}
//			}
//		});
//		
//		Controller.getBackButton().addListener(new GpioPinListenerDigital() {
//			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//				menu.close();
//			}
//		});
	}

}
