package menu.quotes;

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

public class QuoteScreen extends MenuCtrl {
	
	public QuoteScreen() {
		defaultScreen();
		//gpioEventHandler();
		
				//fake GPIO handler
				JLauncher.getBox().setOnKeyPressed(event -> {
					if (event.getCode() == KeyCode.RIGHT) 
					System.out.println("HEY");
				});
	}
	
	public void defaultScreen() {
		setHeader("Quote Menu");
		setStatus("LOADED MEN_01");
		
		AnimationHandler.setTimeline(new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> setMainText("<- Camera Menu ->", new Font(45))),
				new KeyFrame(Duration.seconds(1), event -> setMainText("< - Camera Menu - >", new Font(45))))
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
				}
			}
		});
				
		Controller.getConfirmButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
				}
			}
		});
		
		Controller.getNextButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
				}
			}
		});
		
		Controller.getPreviousButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
				}
			}
		});
		
		Controller.getBackButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
			}
		});
	}

}
