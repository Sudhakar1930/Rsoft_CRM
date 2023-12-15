package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.AllModuleValues;
import pageObjects.CreateModuleDataPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC002_WF2_Only_On_First_Save extends BaseClass{

	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC002_WF2_Only_On_First_Save");
	}
	@Test
	public void testWFEveryTimeRecSave () throws Exception {
		node = test.createNode("WF2_Only_On_First_Save");
		
		logger.info("******starting WF2_Only_On_First_Save ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Live" + ".xlsx" ;
		String sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test_Add" + ".xlsx" ;
		
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
//		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
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
		String sEnquiry_Name=xlObj.getCellData("Sheet1", 1, 23);
		String sEnquiry_Email=xlObj.getCellData("Sheet1", 1, 24);
		String sEnquiry_Text=xlObj.getCellData("Sheet1", 1, 25);
		String sEnquiry_TextArea=xlObj.getCellData("Sheet1", 1, 26);
		String sEnquiry_Date=xlObj.getCellData("Sheet1", 1, 27);
		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 28);
		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 29);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 30);
		String sActionType=xlObj.getCellData("Sheet1", 1, 31);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 32);
		String sRecordId=xlObj.getCellData("Sheet1", 1, 33);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 34);
		
		sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();  
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName1");
		String sPassword1 =  rb.getString("passWord1");
		String sAssignedTo1 = rb.getString("AssignedTo1");
		String sUserName2 =  rb.getString("userName2");
		String sPassword2 =  rb.getString("passWord2");
		String sAssignedTo2 = rb.getString("AssignedTo2");
		
		
		
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		
//		if(objHP.isAvatarDisplayed()) {
//			freport("Home Page Displayed after Login" , "pass",node);
////			objHP.clickAvatar();
//		}
//		else {
//			logger.info("Home Page Not Displayed");
//			freport("Home Page Not Displayed" , "fail",node);
//			System.out.println("Home Page Not Displayed");
//			Assert.fail("Home Page Not Displayed");
//		}
		
//		Thread.sleep(1000);
//		objCRMRs.fNavigatetoWorkflow(sExpModuleName);
//		String sWorkFlowStatus="";
//		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sExpModuleName, sExpWorkFlowName, sExecutionCondition);
//		String sWFStatusRetArr[] = sWorkFlowStatus.split(":");
//		xlObj.setCellData("Sheet1", 1, 33, sWFStatusRetArr[1]);
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
//			
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
//			
//		}//If workflow Enabled but Task not set
		Thread.sleep(2000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		Thread.sleep(1000);
		objCRMRs.fAddValuestoModulePage("Test");
		
		objCRMRs.fValidateAllFields("Test", "Add","After New Record added",node);
		
		
		
		
	}//@Test
}//class	
