import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Main {

	public static void captureScreen(String fileName) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		Rectangle screenRectangle = new Rectangle(screenSize);
  		
try{Robot robot = new Robot();}
catch(IOException e){
System.out.println("error in making Robot");}

  		BufferedImage image = robot.createScreenCapture(screenRectangle);
		File file = new File(fileName);
  		ImageIO.write(image, "png", file);
		
		System.out.println("the screenshot is: "+ file.getPath());
}



	public static void main(String[] args) {
		System.out.println("Hello World");
		String screenshotName = "temp-screenshot.png";	
		captureScreen(screenshotName);
	}
	

}

/*
public class screenInstant() {
	String screenshot = "temp-screenshot.png";

	public static void captureScreen(String fileName) throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
  		Rectangle screenRectangle = new Rectangle(screenSize);
  		Robot robot = new Robot();
  		BufferedImage image = robot.createScreenCapture(screenRectangle);
		File file = new File(fileName);
  		ImageIO.write(image, "png", file);
  		LOG.debug("A screenshot is captured to: " + file.getPath());
}



}
*/
