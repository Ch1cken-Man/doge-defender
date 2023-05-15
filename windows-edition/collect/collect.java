import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		//System.out.println("Hello World");
        Instant newInstant = new Instant();
        System.out.println(newInstant.captureScreen()); 
		}
	

}

public class Instant{
    private static String fileName = "temp-screenshot.png";

 	public static String captureScreen() {
//        String fileName = "temp-screenshot.png";
 	    String returnValue = "";	
        try{
	    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  	    	Rectangle screenRectangle = new Rectangle(screenSize);
           //keypress robot
            Robot robot = new Robot();
  		    BufferedImage image = robot.createScreenCapture(screenRectangle);
		    File file = new File(fileName);
  		    ImageIO.write(image, "png", file);
            returnValue = "successfully took screenshot and saved it to "+fileName;
        }

        catch(IOException e){
           returnValue+="  error in the i/o stack   ";
        }

        catch(AWTException e){
            returnValue+="  error in making the robot   ";
        }
        
        return returnValue;
    }   

}

