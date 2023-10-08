package PageObjects.Google;

import PageObjects.BasePage;
import conf.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(GooglePage.class);



    private final String url=ConfigurationManager.configuration().url();
    private static final By acceptedCookies=By.id("L2AGLb");
    private static final By searchBar=By.id("APjFqb");
    private static final By wikipedia=By.xpath("(//a[@href='https://es.wikipedia.org/wiki/Automatizaci%C3%B3n'])[2]");



    public void visitGoogle(){
        getDriver().get(url);
    }
    public void acceptCookies()  {

        wait.until(ExpectedConditions.elementToBeClickable(acceptedCookies));
        getDriver().findElement(acceptedCookies).click();

    }
    public void search(String data){

        wait.until(ExpectedConditions.elementToBeClickable(searchBar));
        getDriver().findElement(searchBar).clear();
        LOGGER.info("SearchBar Cleaned");
        getDriver().findElement(searchBar).sendKeys(data);
        getDriver().findElement(searchBar).sendKeys(Keys.ENTER);

    }
    public boolean wikipediaLinkIsPresent(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(wikipedia));
            return !getDriver().findElements(wikipedia).isEmpty();
        }catch (TimeoutException e){
            return false;
        }

    }
    public void clickWikipediaLink(){
        wait.until(ExpectedConditions.elementToBeClickable(wikipedia));
        getDriver().findElement(wikipedia).click();
    }




}
