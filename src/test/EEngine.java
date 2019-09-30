package test;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.text.Text;
import javafx.util.Duration;
import system.anim.Database;

public class EEngine {

	private Text text;

	public EEngine(Text text) {
		this.text = text;
		init();
	}

	public void init() { // keep face animated?
		text.setText(Database.initExp);
	}

	public void speak(String src) {

		final Animation animation = new Transition() {
			{
				setCycleCount(INDEFINITE);
				setCycleDuration(Duration.millis(2000));
			}

			protected void interpolate(double frac) {
				final int length = src.length();
				final int n = Math.round(length * (float) frac);
				text.setText(src.substring(0, n));
			}

		};

		animation.play();
	}
	
	public Timeline animation;
	
	public void playOneAnimation(String src, double time) {
		animation = new Timeline(
				new KeyFrame(Duration.seconds(.5), event -> text.setText(src)));
		animation.setCycleCount(1);
		animation.play();
	}

	public void playOneAnim(String src, double time) {
		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(1000 * time));
				setCycleCount(1);
			}

			protected void interpolate(double frac) {
				text.setText(src);
				PauseTransition pause = new PauseTransition(Duration.seconds(time));
				pause.setOnFinished(event -> {
					init();
				});
				pause.play();
			}
		};
		animation.play();
	}

	public void playTwoAnim(String src1, double time1, String src2, double time2) {
		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(1000));
			}

			protected void interpolate(double frac) {
				text.setText(src1);
				PauseTransition pause = new PauseTransition(Duration.seconds(time1));
				pause.setOnFinished(event -> {
					text.setText(src2);

					PauseTransition pause2 = new PauseTransition(Duration.seconds(time2));
					pause2.setOnFinished(event2 -> {
						init();
					});
					pause2.play();

				});
				pause.play();
			}
		};
		animation.play();
	}

	public void playThreeAnim(String src1, double time1, String src2, double time2, String src3, double time3) {
		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(1000));
			}

			protected void interpolate(double frac) {
				text.setText(src1);
				PauseTransition pause = new PauseTransition(Duration.seconds(time1));
				pause.setOnFinished(event -> {
					text.setText(src2);

					PauseTransition pause2 = new PauseTransition(Duration.seconds(time2));
					pause2.setOnFinished(event2 -> {
						text.setText(src3);

						PauseTransition pause3 = new PauseTransition(Duration.seconds(time3));
						pause3.setOnFinished(event3 -> {
							init();
						});
						pause3.play();

					});
					pause2.play();

				});
				pause.play();
			}
		};
		animation.play();
	}

	public void playFourAnim(String src1, double time1, String src2, double time2, String src3, double time3,
			String src4, double time4) {
		final Animation animation = new Transition() {
			{
				setCycleDuration(Duration.millis(1000));
			}

			protected void interpolate(double frac) {
				text.setText(src1);
				PauseTransition pause = new PauseTransition(Duration.seconds(time1));
				pause.setOnFinished(event -> {
					text.setText(src2);

					PauseTransition pause2 = new PauseTransition(Duration.seconds(time2));
					pause2.setOnFinished(event2 -> {
						text.setText(src3);

						PauseTransition pause3 = new PauseTransition(Duration.seconds(time3));
						pause3.setOnFinished(event3 -> {
							text.setText(src4);

							PauseTransition pause4 = new PauseTransition(Duration.seconds(time4));
							pause4.setOnFinished(event4 -> {
								init();
							});
							pause4.play();

						});
						pause3.play();

					});
					pause2.play();

				});
				pause.play();
			}
		};
		animation.play();
	}

	public Timeline getAnimation() {
		return animation;
	}
	
	
}
