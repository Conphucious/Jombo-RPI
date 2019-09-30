package menu.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import driver.JLauncher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import menu.camera.MainCameraMenu;
import menu.quotes.QuoteScreen;
import system.anim.AnimationHandler;
import system.anim.Database;
import system.internal.Controller;
import system.internal.MenuCtrl;
import system.internal.MultiMenu;


public class MainMenu extends MenuCtrl implements MultiMenu {
	
	private static int maxIndex = 5;
	private static int minIndex = 0;
	
	// indicator
	private static int menuIndex = 0;
	
	public MainMenu() {
		new MainCameraMenu();
		//defaultScreen();
	}
	
	public void defaultScreen() {
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
	
	public void open() {
		AnimationHandler.getTimeline().stop();
		// do we need this? ^^^
		switch (menuIndex) {
		case 0:
			defaultScreen();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			new QuoteScreen();
			break;
		default:
			break;
		}
	}
	
	public void processIndex(int operation) {
		menuIndex += operation;
		
		if (menuIndex > maxIndex) {
			menuIndex = minIndex;
		} else if (menuIndex < minIndex) {
			menuIndex = maxIndex;
		}
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
		
		
		Controller.getConfirmButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					open();
				}
			}
		});
		
		Controller.getNextButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					processIndex(1);
				}
			}
		});
		
		Controller.getPreviousButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					processIndex(-1);
				}
			}
		});
		
		Controller.getBackButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
			}
		});
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
