package testCases;

import static org.testng.Assert.assertEquals;

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
import pageObjects.SMSNotifiers;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC002_WF2_Send_Notify_OOF_S extends BaseClass{

	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC002_WF2_Send_Notify_OOF_S");
	}
	@Test
	public void testNotify_OOF_S () throws Exception {
		node = test.createNode("Notify_OOF_Save");
		
		logger.info("******starting TC002_WF2_Send_Notify_OOF_S ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\Notification\\WF2_Send_Notify_OOF_S_Live" + ".xlsx" ;
		String sPath=".\\testData\\Notification\\WF2_Send_Notify_OOF_S_Test" + ".xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		
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
		Thread.sleep(1000);
		objCRMRs.fNavigatetoWorkflow(sDisplayModuleName);
		String sWorkFlowStatus="";
		
		
		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sDisplayModuleName, sExpWorkFlowName, sExecutionCondition);
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
			
		}//If
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(1000);
		String sPrevCount = objCRMRs.fgetNotificationCount();
		UtilityCustomFunctions.logWriteConsole("Prevcount before add:" + sPrevCount);
		//Add new Values to the Module
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet1");
		objCRMRs.fValModuleView("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet1","Add New Module Data",sAssignedTo,node);
		
		String sNotCount=""; 
		//Validate Notification for Current User.
		if(sPrevCount==null) {
			sPrevCount = "0";
		}
		
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		xlObj.setCellData("Sheet1", 1, 37, sNotCount);
		System.out.println("Notifications after add:" + sNotCount);
		logger.info("Notifications After add:" + sNotCount);
		if(sNotCount==null) {
			logger.info("Notification Not Received for user: "+ sUserName);
			freport("Notification Not Received for user: "+ sUserName, "fail",node);
			Assert.fail("Notification Not Received for user: "+ sUserName);
		}else if(Integer.parseInt(sPrevCount)+1== Integer.parseInt(sNotCount)){
			logger.info("Notification Received on first save "+ sUserName);
			freport("Notification Received on first save "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Add Notification received", "Add Notification received");
			
		}
		int iOldCount = Integer.parseInt(sNotCount);
		
		//Notifications 1
		String sCurrentWinHandle = driver.getWindowHandle();
		String sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id:After New Record Added: " + sRecordId);
		
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
		//Add Summary Data
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet2");
		objCRMRs.fValModuleView("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet2","Add New Module Data",sAssignedTo,node);
		
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
				
		//Notification for 2nd AssignedTo User
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
		//Notification for 3rd AssignedTo User
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
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		
		//Notification for AssignedTo User 1
		
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
		
		//Notifications 2
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
		
		//Notification 3
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
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet3");
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		
		objCRMRs.fValModuleView("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet3","Duplicate with New Data",sAssignedTo,node);		
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
				
		//Notification 2
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
		//Notification 3
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
		
		//Edit & Save
		//Edit in Summary with No Modification
		String iOldCount1 = "0";
		String iOldCount2 = "0";
		String iOldCount3 = "0";
		Thread.sleep(3000);
		//Logout
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
		
		if(objHP.isAvatarDisplayed()) {
			logger.info("Home Page Displayed");
			freport("Home Page Displayed" , "pass",node);
			System.out.println("Home Page Displayed");
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
			
		}
		
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(sNotCount==null) {
			sNotCount = "0";
		}
		iOldCount1 =sNotCount;
		//Logout & Login with 2 User
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
				
		//Login
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName1);
			objLP.setPassword(sPassword1);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		
		if(objHP.isAvatarDisplayed()) {
			logger.info("Home Page Displayed");
			freport("Home Page Displayed" , "pass",node);
			System.out.println("Home Page Displayed");
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
			
		}
		
		iOldCount2 = objCRMRs.fgetNotificationCount();
		if(iOldCount2==null) {
			iOldCount2 = "0";
		}
		//Get Old Count of 3rd User
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		
		//Login
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName2);
			objLP.setPassword(sPassword2);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		
		if(objHP.isAvatarDisplayed()) {
			logger.info("Home Page Displayed");
			freport("Home Page Displayed" , "pass",node);
			System.out.println("Home Page Displayed");
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
			
		}
		iOldCount3 = objCRMRs.fgetNotificationCount();
		if(iOldCount3==null) {
			iOldCount3 = "0";
		}
		
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(3000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
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
		//Logout
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
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount1)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Save "+ sUserName);
			freport("Notification Not Received on Edit & Save "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit "+ sUserName);
			freport("Failed: Notification Received on Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 2
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName1);
			objLP.setPassword(sPassword1);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(Integer.parseInt(iOldCount2)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Save "+ sUserName1);
			freport("Notification Not Received on Edit & Save "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit "+ sUserName1);
			freport("Failed: Notification Received on Edit "+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
			Assert.fail("Failed: Notification Received on Edit "+ sUserName1);
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 3
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName2);
			objLP.setPassword(sPassword2);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount3)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Save "+ sUserName2);
			freport("Notification Not Received on Edit & Save "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit "+ sUserName2);
			freport("Failed: Notification Received on Edit "+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
			
		}
		
		//Edit with New Data
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
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet4");
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValModuleView("Test", "//Notification//WF2_Send_Notify_OOF_S_","Sheet4","Edit with New Data",sAssignedTo,node);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		//Validate Notification count not received when Edit with New Data
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User1		
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
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount1)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Modidy "+ sUserName);
			freport("Notification Not Received on Edit & Modidy "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit & Modidy Notification not received", "Edit & Modidy Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit & Modidy "+ sUserName);
			freport("Failed: Notification Received on Edit & Modidy "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit & Modidy Notification not received", "Edit & Modidy Notification received");
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 2
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName1);
			objLP.setPassword(sPassword1);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(Integer.parseInt(iOldCount2)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Modidy "+ sUserName1);
			freport("Notification Not Received on Edit & Modidy "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Edit & Modidy Notification not received", "Edit & Modidy Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit & Modify "+ sUserName1);
			freport("Failed: Notification Received on Edit & Modify "+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Edit & Modify Notification not received", "Edit & Modidy Notification received");
			
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 3
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName2);
			objLP.setPassword(sPassword2);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount3)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit & Modify "+ sUserName2);
			freport("Notification Not Received on Edit & Modify "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Edit & Modify Notification not received", "Edit & Modify Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit & Modify "+ sUserName2);
			freport("Failed: Notification Received on Edit & Modify "+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Edit & Modify Notification not received", "Edit & Modify Notification received");
		}
		
		//Single Summary Edit
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
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
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User1		
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
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount1)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Single Edit "+ sUserName);
			freport("Notification Not Received on Single Edit "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Single Edit "+ sUserName);
			freport("Failed: Notification Received on Single Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification received");
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 2
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName1);
			objLP.setPassword(sPassword1);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(Integer.parseInt(iOldCount2)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Single Edit "+ sUserName1);
			freport("Notification Not Received on Single Edit "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Single Edit "+ sUserName1);
			freport("Failed: Notification Received on Single Edit "+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification received");
			
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		//Login with User 3
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName2);
			objLP.setPassword(sPassword2);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount3)==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Single Edit "+ sUserName2);
			freport("Notification Not Received on Single Edit "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Single Edit "+ sUserName2);
			freport("Failed: Notification Received on Single Edit "+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification received");
		}
	}//@Test
}//class	
