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
import pageObjects.NotificationsPage;
import pageObjects.SMSNotifiers;
import pageObjects.SummaryViewPage;

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

		
		System.out.println("Module Name:  " + sExpModuleName);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();  
		SMSNotifiers objSMS = new SMSNotifiers(driver);
		DetailViewPage objDVP = new DetailViewPage(driver);
		SummaryViewPage objSVP = new SummaryViewPage(driver);
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName2");
		String sPassword1 =  rb.getString("passWord2");
		String sAssignedTo1 = rb.getString("AssignedTo2");
		String sUserName2 =  rb.getString("userName3");
		String sPassword2 =  rb.getString("passWord3");
		String sAssignedTo2 = rb.getString("AssignedTo3");
		
		//Login as User 1
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
		
//		if(objHP.isAvatarDisplayed()) {
////			freport("Home Page Displayed after Login" , "pass",node);
////			objHP.clickAvatar();
//		}
//		else {
//			logger.info("Home Page Not Displayed");
//			freport("Home Page Not Displayed" , "fail",node);
//			System.out.println("Home Page Not Displayed");
//			Assert.fail("Home Page Not Displayed");
//			
//		}
		Thread.sleep(1000);
		objCRMRs.fNavigatetoWorkflow(sDisplayModuleName);
		String sWorkFlowStatus="";
		
		
		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sDisplayModuleName, sExpWorkFlowName, sExecutionCondition);
		System.out.println("Workflow Status Returned: " + sWorkFlowStatus);
		String sWFStatusRetArr[] = sWorkFlowStatus.split(":");
		xlObj.setCellData("Sheet1", 1, 36, sWFStatusRetArr[1]);
		int iWFPos = Integer.parseInt(sWFStatusRetArr[1]);
		if(Boolean.parseBoolean(sWFStatusRetArr[0])==false){
			logger.info(sExpWorkFlowName + "Workflow Not Enabled");
			freport(sExpWorkFlowName + "Workflow Not Enabled", "fail",node);
			Assert.fail(sExpWorkFlowName + "Workflow Not Enabled");
			
		}
		else {
			freport(sExpWorkFlowName + "Workflow Enabled", "pass",node);
			objCRMRs.fClickWorkFlowAndGotoTask(iWFPos);
			logger.info("Clicked Workflow and Navigated to Task Page");
			System.out.println("Clicked Workflow and Navigated to Task Page");
			
			boolean bTaskStatus = objCRMRs.fCheckTaskStatus(sExpWorkFlowName,sActionType,sActionTitle);
			logger.info("Clicked Workflow and Navigated to Task Page");
			System.out.println("Clicked Workflow and Navigated to Task Page");
			
			if(bTaskStatus==false) {
				logger.info("Task Not Active " + sActionType + "  " + sActionTitle);
				freport("Task Not Active " + sActionType + "  " + sActionTitle, "fail",node);
				Assert.fail("Task Not Active " + sActionType + "  " + sActionTitle);
				
			}
			else {
				logger.info("Task Active " + sActionType + "  " + sActionTitle);
				freport("Task Active " + sActionType + "  " + sActionTitle, "pass",node);
				
			}
			
		}//If //Workflow/Task Selection
		//Capture the Notifications Count for all the 3 Users
		String sUser1OldNotifyCount = objCRMRs.fgetNotificationCount();
		UtilityCustomFunctions.logWriteConsole("User1NotifyCount:" + sUser1OldNotifyCount);
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldRecordId = objCRMRs.getLastRecordId();
		
		
		Thread.sleep(5000);
		objHP.clickLogoutCRM();
		Thread.sleep(5000);
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
		UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
		
		String sUser2OldNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//Third User
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
		String sUser3OldNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//Login Back as 1st User to Perform SubmitNewModuleData, 
		//Capture RecordId,check summary, CheckNotification+1 & NotificationPage, 
		//Search Record In Notificaiton for all users 
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
		//Submit Module Data
		//Click Module to Add New Data
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(1000);
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet1");
		UtilityCustomFunctions.fWaitNavLink(sExpModuleName);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		
		//Capture new Record Id & New Notification Count in User 1
		int iCurrRecordId= objCRMRs.getLastRecordId();
		String sCurrRecordId = String.valueOf(iCurrRecordId);
		
		//Validate Notification Count
		String sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		if(sUser1OldNotifyCount!=sUser1CurrNotifyCount) {
			freport("Notification Sent after new Record Added for: " + sUserName, "pass", node);
		}
		else {
			freport("Notification not Sent after new Record Added for: "+sUserName, "fail", node);
		}
		//Validate Module Summary Added Record 
		if(iOldRecordId!=iCurrRecordId) {
			freport("Notification Sent after new Record Added: New Record Id " + sCurrRecordId , "pass", node);
			objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet1","Add New Module Data",sAssignedTo,node);
		}
		else {
			freport("Notification not Sent after new Record Added" + sCurrRecordId, "fail", node);
		}
