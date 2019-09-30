package unimplemented.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import menu.base.MainMenu;
import system.anim.AnimationHandler;
import system.handlers.GameHandler;

public class GuessGameWindow {

	private String primaryMessage = "Place Picker";
	private String secondaryMessage = "Press the\nconfirmation\nbutton!";

	public GuessGameWindow(MainMenu menu) {
		AnimationHandler.getTimeline().stop();
		menu.getText().setText(primaryMessage);
		menu.getText().setFont(new Font(50));

		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(2), event -> menu.getText().setText(primaryMessage)),
				new KeyFrame(Duration.seconds(4.5), event -> menu.getText().setFill(Color.RED)),
				new KeyFrame(Duration.seconds(4), event -> menu.getText().setText(secondaryMessage)),
				new KeyFrame(Duration.seconds(1), event -> menu.getText().setFill(Color.WHITE)),
				new KeyFrame(Duration.seconds(3), event -> menu.getText().setText(primaryMessage)));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		menu.getBox().removeEventHandler(KeyEvent.KEY_PRESSED, GameHandler.getKeyStrokeHandler(this));

		menu.getBox().addEventHandler(KeyEvent.KEY_PRESSED, GameHandler.getGuessKeystrokeHandler(this));
	}
	
	public void keystroke(KeyCode ks) {
		
	}
	
	

}
