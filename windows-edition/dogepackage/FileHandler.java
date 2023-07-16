package dogepackage; 
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

//make a general purpose fileHandler in java
//untested 6/26/23

//tester code
/*
public class Main {
    public static void main(String[] args){
        fileHandler.writeToFile("testFile.txt","this data was successfully written"); 
    }
}
*/
public class fileHandler{
    
    public static String readFile(String theFileName){
        //readFile works
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
            System.out.println("An error occurred in reading the file.");
            e.printStackTrace();
        }
        return "failed to read file";
    }
    public static String writeToFile(String theFileName, String data) {
        //writeToFile works
        try {
            FileWriter myWriter = new FileWriter(theFileName);
            myWriter.write(data);
            myWriter.close();
            return "Successfully wrote \""+data+"\" to the file.";
            
        } 
        catch (IOException e) {
            e.printStackTrace();
            return "An error occurred in writing to the file.";
        }
    }
    //returns current value of the file if it exists
    //returns "no value created yet" if file does not exist
/*
    public static String makeAndReadFile(String theFileName) {
        String fileName = "/screenshot-folder"+theFileName;
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                return "-1"; //denote that the file was created and needs writing to
                //the file will be written over once the rest of the code runs
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
*/
}
