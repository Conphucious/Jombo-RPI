package unimplemented.game;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class RiddleGame {
	
	private int points;
	private ArrayList<Riddle> set = new ArrayList<>();
	
	public RiddleGame() {
		generateQuestions();
	}
	
	private void generateQuestions() {
		String[] bank = {"newspaper", "shit", "test", "yey"};
		set.add(new Riddle("What is black, blue, and green all over?", bank, 1));
	}
	
	private void correctAnimation() {
		Timeline animation = new Timeline(new KeyFrame(Duration.seconds(5), event -> points++));
	}
	
	private void incorrectAnimation() {
		
	}
	
	public int getPoints() {
		return points;
	}

}
