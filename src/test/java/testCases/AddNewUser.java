package testCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.IndvControlsPage;
import pageObjects.UserPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;

public class AddNewUser extends BaseClass {
	@BeforeTest
	public void testName() {
		test = extent.createTest("AddNewUser");
	}
	
	@Test	
	public void testWebFormUser() throws Exception {
		
		node = test.createNode("Create User");
		String sBrowserName=utilities.UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
		String sPath="\\WebForm\\NewUsersList_";
		CRMReUsables ObjCRMRs = new CRMReUsables(); 
		IndvControlsPage IndvObj = new IndvControlsPage(driver); 
		UserPage objUP = new UserPage(driver);
		AllListPage objALP = new AllListPage(driver);
		
		String sMainPath=".\\testData\\WebForm\\" + "NewUsersList" + "_Test.xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sMainPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
		
		
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sLoginUserName =  rb.getString("userName");
		String sLoginPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		
		driver.get(sAppUrl);
		ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sLoginUserName,sLoginPassword);
		Thread.sleep(3000);
		ObjCRMRs.fNavigatetoUserMgmt();
		Thread.sleep(3000);
		for(int i=1;i<=iRowCount;i++) {
			
			logger.info("Extracting DataSheet Values started...");
			String sUserName = xlObj.getCellData("Sheet1", i, 0);
			String sPassword = xlObj.getCellData("Sheet1", i, 1);
			String sFirstName = xlObj.getCellData("Sheet1", i, 2);
			String sLastName = xlObj.getCellData("Sheet1", i, 3);
			String sEmail = xlObj.getCellData("Sheet1", i, 4);
			String sRoles = xlObj.getCellData("Sheet1", i, 5);
			String sLoginAccess = xlObj.getCellData("Sheet1", i, 6);
			String sAvailability = xlObj.getCellData("Sheet1", i, 7);
			String sIsAdmin = xlObj.getCellData("Sheet1", i, 8);
			String sRunFlag = xlObj.getCellData("Sheet1", i, 9);
			
			if(sRunFlag.equalsIgnoreCase("Yes")) {
				objUP.clickAddUserBtn();
				Thread.sleep(4000);
				System.out.println(sUserName);
				//click Add User
				Thread.sleep(1000);
				objUP.setUserName(sUserName);
				Thread.sleep(1000);
				objUP.setUserEmail(sEmail);
				Thread.sleep(1000);
				objUP.setUserPwd(sPassword);
				Thread.sleep(1000);
				objUP.setConfirmPwd(sPassword);
				Thread.sleep(1000);
				objUP.setUserFirstName(sFirstName);
				Thread.sleep(1000);
				objUP.setUserLastName(sLastName);
				Thread.sleep(1000);
				objUP.setRole(sRoles);
				Thread.sleep(1000);
				objUP.setLoginAccess(sLoginAccess);
				Thread.sleep(1000);
				objUP.setAvailability(sAvailability);
				Thread.sleep(1000);
				objUP.setAdmin(sIsAdmin);
				Thread.sleep(1000);
				objUP.clickSaveBtn();
				xlObj.setCellData("Sheet1", i, 9, "No");
				boolean isDuplicate=false;
				isDuplicate = objUP.IsDuplicateMsgExist();
				if(isDuplicate==true) {
					xlObj.setCellData("Sheet1", i, 10, "Duplicate Record");
				}
				Thread.sleep(5000);
				ObjCRMRs.fNavigatetoUserMgmt();
//				objUP.fClickUserMenuItem();
				
			}
			
			
		}
		
	}//Test	
}
