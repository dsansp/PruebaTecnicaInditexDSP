package PageObjects.Petstore;

import PageObjects.BasePage;
import conf.Configuration;
import conf.ConfigurationManager;
import org.aeonbits.owner.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PetstorePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(PetstorePage.class);
    private static final String url= ConfigurationManager.configuration().urlPets();
    private static final By paragraph = By.cssSelector("div#mw-content-text>div>p:nth-of-type(28)");
    private static final By history = By.xpath("//div[text()='Historia temprana']");
    private static final By iniciarSesion = By.linkText("Iniciar sesión");

    public void visitPetstore(){
        getDriver().get(url);
    }
    public void clickLogin(){
        WebElement loginButton = getDriver().findElement(iniciarSesion);
        loginButton.click();
    }

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
