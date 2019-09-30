package unimplemented.game;

public class GuessGame {
	
	private int random_number;
	
	public GuessGame(int guess) {
		random_number = (int) ((5 * Math.random()) + 1);
		
		if (guess == random_number) {
			System.out.println("Your Guess Number: " + random_number + " is correct!");
		} else {
			System.out.println("Your Guess Number: " + random_number + " is incorrect!");
		}
	}

	public static void main(String[] args) {
		new GuessGame(5);
	}

}
