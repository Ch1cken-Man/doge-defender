//timer imports
import java.util.Timer;
import java.util.TimerTask;

//screenshot imports
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//random import
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        
        Timer timer = new Timer();
         
        // Helper class extends TimerTask
        TimerTask task = new TimerRuntime();
         
        /*
         *  Schedule() method calls for timer class.
         *  void schedule(TimerTask task, Date firstTime, long intervals inbetween)
         */
         
        timer.schedule(task, 200, 10000); //start at .2 seconds after code runs, and wait 10 seconds between running it. 
         
    }
}






//SCREENSHOT**********************SCREENSHOT*************SCREENSHOT**********

public class Instant{
    public String fileName;
    
    public boolean isPorn;    
    public Instant(){
        fileName = "temp-screenshot.png";
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

    public boolean checkIsPornRandom(String photoFileName)
    {
    //this will run the input file into some sort of checking program and return true or false.
    //currently this is just a stand in until I program it properly.
    
        boolean decision;
        Random rd = new Random(); 
        decision =rd.nextBoolean();
   

    
        isPorn = decision; 
        return decision; 
    }




}

public class action{
    public static void actionDisplayCondensendingText(){
       System.out.println("you've been really bad****************\n********************\n**************************\n**********************\n");

}


}




//TIMER**********TIMER***********TIMER*****
class TimerRuntime extends TimerTask
{
    public void run()
    {
        Instant thisInstant = new Instant();
        System.out.println(thisInstant.captureScreen());
        boolean currentScreenshotContainsPorn = thisInstant.checkIsPornRandom("temp-screenshot.png");
        
        if(currentScreenshotContainsPorn){
        //do the action phase
        action.actionDisplayCondensendingText();
        //increase the interval that the checker would run after the action is completed
} 
        System.out.println("the porn checker program was ran and it says that "+String.valueOf(currentScreenshotContainsPorn)+" the screenshot contains porn");


    }
} 
