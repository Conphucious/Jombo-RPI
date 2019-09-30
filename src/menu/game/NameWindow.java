package menu.game;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import system.anim.AnimationHandler;
import system.internal.Controller;
import system.internal.MenuCtrl;

public class NameWindow extends MenuCtrl {
	
	private static int indexNest = 0;
	
	public NameWindow() {
		setTitle("Name Guessing Game\nPress confirm to play!", Color.WHITE, new Font(150));
		setSubtitle("Name Guesser", Color.WHITE, new Font(30));
		
	}

	public void gpioEventHandler() {
		Controller.clearListeners();
		
		Controller.getTopButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				
			}
		});
		
		Controller.getAltButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					//exit button 
				}
			}
		});
		
		Controller.getConfirmButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					if (indexNest == 0) {
						
					} else if (indexNest == 1) {
						
					}
				}
			}
		});
		
		Controller.getNextButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					//menu.processIndex("increment");
				}
			}
		});
		
		Controller.getPreviousButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					//menu.processIndex("decrement");
				}
			}
		});
		
		Controller.getBackButton().addListener(new GpioPinListenerDigital() {
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				//menu.close();
			}
		});
	}

}
