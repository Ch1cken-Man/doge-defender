
package dogepackage;
//random import
import java.util.Random;
//tested works 6/26/23
/*
//tester code
public class Main {
    public static void main(String[] args){
    boolean outcome = PChecker.checkIsPornRandom("lajsd;lfkj");
    if(outcome){
    System.out.println("true");
    }
    else{
    System.out.println("false");
    }
    }
}
*/
public class PChecker{
    public static boolean checkIsPornRandom(String photoFileName)
    {
    //tested works
    //randomly says if the screenshot contains porno 
        boolean decision;
        Random rd = new Random(); 
        decision =rd.nextBoolean();
    
        return decision; 
    }

}
