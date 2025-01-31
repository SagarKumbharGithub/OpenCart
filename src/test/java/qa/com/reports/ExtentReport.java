 package qa.com.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public  class ExtentReport {

	public ExtentReport() {
	}

	public  static ExtentReports extent;
	static String path;

	public static void initReports() {
		if (Objects.isNull(extent)) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
			String repName = "Test-Report-" + timeStamp + ".html";
		    path = System.getProperty("user.dir") + "\\extent-test-output\\" + repName;
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path).viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY,ViewName.AUTHOR }).apply();
			
			
		//	ExtentSparkReporter failSparkReporter = new ExtentSparkReporter("failed-test-index.html").filter().statusFilter().as(new Status[] { Status.FAIL }).apply();
		//	failSparkReporter.config().setDocumentTitle("Failed Test Case");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setDocumentTitle("Open Cart Test Report");
			sparkReporter.config().setReportName("Open Cart Web Application Test Report");

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);//We can also add -failSparkReporter
		}
	}

	public static void flushReports() throws IOException {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		Desktop.getDesktop().browse(new File(path).toURI());
		//Desktop.getDesktop().browse(new File("failed-test-index.html").toURI());
	}

	public static void createTest(String testCaseName,String authorName,String featureName,String browser) {
		
	  ExtentManager.setExtentTest(extent.createTest(testCaseName).assignCategory(featureName).assignAuthor(authorName).assignDevice(browser));
	//	ExtentManager.setExtentTest(extent.createTest(testCaseName, featureName));
	}
}
