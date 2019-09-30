package test;

import java.io.File;

public class FileExists {

	public static void main(String[] args) {
		File tmpDir = new File("/asd");
		boolean exists = tmpDir.exists();
		
		if (tmpDir.isDirectory()) {System.out.println("/var/tmp is a directory"); 
			
		} else {
			System.out.println("NOT WORK");
		}
	}

}
