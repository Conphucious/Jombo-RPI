package system.internal;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class Controller {
	
	private static GpioController gpio = GpioFactory.getInstance();
	private static GpioPinDigitalInput topButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29, PinPullResistance.PULL_UP);
	private static GpioPinDigitalInput altButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_23, PinPullResistance.PULL_UP);
	private static GpioPinDigitalInput confirmButton;
	private static GpioPinDigitalInput nextButton;
	private static GpioPinDigitalInput previousButton;
	private static GpioPinDigitalInput backButton;
	
	private Controller() {}
	
	private static void initializeButton(GpioPinDigitalInput button, Pin pin, PinPullResistance type) {
		button = gpio.provisionDigitalInputPin(pin, type);
		button.setShutdownOptions(true);
	}
	
	public static GpioController getGpio() {
		return gpio;
	}
	
	public static GpioPinDigitalInput getTopButton() {
		return topButton;
	}
	
	public static GpioPinDigitalInput getAltButton() {
		return altButton;
	}
	
	public static GpioPinDigitalInput getConfirmButton() {
		if (topButton == null) {
			initializeButton(confirmButton, RaspiPin.GPIO_28, PinPullResistance.PULL_UP);
		}
		return topButton;
	}
	
	public static GpioPinDigitalInput getNextButton() {
		if (topButton == null) {
			initializeButton(nextButton, RaspiPin.GPIO_25, PinPullResistance.PULL_UP);
		}
		return topButton;
	}
	
	public static GpioPinDigitalInput getPreviousButton() {
		if (topButton == null) {
			initializeButton(previousButton, RaspiPin.GPIO_27, PinPullResistance.PULL_UP);
		}
		return topButton;
	}
	
	public static GpioPinDigitalInput getBackButton() {
		if (topButton == null) {
			initializeButton(backButton, RaspiPin.GPIO_24, PinPullResistance.PULL_UP);
		}
		return topButton;
	}
	
	public static void clearListeners() {
		if (topButton != null)
			topButton.removeAllListeners();
		if (altButton != null)
			altButton.removeAllListeners();
		if (confirmButton != null)
			confirmButton.removeAllListeners();
		if (nextButton != null)
			nextButton.removeAllListeners();
		if (previousButton != null)
			previousButton.removeAllListeners();
		if (backButton != null) {
			backButton.removeAllListeners();
		}
	}
	
	public static boolean isHigh(GpioPinDigitalInput button) {
		return (button.isHigh());
	}
	
	

}
