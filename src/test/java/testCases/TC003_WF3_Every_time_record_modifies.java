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
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

	public class TC003_WF3_Every_time_record_modifies extends BaseClass {
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC003_WF3_Every_time_record_modifies");
	}
	
	@Test
	public void testNotifyEvery_T_R_Modifies () throws Exception {
		node = test.createNode("WF3_Every_time_record_modifies");
		
		logger.info("******starting WF3_Every_time_record_modifies ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\Notification\\" + "WF3_Every_time_record_modifies_Live" + ".xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF3_Every_time_record_modifies_Test" + ".xlsx" ;
	
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
//		String sExpAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
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
		String sEditIndText=xlObj.getCellData("Sheet1", 1, 43);
		String sLead_PN_Prefix=xlObj.getCellData("Sheet1", 1, 44);
		String sLead_PN=xlObj.getCellData("Sheet1", 1, 45);
		String sLead_Email=xlObj.getCellData("Sheet1", 1, 46);
		String sLead_Text=xlObj.getCellData("Sheet1", 1, 47);
		String sSales_PN_Prefix=xlObj.getCellData("Sheet1", 1, 48);
		String sSales_PN=xlObj.getCellData("Sheet1", 1, 49);
		String sSales_Email=xlObj.getCellData("Sheet1", 1, 50);
		
		sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		Thread.sleep(3000);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		NotificationsPage objNFP = new NotificationsPage(driver);
		DetailViewPage objDVP = new DetailViewPage(driver);
		
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName1");
		String sPassword =  rb.getString("passWord1");
		String sAssignedTo = rb.getString("AssignedTo1");
		String sUserName1 =  rb.getString("userName3");
		String sPassword1 =  rb.getString("passWord3");
		String sAssignedTo1 = rb.getString("AssignedTo3");
		String sUserName2 =  rb.getString("userName4");
		String sPassword2 =  rb.getString("passWord4");
		String sAssignedTo2 = rb.getString("AssignedTo4");
		
		
		
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
//		}//If workflow Enabled but Task not set
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayModuleName);
		Thread.sleep(2000);
		String sPrevCount = objCRMRs.fgetNotificationCount();
		if(sPrevCount==null) {
			sPrevCount = "0";
		}
		System.out.println("Prevcount before add:" + sPrevCount);
		logger.info("Prevcount before add:" + sPrevCount);
		freport("Previous Notification count before add:" + sPrevCount, "pass",node);
		//**************Add New Record
		objCRMRs.fAddValuestoModulePage("Test","//Notification//WF3_Every_time_record_modifies_");
		String sNotCount=""; 
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		System.out.println("NotifyCount after add:" + sNotCount);
		logger.info("Notify Count after add:" + sNotCount);
		freport("Notification count After Module record added:" + sNotCount, "pass",node);
		//Validate Notification for Current User.
		UtilityCustomFunctions.fSoftAssert(sPrevCount, sNotCount, "Notificaton count after new record added for EveryTimeRecordModifies Condition" , node);
		
		//**************Validate Added Module Data
		objCRMRs.fValidateAllFields("Test", "WF3_Every_time_record_modifies_","Module Data Validation","No",node);
		
		int iOldCount = Integer.parseInt(sNotCount);
		
		//Notification After Edit
		objDVP.clickEditRecord();
		logger.info("Full Edit Clicked");
		Thread.sleep(5000);
		objCMD.clickSave();
		logger.info("Save Clicked after Full Edit");
		Thread.sleep(5000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		
		if(Integer.parseInt(sNotCount)==iOldCount + 1) {
			logger.info("Notification Received on Edit "+ sUserName);
			freport("Notification Received on Edit "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Edit Notification received", "Edit Notification received");
		}else {
			logger.info("Failed: Notification Not Received on Edit "+ sUserName);
			freport("Failed: Notification Not Received on Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
			Assert.fail("Failed: Notification Not Received on Edit "+ sUserName);
		}
		iOldCount = Integer.parseInt(sNotCount);
		//Duplicate Record Validation
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(Integer.parseInt(sNotCount)==iOldCount) {
			logger.info("Passed: Notification Not Received on Duplicate"+ sUserName);
			freport("Passed: Notification Not Received on Duplicate"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Duplicate - Notification Not received", "Duplicate - Notification Not received");
		}else {
			logger.info("Failed: Notification Received when Duplicate"+ sUserName);
			freport("Failed: Notification Received when Duplicate"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Duplicate - Notification received", "Duplicate - Notification not received");
			Assert.fail("Failed: Notification Received when Duplicate");
		}
		//After Add New Record
		iOldCount = Integer.parseInt(sNotCount);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		//Add New Record and Check
		objCRMRs.fAddValuestoModulePage("Test","WF3_Every_time_record_modifies_");
		Thread.sleep(3000);
		sNotCount = objCRMRs.fgetNotificationCount();
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(Integer.parseInt(sNotCount)==iOldCount) {
			logger.info("Passed: Notification Not Received on Add New"+ sUserName);
			freport("Passed: Notification Not Received on Add New"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Add New- Notification Not received", "Add New- Notification Not received");
		}else {
			logger.info("Failed: Notification Received when Add New Record"+ sUserName);
			freport("Failed: Notification Received when Add New Record"+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Add New Record - Notification received", "Add New Record - Notification not received");
			Assert.fail("Failed: Notification Received when New Record Add");
		}
		Thread.sleep(3000);
		//Edit Record Item
		sNotCount = objCRMRs.fgetNotificationCount();
		System.out.println("Notify count before single edit:" + sNotCount);
		if(sNotCount==null) {
			sNotCount = "0";
		}
		iOldCount = Integer.parseInt(objCRMRs.fgetNotificationCount());
		Thread.sleep(1000);
		objDVP.clickEditRecordItem();
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpModuleName, "text", sEditIndText);
		objDVP.clickRecItemSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		sNotCount = objCRMRs.fgetNotificationCount();
		System.out.println(iOldCount+ " Notify count after single edit:" + sNotCount);
		if(sNotCount==null) {
			sNotCount = "0";
		}
		if(Integer.parseInt(sNotCount)==iOldCount + 1) {
			logger.info("Notification Received on Single Edit "+ sUserName);
			freport("Notification Received on Single Edit "+ sUserName, "pass",node);
			sAssertinFn.assertEquals("Single Edit Notification received", "Single Edit Notification not received");
		}else {
			System.out.println("Old count:" + iOldCount + "And New count after single edit:" +sNotCount);
			logger.info("Failed: Notification Not Received or count mismatch on Single Edit "+ sUserName);
			freport("Failed: Notification Not Received or count mismatch  on Single Edit "+ sUserName, "fail",node);
			sAssertinFn.assertEquals("Edit Notification not received", "Edit Notification received");
			Assert.fail("Failed: Notification Not Received or count mismatch on Single Edit "+ sUserName);
		}
		
		//Validate All Fields
		
//		objCRMRs.fValidateAllFields("Test", "WF3_Every_time_record_modifies_","After New Record added","No",node);
		
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
				UtilityCustomFunctions.fSoftAssert(sUser2RecordId, sRecordId,"Second User Notification Received Record Id:" + sUser2RecordId , node);
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
//				objNFP.clickNotifyFirstMsg();
//				objCRMRs.fValidateAllFields("Test", "Add","Add-3rd User","Yes",node);
				//Validate Notification in New Login
				sNewWindowHanlde="";
				sCurrentWinHandle = driver.getWindowHandle();
				sUser3RecordId="";
				System.out.println("Third User Current Window: " + sCurrentWinHandle);
				sNewWindowHanlde = objCRMRs.fOpenNotification(sCurrentWinHandle);
				System.out.println("Third User New Window : " + sNewWindowHanlde );
				sUser3RecordId = objCRMRs.fValNotifySummaryAndDetail(sAssignedTo2,sText,sActionTitle,node);
				UtilityCustomFunctions.fSoftAssert(sUser3RecordId, sRecordId,"Third User Notification Received Record Id:" + sUser3RecordId , node);
				System.out.println("Captured Record Id:" + sUser3RecordId);
				xlObj.setCellData("Sheet1", 1, 41, sUser3RecordId);
				logger.info("Captured RecordId for User 3" + sUser3RecordId);
				
				driver.close();
				driver.switchTo().window(sCurrentWinHandle);
				objHP.clickAvatar();
				objHP.clickLogout();
				Thread.sleep(2000);
		
		
		
	}//test method
}//class