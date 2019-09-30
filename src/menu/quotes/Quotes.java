package menu.quotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import system.internal.FilePath;

public class Quotes {
	
	private static ArrayList<String> quoteList;
	
	public static void main(String[] args) {
		System.out.println(getQuote());
	}
	
	public static void loadQuotes(File file) {
		quoteList = new ArrayList<>();
		Scanner reader;
		try {
			reader = new Scanner(file);
			String text = "";
			while(reader.hasNextLine()) {
				quoteList.add(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String getQuote() {
		if (quoteList == null) {
			loadQuotes(new File(FilePath.QUOTES));
		}
		
		
		return quoteList.get(getRandomIndex());
	}
	
	private static int getRandomIndex() {
		return ((int) (Math.random() * quoteList.size()));
	}
	
//	private static String checkRepeats(String quote) {
//		if (quote.equals("asd")) {
//			//if repeat then return (checkRepeats(getrandomindex)) recursion
//		}
//		if repeated then find something different.
//		return quote;
//	}

}
