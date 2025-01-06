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

public class TC017_CreateEntity_OOFS_MultiSuccessCase extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("CreateEntity_OOFS_MultiSuccessCase");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	@Test
	public void testCreateEntity_OOFS_SuccessUpdate() throws Exception {
		node = test.createNode("CreateEntity_OOFS_MultiSuccessCase");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		UtilityCustomFunctions.logWriteConsole("******TC017_CreateEntity_OOFS_MultiSuccessCase ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_MultiSuccessCase" + "_Test.xlsx";
		
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		int iOldSrcRecId= objCRMRs.getLastRecordId();
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		
		Thread.sleep(2000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
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
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		int iOldTrg1RecId = objCRMRs.getLastRecordId();
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
		int iOldTrg2RecId = objCRMRs.getLastRecordId();
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
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

		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet1",false);
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
//		Thread.sleep(10000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		//Capture Record Ids
		int iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		
		//Navigate to source summary page 
	
		//Source Update Validation
		boolean IsTarget =false; 
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source Add on Create Entity Failure ", "fail", node);
		}
		else {
			freport("Source Add on Create Entity ", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet1","Create Entity Source","No",node,IsTarget);
		}
		
		//Validate in Target Entity
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
		int iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		driver.navigate().refresh();
		Thread.sleep(3000);
		//Target Record Validation
		IsTarget = true;
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created after New source data added", "fail", node);
		}
		else {
			freport("Target Entity Created after New source Added", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet1","Target Validation Create Entity","No",node,IsTarget);
		}
		//On Success Update Case on Various Other Targets
		//Navigate to first Target
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		int iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		driver.navigate().refresh();
		Thread.sleep(3000);
		Thread.sleep(3000);
		String sLeadSuccessEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module List Not Incremented After Create Entity Multi Success", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailSummary, sLeadDefaultCotent, "Lead Module Summary Page Email Validation", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead Module DT View Page Email Validation", node);
		}
		else {
			freport("Lead Module List Incremented After Create Entity Multi Success", "fail", node);
		}
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
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
			freport("Enquiry Module List Not Incremented After Create Entity Multi Success", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry Related Module Summary Page Name Validation", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqFNDTVArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqFNDTVArray[0].trim() + " " + sActEnqFNDTVArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry Module DT View Page Name Validation", node);
		}
		else {
			freport("Enquiry Module List Incremented After Create Entity Multi Success", "fail", node);
		}
		
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
		int iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		String sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sPNTitleArray[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		
		String sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module List Not Incremented After Create Entity Multi Success", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity Related Module Phone Number  Validation", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity Module DT View Page Phone Number Validation", node);
		}
		else {
			freport("Opportunity Module List Incremented After Create Entity Multi Success", "fail", node);
		}
		
	//Summary Add
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity Started");
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2",false);
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Record completed");
//		Thread.sleep(10000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		driver.navigate().refresh();
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Old Source Id:" + iOldSrcRecId);
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		
		//Source Update Validation
		IsTarget =false; 
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Create Entity-Summary Add-Source added", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","Create Entity Summary Add","No",node,IsTarget);
		}
		else {
			freport("Create Entity-Summary Add-Source not added", "fail", node);
		}
		
		//Validate in Target Entity
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		driver.navigate().refresh();
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Target Id:" + iCurrTrgRecId);
		IsTarget =true; 
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created after Summary Add", "fail", node);
		}
		else {
			freport("Target Entity Created after Summary Add", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","CE Target Validation Summary Add","No",node,IsTarget);
		}
		//On Success Update Case on Various Other Targets
		//Navigate to first Target
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		
		sLeadSuccessEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module List Not Incremented at Summary Add", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailSummary, sLeadDefaultCotent, "Lead_RelatedModule_SummPage@SummaryAdd-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead_RelatedModule_DetailViewPage@SummaryAdd-Multiple Success Case", node);
		}
		else {
			freport("Lead Module List Incremented at Summary Add", "fail", node);
		}
			
		//Navigate to EnquiryRelatedModule after Summary Add
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
		sActEnqFullName = objDVP.getArraySummary(1);
		String sActEnqFNSummaryAddArr[] = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqFNSummaryAddArr[0].trim() + " " + sActEnqFNSummaryAddArr[1].trim();
		
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented at Summary Add", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry_RelatedModule_SummPage@SummaryAdd-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			
			String sActEnqNameDTViewArr[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqNameDTViewArr[0].trim() + " " + sActEnqNameDTViewArr[1].trim();
			
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry_RelatedModule_DetailViewPage@SummaryAdd-Multiple Success Case", node);
		}
		else {
			freport("Enquiry Module List Incremented at Summary Add", "fail", node);
		}
		//Navigate to Opportunity Related Module	
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sOppPNTitleArray[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sOppPNTitleArray[0].trim() + " " + sOppPNTitleArray[1].trim();
		
		sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module Not Incremented at Summary Add", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity_RelatedModule_SummPage@SummaryAdd-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity_RelatedModule_DetailViewPage@SummaryAdd-Multiple Success Case", node);
		}
		else {
			freport("Opportunity Module Incremented at Summary Add", "fail", node);
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		objDVP.clickDuplicateRecord();
		Thread.sleep(5000);
		objCMD.clickSave();
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		driver.navigate().refresh();
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		//Source Update on Duplicate Record Validation
		
		IsTarget =false; 
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Source Add after Duplicate Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","CE Source @DuplicateRecord","No",node,IsTarget);
		}
		else {
			freport("Source Add after Duplicate Record", "fail", node);
		}
		
		//Validate in Target Entity
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id @duplicate record:" + iCurrTrgRecId);
		//Target Record Validation
		IsTarget =true;
		if(iOldTrgRecId!=iCurrTrgRecId) {
			freport("Target Add after Duplicate Record", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","CE Target @DuplicateRecord","No",node,IsTarget);
		}
		else {
			freport("Target Add after Duplicate Record", "fail", node);
		}
		
		//Other Multiple success Entity Validations
		//Navigate to LeadRelatedModule
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		sLeadSuccessEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module List Not Incremented at Duplicate Record", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailSummary, sLeadDefaultCotent, "Lead_RelatedModule_SummPage@DuplicateRecord-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead_RelatedModule_DetailViewPage@DuplicateRecord-Multiple Success Case", node);
		}
		else {
			freport("Lead Module List Incremented at DuplicateRecord", "fail", node);
		}
		//Navigate to EnquiryRelatedModule after Duplicate Record
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActEnqFullName = objDVP.getArraySummary(1);
		String sActEnqDuplArray[] = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqDuplArray[0].trim() + " " + sActEnqDuplArray[1].trim();
		
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented @Duplicate Record", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry_RelatedModule_SummPage@Duplicate Record-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqDTVDuplArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqDTVDuplArray[0].trim() + " " + sActEnqDTVDuplArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry_RelatedModule_DetailViewPage@Duplicate Record-Multiple Success Case", node);
		}
		else {
			freport("Enquiry Module List Incremented @Duplicate Record", "fail", node);
		}
		//Navigate to Opportunity Related Module	
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sOppPNArrayDupl[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sOppPNArrayDupl[0].trim() + " " + sOppPNArrayDupl[1].trim();
		
//		sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module Not Incremented @DuplicateRecord", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity_RelatedModule_SummPage@DuplicateRecord-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity_RelatedModule_DetailViewPage@DuplicateRecord-Multiple Success Case", node);
		}
		else {
			freport("Opportunity Module Incremented at DuplicateRecord", "fail", node);
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		System.out.println("Before Edit button clicked in summary view");
		objCMD.clickEdit();
 		Thread.sleep(6000);
		objCMD.clickSave();
		Thread.sleep(3000);
		
		UtilityCustomFunctions.checkPageLoadComplete();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		//Source Update on Edit & Save Record Validation
		
		IsTarget =false; 
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("Source Add after Edit & Save", "fail", node);
			
		}
		else {
			freport("Source Add after Edit & Save", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","CE Source @Edit & Save","No",node,IsTarget);
		}
		
		//Validate in Target Entity
		//get Target LastRecordId
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		driver.navigate().refresh();
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id @Edit & Save:" + iCurrTrgRecId);
		IsTarget =true;
		//Target Record Validation
		if(iOldTrgRecId!=iCurrTrgRecId) {
			freport("Target Entity @Edit & Save", "fail", node);
		}
		else {
			freport("Target Entity @Edit & Save", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_MultiSuccessCase_","Sheet2","CE Target @Edit & Save","No",node,IsTarget);
		}
		
		//Multiple Success Case
		//Navigate to LeadRelatedModule
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sLeadSuccessEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module List Not Incremented @Edit & Save", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailSummary, sLeadDefaultCotent, "Lead_RelatedModule_SummPage@Edit & Save-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead_RelatedModule_DetailViewPage@Edit & Save-Multiple Success Case", node);
		}
		else {
			freport("Lead Module List Incremented at @Edit & Save", "fail", node);
		}
		
		//Navigate to EnquiryRelatedModule after Edit & Save
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActEnqFullName = objDVP.getArraySummary(1);
		String sActEnqEandSArr[] = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqEandSArr[0].trim() + " " + sActEnqEandSArr[1].trim();
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented @Edit & Save", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry_RelatedModule_SummPage@Edit & Save-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqEandSDTVArr[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqEandSDTVArr[0].trim() + " " + sActEnqEandSDTVArr[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry_RelatedModule_DetailViewPage@Edit & Save-Multiple Success Case", node);
		}
		else {
			freport("Enquiry Module List Incremented @Edit & Save Record", "fail", node);
		}
		//Navigate to Opportunity Related Module	
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sOppPNArrayEandS[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sOppPNArrayEandS[0].trim() + " " + sOppPNArrayEandS[1].trim();
		
//				sExpOpportPhoneNo = sOpportPhonePrefix + " " + sOpportunityDefaultCotent; 
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module Not Incremented @Edit & Save", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity_RelatedModule_SummPage@Edit & Save-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity_RelatedModule_DetailViewPage@Edit & Save-Multiple Success Case", node);
		}
		else {
			freport("Opportunity Module Incremented at Edit & Save", "fail", node);
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
//		objDVP.clickEditRecordItem();
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
		UtilityCustomFunctions.fWaitNavLink(sExpSrcModuleName);;
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
			UtilityCustomFunctions.fSoftAssert(sActSummaryText, sEditIndText, "SourceUpdate_SummPage@SingleLineEdit-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActSourceDTViewText = objDVP.getArrayDetails(6);
			UtilityCustomFunctions.fSoftAssert(sActSourceDTViewText, sEditIndText, "SourceUpdate_DetailViewPage@SingleLineEdit-Multiple Success Case", node);
		}
		//Target Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sExpTrgModuleName);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		sActSummaryText = objDVP.getArraySummary(1);
		UtilityCustomFunctions.logWriteConsole("Curr Target Id @SingleLineEdit:" + iCurrTrgRecId);
		//Target Record Validation
		if(iOldTrgRecId!=iCurrTrgRecId) {
			freport("Target Entity @SingleLineEdit", "fail", node);
		}
		else {
			freport("Target Entity @SingleLineEdit", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActSummaryText, sSummaryAddText, "Target Summary Page@SingleLineEdit-Multiple Success Case", node);
		}
		//Multiple Success Case
		//Navigate to LeadRelatedModule
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule1);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule1);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		iCurrTrg1RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		Thread.sleep(3000);
		sLeadSuccessEmailSummary = objDVP.getArraySummary(2);
		if(iOldTrg1RecId == iCurrTrg1RecId) {
			freport("Lead Module List Not Incremented @SingleLineEdit", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailSummary, sLeadDefaultCotent, "Lead_RelatedModule_SummPage@SingleLineEdit-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sLeadSuccessEmailDTView = objDVP.getArrayDetails(3);
			UtilityCustomFunctions.fSoftAssert(sLeadSuccessEmailDTView, sLeadDefaultCotent, "Lead_RelatedModule_DetailViewPage@SingleLineEdit-Multiple Success Case", node);
		}
		else {
			freport("Lead Module List Incremented at @SingleLineEdit", "fail", node);
		}
				
		//Navigate to EnquiryRelatedModule after Single Line Edit
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule2);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule2);
		iCurrTrg2RecId = objCRMRs.getLastRecordId();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		Thread.sleep(3000);
		sActEnqFullName = objDVP.getArraySummary(1);
		String sActEnqFullNameArray[] = sActEnqFullName.split("\\s+");
		sActEnqFullName = sActEnqFullNameArray[0].trim() + " " + sActEnqFullNameArray[1].trim();
		
		sExpEnquiryFullName="";
		sExpEnquiryFullName = "Mr." + " " + sEnquiryDefaultCotent;
		if(iOldTrg2RecId == iCurrTrg2RecId) {
			freport("Enquiry Module List Not Incremented @SingleLineEdit", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActEnqFullName, sExpEnquiryFullName, "Enquiry_RelatedModule_SummPage@SingleLineEdit-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActEnqNameDTView = objDVP.getArrayDetails(2);
			String sActEnqFullNameDTVArray[] = sActEnqNameDTView.split("\\s+");
			sActEnqNameDTView = sActEnqFullNameDTVArray[0].trim() + " " + sActEnqFullNameDTVArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActEnqNameDTView, sExpEnquiryFullName, "Enquiry_RelatedModule_DetailViewPage@SingleLineEdit-Multiple Success Case", node);
		}
		else {
			freport("Enquiry Module List Incremented @SingleLineEdit Record", "fail", node);
		}
		//Navigate to Opportunity Related Module	
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sTarModule3);
		Thread.sleep(5000);