//		//Validate Notifications Module
//		//Notifications 1/AssignedTo 
//		String sCurrentWinHandle = driver.getWindowHandle();
//		String sNewWindowHanlde="";
//		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		
		
		
		//Notification Module from Menu
		xlObj.setCellData("Sheet1", 1, 11, sCurrRecordId);
//		driver.close();
//		driver.switchTo().window(sCurrentWinHandle);
//		Thread.sleep(1000);
	
		String sStatus = "0";
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo);
		
		int iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After new record added: user" + sAssignedTo +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo , "Add New Record - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo , "Add New Record - Notification received for " +sAssignedTo);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		Thread.sleep(3000);
		
		//Notification sub window
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
				
				
		//****** Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After new record added: user" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		//Notifications 2/AssignedTo 2
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After new record added: user" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);

		//Logout from CurrentUser
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//Validate Notification Count for Other Users.
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
		UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
		
		String sUser2CurrNotifyCount = objCRMRs.fgetNotificationCount();
		
		if(sUser2OldNotifyCount!=sUser2CurrNotifyCount) {
			freport("Notification Sent after new Record Added for: " + sUserName1, "pass", node);
		}
		else {
			freport("Notification not Sent after new Record Added for: "+sUserName1, "fail", node);
		}
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After new record added: user 2" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo1 , "Add New Record - Notification received for " +sAssignedTo1);
		}
		
		System.out.println(sAssignedTo1);
		System.out.println(sAssignedTo1);
		System.out.println(sCurrRecordId);
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		//Third User
		Thread.sleep(3000);
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
		String sUser3CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		if(sUser3OldNotifyCount!=sUser3CurrNotifyCount) {
			freport("Notification Sent after new Record Added for: " + sUserName2, "pass", node);
		}
		else {
			freport("Notification not Sent after new Record Added for: "+sUserName2, "fail", node);
		}
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After new record added: user 3" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			logger.info("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received for " +sAssignedTo2 , "Add New Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);

		
		//******************* Summary Data Validation ***********************************************
		//Logout & Login as User 1
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//Login Back as 1st User to Perform SubmitNewModuleData, 
		//Capture RecordId,check summary, CheckNotification+1 & NotificationPage, 
		//Search Record In Notificaiton for all users 
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
		
		
		sUser1OldNotifyCount = sUser1CurrNotifyCount;
		sUser2OldNotifyCount = sUser2CurrNotifyCount;
		sUser3OldNotifyCount = sUser3CurrNotifyCount;
		iOldRecordId = iCurrRecordId;
		String sOldRecordId = sCurrRecordId;
		//Navigate to Module to add Summary Data
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet2");
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		iCurrRecordId= objCRMRs.getLastRecordId();
		sCurrRecordId = String.valueOf(iCurrRecordId);
		
		//Validate Notification Count
		sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		if(sUser1OldNotifyCount!=sUser1CurrNotifyCount) {
			freport("Notification Sent after Summary Record Added for: " + sUserName, "pass", node);
		}
		else {
			freport("Notification not Sent after Summary Record Added for: "+sUserName, "fail", node);
		}
		//Validate Module Summary Added Record 
		if(iOldRecordId!=iCurrRecordId) {
			freport("Notification Sent after Summary Record Added: New Record Id " + sCurrRecordId + "User: " + sUserName , "pass", node);
			objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet2","Summary Add New Data",sAssignedTo,node);
			Thread.sleep(3000);
		}
		else {
			freport("Notification not Sent after Summary Record Added" + sCurrRecordId + "User: " + sUserName, "fail", node);
		}
		
		//Validate Notifications Module
