package com.inetbanking2.utilities;

// Listener class used to generate Extent reports
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	ExtentReports extent = new ExtentReports();
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
	String repName = "./\\Extent Reports\\"+"ExtentReport-Spark" + timeStamp + ".html";
	ExtentSparkReporter spark = new ExtentSparkReporter(repName); // repName = specify location

	public static ExtentTest test;

	public void onStart(ITestContext testContext) {
		test = extent.createTest("InetBankingV2 Test Project").// Tile of report
				assignAuthor("shahnawazm").assignCategory("Functional Test cases"). // name of the report
				assignDevice("Windows");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("InetBanking Title ");
		extent.attachReporter(spark); // using the extent reference we are attaching a report which is of type spark
	}

	public void onTestSuccess(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report
		test.pass(tr.getName()); // send the passed information in the extent report
	}

	public void onTestFailure(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report
		test.fail(tr.getName()); // send the failed information in the extent report
		
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";

		File f = new File(screenshotPath);

		if (f.exists()) {
			try {
				//test.fail("Screenshot is below: " + test.addScreenCaptureFromPath(screenshotPath));
				test.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName()); // create new entry in the report
		test.skip(tr.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

	/*
	 * public static String capturescreenshot(WebDriver driver) throws IOException {
	 * File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 * File destinationfilepath = new File("src/../images/screenshot" +
	 * System.currentTimeMillis() + ".png"); String absolutepathlocation =
	 * destinationfilepath.getAbsolutePath(); FileUtils.copyFile(srcfile,
	 * destinationfilepath); return absolutepathlocation; }
	 */
}
