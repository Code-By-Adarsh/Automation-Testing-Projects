package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstances(){
        if (extent == null){
            String reportPath = System.getProperty("user.dir")+"\\Reports\\OrangeHRMReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("OrangeHRM Automation Report");
            spark.config().setReportName("OrangeHRM Report");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Automation Tester: ","Adarsh Jayprakash Mishra");
            extent.setSystemInfo("Browser: ","Chrome");
            extent.setSystemInfo("Report: ","OrangeHRM Automation");
        }
        return extent;
    }
}
