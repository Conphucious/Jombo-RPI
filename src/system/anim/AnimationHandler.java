package system.anim;

import javafx.animation.Timeline;

public class AnimationHandler {
	
	// Created using singleton pattern
	
	private static Timeline timeline;
	
	private AnimationHandler() {}
	
	
	public static Timeline getTimeline() {
		if (timeline == null) {
			createTimeline();
		}
		return timeline;
	}
	
	public static Timeline setTimeline(Timeline tl) {
		timeline.stop();
		timeline = tl;
		return timeline;
	}
	
	private static void createTimeline() {
		timeline = new Timeline();
	}

}
