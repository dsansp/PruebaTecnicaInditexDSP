package Extent;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static Util.WebDriverFactory.getDriver;

public class ScreenShot {
    //    public static void capturescreen( String screenShotName, String status) {
    public static String capturescreen(String screenShotName) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();

            File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

            File destinationFile = new File("./target/"+ screenShotName + ".png");
            String Filepath = destinationFile.getAbsolutePath();
            FileUtils.copyFile(scrFile, destinationFile);
            return Filepath;
/*
            if (status.equals("FAILURE")) {
                FileUtils.copyFile(scrFile, new File("./target/ScreenshotsFailure/" + screenShotName + ".png"));
            } else if (status.equals("SUCCESS")) {
                FileUtils.copyFile(scrFile, new File("./target/ScreenshotsSuccess/" + screenShotName + ".png"));
            }
*/

//            System.out.println("Printing screen shot taken for className " + screenShotName);

        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }

        return screenShotName;
    }

    public class CaptureScreenshot {
        public static byte[] captureScreenshot(String screenshotName) {
            // Captura de pantalla utilizando Selenium
            TakesScreenshot ts = (TakesScreenshot) getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            String destinationPath = "/target/Screenshots/" + screenshotName + ".png";
            File destination = new File(destinationPath);

//            try {
//            //                FileUtils.copyFile(source, destination);
//                System.out.println("Captura de pantalla tomada: " + destinationPath);
//            } catch (IOException e) {
//                System.out.println("Error al tomar la captura de pantalla: " + e.getMessage());
//            }

           byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
           return screenshot;
        }
    }
}



