package testCases.ExecuteTask.Notification;


import java.time.Duration;
import java.util.Date;
import java.text.SimpleDateFormat;
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

public class TC046_NT_ET_ETRM_TimeAfterMinutes extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC046_NT_ET_ETRM_TimeAfterMinutes");
	}
	@Test
	public void testExecuteTask_TimeAfterMinutes () throws Exception {
		node = test.createNode("NT_ExecuteTask_TimeAfterMinutes");
		UtilityCustomFunctions.logWriteConsole("******starting TC046_NT_ET_ETRM_TimeAfterMinutes ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ETRM\\ET_NT_ETRM_TimeAfterMinutes_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ETRM\\ET_NT_ETRM_TimeAfterMinutes_Test" + ".xlsx" ;
		
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
		String sSMinutes = xlObj.getCellData("Sheet2", 1, 34);
		String sMMinutes = xlObj.getCellData("Sheet3", 1, 34);
		String sEDMinutes = xlObj.getCellData("Sheet4", 1, 34);
		
		String sSeconds = xlObj.getCellData("Sheet1", 1, 35);
		String sSSeconds = xlObj.getCellData("Sheet2", 1, 35);
		String sMSeconds = xlObj.getCellData("Sheet3", 1, 35);
		String sEDSeconds = xlObj.getCellData("Sheet4", 1, 35);
		
		
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
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ETRM//ET_NT_ETRM_TimeAfterMinutes_","Sheet1",false,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
//		objCRMRs.fVerifyETNotificationSummary("Test","//ExecuteTask//Notification//ETRM//ET_NT_ETRM_TimeAfterMinutes_","Sheet1","@Add New", node);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		bFlag = false;
		bFlag = objPAP.IsEntityInQueue(sMySqlUrl, sMySqlUid, sMySqlPwd,"rsoft_workflowtask_queue", sCurrModRecId);
		if(bFlag==false) {
		 objBase.freport("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New", "pass",node);
		 UtilityCustomFunctions.logWriteConsole("Passed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New");
		 BaseClass.sAssertinFn.assertEquals("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New");
		}
		else {
			objBase.freport("Actual EntityId: "+sCurrModRecId + " Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New", "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New");
			BaseClass.sAssertinFn.assertEquals("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Add New");
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
		
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ETRM//ET_NT_ETRM_TimeAfterMinutes_","Sheet2",false,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		bFlag = false;
		bFlag = objPAP.IsEntityInQueue(sMySqlUrl, sMySqlUid, sMySqlPwd,"rsoft_workflowtask_queue", sCurrModRecId);
		if(bFlag==false) {
		 objBase.freport("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add", "pass",node);
		 UtilityCustomFunctions.logWriteConsole("Passed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add");
		 BaseClass.sAssertinFn.assertEquals("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add");
		}
		else {
			objBase.freport("Actual EntityId: "+sCurrModRecId + " Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add", "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add");
			BaseClass.sAssertinFn.assertEquals("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Summary Add");
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
		
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ETRM//ET_NT_ETRM_TimeAfterMinutes_","Sheet3",true,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
		
		sCurrModRecId = objCRMRs.getModuleRecordId();
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
		
		bFlag = false;
		bFlag = objPAP.IsEntityInQueue(sMySqlUrl, sMySqlUid, sMySqlPwd,"rsoft_workflowtask_queue", sCurrModRecId);
		if(bFlag==false) {
		 objBase.freport("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record", "pass",node);
		 UtilityCustomFunctions.logWriteConsole("Passed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record");
		 BaseClass.sAssertinFn.assertEquals("Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record");
		}
		else {
			objBase.freport("Actual EntityId: "+sCurrModRecId + " Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record", "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record");
			BaseClass.sAssertinFn.assertEquals("Failed: Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record","Actual EntityId: "+sCurrModRecId + " Not Created in DB for Every Time Record Modifies condition for TimeAfterMinutes Task @Duplicate Record");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		//********************************** Edit & Save ********************************** 
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
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(3000);
		objCMD.clickEdit();
		Thread.sleep(3000);
		objCRMRs.fAddValuestoETBySpecificValues("Test","//ExecuteTask//Notification//ETRM//ET_NT_ETRM_TimeAfterMinutes_","Sheet4",true,"sTime");
		UtilityCustomFunctions.logWriteConsole("New Record added in: "+sExpModuleName);
		Thread.sleep(2000);
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
		int iHours = 0;
		if(sAMorPM.equalsIgnoreCase("AM")){
			sAfterMinutes = "0" + sEDHours + ":45:00";
		}
		else {
			iHours = Integer.parseInt(sEDHours) + 12;
			sAfterMinutes = String.valueOf(iHours)+":45:00";
		}
		if(sConfigDateTime.contains(sAfterMinutes)) {
			objBase.freport("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save","ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save");
		}else {
			objBase.freport("ET Time sAfterMinutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save","ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Edit & Save");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		//****************************** Single Line Summary Edit  ****************************
		driver.get(rb.getString("appURL"));
		Thread.sleep(3000);
		//Login
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
		UtilityCustomFunctions.logWriteConsole("Before Single LIne Edit: Last Task Id: "+sCurrTaskId+" current record id "+ sCurrModRecId);
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sDisplayModuleName);
		System.out.println("Module clicked");
		Thread.sleep(3000);
		objCMD.clickExistingModData(1);
		Thread.sleep(5000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Mobile Number Edit On in Summary Page");
		objDVP.clickEditNotificationItem();
		Thread.sleep(3000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "mobilenumber", sEditIndText);
		objDVP.clickNotifyRecItemSave(sExpModuleName,"mobilenumber");
		UtilityCustomFunctions.logWriteConsole(sEditIndText + " Is Updated Text for Mobile Number@Single Line Edit");
		Thread.sleep(5000);
				
		objSVP.fWaitTillControlVisible();
		driver.navigate().refresh();
		Thread.sleep(3000);
		
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
//		sAfterMinutes = "0" + sEDHours + ":45:00";
		
		formatDate = new SimpleDateFormat("hh:mm:ss a");
		formattedDate = formatDate.format(new Date()).toString();
		System.out.println(formattedDate);
		sAMorPM = formattedDate.substring(Math.max(formattedDate.length()-2, 0));
		System.out.println("AM OR PM " + sAMorPM);
		sAfterMinutes ="";
		iHours = 0;
		if(sAMorPM.equalsIgnoreCase("AM")){
			sAfterMinutes = "0" + sEDHours + ":45:00";
		}
		else {
			iHours = Integer.parseInt(sEDHours) + 12;
			sAfterMinutes = String.valueOf(iHours)+":45:00";
		}
		
		if(sConfigDateTime.contains(sAfterMinutes)) {
			objBase.freport("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit" , "pass",node);
			UtilityCustomFunctions.logWriteConsole("Passed: ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit"); 
			BaseClass.sAssertinFn.assertEquals("ET Time after Minutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit");
		}else {
			objBase.freport("ET Time sAfterMinutes With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit" , "fail",node);
			UtilityCustomFunctions.logWriteConsole("Failed: ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit"); 
			BaseClass.sAssertinFn.assertEquals("ET Time before Hours With Actual Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit","ET Time before Hours With Configured Time: " + sConfigDateTime + "Expected Time: "+ sAfterMinutes + " EntityId: "+sCurrModRecId + "@Single Line Edit");
		}
		
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		
		
	}//Test
	
	
	
}
