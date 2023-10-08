package conf;

import org.aeonbits.owner.Config;
@Config.Sources(
        "classpath:${env}.properties"
)
public interface Configuration extends Config {

    String url();
    String automatizacion();
    String y270();
    String autToken();
    String browser();
    String urlPets();



}
