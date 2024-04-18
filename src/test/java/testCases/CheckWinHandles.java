package testCases;

import java.time.Duration;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.AllModuleValues;
import pageObjects.CreateModuleDataPage;
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.NotificationsPage;
import pageObjects.PHPMyAdminPage;
import pageObjects.SMSNotifiers;
import pageObjects.SummaryViewPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;

public class CheckWinHandles extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("Window Handles");
	}
	
	@Test
	public void testControl() throws Exception {
	node = test.createNode("get window handles");
	
	LoginPage objLP = new LoginPage(driver);
	HomePage objHP = new HomePage(driver);
	AllListPage objALP = new AllListPage(driver);
	AllModuleValues objEDT = new AllModuleValues(driver);
	CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
	CRMReUsables objCRMRs = new CRMReUsables();
	NotificationsPage objNFP = new NotificationsPage(driver);
	DetailViewPage objDVP = new DetailViewPage(driver);
	SMSNotifiers objSMS = new SMSNotifiers(driver);
	
	driver.get(rb.getString("appURL"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	String sAppUrl = rb.getString("appURL");
	String sCompName =  rb.getString("companyName");
	String sUserName =  rb.getString("userName");
	String sPassword =  rb.getString("passWord");
	String sAssignedTo = rb.getString("AssignedTo");
	String sUserName1 =  rb.getString("userName3");
	String sPassword1 =  rb.getString("passWord3");
	String sAssignedTo1 = rb.getString("AssignedTo3");
	String sUserName2 =  rb.getString("userName2");
	String sPassword2 =  rb.getString("passWord2");
	String sAssignedTo2 = rb.getString("AssignedTo2");
	
	Thread.sleep(3000);
	if(objLP.isLoginPageDisplayed(sAppUrl)) {
		objLP.setCompanyName(sCompName);
		objLP.setUserName(sUserName);
		objLP.setPassword(sPassword);
		objLP.clickLoginSubmit();
		
	}
	else {
		logger.info("CRM Login failed");
		System.out.println("Login Page Not Displayed");
		
	}
	String sCurrentWindow = driver.getWindowHandle();
	System.out.println("Main Window:" + sCurrentWindow);
	NotificationsPage objNotfy = new NotificationsPage(driver);
	objNotfy.clickNotificatons();
	Thread.sleep(10000);
	objNotfy.clickNotifyFirstMsg();
	Thread.sleep(10000);
	Set<String> Handles = driver.getWindowHandles();
	
	System.out.println("Total Windows Opened:" + Handles.size());
	for(String handle: Handles) {
		System.out.println("Window Handle:" + handle.toString());
		System.out.println("Window Title:" + driver.getTitle());
	}
	Thread.sleep(10000);

	
	
	

	

	
	
	
	
	
//	 if(objLP.isLoginPageDisplayed(sAppUrl)) {
//		objLP.setCompanyName(sCompName);
//		objLP.setUserName(sUserName);
//		objLP.setPassword(sPassword);
//		objLP.clickLoginSubmit();
//		logger.info("CRM Login Success with:" + sUserName);
//		System.out.println("CRM Login Success with:" + sUserName);
//		UtilityCustomFunctions.fSoftAssert("Login Success", "Login Success","User: " + sUserName , node);
//	}
//	else {
//		logger.info("CRM Login failed");
//		System.out.println("Login Page Not Displayed");
//		UtilityCustomFunctions.fSoftAssert("Login Fail", "Login Success","User: " + sUserName , node);
//		Assert.fail("Login Page not displayed");
//		
//	}
	 
	 
	}//test
}
