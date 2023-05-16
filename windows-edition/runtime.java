import collect.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        //public Timer("runtime-timer",true); 
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

class TimerRuntime extends TimerTask
{
    public void run()
    {
        Instant thisInstant = new Instant();
        System.out.println(thisInstant.captureScreen());
    }
} 

