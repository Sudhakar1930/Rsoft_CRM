package testCases.ExecuteTask.Notification;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.AllModuleValues;
import pageObjects.CreateModuleDataPage;
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PHPMyAdminPage;
import pageObjects.SummaryViewPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC026_NT_ET_ETRS_DateBeforeDays extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC026_NT_ET_ETRS_DateBeforeDays");
	}
	@Test
	public void testExecuteTask_DateBeforeDays () throws Exception {
		node = test.createNode("NT_ExecuteTask_DateAfterDays");
		UtilityCustomFunctions.logWriteConsole("******starting TC026_NT_ET_ETRS_DateBeforeDays ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ETRS\\ET_NT_ETRS_DateAfterDays_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ETRS\\ET_NT_ETRS_DateAfterDays_Test" + ".xlsx" ;
		
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
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		BaseClass objBase = new BaseClass();
		
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
		String sMySqlUid = rb.getString("MySqlUid");
		String sMySqlPwd = rb.getString("MySqlPwd");
		String sMySqlUrl= rb.getString("MySqlUrl");
		

		boolean bFlag = false;
		boolean isEntity = false;
		String sCurrModRecId ="";
		String sOldModRecId="";
		String sMySqlQuery = "";
		String sCurrTaskId="";
		String sReturnValue="";
		String sCreatedTime="";
		String sModifiedTime="";
		String sRetrieveCTandMT="";
		String sRetArr[];
		String arrPhpRetArray[];
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
		
		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sExpModuleName);
		Thread.sleep(2000);
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ETRS//ET_NT_ETRS_DateBeforeDays_","Sheet1",false,"sDate");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ETRS//ET_NT_ETRS_DateBeforeDays_","Sheet1","@Add Mew", node);
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		String sActExeStart_Date =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		String sExecFrom_Date=xlObj.getCellData("Sheet1", 1, 10);
		System.out.println("Date as is: " + sActExeStart_Date + " " + sExecFrom_Date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dDBDate = formatter.parse(sActExeStart_Date);
		formatter.applyPattern("dd-MM-yyyy");
		String sExeStartDate = formatter.format(dDBDate);
		System.out.println("Execution Start Date" + sExeStartDate);
		System.out.println("Execution From Date" + sExecFrom_Date);
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		Date d1 = null;
		Date d2 = null;
		d1 = format1.parse(sExeStartDate);
		d2 = format1.parse(sExecFrom_Date);
		
		long numberOfDays = d2.getTime() - d1.getTime();
		long days = numberOfDays/(1000*60*60*24); 
		System.out.println("Number of Days: " + days);
		
		 if(days==1) {
			 objBase.freport("Date Selected: "+ d2 +" And Execution Start Date: "+ d1 +" Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added" , "pass",node);
			 UtilityCustomFunctions.logWriteConsole("Date Selected: "+ d2 +" And Execution Start Date: "+ d1 +"Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added"); 
			 BaseClass.sAssertinFn.assertEquals("Date Selected: "+ d2 +" And Execution Start Date: "+d1 +"Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added","Date Selected: "+ d1 +" And Execution Start Date: "+d2 +"Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added");
		 }else {
			 objBase.freport("Date Selected: "+ d2 +" And Execution Start Date: "+d1 +"Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added" , "fail",node);
			 UtilityCustomFunctions.logWriteConsole("Failed: "+"Date Selected: "+ d2 +" And Execution Start Date: "+d1 +"Execute Task Notification Days before given Date is: "+days +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added"); 
			 BaseClass.sAssertinFn.assertEquals("Failed: "+ "Date Selected: "+ d2 +" And Execution Start Date: "+d1 +"Execute Task Notification Days before given Date is: "+days +" days and EntityId: "+sCurrModRecId + "@After New Record added","Execute Task Notification Days before given Date is: "+numberOfDays +" days and "+ "EntityId: "+sCurrModRecId + "@After New Record added"); 
		 }
		  objLP.clickPHPMyAdminLogout();
		  Thread.sleep(1000);
		
		
		
	}//Test
}
