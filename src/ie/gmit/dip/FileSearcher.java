package ie.gmit.dip;
import java.io.File;
import java.util.Scanner;
import java.net.MalformedURLException;
import java.net.URL;

public class FileSearcher {		

	private final static String extension = ".png"; // The default extension is .png.
	private final static Scanner sc = new Scanner(System.in);		

		//Method for input user's file.	
	public static String enterFile() throws Exception {
			
		System.out.println("Enter File Name");
		String fileName = sc.nextLine() + extension;
		System.out.println("Enter Directory Path to Search");
		String directory = sc.nextLine();
		  //If the file exists, moves to next method for retrieving. 
		try {
			File target = new File(findFile(fileName, directory));	
			return target.getAbsolutePath();
			//If the file does not exist then we'll get an error message.
		} catch (Exception e) {
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR] File Not Found!");
			System.out.println(ConsoleColour.RESET);
			}
		return null;
	}

		// Method for file retrieving
	private static String findFile(String fileName, String directory) throws Exception {
		File[] fileList = new File(directory).listFiles();
			
		//Iterating through files in directory to find the searching file.
		for (int i = 0; i < fileList.length; i++) {
			File f = fileList[i];
			if (f.getName().equalsIgnoreCase(fileName)) {//No case sensitive
				System.out.println(ConsoleColour.GREEN);
				System.out.println("[INFO] Retrieving File...");
				System.out.println(ConsoleColour.RESET);
				return f.getAbsolutePath();
			}		
		}
		return null;	
	}			
		
		//Finding file, using URL
	public static URL enterFileURL() throws MalformedURLException {
		String urlFile = sc.nextLine().trim();
			URL url = new URL(urlFile);
		return url;
	}	

}