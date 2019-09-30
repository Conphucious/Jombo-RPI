package unimplemented.picker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PickWindow {

	private String primaryMessage = "Place Picker";
	private String secondaryMessage = "Press the\nconfirmation\nbutton!";

	public PickWindow(VBox box, Text text, Timeline init) {
		init.stop();
		text.setText(primaryMessage);
		text.setFont(new Font(50));

		Timeline beat = new Timeline(new KeyFrame(Duration.seconds(2), event -> text.setText(primaryMessage)),
				new KeyFrame(Duration.seconds(4.5), event -> text.setFill(Color.RED)),
				new KeyFrame(Duration.seconds(4), event -> text.setText(secondaryMessage)),
				new KeyFrame(Duration.seconds(1), event -> text.setFill(Color.WHITE)),
				new KeyFrame(Duration.seconds(3), event -> text.setText(primaryMessage)));
		beat.setCycleCount(Timeline.INDEFINITE);
		beat.play();
		
		//box.removeEventHandler(KeyEvent.KEY_PRESSED, PickerHandler.getKeyStrokeHandler(this));

		box.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.LEFT) {
				System.out.println("Hello");
			} else if (event.getCode() == KeyCode.RIGHT) {
				System.out.println("Hello");
			} else if (event.getCode() == KeyCode.A) {
				System.out.println("Hello");
			} else if (event.getCode() == KeyCode.B) {
				System.out.println("Hello");
			}
		});
	}
	
	public void keystroke(KeyCode ks) {
		
	}

}
