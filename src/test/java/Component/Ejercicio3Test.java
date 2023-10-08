package Component;

import Utils.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.IOException;

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



            }
        }







}




