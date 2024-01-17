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
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SMSNotifiers;

public class TC001_WF1_Send_Notify_ETR_S extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC001_Notification_ETR_Save");
	}
	@Test
	public void testNotifyETR_S () throws Exception {
		node = test.createNode("Notify_ETR_S");
		
		logger.info("******starting WF1_EveryTime_Rec_Save ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
//		String sPath=".\\testData\\Notification\\" + "WF1_Send_Notify_ETR_S" + "_Live.xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF1_Send_Notify_ETR_S" + "_Test.xlsx" ;
		
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
		String sDisplayModuleName=xlObj.getCellData("Sheet1", 1, 42);
		String sEditIndText=xlObj.getCellData("Sheet1", 1, 43);
		String sNotifyTemplateMsg=xlObj.getCellData("Sheet1", 1, 51);
		System.out.println("Print Notify Template Message:" + sNotifyTemplateMsg);
		String sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();  
		SMSNotifiers objSMS = new SMSNotifiers(driver);
		DetailViewPage objDVP = new DetailViewPage(driver);
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
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		
		if(objHP.isAvatarDisplayed()) {
//			freport("Home Page Displayed after Login" , "pass",node);
//			objHP.clickAvatar();
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
			
		}
//		Thread.sleep(1000);
//		objCRMRs.fNavigatetoWorkflow(sDisplayModuleName);
//		String sWorkFlowStatus="";
//		
//		
//		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sDisplayModuleName, sExpWorkFlowName, sExecutionCondition);
//		String sWFStatusRetArr[] = sWorkFlowStatus.split(":");
//		xlObj.setCellData("Sheet1", 1, 36, sWFStatusRetArr[1]);
//		int iWFPos = Integer.parseInt(sWFStatusRetArr[1]);
//		if(Boolean.parseBoolean(sWFStatusRetArr[0])==false){
//			logger.info(sExpWorkFlowName + "Workflow Not Enabled");
//			freport(sExpWorkFlowName + "Workflow Not Enabled", "fail",node);
//			Assert.fail(sExpWorkFlowName + "Workflow Not Enabled");
//			
//		}
//		else {
//			freport(sExpWorkFlowName + "Workflow Enabled", "pass",node);
//			objCRMRs.fClickWorkFlowAndGotoTask(iWFPos);
//			logger.info("Clicked Workflow and Navigated to Task Page");
//			System.out.println("Clicked Workflow and Navigated to Task Page");
//			
//			boolean bTaskStatus = objCRMRs.fCheckTaskStatus(sExpWorkFlowName,sActionType,sActionTitle);
//			logger.info("Clicked Workflow and Navigated to Task Page");
//			System.out.println("Clicked Workflow and Navigated to Task Page");
//			
//			if(bTaskStatus==false) {
//				logger.info("Task Not Active " + sActionType + "  " + sActionTitle);
//				freport("Task Not Active " + sActionType + "  " + sActionTitle, "fail",node);
//				Assert.fail("Task Not Active " + sActionType + "  " + sActionTitle);
//				
//			}
//			else {
//				logger.info("Task Active " + sActionType + "  " + sActionTitle);
//				freport("Task Active " + sActionType + "  " + sActionTitle, "pass",node);
//				
//			}
//			
//		}//If
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName,6);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(1000);

		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet1");
		objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet1","Add New Module Data",sAssignedTo,node);
		//Notifications 1
		String sCurrentWinHandle = driver.getWindowHandle();
		String sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id:After New Record Added: " + sRecordId);
//		
//		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		String sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
		
		int iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo , "Add New Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo , "Add New Record - Notification received for " +sAssignedTo);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		//AssignedTo1
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		//AssignedTo2
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);

		
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
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo , "Add New Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sUserName1,sActionTitle,sRecordId,node);
		objHP.clickAvatar();
		objHP.clickLogout();
