package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import pageObjects.LoginPage;
import pageObjects.PHPMyAdminPage;

import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC008_WF2_Validate_SN_Daily extends BaseClass {
	
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC008_WF2_Validate_SN_Daily");
	}
	
	@Test
	public void testDatainPhpMyAdmin () throws Exception {
		node = test.createNode("Validate_SN_Daily");
		
		logger.info("******starting WF Schedule Notify Daily ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
//		String sPath=".\\testData\\Schedule\\" + "Schedule_Notify_Daily" + "_Live.xlsx" ;
		String sPath=".\\testData\\Schedule\\" + "Schedule_Notify_Daily" + "_Test.xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);	
		logger.info("Extracting DataSheet Values started...");
		
		LoginPage objLP = new LoginPage(driver);
		
		
		String sUser1RecordId=xlObj.getCellData("Sheet1", 1, 39);
		String sMySqlUid = rb.getString("MySqlUid");
		String sMySqlPwd = rb.getString("MySqlPwd");
		String sMySqlUrl= rb.getString("MySqlUrl");
		UtilityCustomFunctions.logWriteConsole("uid" + sMySqlUid);
		UtilityCustomFunctions.logWriteConsole("pwd" + sMySqlPwd);
		UtilityCustomFunctions.logWriteConsole("url" + sMySqlUrl);
		
		System.out.println(sMySqlUrl);
		driver.get(sMySqlUrl);
		Thread.sleep(3000);
		objLP.setMySqlUserId(sMySqlUid);
		objLP.setMySqlPasswd(sMySqlPwd);
		objLP.clickMySqlSubmit();
		Thread.sleep(3000);
//		objPAP.clickDB();
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		
		
		
		objPAP.clickDBLink();
		Thread.sleep(3000);
		objPAP.setTableInDB("rsoft_workflowtask_queue");
		Thread.sleep(3000);
		objPAP.clickaTableLink();
		Thread.sleep(3000);
		objPAP.aNavigtoLastPage();
		Thread.sleep(3000);
//		objPAP.clickaEditInLine();
//		objPAP.clickaSqlWindow();
//		Thread.sleep(3000);
//		objPAP.clickQueryTab();
		
//		String sMySqlQuery = "SELECT * FROM `rsoft_workflowtask_queue` where `entity_id` ="+ sUser1RecordId + ";";
//		objPAP.setQuery(sMySqlQuery);
//		Thread.sleep(3000);
//		objPAP.jSTypeValue(driver, sMySqlQuery);
		
//		objPAP.setQueryTabQuery(sMySqlQuery);
		Thread.sleep(3000);
//		objPAP.clickSubmitQueryTab();
//		objPAP.clickClearButton();
//		Thread.sleep(3000);
//		objPAP.clickConsoleWindow();
//		Thread.sleep(3000);
//		UtilityCustomFunctions.logWriteConsole(sMySqlQuery);
//		objPAP.setQueryInConsole(sMySqlQuery);
//		objPAP.jSTypeValue(driver, sMySqlQuery);
//		
////		objPAP.setQuery(sMySqlQuery);
//		Thread.sleep(3000);
//		objPAP.clickGoQuery();
//		Thread.sleep(3000);
		objPAP.checkDailyNotification(sUser1RecordId, node);
//		objPAP.checkWebTblValues(sUser1RecordId,node);
	}	
}
