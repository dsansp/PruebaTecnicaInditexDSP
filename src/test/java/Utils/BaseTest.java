package Utils;

import Model.DataModelEnum;
import Model.EnvDataEnum;
import Util.PropertiesManager;
import Util.WebDriverFactory;
import com.aventstack.extentreports.ExtentReports;
import conf.ConfigurationManager;
import exception.CustomErrorException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import java.util.Objects;

import static Util.WebDriverFactory.getDriver;

public class BaseTest {

	private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
	ExtentReports extent;

	private final PropertiesManager propertiesManager = PropertiesManager.getInstance();


	private static final String EDGE = "edge";
	private static final String CHROME = "chrome";



	public BaseTest() {


	}

	@BeforeAll
	public static void Setup() throws CustomErrorException {
		LOGGER.info("*********************************");
		LOGGER.info("BeforeAll");
		LOGGER.info("*********************************");
		EnvDataEnum dataPath = EnvDataEnum.ENV_DATA_PATH;
		LoadDataProperty(dataPath);
	}


	@BeforeEach
	public void setup() {


		try {

			WebDriverFactory.setDriver();


		} catch (Exception e) {
			e.printStackTrace();

		}


	}

	@AfterEach
	public void teardown() {
		LOGGER.info("*********************************");
		LOGGER.info("teardown");
		LOGGER.info("*********************************");
		String browserName = ConfigurationManager.configuration().browser();


		if (Objects.equals(browserName, EDGE)) {
			try {
				WebDriverFactory.closeSetup();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Error al cerrar el Driver");
			}
		}
		if (Objects.equals(browserName, CHROME)) {
			try {
				WebDriverFactory.closeSetup();
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Error al cerrar el Driver");
			}
		}

		else {
			try {
				WebDriverFactory.quitSetup();

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("Error al salir el Driver");

			}

		}
	}



	private PropertiesManager getPropertiesManager() {
		return propertiesManager;
	}

	public String getEnvValue(EnvDataEnum key) {
		return getPropertiesManager().getEnvValueFromMatrix(key.getKey());
	}

	public String getDataValue(EnvDataEnum key) {
		return getPropertiesManager().getDataValueFromMatrix(key.getKey());
	}

	public String getDataEntry(DataModelEnum key) {
		return getPropertiesManager().getDataValueFromMatrix(key.getKey());
	}

	// variante
	public String getDataEntryPath(DataModelEnum key, EnvDataEnum datapath) {
		return getPropertiesManager().getDataValueFromMatrixFromProperty(key.getKey(), datapath);
	}

	public static void LoadDataProperty(EnvDataEnum dataPath) throws CustomErrorException {
		PropertiesManager.getInstance().loadPropertiesMatrix(PropertiesManager.CONFIG_FILE_PROP);
		String pathData = PropertiesManager.getInstance().getEnvValueFromMatrix(dataPath.getKey());
		LOGGER.info(dataPath.getKey() + "=>" + pathData);
		PropertiesManager.getInstance().loadPropertiesMatrix(pathData);
	}

	public static int responseComponent(String url) {


		String customUserAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
		RestAssured.baseURI = url;
		Response response = RestAssured.given()
				.header("User-Agent", customUserAgent)
				.get();
		LOGGER.info(response.getStatusCode());
		return response.getStatusCode();
	}
	public static String getResponseBody(String url) {

		String customUserAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
		RestAssured.baseURI = url;
		Response response = RestAssured.given()
				.header("User-Agent", customUserAgent)
				.get();
		LOGGER.info(response.getStatusCode());
		return response.getBody().asString();
	}

	public static int responsePostSMS(String address) {
		String url = "https://staging-api.ethermail.io/user-verified/user-verify-flags";
		String[] flags = {"sms_validated"};
		String customUserAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
		String body = "{ \"address\": \"" + address + "\", \"flags\": "+ toJsonArray(flags) +"}";
		String authToken = ConfigurationManager.configuration().autToken();
		String authHeaderName = "X-Access-Token";
		RestAssured.baseURI = url;
		int statusCode = RestAssured.given()
				.contentType(ContentType.JSON)
				.header(authHeaderName, authToken)
				.header("User-Agent", customUserAgent)
				.body(body)
				.post()
				.getStatusCode();
		return statusCode;

	}
	public static int responsePostTransaction(String address) {
		String url = "https://staging-api.ethermail.io/user-verified/user-verify-flags";
		String[] flags = {"has_transactions"};
		String customUserAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36";
		String body = "{ \"address\": \"" + address + "\", \"flags\": "+ toJsonArray(flags) +"}";
		String authToken = ConfigurationManager.configuration().autToken();
		String authHeaderName = "X-Access-Token";
		RestAssured.baseURI = url;
		int statusCode = RestAssured.given()
				.contentType(ContentType.JSON)
				.header(authHeaderName, authToken)
				.header("User-Agent", customUserAgent)
				.body(body)
				.post()
				.getStatusCode();
		return statusCode;

	}
	private static String toJsonArray(String[] array) {
		StringBuilder jsonArray = new StringBuilder();
		jsonArray.append("[");
		for (int i = 0; i < array.length; i++) {
			jsonArray.append("\"").append(array[i]).append("\"");
			if (i != array.length - 1) {
				jsonArray.append(",");
			}
		}
		jsonArray.append("]");
		return jsonArray.toString();
	}

	public void resetCookies(){
		getDriver().manage().deleteAllCookies();
	}
}
