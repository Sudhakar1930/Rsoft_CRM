package testCases.ExecuteTask.Notification;

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

public class TC001_NT_ExecuteTask_CTAfterMinutes extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC001_NT_ExecuteTask_CTAfterMinutes");
	}
	
	@Test
	public void testExecuteTask_NT_CTAfterMinutes () throws Exception {
		node = test.createNode("NT_ExecuteTask_CTAfterMinutes");
		UtilityCustomFunctions.logWriteConsole("******starting TC001_NT_ExecuteTask_CTAfterMinutes ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_CTAfterMinutes_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_CTAfterMinutes_Test" + ".xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlObj.getRowCount("Sheet1");
		UtilityCustomFunctions.logWriteConsole("Total rows: " + iRowCount);
			
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		UtilityCustomFunctions.logWriteConsole("Cols Count: " + iColCount);
		
		UtilityCustomFunctions.logWriteConsole("Extracting DataSheet Values started...");
		
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
//		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 24);
		String sActionType=xlObj.getCellData("Sheet1", 1, 25);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 26);
		String sDisplayModuleName=xlObj.getCellData("Sheet1", 1, 29);

		String sEditIndText=xlObj.getCellData("Sheet1", 1, 30);
		String sNotifyTemplateMsg=xlObj.getCellData("Sheet1", 1, 31);
		
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		DetailViewPage objDVP = new DetailViewPage(driver);
		SummaryViewPage objSVP = new SummaryViewPage(driver);

		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName4");
		String sPassword1 =  rb.getString("passWord4");
		String sAssignedTo1 = rb.getString("AssignedTo4");
		String sUserName2 =  rb.getString("userName2");
		String sPassword2 =  rb.getString("passWord2");
		String sAssignedTo2 = rb.getString("AssignedTo2");
		
		//Login as User 1
		Thread.sleep(3000);
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
		
		if(objHP.isAvatarDisplayed()) {
//			freport("Home Page Displayed after Login" , "pass",node);
//			objHP.clickAvatar();
		}
		else {
			logger.info("Home Page Not Displayed");
			freport("Home Page Not Displayed" , "fail",node);
			System.out.println("Home Page Not Displayed");
			Assert.fail("Home Page Not Displayed");
			
		}
		Thread.sleep(1000);
		objCRMRs.fNavigatetoWorkflow(sDisplayModuleName);
		String sWorkFlowStatus="";
		
		
		sWorkFlowStatus = objCRMRs.IsCheckWorkflowStatus(sDisplayModuleName, sExpWorkFlowName, sExecutionCondition);
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
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		
//		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sExpModuleName);
//		Thread.sleep(1000);
//		objEDT.clickModule(sExpModuleName);
//		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sExpModuleName);
		objCRMRs.fClickFirstRecord();
		Thread.sleep(2000);
////		objCRMRs.fAddValuestoETNotification("Test","//ExecuteTask//Notification//ET_NT_OOFS_CTAfterMinutes_","Sheet1",false);
//		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ET_NT_OOFS_CTAfterMinutes_","Sheet1","New Add Data in Summary Verification", node);
		
		
		
		
	}
	
}
