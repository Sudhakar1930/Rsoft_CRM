package testCases.WebForms;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.CRMSettingsPage;
import pageObjects.HomePage;
import pageObjects.IndvControlsPage;
import pageObjects.UserPage;
import pageObjects.WebFormsPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;

public class TC002_WF_AvailableYorN_NoCondition extends BaseClass {
	@BeforeTest
	public void testName() {
		test = extent.createTest("TC002_WF_AvailableYorN_NoCondition");
	}
	

	@Test	
	public void testWFUserAvailYesorNo() throws Exception {
		
		node = test.createNode("AvailabilityYesorNo_NoCondition");
		String sBrowserName=utilities.UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
		String sPath="\\WebForm\\WF_AvailYN_NoCondition_";
		CRMReUsables ObjCRMRs = new CRMReUsables(); 
		IndvControlsPage IndvObj = new IndvControlsPage(driver); 
		UserPage objUP = new UserPage(driver);
		AllListPage objALP = new AllListPage(driver);
		HomePage objHP = new HomePage(driver);
		WebFormsPage objWFP = new WebFormsPage(driver);
		CRMSettingsPage objCRMSTngs = new CRMSettingsPage(driver);
		
		String sMainPath=".\\testData\\WebForm\\" + "WF_AvailYN_NoCondition" + "_Test.xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sMainPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
		
		String IsAvail="";
		String IsAdmin="";
		boolean bFlag = false;
		logger.info("Extracting DataSheet Values started...");
		String sPN_Prefix = xlObj.getCellData("Sheet1", 1, 4);
		String sPN_Value = xlObj.getCellData("Sheet1", 1, 5);
		String sEM_Value = xlObj.getCellData("Sheet1", 1, 7);
		String sXQ_Value = xlObj.getCellData("Sheet1", 1, 9);
		String sMS_Value = xlObj.getCellData("Sheet1", 1, 11);
		String sRun_Flag = xlObj.getCellData("Sheet1", 1, 12);
		
		String sUserName1 = xlObj.getCellData("Sheet1", 1, 13);
		String sUser1Avail = xlObj.getCellData("Sheet1", 1, 14);
		String sUser1Admin = xlObj.getCellData("Sheet1", 1, 15);
		String sUserName2 = xlObj.getCellData("Sheet1", 1, 16);
		String sUser2Avail = xlObj.getCellData("Sheet1", 1, 17);
		String sUser2Admin = xlObj.getCellData("Sheet1", 1, 18);
		String sUserName3 = xlObj.getCellData("Sheet1", 1, 19);
		String sUser3Avail = xlObj.getCellData("Sheet1", 1, 20);
		String sUser3Admin = xlObj.getCellData("Sheet1", 1, 21);
		String sUserName4 = xlObj.getCellData("Sheet1", 1, 22);
		String sUser4Avail = xlObj.getCellData("Sheet1", 1, 23);
		String sUser4Admin = xlObj.getCellData("Sheet1", 1, 24);
//		xlObj.setCellData("Sheet1", 1, 25, "0");
		String sModuleName = xlObj.getCellData("Sheet1", 1, 26);
		String sWebFormName = xlObj.getCellData("Sheet1", 1, 27);
		String sMC_Title = xlObj.getCellData("Sheet1", 1, 28);
		String sMC_Value = xlObj.getCellData("Sheet1", 1, 29);
		
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		
		System.out.println("App Url: " + sAppUrl);
		driver.get(sAppUrl);
		ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sUserName,sPassword);
		Thread.sleep(3000);
		ObjCRMRs.fNavigatetoUserMgmt();
		System.out.println("User Name 1: " + sUserName1);
		
//		ObjCRMRs.fNavigatetoUserMgmt();
//		System.out.println("User Name 1: " + sUserName1);
//		if(objUP.fSearchUser(sUserName1)==1) {
//			IsAvail = objUP.fGetUserAvailability();
//			IsAdmin = objUP.fGetUserAdmin();
//			if(IsAvail.equalsIgnoreCase(sUser1Avail) && IsAdmin.equalsIgnoreCase(sUser1Admin)) {
//				bFlag = true;
//				System.out.println(IsAvail);
//				System.out.println(IsAdmin);
//			}
//			if(bFlag==false) {
//				System.out.println("Invalid User in Round Robin:" + sUserName1);
//			}
//		}
//		ObjCRMRs.fNavigatetoUserMgmt();
//		System.out.println("User Name 2: " + sUserName2);
//		if(objUP.fSearchUser(sUserName2)==1) {
//			IsAvail = objUP.fGetUserAvailability();
//			IsAdmin = objUP.fGetUserAdmin();
//			if(IsAvail.equalsIgnoreCase(sUser2Avail) && IsAdmin.equalsIgnoreCase(sUser2Admin)) {
//				bFlag = true;
//				System.out.println(IsAvail);
//				System.out.println(IsAdmin);
//			}
//			if(bFlag==false) {
//				System.out.println("Invalid User in Round Robin:" + sUserName2);
//			}
//		}
//		ObjCRMRs.fNavigatetoUserMgmt();
//		if(objUP.fSearchUser(sUserName3)==1) {
//			IsAvail = objUP.fGetUserAvailability();
//			IsAdmin = objUP.fGetUserAdmin();
//			if(IsAvail.equalsIgnoreCase(sUser3Avail) && IsAdmin.equalsIgnoreCase(sUser3Admin)) {
//				bFlag = true;
//				System.out.println(IsAvail);
//				System.out.println(IsAdmin);
//			}
//			if(bFlag==false) {
//				System.out.println("Invalid User in Round Robin:" + sUserName3);
//			}
//		}
//		ObjCRMRs.fNavigatetoUserMgmt();
//		if(objUP.fSearchUser(sUserName4)==1) {
//			IsAvail = objUP.fGetUserAvailability();
//			IsAdmin = objUP.fGetUserAdmin();
//			if(IsAvail.equalsIgnoreCase(sUser4Avail) && IsAdmin.equalsIgnoreCase(sUser4Admin)) {
//				bFlag = true;
//				System.out.println(IsAvail);
//				System.out.println(IsAdmin);
//			}
//			if(bFlag==false) {
//				System.out.println("Invalid User in Round Robin:" + sUserName4);
//			}
//		}
		String sUsersList="";
//		if(bFlag==true) {
			
