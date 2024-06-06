package testCases.ExecuteTask.Notification;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
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

public class TC009_NT_ET_OOFS_TimeAfterHours extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC009_NT_ET_OOFS_TimeAfterHours");
	}
	@Test
	public void testExecuteTask_TimeAfterHours () throws Exception {
		node = test.createNode("NT_ExecuteTask_TimeAfterHours");
		UtilityCustomFunctions.logWriteConsole("******starting TC009_NT_ET_OOFS_TimeAfterHours ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_TimeAfterHours_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_TimeAfterHours_Test" + ".xlsx" ;
		
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
		String sHours = xlObj.getCellData("Sheet1", 1, 33);
		String sSHours = xlObj.getCellData("Sheet2", 1, 33);
		String sMHours = xlObj.getCellData("Sheet3", 1, 33);
		String sEDHours = xlObj.getCellData("Sheet4", 1, 33);
		String sMinutes = xlObj.getCellData("Sheet1", 1, 34);
		String sSeconds = xlObj.getCellData("Sheet1", 1, 35);
		
		
		
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
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeAfterHours_","Sheet1",false,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeAfterHours_","Sheet1","Add New Validation", node);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sActExeStart_Time =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		Date d1 = null;
		d1 = format.parse(sActExeStart_Time);
		Date newDate = DateUtils.addMinutes(d1, 330);
		String sConfigDateTime = newDate.toString();
		
		int iExp_Hour = Integer.parseInt(sHours) + 1 + 12;
		
		String sExp_Hour = String.valueOf(iExp_Hour);
		if(iExp_Hour==24) {
			sExp_Hour="00";
		}
		String sExp_Time=sExp_Hour+":00:00";
		
		System.out.println("IST Act Execution Time: " + sConfigDateTime);
		System.out.println("IST Exp Execution Time: " + sExp_Time);
		if(sConfigDateTime.contains(sExp_Time)) {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added");
		}else {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@After New Record added");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		//********************************* Summary Add ************************************
		driver.get(rb.getString("appURL"));
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
		//****************************** Add Summary Data ********************************
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		
		objDVP.clickAddRecord();
		Thread.sleep(3000);
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeAfterHours_","Sheet2",false,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		sCurrModRecId = objCRMRs.getModuleRecordId();
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sActExeStart_Time =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		d1 = null;
		d1 = format.parse(sActExeStart_Time);
		newDate = DateUtils.addMinutes(d1, 330);
		sConfigDateTime = newDate.toString();
		 
		
		
		
		iExp_Hour = Integer.parseInt(sSHours) + 1 + 12;
		sExp_Hour = String.valueOf(iExp_Hour);
		if(iExp_Hour==24) {
			sExp_Hour="00";
		}
		sExp_Time=sExp_Hour+":00:00";
		
		
		System.out.println("IST Act Execution Time: " + sConfigDateTime);
		System.out.println("IST Exp Execution Time: " + sExp_Time);
		
		if(sConfigDateTime.contains(sExp_Time)) {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@@Summary Add New","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New");
		}else {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Actual Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Summary Add New","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time+ " EntityId: "+sCurrModRecId + "@Summary Add New");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		//********************************* Duplicate Add Record ************************************
		//Login as User 1
		driver.get(rb.getString("appURL"));
		
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
		//Click Duplicate & Add  New Record
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(1000);
		objDVP.clickDuplicateRecord();
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeAfterHours_","Sheet3",true,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		 
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sActExeStart_Time =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		d1 = null;
		d1 = format.parse(sActExeStart_Time);
		newDate = DateUtils.addMinutes(d1, 330);
		sConfigDateTime = newDate.toString();
		 
			
		iExp_Hour = Integer.parseInt(sMHours) + 1 + 12;
		if(iExp_Hour>=24) {
			iExp_Hour = 24 - iExp_Hour;
		}
		
		sExp_Hour = String.valueOf(iExp_Hour);
		
		
		sExp_Time=sExp_Hour+":00:00";
		
		System.out.println("IST Act Execution Time: " + sConfigDateTime);
		System.out.println("IST Exp Execution Time: " + sExp_Time);
		
		if(sConfigDateTime.contains(sExp_Time)) {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ iExp_Hour+":00:00" + " EntityId: "+sCurrModRecId + "@Duplicate Add New","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New");
		}else {
			objBase.freport("ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time after Hours With Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Hours With Actual Configured Time: " + sConfigDateTime + " Expected Time: "+ sExp_Time + " EntityId: "+sCurrModRecId + "@Duplicate Add New","ET Time after Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sExp_Time+ " EntityId: "+sCurrModRecId + "@Duplicate Add New");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
	}
}
