package testCases.ExecuteTask.Notification;


import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;

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

public class TC013_NT_ET_OOFS_DTAfterMinutes extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC013_NT_ET_OOFS_DTAfterMinutes");
	}
	@Test
	public void testExecuteTask__DTAfterMinutes () throws Exception {
		node = test.createNode("NT_ExecuteTask_DTAfterMinutes");
		UtilityCustomFunctions.logWriteConsole("******starting TC013_NT_ET_OOFS_DTAfterMinutes ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_DTAfterMinutes_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_DTAfterMinutes_Test" + ".xlsx" ;
		
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
		
		String sMinutes = xlObj.getCellData("Sheet1", 1, 34);
		String sSMinutes = xlObj.getCellData("Sheet2", 1, 34);
		String sMMinutes = xlObj.getCellData("Sheet3", 1, 34);
		
		String sSeconds = xlObj.getCellData("Sheet1", 1, 35);
		String sSSeconds = xlObj.getCellData("Sheet2", 1, 35);
		String sMSeconds = xlObj.getCellData("Sheet3", 1, 35);
		
		
		
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
		
		UtilityCustomFunctions.logWriteConsole("Click Module:"+sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sExpModuleName);
		Thread.sleep(2000);
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_DTAfterMinutes_","Sheet1",false,"sDateandTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
//		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ET_NT_OOFS_DTAfterMinutes_","Sheet1","@Add New", node);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sActExeStart_Time =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		Date d1 = null;
		d1 = format.parse(sActExeStart_Time);
		Date newDate = DateUtils.addMinutes(d1, 330);
		String sConfigDateTime = newDate.toString();
		System.out.println("Config datetime:" + sConfigDateTime);
		SimpleDateFormat sActualFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		SimpleDateFormat sTargetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sExpConvFromFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
		Date date = sActualFormat.parse(sConfigDateTime);
		
		String sActualValue = sTargetFormat.format(date);
		System.out.println("Actual Formatted Date & Time" + sActualValue);
		String sDateandTime = xlObj.getCellData("Sheet1", 1, 13);
		Date dExpDate = sExpConvFromFormat.parse(sDateandTime);
		dExpDate = DateUtils.addMinutes(dExpDate, 765);
		String sExpDate = sTargetFormat.format(dExpDate);
		System.out.println("Expected Formatted Date & Time" + sExpDate);

		if(sActualValue.equalsIgnoreCase(sExpDate)) {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New"); 
			BaseClass.sAssertinFn.assertEquals("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New");
		}else {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New"); 
			BaseClass.sAssertinFn.assertEquals("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Add New");
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
		
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_DTAfterMinutes_","Sheet2",false,"sDateandTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
//		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ET_NT_OOFS_DTAfterMinutes_","Sheet1","@Add New", node);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sActExeStart_Time =objPAP.fGetExecutionStartTime(sMySqlUrl,sMySqlUid,sMySqlPwd,"rsoft_workflowtask_queue",sCurrModRecId);
		d1 = null;
		d1 = format.parse(sActExeStart_Time);
		newDate = DateUtils.addMinutes(d1, 330);
		sConfigDateTime = newDate.toString();
		System.out.println("Config datetime:@summary add" + sConfigDateTime);
		sActualFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		sTargetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sExpConvFromFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
		date = sActualFormat.parse(sConfigDateTime);
		
		sActualValue = sTargetFormat.format(date);
		System.out.println("Actual Formatted Date & Time" + sActualValue);
		sDateandTime = xlObj.getCellData("Sheet2", 1, 13);
		dExpDate = sExpConvFromFormat.parse(sDateandTime);
		dExpDate = DateUtils.addMinutes(dExpDate, 765);
		sExpDate = sTargetFormat.format(dExpDate);
		System.out.println("Expected Formatted Date & Time" + sExpDate);

		if(sActualValue.equalsIgnoreCase(sExpDate)) {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add"); 
			BaseClass.sAssertinFn.assertEquals("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add");
		}else {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add"); 
			BaseClass.sAssertinFn.assertEquals("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Summary Add");
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
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_DTAfterMinutes_","Sheet3",true,"sDateandTime");
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
		System.out.println("Config datetime:@summary add" + sConfigDateTime);
		sActualFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		sTargetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sExpConvFromFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
		date = sActualFormat.parse(sConfigDateTime);
		
		sActualValue = sTargetFormat.format(date);
		System.out.println("Actual Formatted Date & Time" + sActualValue);
		sDateandTime = xlObj.getCellData("Sheet3", 1, 13);
		dExpDate = sExpConvFromFormat.parse(sDateandTime);
		dExpDate = DateUtils.addMinutes(dExpDate, 765);
		sExpDate = sTargetFormat.format(dExpDate);
		System.out.println("Expected Formatted Date & Time" + sExpDate);
		
		if(sActualValue.equalsIgnoreCase(sExpDate)) {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add"); 
			BaseClass.sAssertinFn.assertEquals("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add");
		}else {
			objBase.freport("Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add"); 
			BaseClass.sAssertinFn.assertEquals("Failed: Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add","Execute Task Notification Date&Time after Minutes Actual Value: " + sActualValue + "Expected Value: "+ sExpDate + " EntityId: "+sCurrModRecId + "@Duplicate Add");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		
		
		
		
		
	}//Test
	
	
	
}//class
