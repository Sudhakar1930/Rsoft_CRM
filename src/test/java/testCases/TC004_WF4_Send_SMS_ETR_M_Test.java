package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.BaseClass;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;

public class TC004_WF4_Send_SMS_ETR_M_Test extends BaseClass {
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("TC003_WF3_Every_time_record_modifies");
	}
	@Test
	public void test_Send_SMS_ETR_M_Test () throws Exception {
		node = test.createNode("WF4_Send_SMS_ETR_M_Test");
		
		logger.info("******starting WF3_Every_time_record_modifies ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		
//		String sPath=".\\testData\\SMS\\" + "WF4_Send_SMS_ETR_M_Live" + ".xlsx" ;
		String sPath=".\\testData\\SMS\\" + "WF4_Send_SMS_ETR_M_Test" + ".xlsx" ;
	
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		
		
		
	
	}//Test
}//Class
