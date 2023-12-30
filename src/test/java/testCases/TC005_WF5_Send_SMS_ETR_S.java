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
		
		 xlObj.setCellData("Sheet1", 1, 5, randomeNumber());
		 xlObj.setCellData("Sheet1", 1, 30, randomeNumber());
		 xlObj.setCellData("Sheet1", 1, 45, randomeNumber());
		 xlObj.setCellData("Sheet1", 1, 49, randomeNumber());
		 
		 Thread.sleep(1000);
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
		
		 logger.info("Extracting DataSheet Values started...");
		 
		 
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
		
		
//		String sMobNumPrefix=xlObj.getCellData("Sheet1", 1, 4);
//		String sMobileNumber=xlObj.getCellData("Sheet1", 1, 5);
		String sFullMobileNumber = sMobNumPrefix + " " + sMobileNumber;  
//		
//		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 29);
//		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 30);
		String sFullEnquiry_PhoneNumber = sEnquiry_PN_Prefix + sEnquiry_PhoneNumber; 
//		
//		String sLead_PN_Prefix=xlObj.getCellData("Sheet1", 1, 44);
//		String sLead_PN=xlObj.getCellData("Sheet1", 1, 45);
		String sFullLead_PN = sLead_PN_Prefix+ " " + sLead_PN; 
		
		Thread.sleep(5000);
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
			
		}//If workflow Enabled but Task not set
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayModuleName);
		System.out.println("Add New Module data clicked");
		Thread.sleep(2000);
		
