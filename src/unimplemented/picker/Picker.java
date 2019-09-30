package unimplemented.picker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Picker {

	File file;
	ArrayList<String> places = new ArrayList<>();
	ArrayList<Place> placeList = new ArrayList<>();

	public static void main(String[] args) {
		try {
			new Picker("places.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Picker(String dir) throws FileNotFoundException {
		file = new File(dir);

		Scanner reader = new Scanner(file);
		String text = "";
		while (reader.hasNextLine()) { 
			places.add(reader.nextLine());
		}
		reader.close();
		
		for (int i = 0; i < places.size(); i++) {
			if (places.get(i).startsWith("#") || places.get(i).startsWith(" ") || places.get(i).equals("")) {
				places.remove(places.get(i));
			} else {
				
				String[] placeArr = places.get(i).split(",");
				String[] townArr = places.get(i).split(";");
				
				placeList.add(new Place(placeArr[0], placeArr[1].replace(";", ""), townArr[1], placeArr[2]));
			}
		}
		
		
		for (Place line : placeList) {
			System.out.println(line);
		}
		
	}

}