//		objCRMRs.fClickFirstRecord();
		objCMD.clickExistingModData(1);
		UtilityCustomFunctions.fWaitNavLink(sTarModule3);
		iCurrTrg3RecId = objCRMRs.getLastRecordId();
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		Thread.sleep(3000);
		sActOpportPhoneNo = objDVP.getArraySummary(3);
		String sOppPNArraySngLineEdit[] = sActOpportPhoneNo.split("\\s+");
		sActOpportPhoneNo = sOppPNArraySngLineEdit[0].trim() + " " + sOppPNArraySngLineEdit[1].trim();
		if(iOldTrg3RecId == iCurrTrg3RecId) {
			freport("Opportunity Module Not Incremented @SingleLineEdit", "pass", node);
			UtilityCustomFunctions.fSoftAssert(sActOpportPhoneNo, sExpOpportPhoneNo, "Opportunity_RelatedModule_SummPage@SingleLineEdit-Multiple Success Case", node);
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			String sActPhoneNoDTView = objDVP.getArrayDetails(4);
			String sPNOpprtArray[] = sActPhoneNoDTView.split("\\s+");
			sActPhoneNoDTView = sPNOpprtArray[0].trim() + " " + sPNOpprtArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActPhoneNoDTView, sExpOpportPhoneNo, "Opportunity_RelatedModule_DetailViewPage@SingleLineEdit-Multiple Success Case", node);
		}
		else {
			freport("Opportunity Module Incremented at SingleLineEdit", "fail", node);
		}
		
		objHP.clickLogoutCRM();
		

	}
	
	
}
