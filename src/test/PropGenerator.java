package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import system.internal.FilePath;

public class PropGenerator {

	private static Properties properties;
	private static String title;

	public static void main(String[] args) {
		properties = new Properties();
		title = "JConfiguration";

		//writeProperties(new File(FilePaths.PROPERTIES));
		//readProperties(new File(FilePaths.PROPERTIES));
	}

	public static void setProperty() {
		// Windows
		properties.setProperty("default_text_size", "150");

		// Keycodes
		properties.setProperty("keycode_top", "KeyCode.C");
		properties.setProperty("keycode_confirm", "KeyCode.A");
		properties.setProperty("keycode_cancel", "KeyCode.B");
		properties.setProperty("keycode_next", "KeyCode.LEFT");
		properties.setProperty("keycode_previous", "Keycode.RIGHT");
	}

	public static void writeProperties(File file) {
		try {
			FileOutputStream output = new FileOutputStream(file);
			properties.store(output, title);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readProperties(File file) {
		FileInputStream fileInput;
		Properties properties;

		try {
			fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration<?> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				System.out.println(key + ": " + value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
