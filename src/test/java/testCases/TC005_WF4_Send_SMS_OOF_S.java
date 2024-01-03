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

public class TC005_WF4_Send_SMS_OOF_S extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC005_WF5_Send_SMS_OOF_S");
		System.out.println("Bfore test");
	}
	@Test
	public void test_Send_SMS_ETR_M_Test () throws Exception {
		node = test.createNode("WF5_Send_SMS_OOF_S");
		System.out.println("In test");
		logger.info("******starting WF5_Send_SMS_OOF_Save ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
//		String sPath=".\\testData\\SMS\\" + "WF4_Send_SMS_OOF_S" + "_Live.xlsx" ;
		String sPath=".\\testData\\SMS\\" + "WF4_Send_SMS_OOF_S" + "_Test.xlsx" ;
	
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
//		driver.get(rb.getString("appURL"));
		driver.get("https://rdot.in/public/admin?Module=Sms_notifiers&view=List&viewname=");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName1");
		String sPassword =  rb.getString("passWord1");
		String sAssignedTo = rb.getString("AssignedTo1");
		
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
		objALP.clickModuleOnListAll(driver, sDisplayModuleName,6);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayModuleName);
		Thread.sleep(2000);
		
//		//**************Add New Record
		objCRMRs.fAddValuestoModulePage("Test","//SMS//WF4_Send_SMS_OOF_S_","Sheet1");

		objCRMRs.fValidateAllFields("Test", "//SMS//WF4_Send_SMS_OOF_S_","Sheet1","Module Data Validation","No",node);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		
		
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
		Thread.sleep(1000);
		objSMS.setRecipient(sMobileNumber);
		
		objSMS.clickSearch();
		int iSMSCount = objSMS.getSMSRowcount(); 
		if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber);
			freport("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber, "fail",node);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
			Assert.fail("SMS Not Received for the Mobile Number"+ sMobileNumber);
		}
		objSMS.clickFirstSMS();
		sUser1MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullMobileNumber, sSMSTemplateMsg,"SMS Validation for " + sMobileNumber , node);
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		Thread.sleep(2000);
		
		 objSMS.setRecipient(sEnquiry_PhoneNumber);
		 objSMS.clickSearch();
		 Thread.sleep(1000);
		 iSMSCount = objSMS.getSMSRowcount(); 
		 if(iSMSCount<=1) {
			logger.info("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber);
			freport("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber, "fail",node);
			sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
			Assert.fail("SMS Not Received for the Mobile Number"+ sMobileNumber);
		}
		 	
		 System.out.println("Total SMS Count:" + objSMS.getSMSRowcount());
		 objSMS.clickFirstSMS();
		 sUser2MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullEnquiry_PhoneNumber, sSMSTemplateMsg,"SMS Validation for " + sEnquiry_PhoneNumber , node); 
		
		 Thread.sleep(2000);
		 objALP.clickAllList();
		 Thread.sleep(1000);
		 objALP.clickModuleOnListAll(driver, "SMS Notifiers",2);
		 Thread.sleep(2000);
		 
		 objSMS.setRecipient(sLead_PN);
		 objSMS.clickSearch();
		 iSMSCount = objSMS.getSMSRowcount(); 
			if(iSMSCount<=1) {
				logger.info("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber);
				freport("Failed: SMS Not Received for the Mobile Number"+ sMobileNumber, "fail",node);
				sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
				Assert.fail("SMS Not Received for the Mobile Number"+ sMobileNumber);
			}
			 System.out.println("Total SMS Count:" + objSMS.getSMSRowcount());
			 objSMS.clickFirstSMS();
			 sUser3MessageId = objSMS.fValidateSMSNotification(sAssignedTo, sFullLead_PN, sSMSTemplateMsg,"SMS Validation for " + sLead_PN , node);
			 
			 xlObj.setCellData("Sheet1", 1, 36, sUser1MessageId);
			 xlObj.setCellData("Sheet1", 1, 37, sUser2MessageId);
			 xlObj.setCellData("Sheet1", 1, 38, sUser3MessageId);
	}	
	
}
