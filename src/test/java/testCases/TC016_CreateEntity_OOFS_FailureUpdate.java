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
		objCRMRs.fClickFirstRecord();
		int iOldSrcRecId= objCRMRs.getLastRecordId();
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
		objCRMRs.fClickFirstRecord();
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
//		objCRMRs.fClickFirstRecord();
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
			freport("Source update on Create Entity Failure ", "fail", node);
		}
		else {
			freport("Source update on Create Entity Failure ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Failure Update Add New Record","No",node);
		}
		
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
		objCRMRs.fClickFirstRecord();
		Thread.sleep(2000);
		int iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Old Target Id:" + iOldTrgRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created due to duplicate prevention in Target", "pass", node);
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist", "fail", node);
		}
		
		//Summary Add Record
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
			freport("Summary Add on Source in Create Entity Failure ", "fail", node);
		}
		else {
			freport("Summary Add on Source in Create Entity Failure ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_FailureUpdate_","Sheet5","Fail Case Update on Summary Add","No",node);
		}
		
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCRMRs.fClickFirstRecord();
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
		
		//Source Failure case Update While Duplicate Record Validation
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Update on Failure Case in Duplicate Record", "fail", node);
		}
		else {
			freport("Update on Failure Case in Duplicate Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet5","Duplicate Failure Update Case ","No",node);
		}
		
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCRMRs.fClickFirstRecord();
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity on Fail Case: OldRecordId: " + iOldTrgRecId + "Current Target RecId: " + iCurrTrgRecId, "pass", node);
		}
		else {
			freport("Target Entity on Fail Case: OldRecordId: " + iOldTrgRecId + "Current Target RecId: " + iCurrTrgRecId, "fail", node);
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
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		Thread.sleep(3000);
		
		//Source Failure case Update While Edit & Save Record Validation
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Update on Failure Case in Edit & Save Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet5","Edit & Save Failure Update Case ","No",node);
		}
		else {
			freport("Update on Failure Case in Edit & Save Record", "fail", node);
			
		}
		
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCRMRs.fClickFirstRecord();
		iCurrTrgRecId = objCRMRs.getLastRecordId();
				
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created while Edit & Save Record", "pass", node);
		}
		else {
			freport("Target Entity Not Created while Edit & Save Record", "fail", node);
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
		objDVP.clickEditRecordItem();
		Thread.sleep(1000);
		objCMD.setGenericInputValue("text", sExpSrcModuleName, "text", sEditIndText);
		objDVP.clickRecItemSave();
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Update on Failure Case in Single Line Edit Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet5","Single Line Edit Failure Update Case ","No",node);
		}
		else {
			freport("Update on Failure Case in Single Line Edit Record", "fail", node);
			
		}
				
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCRMRs.fClickFirstRecord();
		iCurrTrgRecId = objCRMRs.getLastRecordId();
				
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created while Single Line Edit Record", "pass", node);
		}
		else {
			freport("Target Entity Not Created while Single Line Edit Record", "fail", node);
		}
	}
	
}
