package menu.camera;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import driver.JLauncher;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.util.Duration;
import menu.base.MainMenu;
import system.anim.AnimationHandler;
import system.internal.Controller;
import system.internal.MenuCtrl;
import system.internal.MultiMenu;

public class MainCameraMenu extends MenuCtrl implements MultiMenu {
	
	private static int maxIndex = 2;
	private static int minIndex = 0;
	private static int menuIndex = 1;
	
	public MainCameraMenu() {
		defaultScreen();
		//new CaptureScreen();
		//gpioEventHandler();
		
				//fake GPIO handler
				JLauncher.getBox().setOnKeyPressed(event -> {
					if (event.getCode() == KeyCode.RIGHT) 
						processIndex(1);
					if (event.getCode() == KeyCode.UP)
						defaultScreen();
					if (event.getCode() == KeyCode.LEFT)
						processIndex(-1);
					
					System.out.println(menuIndex);
				});
	}
	
	public void defaultScreen() {
		setHeader("Camera Menu");
		setStatus("LOADED MEN_01");
		
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> setMainText("<- Camera Menu ->", new Font(45))),
				new KeyFrame(Duration.seconds(1), event -> setMainText("< - Camera Menu - >", new Font(45))))
		);
		
		AnimationHandler.getTimeline().setAutoReverse(true);
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
	}
	
	public void captureScreen() {
		setHeader("Take Picture");
		setStatus("LOADED MEN_02");
		
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> setMainText("<- Take a Picture", new Font(45))),
				new KeyFrame(Duration.seconds(1), event -> setMainText("< - Take a Picture", new Font(45))))
		);
		
		AnimationHandler.getTimeline().setAutoReverse(true);
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
	}
	
//	public void takeCaptureScreen() {
//		try {
//            String target = new String("./capture.sh");
//            Runtime rt = Runtime.getRuntime();
//            Process proc = rt.exec(target);
//            proc.waitFor();
//            StringBuffer output = new StringBuffer();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            String line = "";                       
//            while ((line = reader.readLine())!= null) {
//                    output.append(line + "\n");
//            }
//            
//            File path = new File(output.toString());
//            
//            if (!path.isFile()) {	// if we have image folder nad the echo file exists 
//            		setStatus("UNABLE TO CAPTURE");
//            } else {
//            		setStatus("CAPTURING");
//            }
//            System.out.println("###" + output);
//		} catch (Throwable t) {
//            t.printStackTrace();
//		}
//	}
	
	public void viewScreen() {
		setHeader("View Picture");
		setStatus("LOADED MEN_00");
		
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> setMainText("View a Picture ->", new Font(45))),
				new KeyFrame(Duration.seconds(1), event -> setMainText("View a Picture - >", new Font(45))))
		);
		
		AnimationHandler.getTimeline().setAutoReverse(true);
		AnimationHandler.getTimeline().setCycleCount(Timeline.INDEFINITE);
		AnimationHandler.getTimeline().play(); 
	}
	
	public void gpioEventHandler() {
		Controller.clearListeners();
		
		Controller.getTopButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					JLauncher.getEmote().blinkEmote();
				} else if (event.getState().isLow()) {
					JLauncher.getEmote().blinkEmote();
				}
				setStatus("Top Button Pressed");
			}
		});
		
		Controller.getAltButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					// throw back to main menu
					close();
				}
			}
		});
				
		Controller.getConfirmButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					openSubMenu();
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
		
//		Controller.getBackButton().addListener(new GpioPinListenerDigital() {
//			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//				close();
//			}
//		});
	}

	public void open() {
		switch (menuIndex) {
		case 0: 
			viewScreen();
			break;
		case 1:
			defaultScreen();
			break;
		case 2:
			captureScreen();
			break;
		}
	}
	
	public void openSubMenu() {
		switch (menuIndex) {
		case 0:
			
			break;
		case 1: 
			break;
		case 2: 
			//takeCaptureScreen();
			break;
		}
	}

	@Override
	public void processIndex(int operation) {
		//not wrap around 
		menuIndex += operation;
		
		if (menuIndex > maxIndex) {
			menuIndex--;
		} else if (menuIndex < minIndex) {
			menuIndex++;
		}

		
		open();
	}

	@Override
	public void close() {
		new MainMenu();
	}
	
	

}
