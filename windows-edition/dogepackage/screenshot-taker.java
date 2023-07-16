//SCREENSHOT**********************SCREENSHOT*************SCREENSHOT**********
package dogepackage;
//screenshot imports
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//tester code
/*
public class Main {
    public static void main(String[] args){
        Instant currently = new Instant("screenshot-folder/testscreenshot1.png");
        currently.captureScreen();
    }
}
*/

public class Instant{

    public String fileName;
    public boolean isPorn;

    public Instant(String screenshotFile){
        fileName = screenshotFile;
        isPorn = false;
          
    }

 	public String captureScreen() 
    {
 	   
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
