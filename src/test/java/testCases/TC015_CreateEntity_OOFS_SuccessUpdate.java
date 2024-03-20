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
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC015_CreateEntity_OOFS_SuccessUpdate extends BaseClass{
	@BeforeTest()
	public void CreateTest() {
		UtilityCustomFunctions.logWriteConsole("Extent Test Creation");
		test = extent.createTest("CreateEntity_OOFS_SuccessUpdate");
		UtilityCustomFunctions.logWriteConsole("Extent Test Created");
	}
	@Test
	public void testCreateEntity_OOFS_SuccessUpdate() throws Exception {
		node = test.createNode("CreateEntity_OOFS_SuccessUpdate");
		UtilityCustomFunctions.logWriteConsole("Extent Test Node Created");
		UtilityCustomFunctions.logWriteConsole("******TC015_CreateEntity_OOFS_SuccessUpdate ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		UtilityCustomFunctions.logWriteConsole("Browser:" + sBrowserName);
		String sPath=".\\testData\\CreateEntity\\" + "CreateEntity_OOFS_SuccessUpdate" + "_Test.xlsx";
		
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
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		DetailViewPage objDVP = new DetailViewPage(driver);
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
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		int iOldSrcRecId= objCRMRs.getLastRecordId();
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
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
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet1");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sDisplayMod1);
		Thread.sleep(5000);
		//Capture Record Ids
		int iCurrSrcRecId= objCRMRs.getLastRecordId();
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		Thread.sleep(2000);
		int iCurrTrgRecId = objCRMRs.getLastRecordId();
		//Target Record Validation
		if(iOldTrgRecId==iCurrTrgRecId) {
			freport("Target Entity Not Created after New source data added", "fail", node);
		}
		else {
			freport("Target Entity Created after Source Added", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet1","Create Entity in Target","No",node);
		}
		
		
		//Source Update Validation
		if(iOldSrcRecId==iCurrSrcRecId) {
			freport("Source update on Create Entity Success not Happened", "fail", node);
		}
		else {
			freport("Source update on Create Entity Success done", "pass", node);
			objCRMRs.fValidateEntityModuleSummary("Test", "//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet5","Success Update Case","No",node);
		}
		
		//***************Summary Add Create Entity ***********
		iOldTrgRecId=iCurrTrgRecId;
		iOldSrcRecId=iCurrSrcRecId;
		UtilityCustomFunctions.logWriteConsole("Summary Add Entity");
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod1);
		Thread.sleep(2000);
		objCMD.clickExistingModData(1);
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		objCRMRs.fAddValuestoEntityModule("Test","//CreateEntity//CreateEntity_OOFS_SuccessUpdate_","Sheet2");
		Thread.sleep(5000);
		//capture current Record Ids
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		iCurrSrcRecId= objCRMRs.getLastRecordId();
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayMod2);
		iCurrTrgRecId = objCRMRs.getLastRecordId();
		
		
		
		
		
		
	}
	
}
