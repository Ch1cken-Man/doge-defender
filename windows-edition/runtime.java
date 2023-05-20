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
public static int globalInterval;
public class Main {
    public static void main(String[] args) {
        interval = helper.getInterval(); 
        Timer screenshotTimer = new Timer();
         
        TimerTask task = new TimerTask()  {     
        
         
        timer.schedule(task, 200, interval);  
    }
}
public class myTimer extends TimerTask {
    public void run(){

        Instant thisInstant = new Instant();
        System.out.println(thisInstant.captureScreen());
        boolean currentScreenshotContainsPorn = thisInstant.checkIsPornRandom("temp-screenshot.png");
        
        if(currentScreenshotContainsPorn)
        {
        //do the action phase
        action.actionDisplayCondensendingText();
        //increase the interval that the checker would run after the action is completed
        } 
        else
        {
        System.out.println("nothing bad was found");
        }
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
    //randomly says if the screenshot contains porno 
        boolean decision;
        Random rd = new Random(); 
        decision =rd.nextBoolean();
    
        isPorn = decision; 
        return decision; 
    }


}


public class action{
    public static void actionDisplayCondensendingText()
    {
       System.out.println("you've been really bad****************\n********************\n**************************\n**********************\n");

    }
    

}
public class helper{
    public static int getInterval() throws IOException {
        final File file = new File("/interval.txt");

        int firstBootInterval = 200;    // .2 second start time, goes to interval3 initally.
        int interval1 = 10000;          // 10s for 30min after violation
        int interval2 = 30000;          // 30s for 3 hours after interval1
        int interval3 = 120000;         // 120s for 8 hours after interval2
        int interval4 = 300000;         // 300s interval until next violation

        try (final DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            currentInterval = dis.readInt();
            System.out.println("Reading int from file: " + currentInterval);
            globalInterval = currentInterval;
        }
        catch(IOException){ //this runs when there is not already a file made and sets it to the first boot interval
            System.out.println("the file that contains the interval has an error");
        }
    }
    public static void changeInterval(int newInterval) throws IOException{
        final File file = newFile("/interval.txt");

        try(final DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(newInterval);
            System.out.println("the file was edited properly");
        }
        catch(IOException){
            System.out.println("there was an error in editing the file");
        }
    }

}
