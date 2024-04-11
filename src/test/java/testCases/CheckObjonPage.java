package testCases;

import java.time.Duration;

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

public class CheckObjonPage extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("CRMSummaryToggle");
	}
	
	@Test
	public void testControl() throws Exception {
	node = test.createNode("checkToggleOn Summary");
	String sMySqlUid = rb.getString("MySqlUid");
	String sMySqlPwd = rb.getString("MySqlPwd");
	String sMySqlUrl= rb.getString("MySqlUrl");
	
	String sRecordId="122097";
	String sMySqlQuery = "SELECT * FROM `rsoft_workflowtask_queue` where `entity_id` ="+ sRecordId + ";";
	driver.get(sMySqlUrl);
	LoginPage objLP = new LoginPage(driver);
	HomePage objHP = new HomePage(driver);
	AllListPage objALP = new AllListPage(driver);
	AllModuleValues objEDT = new AllModuleValues(driver);
	CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
	CRMReUsables objCRMRs = new CRMReUsables();
	NotificationsPage objNFP = new NotificationsPage(driver);
	DetailViewPage objDVP = new DetailViewPage(driver);
	SMSNotifiers objSMS = new SMSNotifiers(driver);
	objLP.setMySqlUserId(sMySqlUid);
	objLP.setMySqlPasswd(sMySqlPwd);
	Thread.sleep(3000);
	objLP.clickMySqlSubmit();
	Thread.sleep(3000);
//	objPAP.clickDB();
	PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
	objPAP.clickDBLink();
	Thread.sleep(3000);
	objPAP.setTableInDB("rsoft_workflowtask_queue");
	Thread.sleep(4000);
	objPAP.clickaTableLink();
	Thread.sleep(3000);
	objPAP.aNavigtoLastPage();
	
	objPAP.check_SN_Hourly(sRecordId,node);
	
	
	
	
	
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