//		//Notifications 1/AssignedTo 
//		sCurrentWinHandle = driver.getWindowHandle();
//		sNewWindowHanlde="";
//		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		
		//Notification Module from Menu
		 xlObj.setCellData("Sheet2", 1, 11, sCurrRecordId);
		
		 
		
				
		sStatus = "0";
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo);
				
		iNotificationCount = objSMS.getWebTblRowcount(); 
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After summary new add : user 1" + sAssignedTo +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Summary Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Summary Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Summary New Record - Notification received for " +sAssignedTo , "Summary New Record - Notification received for " +sAssignedTo);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Summary New Record - Notification not received for " +sAssignedTo , "Summary New Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		
		//***************Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After summary new add : user 1" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after New Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
				
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		Thread.sleep(3000);
		
		//***************** Notifications /AssignedTo 2
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After summary new add : user 1" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
				
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);

		
		//Logout from CurrentUser
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
				
		//Validate Notification Count for Other Users.
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
		UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
				
		sUser2CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		
		if(sUser2OldNotifyCount!=sUser2CurrNotifyCount) {
			freport("Notification Sent after Summary Record Added for: " + sUserName1, "pass", node);
		}
		else {
			freport("Notification not Sent after Summary Record Added for: "+sUserName1, "fail", node);
		}
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getWebTblRowcount(); 
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After summary new add : user 2" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo1 , "Add Summary Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		//Third User
		Thread.sleep(3000);
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
		
		sUser3CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		if(sUser3OldNotifyCount!=sUser3CurrNotifyCount) {
			freport("Notification Sent after Summary Record Added for: " + sUserName2, "pass", node);
		}
		else {
			freport("Notification not Sent after Summary Record Added for: "+sUserName2, "fail", node);
		}
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After summary new add : user 2" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Summary Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Summary Record - Notification not received for " +sAssignedTo2 , "Add Summary Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
		
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		//******************************Duplicate Record *********************************************
		//Login as User 1
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
		sUser1OldNotifyCount = sUser1CurrNotifyCount;
		sUser2OldNotifyCount = sUser2CurrNotifyCount;
		sUser3OldNotifyCount = sUser3CurrNotifyCount;
		iOldRecordId = iCurrRecordId;
		sOldRecordId = sCurrRecordId;
		
		
		Thread.sleep(3000);
		
		//Click Duplicate & Add  New Record
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		objCRMRs.fNotifyAddValuestoModulePage("Test","//Notification//WF1_Send_Notify_ETR_S_","Sheet3");
		UtilityCustomFunctions.fWaitNavLink(sExpModuleName);		
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();

		Thread.sleep(3000);
		
		iCurrRecordId= objCRMRs.getLastRecordId();
		sCurrRecordId = String.valueOf(iCurrRecordId);
		//Validate Module Summary & Detail Page
		//Validate Notification Count
		sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		if(sUser1OldNotifyCount!=sUser1CurrNotifyCount) {
			freport("Notification Sent after Duplicate Record Added for: " + sUserName, "pass", node);
		}
		else {
			freport("Notification not Sent after Duplicate Record Added for: "+sUserName, "fail", node);
		}
		//Validate Module Duplicate Added Record 
		if(iOldRecordId!=iCurrRecordId) {
			freport("Notification Sent after duplicate Record Added: Record Id " + sCurrRecordId + "User: " + sUserName , "pass", node);
			objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet3","Duplicate with New Data",sAssignedTo,node);
		}
		else {
			freport("Notification not Sent after Duplicate Record Added" + sCurrRecordId + "User: " + sUserName, "fail", node);
		}
		
		//Validate Notifications Module
		//Notifications 1/AssignedTo 