			sUsersList=sUserName1+":"+sUserName2+":"+sUserName3+":"+sUserName4;
//		}
		
//		ObjCRMRs.fConfigureWebForm(sUsersList,sModuleName,sWebFormName);
		objCRMSTngs.fCRMNavigate("Integration", "Web Forms");
		objWFP.fNavigateWFUserConfigPage(sModuleName,sWebFormName);
		objWFP.fSetRoundRobinUsers(true,sUsersList);
		Thread.sleep(3000);
		objWFP.clickWebFormSave();
		Thread.sleep(3000);
		String sUserArray[]= sUsersList.split(":");
		String sCurrUserName="";
		
		sRun_Flag  = "";
		for(int i=1;i<=iRowCount;i++) {
			
			sEM_Value = xlObj.getCellData("Sheet1", i, 7);
			sXQ_Value = xlObj.getCellData("Sheet1", i, 9);
			sMS_Value = xlObj.getCellData("Sheet1", i, 11);
			sRun_Flag = xlObj.getCellData("Sheet1", i, 12);
			if(sRun_Flag.trim().equalsIgnoreCase("Yes")) {
			ObjCRMRs.fWFSubmitSF(i,"Test",sPath,"Sheet1",false,node);
			
			sPN_Prefix = xlObj.getCellData("Sheet1", i, 4);
			sPN_Value = xlObj.getCellData("Sheet1", i, 5);
			
			String sCurrUserPos = xlObj.getCellData("Sheet1", 1, 25);
			System.out.println("Current User Position: " + sCurrUserPos);
			sCurrUserName = sUserArray[Integer.parseInt(sCurrUserPos)];
			int sUpdatePos = Integer.parseInt(sCurrUserPos) + 2;
			if(sUpdatePos>3) {
				sUpdatePos = 1;
			}
			xlObj.setCellData("Sheet1", 1, 25, String.valueOf(sUpdatePos));
			
			System.out.println("Current User:" +sCurrUserName);
			driver.get(sAppUrl);
//			ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sUserName,sPassword);
			Thread.sleep(1000);
			objALP.clickAllList();
			Thread.sleep(1000);
			objALP.clickModuleOnListAll(driver, sModuleName);
			ObjCRMRs.fModuleTableValue(sCurrUserName, sPN_Prefix, sPN_Value, sEM_Value, sXQ_Value, sMS_Value, sMC_Value,node);
		  }//Run Flag	
		}

	}//Test	
}
