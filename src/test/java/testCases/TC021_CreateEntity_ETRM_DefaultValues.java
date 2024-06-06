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

public class TC021_CreateEntity_ETRM_DefaultValues extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("TC021_CreateEntity_ETRM_DefaultValues");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	@Test
	public void testCreateEntity_ETRM_DefaultValues() throws Exception {
		node = test.createNode("CreateEntity_ETRM_DefaultValues");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		
		logger.info("******starting TC021_CreateEntity_ETRM_DefaultValues ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
//		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_ETRM_DefaultValues" + "_Live.xlsx" ;
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_ETRM_DefaultValues" + "_Test.xlsx";
	
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
		UtilityCustomFunctions.logWriteConsole("Capture Initial Record Id for Source & Target");
		int iOldSrcRecId;
		int iCurrSrcRecId;
		int iOldTrgRecId;
		int iCurrTrgRecId;
		
		objALP.clickAllList();
		Thread.sleep(2000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		objCMD.clickExistingModData(1);
		iOldSrcRecId= objCRMRs.getLastRecordId();
		Thread.sleep(5000);
		
		Thread.sleep(5000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		objSVP.fWaitTillControlVisible();
		iOldTrgRecId= objCRMRs.getLastRecordId();
		

		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sDisplayMod1);
		Thread.sleep(1000);
		objEDT.clickModule(sDisplayMod1);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sDisplayMod1);
		Thread.sleep(2000);
		
		Thread.sleep(2000);
//		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet1",false);
		objCRMRs.fAddMandatoryValuestoEntityModule("Test","//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet1");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
		Thread.sleep(5000);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("New Source Record Added @AddNew @ETRM default Values", "pass", node);
			
		}
		else {
			freport("New Source Record Not Added @AddNew @ETRM default Values", "fail", node);
			
		}
		// Check Target Count
		objALP.clickAllList();
		UtilityCustomFunctions.logWriteConsole("All Menu Items Clicked");
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.logWriteConsole("Target Module Opened:"+ sDisplayMod2);
		Thread.sleep(1000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		iCurrTrgRecId= objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Record Id comparison after add new record @ETRM");
		if(iOldTrgRecId == iCurrTrgRecId) {
			freport("Target Entity Not Created for @Add New @ETRM Default Values", "pass", node);
		}
		else {
			freport("Target Entity Created for @Add New @ETRM Default Values", "fail", node);
		}
		//************************************ Summary Add ********************************
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
		Thread.sleep(3000);
		//Summary Add Mandatory values.
		objCRMRs.fAddMandatoryValuestoEntityModule("Test","//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet2");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Summary Add Record completed");
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		
		//Capture Record Ids
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		
		UtilityCustomFunctions.logWriteConsole("Curr Source Id:" + iCurrSrcRecId);
		//Source Update Validation
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("@Summary Add New Source record added @ETRM default Values", "pass", node);
			
		}
		else {
			freport("@Summary Add New Source record not added @ETRM default Values", "fail", node);
			
		}
		//Summary Add Target Entity Validation
		Thread.sleep(3000);
		objALP.clickAllList();
		UtilityCustomFunctions.logWriteConsole("All Menu Items Clicked");
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		UtilityCustomFunctions.logWriteConsole("Target Module Opened:"+sDisplayMod2);
		Thread.sleep(5000);
		objCMD.clickExistingModData(1);
		Thread.sleep(2000);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Record Id comparison started after new Record Added");
		UtilityCustomFunctions.logWriteConsole("Old TargetId: "+iOldTrgRecId +"New TargetId: "+iCurrTrgRecId);
		if(iOldTrgRecId != iCurrTrgRecId) {
			freport("Target Entity Created @Summary Add New at @ETRM default Values", "fail", node);
			
			}
		else {
			freport("Target Entity not Created @Summary Add New at @ETRM default Values", "pass", node);	
		}
		UtilityCustomFunctions.logWriteConsole("Created Entity Validation for Summary Add Done");
		Thread.sleep(3000);
		
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
		objCRMRs.fAddMandatoryValuestoEntityModule("Test","//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet3");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Duplicate Add Record completed");
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(1000);
		objSVP.fWaitTillControlVisible();
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
						
		//While Duplicate Record Validation
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		if(iOldSrcRecId!=iCurrSrcRecId) {
			freport("@Duplicate Source record added @ETRM by default Values", "pass", node);
			
		}
		else {
			freport("@Duplicate Source record added @ETRM by default Values", "fail", node);
			
		}
		
		//Target Entity Validation
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
				
		UtilityCustomFunctions.logWriteConsole("Old TargetId: "+iOldTrgRecId +"New TargetId: "+iCurrTrgRecId);
		if(iOldTrgRecId != iCurrTrgRecId) {
			freport("Target Entity Created after Record Duplicated @ETRM default Values", "fail", node);
			
			}
		else {
			freport("Target Entity not Created after Record Duplicated @ETRM default Values", "pass", node);	
		}
		UtilityCustomFunctions.logWriteConsole("Created Entity Validation for Duplicate Record Done");
		Thread.sleep(3000);
		
		//**************************** Edit & Save ****************************************

		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(3000);
		objCMD.clickEdit();
		Thread.sleep(3000);
		objCRMRs.fAddMandatoryValuestoEntityModule("Test","//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet4");
		UtilityCustomFunctions.logWriteConsole("Edit & Save: "+sDisplayMod1);
		Thread.sleep(5000);
		UtilityCustomFunctions.checkPageLoadComplete();
		Thread.sleep(10000);
		
		//Target Entity Validation after @Edit & Save Record
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		UtilityCustomFunctions.logWriteConsole("Old TargetId: "+iOldTrgRecId +"New TargetId: "+iCurrTrgRecId);
		if(iOldTrgRecId != iCurrTrgRecId) {
			objCRMRs.fValidateEntityResponse("Test", "//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet5","Target Entity @Edit & Save","No",node,true);
			freport("Target Entity Created @Edit & Save @ETRM with Default Values", "pass", node);
			}
		else {
			freport("Target Entity not Created after Record @Edit & Save @ETRM with Default Values", "fail", node);	
		}
		UtilityCustomFunctions.logWriteConsole("Created Entity after @Edit & Save Done");

		//******************* Edit Single Line Summary *************************
		
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
		
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		Thread.sleep(1000);
		if(iOldTrgRecId== iCurrTrgRecId) {
			freport("Target Entity Not Created for @Single Line Edit @ETRM", "fail", node);
		}else {
			objCRMRs.fValidateEntityResponse("Test", "//CreateEntity//CreateEntity_ETRM_DefaultValues_","Sheet5","Target Entity @Single Line Edit","No",node,true);
			freport("Target Entity Created for @Single Line Edit @ETRM", "pass", node);
		}
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		
	}//Test
}
