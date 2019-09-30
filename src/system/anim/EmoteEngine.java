package system.anim;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class EmoteEngine {
	
	private Animation animation;
	private Text text;
	
	public EmoteEngine(Text text) {
		this.text = text;
		text.setText(Database.initExp);
		animation = AnimationHandler.getTimeline();
	}
	
	public void blinkEmote() {
		//engine.playOneAnimation(EDatabase.blinkExp, .5);
		playTwoAnimation(Database.blinkExp, Database.initExp, .5, .5);
	}
	
	public void kittyEmote() {
		playOneAnimation(Database.kittyExp, .5);
	}
	
	// engine that runs stuff
	
	private void playOneAnimation(String src, double time) {
		animation.stop();
		animation = new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> text.setText(src)));
		animation.setCycleCount(1);
		animation.play();
	}
	
	private void playTwoAnimation(String src, String src2, double time, double time2) {
		animation.stop();
		animation = new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> text.setText(src)),
				new KeyFrame(Duration.seconds(1), event -> text.setText(src2)));
		animation.setCycleCount(1);
		animation.play();
	}

}