//		//Third User
		Thread.sleep(3000);
		System.out.println("First Time 3rd User Login");
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
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sUserName2,sActionTitle,sRecordId,node);
		objHP.clickAvatar();
		objHP.clickLogout();
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
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		//Navigate to Module to add Summary Data
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet2");
		objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet2","Summary Add New Data",sAssignedTo,node);
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet2", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id: After New Summary Data Added: " + sRecordId);
		
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
		
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Add Summary Record-"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Add Summary Record -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo , "Add Summary Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo , "Add Summary Record - Notification received for " +sAssignedTo);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Add Summary Record-"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Add Summary Record -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Add Summary Record-"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Add Summary Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Add Summary Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);

		//Duplicate No Modify
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
				
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet3", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id: After Duplicate With No Modification: " + sRecordId);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
				
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate-"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification received for " +sAssignedTo , "Duplicate Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate Record -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate Record -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification not received for " +sAssignedTo , "Duplicate Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate-"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification received for " +sAssignedTo1 , "Duplicate Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification not received for " +sAssignedTo1 , "Duplicate Record - Notification received for " +sAssignedTo1);
		}
				
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate Record-"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification received for " +sAssignedTo2 , "Duplicate Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate Record -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate Record - Notification not received for " +sAssignedTo2 , "Duplicate Record - Notification received for " +sAssignedTo2);
		}
				
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		//Duplicate with Modification
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		objCRMRs.fNotifyAddValuestoModulePage("Test","//Notification//WF1_Send_Notify_ETR_S_","Sheet3");
		
		
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet3","Duplicate with New Data",sAssignedTo,node);
	
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet3", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id: After Duplicate with New Modification Record: " + sRecordId);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
						
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate Change-"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate Change-"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate Change Record - Notification received for " +sAssignedTo , "Duplicate Change Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate Change Record -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate Change Record -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate Change Record - Notification not received for " +sAssignedTo , "Duplicate Change Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate change-"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate change -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate change Record - Notification received for " +sAssignedTo1 , "Duplicate change Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate change -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate change-"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate change Record - Notification not received for " +sAssignedTo1 , "Duplicate change Record - Notification received for " +sAssignedTo1);
		}
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Duplicate change Record-"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Duplicate change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate change Record - Notification received for " +sAssignedTo2 , "Duplicate change Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Duplicate change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Duplicate change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Duplicate change Record - Notification not received for " +sAssignedTo2 , "Duplicate change Record - Notification received for " +sAssignedTo2);
		}
						
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		//Edit in Summary with No Modification
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(3000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(6000);
		System.out.println("Before selecting 1st Record");
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		System.out.println("Before Edit button clicked in summary view");
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		objALP.clickAllList();
		Thread.sleep(1000);
		System.out.println("Before Edit button clicked in summary view");

		
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet3", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id:Edit No Change: " + sRecordId);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
						
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Edit No Change-"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit No Change-"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification received for " +sAssignedTo , "Edit No Change Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after Edit No Change Record -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit No Change Record -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification not received for " +sAssignedTo , "Edit No Change Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Edit No Change-"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit No Change -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification received for " +sAssignedTo1 , "Edit No Change Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Edit No Change -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit No Change-"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification not received for " +sAssignedTo1 , "Edit No Change Record - Notification received for " +sAssignedTo1);
		}
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		sStatus = "0";
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Edit No Change Record-"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit No Change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification received for " +sAssignedTo2 , "Edit No Change Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Edit No Change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit No Change Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit No Change Record - Notification not received for " +sAssignedTo2 , "Edit No Change Record - Notification received for " +sAssignedTo2);
		}
						
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
		
		//Edit with New Data
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(3000);
		objCMD.clickEdit();
		Thread.sleep(3000);
		objCRMRs.fNotifyAddValuestoModulePage("Test","//Notification//WF1_Send_Notify_ETR_S_","Sheet4");
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet4","Edit with New Data",sAssignedTo,node);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		
//		//Notifications 1
			sCurrentWinHandle = driver.getWindowHandle();
			sNewWindowHanlde="";
			sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
			sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
			System.out.println("Captured Record Id:" + sRecordId);
			xlObj.setCellData("Sheet3", 1, 11, sRecordId);
			logger.info("Captured RecordId");
			UtilityCustomFunctions.logWriteConsole("Record Id:Edit with New Data: " + sRecordId);
			driver.close();
			driver.switchTo().window(sCurrentWinHandle);
			sStatus = "0";
			objCRMRs.fClickSearch(sRecordId,sAssignedTo);
							
			iNotificationCount = objSMS.getSMSRowcount(); 
			if(iNotificationCount==2) {
				logger.info("Passed: Notification Received after Edit with New Data-"+ sAssignedTo + " Record Id: " + sRecordId);
				freport("Passed: Notification Received after Edit with New Data-"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
				sAssertinFn.assertEquals("Edit with New Data Record - Notification received for " +sAssignedTo , "Edit with New Data Record - Notification received for " +sAssignedTo);
			}
			else {
				logger.info("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId);
				freport("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
				sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo , "Edit with New Data - Notification received for " +sAssignedTo);
			}
			objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
			sStatus = "0";
			objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
			iNotificationCount = objSMS.getSMSRowcount(); 
			if(iNotificationCount==2) {
				logger.info("Passed: Notification Received after Edit with New Data-"+ sAssignedTo1 + " Record Id: " + sRecordId);
				freport("Passed: Notification Received after Edit with New Data -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
				sAssertinFn.assertEquals("Edit with New Data - Notification received for " +sAssignedTo1 , "Edit with New Data - Notification received for " +sAssignedTo1);
			}
			else {
				logger.info("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo1 + " Record Id: " + sRecordId);
				freport("Failed: Notification not Received after Edit with New Data-"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
				sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo1 , "Edit with New Data - Notification received for " +sAssignedTo1);
			}
			objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
			sStatus = "0";
			objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
			iNotificationCount = objSMS.getSMSRowcount(); 
			if(iNotificationCount==2) {
				logger.info("Passed: Notification Received after Edit with New Data-"+ sAssignedTo2 + " Record Id: " + sRecordId);
				freport("Passed: Notification Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
				sAssertinFn.assertEquals("Edit with New Data - Notification received for " +sAssignedTo2 , "Edit with New Data - Notification received for " +sAssignedTo2);
			}
			else {
				logger.info("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId);
				freport("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
				sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo2 , "Edit with New Data - Notification received for " +sAssignedTo2);
			}
				objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
				//Single Summary Edit
				Thread.sleep(3000);
				objALP.clickAllList();
				Thread.sleep(1000);
				objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
				System.out.println("Module clicked");
				Thread.sleep(3000);
				objCMD.clickExistingModData(1);
				Thread.sleep(1000);
				objDVP.clickEdtSngFldMod();
				Thread.sleep(1000);
				System.out.println("Module Name:" + sExpModuleName);
				System.out.println("Edit Ind Text:" + sEditIndText);
				objCMD.setGenericInputValue("number", sExpModuleName, "numberfield", sEditIndText);
				objDVP.clickNMRecItemSave();
				UtilityCustomFunctions.checkPageLoadComplete();
				Thread.sleep(10000);
				
				//Notifications 1
				sCurrentWinHandle = driver.getWindowHandle();
				sNewWindowHanlde="";
				sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
				sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
				System.out.println("Captured Record Id:" + sRecordId);
				xlObj.setCellData("Sheet4", 1, 11, sRecordId);
				logger.info("Captured RecordId");
				UtilityCustomFunctions.logWriteConsole("Record Id:Single Summary Value Edit: " + sRecordId);
				driver.close();
				driver.switchTo().window(sCurrentWinHandle);
				sStatus = "0";
				objCRMRs.fClickSearch(sRecordId,sAssignedTo);
								
				iNotificationCount = objSMS.getSMSRowcount(); 
				if(iNotificationCount==3) {
					logger.info("Passed: Notification Received after Single Edit-"+ sAssignedTo + " Record Id: " + sRecordId);
					freport("Passed: Notification Received after Single Edit-"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification received for " +sAssignedTo , "Single Edit Record - Notification received for " +sAssignedTo);
				}
				else {
					logger.info("Failed: Notification not Received after Single Edit Record -"+ sAssignedTo + " Record Id: " + sRecordId);
					freport("Failed: Notification not Received after Single Edit Record -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification not received for " +sAssignedTo , "Single Edit Record - Notification received for " +sAssignedTo);
				}
				objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
				sStatus = "0";
				objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
				iNotificationCount = objSMS.getSMSRowcount(); 
				if(iNotificationCount==3) {
					logger.info("Passed: Notification Received after Single Edit-"+ sAssignedTo1 + " Record Id: " + sRecordId);
					freport("Passed: Notification Received after Single Edit -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification received for " +sAssignedTo1 , "Single Edit Record - Notification received for " +sAssignedTo1);
				}
				else {
					logger.info("Failed: Notification not Received after Single Edit -"+ sAssignedTo1 + " Record Id: " + sRecordId);
					freport("Failed: Notification not Received after Single Edit-"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification not received for " +sAssignedTo1 , "Single Edit Record - Notification received for " +sAssignedTo1);
				}
				objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
				sStatus = "0";
				objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
				iNotificationCount = objSMS.getSMSRowcount(); 
				if(iNotificationCount==3) {
					logger.info("Passed: Notification Received after Single Edit Record-"+ sAssignedTo2 + " Record Id: " + sRecordId);
					freport("Passed: Notification Received after Single Edit Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification received for " +sAssignedTo2 , "Single Edit Record - Notification received for " +sAssignedTo2);
				}
				else {
					logger.info("Failed: Notification not Received after Single Edit Record -"+ sAssignedTo2 + " Record Id: " + sRecordId);
					freport("Failed: Notification not Received after Single Edit Record -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
					sAssertinFn.assertEquals("Single Edit Record - Notification not received for " +sAssignedTo2 , "Single Edit Record - Notification received for " +sAssignedTo2);
				}
								
				objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sRecordId,node);
				
				
		
		sAssertinFn.assertAll();
		
	}//test
}//class
