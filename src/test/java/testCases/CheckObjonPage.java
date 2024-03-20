package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.DetailViewPage;
import pageObjects.LoginPage;
import pageObjects.SMSNotifiers;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;

public class CheckObjonPage extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("CRMSummaryToggle");
	}
	
	@Test
	public void testControl() throws Exception {
	node = test.createNode("checkToggleOn Summary");
	
	
	
	String sAppUrl = "https://rdot.in/public/admin?Module=Crmmodonlyonfirstsave&view=Detail&record=97660&Related=Summary";
	driver.get("https://rdot.in/public/login");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
	System.out.println("AppURL:" + sAppUrl);
	String sCompName =  rb.getString("companyName");
	String sUserName =  rb.getString("userName1");
	String sPassword =  rb.getString("passWord1");
	String sAssignedTo = rb.getString("AssignedTo1");
	
	AllListPage objALP = new AllListPage(driver);
	DetailViewPage objDVP = new DetailViewPage(driver);
	LoginPage objLP = new LoginPage(driver);
	SMSNotifiers objSMS = new SMSNotifiers(driver);
	CRMReUsables objCRS = new CRMReUsables();
	
	objLP.setCompanyName(sCompName);
	objLP.setUserName(sUserName);
	objLP.setPassword(sPassword);
	objLP.clickLoginSubmit();
	Thread.sleep(3000);
	Thread.sleep(2000);
	objALP.clickAllList();
	Thread.sleep(1000);
	objALP.clickModuleOnListAll(driver, "CRMNotification");
	Thread.sleep(2000);
	objCRS.fClickFirstRecord();
	Thread.sleep(2000);
	objDVP.fdefaultToggleStatus();
	Thread.sleep(1000);
	objDVP.fSetToggleHeader(true);
	Thread.sleep(1000);
	objDVP.fSetDetailVew(true);
	Thread.sleep(1000);
	objDVP.fdefaultToggleStatus();
	Thread.sleep(3000);
	objDVP.fClickDetailBlockB();
	System.out.println("B Clicked");
	Thread.sleep(3000);
	objDVP.fClickDetailBlockC();
	System.out.println("C Clicked");
	
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