//		//**************Add New Record
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_");

		objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Direct Entry Module Data","No",node);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		int iSMSCount = objSMS.getSMSRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add -"+ sMobileNumber);
			freport("Failed: SMS Not Received after New Record Add -"+ sMobileNumber, "fail",node);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		objSMS.clickFirstSMS();
		sUser1MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullMobileNumber, sSMSTemplateMsg,"SMS Validation for " + sMobileNumber , node);
		UtilityCustomFunctions.logWriteConsole("RecorId after add new record :" + sMobileNumber + "RecordId: "+ sUser1MessageId);
		
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getSMSRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add - Enquiry PN"+ sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after New Record Add - Enquiry PN"+ sEnquiry_PhoneNumber, "fail",node);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		objSMS.clickFirstSMS();
		sUser2MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullEnquiry_PhoneNumber, sSMSTemplateMsg,"SMS Validation for " + sEnquiry_PhoneNumber , node);
		UtilityCustomFunctions.logWriteConsole("RecorId after add new record :" + sMobileNumber + "RecordId: "+ sUser2MessageId);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sLead_PN);
		
		objSMS.clickSearch();
		iSMSCount = objSMS.getSMSRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received after New Record Add - Lead PN"+ sLead_PN);
			freport("Failed: SMS Not Received after New Record Add - Lead PN"+ sLead_PN, "fail",node);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
		}
		objSMS.clickFirstSMS();
		sUser3MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullLead_PN, sSMSTemplateMsg,"SMS Validation for " + sFullLead_PN , node);
		UtilityCustomFunctions.logWriteConsole("RecordId after add new record :" + sFullLead_PN + "RecordId: "+ sUser3MessageId);
		//Edit
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(1000);
		objCMD.clickEllipsis(1);
		objCMD.clickEditRecord();
 		Thread.sleep(6000);
		objCMD.clickSave();
		//SMSNotifier
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		String sCaptureRecordId="";
		iSMSCount = objSMS.getSMSRowcount();
		System.out.println("Total SMS Count:"+iSMSCount+ "for PN:"+ sMobileNumber);
		if(iSMSCount!=3) {
			logger.info("Failed: SMS Not Received after Edit - for Mobile No:"+ sMobileNumber);
			freport("Failed: SMS Not Received after Edit - for Mobile No:"+ sMobileNumber, "fail",node);
			sAssertinFn.assertEquals("Edit Record - SMS Not received"+sMobileNumber, "Edit Record - SMS received"+sMobileNumber);
		}
		else {
			logger.info("Pass: SMS Received for the Mobile Number"+ sMobileNumber);
			freport("Pass: SMS Received for the Mobile Number"+ sMobileNumber, "pass",node);
			sAssertinFn.assertEquals("Edit Record - SMS received:"+sMobileNumber, "Edit Record - SMS received:"+sMobileNumber);
			Thread.sleep(1000);
			sCaptureRecordId = objSMS.ValidateSMSRecord(sMobileNumber, "SMS", "Sent", node);
			UtilityCustomFunctions.logWriteConsole("RecordId after Edit:" + sMobileNumber + "RecordId: "+ sCaptureRecordId );
		}
		//Second SMS Validation after Edit
		Thread.sleep(1000);
		objSMS.setRecipient(sEnquiry_PhoneNumber);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		sCaptureRecordId="";
		iSMSCount = objSMS.getSMSRowcount();
		System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sEnquiry_PhoneNumber);
		if(iSMSCount!=3) {
			logger.info("Failed: SMS Not Received after Edit - for Enquiry No:" + sEnquiry_PhoneNumber);
			freport("Failed: SMS Not Received after Edit - for Enquiry No:" + sEnquiry_PhoneNumber, "fail",node);
			sAssertinFn.assertEquals("Edit Record - SMS received:"+sEnquiry_PhoneNumber, "Edit Record - SMS received:"+sEnquiry_PhoneNumber);
		}
		else {
			logger.info("Pass: SMS Received for the Enquiry Number"+ sEnquiry_PhoneNumber);
			freport("Pass: SMS Received for the Enquiry Number"+ sEnquiry_PhoneNumber, "pass",node);
			sAssertinFn.assertEquals("Edit Record - SMS received: Enquiry "+sEnquiry_PhoneNumber, "Edit Record - SMS received:Enquiry "+sMobileNumber);
			Thread.sleep(1000);
			sCaptureRecordId = objSMS.ValidateSMSRecord(sEnquiry_PhoneNumber, "SMS", "Sent", node);
			UtilityCustomFunctions.logWriteConsole("RecordId after Edit:" + sEnquiry_PhoneNumber + "RecordId: "+ sCaptureRecordId );
		}
		
		//Third SMS Validation after Edit
		Thread.sleep(1000);
		objSMS.setRecipient(sLead_PN);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		sCaptureRecordId="";
		iSMSCount = objSMS.getSMSRowcount();
		System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sLead_PN);
		if(iSMSCount!=3) {
			logger.info("Failed: SMS Not Received after Edit - for Lead No:"+ sLead_PN);
			freport("Failed: SMS Not Received after Edit - for Lead No:"+ sLead_PN, "fail",node);
			sAssertinFn.assertEquals("Edit Old Record - Lead SMS Not received "+sLead_PN, "Edit Old Record - Lead SMS received "+sLead_PN);

		}
		else {
			logger.info("Pass: SMS Received for the Lead Number"+ sLead_PN);
			freport("Pass: SMS Received for the Lead Number"+ sLead_PN, "pass",node);
			sAssertinFn.assertEquals("Edit Record - SMS received:"+sLead_PN, "Edit Record - SMS received:"+sLead_PN);
			Thread.sleep(1000);
			sCaptureRecordId = objSMS.ValidateSMSRecord(sLead_PN, "SMS", "Sent", node);
			UtilityCustomFunctions.logWriteConsole("RecordId after Edit:" + sLead_PN + "RecordId: "+ sCaptureRecordId );
		}
		
		//Single Edit
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickEditRecordItem();
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpModuleName, "text", sEditIndText);
		objDVP.clickRecItemSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		//SMS Validation after Single Edit
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		Thread.sleep(1000);
		sCaptureRecordId="";
		iSMSCount = objSMS.getSMSRowcount();
		System.out.println("Total SMS Count:"+iSMSCount+ "for PN:"+ sMobileNumber);
		if(iSMSCount!=4) {
			logger.info("Failed: SMS Not Received after Single Edit Mobile Number"+ sMobileNumber);
			freport("Failed: SMS Not Received after Single Edit Mobile Number"+ sMobileNumber, "fail",node);
			sAssertinFn.assertEquals("Singe Edit- SMS Not received "+sMobileNumber, "Single Edit Record - SMS received"+sMobileNumber);
		}
		else {
			logger.info("Pass: SMS Received after Single Edit for the Mobile Number"+ sMobileNumber);
			freport("Pass: SMS Received after Single Edit for the Mobile Number"+ sMobileNumber, "pass",node);
			sAssertinFn.assertEquals("Edit Record - SMS received:"+sMobileNumber, "Edit Record - SMS received:"+sMobileNumber);
			Thread.sleep(1000);
			sCaptureRecordId = objSMS.ValidateSMSRecord(sMobileNumber, "SMS", "Sent", node);
			UtilityCustomFunctions.logWriteConsole("Single Edit Record:" + sMobileNumber + "RecordId: "+ sCaptureRecordId );
		}
		//Second SMS Validation after Single Edit
				Thread.sleep(1000);
				objSMS.setRecipient(sEnquiry_PhoneNumber);
				
				objSMS.clickSearch();
				Thread.sleep(1000);
				sCaptureRecordId="";
				iSMSCount = objSMS.getSMSRowcount();
				System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sEnquiry_PhoneNumber);
				if(iSMSCount!=4) {
					logger.info("Failed: SMS Not Received after Single Edit for the Enquiry Number "+ sEnquiry_PhoneNumber);
					freport("Failed: SMS Not Received after Single Edit for the Enquiry Number "+ sEnquiry_PhoneNumber, "fail",node);
					sAssertinFn.assertEquals("Single Edit Second- SMS Not received "+sEnquiry_PhoneNumber, "Single Edit Second - SMS received "+sEnquiry_PhoneNumber);
				}
				else {
					logger.info("Pass: SMS Received after Single Edit for the Enquiry Number"+ sEnquiry_PhoneNumber);
					freport("Pass: SMS Received after Single Edit for Enquiry Number"+ sEnquiry_PhoneNumber, "pass",node);
					sAssertinFn.assertEquals("Single Edit Record - SMS received:"+sEnquiry_PhoneNumber, "Single Edit Record - SMS received:"+sMobileNumber);
					Thread.sleep(1000);
					sCaptureRecordId = objSMS.ValidateSMSRecord(sEnquiry_PhoneNumber, "SMS", "Sent", node);
					UtilityCustomFunctions.logWriteConsole("Single Edit Record:" + sEnquiry_PhoneNumber + "RecordId: "+ sCaptureRecordId );
				}
				
				//Third SMS Validation after Single Edit
				Thread.sleep(1000);
				objSMS.setRecipient(sLead_PN);
				
				objSMS.clickSearch();
				Thread.sleep(1000);
				sCaptureRecordId="";
				iSMSCount = objSMS.getSMSRowcount();
				System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sLead_PN);
				if(iSMSCount!=4) {
					logger.info("Failed: SMS Not Received after Single Edit for the Lead Number "+ sLead_PN);
					freport("Failed: SMS Not Received after Single Edit for the Lead Number "+ sLead_PN, "fail",node);
					sAssertinFn.assertEquals("Single Edit Record - SMS Not received for Lead "+sLead_PN, "Single Edit Record - SMS received for Lead "+sLead_PN);

				}
				else {
					logger.info("Pass: SMS Received after Single Edit for the Lead Number"+ sLead_PN);
					freport("Pass: SMS Received after Single Edit for the Lead Number"+ sLead_PN, "pass",node);
					sAssertinFn.assertEquals("Single Edit Record - SMS received for Lead "+sLead_PN, "Single Edit Record - SMS received for Lead "+sLead_PN);
					Thread.sleep(1000);
					sCaptureRecordId = objSMS.ValidateSMSRecord(sLead_PN, "SMS", "Sent", node);
					UtilityCustomFunctions.logWriteConsole("Single Edit Record:" + sLead_PN + "RecordId: "+ sCaptureRecordId );
				}
				
				//Duplicate
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
				//SMS Validation after Duplicate
				objALP.clickAllList();
				Thread.sleep(1000);
				objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
				Thread.sleep(2000);
				Thread.sleep(1000);
				objSMS.setRecipient(sMobileNumber);
				
				objSMS.clickSearch();
				Thread.sleep(1000);
				sCaptureRecordId="";
				iSMSCount = objSMS.getSMSRowcount();
				System.out.println("Total SMS Count:"+iSMSCount+ "for PN:"+ sMobileNumber);
				if(iSMSCount!=5) {
					logger.info("Failed: SMS Not Received after duplicate for the Mobile Number"+ sMobileNumber);
					freport("Failed: SMS Not Received after duplicate for the Mobile Number"+ sMobileNumber, "fail",node);
					sAssertinFn.assertEquals("SMS Not received after duplicate:"+sMobileNumber, "SMS Not received after duplicate:"+sMobileNumber);
				}
				else {
					logger.info("Pass: SMS Received after duplicate for the Mobile Number"+ sMobileNumber);
					freport("Pass: SMS Received after duplicate for the Mobile Number"+ sMobileNumber, "pass",node);
					sAssertinFn.assertEquals("Duplicate Record - SMS received:Mobile:"+sMobileNumber, "Duplicate Record - SMS received:Mobile:"+sMobileNumber);
					Thread.sleep(1000);
					sCaptureRecordId = objSMS.ValidateSMSRecord(sMobileNumber, "SMS", "Sent", node);
					UtilityCustomFunctions.logWriteConsole("Duplicate Record - SMS received:" + sMobileNumber + "RecordId: "+ sCaptureRecordId );
				}
				//Second SMS Validation after Duplicate
						Thread.sleep(1000);
						objSMS.setRecipient(sEnquiry_PhoneNumber);
						
						objSMS.clickSearch();
						Thread.sleep(1000);
						sCaptureRecordId="";
						iSMSCount = objSMS.getSMSRowcount();
						System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sEnquiry_PhoneNumber);
						if(iSMSCount!=5) {
							logger.info("Failed: SMS Not Received after duplicate for the Enquiry Number"+ sEnquiry_PhoneNumber);
							freport("Failed: SMS Not Received after duplicate for the Enquiry Number"+ sEnquiry_PhoneNumber, "fail",node);
							sAssertinFn.assertEquals("Duplicate Record - SMS not received:Enquiry:"+sEnquiry_PhoneNumber, "Duplicate Record - SMS received:Enquiry:"+sEnquiry_PhoneNumber);
						}
						else {
							logger.info("Pass: SMS Received after duplicate for the Enquiry Number"+ sEnquiry_PhoneNumber);
							freport("Pass: SMS Received for the Enquiry Number"+ sEnquiry_PhoneNumber, "pass",node);
							sAssertinFn.assertEquals("Edit Record - SMS received:Enquiry:"+sEnquiry_PhoneNumber, "Edit Record - SMS received:Enquiry:"+sEnquiry_PhoneNumber);
							Thread.sleep(1000);
							sCaptureRecordId = objSMS.ValidateSMSRecord(sEnquiry_PhoneNumber, "SMS", "Sent", node);
							UtilityCustomFunctions.logWriteConsole("RecordId after duplicate: Enquiry " + sEnquiry_PhoneNumber + "RecordId: "+ sCaptureRecordId );
						}
						
						//Third SMS Validation after Duplicate
						Thread.sleep(1000);
						objSMS.setRecipient(sLead_PN);
						
						objSMS.clickSearch();
						Thread.sleep(1000);
						sCaptureRecordId="";
						iSMSCount = objSMS.getSMSRowcount();
						System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sLead_PN);
						if(iSMSCount!=5) {
							logger.info("Failed: SMS Not Received after duplicate for the Lead Number "+ sLead_PN);
							freport("Failed: SMS Not Received after duplicate for the Lead Number"+ sLead_PN, "fail",node);
							sAssertinFn.assertEquals("After Duplicate - SMS Not received for Lead : " + sLead_PN, "After Duplicate - SMS received for Lead PN : "+ sLead_PN);

						}
						else {
							logger.info("Pass: SMS Received after duplicate for the Lead Number"+ sLead_PN);
							freport("Pass: SMS Received after duplicate for the Lead Number"+ sLead_PN, "pass",node);
							sAssertinFn.assertEquals("After Duplicate - SMS received for Lead : "+sLead_PN, "After Duplicate - SMS received for Lead : "+sLead_PN);
							Thread.sleep(1000);
							sCaptureRecordId = objSMS.ValidateSMSRecord(sLead_PN, "SMS", "Sent", node);
							UtilityCustomFunctions.logWriteConsole("Duplicate Record - SMS received" + sLead_PN + "RecordId: "+ sCaptureRecordId );
						}
						//Add New Data in Summary View
						Thread.sleep(3000);
						objALP.clickAllList();
						Thread.sleep(1000);
						objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
						System.out.println("Module clicked");
						Thread.sleep(3000);
						objCMD.clickExistingModData(1);
						
						objDVP.clickAddRecord();
						Thread.sleep(3000);
						//Add New Record and Check
						objCRMRs.fAddValuestoModulePage("Test","//SMS//WF5_Send_SMS_ETR_S_");
						Thread.sleep(3000);
						objCRMRs.fValidateAllFields("Test", "//SMS//WF5_Send_SMS_ETR_S_","Summary Add Data Validation","No",node);
						//SMS Validation after Summary Add New
						objALP.clickAllList();
						Thread.sleep(1000);
						objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
						Thread.sleep(2000);
						Thread.sleep(1000);
						objSMS.setRecipient(sMobileNumber);
						
						objSMS.clickSearch();
						Thread.sleep(1000);
						sCaptureRecordId="";
						iSMSCount = objSMS.getSMSRowcount();
						System.out.println("Total SMS Count:"+iSMSCount+ "for PN:"+ sMobileNumber);
						if(iSMSCount!=6) {
							logger.info("Failed: SMS Not Received after summary ADD for the Mobile Number "+ sMobileNumber);
							freport("Failed: SMS Not Received after summary ADD for the Mobile Number "+ sMobileNumber, "fail",node);
							sAssertinFn.assertEquals("Summary Add New Record - SMS Not received "+sMobileNumber, "Summary Add New Record - SMS Not received "+sMobileNumber);
						}
						else {
							logger.info("Pass: SMS Received after summary ADD for the Mobile : "+ sMobileNumber);
							freport("Pass: SMS Received after summary ADD for the Mobile : "+ sMobileNumber, "pass",node);
							sAssertinFn.assertEquals("Summary Add New Record - SMS received for Mobile: "+sMobileNumber, "Summary Add New Record - SMS received for Mobile: "+sMobileNumber);
							Thread.sleep(1000);
							sCaptureRecordId = objSMS.ValidateSMSRecord(sMobileNumber, "SMS", "Sent", node);
							UtilityCustomFunctions.logWriteConsole("Summary Add New Record:" + sMobileNumber + "RecordId: "+ sCaptureRecordId );
						}
						//Second SMS Validation after Summary Add
						Thread.sleep(1000);
						objSMS.setRecipient(sEnquiry_PhoneNumber);
						objSMS.clickSearch();
						Thread.sleep(1000);
						sCaptureRecordId="";
						iSMSCount = objSMS.getSMSRowcount();
						System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sEnquiry_PhoneNumber);
						if(iSMSCount!=6) {
									logger.info("Failed: SMS Not Received after Summary Add New for the Enquiry Number"+ sEnquiry_PhoneNumber);
									freport("Failed: SMS Not Received after Summary Add New for the Enquiry Number"+ sEnquiry_PhoneNumber, "fail",node);
									sAssertinFn.assertEquals("Summary Add New Record - SMS Not received for Enquiry: "+sEnquiry_PhoneNumber, "Summary Add New Record - SMS received for Enquiry: "+sEnquiry_PhoneNumber);
						}
						else {
									logger.info("Pass: SMS Received after Summary Add New for the Enquiry Number "+ sEnquiry_PhoneNumber);
									freport("Pass: SMS Received after Summary Add New for the Enquiry Number "+ sEnquiry_PhoneNumber, "pass",node);
									sAssertinFn.assertEquals("Summary Add New Record - SMS received for Enquiry: "+sEnquiry_PhoneNumber, "Summary Add New Record - SMS received for Enquiry: "+sEnquiry_PhoneNumber);
									Thread.sleep(1000);
									sCaptureRecordId = objSMS.ValidateSMSRecord(sEnquiry_PhoneNumber, "SMS", "Sent", node);
									UtilityCustomFunctions.logWriteConsole("RecorId after Add New Summary Details:" + sEnquiry_PhoneNumber + "RecordId: "+ sCaptureRecordId );
						}
								
								//Third SMS Validation after Summary Add
								Thread.sleep(1000);
								objSMS.setRecipient(sLead_PN);
								
								objSMS.clickSearch();
								Thread.sleep(1000);
								sCaptureRecordId="";
								iSMSCount = objSMS.getSMSRowcount();
								System.out.println("Total SMS Count:"+iSMSCount+ "for PN: "+ sLead_PN);
								if(iSMSCount!=6) {
									logger.info("Failed: SMS Not Received after Summary Add New for the Lead Number"+ sLead_PN);
									freport("Failed: SMS Not Received after Summary Add New for the Lead Number"+ sLead_PN, "fail",node);
									sAssertinFn.assertEquals("Summary Add New Record - SMS not received for Lead: "+sLead_PN, "Summary Add New Record - SMS received for Lead: "+sLead_PN);

								}
								else {
									logger.info("Pass: SMS Received after Summary Add New for the Lead Number"+ sLead_PN);
									freport("Pass: SMS Received after Summary Add New for the Lead Number"+ sLead_PN, "pass",node);
									sAssertinFn.assertEquals("Summary Add New Record - SMS received for Lead: "+sLead_PN, "Summary Add New Record - SMS received for Lead: "+sLead_PN);
									Thread.sleep(1000);
									sCaptureRecordId = objSMS.ValidateSMSRecord(sLead_PN, "SMS", "Sent", node);
									UtilityCustomFunctions.logWriteConsole("Summary Add New Record" + sLead_PN + "RecordId: "+ sCaptureRecordId );
								}
	}//test
	}//class