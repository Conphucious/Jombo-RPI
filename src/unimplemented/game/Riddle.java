package unimplemented.game;

public class Riddle {
	// Right answer in bank is first index.
	private String question;
	private String[] answer_bank;
	private int pts;
	
	public Riddle(String question, String[] answer_bank, int pts) {
		this.question = question;
		this.answer_bank = answer_bank;
		this.pts = pts;
	}

	public String getQuestion() {
		return question;
	}

	public String[] getAnswer_bank() {
		return answer_bank;
	}

	public int getPts() {
		return pts;
	}

}
