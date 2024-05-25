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

public class TC005_WF5_Send_SMS_ETR_S extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC005_WF5_Send_SMS_ETR_S");
		System.out.println("Bfore test");
	}
	@Test
	public void test_SMS_ETR_S() throws Exception {
		node = test.createNode("Send_SMS_ETR_S");
		System.out.println("In test");
		logger.info("******starting Send_SMS_ETR_S ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
//		String sPath=".\\testData\\SMS\\WF5_Send_SMS_ETR_S" + "_Live.xlsx" ;
		String sPath=".\\testData\\SMS\\WF5_Send_SMS_ETR_S" + "_Test.xlsx" ;
	
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
		
		 logger.info("Extracting DataSheet Values started...");
		
		 	String sPhoneNumber1 = randomeNumber();
			String sPhoneNumber2 = randomeNumber();
			String sPhoneNumber3 = randomeNumber();
			String sPhoneNumber4 = randomeNumber();
			
			xlObj.setCellData("Sheet1", 1, 5, sPhoneNumber1);
			xlObj.setCellData("Sheet1", 1, 30, sPhoneNumber2);
			xlObj.setCellData("Sheet1", 1, 45, sPhoneNumber3);
			xlObj.setCellData("Sheet1", 1, 49, sPhoneNumber4);
			
			xlObj.setCellData("Sheet2", 1, 5, sPhoneNumber1);
			xlObj.setCellData("Sheet2", 1, 30, sPhoneNumber2);
			xlObj.setCellData("Sheet2", 1, 45, sPhoneNumber3);
			xlObj.setCellData("Sheet2", 1, 49, sPhoneNumber4);
			
			xlObj.setCellData("Sheet3", 1, 5, sPhoneNumber1);
			xlObj.setCellData("Sheet3", 1, 30, sPhoneNumber2);
			xlObj.setCellData("Sheet3", 1, 45, sPhoneNumber3);
			xlObj.setCellData("Sheet3", 1, 49, sPhoneNumber4);
			
			xlObj.setCellData("Sheet4", 1, 5, sPhoneNumber1);
			xlObj.setCellData("Sheet4", 1, 30, sPhoneNumber2);
			xlObj.setCellData("Sheet4", 1, 45, sPhoneNumber3);
			xlObj.setCellData("Sheet4", 1, 49, sPhoneNumber4);
		 
		Thread.sleep(1000);
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
		String sExpAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
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
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 35);
		String sUser1MessageId=xlObj.getCellData("Sheet1", 1, 36);
		String sUser2MessageId=xlObj.getCellData("Sheet1", 1, 37);
		String sUser3MessageId=xlObj.getCellData("Sheet1", 1, 38);
		String sUser1RecordId=xlObj.getCellData("Sheet1", 1, 39);
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
		String sSMSTemplateMsg = xlObj.getCellData("Sheet1", 1, 51);

		 
		 
		 sMobileNumber=xlObj.getCellData("Sheet1", 1, 5);
		 sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 30);
		 sLead_PN=xlObj.getCellData("Sheet1", 1, 45);
		 sSales_PN=xlObj.getCellData("Sheet1", 1, 49);
		 
		 UtilityCustomFunctions.logWriteConsole("PN1:" + sMobileNumber);
		 UtilityCustomFunctions.logWriteConsole("PN2:" + sEnquiry_PhoneNumber);
		 UtilityCustomFunctions.logWriteConsole("PN3:" + sLead_PN);
		 UtilityCustomFunctions.logWriteConsole("PN4:" + sSales_PN);
		
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
		SMSNotifiers objSMS = new SMSNotifiers(driver);
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName1");
		String sPassword =  rb.getString("passWord1");
		String sAssignedTo = rb.getString("AssignedTo1");
		
		int iSMSCount=0;
		
		String sFullMobileNumber = sMobNumPrefix + " " + sMobileNumber;  
		String sFullEnquiry_PhoneNumber = sEnquiry_PN_Prefix + sEnquiry_PhoneNumber; 
		String sFullLead_PN = sLead_PN_Prefix+ " " + sLead_PN; 
		
		Thread.sleep(1000);
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
//			objHP.clickAvatar();
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
		}//avatar displayed.
		
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
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayModuleName);
		System.out.println("Add New Module data clicked");
		Thread.sleep(2000);
		
		
