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
//		objCRMRs.fClickFirstRecord();
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
//		objCRMRs.fClickFirstRecord();
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
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet1");
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
		
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source not Updated on Fail Case - Add New Record ", "fail", node);
		}
		else {
			freport("Source Updated on Fail Case - Add New Record ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiFailureCase_","Sheet6","Source Update on FailCase: Add New Record","No",node,false);
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
			freport("Target Entity Not Created due to duplicate prevention in Target", "pass", node);
			
		}
		else {
			freport("Target Entity Created even when duplicate prevention exist", "fail", node);
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
			freport("Lead Module Not Incremented After Add New Record on MultiUpdate on Fail Case", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadFailureEmailSummary, sLeadDefaultCotent, "Lead Module on Add New Record-Summary Record", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead Module on Add New Record-Detail View", node);
		}
		else {
			freport("Lead Module List Incremented After Create Entity Multi Update on Fail case", "fail", node);
		}
		
	}
	
}
