package PageObjects.Wikipedia;

import PageObjects.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(WikipediaPage.class);

    private static final By paragraph = By.cssSelector("div#mw-content-text>div>p:nth-of-type(28)");
    private static final By history = By.xpath("//div[text()='Historia temprana']");


    public String paragraph() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(history));
        WebElement element=getDriver().findElement(history);
        element.click();
        Actions action= new Actions(getDriver());
        action.moveToElement(element).perform();
        LOGGER.info("Moving to object");
        wait.until(ExpectedConditions.visibilityOfElementLocated(paragraph));
        return getDriver().findElement(paragraph).getText();


    }





}
