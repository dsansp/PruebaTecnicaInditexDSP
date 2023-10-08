package Extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
               ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
            extent.attachReporter(spark);

        }
        return extent;
    }
}