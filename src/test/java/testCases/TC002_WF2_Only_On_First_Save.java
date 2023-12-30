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
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC002_WF2_Only_On_First_Save extends BaseClass{

	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC002_WF2_Only_On_First_Save");
	}
	@Test
	public void testWFOnlyOnFirstSave () throws Exception {
		node = test.createNode("WF2_Only_On_First_Save");
		
		logger.info("******starting WF2_Only_On_First_Save ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Live" + ".xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test" + ".xlsx" ;
		
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
		String sText=xlObj.getCellData("Sheet1", 1, 3);
		String sMobNumPrefix=xlObj.getCellData("Sheet1", 1, 4);
		String sMobileNumber=xlObj.getCellData("Sheet1", 1, 5);
		String sEmail=xlObj.getCellData("Sheet1", 1, 6);
		String sPickListValue=xlObj.getCellData("Sheet1", 1, 7);
		String sMultiComboValues=xlObj.getCellData("Sheet1", 1, 8);
		String sCity=xlObj.getCellData("Sheet1", 1, 9);
		String sState=xlObj.getCellData("Sheet1", 1, 10);
		String sCountry=xlObj.getCellData("Sheet1", 1, 11);
		String sCheckBox=xlObj.getCellData("Sheet1", 1, 12);
		String sDate =xlObj.getCellData("Sheet1", 1, 13);
		String sTime =xlObj.getCellData("Sheet1", 1, 14);
		String sDateandTime =xlObj.getCellData("Sheet1", 1, 15);
		String sRelatedModule =xlObj.getCellData("Sheet1", 1, 16);
		String sFilePath =xlObj.getCellData("Sheet1", 1, 17);
		String sNamePrefix =xlObj.getCellData("Sheet1", 1, 18);
		String sName=xlObj.getCellData("Sheet1", 1, 19);
		String sNumber=xlObj.getCellData("Sheet1", 1, 20);
		String sCurrency=xlObj.getCellData("Sheet1", 1, 21);
		String sUrl=xlObj.getCellData("Sheet1", 1, 22);
		String sEnq_Name_Prefix=xlObj.getCellData("Sheet1", 1, 23);
		String sEnquiry_Name=xlObj.getCellData("Sheet1", 1, 24);
		String sEnquiry_Email=xlObj.getCellData("Sheet1", 1, 25);
		String sEnquiry_Text=xlObj.getCellData("Sheet1", 1, 26);
		String sEnquiry_TextArea=xlObj.getCellData("Sheet1", 1, 27);
		String sEnquiry_Date=xlObj.getCellData("Sheet1", 1, 28);
		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 29);
		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 30);
		String sEnquiry_Category=xlObj.getCellData("Sheet1", 1, 31);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 32);
		String sActionType=xlObj.getCellData("Sheet1", 1, 33);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 34);
		String sRecordId=xlObj.getCellData("Sheet1", 1, 35);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 36);
		String sUser1NotifyCount=xlObj.getCellData("Sheet1", 1, 37); 
		String sUser2NotifyCount=xlObj.getCellData("Sheet1", 1, 38);
		String sUser3NotifyCount=xlObj.getCellData("Sheet1", 1, 39);
		String sUser2RecordId=xlObj.getCellData("Sheet1", 1, 40);
		String sUser3RecordId=xlObj.getCellData("Sheet1", 1, 41);
		String sDisplayModuleName=xlObj.getCellData("Sheet1", 1, 42);
		
		sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		NotificationsPage objNFP = new NotificationsPage(driver);
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
		Thread.sleep(3000);
		
		if(objHP.isAvatarDisplayed()) {
			freport("Home Page Displayed after Login" , "pass",node);
////			objHP.clickAvatar();
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
			
		}//If workflow Enabled but Task not set
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayModuleName);
		Thread.sleep(2000);
		String sPrevCount = objCRMRs.fgetNotificationCount();
		System.out.println("Notifications BEFORE add:" + sPrevCount);
		logger.info("Notifications BEFORE add:" + sPrevCount);
		//Add new Values to the Module
		objCRMRs.fAddValuestoModulePage("Test","//Notification//WF2_Only_On_the_first_Save_");
		
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
		//Click Edit
		objDVP.clickEditRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		Thread.sleep(5000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(iOldCount==Integer.parseInt(sNotCount)) {
			logger.info("Notification Not Received on Edit "+ sUserName);
			freport("Notification Not Received on Edit "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification not received");
		}else {
			logger.info("Failed: Notification Received on Edit "+ sUserName);
			freport("Failed: Notification Received on Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
			Assert.fail("Failed: Notification Received on Edit "+ sUserName);
		}
		iOldCount = Integer.parseInt(sNotCount);
		//Duplicate Record Validation
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		Thread.sleep(5000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(Integer.parseInt(sNotCount)==iOldCount+1) {
			logger.info("Passed: Notification Received on Duplicate"+ sUserName);
			freport("Passed: Notification Received on Duplicate"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Duplicate - Notification received", "Duplicate - Notification received");
		}else {
			logger.info("Failed: Notification not Received when Duplicate"+ sUserName);
			freport("Failed: Notification not Received when Duplicate"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Duplicate - Notification not received", "Duplicate - Notification not received");
			Assert.fail("Failed: Notification not Received when Duplicate");
		}
		iOldCount = Integer.parseInt(sNotCount);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		objCRMRs.fAddValuestoModulePage("Test","WF2_Only_On_the_first_Save_");
		Thread.sleep(3000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(Integer.parseInt(sNotCount)==iOldCount+1) {
			logger.info("Passed: Notification Received on Add New Record"+ sUserName);
			freport("Passed: Notification Received on Add New Record"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received", "Add New Record - Notification received");
		}else {
			logger.info("Failed: Notification not Received when Add New Record"+ sUserName);
			freport("Failed: Notification not Received when Add New Record"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification not received", "Duplicate - Notification received");
			Assert.fail("Add New Record-Notification not received");
		}
		
		
		objCRMRs.fValidateAllFields("Test", "WF2_Only_On_the_first_Save_","After New Record added","No",node);
//		//Validate Notification in New Login
		String sCurrentWinHandle="";
		String sNewWindowHanlde="";
		
		sCurrentWinHandle = driver.getWindowHandle();
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sRecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo,sText,sActionTitle,node);
		System.out.println("Captured Record Id:" + sRecordId);
		xlObj.setCellData("Sheet1", 1, 35, sRecordId);
		logger.info("Captured RecordId for User 1" + sRecordId);
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(2000);
		
		//Validate Notification for 2nd User.
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
			logger.info("CRM Login Success with:" + sUserName1);
			System.out.println("CRM Login Success with:" + sUserName1);
			UtilityCustomFunctions.fSoftAssert("Login Success", "Login Success","User: " + sUserName1 , node);
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			UtilityCustomFunctions.fSoftAssert("Login Fail", "Login Success","User: " + sUserName1 , node);
			Assert.fail("Login Page not displayed");
			
		}
			Thread.sleep(3000);
		
		//Validate Notification for Seoncd User.
		sNotCount = objCRMRs.fgetNotificationCount();
		xlObj.setCellData("Sheet1", 1, 38, sNotCount);
			
		if(sNotCount==null) {
			logger.info("Notification Not Received for user: "+ sUserName1);
			freport("Notification Not Received for user: "+ sUserName1, "fail",node);
			Assert.fail("Notification Not Received for user: "+ sUserName1);
		}
		logger.info("Before 2nd User Validate all test");
		System.out.println("Before 2nd User Validate all test");

		sCurrentWinHandle = driver.getWindowHandle();
		System.out.println(sCurrentWinHandle);
		sNewWindowHanlde="";
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		sUser2RecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo1,sText,sActionTitle,node);
		UtilityCustomFunctions.fSoftAssert(sUser2RecordId, sRecordId,"Second User Notification Received" , node);
		System.out.println("Captured Record Id:" + sUser2RecordId);
		xlObj.setCellData("Sheet1", 1, 40, sUser2RecordId);
		logger.info("Captured RecordId for User 2" + sUser2RecordId);
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(2000);
		
		//Validate Notification for 3rd User.
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
			UtilityCustomFunctions.fSoftAssert("Login Success", "Login Success","User: " + sUserName2 , node);
		}
		else {
			logger.info("CRM Login failed");
			UtilityCustomFunctions.fSoftAssert("Login Fail", "Login Success","User: " + sUserName2 , node);
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
			Thread.sleep(3000);
		
		//Validate Notification for Seoncd User.
		sNotCount = objCRMRs.fgetNotificationCount();
		xlObj.setCellData("Sheet1", 1, 39, sNotCount);
			
		if(sNotCount==null) {
			logger.info("Notification Not Received for user: "+ sUserName2);
			freport("Notification Not Received for user: "+ sUserName2, "fail",node);
			Assert.fail("Notification Not Received for user: "+ sUserName2);
		}
//		objNFP.clickNotifyFirstMsg();
//		objCRMRs.fValidateAllFields("Test", "Add","Add-3rd User","Yes",node);
		//Validate Notification in New Login
		sNewWindowHanlde="";
		sCurrentWinHandle = driver.getWindowHandle();
		sUser3RecordId="";
		System.out.println("Third User Current Window: " + sCurrentWinHandle);
		sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
		System.out.println("Third User New Window : " + sNewWindowHanlde );
		sUser3RecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo2,sText,sActionTitle,node);
		UtilityCustomFunctions.fSoftAssert(sUser3RecordId, sRecordId,"Third User Notification Received" , node);
		System.out.println("Captured Record Id:" + sUser3RecordId);
		xlObj.setCellData("Sheet1", 1, 41, sUser3RecordId);
		logger.info("Captured RecordId for User 3" + sUser3RecordId);
		
		driver.close();
		driver.switchTo().window(sCurrentWinHandle);
		objHP.clickAvatar();
		objHP.clickLogout();
		Thread.sleep(2000);
		
		
		
	}//@Test
}//class	
