package testCases.WebForms;

import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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

public class TC005_WF_RRUserYN_OneConditionYN extends  BaseClass{
	@BeforeTest
	public void testName() {
		test = extent.createTest("TC005_WF_RRUserYN_OneConditionYN");
	}
	@Test	
	public void testWebFormUser() throws Exception {
		node = test.createNode("RRUserYN_OneConditionYN");
		String sBrowserName=utilities.UtilityCustomFunctions.getBrowserName(driver);
		logger.info("Test Execution on Browser: "+ sBrowserName);
		System.out.println("Test Execution on Browser: "+ sBrowserName);
		String sPath="\\WebForm\\TC005_WF_RRUserYN_OneConditionYN_";
		CRMReUsables ObjCRMRs = new CRMReUsables(); 
		IndvControlsPage IndvObj = new IndvControlsPage(driver); 
		UserPage objUP = new UserPage(driver);
		AllListPage objALP = new AllListPage(driver);
		HomePage objHP = new HomePage(driver);
		WebFormsPage objWFP = new WebFormsPage(driver);
		CRMSettingsPage objCRMSTngs = new CRMSettingsPage(driver);
		
		String sMainPath=".\\testData\\WebForm\\" + "TC005_WF_RRUserYN_OneConditionYN" + "_Test.xlsx" ;
		
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
		String sMC_Value = xlObj.getCellData("Sheet1", 1, 13);
		
		
		String sUserName1 = xlObj.getCellData("Sheet1", 1, 15);
		String sUser1Avail = xlObj.getCellData("Sheet1", 1, 16);
		String sUser1Admin = xlObj.getCellData("Sheet1", 1, 17);
		
		String sUserName2 = xlObj.getCellData("Sheet1", 1, 18);
		String sUser2Avail = xlObj.getCellData("Sheet1", 1, 19);
		String sUser2Admin = xlObj.getCellData("Sheet1", 1, 20);
		
		String sUserName3 = xlObj.getCellData("Sheet1", 1, 21);
		String sUser3Avail = xlObj.getCellData("Sheet1", 1, 22);
		String sUser3Admin = xlObj.getCellData("Sheet1", 1, 23);
		
		String sUserName4 = xlObj.getCellData("Sheet1", 1, 24);
		String sUser4Avail = xlObj.getCellData("Sheet1", 1, 25);
		String sUser4Admin = xlObj.getCellData("Sheet1", 1, 26);
		
		String sUserName5 = xlObj.getCellData("Sheet1", 1, 27);
		String sUser5Avail = xlObj.getCellData("Sheet1", 1, 28);
		String sUser5Admin = xlObj.getCellData("Sheet1", 1, 29);
		
		String sUserName6 = xlObj.getCellData("Sheet1", 1, 30);
		String sUser6Avail = xlObj.getCellData("Sheet1", 1, 31);
		String sUser6Admin = xlObj.getCellData("Sheet1", 1, 32);
		
		String sUserName7 = xlObj.getCellData("Sheet1", 1, 33);
		String sUser7Avail = xlObj.getCellData("Sheet1", 1, 34);
		String sUser7Admin = xlObj.getCellData("Sheet1", 1, 35);
		
		String sModuleName = xlObj.getCellData("Sheet1", 1, 36);
		String sWebFormName = xlObj.getCellData("Sheet1", 1, 37);
		

		
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		
	  	driver.get(sAppUrl);
		ObjCRMRs.fLoginCRM(sAppUrl,sCompName,sUserName,sPassword);
		Thread.sleep(3000);
		/*
		ObjCRMRs.fNavigatetoUserMgmt();
		
		// *********** Check User Details From Test Data is Neither Available  nor Admin ********
		System.out.println("User Name 1: " + sUserName1);
		if(objUP.fSearchUser(sUserName1)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser1Avail) && IsAdmin.equalsIgnoreCase(sUser1Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName1);
				Assert.fail("InValid User:" + sUserName1);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		System.out.println("User Name 2: " + sUserName2);
		if(objUP.fSearchUser(sUserName2)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser2Avail) && IsAdmin.equalsIgnoreCase(sUser2Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName2);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		if(objUP.fSearchUser(sUserName3)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser3Avail) && IsAdmin.equalsIgnoreCase(sUser3Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName3);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		if(objUP.fSearchUser(sUserName4)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser4Avail) && IsAdmin.equalsIgnoreCase(sUser4Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName4);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		if(objUP.fSearchUser(sUserName5)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser5Avail) && IsAdmin.equalsIgnoreCase(sUser5Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName5);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		if(objUP.fSearchUser(sUserName6)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser6Avail) && IsAdmin.equalsIgnoreCase(sUser6Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName6);
			}
		}
		ObjCRMRs.fNavigatetoUserMgmt();
		if(objUP.fSearchUser(sUserName7)==1) {
			IsAvail = objUP.fGetUserAvailability();
			IsAdmin = objUP.fGetUserAdmin();
			if(IsAvail.equalsIgnoreCase(sUser7Avail) && IsAdmin.equalsIgnoreCase(sUser7Admin)) {
				bFlag = true;
				System.out.println(IsAvail);
				System.out.println(IsAdmin);
			}
			if(bFlag==false) {
				System.out.println("Invalid User in Condition RR:" + sUserName7);
			}
		}
		*/
		String sMatchUsersList="";
		String sUnMatchUserList="";
		
		sUnMatchUserList = sUserName1+":"+sUserName2+":"+sUserName3+":"+sUserName4;
		sMatchUsersList=sUserName5+":"+sUserName6+":"+sUserName7;
		
		objCRMSTngs.fCRMNavigate("Integration", "Web Forms");
		objWFP.fNavigateWFUserConfigPage(sModuleName,sWebFormName);
//		objWFP.fSetCond1RRUsers(true,sMatchUsersList);
		
		objWFP.clickWebFormSave();
		Thread.sleep(2000);
		String sMatchUserArray[]= sMatchUsersList.split(":");
		String sUnMatchUserArray[]= sUnMatchUserList.split(":");
		String sCurrUserName="";
		String sWebFormId="";
		String sRun_Flag="";
		sRun_Flag  = "";
		for(int i=1;i<=iRowCount;i++) {
			sEM_Value = xlObj.getCellData("Sheet1", i, 7);
			sXQ_Value = xlObj.getCellData("Sheet1", i, 9);
			sMS_Value = xlObj.getCellData("Sheet1", i, 11);
			sMC_Value = xlObj.getCellData("Sheet1", i, 13);
			sRun_Flag = xlObj.getCellData("Sheet1", i, 14);
			sWebFormId = xlObj.getCellData("Sheet1", 1, 40);
			
			int sUpdatePos =0;
			System.out.println("Run  flag valuie: " + i + " " + sRun_Flag);
			if(sRun_Flag.trim().equalsIgnoreCase("Yes")) {
				
//				ObjCRMRs.fConditionSFSubmit(i,"Test",sPath,"Sheet1",false,node);
				
				String sRandPhoneNo= randomeNumber(); 
				xlObj.setCellData("Sheet1", i, 5, sRandPhoneNo);
				
				sPN_Prefix = xlObj.getCellData("Sheet1", i, 4);
				sPN_Value = xlObj.getCellData("Sheet1", i, 5);
				
				System.out.println("mOBILE NUMBER:" + sRandPhoneNo);
				System.out.println("mOBILE NUMBER prefix:" + sPN_Prefix);
				System.out.println("email:" + sEM_Value);
				System.out.println("text" + sXQ_Value);
				System.out.println("etnotification_multiselect:" + sMS_Value);
				System.out.println("state:" + sMC_Value);
				System.out.println("webformid:" + sWebFormId);
					
				
//				ObjCRMRs.fWFSubmitSF(i,"Test",sPath,"Sheet1",false,node);
				HashMap data = new HashMap();
				data.put("etnotification_mobilenumber", sRandPhoneNo);
				data.put("etnotification_mobilenumber_prefix", sPN_Prefix);
				data.put("etnotification_email", sEM_Value);
				data.put("etnotification_text", sXQ_Value);
				data.put("etnotification_multiselect", sMS_Value);
				data.put("etnotification_state", sMC_Value);
				data.put("webformid", sWebFormId);
				given()
				.contentType("application/json")
				.body(data)
				.when()
				.post(testBase.Routes.full_url)
				.then()
				.statusCode(200);
				
				sPN_Prefix = xlObj.getCellData("Sheet1", i, 4);
				sPN_Value = xlObj.getCellData("Sheet1", i, 5);
				
				if(sMS_Value.trim().equalsIgnoreCase("one")) {
					String sCurrUserPos = xlObj.getCellData("Sheet1", 1, 38);
					System.out.println("Current User Position: " + sCurrUserPos);
					sCurrUserName = sMatchUserArray[Integer.parseInt(sCurrUserPos)];
					sUpdatePos = Integer.parseInt(sCurrUserPos) + 2;
					if(sUpdatePos>2) {
						sUpdatePos = 0;
					}
					xlObj.setCellData("Sheet1", 1, 38, String.valueOf(sUpdatePos));
				}
				else {
					String sCurrUserPos = xlObj.getCellData("Sheet1", 1, 39);
					System.out.println("Current User Position: " + sCurrUserPos);
					sCurrUserName = sUnMatchUserArray[Integer.parseInt(sCurrUserPos)];
					sUpdatePos = Integer.parseInt(sCurrUserPos) + 2;
					if(sUpdatePos>3) {
						sUpdatePos = 0;
					}
					xlObj.setCellData("Sheet1", 1, 39, String.valueOf(sUpdatePos));
				}// if Match/Un Match
				System.out.println("Current User:" +sCurrUserName);
				driver.get(sAppUrl);
				Thread.sleep(1000);
				objALP.clickAllList();
				Thread.sleep(1000);
				objALP.clickModuleOnListAll(driver, sModuleName);
				ObjCRMRs.fModuleTableValue(sCurrUserName, sPN_Prefix, sPN_Value, sEM_Value, sXQ_Value, sMS_Value,sMC_Value, node);
				
				xlObj.setCellData("Sheet1", i, 41, "Done");
				Date currentDate = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String currentDateTime = dateFormat. format(currentDate);
				xlObj.setCellData("Sheet1", i, 42, currentDateTime);
				
				
				
			}//If Run_Flag	
		
		}//for Loop
		
		Thread.sleep(2000);
		objHP.clickLogoutCRM();
	}//Test	
}
