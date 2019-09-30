package menu.game;

public class NameGuesser {
	
	private String questionOne = "What does your name start with A-L?";
	private String questionTwo = "What does your name end with A-L?";
	private String questionThree = "What year were you born?"; //yes or no
	
	private boolean answerOne, answerTwo, answerThree;

	@Override
	public String toString() {
		return "NameGuesser [questionOne=" + questionOne + ", questionTwo=" + questionTwo + ", questionThree="
				+ questionThree + ", answerOne=" + answerOne + ", answerTwo=" + answerTwo + ", answerThree="
				+ answerThree + "]";
	}
	
	
	
}
