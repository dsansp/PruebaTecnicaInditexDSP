package PageObjects;

import Model.EnvDataEnum;
import Util.PropertiesManager;
import Util.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

import static Util.WebDriverFactory.getDriver;


public class BasePage {


    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    private PropertiesManager propertiesManager = PropertiesManager.getInstance();

    private static final long DEFAULT_TIME = 70;
    private static final long DEFAULT_SLEEP = 2;

        private static WebDriver driver;
        public static WebDriverWait wait;

	public BasePage() {
            driver = WebDriverFactory.getDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME), Duration.ofSeconds(DEFAULT_SLEEP));
        }

    protected static  WebDriver getDriver() {
        return driver;
    }


    private PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }
    public String getDataValue(EnvDataEnum key) {
        return getPropertiesManager().getDataValueFromMatrix(key.getKey());
    }


    protected void waitToUrlMatch(String url) {
        wait.until(ExpectedConditions.urlMatches(url));
    }

    public void refreshBrowser() {
        getDriver().navigate().refresh();
    }
    public String getCookies() {
        return String.valueOf(getDriver().manage().getCookies());

    }
    public String getActuallyPage(){
        return  getDriver().getCurrentUrl();
    }
    public void wait10() throws InterruptedException {
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void waitUntilText(By id, String text){
        try {
            wait.until(ExpectedConditions.textToBe(id, text));
        }catch (NoSuchElementException e) {
            Assertions.fail("Failure: "+text+" Not match");

        }
    }


    public void waitExpect(int time) throws InterruptedException {
       getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
    public void waitExpect(int time, WebDriver driver) throws InterruptedException {
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
    public String verifyUrl() {
        return getDriver().getCurrentUrl();}



    public void newWindow(String url){
        getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
        Set<String> windowHandles = getDriver().getWindowHandles();
        for (String windowHandle : windowHandles) {
            getDriver().switchTo().window(windowHandle);
            if (!windowHandle.equals(getDriver().getWindowHandle())) {
                break;
            }
        }
        getDriver().get(url);
    }
    public void driverClose(){
        getDriver().close();
    }
    public void driverQuit(){
        getDriver().quit();
    }
    public void changeTab() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(3000);


        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        LOGGER.info("tabs nº= "+tabs2.size());
        if (tabs2.size() > 1) {

        driver.switchTo().window(tabs2.get(1));
        }else{
            Assertions.fail("Tabs not open properly");
        }
    }
    public void changeTab(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(3000);


        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        LOGGER.info("tabs nº= "+tabs2.size());
        if (tabs2.size() > 1) {

        driver.switchTo().window(tabs2.get(1));
        }else{
            Assertions.fail("Tabs not open properly");
        }
    }
       public void changeInicitalTab() throws InterruptedException {

        Thread.sleep(1000);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));



    }
    public void changeInicitalTab(WebDriver driver) throws InterruptedException {

        Thread.sleep(1000);
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(0));



    }
    public void deleteCookies(){
        getDriver().manage().deleteAllCookies();
    }
    public int numberOfTabs() {


        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
       return tabs2.size();




    }

    public void closeTab(){


        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }
    public void closeTab(WebDriver driver){


        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }



    public void refresh() {
        driver.navigate().refresh();
    }

    public static String capture() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("./src/../BStackImages/" + System.currentTimeMillis()
                + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
        return errflpath;
    }
    public static String getScreenshot(String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    public static String getScreenshot64(String screenshotName) throws IOException {

        InputStream in = new FileInputStream(getScreenshot(screenshotName));
        byte[] imageBytes = IOUtils.toByteArray(in);
        String base64 = Base64.getEncoder().encodeToString(imageBytes);
        return base64;
    }
    public String getName() {
        return  new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    }


}
