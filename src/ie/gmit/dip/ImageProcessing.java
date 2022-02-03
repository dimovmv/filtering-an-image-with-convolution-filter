package ie.gmit.dip;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.*;
import javax.imageio.ImageIO;

public class ImageProcessing {

	// kernel could be changed by the user
	public static Kernel k;
	
	// method for reading an input image
	public static void readImage(BufferedImage inputImage, Kernel kern) throws IOException {
		k=kern;
		writeImage(inputImage);
	}
	
	//Method for writing an output image
	private static void writeImage(BufferedImage inputImage) throws IOException {				
		BufferedImage outputImage = convolution(inputImage);
				
		// Writing the output image in PNG format
	try {
			ImageIO.write(outputImage, "PNG", new File("output.png"));
	} catch (Exception e) {
			System.out.println(ConsoleColour.RED);
			System.out.println("[ERROR] Writing the File Unseccessful!");	
			System.out.println(ConsoleColour.RESET);
			ImageIO.write(outputImage, "PNG", new File("output.png"));
		}
	}	
   //Method for convoluting the image
	private static BufferedImage convolution(BufferedImage image) {	
		BufferedImage imageO = image;
		BufferedImage output = new BufferedImage(imageO.getWidth(), imageO.getHeight(), imageO.getType());
				
		//Height and width of Image
		int height = image.getHeight();
		int width = image.getWidth();			
		
			// Loops over each pixel of the image
		for (int x = 0; x < (width); x++) {	
			for (int y = 0; y < (height); y++) {
												
				// red, green, blue values that will contain the total r/g/b values after kernel multiplication
				double red = 0, green = 0, blue = 0, alpha = 0;
				int outRGB = 0;					
					
				/* Loops over the Kernel. The offset is added to the x and y coordinates to follow the footprint of the kernel.				
				The offset needs to run from negative half their length (rounded down) to the positive of half their length (rounded down)
				That makes possible to use kernels of different sizes without the calculation be thrown off*/
				try {
					for ( int xOffset = Math.negateExact(k.getKernels().length/2); xOffset <= k.getKernels().length/2; xOffset++) {
						for (int yOffset = Math.negateExact(k.getKernels().length/2); yOffset <= k.getKernels().length/2; yOffset++) {									
									
				/*Calculating X and Y coordinates of the pixel (add offset to match up the kernel with the pixels corresponding to the kernel's footprint)
			   In case of edges of image the '% WIDTH' wraps the image and the pixel from opposite edge is used	*/			
							int realX = (x - k.getKernels().length/ 2 +xOffset+  width) % width; 
							int realY = (y - k.getKernels().length/ 2 +yOffset+  height) % height;
									
							//The RGB value for the pixel
							int RGB = image.getRGB((realX), (realY));	
							int A = (RGB >> 24) & 0xFF;	// Alpha value 
							int R = (RGB >> 16) & 0xFF; //Red Value
							int G = (RGB >> 8) & 0xFF; // Green Value
							int B = (RGB) & 0xFF;      // Blue Value
																	
			   // The RGB is multiplied with current kernel element and added on to the variables red, blue, green and alpha
						red+=  (R*(k.getKernels()[yOffset + k.getKernels().length/2])[xOffset +k.getKernels().length/2]);
						green +=  (G*k.getKernels()[yOffset+ k.getKernels().length/2][xOffset+k.getKernels().length/2]);		
						blue +=  (B*k.getKernels()[yOffset +k.getKernels().length/2][xOffset+k.getKernels().length/2]);
						alpha += (A*k.getKernels()[yOffset +k.getKernels().length/2][xOffset+k.getKernels().length/2]);							
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				  System.out.println("[ERROR] Something went wrong.");	
			}
					
					///Output Red, Green and Blue Values
				int outR, outG, outB, outA;						
				// The value is limited to 0 and 255
				outR = (int) Math.min(Math.max((red),0),255);
				outG = (int) Math.min(Math.max((green),0),255);
				outB = (int) Math.min(Math.max((blue),0),255);
				outA = (int) Math.min(Math.max((alpha),0),255);					
					
				//Recreating the pixel from the channels
			    outRGB =  outRGB |  (outA << 24);
				outRGB = outRGB | (outR << 16);
				outRGB = outRGB  | (outG << 8);
				outRGB = outRGB  | outB ;
				
					//Writing output pixel with the individual color channels 
				output.setRGB(x, y, new Color(outR,outG,outB).getRGB());	
			}
		}
		System.out.println(ConsoleColour.RED);
		System.out.println("[INFO] Processing...Please Wait.");
		System.out.println(ConsoleColour.RESET);
	
		return output;
	}
}

