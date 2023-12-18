package utilities;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import jdk.internal.org.jline.utils.Log;
import testBase.BaseClass;
import pageObjects.AllListPage;
import pageObjects.CRMSettingsPage;
import pageObjects.CreateModuleDataPage;
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.NotificationsPage;
import pageObjects.WorkFlowPage;
import utilities.UtilityCustomFunctions;
import utilities.ExcelUtility;

public class CRMReUsables extends BaseClass {

	public void CrmLogin() {

	}

	public String fOpenNotification(String sCurrWinHandle) throws Exception {
		System.out.println("fOPenNotification function started in ..");
		String sActualWindow = "";
		NotificationsPage objNotfy = new NotificationsPage(driver);
		objNotfy.clickNotificatons();
		logger.info("Notification Link Clicked");
		System.out.println("Notification link clicked");
		Thread.sleep(5000);
		objNotfy.clickOpnNotifyPage();
		logger.info("Notification detail view opened");
//		objNotfy.clickNotifyFirstMsg();
		Thread.sleep(5000);
		Set<String> Handles = driver.getWindowHandles();
		for (String actual : Handles) {
			System.out.println("Current Window Handle: " + sCurrWinHandle);
			System.out.println("Window Handle: " + actual);
			if (!actual.equalsIgnoreCase(sCurrWinHandle)) {
				driver.switchTo().window(actual);
				logger.info("Switched to New Window");
				sActualWindow = actual;
				break;
			}
		}
		return sActualWindow;

	}
	public String fgetNotificationCount() throws Exception {
		NotificationsPage objNP = new NotificationsPage(driver);
		String sCount = objNP.getNotificatonCount();
		return sCount;
				
	}
	public void fValModuleView(String sExpModuleName, String sAssignedTo, String sPhoneNoumber, String sNumberField,
			String sEmail, String sPickListItem, String sEnterYourNumber, ExtentTest node) throws Exception {
		DetailViewPage objDVP = new DetailViewPage(driver);
		String aActModuleName = objDVP.getNavBarModuleName();
		System.out.println("Actual Module Name: " + aActModuleName);
		System.out.println("Expected Module Name: " + sExpModuleName);
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name", node);
		// Title Actual Values
		String sActPNTitle = objDVP.getPhoneNMTitle();
		String sPNTitleArray[] = sActPNTitle.split("\\s+");
		sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		System.out.println("Actual: " + sActPNTitle);
		String sActEMTitle = objDVP.getEmailTitle();
		String sActMnuTitle = objDVP.getMenuItemTitle();

		// Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name", node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle, sPhoneNoumber, "Phone Number", node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle, sEmail, "Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle, sPickListItem, "Piclist Value", node);

		objDVP.clickSummaryView();
		Thread.sleep(3000);
		// Summary Actual Values
		String sActPNSummary = objDVP.getPhoneNMSummary();
		System.out.println("Actual raw: " + sActPNSummary.trim());
		sActPNSummary = sActPNSummary.trim();
		String sPNArray[] = sActPNSummary.split("\\s+");
		System.out.println("Array size: " + sPNArray.length);
		sActPNSummary = sPNArray[0].trim() + " " + sPNArray[1].trim();
		System.out.println("Actual: Summary" + sActPNSummary);
		String sActNFSummary = objDVP.getNumberFieldSummary();
		String sActEMSummary = objDVP.getEmailSummary();
		String sActMnuSummary = objDVP.getMenuItemSummary();
		String sActEYNSummary = objDVP.getEYNumberSummary();

		// Summary Values Validation
		UtilityCustomFunctions.fSoftAssert(sActPNSummary, sPhoneNoumber, "Summary Phone Number", node);
		UtilityCustomFunctions.fSoftAssert(sActNFSummary, sNumberField, "Summary Number Field", node);
		UtilityCustomFunctions.fSoftAssert(sActEMSummary, sEmail, "Summary Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuSummary, sPickListItem, "Summary Picklist Item", node);
		UtilityCustomFunctions.fSoftAssert(sActEYNSummary, sEnterYourNumber, "Summary Enter Your Number", node);

		// Click Detail View
		objDVP.clickDetailView();
		Thread.sleep(3000);
		// Detail View Actual Values
		String sActAsgnToDTView = objDVP.getAssignedToDTView();

		String sActPNDTView = objDVP.getPhoneNMDTView();

		String sPNArrayDT[] = sActPNDTView.split("\\s+");
		sActPNDTView = sPNArrayDT[0].trim() + " " + sPNArrayDT[1].trim();
		System.out.println("Actual: DTView : " + sActPNDTView);
		String sActEMDTView = objDVP.getEmailDTView();
		String sActMnuDTView = objDVP.getMenuListDTView();
		String sActEYVDT = objDVP.getEYVDTView();

		// Detail View Validation
		System.out.println("Actual Assigned To:" + sActAsgnToDTView);
		System.out.println("Expected Assigned To:" + sAssignedTo);
		UtilityCustomFunctions.fSoftAssert(sActAsgnToDTView, sAssignedTo, "Detail View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sActPNDTView, sPhoneNoumber, "Detail View Phone Numer", node);
		UtilityCustomFunctions.fSoftAssert(sActEMDTView, sEmail, "Detail View Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuDTView, sPickListItem, "Detail View Menu Item", node);
		UtilityCustomFunctions.fSoftAssert(sActEYVDT, sEnterYourNumber, "Detail View Enter Your Number", node);

		// Title & NavBar Validation again
		String aActModuleName1 = objDVP.getNavBarModuleName();
		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName, "Module Name", node);
		// Title Actual Values
		String sActPNTitle1 = objDVP.getPhoneNMTitle();
		String sPNTitleArray1[] = sActPNTitle1.split("\\s+");
		sActPNTitle1 = sPNTitleArray1[0].trim() + " " + sPNTitleArray1[1].trim();
		System.out.println("Actual: " + sActPNTitle1);

		String sActEMTitle1 = objDVP.getEmailTitle();
		String sActMnuTitle1 = objDVP.getMenuItemTitle();

		// Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName, "Module Name", node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle1, sPhoneNoumber, "Phone Number", node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle1, sEmail, "Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle1, sPickListItem, "Piclist Value", node);

	}

	public String fValNotifySummaryAndDetail(String sAssignedTo, String sUserName, String sActionTitle, ExtentTest node)
			throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);

		String sStatus = "0";

//		sActionTitle
		// get Details
		String sActDTAssignedTo = objNFP.getDTAssignedTo();
		String sActDTStatus = objNFP.getDTStatus();
		String sActDTSummary = objNFP.getDTSummary();
		String sActDTTitle = objNFP.getDTTitle();
		String sActDTModRecId = objNFP.getDTModRecId();
		// Validations
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sAssignedTo, "Detail View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sActDTStatus, sStatus, "Detail View Status", node);
		UtilityCustomFunctions.fSoftAssert(sActDTSummary, sUserName, "Detail View Summary/UserName", node);
		UtilityCustomFunctions.fSoftAssert(sActDTTitle, sActionTitle, "Detail View Action Title", node);
		objNFP.clickSummaryTab();
		// getActualSummaryDetails
		Thread.sleep(1000);
		String sActSMAssignedTo = objNFP.getSMAssignedTo();
		String sActSMStatus = objNFP.getSMStatus();
		String sActSMSummary = objNFP.getSMSummary();
		String sActSMTitle = objNFP.getSMActionTitle();
		// Validations
		UtilityCustomFunctions.fSoftAssert(sAssignedTo, sActSMAssignedTo, "Summary View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sStatus, sActSMStatus, "Summary View Status", node);
		UtilityCustomFunctions.fSoftAssert(sActSMSummary, sUserName, "Summary View User", node);
		UtilityCustomFunctions.fSoftAssert(sActSMTitle, sActionTitle, "Summary View Action Title", node);

		return sActDTModRecId;

	}

	public void fClickSearch(String sRecordId) throws Exception {
		AllListPage objALP = new AllListPage(driver);
		NotificationsPage objNFP = new NotificationsPage(driver);
		objALP.clickAllList();
		objALP.clickAllNotifications();
		Thread.sleep(3000);
		objNFP.setRecordId(sRecordId);
		objNFP.clickSearchButton();

	}

	public void fgetTablevalues(String sAssignedTo, String sStatus, String sCreatedBy, String sUserName,
			String sExpTitle, String sRecordId, ExtentTest node) throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		List<WebElement> tRowCount = driver.findElements(By.xpath("//tbody/tr"));
		for (int i = 2; i <= tRowCount.size(); i++) {
			System.out.println("Current Row Number: " + i);
			List<WebElement> tColCount = tRowCount.get(i).findElements(By.tagName("td"));
//			for(int j=0;j<=tColCount.size();j++) {
//				System.out.println("Values Selected is:" + tColCount.get(j).getText());
//			}

			String sActAssignedTo = UtilityCustomFunctions.getText(driver, tColCount.get(2));
			String sActStatus = UtilityCustomFunctions.getText(driver, tColCount.get(3));
			String sActCreatedBy = UtilityCustomFunctions.getText(driver, tColCount.get(6));
			String sActSummary = UtilityCustomFunctions.getText(driver, tColCount.get(7));
			String sActTitle = UtilityCustomFunctions.getText(driver, tColCount.get(8));
			String sActModRecId = UtilityCustomFunctions.getText(driver, tColCount.get(9));

			System.out.println("Actual status: " + sActStatus);
			System.out.println("Actual Created By: " + sActCreatedBy);
			System.out.println("Actual Summary: " + sActSummary);
			System.out.println("Actual Title: " + sActTitle);
			System.out.println("Actual Record Id: " + sActModRecId);

			System.out.println("Expected status: " + sStatus);
			System.out.println("Expected Created By: " + sCreatedBy);
			System.out.println("Expected Summary: " + sUserName);
			System.out.println("Expected Title: " + sExpTitle);
			System.out.println("Expected Record Id: " + sRecordId);

			UtilityCustomFunctions.fSoftAssert(sAssignedTo, sAssignedTo, "AssignedTo Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActStatus, sStatus, "Status in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActCreatedBy, sCreatedBy, "Created By in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActSummary, sUserName, "UserName in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActTitle, sExpTitle, "Workflow Title in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActModRecId, sRecordId, "Record Id in Record Details", node);
			break;
		}
	}

	public void fNavigatetoWorkflow(String sModule) throws Exception {
		HomePage objHP = new HomePage(driver);
		CRMSettingsPage objCRMs = new CRMSettingsPage(driver);
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		// Click Avatar
		BaseClass.logger.info("Clicked Avatar");
		System.out.println("Clicked Avatar");
		objHP.clickAvatar();
		objCRMs.clickMnuCRMSetting();
		BaseClass.logger.info("Clicked Menu CRM Setting");
		System.out.println("Clicked Menu CRM Setting");
		objCRMs.clickMnuOtherSetting();
		BaseClass.logger.info("Clicked Menu Other Setting");
		System.out.println("Clicked Menu Other Setting");
		objCRMs.clickMnuItemWorkflow();
		BaseClass.logger.info("Clicked Menu Item Workflow");
		System.out.println("Clicked Menu Item Workflow");
		objWFP.fClickWorkflowsList(sModule);
		BaseClass.logger.info("Selected Module to View its Workflows");
		System.out.println("Selected Module to View its Workflows");

	}

	public String IsCheckWorkflowStatus(String sModule, String sWorkflow, String sExecCondition) {
		String sWFStatusReturn = "";
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		sWFStatusReturn = objWFP.fWorkFlowStatus(sModule, sWorkflow, sExecCondition);
		BaseClass.logger.info(sWFStatusReturn);
		return sWFStatusReturn;
	}

	public void fClickWorkFlowAndGotoTask(int iEditPos) throws Exception {
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		objWFP.fNavigateToTask(iEditPos);

	}

	public boolean fCheckTaskStatus(String sWorkflow, String sActionType, String sActionTitle) throws Exception {
		boolean bTaskStatus = false;
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		return bTaskStatus = objWFP.fValidateTaskStatus(sWorkflow, sActionType, sActionTitle);
	}

	public void fAddValuestoModulePage(String sEnv) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test_Add" + ".xlsx" ;
		}
		else {
			sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Live" + ".xlsx" ;
		}
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
		String sText=xlObj.getCellData("Sheet1", 1, 3);
		String sMobNumPrefix=xlObj.getCellData("Sheet1", 1, 4);
		String sMobileNumber=xlObj.getCellData("Sheet1", 1, 5);
		String sEmail=xlObj.getCellData("Sheet1", 1, 6);
		String sPickListValue=xlObj.getCellData("Sheet1", 1, 7);
		String sMultiComboValues=xlObj.getCellData("Sheet1", 1, 8);
		String sCity=xlObj.getCellData("Sheet1", 1, 9);
		String sState=xlObj.getCellData("Sheet1", 1, 10);
		String sCountry=xlObj.getCellData("Sheet1", 1, 11);
		String sCheckBox=xlObj.getCellData("Sheet1", 1, 12);
		String sDate =xlObj.getCellData("Sheet1", 1, 13);
		String sTime =xlObj.getCellData("Sheet1", 1, 14);
		String sDateandTime =xlObj.getCellData("Sheet1", 1, 15);
		String sRelatedModule =xlObj.getCellData("Sheet1", 1, 16);
		String sFilePath =xlObj.getCellData("Sheet1", 1, 17);
		String sNamePrefix =xlObj.getCellData("Sheet1", 1, 18);
		String sName=xlObj.getCellData("Sheet1", 1, 19);
		String sNumber=xlObj.getCellData("Sheet1", 1, 20);
		String sCurrency=xlObj.getCellData("Sheet1", 1, 21);
		String sUrl=xlObj.getCellData("Sheet1", 1, 22);
		String sEnqNamePrefix = xlObj.getCellData("Sheet1", 1, 23);
		String sEnquiry_Name=xlObj.getCellData("Sheet1", 1, 24);
		String sEnquiry_Email=xlObj.getCellData("Sheet1", 1, 25);
		String sEnquiry_Text=xlObj.getCellData("Sheet1", 1, 26);
		String sEnquiry_TextArea=xlObj.getCellData("Sheet1", 1, 27);
		String sEnquiry_Date=xlObj.getCellData("Sheet1", 1, 28);
		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 29);
		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 30);
		String sEnquiry_category =xlObj.getCellData("Sheet1", 1, 31);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 32);
		String sActionType=xlObj.getCellData("Sheet1", 1, 33);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 34);
		String sRecordId=xlObj.getCellData("Sheet1", 1, 35);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 36);
		
		sRecordId="";
		System.out.println("Module Name:  " + sExpModuleName);
		
		Thread.sleep(3000);
		objCMD.clickArrayDropDown(1);
		Thread.sleep(2000);
		objCMD.selectListValue("Sudhakar VE");
		objCMD.setInputValue(sExpModuleName, "text",sText);
		Thread.sleep(1000);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"mobilenumber_prefix-container");
		objCMD.selectListValue(sMobNumPrefix);
		objCMD.setMobileNumber(sMobileNumber);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(3);
		Thread.sleep(1000);
		objCMD.setEmailValue(sExpModuleName, sEmail);
		Thread.sleep(1000);
		objCMD.selectListValue(sPickListValue);
		Thread.sleep(1000);
		objCMD.clickMultiComboBox(sMultiComboValues);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(4);
		objCMD.selectListValue(sCity);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(5);
		objCMD.selectListValue(sState);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(6);
		objCMD.selectListValue(sCountry);
		Thread.sleep(1000);
		objCMD.clickArrayCheckBox(1, sCheckBox);
		objCMD.clickDateBox(sExpModuleName, "date");
		Thread.sleep(3000);
		objCMD.clickTodayDate();
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		objCMD.clickTime("2", "35");
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "datetime");
		Thread.sleep(3000);
		objCMD.clickTodayDate();
		objCMD.clickDandTApply();
		String sActDate = objCMD.fGetModuleValue(sExpModuleName, "date");
		System.out.println("AcutalDate: " + sActDate);
		xlObj.setCellData("Sheet1", 1, 13, sActDate);
		
		String sActTime= objCMD.fGetModuleValue(sExpModuleName, "time");
		System.out.println("Time" + sActTime);
		xlObj.setCellData("Sheet1", 1, 14, sActTime);
		
		String sActDateandTime= objCMD.fGetModuleValue(sExpModuleName, "datetime");
		System.out.println("Date & Time" + sActDateandTime);
		xlObj.setCellData("Sheet1", 1, 15, sActDateandTime);
		
		objCMD.setInputValue(sExpModuleName, "relatedmodule_name", sRelatedModule);
		
		objCMD.setUploadFile();
