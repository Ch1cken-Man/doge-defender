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

//file reading imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//file writing imports
import java.io.IOException;
import java.io.FileWriter;
//file making imports
import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
//        interval = helper.getInterval(); 
        Timer screenshotTimer = new Timer();
         
        TimerTask task = new myTimer(); 
         
        //run the program at the smallest interval, and only run what needs to at that time.
        screenshotTimer.schedule(task, 10000, 2000);//every 2 seconds run the program  
   } 
}

public class myTimer extends TimerTask {
    private boolean takeAndAnalyzeScreenshot(String timeFileName)
    {
    
        Instant thisInstant = new Instant();
        System.out.println(thisInstant.captureScreen());
        boolean currentScreenshotContainsPorn = thisInstant.checkIsPornRandom("temp-screenshot.png");

        int timeSinceIncident = Integer.parseInt(fileHandler.readFile(timeFileName)); 

        if(currentScreenshotContainsPorn)
        {
            //do the action phase
            timeSinceIncident = 0;
            fileHandler.writeToFile(timeFileName, timeSinceIncident);
            action.actionDisplayCondensendingText();
            return true;
        } 



        else
        {
            timeSinceIncident+=2;
            fileHandler.writeToFile(timeFileName, timeSinceIncident);
            System.out.println("nothing bad was found");
            return false;
        }
    }

    public void run(){
        String timeFileName = "/newtxt.txt";

        String intervalStateFile = "/intervalState.txt";

        int intervalState = Integer.parseInt(fileHandler.makeAndReadFile(intervalStateFile));
        int timeSinceIncident = Integer.parseInt(fileHandler.makeAndReadFile(timeFileName));  
        //interval shortly after incident
        //interval slowly after incident (that day)
        //default interval and a day after incident
        //long term "sleep" interval for if no activity was found for a while
        
        int sleepInterval3 = 660480003;         // 300s (5 min) interval until next violation

        int defaultInterval2 = 604800000;         // 120s (2 min) for 24-168 hours after interval2

        int intermediateInterval1 = 86400000;          // 30s for 0-24 hours after interval1
        int intervalFollowingIncident0 = 1800000;          // 10s for 0-30min after violation
         

//        int intervalState = 0;

        if(timeSinceIncident < intervalFollowingIncident0){//interval 0
        intervalState = 0; 
        }
        else if(timeSinceIncident < intermediateInterval1){//interval 1
        intervalState = 1;
        }
        else if(timeSinceIncident < defaultInterval2){//interval 2
        intervalState = 2;
        }
        else if(timeSinceIncident < sleepInterval3){// sleep interval
        intervalState = 3;
        }




//I know this is redundant but it may be helpful to seperate them in the future

        if(intervalState==0 && timeSinceIncident%10==0){//10s interval
            if(takeAndAnalyzeScreenshot(timeFileName)){
                timeSinceIncident=0;
            }
        } 
        else if(intervalState==1 && timeSinceIncident%30==0){//30s interval
             if(takeAndAnalyzeScreenshot(timeFileName)){
                timeSinceIncident=0;
            }
         
        }
        else if(intervalState==2 && timeSinceIncident%120==0){//120s interval
            if(takeAndAnalyzeScreenshot(timeFileName)){
                timeSinceIncident=0;
            }
 
        }
        else if(intervalState==3 && timeSinceIncident%300==0){//5min interval
            if(takeAndAnalyzeScreenshot(timeFileName)){
                timeSinceIncident=0;
            }
 
        }
        timeSinceIncident+=1;
        fileHandler.writeToFile(timeFileName,timeSinceIncident);
        fileHandler.writeToFile(intervalStateFile, intervalState); 

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
public class fileHandler{
    public static String readFile(String theFileName){
        String data = "0";
        try 
        {
            File myObj = new File(theFileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) 
            {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
            return data;
        }       
        catch (FileNotFoundException e) {
            System.out.println("An error occurred. in reading the file.");
            e.printStackTrace();
        }
        return "failed to read file";
    }
    public static void writeToFile(String theFileName, int data) {
        try {
            FileWriter myWriter = new FileWriter(theFileName);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static String makeAndReadFile(String theFileName) {
        try {
            File myObj = new File(theFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return "-1"; //denote that the file was created and needs writing to
            } 
            else { //file already exists - run this code
                System.out.println("File already exists. passing already existing value");
                return readFile(theFileName); 
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "error 404";
    }
}
/*
    public static void writeToFile(String theFilename, int data) throws IOException {
        final File file = new File(theFilename);

        try(final DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(data);
            System.out.println(data+" was successfully added to the file");
        }
        //catch(){
         //   System.out.println("there was an error in editing the file");
        //}
 
    }
    public static int readFile(String theFilename){
        final File file = new File(theFilename);
        int readData;
        System.out.println("ran \"readfile\" script");
        try (final DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            readData = dis.readInt();
            System.out.println("Reading int from file: " + readData);
            //globalInterval = currentInterval;
        }

        catch(){ //this runs when there is not already a file made and sets it to the first boot interval
            System.out.println("the file that contains the interval has an error");
        }
        return readData;
*/


/*
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
*/