//		//**************Add New Record
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_","Sheet1",false);

		objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Sheet1","@Add New Record","No",node);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add -"+ sMobileNumber);
			freport("Failed: SMS Not Received after New Record Add -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after New Record Add -"+ sMobileNumber);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		else {
			logger.info("Passed: SMS Received after New Record Add -"+ sMobileNumber);
			freport("Passed: SMS Received after New Record Add -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after New Record Add -"+ sMobileNumber);
			sAssertinFn.assertEquals("Add New Record - SMS received", "Add New Record - SMS received");
		}
		Thread.sleep(3000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after New Record Add -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after New Record Add -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		else {
			logger.info("Passed: SMS Received after New Record Add -"+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after New Record Add -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after New Record Add -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Add New Record - SMS received", "Add New Record - SMS received");
		}
//		objSMS.clickFirstSMS();
//		sUser2MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullEnquiry_PhoneNumber, sSMSTemplateMsg,"SMS Validation for " + sEnquiry_PhoneNumber , node);
//		UtilityCustomFunctions.logWriteConsole("RecorId after add new record :" + sMobileNumber + "RecordId: "+ sUser2MessageId);
		Thread.sleep(3000);
		objSMS.setRecipient(sLead_PN);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add -"+ sLead_PN);
			freport("Failed: SMS Not Received after New Record Add -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after New Record Add -"+ sLead_PN);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		else {
			logger.info("Passed: SMS Received after New Record Add -"+ sLead_PN);
			freport("Passed: SMS Received after New Record Add -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after New Record Add -"+ sLead_PN);
			sAssertinFn.assertEquals("Add New Record - SMS received", "Add New Record - SMS received");
		}
		
		//Summary Add New Record
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		//Add New Record and Check
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_","Sheet2",false);
		Thread.sleep(3000);
		objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Sheet2","Summary Add Data Validation","No",node);
		//SMS Validation after Summary Add New
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		objSMS.setRecipient(sMobileNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==3) {
			logger.info("Passed: SMS Received after Summary New Record Add -"+ sMobileNumber);
			freport("Passed: SMS Received after  Summary New Record Add -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after  Summary New Record Add -"+ sMobileNumber);
			sAssertinFn.assertEquals("Summary New Record - SMS received", "Summary New Record - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary New Record Add -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Summary New Record Add  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary New Record Add -"+ sMobileNumber);
			sAssertinFn.assertEquals("Summary New Record - SMS Not received", "Summary New Record - SMS Not received");
		}
		Thread.sleep(3000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==3) {
			logger.info("Passed: SMS Received after Summary New Record Add -"+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after  Summary New Record Add -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after  Summary New Record Add -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Summary New Record - SMS received", "Summary New Record - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary New Record Add -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Summary New Record Add  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary New Record Add -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Summary New Record - SMS Not received", "Summary New Record - SMS Not received");
		}
		
		Thread.sleep(3000);
		objSMS.setRecipient(sLead_PN);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==3) {
			logger.info("Passed: SMS Received after Summary New Record Add -"+ sLead_PN);
			freport("Passed: SMS Received after  Summary New Record Add -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after  Summary New Record Add -"+ sLead_PN);
			sAssertinFn.assertEquals("Summary New Record - SMS received", "Summary New Record - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary New Record Add -"+ sLead_PN);
			freport("Failed: SMS Not Received after Summary New Record Add  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary New Record Add -"+ sLead_PN);
			sAssertinFn.assertEquals("Summary New Record - SMS Not received", "Summary New Record - SMS Not received");
		}
		
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
		//SMS Validation after Duplicate
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==4) {
			logger.info("Passed: SMS Received after Duplicate No Modify -"+ sMobileNumber);
			freport("Passed: SMS Received after Duplicate No Modify -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate No Modify -"+ sMobileNumber);
			sAssertinFn.assertEquals("after Duplicate No Modify - SMS received", "after Duplicate No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate No Modify -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Duplicate No Modify  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate No Modify -"+ sMobileNumber);
			sAssertinFn.assertEquals("Duplicate No Modify - SMS Not received", "Duplicate No Modify - SMS  received");
		}
		Thread.sleep(3000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==4) {
			logger.info("Passed: SMS Received after Duplicate No Modify -"+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after Duplicate No Modify -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate No Modify -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("after Duplicate No Modify - SMS received", "after Duplicate No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate No Modify -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Duplicate No Modify  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate No Modify -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Duplicate No Modify - SMS Not received", "Duplicate No Modify - SMS  received");
		}
		Thread.sleep(2000);
		objSMS.setRecipient(sLead_PN);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==4) {
			logger.info("Passed: SMS Received after Duplicate No Modify -"+ sLead_PN);
			freport("Passed: SMS Received after Duplicate No Modify -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate No Modify -"+ sLead_PN);
			sAssertinFn.assertEquals("after Duplicate No Modify - SMS received", "after Duplicate No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate No Modify -"+ sLead_PN);
			freport("Failed: SMS Not Received after Duplicate No Modify  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate No Modify -"+ sLead_PN);
			sAssertinFn.assertEquals("Duplicate No Modify - SMS Not received", "Duplicate No Modify - SMS  received");
		}
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
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_","Sheet3",true);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Sheet3","Duplicate with New Data","No",node);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(3000);
		objSMS.setRecipient(sMobileNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==5) {
			logger.info("Passed: SMS Received after Duplicate with New Data-"+ sMobileNumber);
			freport("Passed: SMS Received after Duplicate with New Data -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate with New Data -"+ sMobileNumber);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS received", "Duplicate with New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate with New Data -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Duplicate with New Data  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate with New Data -"+ sMobileNumber);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS Not received", "Duplicate with New Data - SMS received");
		}
		Thread.sleep(2000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==5) {
			logger.info("Passed: SMS Received after Duplicate with New Data-"+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after Duplicate with New Data -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate with New Data -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS received", "Duplicate with New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate with New Data -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Duplicate with New Data  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate with New Data -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS Not received", "Duplicate with New Data - SMS received");
		}
		Thread.sleep(2000);
		objSMS.setRecipient(sLead_PN);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==5) {
			logger.info("Passed: SMS Received after Duplicate with New Data-"+ sLead_PN);
			freport("Passed: SMS Received after Duplicate with New Data -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Duplicate with New Data -"+ sLead_PN);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS received", "Duplicate with New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Duplicate with New Data -"+ sLead_PN);
			freport("Failed: SMS Not Received after Duplicate with New Data  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Duplicate with New Data -"+ sLead_PN);
			sAssertinFn.assertEquals("Duplicate with New Data - SMS Not received", "Duplicate with New Data - SMS received");
		}
		
		//Edit in Summary with No Modification
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(3000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(6000);
		System.out.println("Before selecting 1st Record");
		objCMD.clickExistingModData(1);
		Thread.sleep(6000);
//		objCMD.clickEllipsis(1);
		Thread.sleep(1000);
		System.out.println("Before Edit button clicked in summary view");
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==6) {
			logger.info("Passed: SMS Received after Edit&Save No Modify - "+ sMobileNumber);
			freport("Passed: SMS Received after Edit&Save No Modify -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save No Modify -"+ sMobileNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save No Modify -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Edit&Save No Modify  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save No Modify -"+ sMobileNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS Not received", "Edit&Save No Modify - SMS received");
		}
		Thread.sleep(2000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==6) {
			logger.info("Passed: SMS Received after Edit&Save No Modify - "+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after Edit&Save No Modify -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save No Modify -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save No Modify -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Edit&Save No Modify  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save No Modify -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS Not received", "Edit&Save No Modify - SMS received");
		}
		Thread.sleep(1000);
		objSMS.setRecipient(sLead_PN);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==6) {
			logger.info("Passed: SMS Received after Edit&Save No Modify - "+ sLead_PN);
			freport("Passed: SMS Received after Edit&Save No Modify -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save No Modify -"+ sLead_PN);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save No Modify - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save No Modify -"+ sLead_PN);
			freport("Failed: SMS Not Received after Edit&Save No Modify  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save No Modify -"+ sLead_PN);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS Not received", "Edit&Save No Modify - SMS received");
		}
		//Click Edit and Update with New Data
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
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_","Sheet4",true);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Sheet4","Edit with New Data","No",node);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount(); 
		if(iSMSCount==7) {
			logger.info("Passed: SMS Received after Edit&Save New Data - "+ sMobileNumber);
			freport("Passed: SMS Received after Edit&Save New Data -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save New Data -"+ sMobileNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save New Data -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Edit&Save New Data  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save New Data -"+ sMobileNumber);
			sAssertinFn.assertEquals("Edit&Save New Data - SMS Not received", "Edit&Save New Data - SMS received");
		}
		Thread.sleep(3000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==7) {
			logger.info("Passed: SMS Received after Edit&Save New Data - "+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after Edit&Save New Data -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save New Data -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save New Data -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Edit&Save New Data  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save New Data -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Edit&Save New Data - SMS Not received", "Edit&Save New Data - SMS received");
		}
		Thread.sleep(1000);
		objSMS.setRecipient(sLead_PN);
		Thread.sleep(1000);
		objSMS.clickSearch();
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==7) {
			logger.info("Passed: SMS Received after Edit&Save New Data - "+ sLead_PN);
			freport("Passed: SMS Received after Edit&Save New Data -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Edit&Save New Data -"+ sLead_PN);
			sAssertinFn.assertEquals("Edit&Save No Modify - SMS received", "Edit&Save New Data - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Edit&Save New Data -"+ sLead_PN);
			freport("Failed: SMS Not Received after Edit&Save New Data  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Edit&Save New Data -"+ sLead_PN);
			sAssertinFn.assertEquals("Edit&Save New Data - SMS Not received", "Edit&Save New Data - SMS received");
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
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
		objDVP.clickEditNotificationItem();
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpModuleName, "text", sEditIndText);
		objDVP.clickRecItemSave(sExpModuleName);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers");
		Thread.sleep(2000);
		objSMS.setRecipient(sMobileNumber);
		Thread.sleep(1000);
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==8) {
			logger.info("Passed: SMS Received after Summary Single Edit - "+ sMobileNumber);
			freport("Passed: SMS Received after Summary Single Edit -"+ sMobileNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Summary Single Edit -"+ sMobileNumber);
			sAssertinFn.assertEquals("Summary Single Edit - SMS received", "Summary Single Edit - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary Single Edit -"+ sMobileNumber);
			freport("Failed: SMS Not Received after Summary Single Edit  -"+ sMobileNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary Single Edit -"+ sMobileNumber);
			sAssertinFn.assertEquals("Summary Single Edit - SMS Not received", "Summary Single Edit - SMS received");
		}
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==8) {
			logger.info("Passed: SMS Received after Summary Single Edit - "+ sEnquiry_PhoneNumber);
			freport("Passed: SMS Received after Summary Single Edit -"+ sEnquiry_PhoneNumber, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Summary Single Edit -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Summary Single Edit - SMS received", "Summary Single Edit - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary Single Edit -"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Summary Single Edit  -"+ sEnquiry_PhoneNumber, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary Single Edit -"+ sEnquiry_PhoneNumber);
			sAssertinFn.assertEquals("Summary Single Edit - SMS Not received", "Summary Single Edit - SMS received");
		}
		Thread.sleep(1000);
		objSMS.setRecipient(sLead_PN);
		objSMS.clickSearch();
		Thread.sleep(1000);
		iSMSCount = objSMS.getWebTblRowcount();
		if(iSMSCount==8) {
			logger.info("Passed: SMS Received after Summary Single Edit - "+ sLead_PN);
			freport("Passed: SMS Received after Summary Single Edit -"+ sLead_PN, "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: SMS Received after Summary Single Edit -"+ sLead_PN);
			sAssertinFn.assertEquals("Summary Single Edit - SMS received", "Summary Single Edit - SMS received");
		}
		else {
			logger.info("Failed: SMS Not Received after Summary Single Edit -"+ sLead_PN);
			freport("Failed: SMS Not Received after Summary Single Edit  -"+ sLead_PN, "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: SMS Not Received after Summary Single Edit -"+ sLead_PN);
			sAssertinFn.assertEquals("Summary Single Edit - SMS Not received", "Summary Single Edit - SMS received");
		}
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
								
								
	}//test
	}//class