//		sFileName
//		xlObj.setCellData("Sheet1", 1, 17, sFileName);
		
		objCMD.clickArrayDropDown(7);
		
		objCMD.selectListValue(sNamePrefix);
		
		objCMD.setInputValue(sExpModuleName, "name", sName);
		
		objCMD.setInputNumber(sExpModuleName, "number", sNumber);
		
		objCMD.setInputValue(sExpModuleName, "currency", sCurrency);
		
		objCMD.setGenericInputValue("url", sExpModuleName, "url", sUrl);
		
		//Block B
		
		objCMD.clickArrayDropDown(8);
		objCMD.selectListValue(sEnqNamePrefix);
		
		objCMD.setGenericInputValue("text", sExpModuleName, "enquiryname", sEnquiry_Name);
		
		objCMD.setGenericInputValue("email", sExpModuleName, "enquiryemail", sEnquiry_Email);
		
		objCMD.setGenericInputValue("text", sExpModuleName, "enquirytext", sEnquiry_Text);
		
		objCMD.setTextAreaValue(sExpModuleName, "enquirytextarea", sEnquiry_TextArea);
		
		objCMD.clickDateBox(sExpModuleName, "enquirydate");
		
		Thread.sleep(3000);
		
		objCMD.clickTodayDate();
		String sActEnquiryDate = objCMD.fGetModuleValue(sExpModuleName, "enquirydate");
		System.out.println("AcutalDate: " + sActEnquiryDate);
		xlObj.setCellData("Sheet1", 1, 28, sActEnquiryDate);
		
		
		System.out.println("Enquiriy prefix: " + sEnquiry_PN_Prefix);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"enquiryphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sEnquiry_PN_Prefix);
		
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "enquiryphonenumber", sEnquiry_PhoneNumber);
		
		objCMD.clickEnqCategory();
		
		objCMD.selectListValue(sEnquiry_category);
		
		Thread.sleep(1000);
		
		objCMD.clickSave();
		
		Thread.sleep(5000);
	}

	public void fValidateAllFields(String sEnv,String sType,String sMessage,String isNotify,ExtentTest node) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			if(sType.equalsIgnoreCase("Add")) {
				sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test_Add" + ".xlsx" ;	
			}
			else {
				sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test_Edit" + ".xlsx" ;
			}
			
		}
		else {
			sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Live" + ".xlsx" ;
		}//get the test data sheet
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlObj.getRowCount("Sheet1");
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		String sCurrentWinHandle = "";
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
		String sText=xlObj.getCellData("Sheet1", 1, 3);
		String sMobNumPrefix=xlObj.getCellData("Sheet1", 1, 4);
		String sMobileNumber=xlObj.getCellData("Sheet1", 1, 5);
		sMobileNumber = sMobNumPrefix + " " + sMobileNumber;  	
		String sEmail=xlObj.getCellData("Sheet1", 1, 6);
		String sPickListValue=xlObj.getCellData("Sheet1", 1, 7);
		String sMultiComboValues=xlObj.getCellData("Sheet1", 1, 8);
		String sCity=xlObj.getCellData("Sheet1", 1, 9);
		String sState=xlObj.getCellData("Sheet1", 1, 10);
		String sCountry=xlObj.getCellData("Sheet1", 1, 11);
		String sCheckBox=xlObj.getCellData("Sheet1", 1, 12);
		if(sCheckBox.equalsIgnoreCase("ON")) {
			sCheckBox = "Yes";
		}
		else {
			sCheckBox = "No";
		}
		String sDate =xlObj.getCellData("Sheet1", 1, 13);
		String sTime =xlObj.getCellData("Sheet1", 1, 14);
		String sDateandTime =xlObj.getCellData("Sheet1", 1, 15);
		String sRelatedModule =xlObj.getCellData("Sheet1", 1, 16);
		String sFileName =xlObj.getCellData("Sheet1", 1, 17);
		String sNamePrefix =xlObj.getCellData("Sheet1", 1, 18);
		String sName=xlObj.getCellData("Sheet1", 1, 19);
		sName = sNamePrefix + " " + sName;
		String sNumber=xlObj.getCellData("Sheet1", 1, 20);
		String sCurrency=xlObj.getCellData("Sheet1", 1, 21);
		String sUrl=xlObj.getCellData("Sheet1", 1, 22);
		String sEnqNamePrefix = xlObj.getCellData("Sheet1", 1, 23);
		String sEnquiry_Name=xlObj.getCellData("Sheet1", 1, 24);
		String sEnquiry_Email=xlObj.getCellData("Sheet1", 1, 25);
		String sEnquiry_Text=xlObj.getCellData("Sheet1", 1, 26);
		String sEnquiry_TextArea=xlObj.getCellData("Sheet1", 1, 27);
		String sEnquiry_Date=xlObj.getCellData("Sheet1", 1, 28);
		String sEnquiry_PN_Prefix=xlObj.getCellData("Sheet1", 1, 29);
		String sEnquiry_PhoneNumber=xlObj.getCellData("Sheet1", 1, 30);
		String sEnquiry_category =xlObj.getCellData("Sheet1", 1, 31);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 32);
		String sActionType=xlObj.getCellData("Sheet1", 1, 33);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 34);
		String sRecordId=xlObj.getCellData("Sheet1", 1, 35);
		String sWorkFlowPos=xlObj.getCellData("Sheet1", 1, 36);
		sCurrentWinHandle="";
		String sNewWindowHanlde="";
		if(isNotify.equalsIgnoreCase("Yes")){
			sCurrentWinHandle = driver.getWindowHandle();
			sNewWindowHanlde="";
			NotificationsPage objNotfy = new NotificationsPage(driver);
			objNotfy.clickNotificatons();
			objNotfy.clickNotifyFirstMsg();
			Thread.sleep(3000);
			Set<String> Handles = driver.getWindowHandles();
			for(String actual: Handles) {
				if (!actual.equalsIgnoreCase(sCurrentWinHandle)) {
					driver.switchTo().window(actual);
					break;
				}
			}
			
		}//if
		DetailViewPage objDVP = new DetailViewPage(driver);
		String aActModuleName = objDVP.getNavBarModuleName();
		System.out.println("Actual Module Name: " + aActModuleName);
		System.out.println("Expected Module Name: " + sExpModuleName);
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name : " + sMessage, node);
		// Title Actual Values
		String sActText = objDVP.getGenericTitle(1);
		String sActPNTitle = objDVP.getGenericTitle(2);
		String sPNTitleArray[] = sActPNTitle.split("\\s+");
		sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
		System.out.println("Actual: " + sActPNTitle);
		String sActEMTitle = objDVP.getGenericTitle(3);
		

		//Title Validations
		UtilityCustomFunctions.fSoftAssert(sActText, sText, "Summary Text:  " + sMessage, node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle, sMobileNumber, "Mobile Title: " + sMessage, node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle, sEmail, "Email: " + sMessage, node);
		
		objDVP.clickSummaryView();
		Thread.sleep(3000);
		String sActAssignedTo = objDVP.getAssignToAllSummary().trim();
		UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sAssignedTo, "Summary AssignedTo:  " + sMessage, node);
		
		String sActSummaryText = objDVP.getArraySummary(1).trim();
		UtilityCustomFunctions.fSoftAssert(sActSummaryText, sText, "Summary Text:  " + sMessage, node);
		
		String sActMobileNumber = objDVP.getArraySummary(2).trim();
		String sMobNMArray[] = sActMobileNumber.split("\\s+");
		sActMobileNumber = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
		System.out.println("Actual: " + sActMobileNumber);
		
		System.out.println("actual mobile number: " + sActMobileNumber);
		UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sMobileNumber, "Summary Mobile Number:  " + sMessage, node);
		
		String sActEmail = objDVP.getArraySummary(3).trim();
		UtilityCustomFunctions.fSoftAssert(sActEmail, sEmail, "Summary Email :  " + sMessage, node);
		
		String sActPickListValue = objDVP.getArraySummary(4).trim();
		UtilityCustomFunctions.fSoftAssert(sActPickListValue, sPickListValue, "Summary PicListValue :  " + sMessage, node);
		
		String sActMultiComboValues = objDVP.getArraySummary(5).trim();
		UtilityCustomFunctions.fSoftAssert(sActMultiComboValues, sMultiComboValues, "Summary MuultiCombo Values :  " + sMessage, node);
		System.out.println("Actual Summary sActMultiComboValues: " + sActMultiComboValues);
		
		String sActCity = objDVP.getArraySummary(6).trim();
		UtilityCustomFunctions.fSoftAssert(sActCity, sCity, "Summary City:  " + sMessage, node);
		
		String sActState = objDVP.getArraySummary(7).trim();
		UtilityCustomFunctions.fSoftAssert(sActState, sState, "Summary State:  " + sMessage, node);
		
		String sActCountry = objDVP.getArraySummary(8).trim();
		UtilityCustomFunctions.fSoftAssert(sActCountry, sCountry, "Summary Country:  " + sMessage, node);
		
		String sActCheckBox = objDVP.getArraySummary(9).trim();
		UtilityCustomFunctions.fSoftAssert(sActCheckBox, sCheckBox, "Summary CheckBox:  " + sMessage, node);
		
		String sActDate = objDVP.getArraySummary(10).trim();
		UtilityCustomFunctions.fSoftAssert(sActDate, sDate, "Summary Date:  " + sMessage, node);
		
		String sActTime= objDVP.getArraySummary(11).trim();
		UtilityCustomFunctions.fSoftAssert(sActTime, sTime, "Summary Time:  " + sMessage, node);
		
		String sActDTandTime = objDVP.getArraySummary(12).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTandTime, sDateandTime, "Summary Date & Time :  " + sMessage, node);
		
		String sActRelatedModule = objDVP.getArraySummary(13).trim();
		UtilityCustomFunctions.fSoftAssert(sActRelatedModule, sRelatedModule, "Summary Related Module :  " + sMessage, node);
		
		String sActUploadFile= objDVP.getUploadFileText().trim();
		
		if(sActUploadFile.contains(sFileName)) {
			UtilityCustomFunctions.fSoftAssert(sFileName, sFileName, "Summary File Upload :  " + sMessage, node);
		}
		else {
			UtilityCustomFunctions.fSoftAssert(sActUploadFile, sFileName, "Summary File Upload :  " + sMessage, node);
		}
		System.out.println("Actual Summary sActUploadFile: " + sActUploadFile);
		
		String sActName = objDVP.getArraySummary(14).trim();
		String sNameArray[] = sActName.split("\\s+");
		sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActName, sName, "Summary Name:  " + sMessage, node);
		String sActNumber = objDVP.getArraySummary(15).trim();
		UtilityCustomFunctions.fSoftAssert(sActNumber, sNumber, "Summary Number:  " + sMessage, node);
		
		String sActCurrency = objDVP.getArraySummary(16).trim();
		UtilityCustomFunctions.fSoftAssert(sActCurrency, sCurrency, "Summary Currency:  " + sMessage, node);
		
		String sActUrl = objDVP.getSummaryUrl().trim();
		UtilityCustomFunctions.fSoftAssert(sActUrl, sUrl, "Summary Url:  " + sMessage, node);
		
		String sActEnq_Name=objDVP.getArraySummary(18).trim();
		String sEnqNameArray[] = sActEnq_Name.split("\\s+");
		sActEnq_Name = sEnqNameArray[0].trim() + " " + sEnqNameArray[1].trim();
		System.out.println("Actual: " + sActEnq_Name);
		
		System.out.println("Actual Summary Eqnuiry Name: " + sActEnq_Name);
		
		sEnquiry_Name = sEnqNamePrefix + " " + sEnquiry_Name;
		
		UtilityCustomFunctions.fSoftAssert(sActEnq_Name, sEnquiry_Name, "Summary Enquiry Name:  " + sMessage, node);
		
		String sActEnq_Email=objDVP.getArraySummary(19).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_Email, sEnquiry_Email, "Summary Enquiry Email:  " + sMessage, node);
		
		String sActEnq_Text=objDVP.getArraySummary(20).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_Text, sEnquiry_Text, "Summary Enquiry Text:  " + sMessage, node);
		
		String sActEnq_TextArea=objDVP.getArraySummary(21).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_TextArea, sEnquiry_TextArea, "Summary Enquiry Text Area:  " + sMessage, node);
		
		String sActEnq_Date=objDVP.getArraySummary(22).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_Date, sEnquiry_Date, "Summary Enquiry Date:  " + sMessage, node);
		
		
		sEnquiry_PhoneNumber = sEnquiry_PN_Prefix + " " + sEnquiry_PhoneNumber ; 
		
		String sActEnq_PhoneNumber=objDVP.getArraySummary(23).trim();
		System.out.println("Actual Summary Phone Number: " + sActEnq_PhoneNumber);
		
		
		String sEnqPNArray[] = sActEnq_PhoneNumber.split("\\s+");
		sActEnq_PhoneNumber = sEnqPNArray[0].trim() + " " + sEnqPNArray[1].trim();
		System.out.println("Actual: " + sActEnq_PhoneNumber);
		
		
		UtilityCustomFunctions.fSoftAssert(sActEnq_PhoneNumber, sEnquiry_PhoneNumber, "Summary Enquiry Phone Number:  " + sMessage, node);
		
		String sActEnq_Category=objDVP.getArraySummary(24).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_Category, sEnquiry_category, "Summary Enquiry Category:  " + sMessage, node);
		

		
		
		
		
		
		//Details View
		objDVP.clickDetailView();
		Thread.sleep(300);
		String sActDTAssignedTo = objDVP.getArrayDetails(1).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sAssignedTo, "Detail View AssignedTo:  " + sMessage, node);
		
		String sActDTText= objDVP.getArrayDetails(2).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTText, sText, "Detail View Text:  " + sMessage, node);
		
		String sActDTMobileNumber= objDVP.getArrayDetails(3).trim();
		String sDTMobileNMArray[] = sActDTMobileNumber.split("\\s+");
		sActDTMobileNumber = sDTMobileNMArray[0].trim() + " " + sDTMobileNMArray[1].trim();
		
		System.out.println("actual mobile number: " + sActMobileNumber);
		UtilityCustomFunctions.fSoftAssert(sActDTMobileNumber, sMobileNumber, "Detail View Mobile Number:  " + sMessage, node);
		
		String sActDTEmail= objDVP.getArrayDetails(4).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEmail, sEmail, "Detail View Email :  " + sMessage, node);
		
		String sActDTPicList= objDVP.getArrayDetails(5).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTPicList, sPickListValue, "Detail View PicListValue :  " + sMessage, node);
		
		String sActDTMultiCombo= objDVP.getArrayDetails(6).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTMultiCombo, sMultiComboValues, "Detail View MuultiCombo Values :  " + sMessage, node);
		
		String sActDTCity= objDVP.getArrayDetails(7).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCity, sCity, "Detail View City:  " + sMessage, node);
		
		String sActDTState= objDVP.getArrayDetails(8).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTState, sState, "Detail View Stat:  " + sMessage, node);
		
		
		
		String sActDTCountry= objDVP.getArrayDetails(9).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCountry, sCountry, "Detail View Country:  " + sMessage, node);
		
		String sActDTChekBox= objDVP.getArrayDetails(10).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTChekBox, sCheckBox, "Detail View CheckBox:  " + sMessage, node);
		
		String sActDTDate= objDVP.getArrayDetails(11).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTDate, sDate, "Detail View Date:  " + sMessage, node);
		
		String sActDTTime= objDVP.getArrayDetails(12).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTTime, sTime, "Detail View Time:  " + sMessage, node);
		
		String sActDTDateandTime= objDVP.getArrayDetails(13).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTDateandTime, sDateandTime, "Detail View Date & Time :  " + sMessage, node);
		
		String sActDTRelModule= objDVP.getArrayDetails(14).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTRelModule, sRelatedModule, "Detail View Related Module :  " + sMessage, node);
		
		String sActDTUploadFile= objDVP.getUploadFileText().trim();
		if(sActDTUploadFile.contains(sFileName)) {
			UtilityCustomFunctions.fSoftAssert(sFileName, sFileName, "Detail View File Upload :  " + sMessage, node);
		}
		else {
			UtilityCustomFunctions.fSoftAssert(sActDTUploadFile, sFileName, "Detail View Upload :  " + sMessage, node);
		}
		
		String sActDTName= objDVP.getArrayDetails(16).trim();
		String sDTNameArray[] = sActDTName.split("\\s+");
		sActDTName = sDTNameArray[0].trim() + " " + sDTNameArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTName, sName, "Detail View Name:  " + sMessage, node);
		
		String sActDTNumber= objDVP.getArrayDetails(17).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTNumber, sNumber, "Detail View Number:  " + sMessage, node);
		
		String sActDTCurrency= objDVP.getArrayDetails(18).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCurrency, sCurrency, "Detail View Currency:  " + sMessage, node);
		
		String sActDTUrl= objDVP.getSummaryUrl().trim();
		UtilityCustomFunctions.fSoftAssert(sActDTUrl, sUrl, "Detail View Url:  " + sMessage, node);
		
		String sActDTEnqName = objDVP.getArrayDetails(20).trim();
		
		String sDTEnqNameArray[] = sActDTEnqName.split("\\s+");
		sActDTEnqName = sDTEnqNameArray[0].trim() + " " + sDTEnqNameArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqName, sEnquiry_Name, "Detail View Enquiry Name:  " + sMessage, node);
		
		String sActDTEnqEmail= objDVP.getArrayDetails(21).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqEmail, sEnquiry_Email, "Detail View Enquiry Email:  " + sMessage, node);
		
		String sActDTEnqText= objDVP.getArrayDetails(22).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqText, sEnquiry_Text, "Detail View Enquiry Text:  " + sMessage, node);
		
		String sActDTEnqTextArea= objDVP.getArrayDetails(23).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqTextArea, sEnquiry_TextArea, "Detail View Enquiry Text Area:  " + sMessage, node);
		
		String sActDTEnqDate= objDVP.getArrayDetails(24).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqDate, sEnquiry_Date, "Detail View Enquiry Date:  " + sMessage, node);
		
		String sActDTEnqPN= objDVP.getArrayDetails(25).trim();
		String sEnqActDTArray[] = sActDTEnqPN.split("\\s+");
		sActDTEnqPN = sEnqActDTArray[0].trim() + " " + sEnqActDTArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqPN, sEnquiry_PhoneNumber, "Detail View Enquiry Phone Number:  " + sMessage, node);
		
		String sActDTEnqCategory= objDVP.getArrayDetails(26).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqCategory, sEnquiry_category, "Detail View Enquiry Category:  " + sMessage, node);
		
		if(isNotify.equalsIgnoreCase("Yes")){
			driver.close();
		}
		
		System.out.println("Inside Validate All fields: isNotify" + isNotify);
	}
	
	
	
}