//		sCurrentWinHandle = driver.getWindowHandle();
//		sNewWindowHanlde="";
//		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
						
		//Notification sub window
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		
		xlObj.setCellData("Sheet3", 1, 11, sCurrRecordId);
		
		sStatus = "0";
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo);
						
		iNotificationCount = objSMS.getWebTblRowcount(); 
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Duplicate record : user 1 and AssignedTo:" + sAssignedTo +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Duplicate Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Duplicate Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Duplicate New Record - Notification received for " +sAssignedTo , "Duplicate New Record - Notification received for " +sAssignedTo);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification not received for " +sAssignedTo , "Add Duplicate Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
						
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Duplicate record : user 1 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification received for " +sAssignedTo1 , "Add Duplicate Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification not received for " +sAssignedTo1 , "Add Duplicate Record - Notification received for " +sAssignedTo1);
		}
						
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		
		//Notifications 1/AssignedTo 2
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Duplicate record : user 1 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification received for " +sAssignedTo2 , "Add Duplicate Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification not received for " +sAssignedTo2 , "Add Duplicate Record - Notification received for " +sAssignedTo2);
		}
						
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
		
		//Logout from CurrentUser
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
						
		//Validate Notification Count for Other Users.
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
		UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
						
		sUser2CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		
		if(sUser2OldNotifyCount!=sUser2CurrNotifyCount) {
			freport("Notification Sent after Duplicate Record Added for: " + sUserName1, "pass", node);
		}
		else {
			freport("Notification not Sent after Duplicate Record Added for: "+sUserName1, "fail", node);
		}
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Duplicate record : user 2 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification received for " +sAssignedTo1 , "Add Duplicate Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification not received for " +sAssignedTo1 , "Add Duplicate Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//Third User
		Thread.sleep(3000);
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
		
		sUser3CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		if(sUser3OldNotifyCount!=sUser3CurrNotifyCount) {
			freport("Notification Sent after Duplicate Record Added for: " + sUserName2, "pass", node);
		}
		else {
			freport("Notification not Sent after Duplicate Record Added for: "+sUserName2, "fail", node);
		}
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Duplicate record : user 3 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==1) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification received for " +sAssignedTo2 , "Add Duplicate Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Duplicate Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Duplicate Record - Notification not received for " +sAssignedTo2 , "Add Duplicate Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
		
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		//**************************** Edit with New Data *****************************
		
		//Login Back as 1st User to Perform SubmitNewModuleData, 
		//Capture RecordId,check summary, CheckNotification+1 & NotificationPage, 
		//Search Record In Notificaiton for all users 
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
		sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		sUser1OldNotifyCount = sUser1CurrNotifyCount;
		sUser2OldNotifyCount = sUser2CurrNotifyCount;
		sUser3OldNotifyCount = sUser3CurrNotifyCount;
		iOldRecordId = iCurrRecordId;
		sOldRecordId = sCurrRecordId;
		UtilityCustomFunctions.logWriteConsole("Old Record Id: Before Edit & Save:" + sOldRecordId);
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(3000);
		objCMD.clickEdit();
		Thread.sleep(3000);
		objCRMRs.fNotifyAddValuestoModulePage("Test","//Notification//WF1_Send_Notify_ETR_S_","Sheet4");
		UtilityCustomFunctions.fWaitNavLink(sExpModuleName);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		
		
		iCurrRecordId= objCRMRs.getLastRecordId();
		sCurrRecordId = String.valueOf(iCurrRecordId);
		UtilityCustomFunctions.logWriteConsole("Current Record Id: after Edit & Save:" + sCurrRecordId);
		//Validate Module Summary & Detail Page
		//Validate Notification Count
		sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		
		UtilityCustomFunctions.logWriteConsole("After Edit & Save : Old Notification count : "+sUser1OldNotifyCount+" current Notification count "+ sUser1CurrNotifyCount);
		
		if(Integer.parseInt(sUser1OldNotifyCount) +1 == Integer.parseInt(sUser1CurrNotifyCount)) {
			freport("Notification Sent after Edit &  With New Record Added for: " + sUserName, "pass", node);
		}
		else {
			freport("Notification not Sent after Edit &  With New Record Added for: "+sUserName, "fail", node);
		}
		if(iOldRecordId!=iCurrRecordId) {
			freport("Notification Sent after Edit with New Data Record Added: Record Id " + sCurrRecordId + "User: " + sUserName , "pass", node);
			objCRMRs.fValModuleView("Test", "//Notification//WF1_Send_Notify_ETR_S_","Sheet4","Edit &  With New Record",sAssignedTo,node);
		}
		else {
			freport("Notification not Sent after Edit with New Data Record Added: Record Id " + sCurrRecordId + "User: " + sUserName , "fail", node);
		}
		
		
		//Validate Module Summary Added Record 
		xlObj.setCellData("Sheet1", 1, 11, sCurrRecordId);
		
		sStatus = "0";
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo);
								
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Edit with New Data : user 1 and AssignedTo:" + sAssignedTo +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==2) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Edit & Save New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Edit & Save New Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit & Save New Record New Record - Notification received for " +sAssignedTo , "Edit & Save New Record New Record - Notification received for " +sAssignedTo);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save New Record Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Edit & Save New Record Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Edit & Save New Record Record - Notification not received for " +sAssignedTo , "Add Edit & Save New Record Record - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
						
		iNotificationCount = objSMS.getWebTblRowcount(); 
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Edit with New Data : user 1 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==2) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Edit & Save New Record Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Edit & Save New Record Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Edit & Save New Record Record - Notification received for " +sAssignedTo1 , "Add Edit & Save New Record Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save New Record Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Edit & Save New Record Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Edit & Save New Record - Notification not received for " +sAssignedTo1 , "Add Edit & Save New Record received for " +sAssignedTo1);
		}
								
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		
		//Notifications 1/AssignedTo 2
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Edit with New Data : user 1 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==2) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification received for " +sAssignedTo2 , "Add Edit & Save Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification not received for " +sAssignedTo2 , "Add Edit & Save Record - Notification received for " +sAssignedTo2);
		}
								
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
		
		//Logout from CurrentUser
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
								
		//Validate Notification Count for Other Users.
		//Login Second User
		System.out.println(sCompName);
		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
		UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
		UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
								
		sUser2CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		
		if(sUser2OldNotifyCount!=sUser2CurrNotifyCount) {
			freport("Notification Sent after Edit & Save Record Added for: " + sUserName1, "pass", node);
		}
		else {
			freport("Notification not Sent after Edit & Save Record Added for: "+sUserName1, "fail", node);
		}
		//Notifications 1/AssignedTo 1
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
		
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Edit with New Data : user 2 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==2) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification received for " +sAssignedTo1 , "Add Edit & Save Record - Notification received for " +sAssignedTo1);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification not received for " +sAssignedTo1 , "Add Edit & Save Record - Notification received for " +sAssignedTo1);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
		
		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
		
		
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
				
		//Third User
		Thread.sleep(3000);
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
		
		sUser3CurrNotifyCount = objCRMRs.fgetNotificationCount();
		Thread.sleep(2000);
		if(sUser3OldNotifyCount!=sUser3CurrNotifyCount) {
			freport("Notification Sent after Edit & Save Record Added for: " + sUserName2, "pass", node);
		}
		else {
			freport("Notification not Sent after Edit & Save Record Added for: "+sUserName2, "fail", node);
		}
		
		
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getWebTblRowcount();
		iNotificationCount = iNotificationCount - 1;
		UtilityCustomFunctions.logWriteConsole("After Edit with New Data : user 3 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
		if(iNotificationCount==2) {
			UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Passed: Notification Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification received for " +sAssignedTo2 , "Add Edit & Save Record - Notification received for " +sAssignedTo2);
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
			freport("Failed: Notification not Received after Edit & Save Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
			sAssertinFn.assertEquals("Add Edit & Save Record - Notification not received for " +sAssignedTo2 , "Add Edit & Save Record - Notification received for " +sAssignedTo2);
		}
		
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);

		Thread.sleep(2000);
		objCMD.clickLatestNotification();
		Thread.sleep(3000);
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		//****************************** Single Line Summary Edit  ****************************

		//Login Back as 1st User to Perform SubmitNewModuleData, 
		//Capture RecordId,check summary, CheckNotification+1 & NotificationPage, 
		//Search Record In Notificaiton for all users 
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
		
		sUser1OldNotifyCount = sUser1CurrNotifyCount;
		sUser2OldNotifyCount = sUser2CurrNotifyCount;
		sUser3OldNotifyCount = sUser3CurrNotifyCount;
		iOldRecordId = iCurrRecordId;
		sOldRecordId = sCurrRecordId;
		UtilityCustomFunctions.logWriteConsole("Before Single LIne Edit: Old Record Id: "+iOldRecordId+" current record id "+ iCurrRecordId);
		Thread.sleep(3000);
	
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(5000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
				
		objDVP.clickSendNotifyEdit();
		sEditIndText = "1234567890";
		objCMD.setGenericInputValue("tel", sExpModuleName, "phonenumber", sEditIndText);
		objDVP.clickNotifyRecItemSave(sExpModuleName,"phonenumber");
		UtilityCustomFunctions.fWaitNavLink(sExpModuleName);
		objSVP.fWaitTillControlVisible();
		
		Thread.sleep(3000);
		
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
		
		iCurrRecordId= objCRMRs.getLastRecordId();
		sCurrRecordId = String.valueOf(iCurrRecordId);
		//Validate Module Summary & Detail Page
		//Validate Notification Count
		sUser1CurrNotifyCount = objCRMRs.fgetNotificationCount();
		if(sUser1OldNotifyCount!=sUser1CurrNotifyCount) {
			freport("Notification Sent after Single Line Edit Added for: " + sUserName, "pass", node);
		}
		else {
			freport("Notification not Sent after Single Line Edit Added for: "+sUserName, "fail", node);
		}
		//Validate Module Summary Added Record
		// check whether new record createdfor this scnario manually\
		UtilityCustomFunctions.logWriteConsole("after single line edit Old Record Id: "+iOldRecordId+" current record id "+ iCurrRecordId);
		
		objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
										
		//Notification Module from Menu
		xlObj.setCellData("Sheet4", 1, 11, sCurrRecordId); //check withe n ew record created
		
		
		sStatus = "0";
		objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo);
										
				iNotificationCount = objSMS.getWebTblRowcount(); 
				iNotificationCount = iNotificationCount - 1;
				UtilityCustomFunctions.logWriteConsole("After Single Line Edit : user 1 and AssignedTo:" + sAssignedTo +"Notification record count: "+ iNotificationCount);
				if(iNotificationCount==3) {
					UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Single Line Edit Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
					freport("Passed: Notification Received after Single Line Edit Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "pass",node);
					sAssertinFn.assertEquals("Single Line Edit New Record - Notification received for " +sAssignedTo , "Single Line Edit New Record - Notification received for " +sAssignedTo);
				}
				else {
					UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId);
					freport("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo + " Record Id: " + sCurrRecordId, "fail",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification not received for " +sAssignedTo , "Add Single Line Edit Record - Notification received for " +sAssignedTo);
				}
				objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
				
				Thread.sleep(2000);
				objCMD.clickLatestNotification();
				Thread.sleep(3000);
				objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
				
				//Notifications 1/AssignedTo 1
				objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
								
				iNotificationCount = objSMS.getWebTblRowcount();
				iNotificationCount = iNotificationCount - 1;
				UtilityCustomFunctions.logWriteConsole("After Single Line Edit : user 1 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
				if(iNotificationCount==3) {
					UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
					freport("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification received for " +sAssignedTo1 , "Add Single Line Edit Record - Notification received for " +sAssignedTo1);
				}
				else {
					UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
					freport("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
					sAssertinFn.assertEquals("Add Single Line Edit - Notification not received for " +sAssignedTo1 , "Add Single Line Edit received for " +sAssignedTo1);
				}
										
				objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
				//Notifications 1/AssignedTo 2
				objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
						
				iNotificationCount = objSMS.getWebTblRowcount();
				iNotificationCount = iNotificationCount - 1;
				UtilityCustomFunctions.logWriteConsole("After Single Line Edit : user 1 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
				if(iNotificationCount==3) {
					UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
					freport("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification received for " +sAssignedTo2 , "Add Single Line Edit Record - Notification received for " +sAssignedTo2);
				}
				else {
					UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
					freport("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification not received for " +sAssignedTo2 , "Add Single Line Edit Record - Notification received for " +sAssignedTo2);
				}
										
				objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
				Thread.sleep(2000);
				objCMD.clickLatestNotification();
				Thread.sleep(3000);
				objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
				//Logout from CurrentUser
				Thread.sleep(2000);
				objHP.clickLogoutCRM();
										
				//Validate Notification Count for Other Users.
				//Login Second User
				System.out.println(sCompName);
				System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
				UtilityCustomFunctions.logWriteConsole("current ulr:" + driver.getCurrentUrl());
				UtilityCustomFunctions.logWriteConsole("app ulr:" + sAppUrl);
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
										
				sUser2CurrNotifyCount = objCRMRs.fgetNotificationCount();
				Thread.sleep(2000);
				
				if(sUser2OldNotifyCount!=sUser2CurrNotifyCount) {
					freport("Notification Sent after Edit & Save Record Added for: " + sUserName1, "pass", node);
				}
				else {
					freport("Notification not Sent after Edit & Save Record Added for: "+sUserName1, "fail", node);
				}
				//Notifications 1/AssignedTo 1
				objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo1);
				
				iNotificationCount = objSMS.getWebTblRowcount();
				iNotificationCount = iNotificationCount - 1;
				UtilityCustomFunctions.logWriteConsole("After Single Line Edit : user 2 and AssignedTo:" + sAssignedTo1 +"Notification record count: "+ iNotificationCount);
				if(iNotificationCount==3) {
					UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
					freport("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "pass",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification received for " +sAssignedTo1 , "Add Single Line Edit Record - Notification received for " +sAssignedTo1);
				}
				else {
					UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId);
					freport("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo1 + " Record Id: " + sCurrRecordId, "fail",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification not received for " +sAssignedTo1 , "Add Single Line Edit Record - Notification received for " +sAssignedTo1);
				}
				
				objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
				Thread.sleep(2000);
				objCMD.clickLatestNotification();
				Thread.sleep(3000);
				objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo1,sNotifyTemplateMsg,sActionTitle,node);
				
				Thread.sleep(2000);
				objHP.clickLogoutCRM();
						
				//Third User
				Thread.sleep(3000);
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
				
				sUser3CurrNotifyCount = objCRMRs.fgetNotificationCount();
				Thread.sleep(2000);
				if(sUser3OldNotifyCount!=sUser3CurrNotifyCount) {
					freport("Notification Sent after Single Line Edit Record Added for: " + sUserName2, "pass", node);
				}
				else {
					freport("Notification not Sent after Single Line Edit Record Added for: "+sUserName2, "fail", node);
				}
				objCRMRs.fClickSearch(sCurrRecordId,sAssignedTo2);
						
				iNotificationCount = objSMS.getWebTblRowcount();
				iNotificationCount = iNotificationCount - 1;
				UtilityCustomFunctions.logWriteConsole("After Single Line Edit : user 2 and AssignedTo:" + sAssignedTo2 +"Notification record count: "+ iNotificationCount);
				
				if(iNotificationCount==3) {
					UtilityCustomFunctions.logWriteConsole("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
					freport("Passed: Notification Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "pass",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification received for " +sAssignedTo2 , "Add Single Line Edit Record - Notification received for " +sAssignedTo2);
				}
				else {
					UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId);
					freport("Failed: Notification not Received after Single Line Edit Record Add -"+ sAssignedTo2 + " Record Id: " + sCurrRecordId, "fail",node);
					sAssertinFn.assertEquals("Add Single Line Edit Record - Notification not received for " +sAssignedTo2 , "Add Single Line Edit Record - Notification received for " +sAssignedTo2);
				}
				
				objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sNotifyTemplateMsg,sActionTitle,sCurrRecordId,node);
				Thread.sleep(2000);
				objCMD.clickLatestNotification();
				Thread.sleep(3000);
				objCRMRs.fValNotifySummaryAndDetail(sCurrRecordId,sAssignedTo2,sNotifyTemplateMsg,sActionTitle,node);
				
				Thread.sleep(2000);
				objHP.clickLogoutCRM();
				
		
		
		
	}//Test Method
}//Class
