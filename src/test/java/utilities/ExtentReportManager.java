package utilities;

import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;

//Extent report 5.x...//version


import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestNGListener {
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public ExtentTest test;
	public ExtentTest node;

	String repName;

	@BeforeSuite
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\Report\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("Test Automation Report"); // Title of report
		sparkReporter.config().setReportName("Automation Exercise Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "CRM");
		extent.setSystemInfo("Module", "Forms");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Browser Name", System.getProperty("browser"));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "Testing source");

		extent.setSystemInfo(timeStamp, timeStamp);

	}

	@AfterSuite
	public void flush() {
		// TODO Auto-generated method stub
		extent.flush();
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());

		test.log(Status.PASS, "Test Passed");
	}

//
	// public void onTestFailure(ITestResult result) {
	public void onTestFailure(ITestResult result) throws IOException, InterruptedException {
		// test = extent.createTest(result);
		// test.log(Status.FAIL, "Test Failed");
		// test.log(Status.FAIL, result.getThrowable().getMessage());

		try {

			// String imgPath = new SaiSudrikBaseClass().captureScreen(result.getName());
			// test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.getCause();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
//	@AfterSuite
//	public void onFinish(ITestContext testContext) {
//		extent.flush();
//		System.err.println("After Suite");

	/*
	 * try { URL url = new
	 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
	 * 
	 * // Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
	 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
	 * email.setHostName("smtp.googlemail.com"); email.setSmtpPort(465);
	 * email.setAuthenticator(new
	 * DefaultAuthenticator("pavanoltraining@gmail.com","password"));
	 * email.setSSLOnConnect(true); email.setFrom("pavanoltraining@gmail.com");
	 * //Sender email.setSubject("Test Results");
	 * email.setMsg("Please find Attached Report....");
	 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver email.attach(url,
	 * "extent report", "please check report..."); email.send(); // send the email }
	 * catch(Exception e) { e.printStackTrace(); }
	 */
//	}

}
