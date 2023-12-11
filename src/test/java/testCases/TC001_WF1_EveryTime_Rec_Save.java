package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;
import utilities.CRMReUsables;
import testBase.BaseClass;
import pageObjects.AllListPage;
import pageObjects.AllModuleValues;
import pageObjects.CreateModuleDataPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC001_WF1_EveryTime_Rec_Save extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC001_WF1_EveryTime_Rec_Save");
	}
	@Test
	public void testWFEveryTimeRecSave () throws Exception {
		node = test.createNode("EveryRecordSaved");
		
		logger.info("******starting WF1_EveryTime_Rec_Save ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
//		String sPath=".\\testData\\Notification\\" + "WF1_EveryTime_Rec_Save_Live" + ".xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF1_EveryTime_Rec_Save_Test" + ".xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
//		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
		String sPhoneNoumber=xlObj.getCellData("Sheet1", 1, 3);
		String sNumberField=xlObj.getCellData("Sheet1", 1, 4);
		String sEmail=xlObj.getCellData("Sheet1", 1, 5);
		String sPickListItem=xlObj.getCellData("Sheet1", 1, 6);
		String sEnterYourNumber=xlObj.getCellData("Sheet1", 1, 7);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 8);
		String sActionType=xlObj.getCellData("Sheet1", 1, 9);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 10);
		
		String sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();  
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName1");
		String sPassword1 =  rb.getString("passWord1");
		String sAssignedTo1 = rb.getString("AssignedTo1");
		String sUserName2 =  rb.getString("userName2");
		String sPassword2 =  rb.getString("passWord2");
		String sAssignedTo2 = rb.getString("AssignedTo2");
		
		
		
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			Assert.fail("Login Page not displayed");
			System.out.println("Login Page Not Displayed");
		}
		Thread.sleep(3000);
		
		if(objHP.isAvatarDisplayed()) {
			freport("Home Page Displayed after Login" , "pass",node);
//			objHP.clickAvatar();
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			Assert.fail("Home Page Not Displayed");
			System.out.println("Home Page Not Displayed");
		}
		Thread.sleep(1000);
		boolean bIsWrkFlwStatus = false;
		logger.info("Check Whether the workflow status is enabled");
		bIsWrkFlwStatus =  objCRMRs.IsWorflowEnabled(sExpModuleName,sExpWorkFlowName,sExecutionCondition);
		System.out.println("Main Workflow status: " + bIsWrkFlwStatus);
		if(bIsWrkFlwStatus==false) {
			logger.info(sExpWorkFlowName + "Workflow Not Enabled");
			Assert.fail(sExpWorkFlowName + "Workflow Not Enabled");
			freport(sExpWorkFlowName + "Workflow Not Enabled", "fail",node);
		}
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(1000);
//		objCMD.displayComboBoxItems();
//		objCMD.SetAssignedTo(sAssignedTo);
		
		System.out.println("Phone Number:" + sPhoneNoumber);
		
//	    driver.findElement(By.xpath("//input[@name='crmeditfieldmodule_phonenumber']")).sendKeys("1234567890");
	    
		objCMD.SetPhoneNumber(sPhoneNoumber);
		System.out.println("phone number entered");
		objCMD.SetNumberField(sNumberField);
		
		System.out.println("number field entered");
		Thread.sleep(1000);
		if(objCMD.isEmailDisplayed()) {
			System.out.println("Email control present");
			objCMD.SetEmail(sEmail);
		}
		else {
			System.out.println("Email control missing");
		}

		Thread.sleep(1000);
		objCMD.SetEnterYourNumber(sEnterYourNumber);
		Thread.sleep(2000);
		//sPickListItem
		objCMD.clickMenuList(sPickListItem);
		objCMD.selectMenuValue(sPickListItem);
		objCMD.clickSave();
		Thread.sleep(10000);
		sPhoneNoumber = "+91 " + sPhoneNoumber;
		System.out.println("Expected: "+sPhoneNoumber);
		objCRMRs.fValModuleView(sExpModuleName,sAssignedTo,sPhoneNoumber,sNumberField,sEmail,sPickListItem,sEnterYourNumber,node);
		
		//Notifications 1
		String sCurrentWinHandle = driver.getWindowHandle();
		String sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
//		objCRMRs.fValModuleView(sExpModuleName,sAssignedTo,sPhoneNoumber,sNumberField,sEmail,sPickListItem,sEnterYourNumber,node);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sUserName,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(2000);

		
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName1);
			objLP.setPassword(sPassword1);
			objLP.clickEyeIcon();
			Thread.sleep(3000);
			objLP.clickLoginSubmit();
			Thread.sleep(3000);
		}
		else {
			logger.info("CRM Login failed");
			Assert.fail("Login Page not displayed");
			System.out.println("Login Page Not Displayed");
		}
		Thread.sleep(3000);
		
		//Validate Notification in New Login
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo1,sUserName1,sActionTitle,node);
//		objCRMRs.fValModuleView(sExpModuleName,sAssignedTo,sPhoneNoumber,sNumberField,sEmail,sPickListItem,sEnterYourNumber,node);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		
		objHP.clickAvatar();
		objHP.clickLogout();
		//Third User
		
		System.out.println(sCompName);
		System.out.println("UserName2:" + sUserName2 + "Password2: " +sPassword2);
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName2);
			objLP.setPassword(sPassword2);
			objLP.clickEyeIcon();
			Thread.sleep(3000);
			objLP.clickLoginSubmit();
			Thread.sleep(3000);
		}
		else {
			logger.info("CRM Login failed");
			Assert.fail("Login Page not displayed");
			System.out.println("Login Page Not Displayed");
		}
		Thread.sleep(3000);
		//Validate Notification in New Login
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
//		objCRMRs.fValModuleView(sExpModuleName,sAssignedTo,sPhoneNoumber,sNumberField,sEmail,sPickListItem,sEnterYourNumber,node);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo2,sUserName2,sActionTitle,node);
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		String sStatus = "0";
		objCRMRs.fClickSearch(sRecordId);
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sUserName2,sActionTitle,sRecordId,node);
		
		objHP.clickAvatar();
		objHP.clickLogout();		
		
		sAssertinFn.assertAll();
		
	}//test
}//class
