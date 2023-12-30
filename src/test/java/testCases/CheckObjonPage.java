package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SMSNotifiers;
import testBase.BaseClass;
import utilities.UtilityCustomFunctions;

public class CheckObjonPage extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("CheckObjonPage");
	}
	
	@Test
	public void testControl() throws Exception {
	node = test.createNode("CheckObjonPage");
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	driver.get("https://rdot.in/public/admin?Module=Sms_notifiers&view=List&viewname=");
	
	
	
	String sAppUrl = rb.getString("appURL");
	System.out.println("AppURL:" + sAppUrl);
	String sCompName =  rb.getString("companyName");
	String sUserName =  rb.getString("userName1");
	String sPassword =  rb.getString("passWord1");
	String sAssignedTo = rb.getString("AssignedTo1");
	LoginPage objLP = new LoginPage(driver);
	SMSNotifiers objSMS = new SMSNotifiers(driver);
	 if(objLP.isLoginPageDisplayed(sAppUrl)) {
		objLP.setCompanyName(sCompName);
		objLP.setUserName(sUserName);
		objLP.setPassword(sPassword);
		objLP.clickLoginSubmit();
		logger.info("CRM Login Success with:" + sUserName);
		System.out.println("CRM Login Success with:" + sUserName);
		UtilityCustomFunctions.fSoftAssert("Login Success", "Login Success","User: " + sUserName , node);
	}
	else {
		logger.info("CRM Login failed");
		System.out.println("Login Page Not Displayed");
		UtilityCustomFunctions.fSoftAssert("Login Fail", "Login Success","User: " + sUserName , node);
		Assert.fail("Login Page not displayed");
		
	}
	 objSMS.setRecipient("7777777777");
	 objSMS.clickSearch();
	 Thread.sleep(1000);
	 System.out.println("Total SMS Count:" + objSMS.getSMSRowcount());
	 
	}//test
}
