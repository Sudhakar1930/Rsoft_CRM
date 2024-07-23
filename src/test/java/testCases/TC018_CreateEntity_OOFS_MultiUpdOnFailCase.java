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
import pageObjects.SummaryViewPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC018_CreateEntity_OOFS_MultiUpdOnFailCase extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("CreateEntity_OOFS_MultiUpdateonFailCase");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	
	@Test
	public void tCreateEntity_OOFS_MultiUpdateOnFailCase() throws Exception {
		node = test.createNode("CreateEntity_OOFS_MultiUpdateOnFailCase");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		UtilityCustomFunctions.logWriteConsole("******TC018_CreateEntity_OOFS_MultiUpdOnFailCase ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_MultiFailureCase" + "_Test.xlsx";
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
		
		
		Thread.sleep(1000);
		String sExpSrcModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpTrgModuleName = xlObj.getCellData("Sheet1", 1, 1);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 2);
		
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 33);
		String sActionType=xlObj.getCellData("Sheet1", 1, 34);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 35);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 36);
		
		String sTarModule1=xlObj.getCellData("Sheet1", 1, 37);
		String sTarModule2=xlObj.getCellData("Sheet1", 1, 38);
		String sTarModule3=xlObj.getCellData("Sheet1", 1, 39);
		
		String sLeadDefaultCotent = xlObj.getCellData("Sheet5", 1, 6);
		String sEnquiryDefaultCotent = xlObj.getCellData("Sheet5", 1, 7);
		String sOpportPhonePrefix = xlObj.getCellData("Sheet5", 1, 8);
		String sOpportunityDefaultCotent = xlObj.getCellData("Sheet5", 1, 9);
		
		String sDisplayMod1=xlObj.getCellData("Sheet1", 1, 43);
		String sDisplayMod2=xlObj.getCellData("Sheet1", 1, 44);
		String sEditIndText=xlObj.getCellData("Sheet1", 1, 45);
		String sOriginalSummaryText =xlObj.getCellData("Sheet1", 1, 4);
		String sSummaryAddText =xlObj.getCellData("Sheet2", 1, 4);
		
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
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		
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
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
		}//avatar displayed.
		
		// Submit the Current Workflow and Task
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Navigate Module:" + sDisplayMod1);
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
		
		UtilityCustomFunctions.logWriteConsole("Capture Initial Record Id for Source & Target Started");
		//get old source record Id
		objALP.clickAllList();
		Thread.sleep(2000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		Thread.sleep(1000);
		int iOldSrcRecId= objCRMRs.getLastRecordId();
		
		//get old target record Id
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldTrgRecId= objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Captured the Target & Source Latest Record Ids");
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		
		//Targets Old Record Ids
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldTrg1RecId = objCRMRs.getLastRecordId();
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldTrg2RecId = objCRMRs.getLastRecordId();
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldTrg3RecId = objCRMRs.getLastRecordId();
		
		//Add New Module Data
		//Navigate to Source Module
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sDisplayMod1);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sDisplayMod1);
		Thread.sleep(2000);

		//Add New Record
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet1",false);
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
				
		//Capture Record Ids
		int iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Source Id after add new record:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id after add new record:" + iCurrSrcRecId);
		boolean IsTarget =false; 
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source not Updated on Fail Case - Add New Record ", "fail", node);
		}
		else {
			freport("Source Updated on Fail Case - Add New Record ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet6","Source Update on FailCase: Add New Record","No",node,IsTarget);
		}
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);

		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target - Add New Record", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist- Add New Record", "fail", node);
		}
		
		
		//Navigate to first Target
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		driver.navigate().refresh();
		Thread.sleep(3000);
		Thread.sleep(3000);
		String sLeadFailureEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module Not Incremented After @Add New Record - OOFS-on MultiUpdate on Fail Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailSummary, sLeadDefaultCotent, "Lead Module on @Add New Record - OOFS--Summary Record", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadFailureEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailDTView, sLeadDefaultCotent, "Lead Module on @Add New Record - OOFS--Detail View", node);		}
		else {
			freport("Lead Module List Incremented After @Add New Record - OOFS- Multi Update on Fail case", "fail", node);
		}
		//Navigate to second Target
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		String sActEnqFullName = objDVP.getArraySummary(1);
		String sActEnqFNArray[] = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqFNArray[0].trim() + " " + sActEnqFNArray[1].trim();
		
		String sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented After @Add New Record - OOFS- MultiUpdate on Failure case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry Related Module Summary Page Name Validation @Add New Record - OOFS-", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqFNDTVArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqFNDTVArray[0].trim() + " " + sActEnqFNDTVArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry Module DT View Page Name Validation @Add New Record - OOFS-", node);
		}
		else {
			freport("Enquiry Module List Incremented After @Add New Record - OOFS- MultiUpdate on Failure case", "fail", node);
		}
		
		//Navigate to Third Module
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		String sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sPNTitleArray[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		
		String sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module List Not Incremented @Add New Record - OOFS- MultiUpdate on Failure Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity Related Module Phone Number  Validation@Add New Record - OOFS-", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity Module DT View Page Phone Number Validation@Add New Record - OOFS-", node);
		}
		else {
			freport("Opportunity Module List Incremented @Add New Record - OOFS- MultiUpdate on Failure Case", "fail", node);
		}
		
		//Summary Add New Record
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		iOldTrg1RecId = iCurrTrg1RecId;
		iOldTrg2RecId = iCurrTrg2RecId;
		iOldTrg3RecId = iCurrTrg3RecId;
		
		UtilityCustomFunctions.logWriteConsole("Navigate to Source Summary Page");
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity Started");
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet2",false);
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Record completed");
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		driver.navigate().refresh();
		Thread.sleep(3000);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		
		//Source Update Validation
		IsTarget =false; 
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Create Entity-Summary Add-Source added", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet6","Source Update on FailCase: Summary Add","No",node,IsTarget);
		}
		else {
			freport("Create Entity-Summary Add-Source not added", "fail", node);
		}
		//Failed Target  Count Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target @Summary Add New Record - OOFS-", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist- @Summary Add New Record - OOFS-", "fail", node);
		}
		
		//Navigate to first Target
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		driver.navigate().refresh();
		Thread.sleep(3000);
		Thread.sleep(3000);
		sLeadFailureEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module Not Incremented @Summary Add New Record - OOFS-on MultiUpdate on Fail Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailSummary, sLeadDefaultCotent, "Lead Module on @Summary Add New Record - OOFS - Summary View", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadFailureEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailDTView, sLeadDefaultCotent, "Lead Module on  @Summary Add New Record - OOFS- -Detail View", node);
		}
		else {
			freport("Lead Module List Incremented After - @Summary Add New Record - OOFS- Multi Update on Fail case", "fail", node);
		}
		//Navigate to second Target
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActEnqFullName = objDVP.getArraySummary(1);
		sActEnqFNArray = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqFNArray[0].trim() + " " + sActEnqFNArray[1].trim();
		
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		
		freport("Enquiry Module List Not Incremented @Summary Add New Record - OOFS- MultiUpdate on Failure case", "pass", node);
		UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry Related Module Summary Page Name Validation @Summary Add New Record - OOFS-", node);
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented After - Summary Add New, MultiUpdate on Failure case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry Related Module - after Summary Add New, Summary Page Name Validation @Summary Add New Record - OOFS-", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqFNDTVArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqFNDTVArray[0].trim() + " " + sActEnqFNDTVArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry Related Module - @Summary Add New Record - OOFS-, DT View Page Name Validation", node);
		}
		else {
			freport("Enquiry Module List Incremented @Summary Add New Record - OOFS-, MultiUpdate on Failure case", "fail", node);
		}
		
		//Navigate to Third Module
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		sPNTitleArray = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		
		sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		
		freport("Opportunity Module List Not Incremented After @Summary Add New Record - OOFS-MultiUpdate on Failure Case", "pass", node);
		UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity Related Module Phone Number  Validation @Summary Add New Record - OOFS-", node);
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module List Not Incremented After @Summary Add New Record - OOFS-, MultiUpdate on Failure Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity Related Summary View Module Phone Number  Validation @Summary Add New Record - OOFS", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity Module DT View Page Phone Number Validation@Summary Add New Record - OOFS", node);
		}
		else {
			freport("Opportunity Module List Incremented @Summary Add New Record - OOFS, MultiUpdate on Failure Case", "fail", node);
		}

		//Duplicate Record Scenario
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		iOldTrg1RecId = iCurrTrg1RecId;
		iOldTrg2RecId = iCurrTrg2RecId;
		iOldTrg3RecId = iCurrTrg3RecId;
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity Started");
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet3",true);
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Duplicate Add Record completed");
//		objCMD.clickSave();
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		//Source Update on Duplicate Record Validation
		//Capture Record Ids
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Source Id after duplicate record:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id after duplicate record:" + iCurrSrcRecId);
		IsTarget =false; 
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source not Updated on Fail Case - @Duplicate Record - OOFS", "fail", node);
		}
		else {
			freport("Source Updated on Fail Case - @Duplicate Record - OOFS", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet6","Source Update on FailCase: @Duplicate Record - OOFS","No",node,IsTarget);
		}
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);

		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target - @Duplicate Record - OOFS", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist- @Duplicate Record - OOFS", "fail", node);
		}
		
		//Navigate to first Target
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		driver.navigate().refresh();
		Thread.sleep(3000);
		Thread.sleep(3000);
		sLeadFailureEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module Not Incremented After @Duplicate Record - OOFS on MultiUpdate on Fail Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailSummary, sLeadDefaultCotent, "Lead Module @Duplicate Record - OOFS - Summary View", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadFailureEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailDTView, sLeadDefaultCotent, "Lead Module @Duplicate Record - OOFS-Detail View", node);
		}
		else {
			freport("Lead Module List Incremented After @Duplicate Record - OOFS Multi Update on Fail case", "fail", node);
		}
		//Navigate to second Target
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActEnqFullName = objDVP.getArraySummary(1);
		sActEnqFNArray = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqFNArray[0].trim() + " " + sActEnqFNArray[1].trim();
		
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented After  @Duplicate Record - OOFS MultiUpdate on Failure case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry Related Module  @Duplicate Record - OOFS - Summary Page Name Validation", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqFNDTVArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqFNDTVArray[0].trim() + " " + sActEnqFNDTVArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry Module  @Duplicate Record - OOFS - DT View Page Name Validation", node);
		}
		else {
			freport("Enquiry Module List Incremented After  @Duplicate Record - OOFS MultiUpdate on Failure case", "fail", node);
		}
				
		//Navigate to Third Module
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		sPNTitleArray = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		
		sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module List Not Incremented  @Duplicate Record - OOFS MultiUpdate on Failure Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity Related Module Phone Number  Validation  @Duplicate Record - OOFS", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity Module DT View Page Phone Number Validation @Duplicate Record - OOFS", node);
		}
		else {
			freport("Opportunity Module List Incremented After Create Entity MultiUpdate on Failure Case @Duplicate Record - OOFS", "fail", node);
		}
		
		//************** Edit & Save *****************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		iOldTrg1RecId = iCurrTrg1RecId;
		iOldTrg2RecId = iCurrTrg2RecId;
		iOldTrg3RecId = iCurrTrg3RecId;
		
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
		System.out.println("Before Edit button clicked in summary view");
		objCMD.clickEdit();
 		Thread.sleep(6000);
 		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet4",true);
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Edit & Save Record completed");
//		objCMD.clickSave();
		Thread.sleep(3000);
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		//Source Update on Edit & Save Record Validation
		
		IsTarget =false; 
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Source Add after @Edit & Save - OOFS", "fail", node);
			
		}
		else {
			freport("Source Add after @Edit & Save - OOFS", "pass", node);
//			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity/CreateEntity_OOFS_MultiFailureCase_","Sheet4","CE Source @Edit & Save - OOFS","No",node,IsTarget);
		}
		
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);

		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target - @Edit & Save - OOFS", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist- @Edit & Save - OOFS", "fail", node);
		}
		
		
		
		//************** Single Line Summary Edit *****************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		iOldTrg1RecId = iCurrTrg1RecId;
		iOldTrg2RecId = iCurrTrg2RecId;
		iOldTrg3RecId = iCurrTrg3RecId;
		
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
//				objDVP.clickEditRecordItem();
		objSVP.clickEditCheckBox(1);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpSrcModuleName, "text", sEditIndText);
		Thread.sleep(1000);
		objDVP.clickRecItemSave(sExpSrcModuleName);
		Thread.sleep(1000);
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		Thread.sleep(5000);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		Thread.sleep(5000);
		String sActSummaryText = objDVP.getArraySummary(1);
		
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Source Add after Single Line Edit", "fail", node);
			
		}
		else {
			freport("Source Add after Single Line Edit", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActSummaryText, sEditIndText, "SourceUpdate_SummPage@SingleLineEdit-Multiple Update on Fail Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActSourceDTViewText = objDVP.getArrayDetails(6);
			UtilityCustomFunctions.fSoftAssert(sActSourceDTViewText, sEditIndText, "SourceUpdate_DetailViewPage@SingleLineEdit-Multiple Update on Fail Case", node);
		}
			
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);

		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target - @Single Line Edit-OOFS Fail Case", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist- @Single Line Edit-OOFS Fail Case", "fail", node);
		}	
		
		objHP.clickLogoutCRM();
	}
	
}
