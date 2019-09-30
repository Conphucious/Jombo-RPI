package test;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import system.anim.Database;

public class Emoticon {
	
	private EEngine eEngine;
	
	
	public Emoticon(Text text) {
		eEngine = new EEngine(text);
	}
	
	public EEngine getEngine() {
		return eEngine;
	}
	
	public void talking(Text text, Timeline previousTL) {
		previousTL.stop();
		Timeline beat = new Timeline(
	            new KeyFrame(Duration.millis(400),         event -> text.setText(Database.talkExp1)),
	            new KeyFrame(Duration.seconds(0.30), event -> text.setText(Database.talkExp2)),
	            new KeyFrame(Duration.seconds(0.30),         event -> text.setText(Database.talkExp1))
	        );
	        beat.setAutoReverse(true);
	        beat.setCycleCount(Timeline.INDEFINITE);
	        beat.play();
	}
	
	public void ohDayumEmote() {
		eEngine.playTwoAnim(Database.ohExp, 2.0, Database.dayumExp, 2.0);
	}
	
	public void angryEmote() {
		eEngine.playOneAnim(Database.angryExp, 2);
	}
	
	public void annoyedEmote() {
		eEngine.playOneAnim(Database.annoyedExp, 2.0);
	}
	
	public void blinkEmote() {
		eEngine.playOneAnim(Database.blinkExp, 0.5);
	}
	
	public void cheeryEmote() {
		eEngine.playOneAnimation(Database.cheeryExp1, 1);
//		eEngine.playTwoAnim(EDatabase.cheeryExp1, 1.5,
//				EDatabase.cheeryExp2, 1.5);
	}
	
	public void chetEmote() {
		eEngine.playOneAnim(Database.chetExp, 2.0);
	}
	
	public void cryEmote() {
		eEngine.playOneAnim(Database.cryExp, 2);
	}
	
	public void dayumEmote() {
		eEngine.playOneAnim(Database.dayumExp, 2.0);
	}
	
	public void happyEmote() {
		eEngine.playThreeAnim(Database.happyExp1, 1.0, Database.happyExp2, 1.0, Database.happyExp3, 1.0);
	}
	
	public void hmEmote() {
		eEngine.playOneAnim(Database.heartExp, 2);
	}
	
	public void kissEmote() {
		eEngine.playOneAnim(Database.kissExp, 2);
	}
	
	public void kittyEmote() {
		eEngine.playOneAnim(Database.kittyExp, 2);
	}
	
	public void mehEmote() {
		eEngine.playOneAnim(Database.mehExp, 2);
	}
	
	public void ohEmote() {
		eEngine.playOneAnim(Database.ohExp, 2.0);
	}
	
	public void lips3Emote() {
		eEngine.playOneAnim(Database.lips3Exp, 2);
	}
	
	public void sadEmote() {
		eEngine.playOneAnim(Database.sadExp, 2.0);
	}
	
	public void scaredEmote() {
		eEngine.playOneAnim(Database.scaredExp, 2);
	}
	
	public void stareEmote() {
		eEngine.playOneAnim(Database.stareExp, 2.0);
	}
	
	public void ughEmote() {
		eEngine.playTwoAnim(Database.ughExp1, 2.0, Database.ughExp2, 2.0);
	}
	
	public void whyEmote() {
		eEngine.playOneAnim(Database.whyExp, 2);
	}
	
	public void xdEmote() {
		eEngine.playOneAnim(Database.xdExp, 1.25);
	}
	
	//effects: police siren screen red blue white with sfx?

}
