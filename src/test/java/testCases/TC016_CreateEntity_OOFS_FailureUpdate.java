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

public class TC016_CreateEntity_OOFS_FailureUpdate extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("CreateEntity_OOFS_FailureUpdate");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	
	@Test
	public void testCreateEntity_OOFS_FailureUpdate() throws Exception {
		node = test.createNode("CreateEntity_OOFS_FailureUpdate");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		UtilityCustomFunctions.logWriteConsole("******TC016_CreateEntity_OOFS_FailureUpdate****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_FailureUpdate" + "_Test.xlsx";
		
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
		
		String sDisplayMod1=xlObj.getCellData("Sheet1", 1, 43);
		String sDisplayMod2=xlObj.getCellData("Sheet1", 1, 44);
		String sEditIndText=xlObj.getCellData("Sheet1", 1, 45);
		
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
		objALP.clickAllList();
		Thread.sleep(2000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		int iOldSrcRecId= objCRMRs.getLastRecordId();
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iOldTrgRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Captured the Target & Source Latest Record Ids");
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		
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
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet1");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
//		Thread.sleep(10000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		//Capture Record Ids
		int iCurrSrcRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source not Updated on Fail Case - Add New Record ", "fail", node);
		}
		else {
			freport("Source Updated on Fail Case - Add New Record ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Failure Update Add New Record","No",node,false);
		}
		
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		int iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);

		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist", "fail", node);
		}
		
		//**************** Summary Add Record *********************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		UtilityCustomFunctions.logWriteConsole("Navigate to Source Summary Page");
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity Started");
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet2");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Record completed");
//		Thread.sleep(10000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		
		//Capture Record Ids
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source Update on Fail Case @ Summary Add", "fail", node);
		}
		else {
			freport("Source Update on Fail Case @ Summary Add", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Fail Case Update on Summary Add","No",node,false);
		}
		
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created after Summary data added", "pass", node);
		}
		else {
			freport("Target Entity Created after Summary data Added", "fail", node);
		}
		
		//**************** Duplicate Record Validation **************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		objSVP.fWaitTillControlVisible();
		//Source Failure case Update While Duplicate Record Validation
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source not Updated on Fail Case @Duplicate Record", "fail", node);
		}
		else {
			freport("Source Updated on Fail Case @Duplicate Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Duplicate Failure Update Case ","No",node,false);
		}
		
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		Thread.sleep(3000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		UtilityCustomFunctions.logWriteConsole("CurrentUrl: Target URL"+driver.getCurrentUrl());
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity on Fail Case: OldRecordId: " + iOldTrgRecId + "Current Target RecId: " + iCurrTrgRecId, "pass", node);
			freport("Target not created on Fail Case - Duplicate Record", "pass", node);
		}
		else {
			freport("Target Entity on Fail Case: OldRecordId: " + iOldTrgRecId + "Current Target RecId: " + iCurrTrgRecId, "fail", node);
			freport("Target not created on Fail Case - Duplicate Record", "fail", node);
		}
		
		//************** Edit & Save *****************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
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
		iCurrSrcRecId = objCRMRs.getLastRecordId(); 
		UtilityCustomFunctions.logWriteConsole("Old Source Record Id before Edit & Save:" + iOldSrcRecId + "Current Record Id: Before Edit & Save" + iCurrSrcRecId);
		iOldSrcRecId=iCurrSrcRecId;
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		Thread.sleep(3000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
		objSVP.fWaitTillControlVisible();
		//Source Success Update Case Validation
		UtilityCustomFunctions.logWriteConsole("CurrentUrl: after edit & save"+driver.getCurrentUrl());
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("current Source Record Id: after edit & save" + iCurrSrcRecId + "Old Source Record Id: after edit & save" + iOldSrcRecId);
		//Source Failure case Update While Edit & Save Record Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Update on Failure Case in @ Edit & Save Record", "pass", node);
//			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Edit & Save Failure Update Case ","No",node,false);
		}
		else {
			freport("Update on Failure Case in @ Edit & Save Record", "fail", node);
			
		}
		
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
				
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created for fail case @ Edit & Save Record", "pass", node);
		}
		else {
			freport("Target Entity Created for fail case @ Edit & Save Record", "fail", node);
		}
		
		//************** Single Line Summary Edit *****************
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
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
		Thread.sleep(3000);
		objCMD.setGenericInputValue("text", sExpSrcModuleName, "text", sEditIndText);
		Thread.sleep(3000);
		objDVP.clickRecItemSourceSave();
		Thread.sleep(3000);
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		String sActSummaryText = objDVP.getArraySummary(1);
		if(iOldSrcRecId==iCurrSrcRecId) {
			
			freport("Source Update on fail Case @ Single Line Edit", "pass", node);
//			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Single Line Edit Failure Update Case ","No",node,false);
			UtilityCustomFunctions.fSoftAssert(sActSummaryText, sEditIndText, "SourceEdit_SummPage@SingleLineEdit-Update on fail Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActSourceDTViewText = objDVP.getArrayDetails(6);
			UtilityCustomFunctions.fSoftAssert(sActSourceDTViewText, sEditIndText, "SourceEdit_DetailViewPage@SingleLineEdit-Update on fail Case", node);
		}
		else {
			freport("Source Update on fail Case @ Single Line Edit", "fail", node);
		}
				
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		Thread.sleep(2000);		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created  @ Single Line Edit", "pass", node);
		}
		else {
			freport("Target Entity Not Created  @ Single Line Edit", "fail", node);
		}
		
		objHP.clickLogoutCRM();
	}
	
}
