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
import pageObjects.SummaryViewPage;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;
import testBase.BaseClass;
public class TC013_CreateEntity_OOFS_NewValues extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("CreateEntity_OOFS_NewValues");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	@Test
	public void testCreateEntity_OOFS_NewValues() throws Exception {
		node = test.createNode("CreateEntity_OOF_S_NewVal");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		
		logger.info("******starting TC013_CreateEntity_OOF_S_NewValues ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
//		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_NewValues" + "_Live.xlsx" ;
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_NewValues" + "_Test.xlsx";
	
		ExcelUtility xlObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
		
		String sPhoneNumber1 = randomeNumber();
		String sPhoneNumber2 = randomeNumber();
		String sPhoneNumber3 = randomeNumber();
		String sPhoneNumber4 = randomeNumber();
		
		xlObj.setCellData("Sheet1", 1, 6, sPhoneNumber1);
		xlObj.setCellData("Sheet1", 1, 31, sPhoneNumber2);
		xlObj.setCellData("Sheet1", 1, 47, sPhoneNumber3);
		xlObj.setCellData("Sheet1", 1, 51, sPhoneNumber4);
		
		xlObj.setCellData("Sheet2", 1, 6, sPhoneNumber1);
		xlObj.setCellData("Sheet2", 1, 31, sPhoneNumber2);
		xlObj.setCellData("Sheet2", 1, 47, sPhoneNumber3);
		xlObj.setCellData("Sheet2", 1, 51, sPhoneNumber4);
		
		xlObj.setCellData("Sheet3", 1, 6, sPhoneNumber1);
		xlObj.setCellData("Sheet3", 1, 31, sPhoneNumber2);
		xlObj.setCellData("Sheet3", 1, 47, sPhoneNumber3);
		xlObj.setCellData("Sheet3", 1, 51, sPhoneNumber4);
		
		xlObj.setCellData("Sheet4", 1, 6, sPhoneNumber1);
		xlObj.setCellData("Sheet4", 1, 31, sPhoneNumber2);
		xlObj.setCellData("Sheet4", 1, 47, sPhoneNumber3);
		xlObj.setCellData("Sheet4", 1, 51, sPhoneNumber4);
		logger.info("Extracting DataSheet Values started...");
		
		Thread.sleep(1000);
		String sExpSrcModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpTrgModuleName = xlObj.getCellData("Sheet1", 1, 1);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 2);
//		String sExpAssignedTo = xlObj.getCellData("Sheet1", 1, 3);
		String sText=xlObj.getCellData("Sheet1", 1, 4);
		String sMobNumPrefix=xlObj.getCellData("Sheet1", 1, 5);
		String sMobileNumber=xlObj.getCellData("Sheet1", 1, 6);
		String sEmail=xlObj.getCellData("Sheet1", 1, 7);
		String sPickListValue=xlObj.getCellData("Sheet1", 1, 8);
		String sMultiComboValues=xlObj.getCellData("Sheet1", 1, 9);
		String sCity=xlObj.getCellData("Sheet1", 1, 10);
		String sState=xlObj.getCellData("Sheet1", 1, 11);
		String sCountry=xlObj.getCellData("Sheet1", 1, 12);
		String sCheckBox=xlObj.getCellData("Sheet1", 1, 13);
		String sDate =xlObj.getCellData("Sheet1", 1, 14);
		String sTime =xlObj.getCellData("Sheet1", 1, 15);
		String sDateandTime =xlObj.getCellData("Sheet1", 1, 16);
		String sRelatedModule =xlObj.getCellData("Sheet1", 1, 17);
		String sFilePath =xlObj.getCellData("Sheet1", 1, 18);
		String sNamePrefix =xlObj.getCellData("Sheet1", 1, 19);
		String sName=xlObj.getCellData("Sheet1", 1, 20);
		String sNumber=xlObj.getCellData("Sheet1", 1, 21);
		String sCurrency=xlObj.getCellData("Sheet1", 1, 22);
		String sUrl=xlObj.getCellData("Sheet1", 1, 23);
		String sEnq_Name_Prefix=xlObj.getCellData("Sheet1", 1, 24);
		String sEnquiry_Name=xlObj.getCellData("Sheet1", 1, 25);
		String sEnquiry_Email=xlObj.getCellData("Sheet1", 1, 26);
		String sEnquiry_Text=xlObj.getCellData("Sheet1", 1, 27);
		String sEnquiry_TextArea=xlObj.getCellData("Sheet1", 1, 28);
		String sEnquiry_Date=xlObj.getCellData("Sheet1", 1, 29);
		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 30);
		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 31);
		String sEnquiry_Category=xlObj.getCellData("Sheet1", 1, 32);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 33);
		String sActionType=xlObj.getCellData("Sheet1", 1, 34);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 35);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 36);
		String sUser1MessageId=xlObj.getCellData("Sheet1", 1, 37);
		String sUser2MessageId=xlObj.getCellData("Sheet1", 1, 38);
		String sUser3MessageId=xlObj.getCellData("Sheet1", 1, 39);
		String sUser1RecordId=xlObj.getCellData("Sheet1", 1, 40);
		String sUser2RecordId=xlObj.getCellData("Sheet1", 1, 41);
		String sUser3RecordId=xlObj.getCellData("Sheet1", 1, 42);
		String sDisplayMod1=xlObj.getCellData("Sheet1", 1, 43);
		String sDisplayMod2=xlObj.getCellData("Sheet1", 1, 44);
		String sEditIndText=xlObj.getCellData("Sheet1", 1, 45);
		String sLead_PN_Prefix=xlObj.getCellData("Sheet1", 1, 46);
		String sLead_PN=xlObj.getCellData("Sheet1", 1, 47);
		String sLead_Email=xlObj.getCellData("Sheet1", 1, 48);
		String sLead_Text=xlObj.getCellData("Sheet1", 1, 49);
		String sSales_PN_Prefix=xlObj.getCellData("Sheet1", 1, 50);
		String sSales_PN=xlObj.getCellData("Sheet1", 1, 51);
		String sSales_Email=xlObj.getCellData("Sheet1", 1, 52);
		
		sMobileNumber=xlObj.getCellData("Sheet1", 1, 6);
		sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 31);
		sLead_PN=xlObj.getCellData("Sheet1", 1, 47);
		sSales_PN=xlObj.getCellData("Sheet1", 1, 51);
		 
		UtilityCustomFunctions.logWriteConsole("PN1:" + sMobileNumber);
		UtilityCustomFunctions.logWriteConsole("PN2:" + sEnquiry_PhoneNumber);
		UtilityCustomFunctions.logWriteConsole("PN3:" + sLead_PN);
		UtilityCustomFunctions.logWriteConsole("PN4:" + sSales_PN);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		DetailViewPage objDVP = new DetailViewPage(driver);
		SummaryViewPage objSVP = new SummaryViewPage(driver);
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName1");
		String sPassword =  rb.getString("passWord1");
		String sAssignedTo = rb.getString("AssignedTo1");
		
		Thread.sleep(3000);
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
		objCRMRs.fNavigatetoWorkflow(sDisplayMod1);
		String sWorkFlowStatus="";
		
		UtilityCustomFunctions.logWriteConsole("Exp Module Name:" + sExpWorkFlowName);
		UtilityCustomFunctions.logWriteConsole("Execution Condition:" + sExecutionCondition);
		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sDisplayMod1, sExpWorkFlowName, sExecutionCondition);
		UtilityCustomFunctions.logWriteConsole("Current Workflow status:" + sWorkFlowStatus);
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
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		int iTarRecId = objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Captured the Targer last record:"+iTarRecId);
//		int iTarRecId = 98496;
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sDisplayMod1);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sDisplayMod1);
		Thread.sleep(2000);
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_NewValues_","Sheet1",false);
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
		Thread.sleep(5000);
		objALP.clickAllList();
		UtilityCustomFunctions.logWriteConsole("All Menu Items Clicked");
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.logWriteConsole("Target Module Opened:"+sDisplayMod2);
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		int iTarAfterNewRec = objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Record Id comparison started after new Record Added");
		if(iTarRecId == iTarAfterNewRec) {
			freport("Target Entity Not Created after New source data added", "fail", node);
		}
		else {
			freport("Target Entity Created after Source Added", "pass", node);
			objCRMRs.fValidateEntityResponse("Test", "//CreateEntity//CreateEntity_OOFS_NewValues_","Sheet1","Create Entity in Target","No",node,true);
		}
		
		
		UtilityCustomFunctions.logWriteConsole("Created Entity Validation in Target Module Done");
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity");
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		objCMD.clickExistingModData(1);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_NewValues_","Sheet2",false);
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		int iTarAftSummaryAdd = objCRMRs.getLastRecordId();
		if(iTarAfterNewRec == iTarAftSummaryAdd) {
			freport("Target Entity Not Created for Summary Add", "fail", node);
		}
		else {
			freport("Target Entity Created after Summary Added", "pass", node);
			objCRMRs.fValidateEntityResponse("Test", "//CreateEntity//CreateEntity_OOFS_NewValues_","Sheet2","Create Entity after Summary Add","No",node, true);
			UtilityCustomFunctions.logWriteConsole("Created Entity after Summary Add is Done");
		}
		//Duplicate Entity Validation
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
//		objCMD.clickExistingModData(1);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		int iTarAftDuplicate = objCRMRs.getLastRecordId();
	
		if(iTarAftSummaryAdd== iTarAftDuplicate) {
			freport("Target Entity Not Created for Duplicate Record", "fail", node);
		}
		else {
			freport("Target Entity Created after Duplicate Record", "pass", node);
			objCRMRs.fValidateEntityResponse("Test", "//CreateEntity//CreateEntity_OOFS_NewValues_","Sheet2","Create Entity after duplicate record","No",node,true);
			UtilityCustomFunctions.logWriteConsole("Created Entity after Duplicate is Done");
		}
		//Edit Source Module Validation
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		System.out.println("Module clicked");
		Thread.sleep(6000);
		System.out.println("Before selecting 1st Record");
		objCMD.clickExistingModData(1);
		Thread.sleep(6000);
		Thread.sleep(1000);
		System.out.println("Before Edit button clicked in summary view");
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		
		int iTarAftFullEdit = objCRMRs.getLastRecordId();
		if(iTarAftDuplicate== iTarAftFullEdit) {
			freport("Target Entity Not Created for Edit & Save", "pass", node);
		}else {
			freport("Target Entity Created for Edit & save", "fail", node);
		}
		//Edit Single Line Summary
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		System.out.println("Module clicked");
		Thread.sleep(6000);
		System.out.println("Before selecting 1st Record");
		objCMD.clickExistingModData(1);

		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
//		objDVP.clickEditRecordItem();
		objSVP.clickEditCheckBox(1);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpSrcModuleName, "text", sEditIndText);
		objDVP.clickRecItemSave(sExpSrcModuleName);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.checkPageLoadComplete();
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		int iTarAftSingleLineEdit = objCRMRs.getLastRecordId();
		if(iTarAftSingleLineEdit== iTarAftFullEdit) {
			freport("Target Entity Not Created for Single Line Edit", "pass", node);
		}else {
			freport("Target Entity Created for Single Line Edit", "fail", node);
		}
		
		objHP.clickLogoutCRM();
	}
}
