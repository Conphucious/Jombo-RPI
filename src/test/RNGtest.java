package test;

public class RNGtest {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			int rng = ((int) (Math.random() * 1));
			if (rng == 1) {
				System.out.println("YES");
			}
		}
		System.out.println("DONE");
	}

}
