package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class testfileinput {

	public static void main(String[] args) {
		
		
		
		 try {
	        	FileInputStream fileInputStream = new FileInputStream("src/main/resources/dbHikari.properties");
	        }
	        catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}

	}

}
