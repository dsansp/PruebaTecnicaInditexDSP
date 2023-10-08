package Component;

import PageObjects.Petstore.PetstorePage;
import Utils.BaseTest;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Ejercicio3")

 class Ejercicio3Test extends BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(Ejercicio3Test.class);



    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class Api {


        @Test
        @Order(1)
        @DisplayName("Ejercicio 3: Tratamiento de datos en APIs ")
        void ejercicio3Test() throws  IOException {

            String username = "miusuario";
            String password = "mipassword";
            String name= "David";
            String LastName = "Sans";
            String email = "miemail@example.com";
            String phone="1234567890";

            assertEquals(200,postRest(username,name,LastName,email,password,phone));

            // Verificar que la respuesta sea exitosa (código de estado 200)


            // Paso 2: Recuperar los datos del usuario mediante Selenium
            PetstorePage pet= new PetstorePage();
            pet.visitPetstore();

            // Ingresar al área de inicio de sesión
         pet.clickLogin();
/*
            // Ingresar el nombre de usuario y contraseña
            WebElement usernameInput = driver.findElement(By.name("username"));
            WebElement passwordInput = driver.findElement(By.name("password"));
            WebElement loginSubmitButton = driver.findElement(By.tagName("button"));

            usernameInput.sendKeys(username);
            passwordInput.sendKeys(password);
            loginSubmitButton.click();

            // Verificar que el nombre de usuario coincida con el creado
            WebElement loggedInUserElement = driver.findElement(By.xpath("//span[@id='user_login']"));
            String loggedInUser = loggedInUserElement.getText();
            Assert.assertEquals(username, loggedInUser);


 */
            }
        }







}




