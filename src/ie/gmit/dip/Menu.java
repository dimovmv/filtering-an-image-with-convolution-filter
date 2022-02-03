package ie.gmit.dip;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.*;

public class Menu {

	private Scanner sc;
	private boolean keeprunning = true;
	// Default Kernel value (In case the user don't choose any kernel)
	public static Kernel k = Kernel.IDENTITY;
		// Set Of all Enums
	public static EnumSet<Kernel> kernelSet = EnumSet.allOf(Kernel.class);
	
	public Menu() {
		sc = new Scanner(System.in);
	}
	
	public void start() throws Exception{
		while(keeprunning) {
			showOptions();
			int choise = Integer.parseInt(sc.nextLine());
			switch(choise) {
			case 1 -> displayFilters();      
			case 2 -> enterFilter();     		
			case 3 -> imageProcessing();
			case 4 -> url();
			case 5 -> shuttingDown();		
			
			default -> System.out.println("[INFO] Invalid Input.\nPlease enter a number between 1 - 5");
			}
		}
	}
	
	private void showOptions()  throws Exception{		
		System.out.println(ConsoleColour.CYAN_BOLD);
		System.out.println(ConsoleColour.BLUE_BACKGROUND_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Image Filtering System V1.0           *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");			
		System.out.println("1) Display Filters Available"); 		
        System.out.println("2) Enter Filter Name");
        System.out.println("3) Enter Image Name"); 
        System.out.println("4) Enter URL");       
        System.out.println("5) Quit"); 
        System.out.println("\nSelect Option [1-4]> \nor press 5 + Enter for exit");
        System.out.print(ConsoleColour.RESET);
	}
	   //Method for processing user's image in the menu
	private void imageProcessing() {
		try {
			//Getting file name and directory from the user
			BufferedImage inputImage = ImageIO.read(new File(FileSearcher.enterFile())); 
			//Processing the image
			ImageProcessing.readImage(inputImage, k); 
		} catch (Exception e) {
			System.out.println(ConsoleColour.RED);
			System.out.println("Something went wrong.");
			System.out.println(ConsoleColour.RESET);
		}
	}
	 //Method to shut down the application
	private void shuttingDown() {
		keeprunning = false;
		System.out.println(ConsoleColour.BLUE);
		System.out.println("[INFO] Shutting down...Please wait..");	
		System.out.println(ConsoleColour.RESET);
	}
	//Method for processing a URL image in the menu
    private void url() {
	    System.out.println(ConsoleColour.BLUE);
	    System.out.println("Enter image URL");
	    System.out.println(ConsoleColour.RESET);
	    try {
	    	//Getting a URL from the user
		    BufferedImage inputURLImage = ImageIO.read(FileSearcher.enterFileURL()); 
		    //Processing the image
		    ImageProcessing.readImage(inputURLImage, k); 		    
	    } catch (Exception e) {
		    System.out.println(ConsoleColour.RED);
		    System.out.println("[ERROR] Problem with loading File from URL, Check URL and try again.");
		    System.out.println(ConsoleColour.RESET);
	   }
    }
      //Method for displaying all available filters (kernels) in the menu
    private void displayFilters() {
	    System.out.println(ConsoleColour.BLUE);
	    kernelSet.forEach(System.out::println); 
	    System.out.println(ConsoleColour.RESET);
    }
      //Method for selecting a filter(kernel) in the menu
    private void enterFilter() {	  
	    String s;	
	    System.out.println(ConsoleColour.BLUE);
	    System.out.println("Enter the selected Filter");
	    System.out.println(ConsoleColour.RESET);
	    s = getStringInput();
	    try {		   
		    setKernel(s); // try set kernel by passing s to the .valueOf() enum method
			 } catch (Exception e) {
				 System.out.println(ConsoleColour.RED);
				 System.out.println("[ERROR] Invalid Filter Name, Please Try Again!");
				 System.out.println(ConsoleColour.RESET);
			 }
	     System.out.println(ConsoleColour.BLUE);
		 System.out.println("[INFO] The filter you choose is " + s); 
		 System.out.println(ConsoleColour.RESET);
    }
     // Method for taking user's input for kernel
	private static String getStringInput() {
		Scanner scanStringIn = new Scanner(System.in);		
		String s = scanStringIn.nextLine().toUpperCase(); //not case sensitive
		return s;				
	}	
	
	// Method for setting a Kernel - Takes String argument and checks it against the Enum
		// valueOf method.
		private static void setKernel(String s) {
			k = Kernel.valueOf(s);
	}
}
