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
import pageObjects.SMSNotifiers;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

	public class TC003_WF3_Send_Notify_ETR_M extends BaseClass {
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("WF3_Send_Notify_ETR_M");
	}
	
	@Test
	public void testNotify_ETR_M () throws Exception {
		node = test.createNode("Notify_ETR_M");
		
		logger.info("******starting WF3_Notify_Every_time_record_modifies ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\Notification\\" + "WF3_Send_Notify_ETR_M" + "_Live.xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF3_Send_Notify_ETR_M" + "_Test.xlsx" ;
	
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
		String sUserName1 =  rb.getString("userName4");
		String sPassword1 =  rb.getString("passWord4");
		String sAssignedTo1 = rb.getString("AssignedTo4");
		String sUserName2 =  rb.getString("userName5");
		String sPassword2 =  rb.getString("passWord5");
		String sAssignedTo2 = rb.getString("AssignedTo5");
		
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
		//Workflow and Task Navigation
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
		String iOldCount1 = "0";
		String iOldCount2 = "0";
		String iOldCount3 = "0";
		String sNotCount="0";
		int iNotificationCount = 0;
		//Capture Old Count Values
		Thread.sleep(1000);
		objHP.clickAvatar();
		Thread.sleep(2000);
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
		//get iOldCount2 value
		//Login with User 2
		objHP.clickAvatar();
		Thread.sleep(2000);
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
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(sNotCount==null) {
			sNotCount = "0";
		}
		iOldCount2 =sNotCount;
		//get iOldCount3 Value
		//Login with 3rd User
		objHP.clickAvatar();
		Thread.sleep(2000);
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
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(sNotCount==null) {
			sNotCount = "0";
		}
		iOldCount3 =sNotCount;
		
		Thread.sleep(2000);
		//Validate Notification count Values
		//Login with User 1 : rsoft
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
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
		//Submit new Data
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName,6);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(2000);
		String sPrevCount = objCRMRs.fgetNotificationCount();
		if(sPrevCount==null) {
			sPrevCount = "0";
		}
		System.out.println("Prevcount before add:" + sPrevCount);
		UtilityCustomFunctions.logWriteConsole("Prevcount before add:" + sPrevCount);
		
		//**************Add New Record
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet1");
//		objCRMRs.fValModuleView("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet1","Add New Module Data",sAssignedTo,node);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after add: " + sNotCount);
//		if(Integer.parseInt(iOldCount1)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after new record added on ETRM "+ sUserName);
//			freport("Notification not Received after new record added on ETRM "+ sUserName, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName, "Notification not Received after new record added on ETRM"+ sUserName);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after new record added on ETRM "+ sUserName);
//			freport("Notification Received after new record added on ETRM "+ sUserName, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName, "Notification not Received after new record added on ETRM"+ sUserName);
//		}
//		// Check count for User 2
//		//Login with User 2
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		
//		//Login Second User
//		System.out.println(sCompName);
//		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName1);
//			objLP.setPassword(sPassword1);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after add: " + sNotCount);
//		if(Integer.parseInt(iOldCount2)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after new record added on ETRM "+ sUserName1);
//			freport("Notification not Received after new record added on ETRM "+ sUserName1, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName1, "Notification not Received after new record added on ETRM"+ sUserName1);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after new record added on ETRM "+ sUserName1);
//			freport("Notification Received after new record added on ETRM "+ sUserName1, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName1, "Notification not Received after new record added on ETRM"+ sUserName1);
//		}
//		
//		//Login with User3 and Validate Notification count
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		//Third User
//		Thread.sleep(3000);
//		System.out.println("First Time 3rd User Login");
//		System.out.println(sCompName);
//		System.out.println("UserName2:" + sUserName2 + "Password2: " +sPassword2);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName2);
//			objLP.setPassword(sPassword2);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after add: " + sNotCount);
//		if(Integer.parseInt(iOldCount3)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after new record added on ETRM "+ sUserName2);
//			freport("Notification not Received after new record added on ETRM "+ sUserName2, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName2, "Notification not Received after new record added on ETRM"+ sUserName1);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after new record added on ETRM "+ sUserName2);
//			freport("Notification Received after new record added on ETRM "+ sUserName2, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after new record added on ETRM"+ sUserName2, "Notification not Received after new record added on ETRM"+ sUserName2);
//		}
//		
//		///************ New Record Add Completed *************
//		Thread.sleep(2000);
//		//************ Add New Summary Data Completed *************
//		//Login with User 1 : rsoft
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName);
//			objLP.setPassword(sPassword);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}		
//		
//		
//		//Add Summary Data
//		Thread.sleep(3000);
//		objALP.clickAllList();
//		Thread.sleep(1000);
//		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
//		System.out.println("Module clicked");
//		Thread.sleep(3000);
//		objCMD.clickExistingModData(1);
//		
//		objDVP.clickAddRecord();
//		Thread.sleep(3000);
//		
//		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet2");
//		objCRMRs.fValModuleView("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet2","Summary Add New Data",sAssignedTo,node);
//		
//		sNotCount = objCRMRs.fgetNotificationCount();
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Summary Add New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount1)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Summary Add New Data ETRM "+ sUserName);
//			freport("Notification not Received after Summary Add New Data ETRM "+ sUserName, "pass",node);
//			sAssertinFn.assertEquals("Notification not Received after Summary Add New Data ETRM"+ sUserName, "Notification not Received after Summary Add New Data ETRM"+ sUserName);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Summary Add New Data on ETRM "+ sUserName);
//			freport("Notification Received after Summary Add New Data on ETRM "+ sUserName, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Summary Add New Data on ETRM"+ sUserName, "Notification not Received after Summary Add New Data on ETRM"+ sUserName);
//		}
//		//Login User 2
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		
//		//Login Second User
//		System.out.println(sCompName);
//		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName1);
//			objLP.setPassword(sPassword1);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Summary Add New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount2)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Summary Add New Data on ETRM "+ sUserName1);
//			freport("Notification not Received after new Summary Add New Data on ETRM "+ sUserName1, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Summary Add New Data on ETRM"+ sUserName1, "Notification not Received after Summary Add New Data on ETRM"+ sUserName1);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Summary Add New Data on ETRM "+ sUserName1);
//			freport("Notification Received after Summary Add New Data on ETRM "+ sUserName1, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Summary Add New Data on ETRM"+ sUserName1, "Notification not Received after Summary Add New Data on ETRM"+ sUserName1);
//		}
//		
//		//Logout Third User
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		//Login 3rd User
//		System.out.println(sCompName);
//		System.out.println("UserName2:" + sUserName2 + "Password2: " +sPassword2);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName2);
//			objLP.setPassword(sPassword2);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Summary Add New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount3)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Summary Add New Data on ETRM "+ sUserName2);
//			freport("Notification not Received after new Summary Add New Data on ETRM "+ sUserName2, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Summary Add New Data on ETRM"+ sUserName2, "Notification not Received after Summary Add New Data on ETRM"+ sUserName2);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Summary Add New Data on ETRM "+ sUserName2);
//			freport("Notification Received after Summary Add New Data on ETRM "+ sUserName2, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Summary Add New Data on ETRM"+ sUserName2, "Notification not Received after Summary Add New Data on ETRM"+ sUserName2);
//		}
//		//Login with User 1 : rsoft
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName);
//			objLP.setPassword(sPassword);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}	
//		//Duplicate No Modify
//		Thread.sleep(3000);
//		objALP.clickAllList();
//		Thread.sleep(1000);
//		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
//		System.out.println("Module clicked");
//		Thread.sleep(3000);
//		objCMD.clickExistingModData(1);
//		Thread.sleep(1000);
//		objDVP.clickDuplicateRecord();
//		Thread.sleep(5000);
//		objCMD.clickSave();
//		UtilityCustomFunctions.checkPageLoadComplete();
//		Thread.sleep(10000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate No Modify: " + sNotCount);
//		if(Integer.parseInt(iOldCount1)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate No Modify ETRM "+ sUserName);
//			freport("Notification not Received after Duplicate No Modify ETRM "+ sUserName, "pass",node);
//			sAssertinFn.assertEquals("Notification not Received after Duplicate No Modify ETRM"+ sUserName, "Notification not Received after Duplicate No Modify ETRM"+ sUserName);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate No Modify on ETRM "+ sUserName);
//			freport("Notification Received after Duplicate No Modify on ETRM "+ sUserName, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate No Modify on ETRM"+ sUserName, "Notification not Received after Duplicate No Modify on ETRM"+ sUserName);
//		}
//		//Login User 2
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		
//		//Login Second User
//		System.out.println(sCompName);
//		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName1);
//			objLP.setPassword(sPassword1);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate No Modify: " + sNotCount);
//		if(Integer.parseInt(iOldCount2)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate No Modify on ETRM "+ sUserName1);
//			freport("Notification not Received after new Duplicate No Modify on ETRM "+ sUserName1, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate No Modify on ETRM"+ sUserName1, "Notification not Received after Duplicate No Modify on ETRM"+ sUserName1);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate No Modify on ETRM "+ sUserName1);
//			freport("Notification Received after Duplicate No Modify on ETRM "+ sUserName1, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate No Modify on ETRM"+ sUserName1, "Notification not Received after Duplicate No Modify on ETRM"+ sUserName1);
//		}
//		
//		//Third User
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		System.out.println("UserName2:" + sUserName2 + "Password2: " +sPassword2);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName2);
//			objLP.setPassword(sPassword2);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate No Modify: " + sNotCount);
//		if(Integer.parseInt(iOldCount3)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate No Modify on ETRM "+ sUserName2);
//			freport("Notification not Received after new Duplicate No Modify on ETRM "+ sUserName2, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate No Modify on ETRM"+ sUserName2, "Notification not Received after Duplicate No Modify on ETRM"+ sUserName2);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate No Modify on ETRM "+ sUserName2);
//			freport("Notification Received after Duplicate No Modify on ETRM "+ sUserName2, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate No Modify on ETRM"+ sUserName2, "Notification not Received after Duplicate No Modify on ETRM"+ sUserName2);
//		}
//		//Login with User 1 : rsoft
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName);
//			objLP.setPassword(sPassword);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		
//		//Duplicate with Modification
//		Thread.sleep(3000);
//		objALP.clickAllList();
//		Thread.sleep(1000);
//		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
//		System.out.println("Module clicked");
//		Thread.sleep(3000);
//		objCMD.clickExistingModData(1);
//		Thread.sleep(1000);
//		objDVP.clickDuplicateRecord();
//		
//		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet3");
//		Thread.sleep(5000);
//		UtilityCustomFunctions.checkPageLoadComplete();
//		Thread.sleep(10000);
//		
////		objCRMRs.fValModuleView("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet3","Duplicate with New Data",sAssignedTo,node);
//		
//		sNotCount = objCRMRs.fgetNotificationCount();
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate with New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount1)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate with New Data ETRM "+ sUserName);
//			freport("Notification not Received after Duplicate with New Data ETRM "+ sUserName, "pass",node);
//			sAssertinFn.assertEquals("Notification not Received after Duplicate with New Data ETRM"+ sUserName, "Notification not Received after Duplicate with New Data ETRM"+ sUserName);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate with New Data on ETRM "+ sUserName);
//			freport("Notification Received after Duplicate with New Data on ETRM "+ sUserName, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate with New Data on ETRM"+ sUserName, "Notification not Received after Duplicate with New Data on ETRM"+ sUserName);
//		}
//		//Login User 2
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		
//		//Login Second User
//		System.out.println(sCompName);
//		System.out.println("UserName1:" + sUserName1 + "Password1: " +sPassword1);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName1);
//			objLP.setPassword(sPassword1);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate with New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount2)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate with New Data on ETRM "+ sUserName1);
//			freport("Notification not Received after new Duplicate with New Data on ETRM "+ sUserName1, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate with New Data on ETRM"+ sUserName1, "Notification not Received after Duplicate with New Data on ETRM"+ sUserName1);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate with New Data on ETRM "+ sUserName1);
//			freport("Notification Received after Duplicate with New Data on ETRM "+ sUserName1, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate with New Data on ETRM"+ sUserName1, "Notification not Received after Duplicate with New Data on ETRM"+ sUserName1);
//		}
//		
//		//Third User
//		Thread.sleep(3000);
//		objHP.clickAvatar();
//		Thread.sleep(2000);
//		objHP.clickLogout();
//		Thread.sleep(3000);
//		System.out.println("UserName2:" + sUserName2 + "Password2: " +sPassword2);
//		if(objLP.isLoginPageDisplayed(sAppUrl)) {
//			objLP.setCompanyName(sCompName);
//			objLP.setUserName(sUserName2);
//			objLP.setPassword(sPassword2);
//			objLP.clickEyeIcon();
//			Thread.sleep(3000);
//			objLP.clickLoginSubmit();
//			Thread.sleep(3000);
//		}
//		else {
//			logger.info("CRM Login failed");
//			Assert.fail("Login Page not displayed");
//			System.out.println("Login Page Not Displayed");
//		}
//		Thread.sleep(3000);
//		sNotCount = objCRMRs.fgetNotificationCount();
//		
//		if(sNotCount==null) {
//			sNotCount = "0";
//		}
//		UtilityCustomFunctions.logWriteConsole("Notification count after Duplicate with New Data: " + sNotCount);
//		if(Integer.parseInt(iOldCount3)== Integer.parseInt(sNotCount)){
//			UtilityCustomFunctions.logWriteConsole("Notification not Received after Duplicate with New Data on ETRM "+ sUserName2);
//			freport("Notification not Received after new Duplicate with New Data on ETRM "+ sUserName2, "pass",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate with New Data on ETRM"+ sUserName2, "Notification not Received after Duplicate with New Data on ETRM"+ sUserName2);	
//		
//		}
//		else {
//			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after Duplicate with New Data on ETRM "+ sUserName2);
//			freport("Notification Received after Duplicate with New Data on ETRM "+ sUserName2, "fail",node);
//			sAssertinFn.assertEquals("Notification Received after Duplicate with New Data on ETRM"+ sUserName2, "Notification not Received after Duplicate with New Data on ETRM"+ sUserName2);
//		}
		//Login with User 1
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
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
		
		//Edit & Save
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
		Thread.sleep(3000);
		//Notification count
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount1)+1==Integer.parseInt(sNotCount)) {
			logger.info("Notification Received on Edit & Save "+ sUserName);
			freport("Notification Received on Edit & Save "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit Notification received", "Edit Notification received");
		}else {
			logger.info("Failed: Notification not Received on Edit "+ sUserName);
			freport("Failed: Notification not Received on Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
		}
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
		//Notifications Assigned To 1
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received Edit & Save -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received Edit & Save -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit & Save - Notification received for " +sAssignedTo , "Edit & Save - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received Edit & Save -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received Edit & Save -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit & Save - Notification received for " +sAssignedTo , "Edit & Save - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sUserName,sActionTitle,sRecordId,node);
		
		//Assigned To 2
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received Edit & Save Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit & Save -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit & Save - Notification received for " +sAssignedTo1 , "Edit & Save - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Edit & Save -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit & Save -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit & Save - Notification not received for " +sAssignedTo1 , "Edit & Save - Notification received for " +sAssignedTo1);
		}
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sUserName1,sActionTitle,sRecordId,node);
		
		//Assigned To 3
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
		
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==2) {
			logger.info("Passed: Notification Received after Edit & Save -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit & Save -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit & Save - Notification received for " +sAssignedTo2 , "Edit & Save - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Edit & Save -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit & Save -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit & Save - Notification not received for " +sAssignedTo2 , "Edit & Save - Notification received for " +sAssignedTo2);
		}
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sUserName2,sActionTitle,sRecordId,node);
		
		//Login User 2
		objHP.clickAvatar();
		Thread.sleep(2000);
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
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(sNotCount==null) {
			sNotCount = "0";
		}
		UtilityCustomFunctions.logWriteConsole("Notification count after Edit & Save on ETRM: " + sNotCount);
		if(Integer.parseInt(iOldCount2)+1== Integer.parseInt(sNotCount)){
			UtilityCustomFunctions.logWriteConsole("Notification Received after Edit & Save on ETRM "+ sUserName1);
			freport("Notification Received after new Edit & Save on ETRM "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Notification Received after Edit & Save on ETRM"+ sUserName1, "Notification not Received after Edit & Save on ETRM"+ sUserName1);	
		
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit & Save on ETRM "+ sUserName1);
			freport("Notification not Received after Edit & Save on ETRM "+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after Edit & Save on ETRM"+ sUserName1, "Notification not Received after Edit & Save on ETRM"+ sUserName1);
		}
		//Third User
		Thread.sleep(3000);
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
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
		Thread.sleep(3000);
		sNotCount = objCRMRs.fgetNotificationCount();
		
		if(sNotCount==null) {
			sNotCount = "0";
		}
		UtilityCustomFunctions.logWriteConsole("Notification count after new Edit & Save on ETRM: " + sNotCount);
		if(Integer.parseInt(iOldCount3)+1== Integer.parseInt(sNotCount)){
			UtilityCustomFunctions.logWriteConsole("Notification not Received after new Edit & Save on ETRM"+ sUserName2);
			freport("Notification not Received after new Edit & Save on ETRM "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Notification Received after new Edit & Save on ETRM"+ sUserName2, "Notification not Received after new Edit & Save on ETRM"+ sUserName2);	
		
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification Received after new Edit & Save on ETRM "+ sUserName2);
			freport("Notification Received after new Edit & Save on ETRM "+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Notification Received after new Edit & Save on ETRM"+ sUserName2, "Notification not Received after new Edit & Save on ETRM"+ sUserName2);
		}
		
		//Login with User 1
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
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
		objCRMRs.fNotifyAddValuestoModulePage("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet4");
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValModuleView("Test", "//Notification//WF3_Send_Notify_ETR_M_","Sheet4","Edit with New Data",sAssignedTo,node);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		
		//Validate Edit with New Data
		//Notification count
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount1)+2==Integer.parseInt(sNotCount)) {
			logger.info("Notification Received on Edit with New Data "+ sUserName);
			freport("Notification Received on Edit with New Data "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit Notification received", "Edit Notification received");
		}else {
			logger.info("Failed: Notification not Received on Edit "+ sUserName);
			freport("Failed: Notification not Received on Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
		}
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id:After Edit with New Data: " + sRecordId);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		//Validate for Each User
		//Notifications Assigned To 1
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==3) {
			logger.info("Passed: Notification Received Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Received Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit with New Data - Notification received for " +sAssignedTo , "Edit with New Data - Notification received for " +sAssignedTo);
		}
		else {
		logger.info("Failed: Notification not Received Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId);
		freport("Failed: Notification not Received Edit with New Data -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
		sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo , "Edit with New Data - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sUserName,sActionTitle,sRecordId,node);
				
		//Assigned To 2
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==3) {
			logger.info("Passed: Notification Received Edit with New Data Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit with New Data -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit with New Data - Notification received for " +sAssignedTo1 , "Edit with New Data - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo1 , "Edit with New Data - Notification received for " +sAssignedTo1);
		}
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sUserName1,sActionTitle,sRecordId,node);
				
		//Assigned To 3
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
				
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==3) {
			logger.info("Passed: Notification Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Edit with New Data - Notification received for " +sAssignedTo2 , "Edit with New Data - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Edit with New Data -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Edit with New Data - Notification not received for " +sAssignedTo2 , "Edit with New Data - Notification received for " +sAssignedTo2);
		}
				
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sUserName2,sActionTitle,sRecordId,node);
		
		//*******************
		//Login User 2
		objHP.clickAvatar();
		Thread.sleep(2000);
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
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		UtilityCustomFunctions.logWriteConsole("Notification count after Edit with New Data on ETRM: " + sNotCount);
		if(Integer.parseInt(iOldCount2)+2== Integer.parseInt(sNotCount)){
			UtilityCustomFunctions.logWriteConsole("Notification Received after Edit with New Data on ETRM "+ sUserName1);
			freport("Notification Received after new Edit with New Data on ETRM "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Notification Received after Edit with New Data on ETRM"+ sUserName1, "Notification Received after Edit with New Data on ETRM"+ sUserName1);	
		
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after Edit with New Data on ETRM "+ sUserName1);
			freport("Notification not Received after Edit with New Data on ETRM "+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after Edit with New Data on ETRM"+ sUserName1, "Notification not Received after Edit with New Data on ETRM"+ sUserName1);
		}
		//Third User
		Thread.sleep(3000);
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
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
		Thread.sleep(3000);
		sNotCount = objCRMRs.fgetNotificationCount();
			
		if(sNotCount==null) {
			sNotCount = "0";
		}
		UtilityCustomFunctions.logWriteConsole("Notification count after new Edit with New Data on ETRM: " + sNotCount);
		if(Integer.parseInt(iOldCount3)+2== Integer.parseInt(sNotCount)){
			UtilityCustomFunctions.logWriteConsole("Notification Received after new Edit with New Data on ETRM"+ sUserName2);
			freport("Notification Received after new Edit with New Data on ETRM "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Notification Received after new Edit with New Data on ETRM"+ sUserName2, "Notification Received after new Edit with New Data on ETRM"+ sUserName2);	
		
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Failed: Notification not Received after new Edit with New Data on ETRM "+ sUserName2);
			freport("Notification not Received after new Edit with New Data on ETRM "+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after new Edit with New Data on ETRM"+ sUserName2, "Notification not Received after new Edit with New Data on ETRM"+ sUserName2);
		}
			
		//************ Single Line Edit
		//Login with User 1
		objHP.clickAvatar();
		Thread.sleep(2000);
		objHP.clickLogout();
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
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
		sNotCount = objCRMRs.fgetNotificationCount();
		if(Integer.parseInt(iOldCount2)+3==Integer.parseInt(sNotCount)) {
			logger.info("Passed:Notification Received after Single Line Edit "+ sUserName);
			freport("Passed: Notification Received after Single Line Edit "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Notification Received after Single Line Edit ", "Notification Received after Single Line Edit ");
		}else {
			logger.info("Failed: Notification not Received after Single Line Edit"+ sUserName);
			freport("Failed: Notification not Received after Single Line Edit"+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after Single Line Edit", "Notification Received after Single Line Edit");sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification received");
		}
		
		//Notifications 1
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sNotifyTemplateMsg,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 11, sRecordId);
		logger.info("Captured RecordId");
		UtilityCustomFunctions.logWriteConsole("Record Id:Single Line Edit: " + sRecordId);
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		sStatus = "0";
		//Validate for Each User
		//Notifications Assigned To 1
		objCRMRs.fClickSearch(sRecordId,sAssignedTo);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==4) {
			logger.info("Passed: Notification Received Single Line Edit -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Passed: Notification Not Received Single Line Edit -"+ sAssignedTo + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification received for " +sAssignedTo , "Single Line Edit - Notification received for " +sAssignedTo);
		}
		else {
			logger.info("Failed: Notification not Received Single Line Edit -"+ sAssignedTo + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received Single Line Edit -"+ sAssignedTo + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification not received for " +sAssignedTo , "Single Line Edit - Notification received for " +sAssignedTo);
		}
		objCRMRs.fgetTablevalues(sAssignedTo,sStatus,sAssignedTo,sUserName,sActionTitle,sRecordId,node);
						
		//Assigned To 2
		objCRMRs.fClickSearch(sRecordId,sAssignedTo1);
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==4) {
			logger.info("Passed: Notification Received Single Line Edit Add -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Passed: Notification not Received after Single Line Edit -"+ sAssignedTo1 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification received for " +sAssignedTo1 , "Single Line Edit - Notification received for " +sAssignedTo1);
		}
		else {
			logger.info("Failed: Notification not Received after Single Line Edit -"+ sAssignedTo1 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Single Line Edit -"+ sAssignedTo1 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification not received for " +sAssignedTo1 , "Single Line Edit - Notification received for " +sAssignedTo1);
		}
		objCRMRs.fgetTablevalues(sAssignedTo1,sStatus,sAssignedTo,sUserName1,sActionTitle,sRecordId,node);
						
		//Assigned To 3
		objCRMRs.fClickSearch(sRecordId,sAssignedTo2);
						
		iNotificationCount = objSMS.getSMSRowcount(); 
		if(iNotificationCount==4) {
			logger.info("Passed: Notification Received after Single Line Edit -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Passed: Notification Received after Single Line Edit -"+ sAssignedTo2 + " Record Id: " + sRecordId, "pass",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification received for " +sAssignedTo2 , "Single Line Edit - Notification received for " +sAssignedTo2);
		}
		else {
			logger.info("Failed: Notification not Received after Single Line Edit -"+ sAssignedTo2 + " Record Id: " + sRecordId);
			freport("Failed: Notification not Received after Single Line Edit -"+ sAssignedTo2 + " Record Id: " + sRecordId, "fail",node);
			sAssertinFn.assertEquals("Single Line Edit - Notification not received for " +sAssignedTo2 , "Single Line Edit - Notification received for " +sAssignedTo2);
		}
		objCRMRs.fgetTablevalues(sAssignedTo2,sStatus,sAssignedTo,sUserName2,sActionTitle,sRecordId,node);
				
		//Logout & Login as User 2
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
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
		if(Integer.parseInt(iOldCount2)+3==Integer.parseInt(sNotCount)) {
			logger.info("Passed:Notification Received after Single Line Edit "+ sUserName1);
			freport("Passed: Notification Received after Single Line Edit "+ sUserName1, "pass",node);
			sAssertinFn.assertEquals("Notification Received after Single Line Edit ", "Notification Received after Single Line Edit ");
		}else {
			logger.info("Failed: Notification not Received after Single Line Edit"+ sUserName1);
			freport("Failed: Notification not Received after Single Line Edit"+ sUserName1, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after Single Line Edit ", "Notification Received after Single Line Edit ");sAssertinFn.assertEquals("Single Edit Notification not received", "Single Edit Notification received");
		}
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
		
		//Login as User 3
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
		if(Integer.parseInt(iOldCount3)+3==Integer.parseInt(sNotCount)) {
			logger.info("Passed:Notification Received after Single Line Edit "+ sUserName2);
			freport("Passed: Notification Received after Single Line Edit "+ sUserName2, "pass",node);
			sAssertinFn.assertEquals("Notification Received after Single Line Edit ", "Notification Received after Single Line Edit ");
		}else {
			logger.info("Failed: Notification not Received after Single Line Edit"+ sUserName2);
			freport("Failed: Notification not Received after Single Line Edit"+ sUserName2, "fail",node);
			sAssertinFn.assertEquals("Notification not Received after Single Line Edit ", "Notification Received after Single Line Edit ");
		}
		
		//Logout
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(3000);
	}//test method
}//class