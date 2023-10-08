package Component;

import PageObjects.BasePage;
import PageObjects.Google.GooglePage;
import PageObjects.Wikipedia.WikipediaPage;
import Utils.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import conf.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Ejercicio2")

 class Ejercicio2Test extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(Ejercicio2Test.class);
    private static final String automatizacion= ConfigurationManager.configuration().automatizacion();
    private static final String expected= ConfigurationManager.configuration().y270();



    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class webAutomation {


        @Test
        @Order(1)
        @DisplayName("Ejercicio 2: Automatización de una web ")
        void ejercicio2Test() throws  IOException {
            GooglePage google = new GooglePage();
            google.visitGoogle();
            LOGGER.info("Visit Google");
            int status =  responseComponent(google.getActuallyPage());
            LOGGER.info("Status page: "+status);
            assertEquals(200,status );
            google.acceptCookies();
            google.search(automatizacion);
            assertTrue(google.wikipediaLinkIsPresent());
            LOGGER.info("Wikipedia link is present");
            google.clickWikipediaLink();
            WikipediaPage wikipedia = new WikipediaPage();
            String text=wikipedia.paragraph();
            assertTrue(text.contains(expected));
            LOGGER.info("Wikipedia year match");
            MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.getScreenshot64("WikipediaResult")).build();
            LOGGER.info("Capture Done! Ejercicio 2 OK");


            }
        }







}




