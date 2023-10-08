package Util;
import Model.EnvDataEnum;
import conf.ConfigurationManager;
import exception.CustomErrorException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


public class WebDriverFactory {
    private static WebDriver driver = null;

    /** Logger class initialization. */
    private static final Logger LOGGER = LogManager.getLogger(WebDriverFactory.class);




    private static final String CHROME = "chrome";


    public static  void setDriver() throws CustomErrorException {
        String browserName = ConfigurationManager.configuration().browser(); //PropertiesManager.getInstance().getEnvValueFromMatrix( EnvDataEnum.WEB_BROWSER_NAME.getKey());
        String url = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", url);
        LOGGER.info("@setDriver,"+browserName);
//        LOGGER.info("@setDriver,"+browserName+","+remote);

        switch (browserName) {


            case CHROME:


                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--remote-allow-origins=*");
                optionsChrome.addArguments("--scuttleGlobalThis=false");
                optionsChrome.addArguments("--disable-notifications");

                optionsChrome.addArguments("use-fake-device-for-media-stream");
                optionsChrome.addArguments("use-fake-ui-for-media-stream");

                optionsChrome.addArguments("start-maximized");
                DesiredCapabilities capabilities = new DesiredCapabilities ();

                capabilities.setCapability(ChromeOptions.CAPABILITY, optionsChrome);


                driver = new ChromeDriver(optionsChrome);
                break;

            default:
                throw new CustomErrorException("Browser not allowd: " + browserName);

        }
        driver.get(url);



    }

    public static void closeSetup() {
        driver.close();
    }
    public static void quitSetup() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }


}
