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

public class TC012_NT_ExecuteTask_TimeBeforeMinutes extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC012_NT_ExecuteTask_TimeBeforeMinutes");
	}
	@Test
	public void testExecuteTask_TimeBeforeMinutes () throws Exception {
		node = test.createNode("NT_ExecuteTask_TimeBeforeMinutes");
		UtilityCustomFunctions.logWriteConsole("******starting TC012_NT_ExecuteTask_TimeBeforeMinutes ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_TimeBeforeMinutes_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_TimeBeforeMinutes_Test" + ".xlsx" ;
		
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
		
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeBeforeMinutes_","Sheet1",false,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
//		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeBeforeMinutes_","Sheet1","@Add New", node);
		
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
		//Config datetime:Fri May 10 05:45:00 IST 2024
		
		
		SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm:ss a");
		String formattedDate = formatDate.format(new Date()).toString();
		System.out.println(formattedDate);
		String sAMorPM = formattedDate.substring(Math.max(formattedDate.length()-2, 0));
		System.out.println("AM or PM : " + sAMorPM);
		
		String sAfterMinutes ="";
		int iHours = Integer.parseInt(sHours) - 1;
		if(sAMorPM.equalsIgnoreCase("AM")){
			sAfterMinutes = "0" + String.valueOf(iHours) + ":15:00";
		}
		else {
			iHours = iHours + 12;
			sAfterMinutes = String.valueOf(iHours)+":15:00";
		}
		
		if(sConfigDateTime.contains(sAfterMinutes)) {
			objBase.freport("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New");
		}else {
			objBase.freport("ET Time sAfterMinutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time before Hours With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@After Add New");
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
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeBeforeMinutes_","Sheet2",false,"sTime");
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
		System.out.println("Config datetime:" + sConfigDateTime);
		//Config datetime:Fri May 10 05:45:00 IST 2024
		
		formatDate = new SimpleDateFormat("hh:mm:ss a");
		formattedDate = formatDate.format(new Date()).toString();
		System.out.println(formattedDate);
		sAMorPM = formattedDate.substring(Math.max(formattedDate.length()-2, 0));
		System.out.println("AM or PM : " + sAMorPM);
		
		sAfterMinutes ="";
		iHours=0;
		iHours = Integer.parseInt(sSHours) - 1;
		if(sAMorPM.equalsIgnoreCase("AM")){
			sAfterMinutes = "0" + String.valueOf(iHours) + ":15:00";
		}
		else {
			iHours = iHours + 12;
			sAfterMinutes = String.valueOf(iHours)+":15:00";
		}
		
		if(sConfigDateTime.contains(sAfterMinutes)) {
			objBase.freport("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New");
		}else {
			objBase.freport("ET Time sAfterMinutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New"); 
			BaseClass.sAssertinFn.assertEquals("ET Time before Hours With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Summary Add New");
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
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ET_NT_OOFS_TimeBeforeMinutes_","Sheet3",true,"sTime");
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
		System.out.println("Config datetime:" + sConfigDateTime);
		//Config datetime:Fri May 10 05:45:00 IST 2024
		
		formatDate = new SimpleDateFormat("hh:mm:ss a");
		formattedDate = formatDate.format(new Date()).toString();
		System.out.println(formattedDate);
		sAMorPM = formattedDate.substring(Math.max(formattedDate.length()-2, 0));
		System.out.println("AM or PM : " + sAMorPM);
		
		sAfterMinutes ="";
		iHours=0;
		iHours = Integer.parseInt(sMHours) - 1;
		if(sAMorPM.equalsIgnoreCase("AM")){
			sAfterMinutes = "0" + String.valueOf(iHours) + ":15:00";
		}
		else {
			iHours = iHours + 12;
			sAfterMinutes = String.valueOf(iHours)+":15:00";
		}
		
		
		
		if(sConfigDateTime.contains(sAfterMinutes)) {
			objBase.freport("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record");
		}else {
			objBase.freport("ET Time sAfterMinutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record"); 
			BaseClass.sAssertinFn.assertEquals("ET Time before Hours With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Duplicate Record");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		
	
	}//Test
}
