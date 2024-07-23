package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;


import testBase.BaseClass;
import pageObjects.AllListPage;
import pageObjects.CRMSettingsPage;
import pageObjects.CreateModuleDataPage;
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.IndvControlsPage;
import pageObjects.LoginPage;
import pageObjects.NotificationsPage;
import pageObjects.PHPMyAdminPage;
import pageObjects.SummaryViewPage;
import pageObjects.UserPage;
import pageObjects.WebFormsPage;
import pageObjects.WorkFlowPage;
import utilities.UtilityCustomFunctions;
import utilities.ExcelUtility;

public class CRMReUsables extends BaseClass {
		CRMSettingsPage objCRMSTngs = new CRMSettingsPage(driver);
		 
		public String getModuleRecordId() throws MalformedURLException {
			String sCurrRecordId="";
			try {
			URL sCurrentUrl = new URL(driver.getCurrentUrl());
			String sUrlQuery = sCurrentUrl.getQuery();
			String sQryArray[] =sUrlQuery.split("=");
			for(int i = 0;i<sQryArray.length;i++) {
				System.out.println("Split - " + i + "Value is: " + sQryArray[i]);
				if(i==3) {
					String sArrayRecId[] = sQryArray[i].split("&");
					System.out.println("Record Id is:" + sArrayRecId[0]);
					sCurrRecordId = sArrayRecId[0];
					break;
				}
			}
			}catch(Exception e) {
				sCurrRecordId = "0";
				System.out.println(e.getCause());
			}
			return sCurrRecordId;
		}
	
		public String fOpenNotification(String sCurrWinHandle) throws Exception {
		String sActualWindow = "";
		try {
		System.out.println("fOPenNotification function started in ..");
		
		NotificationsPage objNotfy = new NotificationsPage(driver);
		objNotfy.clickNotificatons();

		objNotfy.clickNotifyFirstMsg();
		Thread.sleep(2000);
		logger.info("Notification detail view opened");
		
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
		}catch(Exception e) {
			System.out.println(e.getCause());
			Assert.fail("Notification Failed opening");
			
		}
		return sActualWindow;

	}
	public String fgetNotificationCount() throws Exception {
		NotificationsPage objNP = new NotificationsPage(driver);
		String sCount = objNP.getNotificatonCount();
		return sCount;
				
	}
	
	
	public void fValModuleView(String sEnv, String sExcelName,String sSheetName, String sMessage,String sAssignedTo,ExtentTest node) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\" + sExcelName + "Test.xlsx" ;	
		}
		else {
			sPath=".\\testData\\" + sExcelName + "Live.xlsx" ;
		}//get the test data sheet
		
		ExcelUtility xlValObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlValObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlValObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		String sExpModuleName = xlValObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlValObj.getCellData(sSheetName, 1, 1);
//		String sAssignedTo = xlValObj.getCellData(sSheetName, 1, 2);
		String sPhoneNoumber=xlValObj.getCellData(sSheetName, 1, 3);
		String sNumberField=xlValObj.getCellData(sSheetName, 1, 4);
		String sEmail=xlValObj.getCellData(sSheetName, 1, 5);
		String sPickListItem=xlValObj.getCellData(sSheetName, 1, 6);
		String sEnterYourNumber=xlValObj.getCellData(sSheetName, 1, 7);
		String sExecutionCondition=xlValObj.getCellData(sSheetName, 1, 8);
		String sActionType=xlValObj.getCellData(sSheetName, 1, 9);
		String sActionTitle=xlValObj.getCellData(sSheetName, 1, 10);
		String sDisplayModuleName=xlValObj.getCellData(sSheetName, 1, 42);
		String sNotifyTemplateMsg=xlValObj.getCellData(sSheetName, 1, 51);
		System.out.println("Print Notify Template Message:" + sNotifyTemplateMsg);
		String sRecordId="";
		
		System.out.println("Module Name:  " + sExpModuleName);
		
		
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

		sPhoneNoumber = "+91" + " " + sPhoneNoumber;
		// Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name", node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle, sPhoneNoumber, "Phone Number", node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle, sEmail, "Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle, sPickListItem, "Piclist Value", node);

		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		// Summary Actual Values
		String sActPNSummary = objDVP.getPhoneNMSummary();
		System.out.println("Actual raw: " + sActPNSummary.trim());
		sActPNSummary = sActPNSummary.trim();
		String sPNArray[] = sActPNSummary.split("\\s+");
		System.out.println("Array size: " + sPNArray.length);
		sActPNSummary = sPNArray[0].trim() + " " + sPNArray[1].trim();
		System.out.println("Actual: Summary" + sActPNSummary);
		String sActNFSummary = objDVP.getNumberFieldSummary();
		Thread.sleep(1000);
		String sActEMSummary = objDVP.getEmailSummary();
		Thread.sleep(1000);
		String sActMnuSummary = objDVP.getMenuItemSummary();
		Thread.sleep(1000);
		String sActEYNSummary = objDVP.getEYNumberSummary();
		Thread.sleep(1000);
		// Summary Values Validation
		UtilityCustomFunctions.fSoftAssert(sActPNSummary, sPhoneNoumber, "Summary Phone Number", node);
		UtilityCustomFunctions.fSoftAssert(sActNFSummary, sNumberField, "Summary Number Field", node);
		UtilityCustomFunctions.fSoftAssert(sActEMSummary, sEmail, "Summary Email", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuSummary, sPickListItem, "Summary Picklist Item", node);
		UtilityCustomFunctions.fSoftAssert(sActEYNSummary, sEnterYourNumber, "Summary Enter Your Number", node);

		// Click Detail View
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		// Detail View Actual Values
		String sActAsgnToDTView = objDVP.getAssignedToDTView();

		String sActPNDTView = objDVP.getPhoneNMDTView();

		String sPNArrayDT[] = sActPNDTView.split("\\s+");
		sActPNDTView = sPNArrayDT[0].trim() + " " + sPNArrayDT[1].trim();
		System.out.println("Actual: DTView : " + sActPNDTView);
		String sActNumberField = objDVP.getNumberFieldDTView();
		
		Thread.sleep(3000);
		objDVP.fClickDetailBlockB();
		Thread.sleep(3000);
			
		String sActEMDTView = objDVP.getEmailDTView();
		Thread.sleep(1000);
		String sActMnuDTView = objDVP.getMenuListDTView();
		Thread.sleep(1000);
		String sActEYVDT = objDVP.getEYVDTView();
		Thread.sleep(1000);
		// Detail View Validation
		System.out.println("Actual Assigned To:" + sActAsgnToDTView);
		System.out.println("Expected Assigned To:" + sAssignedTo);
		UtilityCustomFunctions.fSoftAssert(sActAsgnToDTView, sAssignedTo, "Detail View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sActPNDTView, sPhoneNoumber, "Detail View Phone Numer", node);
		UtilityCustomFunctions.fSoftAssert(sActNumberField, sNumberField, "Detail View Number Field", node);
		
		
		UtilityCustomFunctions.fSoftAssert(sActEMDTView, sEmail, "Detail View Email", node);
		
		UtilityCustomFunctions.fSoftAssert(sActMnuDTView, sPickListItem, "Detail View Menu Item", node);
		UtilityCustomFunctions.fSoftAssert(sActEYVDT, sEnterYourNumber, "Detail View Enter Your Number", node);

		// Title & NavBar Validation again
		String aActModuleName1 = objDVP.getNavBarModuleName();
		Thread.sleep(1000);
//		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName, "Module Name", node);
		// Title Actual Values
		String sActPNTitle1 = objDVP.getPhoneNMTitle();
		Thread.sleep(1000);
		String sPNTitleArray1[] = sActPNTitle1.split("\\s+");
		sActPNTitle1 = sPNTitleArray1[0].trim() + " " + sPNTitleArray1[1].trim();
		System.out.println("Actual: " + sActPNTitle1);
		Thread.sleep(1000);
		String sActEMTitle1 = objDVP.getEmailTitle();
		Thread.sleep(1000);
		String sActMnuTitle1 = objDVP.getMenuItemTitle();
		Thread.sleep(1000);
		// Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName, "Module Name Title", node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle1, sPhoneNoumber, "Phone Number Title", node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle1, sEmail, "Email Title", node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle1, sPickListItem, "Piclist Value Title", node);

	}

		public void	fVerifyETNotificationSummary(String sEnv, String sExcelName,String sSheetName, String sMessage,ExtentTest node) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\" + sExcelName + "Test.xlsx" ;	
		}
		else {
			sPath=".\\testData\\" + sExcelName + "Live.xlsx" ;
			}//get the test data sheet

			ExcelUtility xlAddObj = new ExcelUtility(sPath);
			UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
			
			int iRowCount = xlAddObj.getRowCount(sSheetName);
			System.out.println("Total rows: " + iRowCount);
			UtilityCustomFunctions.logWriteConsole("Row Count to Add Values: " + iRowCount);

			int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);

			UtilityCustomFunctions.logWriteConsole("Col Count is: " + iColCount);	
			UtilityCustomFunctions.logWriteConsole("Extracting DataSheet Values started...");

			//DataSheet Values Extracted

			String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
			String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 1);
			String sExpAssignedTo = xlAddObj.getCellData(sSheetName, 1, 2);
			String sMobilePrefix = xlAddObj.getCellData(sSheetName, 1, 3);
			String sMobileNumber = xlAddObj.getCellData(sSheetName, 1, 4);

			String sEmail=xlAddObj.getCellData(sSheetName, 1, 5);
			String sText=xlAddObj.getCellData(sSheetName, 1, 6);
			String sMultiComboValues=xlAddObj.getCellData(sSheetName, 1, 7);
			String sTextArea=xlAddObj.getCellData(sSheetName, 1, 8);
			String sCheckBox=xlAddObj.getCellData(sSheetName, 1, 9);
			
			if(sCheckBox.equalsIgnoreCase("ON")) {
				sCheckBox = "Yes";
			}
			else {
				sCheckBox = "No";
			}
			
			String sDate=xlAddObj.getCellData(sSheetName, 1, 10);
			String sPickList=xlAddObj.getCellData(sSheetName, 1, 11);
			String sFile=xlAddObj.getCellData(sSheetName, 1, 12);
			String sDateandTime =xlAddObj.getCellData(sSheetName, 1, 13);
			String sTime =xlAddObj.getCellData(sSheetName, 1, 14);
			String sNamePrefix =xlAddObj.getCellData(sSheetName, 1, 15);
			String sName =xlAddObj.getCellData(sSheetName, 1, 16);
			String sNumber =xlAddObj.getCellData(sSheetName, 1, 17);
			String sCurrency=xlAddObj.getCellData(sSheetName, 1, 18);
			String sURL=xlAddObj.getCellData(sSheetName, 1, 19);
			String sCity=xlAddObj.getCellData(sSheetName, 1, 20);
			String sState=xlAddObj.getCellData(sSheetName, 1, 21);
			String sCountry=xlAddObj.getCellData(sSheetName, 1, 22);
			String sRelatedModText =xlAddObj.getCellData(sSheetName, 1, 23);
			System.out.println("Module Name:  " + sExpModuleName);
			UtilityCustomFunctions.logWriteConsole("Before Verify Detail Summary Module data");

				SummaryViewPage objSVP = new SummaryViewPage(driver);
				DetailViewPage objDVP = new DetailViewPage(driver);
				
				Thread.sleep(6000);
				objDVP.fSetToggleHeader(true);
				objDVP.fSetDetailVew(false);
				
				
				String sSequenceNo = objSVP.getCurrentSequenceNo();
				xlAddObj.setCellData(sSheetName, 1, 32, sSequenceNo);
				
				//Title Validation on Summary SummaryViewPage
				String aActModuleName = objDVP.getNavBarModuleName();
				UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name in SummaryDetail View Page", node);
				
				// Title Actual Values
				Thread.sleep(2000);
				String sActAssignedTo =objSVP.getTitleAssignedTo();
				String sActMobileNumber =objSVP.getTitlePhoneNumber();
				String sActEmail =objSVP.getTitleEmail();
						
//				String sActPNTitle = objDVP.getPhoneNMTitle();
//				String sPNTitleArray[] = sActPNTitle.split("\\s+");
//				sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
//				System.out.println("Actual: " + sActPNTitle);
//				String sActEMTitle = objDVP.getEmailTitle();
//				String sActMnuTitle = objDVP.getMenuItemTitle();

				String sPhoneNoumber = sMobilePrefix + " " + sMobileNumber;
				// Title Validations
				UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sExpAssignedTo, "Assigned To-Summary Title", node);
				UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sPhoneNoumber, "Phone Number - Summary Title", node);
				UtilityCustomFunctions.fSoftAssert(sActEmail, sEmail, "Email - Summary Title", node);
				
				//Summary Page Validation
				String sActSummaryAssignedTo = objDVP.getSummaryAssignTo();
				UtilityCustomFunctions.fSoftAssert(sActSummaryAssignedTo, sExpAssignedTo, "Assigned To - Summary View Page : ", node);
				
				sActMobileNumber = objDVP.getArraySummary(1).trim();
				String sMobNMArray[] = sActMobileNumber.split("\\s+");
				sActMobileNumber = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
				UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sPhoneNoumber, "Mobile Number - Summary View Page : " + sMessage, node);
				
				sActEmail = objDVP.getArraySummary(2).trim();
				UtilityCustomFunctions.fSoftAssert(sActEmail, sEmail, "Email - Summary View Page: " + sMessage, node);
				
				String sActSummaryText = objDVP.getArraySummary(3).trim();
				UtilityCustomFunctions.fSoftAssert(sActSummaryText, sText, "Text - Summary View Page:" + sMessage, node);
				
				String sActMultiComboValues = objDVP.getArraySummary(4).trim();
				UtilityCustomFunctions.fSoftAssert(sActMultiComboValues, sMultiComboValues, "MultiComboValues - Summary View Page:  " + sMessage, node);
				System.out.println("Actual Summary sActMultiComboValues: " + sActMultiComboValues);
				
				String sActTextArea=objDVP.getArraySummary(5).trim();
				sActTextArea = UtilityCustomFunctions.fArrayConcat(sActTextArea);
				sTextArea = UtilityCustomFunctions.fArrayConcat(sTextArea);
				
				UtilityCustomFunctions.fSoftAssert(sActTextArea, sTextArea, "Text Area - Summary View Page:  " + sMessage, node);
				
				String sActCheckBox = objDVP.getArraySummary(6).trim();
				UtilityCustomFunctions.fSoftAssert(sActCheckBox, sCheckBox, "CheckBox - Summary View Page:   " + sMessage, node);
				
				String sActDate = objDVP.getArraySummary(7).trim();
				UtilityCustomFunctions.fSoftAssert(sActDate, sDate, "Date - Summary View Page:  " + sMessage, node);

				String sActPickListValue = objDVP.getArraySummary(8).trim();
				UtilityCustomFunctions.fSoftAssert(sActPickListValue, sPickList, "PickList - Summary View Page:  " + sMessage, node);
				
//				String sActSequenceNo= objDVP.getArraySummary(9).trim();
//				UtilityCustomFunctions.fSoftAssert(sActSequenceNo, sSequenceNo, "Sequence Number - Summary View Page:  " + sMessage, node);
				
				String sActUploadFile="";
				if(objDVP.getUploadFileText()!=null) {
					sActUploadFile= objDVP.getUploadFileText();
				}
				if(sActUploadFile.contains(sFile)) {
					UtilityCustomFunctions.fSoftAssert(sFile, sFile, "File Upload - Summary View Page: " + sMessage, node);
				}
				else {
					UtilityCustomFunctions.fSoftAssert(sActUploadFile, sFile, "File Upload - Summary View Page: " + sMessage, node);
				}
				
				String sActDTandTime = objDVP.getArraySummary(9).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTandTime, sDateandTime, "Date & Time - Summary View Page:   " + sMessage, node);
				
				String sActTime= objDVP.getArraySummary(10).trim();
				UtilityCustomFunctions.fSoftAssert(sActTime, sTime, "Time - Summary View Page:   " + sMessage, node);
				
				String sExpName = sNamePrefix + " " + sName;
				String sActName = objDVP.getArraySummary(11).trim();
				if(sActName== null || sActName.isEmpty()) {
					String sNameArray[] = sActName.split("\\s+");
					sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
					UtilityCustomFunctions.logWriteConsole("ActualName:"+sActName+"ExpectedName:"+sExpName);
					UtilityCustomFunctions.fSoftAssert(sActName, sExpName, "Name - Summary View Page:   " + sMessage, node);
				}
				else {
					UtilityCustomFunctions.fSoftAssert("", sExpName, "Name - Summary View Page:   " + sMessage, node);
				}
				String sActNumber = objDVP.getArraySummary(12).trim();
				UtilityCustomFunctions.fSoftAssert(sActNumber, sNumber, "Number - Summary View Page:   " + sMessage, node);
				
				String sActCurrency = objDVP.getArraySummary(13).trim();
				UtilityCustomFunctions.fSoftAssert(sActCurrency, sCurrency, "Currency - Summary View Page:   " + sMessage, node);
				
				String sActUrl = objDVP.getSummaryUrl().trim();
				if(sActUrl!=null) {
					UtilityCustomFunctions.fSoftAssert(sActUrl, sURL, "URL - Summary View Page:  " + sMessage, node);
				}
				String sActCity="";
				if(objDVP.getArraySummary(15).trim()!=null) {
					UtilityCustomFunctions.fSoftAssert(sActCity, sCity, "City - Summary View Page: " + sMessage, node);
				}
				
				String sActState = objDVP.getArraySummary(16).trim();
				UtilityCustomFunctions.fSoftAssert(sActState, sState, "State - Summary View Page:" + sMessage, node);
				
				String sActCountry = objDVP.getArraySummary(17).trim();
				UtilityCustomFunctions.fSoftAssert(sActCountry, sCountry, "Country - Summary View Page:  " + sMessage, node);
				
				String sActRelatedModuleText = objDVP.getArraySummary(18).trim();
				UtilityCustomFunctions.fSoftAssert(sActRelatedModuleText, sRelatedModText, "Related Module Text - Summary View Page:  " + sMessage, node);
				
				//Detail View Page Validation
				objDVP.fSetDetailVew(true);
				Thread.sleep(3000);
				driver.navigate().refresh();
				Thread.sleep(3000);
				
				String sActDTAssignedTo = objDVP.getArrayDetails(1).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sExpAssignedTo, "AssignedTo - Detail View Page:   " + sMessage, node);
				
				String sActDTMobileNumber= objDVP.getArrayDetails(2).trim();
				UtilityCustomFunctions.logWriteConsole("sActDTMobileNumber "+ sActDTMobileNumber);

				String sDTMobileNMArray[] = sActDTMobileNumber.split("\\s+");
				if(sDTMobileNMArray.length>=1) {
				 sActDTMobileNumber = sDTMobileNMArray[0].trim() + " " + sDTMobileNMArray[1].trim();
				}
				System.out.println("actual mobile number: " + sActDTMobileNumber);
				UtilityCustomFunctions.fSoftAssert(sActDTMobileNumber, sPhoneNoumber, "Mobile Number - Detail View Page:   " + sMessage, node);
				
				String sActDTEmail= objDVP.getArrayDetails(3).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTEmail, sEmail, "Email - Detail View Page: " + sMessage, node);
				
				String sActDTText= objDVP.getArrayDetails(4).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTText, sText, "Text - Detail View Page:  " + sMessage, node);
				
				String sActDTMultiCombo= objDVP.getArrayDetails(5).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTMultiCombo, sMultiComboValues, "MultiCombo - Detail View Page:   " + sMessage, node);
				
				String sActDTTextArea= objDVP.getArrayDetails(6).trim();
				sActDTTextArea = UtilityCustomFunctions.fArrayConcat(sActDTTextArea);
				UtilityCustomFunctions.fSoftAssert(sActDTTextArea, sTextArea, "TextArea - Detail View Page:  " + sMessage, node);
				
				String sActDTChekBox= objDVP.getArrayDetails(7).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTChekBox, sCheckBox, "CheckBox - Detail View Page: " + sMessage, node);
				
				String sActDTDate= objDVP.getArrayDetails(8).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTDate, sDate, "Date - Detail View Page: " + sMessage, node);
				
				String sActDTPicList= objDVP.getArrayDetails(9).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTPicList, sPickList, "PickList - Detail View Page:   " + sMessage, node);
				
				String sActDTUploadFile = "";
				if(objDVP.getUploadFileText()!=null) {
					sActDTUploadFile= objDVP.getUploadFileText();
				}
				if(sActDTUploadFile.contains(sFile)) {
					UtilityCustomFunctions.fSoftAssert(sFile, sFile, "File Upload - Detail View Page:   " + sMessage, node);
				}
				else {
					UtilityCustomFunctions.fSoftAssert(sActDTUploadFile, sFile, "File Upload - Detail View Page:   " + sMessage, node);
				}
				
				String sActDTDateandTime= objDVP.getArrayDetails(12).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTDateandTime, sDateandTime, "Date & Time - Detail View Page: " + sMessage, node);

				String sActDTTime= objDVP.getArrayDetails(13).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTTime, sTime, "Time - Detail View Page:    " + sMessage, node);
				
				String sActDTName= objDVP.getArrayDetails(14).trim();
				String sDTNameArray[] = sActDTName.split("\\s+");
				sActDTName = sDTNameArray[0].trim() + " " + sDTNameArray[1].trim();
				UtilityCustomFunctions.logWriteConsole("ActualNameDT: "+sActDTName +" ExpectedNameDT: "+sExpName);
				UtilityCustomFunctions.fSoftAssert(sActDTName, sExpName, "Name - Detail View Page:  " + sMessage, node);
				
				String sActDTNumber= objDVP.getArrayDetails(15).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTNumber, sNumber, "Number - Detail View Page:  " + sMessage, node);
				
				String sActDTCurrency= objDVP.getArrayDetails(16).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTCurrency, sCurrency, "Currency - Detail View Page:  " + sMessage, node);
				
				String sActDTUrl= objDVP.getDTSummaryUrl().trim();
				UtilityCustomFunctions.fSoftAssert(sActDTUrl, sURL, "URL - Detail View Page:  " + sMessage, node);
				Thread.sleep(3000);
				
				String sActDTCity= objDVP.getArrayDetails(18).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTCity, sCity, "City - Detail View Page:   " + sMessage, node);

				String sActDTState= objDVP.getArrayDetails(19).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTState, sState, "State - Detail View Page:   " + sMessage, node);
				
				String sActDTCountry= objDVP.getArrayDetails(20).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTCountry, sCountry, "Country - Detail View Page:  " + sMessage, node);
				
				String sActDTRelatedModText= objDVP.getArrayDetails(21).trim();
				UtilityCustomFunctions.fSoftAssert(sActDTRelatedModText, sRelatedModText, "Related Module Text - Detail View Page:  " + sMessage, node);
				
		}
	
	public void fValNotifySummaryAndDetail(String sCurrRecordId, String sAssignedTo, String sNotifyTemplMsg, String sActionTitle, ExtentTest node)
			throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		DetailViewPage objDVP = new DetailViewPage(driver);
		String sStatus = "0";
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		
		// getActualSummaryDetails
		Thread.sleep(2000);
		String sActSMAssignedTo = objNFP.getSMAssignedTo();
		String sActSMStatus = objNFP.getSMStatus();
		String sActSMSummary = objNFP.getSMSummary();
		String sActSMTitle = objNFP.getSMActionTitle();
		// Validations
		UtilityCustomFunctions.fSoftAssert(sAssignedTo, sActSMAssignedTo, "Summary View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sStatus, sActSMStatus, "Summary View Status", node);
		UtilityCustomFunctions.fSoftAssert(sActSMSummary, sNotifyTemplMsg, "Summary View User", node);
		UtilityCustomFunctions.fSoftAssert(sActSMTitle, sActionTitle, "Summary View Action Title", node);
				
		// get Details
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		String sActDTAssignedTo = objNFP.getDTAssignedTo();
		String sActDTStatus = objNFP.getDTStatus();
		String sActDTSummary = objNFP.getDTSummary();
		String sActDTTitle = objNFP.getDTTitle();
		String sActDTModRecId = objNFP.getDTModRecId();
		// Validations
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sAssignedTo, "Detail View Assigned To", node);
		UtilityCustomFunctions.fSoftAssert(sActDTStatus, sStatus, "Detail View Status", node);
		UtilityCustomFunctions.fSoftAssert(sActDTSummary, sNotifyTemplMsg, "Detail View Summary", node);
		UtilityCustomFunctions.fSoftAssert(sActDTTitle, sActionTitle, "Detail View Action Title", node);
		UtilityCustomFunctions.fSoftAssert(sActDTModRecId, sCurrRecordId, "Detail View Module Record Id", node);


	}

	public void fClickSearch(String sRecordId,String sAssignedTo) throws Exception {
		AllListPage objALP = new AllListPage(driver);
		NotificationsPage objNFP = new NotificationsPage(driver);
		objALP.clickAllList();
		objALP.clickAllNotifications();
		Thread.sleep(3000);
		objNFP.setAssignedTo(sAssignedTo);
		objNFP.setRecordId(sRecordId);
		objNFP.clickSearchButton();

	}
	public void fClickFirstRecord() throws InterruptedException {
//		List<WebElement> tRowCount = driver.findElements(By.xpath("//tbody/tr"));
//		List<WebElement> tColCount = tRowCount.get(2).findElements(By.tagName("td"));
//		tColCount.get(2).click();
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		objCMD.clickExistingModData(1);
	}
	public void fgetTablevalues(String sAssignedTo, String sStatus, String sCreatedBy, String sNotifyTemplateMsg,
			String sExpTitle, String sRecordId, ExtentTest node) throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		List<WebElement> tRowCount = driver.findElements(By.xpath("//tbody/tr"));
		for (int i = 2; i <= tRowCount.size(); i++) {
			System.out.println("Current Row Number: " + i);
			List<WebElement> tColCount = tRowCount.get(i).findElements(By.tagName("td"));


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
			System.out.println("Expected Summary: " + sNotifyTemplateMsg);
			System.out.println("Expected Title: " + sExpTitle);
			System.out.println("Expected Record Id: " + sRecordId);

			UtilityCustomFunctions.fSoftAssert(sAssignedTo, sAssignedTo, "AssignedTo Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActStatus, sStatus, "Status in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActCreatedBy, sCreatedBy, "Created By in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActSummary, sNotifyTemplateMsg, "Summary in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActTitle, sExpTitle, "Workflow Title in Record Details", node);
			UtilityCustomFunctions.fSoftAssert(sActModRecId, sRecordId, "Record Id in Record Details", node);
			break;
		}
	}

	//WebForm Integration to CRM Module Validation
	public void fModuleTableValue(String sAssignedTo, String sMobilePrefix, String sMobileNumber, String sEmail,
			String sText, String sMultiSelect, String sMultiChoice,ExtentTest node) throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		List<WebElement> tRowCount = driver.findElements(By.xpath("//tbody/tr"));
//		for (int i = 2; i <= tRowCount.size(); i++) {
			System.out.println("Current Row Number: " + 2);
			List<WebElement> tColCount = tRowCount.get(2).findElements(By.tagName("td"));


			String sActAssignedTo = UtilityCustomFunctions.getText(driver, tColCount.get(1));
			String sActMobilePrefix = UtilityCustomFunctions.getText(driver, tColCount.get(2));
			String sActMobileNumber = UtilityCustomFunctions.getText(driver, tColCount.get(3));
			String sActEmail = UtilityCustomFunctions.getText(driver, tColCount.get(4));
			String sActText = UtilityCustomFunctions.getText(driver, tColCount.get(5));
			String sActMultiSelect = UtilityCustomFunctions.getText(driver, tColCount.get(6));
			String sActState= UtilityCustomFunctions.getText(driver, tColCount.get(7));
			sActAssignedTo = UtilityCustomFunctions.getText(driver, tColCount.get(1));

			System.out.println("Actual Assigned: " + sActAssignedTo);
			System.out.println("Actual MobileNoPrefix: " + sActMobilePrefix);
			System.out.println("Actual MobileNumber: " + sActMobileNumber);
			System.out.println("Actual Email: " + sActEmail);
			System.out.println("Actual Text: " + sActText);
			System.out.println("Actual sActMultiSelect: " + sActMultiSelect);

			System.out.println("Expected AssignedTo: " + sAssignedTo);
			System.out.println("Expected MobileNoPrefix: " + sMobilePrefix);
			System.out.println("Expected MobileNumber: " + sMobileNumber);
			System.out.println("Expected Email: " + sEmail);
			System.out.println("Expected Text: " + sText);
			System.out.println("Expected MultiSelect: " + sMultiSelect);

			if(sActAssignedTo.contains(sAssignedTo)) {
				UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sActAssignedTo, "AssignedTo Value", node);
			}
			else {
				UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sAssignedTo, "AssignedTo Value", node);
			}
			UtilityCustomFunctions.fSoftAssert(sActMobilePrefix, sMobilePrefix, "Mobile Number Prefix", node);
			UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sMobileNumber, "Mobile Number", node);
			UtilityCustomFunctions.fSoftAssert(sActEmail, sEmail, "Email", node);
			UtilityCustomFunctions.fSoftAssert(sActText, sText, "Text", node);
			UtilityCustomFunctions.fSoftAssert(sActMultiSelect, sMultiSelect, "MultiSelect", node);
			UtilityCustomFunctions.fSoftAssert(sActState, sMultiChoice, "MultiChoice State", node);
		}
	public void fNavigatetoWorkflow(String sModule) throws Exception {
		HomePage objHP = new HomePage(driver);
		CRMSettingsPage objCRMs = new CRMSettingsPage(driver);
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		// Click Avatar
		BaseClass.logger.info("Clicked Avatar");
		System.out.println("Clicked Avatar");
		objHP.clickAvatar();
		Thread.sleep(2000);
		objCRMs.clickMnuCRMSetting();
		BaseClass.logger.info("Clicked Menu CRM Setting");
		System.out.println("Clicked Menu CRM Setting");
		objCRMs.clickMnuOtherSetting();
		Thread.sleep(2000);
		BaseClass.logger.info("Clicked Menu Other Setting");
		System.out.println("Clicked Menu Other Setting");
		objCRMs.clickMnuItemWorkflow();
		Thread.sleep(2000);
		BaseClass.logger.info("Clicked Menu Item Workflow");
		System.out.println("Clicked Menu Item Workflow");
		objWFP.fClickWorkflowsList(sModule);
		Thread.sleep(2000);
		BaseClass.logger.info("Selected Module to View its Workflows");
		System.out.println("Selected Module to View its Workflows");

	}
	public void fNavigatetoUserMgmt() throws Exception {
		HomePage objHP = new HomePage(driver);
		CRMSettingsPage objCRMSTs = new CRMSettingsPage(driver);
		UserPage objUP = new UserPage(driver);
		// Click Avatar
		BaseClass.logger.info("Clicked Avatar");
		System.out.println("Clicked Avatar");
		objHP.clickAvatar();
		Thread.sleep(2000);
		objCRMSTs.clickMnuCRMSetting();
		BaseClass.logger.info("Clicked Menu CRM Setting");
		System.out.println("Clicked Menu CRM Setting");
		objCRMSTs.clickMnuUserAccesCtrl();
		Thread.sleep(2000);
		BaseClass.logger.info("Clicked Menu User Access Control");
		System.out.println("Clicked Menu User Access Control");
		objCRMSTs.clickMnuItemUser();
		Thread.sleep(2000);
		
	}
	public void fLoginCRM(String sUrl,String sCompName,String sUserName,String sPassword) throws InterruptedException, Exception {
		LoginPage objLP = new LoginPage(driver);
		if(objLP.isLoginPageDisplayed(sUrl)) {
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

	public void fWFSubmitSF(int iRowIndex,String sEnv,String sExcelName,String sSheetName,boolean IsAmend,ExtentTest node) throws Exception {
		
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
		IndvControlsPage  IndvObj = new IndvControlsPage(driver);
		logger.info("Excel file Utility instance created");
		
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
	
		String sBuildUrl = xlAddObj.getCellData(sSheetName, iRowIndex, 0);
		String sPN_Title = xlAddObj.getCellData(sSheetName, iRowIndex, 3);
		String sPN_Prefix = xlAddObj.getCellData(sSheetName, iRowIndex, 4);
		String sPN_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 5);
		String sEM_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 6);
		String sEM_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 7);
		String sXQ_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 8);
		String sXQ_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 9);
		String sMS_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 10);
		String sMS_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 11);
		String sRun_Flag=xlAddObj.getCellData(sSheetName, iRowIndex, 12);
		String sMC_Title = xlAddObj.getCellData("Sheet1", iRowIndex, 28);
		String sMC_Value = xlAddObj.getCellData("Sheet1", iRowIndex, 29);
		
		System.out.println(sRun_Flag);
		
		
		if(sRun_Flag.equalsIgnoreCase("Yes"))
		{
			fLaunchUrl(driver, sBuildUrl);
			//URL Validation 
			logger.info("Survey Form Url Launched..");
			String sActBuildUrl = driver.getCurrentUrl();
			if(sActBuildUrl.trim().equalsIgnoreCase(sBuildUrl)) {
				freport(sBuildUrl, "pass", node);
				logger.info("Build Url Pass");
			}
			else {
				logger.info("Build Url failing");
				freport("Browser URL Not Correct" , "fail",node);
				Assert.fail("Url Failed");
			}
//			PhoneNumber Control Validation
			if(IndvObj.bIsPhoneNoDisplayed()) {
				String sActPN_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("PN Control Title : " + sActPN_Title );
				String sExpPNN_Title = sPN_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActPN_Title.trim(),sExpPNN_Title,"Phone Number Control Title",node);
				logger.info("Validation of PhoneNumber Control Title") ;
				Thread.sleep(1000);
				 freport("Phone Number Conrol Present" , "pass",node);
				 logger.info("Phone Number Conrol Present");
				 String sRandPhoneNo= randomeNumber(); 
				 xlAddObj.setCellData("Sheet1", iRowIndex, 5, sRandPhoneNo);
				 IndvObj.setPhoneNumber(sRandPhoneNo);
				 freport("Phone Number Entered: " +sRandPhoneNo , "pass",node);
				 logger.info("Phone Number Entered");
					
			}
			else {
				 freport("Phone Number Conrol Missing" , "fail",node);
				 logger.info("Phone Number Conrol Missing");
				 Assert.fail("PhoneNumber Missing");
			}
			Thread.sleep(1000);
			IndvObj.clickPhoneNumberNext();
			logger.info("PhoneNumber Next Clicked") ;
			Thread.sleep(3000);
			
//			Email Control
			if(IndvObj.bEmailPresent()) {
				freport("Email Control Present" , "pass",node);
				logger.info("Email Control Present");
				String sActEM_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("Email Control Title : " + sActEM_Title);
				String sExpEMN_Title =sEM_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActEM_Title.trim(),sExpEMN_Title,"Email Control Title",node);
				logger.info("Validation of Email Control Title") ;
				IndvObj.setEmail(sEM_Value);
				logger.info("Email Id Entered") ;
				freport("Email Id Entered:" +sEM_Value , "pass",node);
				
			}else {
				freport("Email Control Missing" , "fail",node);
				logger.info("Email Control Missing");
				Assert.fail("Email Control Missing");
			}
			IndvObj.clickGeneralNext();
			logger.info("Email Next Clicked") ;
			Thread.sleep(1000);
			
//			TextQuestion Control Validation
			System.out.println("XQ not displayed: " + IndvObj.bIsTextQuestionDisplayed());
			if (IndvObj.bIsTextQuestionDisplayed()) {
				freport("Text Question Conrol Present", "pass", node);
				logger.info("Text Question Conrol Present");
				String sActTX_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("Text Question Choice Control Title : " + sActTX_Title);
				String sExpXQN_Title =sXQ_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActTX_Title.trim(), sExpXQN_Title, "TextQuestion Control Title", node);
				logger.info("Validation of TextQuestion Control Title");
				IndvObj.setTextAnswer(sXQ_Value);
				logger.info("Text Entered in TextAnswer Control");
				freport("Text Entered in TextAnswer Control:" + sXQ_Value, "pass", node);
				
			} else {
				freport("Text Question Conrol Missing", "fail", node);
				logger.info("Text Question Conrol Missing");
				Assert.fail("Text Question Conrol Missing");
			}
			IndvObj.clickGeneralNext();
			logger.info("Text Question Next Clicked");
			Thread.sleep(3000);
			
//			MultiSelect Control Validation
			if(IndvObj.bIsMultiSelectDisplayed()) {
				String sActMS_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("MS Control Title : " + sActMS_Title);
				String sExpMSN_Title =sMS_Title + " *";
				UtilityCustomFunctions.logWriteConsole(sActMS_Title);
				UtilityCustomFunctions.fSoftAssert(sActMS_Title.trim(),sExpMSN_Title,"MultiSelect Control Title",node);
				logger.info("Validation of MultiSelect Control Title") ;
				//Set MultiSelectValue
				UtilityCustomFunctions.logWriteConsole("MultiSelect Value:" +sMS_Value); 
				IndvObj.setDropdownMS(sMS_Value);
				logger.info("MultiSelect Value Selected") ;
				freport("MultiSelect Value Selected: " +sMS_Value , "pass",node);
				
			}
			else {
				 freport("MultiSelect Control Missing", "fail", node);
				 logger.info("MultiSelect Conrol Missing");
				 Assert.fail("MultiSelect Control Missing");
			}
			//MultiSelect Next Clicked
			IndvObj.clickGeneralNext();
			logger.info("MultiSelect Next Clicked");
			Thread.sleep(3000);
			
//			MultiChoice Control Validation
			if(IndvObj.bIsMultiChoiceDisplayed()) {

				String sActMC_Title = IndvObj.getHeadThreeTitle();
				logger.info(sActMC_Title +"Actual Title ");
				System.out.println(sActMC_Title);
				UtilityCustomFunctions.logWriteConsole("Multi Choice Control Title : " + sActMC_Title);
				String sExpMCN_Title =sMC_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActMC_Title.trim(), sExpMCN_Title, "MultiChoice Control Title",node);
				System.out.println("Multi Choice Value is: " +sMC_Value);
				IndvObj.selectOneItem(sMC_Value);
				logger.info("MultiChoice Item Selected");
				freport("MultiChoice Item Selected:" +sMC_Value , "pass",node);
				}
			else
				{
				logger.info("MultiChoice control Missing");
				freport("MultiChoice Control Missing" , "fail",node);
				Assert.fail("MultiChoice Not Displayed");
			}
			
			Thread.sleep(3000);
			IndvObj.clickSubmit();
			logger.info("MultiChoice Next Clicked");
			Thread.sleep(3000);
			
		}
	}
	
	public void fConditionSFSubmit(int iRowIndex,String sEnv,String sExcelName,String sSheetName,boolean IsAmend,ExtentTest node) throws Exception {
		
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
		IndvControlsPage  IndvObj = new IndvControlsPage(driver);
		logger.info("Excel file Utility instance created");
		
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
	
		String sBuildUrl = xlAddObj.getCellData(sSheetName, iRowIndex, 0);
		String sPN_Title = xlAddObj.getCellData(sSheetName, iRowIndex, 3);
		String sPN_Prefix = xlAddObj.getCellData(sSheetName, iRowIndex, 4);
		String sPN_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 5);
		String sEM_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 6);
		String sEM_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 7);
		String sXQ_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 8);
		String sXQ_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 9);
		String sMS_Title=xlAddObj.getCellData(sSheetName, iRowIndex, 10);
		String sMS_Value=xlAddObj.getCellData(sSheetName, iRowIndex, 11);
        String sMC_Title = xlAddObj.getCellData("Sheet1", iRowIndex, 12);
		String sMC_Value = xlAddObj.getCellData("Sheet1", iRowIndex, 13);
        String sRun_Flag=xlAddObj.getCellData(sSheetName, iRowIndex, 14);
		
		
		System.out.println(sRun_Flag);
		
		
		if(sRun_Flag.equalsIgnoreCase("Yes"))
		{
			fLaunchUrl(driver, sBuildUrl);
			//URL Validation 
			logger.info("Survey Form Url Launched..");
			String sActBuildUrl = driver.getCurrentUrl();
			if(sActBuildUrl.trim().equalsIgnoreCase(sBuildUrl)) {
				freport(sBuildUrl, "pass", node);
				logger.info("Build Url Pass");
			}
			else {
				logger.info("Build Url failing");
				freport("Browser URL Not Correct" , "fail",node);
				Assert.fail("Url Failed");
			}
//			PhoneNumber Control Validation
			if(IndvObj.bIsPhoneNoDisplayed()) {
				String sActPN_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("PN Control Title : " + sActPN_Title );
				String sExpPNN_Title = sPN_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActPN_Title.trim(),sExpPNN_Title,"Phone Number Control Title",node);
				logger.info("Validation of PhoneNumber Control Title") ;
				Thread.sleep(1000);
				 freport("Phone Number Conrol Present" , "pass",node);
				 logger.info("Phone Number Conrol Present");
				 String sRandPhoneNo= randomeNumber(); 
				 xlAddObj.setCellData("Sheet1", iRowIndex, 5, sRandPhoneNo);
				 IndvObj.setPhoneNumber(sRandPhoneNo);
				 freport("Phone Number Entered: " +sRandPhoneNo , "pass",node);
				 logger.info("Phone Number Entered");
					
			}
			else {
				 freport("Phone Number Conrol Missing" , "fail",node);
				 logger.info("Phone Number Conrol Missing");
				 Assert.fail("PhoneNumber Missing");
			}
			Thread.sleep(1000);
			IndvObj.clickPhoneNumberNext();
			logger.info("PhoneNumber Next Clicked") ;
			Thread.sleep(3000);
			
//			Email Control
			if(IndvObj.bEmailPresent()) {
				freport("Email Control Present" , "pass",node);
				logger.info("Email Control Present");
				String sActEM_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("Email Control Title : " + sActEM_Title);
				String sExpEMN_Title =sEM_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActEM_Title.trim(),sExpEMN_Title,"Email Control Title",node);
				logger.info("Validation of Email Control Title") ;
				IndvObj.setEmail(sEM_Value);
				logger.info("Email Id Entered") ;
				freport("Email Id Entered:" +sEM_Value , "pass",node);
				
			}else {
				freport("Email Control Missing" , "fail",node);
				logger.info("Email Control Missing");
				Assert.fail("Email Control Missing");
			}
			IndvObj.clickGeneralNext();
			logger.info("Email Next Clicked") ;
			Thread.sleep(1000);
			
//			TextQuestion Control Validation
			System.out.println("XQ not displayed: " + IndvObj.bIsTextQuestionDisplayed());
			if (IndvObj.bIsTextQuestionDisplayed()) {
				freport("Text Question Conrol Present", "pass", node);
				logger.info("Text Question Conrol Present");
				String sActTX_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("Text Question Choice Control Title : " + sActTX_Title);
				String sExpXQN_Title =sXQ_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActTX_Title.trim(), sExpXQN_Title, "TextQuestion Control Title", node);
				logger.info("Validation of TextQuestion Control Title");
				IndvObj.setTextAnswer(sXQ_Value);
				logger.info("Text Entered in TextAnswer Control");
				freport("Text Entered in TextAnswer Control:" + sXQ_Value, "pass", node);
				
			} else {
				freport("Text Question Conrol Missing", "fail", node);
				logger.info("Text Question Conrol Missing");
				Assert.fail("Text Question Conrol Missing");
			}
			IndvObj.clickGeneralNext();
			logger.info("Text Question Next Clicked");
			Thread.sleep(3000);
			
//			MultiSelect Control Validation
			if(IndvObj.bIsMultiSelectDisplayed()) {
				String sActMS_Title = IndvObj.getHeadThreeTitle();
				UtilityCustomFunctions.logWriteConsole("MS Control Title : " + sActMS_Title);
				String sExpMSN_Title =sMS_Title + " *";
				UtilityCustomFunctions.logWriteConsole(sActMS_Title);
				UtilityCustomFunctions.fSoftAssert(sActMS_Title.trim(),sExpMSN_Title,"MultiSelect Control Title",node);
				logger.info("Validation of MultiSelect Control Title") ;
				//Set MultiSelectValue
				UtilityCustomFunctions.logWriteConsole("MultiSelect Value:" +sMS_Value); 
				IndvObj.setDropdownMS(sMS_Value);
				logger.info("MultiSelect Value Selected") ;
				freport("MultiSelect Value Selected: " +sMS_Value , "pass",node);
				
			}
			else {
				 freport("MultiSelect Control Missing", "fail", node);
				 logger.info("MultiSelect Conrol Missing");
				 Assert.fail("MultiSelect Control Missing");
			}
			//MultiSelect Next Clicked
			IndvObj.clickGeneralNext();
			logger.info("MultiSelect Next Clicked");
			Thread.sleep(3000);
			
//			MultiChoice Control Validation
			if(IndvObj.bIsMultiChoiceDisplayed()) {

				String sActMC_Title = IndvObj.getHeadThreeTitle();
				logger.info(sActMC_Title +"Actual Title ");
				System.out.println(sActMC_Title);
				UtilityCustomFunctions.logWriteConsole("Multi Choice Control Title : " + sActMC_Title);
				String sExpMCN_Title =sMC_Title + " *";
				UtilityCustomFunctions.fSoftAssert(sActMC_Title.trim(), sExpMCN_Title, "MultiChoice Control Title",node);
				System.out.println("Multi Choice Value is: " +sMC_Value);
				IndvObj.selectOneItem(sMC_Value);
				logger.info("MultiChoice Item Selected");
				freport("MultiChoice Item Selected:" +sMC_Value , "pass",node);
				}
			else
				{
				logger.info("MultiChoice control Missing");
				freport("MultiChoice Control Missing" , "fail",node);
				Assert.fail("MultiChoice Not Displayed");
			}
			
			Thread.sleep(3000);
			IndvObj.clickSubmit();
			logger.info("MultiChoice Next Clicked");
			Thread.sleep(6000);
			
		}
	}

	public void fAddValuestoModulePage(String sEnv,String sExcelName,String sSheetName,boolean IsAmend) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
	
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		
		String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 1);
		String sAssignedTo = xlAddObj.getCellData(sSheetName, 1, 2);
		String sText=xlAddObj.getCellData(sSheetName, 1, 3);
		String sMobNumPrefix=xlAddObj.getCellData(sSheetName, 1, 4);
		String sMobileNumber=xlAddObj.getCellData(sSheetName, 1, 5);
		String sEmail=xlAddObj.getCellData(sSheetName, 1, 6);
		String sPickListValue=xlAddObj.getCellData(sSheetName, 1, 7);
		String sMultiComboValues=xlAddObj.getCellData(sSheetName, 1, 8);
		String sCity=xlAddObj.getCellData(sSheetName, 1, 9);
		String sState=xlAddObj.getCellData(sSheetName, 1, 10);
		String sCountry=xlAddObj.getCellData(sSheetName, 1, 11);
		String sCheckBox=xlAddObj.getCellData(sSheetName, 1, 12);
		String sDate =xlAddObj.getCellData(sSheetName, 1, 13);
		String sTime =xlAddObj.getCellData(sSheetName, 1, 14);
		String sDateandTime =xlAddObj.getCellData(sSheetName, 1, 15);
		String sRelatedModule =xlAddObj.getCellData(sSheetName, 1, 16);
		String sFilePath =xlAddObj.getCellData(sSheetName, 1, 17);
		String sNamePrefix =xlAddObj.getCellData(sSheetName, 1, 18);
		String sName=xlAddObj.getCellData(sSheetName, 1, 19);
		String sNumber=xlAddObj.getCellData(sSheetName, 1, 20);
		String sCurrency=xlAddObj.getCellData(sSheetName, 1, 21);
		String sUrl=xlAddObj.getCellData(sSheetName, 1, 22);
		String sEnqNamePrefix = xlAddObj.getCellData(sSheetName, 1, 23);
		String sEnquiry_Name=xlAddObj.getCellData(sSheetName, 1, 24);
		String sEnquiry_Email=xlAddObj.getCellData(sSheetName, 1, 25);
		String sEnquiry_Text=xlAddObj.getCellData(sSheetName, 1, 26);
		String sEnquiry_TextArea=xlAddObj.getCellData(sSheetName, 1, 27);
		String sEnquiry_Date=xlAddObj.getCellData(sSheetName, 1, 28);
		String sEnquiry_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 29);
		String sEnquiry_PhoneNumber=xlAddObj.getCellData(sSheetName, 1, 30);
		String sEnquiry_category =xlAddObj.getCellData(sSheetName, 1, 31);
		String sExecutionCondition=xlAddObj.getCellData(sSheetName, 1, 32);
		String sActionType=xlAddObj.getCellData(sSheetName, 1, 33);
		String sActionTitle=xlAddObj.getCellData(sSheetName, 1, 34);
		String sRecordId=xlAddObj.getCellData(sSheetName, 1, 35);
		String sWorkFlowPos=xlAddObj.getCellData(sSheetName, 1, 36);
		String sUser1NotifyCount=xlAddObj.getCellData(sSheetName, 1, 37); 
		String sUser2NotifyCount=xlAddObj.getCellData(sSheetName, 1, 38);
		String sUser3NotifyCount=xlAddObj.getCellData(sSheetName, 1, 39);
		String sUser2RecordId=xlAddObj.getCellData(sSheetName, 1, 40);
		String sUser3RecordId=xlAddObj.getCellData(sSheetName, 1, 41);
		String sDisplayModuleName=xlAddObj.getCellData(sSheetName, 1, 42);
		String sEditIndText=xlAddObj.getCellData(sSheetName, 1, 43);
		String sLead_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 44);
		String sLead_PN=xlAddObj.getCellData(sSheetName, 1, 45);
		String sLead_Email=xlAddObj.getCellData(sSheetName, 1, 46);
		String sLead_Text=xlAddObj.getCellData(sSheetName, 1, 47);
		String sSales_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 48);
		String sSales_PN=xlAddObj.getCellData(sSheetName, 1, 49);
		String sSales_Email=xlAddObj.getCellData(sSheetName, 1, 50);
		
		sRecordId="";
		System.out.println("Module Name:  " + sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Before Adding Module data");
		Thread.sleep(3000);
		objCMD.clickArrayDropDown(1);
//		objCMD.clickAssignedTo();
		UtilityCustomFunctions.logWriteConsole("Assinged To Drop Down clicked");
		Thread.sleep(2000);
		objCMD.selectListValue(sAssignedTo);
		UtilityCustomFunctions.logWriteConsole("Assinged To Value Seleted is::  "+sAssignedTo);
		objCMD.setInputValue(sExpModuleName, "text",sText);
		UtilityCustomFunctions.logWriteConsole("Text Value added is::  "+sText);
		Thread.sleep(1000);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"mobilenumber_prefix-container");
		UtilityCustomFunctions.logWriteConsole("Phone NUmber prefix clicked :  ");
		objCMD.selectListValue(sMobNumPrefix);
		UtilityCustomFunctions.logWriteConsole("Phone NUmber selected is :  "+sMobileNumber);
		objCMD.setMobileNumber(sMobileNumber);
		Thread.sleep(1000);
		objCMD.setEmailValue(sExpModuleName, sEmail);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Email Added is: "+sEmail);
		objCMD.clickArrayDropDown(3);
		
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Picklist clicked");
		Thread.sleep(1000);
		objCMD.selectListValue2(sPickListValue);
		UtilityCustomFunctions.logWriteConsole("Picklist selected: "+sPickListValue);
		Thread.sleep(1000);
		objCMD.clickMultiComboBox(sMultiComboValues,IsAmend);
		UtilityCustomFunctions.logWriteConsole("MultiCombo box values: " + sMultiComboValues);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(4);
		UtilityCustomFunctions.logWriteConsole("City Drop Down clicked");
		objCMD.selectListValue(sCity);
		UtilityCustomFunctions.logWriteConsole("City Selected: " + sCity);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(5);
		UtilityCustomFunctions.logWriteConsole("State Drop Down clicked");
		objCMD.selectListValue(sState);
		UtilityCustomFunctions.logWriteConsole("State Selected: "+sState);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(6);
		UtilityCustomFunctions.logWriteConsole("Country List clicked");
		objCMD.selectListValue(sCountry);
		UtilityCustomFunctions.logWriteConsole("Country Selected: " + sCountry);
		Thread.sleep(1000);
		objCMD.clickArrayCheckBox(1, sCheckBox);
		UtilityCustomFunctions.logWriteConsole("Checkbox clicked");
		objCMD.clickDateBox(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("DateBox clicked");
		Thread.sleep(3000);
		objCMD.clickDayInDate(1,"sDate",null);
		UtilityCustomFunctions.logWriteConsole("Today Date Selected");
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		UtilityCustomFunctions.logWriteConsole("Time Clicked");
		objCMD.clickTime("2", "35","55");
		UtilityCustomFunctions.logWriteConsole("Time Selected is: 02:36 PM");
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "datetime");
		UtilityCustomFunctions.logWriteConsole("Date & Time Clicked");
		Thread.sleep(5000);
		objCMD.clickDayInDate(3,"sDateandTime",null);
		Thread.sleep(2000);
		UtilityCustomFunctions.logWriteConsole("Clicked Current Date in Date&Time");
		objCMD.clickDandTApply();
		UtilityCustomFunctions.logWriteConsole("Date & Time Selected");
		
		String sActDate = objCMD.fGetModuleValue(sExpModuleName, "date");
		System.out.println("AcutalDate: " + sActDate);
		xlAddObj.setCellData(sSheetName, 1, 13, sActDate);
		
		String sActTime= objCMD.fGetModuleValue(sExpModuleName, "time");
		System.out.println("Time" + sActTime);
		xlAddObj.setCellData(sSheetName, 1, 14, sActTime);
		
		String sActDateandTime= objCMD.fGetModuleValue(sExpModuleName, "datetime");
		System.out.println("Date & Time" + sActDateandTime);
		xlAddObj.setCellData(sSheetName, 1, 15, sActDateandTime);
		
		objCMD.setInputValue(sExpModuleName, "relatedmodule_name", sRelatedModule);
		
		objCMD.setUploadFile();
//		sFileName
//		xlAddObj.setCellData(sSheetName, 1, 17, sFileName);
		
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
//		objCMD.clickDayInDate(3,"sEnquiryDate",null);
//		In Send SMS Notification, Enquiry Date is working as sDate
		objCMD.clickDayInDate(2,"sEnquiryDate",null);
//		objCMD.clickDayInDate(1,"sDate",null);

		
		String sActEnquiryDate = objCMD.fGetModuleValue(sExpModuleName, "enquirydate");
		System.out.println("AcutalDate: " + sActEnquiryDate);
		xlAddObj.setCellData(sSheetName, 1, 28, sActEnquiryDate);
		
		
		System.out.println("Enquiriy prefix: " + sEnquiry_PN_Prefix);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"enquiryphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sEnquiry_PN_Prefix);
		
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "enquiryphonenumber", sEnquiry_PhoneNumber);
		
//		objCMD.clickEnqCategory();
		objCMD.clickSelectControl(sExpModuleName,"enquirycategory");
		Thread.sleep(3000);
		objCMD.selectListValue(sEnquiry_category);
		
		Thread.sleep(2000);
		
		//Lead PhoneNumber prefix
		objCMD.ClickListPhonePrefix(sExpModuleName,"leadphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sLead_PN_Prefix);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "leadphonenumber", sLead_PN);
		
		//Lead Email
		objCMD.setGenericInputValue("email", sExpModuleName, "leademail", sLead_Email);
		
		//Lead Text
		objCMD.setGenericInputValue("text", sExpModuleName, "leadtext", sLead_Text);
		
		//Sales PN Prefix
		objCMD.ClickListPhonePrefix(sExpModuleName,"salesphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sSales_PN_Prefix);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "salesphonenumber", sSales_PN);
		
		//Sales Email
		objCMD.setGenericInputValue("email", sExpModuleName, "salesemail", sSales_Email);
		Thread.sleep(5000);		
		
		objCMD.clickSave();
		
		Thread.sleep(5000);
	}

	
	
	public void fValidateAllFields(String sEnv,String sExcelName,String sSheetName,String sMessage,String isNotify,ExtentTest node) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\" + sExcelName + "Test.xlsx" ;	
		}
		else {
			sPath=".\\testData\\" + sExcelName + "Live.xlsx" ;
		}//get the test data sheet
		
		ExcelUtility xlValObj = new ExcelUtility(sPath);
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlValObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlValObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		String sCurrentWinHandle = "";
		String sExpModuleName = xlValObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlValObj.getCellData(sSheetName, 1, 1);
		String sAssignedTo = xlValObj.getCellData(sSheetName, 1, 2);
		String sText=xlValObj.getCellData(sSheetName, 1, 3);
		String sMobNumPrefix=xlValObj.getCellData(sSheetName, 1, 4);
		String sMobileNumber=xlValObj.getCellData(sSheetName, 1, 5);
		sMobileNumber = sMobNumPrefix + " " + sMobileNumber;  	
		String sEmail=xlValObj.getCellData(sSheetName, 1, 6);
		String sPickListValue=xlValObj.getCellData(sSheetName, 1, 7);
		String sMultiComboValues=xlValObj.getCellData(sSheetName, 1, 8);
		String sCity=xlValObj.getCellData(sSheetName, 1, 9);
		String sState=xlValObj.getCellData(sSheetName, 1, 10);
		String sCountry=xlValObj.getCellData(sSheetName, 1, 11);
		String sCheckBox=xlValObj.getCellData(sSheetName, 1, 12);
		if(sCheckBox.equalsIgnoreCase("ON")) {
			sCheckBox = "Yes";
		}
		else {
			sCheckBox = "No";
		}
		String sDate =xlValObj.getCellData(sSheetName, 1, 13);
		String sTime =xlValObj.getCellData(sSheetName, 1, 14);
		String sDateandTime =xlValObj.getCellData(sSheetName, 1, 15);
		String sRelatedModule =xlValObj.getCellData(sSheetName, 1, 16);
		String sFileName =xlValObj.getCellData(sSheetName, 1, 17);
		String sNamePrefix =xlValObj.getCellData(sSheetName, 1, 18);
		String sName=xlValObj.getCellData(sSheetName, 1, 19);
		sName = sNamePrefix + " " + sName;
		String sNumber=xlValObj.getCellData(sSheetName, 1, 20);
		String sCurrency=xlValObj.getCellData(sSheetName, 1, 21);
		String sUrl=xlValObj.getCellData(sSheetName, 1, 22);
		String sEnqNamePrefix = xlValObj.getCellData(sSheetName, 1, 23);
		String sEnquiry_Name=xlValObj.getCellData(sSheetName, 1, 24);
		String sEnquiry_Email=xlValObj.getCellData(sSheetName, 1, 25);
		String sEnquiry_Text=xlValObj.getCellData(sSheetName, 1, 26);
		String sEnquiry_TextArea=xlValObj.getCellData(sSheetName, 1, 27);
		String sEnquiry_Date=xlValObj.getCellData(sSheetName, 1, 28);
		String sEnquiry_PN_Prefix=xlValObj.getCellData(sSheetName, 1, 29);
		String sEnquiry_PhoneNumber=xlValObj.getCellData(sSheetName, 1, 30);
		String sEnquiry_category =xlValObj.getCellData(sSheetName, 1, 31);
		String sExecutionCondition=xlValObj.getCellData(sSheetName, 1, 32);
		String sActionType=xlValObj.getCellData(sSheetName, 1, 33);
		String sActionTitle=xlValObj.getCellData(sSheetName, 1, 34);
		String sRecordId=xlValObj.getCellData(sSheetName, 1, 35);
		String sWorkFlowPos=xlValObj.getCellData(sSheetName, 1, 36);
		String sUser1NotifyCount=xlValObj.getCellData(sSheetName, 1, 37); 
		String sUser2NotifyCount=xlValObj.getCellData(sSheetName, 1, 38);
		String sUser3NotifyCount=xlValObj.getCellData(sSheetName, 1, 39);
		String sUser2RecordId=xlValObj.getCellData(sSheetName, 1, 40);
		String sUser3RecordId=xlValObj.getCellData(sSheetName, 1, 41);
		String sDisplayModuleName=xlValObj.getCellData(sSheetName, 1, 42);
		String sEditIndText=xlValObj.getCellData(sSheetName, 1, 43);
		String sLead_PN_Prefix=xlValObj.getCellData(sSheetName, 1, 44);
		String sLead_PN=xlValObj.getCellData(sSheetName, 1, 45);
		String sLead_Email=xlValObj.getCellData(sSheetName, 1, 46);
		String sLead_Text=xlValObj.getCellData(sSheetName, 1, 47);
		String sSales_PN_Prefix=xlValObj.getCellData(sSheetName, 1, 48);
		String sSales_PN=xlValObj.getCellData(sSheetName, 1, 49);
		String sSales_Email=xlValObj.getCellData(sSheetName, 1, 50);
		DetailViewPage objDVP = new DetailViewPage(driver);
		SummaryViewPage objSVP = new SummaryViewPage(driver);
//		fClickFirstRecord();
		Thread.sleep(6000);
		objDVP.fSetToggleHeader(true);
		objDVP.fSetDetailVew(false);
		
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
		
		
		String aActModuleName = objDVP.getNavBarModuleName();
		System.out.println("Actual Module Name: " + aActModuleName);
		System.out.println("Expected Module Name: " + sExpModuleName);
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sDisplayModuleName, "Module Name : " + sMessage, node);
		

		// Title Actual Values
		String sActAssignedTo =objSVP.getTitleAssignedTo();
		String sActMobileNumber =objSVP.getTitlePhoneNumber();
		String sActEmail =objSVP.getTitleEmail();
		
//		String sPhoneNumber = sMobilePrefix + " " + sMobileNumber;
		// Title Validations
		UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sAssignedTo, "Assigned To-Summary Title", node);
//		UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sPhoneNumber, "Phone Number - Summary Title", node);
		UtilityCustomFunctions.fSoftAssert(sActEmail, sEmail, "Email - Summary Title", node);
		
		//Summary Page Validation
		
		String sCurrentTime =objDVP.getCurrentTime();
		xlValObj.setCellData(sSheetName, 0, 51, "CurrentTime");
		xlValObj.setCellData(sSheetName, 1, 51, sCurrentTime);
		
		String sActSummaryAssignedTo = objDVP.getSummaryAssignTo();
		UtilityCustomFunctions.fSoftAssert(sActSummaryAssignedTo, sAssignedTo, "Summary AssignedTo:  " + sMessage, node);
		
		String sActSummaryText = objDVP.getArraySummary(1).trim();
		UtilityCustomFunctions.fSoftAssert(sActSummaryText, sText, "Summary Text:  " + sMessage, node);
		
		sActMobileNumber = objDVP.getArraySummary(2).trim();
		String sMobNMArray[] = sActMobileNumber.split("\\s+");
		sActMobileNumber = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
				
		System.out.println("Actual mobile number: " + sActMobileNumber);
		UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sMobileNumber, "Summary Mobile Number:  " + sMessage, node);
		
		sActEmail = objDVP.getArraySummary(3).trim();
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
		
//		String sActRelatedModule = objDVP.getArraySummary(13).trim();
//		UtilityCustomFunctions.fSoftAssert(sActRelatedModule, sRelatedModule, "Summary Related Module :  " + sMessage, node);
		
		String sActUploadFile="";
		if(objDVP.getUploadFileText()!=null) {
			sActUploadFile= objDVP.getUploadFileText();
		}
		
		
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
		System.out.println("Actual Enquiry Phone Number: " + sActEnq_PhoneNumber);
		
		
		String sEnqPNArray[] = sActEnq_PhoneNumber.split("\\s+");
		sActEnq_PhoneNumber = sEnqPNArray[0].trim() + " " + sEnqPNArray[1].trim();
		System.out.println("Actual: " + sActEnq_PhoneNumber);
		
		
		UtilityCustomFunctions.fSoftAssert(sActEnq_PhoneNumber, sEnquiry_PhoneNumber, "Summary Enquiry Phone Number:  " + sMessage, node);
		
		String sActEnq_Category=objDVP.getArraySummary(24).trim();
		UtilityCustomFunctions.fSoftAssert(sActEnq_Category, sEnquiry_category, "Summary Enquiry Category:  " + sMessage, node);
		

		//Leads Validation.
		
		String sLead_PhoneNumber = sLead_PN_Prefix + " " + sLead_PN ;
		
		String sActLead_PhoneNumber=objDVP.getArraySummary(25).trim();
		System.out.println("Actual Lead Summary Phone Number: " + sActLead_PhoneNumber);
		
		
		String sActLeadPNArray[] = sActLead_PhoneNumber.split("\\s+");
		sActLead_PhoneNumber = sActLeadPNArray[0].trim() + " " + sActLeadPNArray[1].trim();
		System.out.println("Actual: " + sActLead_PhoneNumber);
		
		UtilityCustomFunctions.fSoftAssert(sActLead_PhoneNumber, sLead_PhoneNumber, "Summary Lead PhoneNumber:  " + sMessage, node);
		//Lead Email
		String sActLead_Email=objDVP.getArraySummary(26).trim();
		UtilityCustomFunctions.fSoftAssert(sActLead_Email, sLead_Email, "Summary Lead Email:  " + sMessage, node);
		
		//Lead Text
		String sActLead_Text=objDVP.getArraySummary(27).trim();
		UtilityCustomFunctions.fSoftAssert(sActLead_Text, sLead_Text, "Summary Lead Text:  " + sMessage, node);
		//Sales Validation
		String sSales_PhoneNumber = sSales_PN_Prefix + " " + sSales_PN ;
		
		String sActSales_PhoneNumber=objDVP.getArraySummary(28).trim();
		System.out.println("Actual Sales Summary Phone Number: " + sActSales_PhoneNumber);
		
		
		String sActSalesPNArray[] = sActSales_PhoneNumber.split("\\s+");
		sActSales_PhoneNumber = sActSalesPNArray[0].trim() + " " + sActSalesPNArray[1].trim();
		System.out.println("Actual: " + sActSales_PhoneNumber);
		
		UtilityCustomFunctions.fSoftAssert(sActSales_PhoneNumber, sSales_PhoneNumber, "Summary Sales PhoneNumber:  " + sMessage, node);
		
		
		String sActSales_Email=objDVP.getArraySummary(29).trim();
		UtilityCustomFunctions.fSoftAssert(sActSales_Email, sSales_Email, "Summary Sales Email:  " + sMessage, node);
	
		//Details View
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		String sActDTAssignedTo = objDVP.getArrayDetails(2).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sAssignedTo, "Detail View AssignedTo:  " + sMessage, node);
		
		String sActDTText= objDVP.getArrayDetails(3).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTText, sText, "Detail View Text:  " + sMessage, node);
		
		String sActDTMobileNumber= objDVP.getArrayDetails(4).trim();
		String sDTMobileNMArray[] = sActDTMobileNumber.split("\\s+");
		sActDTMobileNumber = sDTMobileNMArray[0].trim() + " " + sDTMobileNMArray[1].trim();
		
		System.out.println("actual mobile number: " + sActMobileNumber);
		UtilityCustomFunctions.fSoftAssert(sActDTMobileNumber, sMobileNumber, "Detail View Mobile Number:  " + sMessage, node);
		
		String sActDTEmail= objDVP.getArrayDetails(5).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEmail, sEmail, "Detail View Email :  " + sMessage, node);
		
		String sActDTPicList= objDVP.getArrayDetails(6).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTPicList, sPickListValue, "Detail View PicListValue :  " + sMessage, node);
		
		String sActDTMultiCombo= objDVP.getArrayDetails(7).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTMultiCombo, sMultiComboValues, "Detail View MuultiCombo Values :  " + sMessage, node);
		
		String sActDTCity= objDVP.getArrayDetails(8).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCity, sCity, "Detail View City:  " + sMessage, node);
		
		String sActDTState= objDVP.getArrayDetails(9).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTState, sState, "Detail View Stat:  " + sMessage, node);
		
		String sActDTCountry= objDVP.getArrayDetails(10).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCountry, sCountry, "Detail View Country:  " + sMessage, node);
		
		String sActDTChekBox= objDVP.getArrayDetails(11).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTChekBox, sCheckBox, "Detail View CheckBox:  " + sMessage, node);
		
		String sActDTDate= objDVP.getArrayDetails(12).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTDate, sDate, "Detail View Date:  " + sMessage, node);
		
		String sActDTTime= objDVP.getArrayDetails(13).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTTime, sTime, "Detail View Time:  " + sMessage, node);
		
		String sActDTDateandTime= objDVP.getArrayDetails(14).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTDateandTime, sDateandTime, "Detail View Date & Time :  " + sMessage, node);
		
		String sActDTRelModule= objDVP.getArrayDetails(15).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTRelModule, sRelatedModule, "Detail View Related Module :  " + sMessage, node);
		
		String sActDTUploadFile= objDVP.getUploadFileText().trim();
		if(sActDTUploadFile.contains(sFileName)) {
			UtilityCustomFunctions.fSoftAssert(sFileName, sFileName, "Detail View File Upload :  " + sMessage, node);
		}
		else {
			UtilityCustomFunctions.fSoftAssert(sActDTUploadFile, sFileName, "Detail View Upload :  " + sMessage, node);
		}
		
		String sActDTName= objDVP.getArrayDetails(17).trim();
		String sDTNameArray[] = sActDTName.split("\\s+");
		sActDTName = sDTNameArray[0].trim() + " " + sDTNameArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTName, sName, "Detail View Name:  " + sMessage, node);
		
		String sActDTNumber= objDVP.getArrayDetails(18).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTNumber, sNumber, "Detail View Number:  " + sMessage, node);
		
		String sActDTCurrency= objDVP.getArrayDetails(19).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTCurrency, sCurrency, "Detail View Currency:  " + sMessage, node);
		
		String sActDTUrl= objDVP.getDTSummaryUrl().trim();
		UtilityCustomFunctions.fSoftAssert(sActDTUrl, sUrl, "Detail View Url:  " + sMessage, node);
		Thread.sleep(3000);
		
		objDVP.fClickDetailBlockB();
		Thread.sleep(3000);
		
		String sActDTEnqName = objDVP.getArrayDetails(21).trim();
		
		String sDTEnqNameArray[] = sActDTEnqName.split("\\s+");
		sActDTEnqName = sDTEnqNameArray[0].trim() + " " + sDTEnqNameArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqName, sEnquiry_Name, "Detail View Enquiry Name:  " + sMessage, node);
		
		String sActDTEnqEmail= objDVP.getArrayDetails(22).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqEmail, sEnquiry_Email, "Detail View Enquiry Email:  " + sMessage, node);
		
		String sActDTEnqText= objDVP.getArrayDetails(23).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqText, sEnquiry_Text, "Detail View Enquiry Text:  " + sMessage, node);
		
		String sActDTEnqTextArea= objDVP.getArrayDetails(24).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqTextArea, sEnquiry_TextArea, "Detail View Enquiry Text Area:  " + sMessage, node);
		
		String sActDTEnqDate= objDVP.getArrayDetails(25).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqDate, sEnquiry_Date, "Detail View Enquiry Date:  " + sMessage, node);
		
		String sActDTEnqPN= objDVP.getArrayDetails(26).trim();
		String sEnqActDTArray[] = sActDTEnqPN.split("\\s+");
		sActDTEnqPN = sEnqActDTArray[0].trim() + " " + sEnqActDTArray[1].trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqPN, sEnquiry_PhoneNumber, "Detail View Enquiry Phone Number:  " + sMessage, node);
		
		String sActDTEnqCategory= objDVP.getArrayDetails(27).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTEnqCategory, sEnquiry_category, "Detail View Enquiry Category:  " + sMessage, node);
		
		Thread.sleep(3000);
		objDVP.fClickDetailBlockC();
		Thread.sleep(3000);
		
		String sActLeadDTMobileNumber= objDVP.getArrayDetails(28).trim();
		String sActLeadDTMobileNMArray[] = sActLeadDTMobileNumber.split("\\s+");
		sActLeadDTMobileNumber = sActLeadDTMobileNMArray[0].trim() + " " + sActLeadDTMobileNMArray[1].trim();
		System.out.println("actual mobile number: " + sActLeadDTMobileNumber);
		
		String sDTLead_PN = sLead_PN_Prefix + " " + sLead_PN;
		UtilityCustomFunctions.fSoftAssert(sActLeadDTMobileNumber, sDTLead_PN, "DT Lead Mobile Number:  " + sMessage, node);
		//Created Time added which is not captured.
		
		String sActDTLeadEmail= objDVP.getArrayDetails(29).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTLeadEmail, sLead_Email, "DT View Lead Email  " + sMessage, node);
		
		String sActDTLeadText= objDVP.getArrayDetails(30).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTLeadText, sLead_Text, "DT View Lead Text " + sMessage, node);
		
		//Sales
		String sActSalesDTMobileNumber= objDVP.getArrayDetails(31).trim();
		String sActSalesDTMobileNMArray[] = sActSalesDTMobileNumber.split("\\s+");
		sActSalesDTMobileNumber = sActSalesDTMobileNMArray[0].trim() + " " + sActSalesDTMobileNMArray[1].trim();
		
		System.out.println("actual mobile number: " + sActSalesDTMobileNumber );
		
		String sDTSales_PN = sSales_PN_Prefix + " " + sSales_PN;
		UtilityCustomFunctions.fSoftAssert(sActSalesDTMobileNumber, sDTSales_PN, "DT Sales Mobile Number:  " + sMessage, node);
		
		String sActDTSalesEmail= objDVP.getArrayDetails(32).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTSalesEmail, sSales_Email, "DT View Sales Email" + sMessage, node);
		
		if(isNotify.equalsIgnoreCase("Yes")){
			driver.close();
		}
		
		System.out.println("Inside Validate All fields: isNotify" + isNotify);
	}
	
	//Add Notification Values
	public void fNotifyAddValuestoModulePage(String sEnv,String sExcelName,String sSheetName) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}

		ExcelUtility xlAddObj = new ExcelUtility(sPath);
	
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");

		String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 1);
//		String sAssignedTo = xlAddObj.getCellData(sSheetName, 1, 2);
		String sPhoneNoumber=xlAddObj.getCellData(sSheetName, 1, 3);
		String sNumberField=xlAddObj.getCellData(sSheetName, 1, 4);
		String sEmail=xlAddObj.getCellData(sSheetName, 1, 5);
		String sPickListItem=xlAddObj.getCellData(sSheetName, 1, 6);
		String sEnterYourNumber=xlAddObj.getCellData(sSheetName, 1, 7);
		String sExecutionCondition=xlAddObj.getCellData(sSheetName, 1, 8);
		String sActionType=xlAddObj.getCellData(sSheetName, 1, 9);
		String sActionTitle=xlAddObj.getCellData(sSheetName, 1, 10);
		String sDisplayModuleName=xlAddObj.getCellData(sSheetName, 1, 42);
		
		objCMD.SetPhoneNumber(sPhoneNoumber);

		objCMD.SetNumberField(sNumberField);

		Thread.sleep(1000);
		if(objCMD.isEmailDisplayed()) {
			System.out.println("Email control present");
			objCMD.SetEmail(sEmail);
		}
		else {
			System.out.println("Email control missing");
		}

		Thread.sleep(1000);
		objCMD.SetEnterYourNumber(sEnterYourNumber);
		Thread.sleep(2000);

		//sPickListItem
		objCMD.clickMenuList(sPickListItem);
		
		objCMD.selectMenuValue(sPickListItem);
		objCMD.clickSave();
		Thread.sleep(10000);
	}
	//Add Values to Entity Module
	public void fAddValuestoEntityModule(String sEnv,String sExcelName,String sSheetName,boolean IsAmend) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
	
		logger.info("Excel file Utility instance created");
	
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		logger.info("Row Count is: " + iRowCount);
		
	
		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
		System.out.println("Cols: " + iColCount);
		logger.info("Col Count is: " + iColCount);
					
		logger.info("Extracting DataSheet Values started...");
		String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
		String sExpTrgModuleName = xlAddObj.getCellData(sSheetName, 1, 1);
		String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 2);
		String sExpAssignedTo = xlAddObj.getCellData(sSheetName, 1, 3);
		String sText=xlAddObj.getCellData(sSheetName, 1, 4);
		
		String sMobNumPrefix=xlAddObj.getCellData(sSheetName, 1, 5);
		String sMobileNumber=xlAddObj.getCellData(sSheetName, 1, 6);
		String sEmail=xlAddObj.getCellData(sSheetName, 1, 7);
		String sPickListValue=xlAddObj.getCellData(sSheetName, 1, 8);
		String sMultiComboValues=xlAddObj.getCellData(sSheetName, 1, 9);
		String sCity=xlAddObj.getCellData(sSheetName, 1, 10);
		String sState=xlAddObj.getCellData(sSheetName, 1, 11);
		String sCountry=xlAddObj.getCellData(sSheetName, 1, 12);
		String sCheckBox=xlAddObj.getCellData(sSheetName, 1, 13);
		String sDate =xlAddObj.getCellData(sSheetName, 1, 14);
		String sTime =xlAddObj.getCellData(sSheetName, 1, 15);
		String sDateandTime =xlAddObj.getCellData(sSheetName, 1, 16);
		String sRelatedModule =xlAddObj.getCellData(sSheetName, 1, 17);
		String sFilePath =xlAddObj.getCellData(sSheetName, 1, 18);
		String sNamePrefix =xlAddObj.getCellData(sSheetName, 1, 19);
		String sName=xlAddObj.getCellData(sSheetName, 1, 20);
		String sNumber=xlAddObj.getCellData(sSheetName, 1, 21);
		String sCurrency=xlAddObj.getCellData(sSheetName, 1, 22);
		String sUrl=xlAddObj.getCellData(sSheetName, 1, 23);
		String sEnqNamePrefix=xlAddObj.getCellData(sSheetName, 1, 24);
		String sEnquiry_Name=xlAddObj.getCellData(sSheetName, 1, 25);
		String sEnquiry_Email=xlAddObj.getCellData(sSheetName, 1, 26);
		String sEnquiry_Text=xlAddObj.getCellData(sSheetName, 1, 27);
		String sEnquiry_TextArea=xlAddObj.getCellData(sSheetName, 1, 28);
		UtilityCustomFunctions.logWriteConsole("ETA"+sEnquiry_TextArea);
		String sEnquiry_Date=xlAddObj.getCellData(sSheetName, 1, 29);
		String sEnquiry_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 30);
		String sEnquiry_PhoneNumber=xlAddObj.getCellData(sSheetName, 1, 31);
		String sEnquiry_category=xlAddObj.getCellData(sSheetName, 1, 32);
		String sExecutionCondition=xlAddObj.getCellData(sSheetName, 1, 33);
		String sActionType=xlAddObj.getCellData(sSheetName, 1, 34);
		String sActionTitle=xlAddObj.getCellData(sSheetName, 1, 35);
		String sWorkFlowPos=xlAddObj.getCellData(sSheetName, 1, 36);
		String sUser1MessageId=xlAddObj.getCellData(sSheetName, 1, 37);
		String sUser2MessageId=xlAddObj.getCellData(sSheetName, 1, 38);
		String sUser3MessageId=xlAddObj.getCellData(sSheetName, 1, 39);
		String sUser1RecordId=xlAddObj.getCellData(sSheetName, 1, 40);
		String sUser2RecordId=xlAddObj.getCellData(sSheetName, 1, 41);
		String sUser3RecordId=xlAddObj.getCellData(sSheetName, 1, 42);
		String sDisplayMod1=xlAddObj.getCellData(sSheetName, 1, 43);
		String sDisplayMod2=xlAddObj.getCellData(sSheetName, 1, 44);
		String sEditIndText=xlAddObj.getCellData(sSheetName, 1, 45);
		String sLead_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 46);
		String sLead_PN=xlAddObj.getCellData(sSheetName, 1, 47);
		String sLead_Email=xlAddObj.getCellData(sSheetName, 1, 48);
		String sLead_Text=xlAddObj.getCellData(sSheetName, 1, 49);
		String sSales_PN_Prefix=xlAddObj.getCellData(sSheetName, 1, 50);
		String sSales_PN=xlAddObj.getCellData(sSheetName, 1, 51);
		String sSales_Email=xlAddObj.getCellData(sSheetName, 1, 52);
		
//		sRecordId="";
		System.out.println("Module Name:  " + sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Before Adding Module data");
		Thread.sleep(3000);
		objCMD.clickArrayDropDown(1);
//		objCMD.clickAssignedTo();
		UtilityCustomFunctions.logWriteConsole("Assinged To Drop Down clicked");
		Thread.sleep(2000);
		objCMD.selectListValue(sExpAssignedTo);
		UtilityCustomFunctions.logWriteConsole("Assinged To Value Seleted is::  "+sExpAssignedTo);
		Thread.sleep(2000);
		objCMD.setInputValue(sExpModuleName, "text",sText);
		UtilityCustomFunctions.logWriteConsole("Text Value added is::  "+sText);
		Thread.sleep(1000);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"mobilenumber_prefix-container");
		UtilityCustomFunctions.logWriteConsole("Phone NUmber prefix clicked :  ");
		objCMD.selectListValue(sMobNumPrefix);
		UtilityCustomFunctions.logWriteConsole("Phone NUmber selected is :  "+sMobileNumber);
		objCMD.setMobileNumber(sMobileNumber);
		Thread.sleep(1000);
		objCMD.setEmailValue(sExpModuleName, sEmail);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Email Added is: "+sEmail);
		
		objCMD.clickArrayDropDown(3);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Picklist clicked");
		Thread.sleep(1000);
		objCMD.selectListValue2(sPickListValue);
		UtilityCustomFunctions.logWriteConsole("Picklist selected: "+sPickListValue);
		
		Thread.sleep(1000);
		objCMD.clickMultiComboBox(sMultiComboValues,IsAmend);
		UtilityCustomFunctions.logWriteConsole("MultiCombo box values: " + sMultiComboValues);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(4);
		UtilityCustomFunctions.logWriteConsole("City Drop Down clicked");
		objCMD.selectListValue(sCity);
		UtilityCustomFunctions.logWriteConsole("City Selected: " + sCity);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(5);
		UtilityCustomFunctions.logWriteConsole("State Drop Down clicked");
		objCMD.selectListValue(sState);
		UtilityCustomFunctions.logWriteConsole("State Selected: "+sState);
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(6);
		UtilityCustomFunctions.logWriteConsole("Country List clicked");
		objCMD.selectListValue(sCountry);
		UtilityCustomFunctions.logWriteConsole("Country Selected: " + sCountry);
		Thread.sleep(1000);
		objCMD.clickArrayCheckBox(1, sCheckBox);
		UtilityCustomFunctions.logWriteConsole("Checkbox clicked");
		objCMD.clickDateBox(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("DateBox clicked");
		Thread.sleep(3000);
		objCMD.clickDayInDate(1,"sDate",null);
		UtilityCustomFunctions.logWriteConsole("Today Date Selected");
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		UtilityCustomFunctions.logWriteConsole("Time Clicked");
		objCMD.clickTime("2", "35","55");
		UtilityCustomFunctions.logWriteConsole("Time Selected is: 02:36 PM");
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "datetime");
		UtilityCustomFunctions.logWriteConsole("Date & Time Clicked");
		Thread.sleep(5000);
		String sCurrMonth = getTodayMonth();
		UtilityCustomFunctions.logWriteConsole("Current Month:" + sCurrMonth);
		Thread.sleep(2000);
		String sYear =  Integer.toString(getCurrentYear());
		Thread.sleep(1000);
		objCMD.SelectMonthandYear("sDateandTime",sCurrMonth,sYear);
//		objCMD.SelectDayandTime(2,"8","00","00","PM");
		objCMD.clickDayInDate(2,"sDateandTime",null);
		Thread.sleep(2000);
		UtilityCustomFunctions.logWriteConsole("Clicked Current Date in Date&Time");
		objCMD.clickDandTApply();
		UtilityCustomFunctions.logWriteConsole("Date & Time Selected");
		
		String sActDate = objCMD.fGetModuleValue(sExpModuleName, "date");
		System.out.println("AcutalDate: " + sActDate);
//		String fromDateFormat = "dd-MM-yyyy";
//        SimpleDateFormat sdfSource = new SimpleDateFormat(fromDateFormat);
//        Date date = sdfSource.parse(sActDate);
//        SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy/MM/dd");
//        sActDate= sdfDestination.format(date);
        UtilityCustomFunctions.logWriteConsole("Formatted Date from sheet:" + sActDate);
        
		xlAddObj.setCellData(sSheetName, 1, 14, sActDate);
		
		String sActTime= objCMD.fGetModuleValue(sExpModuleName, "time");
		System.out.println("Time" + sActTime);
		xlAddObj.setCellData(sSheetName, 1, 15, sActTime);
		
		String sActDateandTime= objCMD.fGetModuleValue(sExpModuleName, "datetime");
		System.out.println("Date & Time" + sActDateandTime);
		xlAddObj.setCellData(sSheetName, 1, 16, sActDateandTime);
		
//		objCMD.setInputValue(sExpModuleName, "relatedmodule_name", sRelatedModule);
		
		objCMD.setUploadFile();
//		sFileName
//		xlAddObj.setCellData(sSheetName, 1, 17, sFileName);
		
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
		UtilityCustomFunctions.logWriteConsole("Enqiry txt area:"+sEnquiry_TextArea);
		objCMD.setTextAreaValue(sExpModuleName, "enquirytextarea", sEnquiry_TextArea);
		
		objCMD.clickDateBox(sExpModuleName, "enquirydate");
		
		Thread.sleep(3000);
//		objCMD.clickDayInDate(2,"sEnquiryDate",null); Not Working for CreateEntity CESourceDupPrev
		//objCMD.clickDayInDate(3,"sEnquiryDate",null); not working for OOFS NEW VALUES
		String className = this.getClass().getSimpleName();
		System.out.println("CURRENT CLASS NAME:" + className);
		
		objCMD.clickDayInDate(2,"sEnquiryDate",null);
		
		String sActEnquiryDate = objCMD.fGetModuleValue(sExpModuleName, "enquirydate");
		System.out.println("AcutalDate: " + sActEnquiryDate);
		xlAddObj.setCellData(sSheetName, 1, 29, sActEnquiryDate);
		
		
		System.out.println("Enquiriy prefix: " + sEnquiry_PN_Prefix);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"enquiryphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sEnquiry_PN_Prefix);
		
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "enquiryphonenumber", sEnquiry_PhoneNumber);
		
//		objCMD.clickEnqCategory();
		objCMD.clickSelectControl(sExpModuleName,"enquirycategory");
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Enq Cat:"+sEnquiry_category);
		objCMD.selectListValue(sEnquiry_category.trim());
		Thread.sleep(2000);
		
		//Lead PhoneNumber prefix
		objCMD.ClickListPhonePrefix(sExpModuleName,"leadphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sLead_PN_Prefix);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "leadphonenumber", sLead_PN);
		
		//Lead Email
		objCMD.setGenericInputValue("email", sExpModuleName, "leademail", sLead_Email);
		
		//Lead Text
		objCMD.setGenericInputValue("text", sExpModuleName, "leadtext", sLead_Text);
		
		//Sales PN Prefix
		objCMD.ClickListPhonePrefix(sExpModuleName,"salesphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sSales_PN_Prefix);
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "salesphonenumber", sSales_PN);
		
		//Sales Email
		objCMD.setGenericInputValue("email", sExpModuleName, "salesemail", sSales_Email);
		Thread.sleep(5000);		
		
		objCMD.clickSave();
		
		Thread.sleep(5000);
	}
	public void fAddValuestoETNotification(String sEnv,String sExcelName,String sSheetName,boolean isAmend) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		UtilityCustomFunctions.logWriteConsole("Row Count to Add Values: " + iRowCount);

		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);

		UtilityCustomFunctions.logWriteConsole("Col Count is: " + iColCount);	
		UtilityCustomFunctions.logWriteConsole("Extracting DataSheet Values started...");		

		String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 1);
		String sExpAssignedTo = xlAddObj.getCellData(sSheetName, 1, 2);
		String sMobilePrefix = xlAddObj.getCellData(sSheetName, 1, 3);
		String sMobileNumber = xlAddObj.getCellData(sSheetName, 1, 4);

		String sEmail=xlAddObj.getCellData(sSheetName, 1, 5);
		String sText=xlAddObj.getCellData(sSheetName, 1, 6);
		String sMultiComboValues=xlAddObj.getCellData(sSheetName, 1, 7);
		String sTextArea=xlAddObj.getCellData(sSheetName, 1, 8);
		String sCheckBox=xlAddObj.getCellData(sSheetName, 1, 9);
		String sDate=xlAddObj.getCellData(sSheetName, 1, 10);
		String sPickList=xlAddObj.getCellData(sSheetName, 1, 11);
		String sFile=xlAddObj.getCellData(sSheetName, 1, 12);
		String sDateandTime =xlAddObj.getCellData(sSheetName, 1, 13);
		String sTime =xlAddObj.getCellData(sSheetName, 1, 14);
		String sNamePrefix =xlAddObj.getCellData(sSheetName, 1, 15);
		String sName =xlAddObj.getCellData(sSheetName, 1, 16);
		String sNumber =xlAddObj.getCellData(sSheetName, 1, 17);
		String sCurrency=xlAddObj.getCellData(sSheetName, 1, 18);
		String sURL=xlAddObj.getCellData(sSheetName, 1, 19);
		String sCity=xlAddObj.getCellData(sSheetName, 1, 20);
		String sState=xlAddObj.getCellData(sSheetName, 1, 21);
		String sCountry=xlAddObj.getCellData(sSheetName, 1, 22);
		String sRelatedModText =xlAddObj.getCellData(sSheetName, 1, 23);
		System.out.println("Module Name:  " + sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Before Adding Module data");
		Thread.sleep(3000);
		//Assigned To
		objCMD.clickArrayDropDown(1);
//		objCMD.clickAssignedTo();
		UtilityCustomFunctions.logWriteConsole("Assinged To Drop Down clicked");
		Thread.sleep(2000);
		objCMD.selectListValue(sExpAssignedTo);
		//Phone Number
		objCMD.ClickListPhonePrefix(sExpModuleName,"mobilenumber_prefix-container");
		UtilityCustomFunctions.logWriteConsole("Phone NUmber prefix clicked :  ");
		objCMD.selectListValue(sMobilePrefix);
		UtilityCustomFunctions.logWriteConsole("Phone NUmber selected is :  "+sMobileNumber);
		objCMD.setMobileNumber(sMobileNumber);
		Thread.sleep(1000);
		//Email
		Thread.sleep(1000);
		objCMD.setEmailValue(sExpModuleName, sEmail);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Email Added is: "+sEmail);
		//Text
		objCMD.setInputValue(sExpModuleName, "text",sText);
		UtilityCustomFunctions.logWriteConsole("Text Value added is::  "+sText);
		Thread.sleep(1000);
		//MultiCombo Values
		Thread.sleep(1000);
		objCMD.clickMultiComboBox(sMultiComboValues,isAmend);
		UtilityCustomFunctions.logWriteConsole("MultiCombo box values: " + sMultiComboValues);
		Thread.sleep(1000);
		//Text Area Value
		objCMD.setTextAreaValue(sExpModuleName, "textarea", sTextArea);
		//CheckBox
		Thread.sleep(1000);
		objCMD.clickArrayCheckBox(1, sCheckBox);
		UtilityCustomFunctions.logWriteConsole("Checkbox clicked");
		//Date
		objCMD.clickDateBox(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("DateBox clicked");
		Thread.sleep(3000);
		objCMD.clickDayInDate(1,"sDate",null);
		UtilityCustomFunctions.logWriteConsole("Today Date Selected");
		Thread.sleep(1000);
		//PickList
		objCMD.clickArrayDropDown(3);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Picklist clicked");
		Thread.sleep(1000);
		objCMD.selectListValue2(sPickList);
		UtilityCustomFunctions.logWriteConsole("Picklist selected: "+sPickList);
		//File Upload
		objCMD.setUploadFile();
		//Date & Time
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "datetime");
		UtilityCustomFunctions.logWriteConsole("Date & Time Clicked");
		Thread.sleep(5000);
		//For Modifying record Index 2 value passed.
		objCMD.clickDayInDate(2,"sDateandTime",null);
		Thread.sleep(2000);
		UtilityCustomFunctions.logWriteConsole("Clicked Current Date in Date&Time");
		objCMD.clickDandTApply();
		UtilityCustomFunctions.logWriteConsole("Date & Time Selected");
		//Time
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		UtilityCustomFunctions.logWriteConsole("Time Clicked");
		objCMD.clickTime("2", "35","55");
		UtilityCustomFunctions.logWriteConsole("Time Selected is: 02:35 PM");
		Thread.sleep(1000);
		
		//Write Date, Time & Date&Time to Datasheet.
		String sActDate = objCMD.fGetModuleValue(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("Actual Date Selected :" + sActDate);
        xlAddObj.setCellData(sSheetName, 1, 10, sActDate);
        
        String sActDateandTime= objCMD.fGetModuleValue(sExpModuleName, "datetime");
		System.out.println("Date & Time" + sActDateandTime);
		xlAddObj.setCellData(sSheetName, 1, 13, sActDateandTime);
		
        String sActTime= objCMD.fGetModuleValue(sExpModuleName, "time");
		System.out.println("Time" + sActTime);
		xlAddObj.setCellData(sSheetName, 1, 14, sActTime);
		
		//Enter Name
		objCMD.clickArrayDropDown(4);
		objCMD.selectListValue(sNamePrefix);
		objCMD.setInputValue(sExpModuleName, "name", sName);
		
		//Enter Number
		objCMD.setInputNumber(sExpModuleName, "number", sNumber);
		
		//Enter Currency
		objCMD.setInputValue(sExpModuleName, "currency", sCurrency);
		
		//Enter URL
		objCMD.setGenericInputValue("url", sExpModuleName, "url", sURL);
		
		//City 
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(5);
		UtilityCustomFunctions.logWriteConsole("City Drop Down clicked");
		objCMD.selectListValue(sCity);
		UtilityCustomFunctions.logWriteConsole("City Selected: " + sCity);
		Thread.sleep(1000);
		//State
		objCMD.clickArrayDropDown(6);
		UtilityCustomFunctions.logWriteConsole("State Drop Down clicked");
		objCMD.selectListValue(sState);
		UtilityCustomFunctions.logWriteConsole("State Selected: "+sState);
		Thread.sleep(1000);
		//Country
		objCMD.clickArrayDropDown(7);
		UtilityCustomFunctions.logWriteConsole("Country List clicked");
		objCMD.selectListValue(sCountry);
		UtilityCustomFunctions.logWriteConsole("Country Selected: " + sCountry);
		Thread.sleep(1000);
		
		//Related Module
		objCMD.SelectRelModValue(sRelatedModText);
		Thread.sleep(1000);		
		objCMD.clickSave();
		Thread.sleep(1000);		
		
	}	
	
	
	//Validate the Summary Page Values of Entity Module
		public void fValidateEntityModuleSummary(String sEnv,String sExcelName,String sSheetName,String sMessage,String isNotify,ExtentTest node,boolean IsTarget) throws Exception {
			CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
			String sPath="";
			if(sEnv.equalsIgnoreCase("Test")){
				sPath=".\\testData\\" + sExcelName + "Test.xlsx" ;	
			}
			else {
				sPath=".\\testData\\" + sExcelName + "Live.xlsx" ;
			}//get the test data sheet
			
			ExcelUtility xlValidObj = new ExcelUtility(sPath);
			
			logger.info("Excel file Utility instance created");
		
			int iRowCount = xlValidObj.getRowCount(sSheetName);
			System.out.println("Total rows: " + iRowCount);
			logger.info("Row Count is: " + iRowCount);
			
		
			int iColCount = xlValidObj.getCellCount(sSheetName, iRowCount);
			System.out.println("Cols: " + iColCount);
			logger.info("Col Count is: " + iColCount);
						
			logger.info("Extracting DataSheet Values started...");
			String sExpModuleName = xlValidObj.getCellData(sSheetName, 1, 0);
			String sExpTrgModuleName = xlValidObj.getCellData(sSheetName, 1, 1);
			String sExpWorkFlowName = xlValidObj.getCellData(sSheetName, 1, 2);
			String sExpAssignedTo = xlValidObj.getCellData(sSheetName, 1, 3);
			String sText=xlValidObj.getCellData(sSheetName, 1, 4);
			
			String sMobNumPrefix=xlValidObj.getCellData(sSheetName, 1, 5);
			String sMobileNumber=xlValidObj.getCellData(sSheetName, 1, 6);
			String sEmail=xlValidObj.getCellData(sSheetName, 1, 7);
			String sPickListValue=xlValidObj.getCellData(sSheetName, 1, 8);
			String sMultiComboValues=xlValidObj.getCellData(sSheetName, 1, 9);
			String sCity=xlValidObj.getCellData(sSheetName, 1, 10);
			String sState=xlValidObj.getCellData(sSheetName, 1, 11);
			String sCountry=xlValidObj.getCellData(sSheetName, 1, 12);
			String sCheckBox=xlValidObj.getCellData(sSheetName, 1, 13);
			if(sCheckBox.equalsIgnoreCase("ON")) {
				sCheckBox="Yes";
			}
			else {
				sCheckBox="No";
			}
			String sDate =xlValidObj.getCellData(sSheetName, 1, 14);
			String sTime =xlValidObj.getCellData(sSheetName, 1, 15);
			String sDateandTime =xlValidObj.getCellData(sSheetName, 1, 16);
			String sRelatedModule =xlValidObj.getCellData(sSheetName, 1, 17);
			String sFilePath =xlValidObj.getCellData(sSheetName, 1, 18);
			String sNamePrefix =xlValidObj.getCellData(sSheetName, 1, 19);
			String sName=xlValidObj.getCellData(sSheetName, 1, 20);
			String sNumber=xlValidObj.getCellData(sSheetName, 1, 21);
			String sCurrency=xlValidObj.getCellData(sSheetName, 1, 22);
			String sUrl=xlValidObj.getCellData(sSheetName, 1, 23);
			String sEnqNamePrefix=xlValidObj.getCellData(sSheetName, 1, 24);
			String sEnquiry_Name=xlValidObj.getCellData(sSheetName, 1, 25);
			String sEnquiry_Email=xlValidObj.getCellData(sSheetName, 1, 26);
			String sEnquiry_Text=xlValidObj.getCellData(sSheetName, 1, 27);
			String sEnquiry_TextArea=xlValidObj.getCellData(sSheetName, 1, 28);
			String sEnquiry_Date=xlValidObj.getCellData(sSheetName, 1, 29);
			String sEnquiry_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 30);
			String sEnquiry_PhoneNumber=xlValidObj.getCellData(sSheetName, 1, 31);
			String sEnquiry_category=xlValidObj.getCellData(sSheetName, 1, 32);
			String sExecutionCondition=xlValidObj.getCellData(sSheetName, 1, 33);
			String sActionType=xlValidObj.getCellData(sSheetName, 1, 34);
			String sActionTitle=xlValidObj.getCellData(sSheetName, 1, 35);
			String sWorkFlowPos=xlValidObj.getCellData(sSheetName, 1, 36);
			String sUser1MessageId=xlValidObj.getCellData(sSheetName, 1, 37);
			String sUser2MessageId=xlValidObj.getCellData(sSheetName, 1, 38);
			String sUser3MessageId=xlValidObj.getCellData(sSheetName, 1, 39);
			String sUser1RecordId=xlValidObj.getCellData(sSheetName, 1, 40);
			String sUser2RecordId=xlValidObj.getCellData(sSheetName, 1, 41);
			String sUser3RecordId=xlValidObj.getCellData(sSheetName, 1, 42);
			String sDisplayMod1=xlValidObj.getCellData(sSheetName, 1, 43);
			String sDisplayMod2=xlValidObj.getCellData(sSheetName, 1, 44);
			String sEditIndText=xlValidObj.getCellData(sSheetName, 1, 45);
			String sLead_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 46);
			String sLead_PN=xlValidObj.getCellData(sSheetName, 1, 47);
			String sLead_Email=xlValidObj.getCellData(sSheetName, 1, 48);
			String sLead_Text=xlValidObj.getCellData(sSheetName, 1, 49);
			String sSales_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 50);
			String sSales_PN=xlValidObj.getCellData(sSheetName, 1, 51);
			String sSales_Email=xlValidObj.getCellData(sSheetName, 1, 52);
			DetailViewPage objDVP = new DetailViewPage(driver);
			UtilityCustomFunctions.logWriteConsole("Before clicking last record to Validate");
//			fClickFirstRecord();
			Thread.sleep(6000);
			objDVP.fSetToggleHeader(true);
			objDVP.fSetDetailVew(false);
			String sCurrentWinHandle="";
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
			driver.navigate().refresh();
			Thread.sleep(6000);
			if(IsTarget==true) {
				sExpModuleName = sExpTrgModuleName;
			}
			String aActModuleName = objDVP.getNavBarModuleName();
			UtilityCustomFunctions.logWriteConsole("Entity Module Name: "+sExpModuleName);
			UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name : " + sMessage, node);
			
			// Title Actual Values
			String sActAssignedTo = objDVP.getGenericTitle(1).trim() + " "+objDVP.getGenericTitle(2).trim();
			String sActPNTitle = objDVP.getGenericTitle(3).trim(); 
//			String sPNTitleArray[] = sActPNTitle.split("\\s+");
//			sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
//			String sActEMTitle = objDVP.getGenericTitle(4).trim();
			String sActEMTitle = objDVP.getGenericTitle(4).trim();
			String sExpMobileNumber= sMobNumPrefix+" " + sMobileNumber;
			//Title Validations
			UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sExpAssignedTo, "Assigned To Title: " + sMessage, node);
			UtilityCustomFunctions.fSoftAssert(sActPNTitle, sExpMobileNumber, "Mobile Number Title : " + sMessage, node);
			UtilityCustomFunctions.fSoftAssert(sActEMTitle, sEmail, "Email Title: " + sMessage, node);
			
			
			Thread.sleep(3000);
//			objDVP.clickSummaryView();
			
			String sCurrentTime =objDVP.getCurrentTime();
			xlValidObj.setCellData(sSheetName, 0, 53, "CurrentTime");
			xlValidObj.setCellData(sSheetName, 1, 53, sCurrentTime);
			String sActSummaryAssignedTo = objDVP.getSummaryAssignTo().trim();
			UtilityCustomFunctions.fSoftAssert(sActSummaryAssignedTo, sExpAssignedTo, "Summary AssignedTo:  " + sMessage, node);
			
			String sActSummaryText = objDVP.getArraySummary(1).trim();
			UtilityCustomFunctions.fSoftAssert(sActSummaryText, sText, "Summary Text:  " + sMessage, node);
			
			String sActMobileNumber = objDVP.getArraySummary(2).trim();
			String sMobNMArray[] = sActMobileNumber.split("\\s+");
			sActMobileNumber = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
					
			System.out.println("Actual mobile number: " + sActMobileNumber);
			UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sExpMobileNumber, "Summary Mobile Number:  " + sMessage, node);
			
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
			UtilityCustomFunctions.logWriteConsole("Expected Date from sheet:" + sDate);
//			String fromDateFormat = "yyyy/MM/dd";
//			SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd");
//			Date date = sdfSource.parse(sDate);
//			SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
//			sDate= sdfDestination.format(date);
//			UtilityCustomFunctions.logWriteConsole("Formatted Date from sheet:" + sDate);
			UtilityCustomFunctions.fSoftAssert(sActDate, sDate, "Summary Date:  " + sMessage, node);
			
			UtilityCustomFunctions.logWriteConsole("Expected Time from sheet:" + sTime);
			String sActTime= objDVP.getArraySummary(11).trim();
			
//		    fromDateFormat = "hh:mm:ss";
//			SimpleDateFormat sdfSource1 = new SimpleDateFormat(fromDateFormat);
//			Date date1 = sdfSource1.parse(sTime);
//			SimpleDateFormat sdfDestination1 = new SimpleDateFormat("hh:mm:ss aa");
//			sTime= sdfDestination1.format(date1);
			UtilityCustomFunctions.logWriteConsole("Formatted Time from sheet:" + sTime);
			UtilityCustomFunctions.fSoftAssert(sActTime, sTime, "Summary Time:  " + sMessage, node);
			
			UtilityCustomFunctions.logWriteConsole("Expected Date Time from sheet:" + sDateandTime);
			String sActDTandTime = objDVP.getArraySummary(12).trim();
//			Date date2;
//			String fromDateFormat2;
//			if(IsDate==false) {
//				fromDateFormat2 = "yyyy-MM-dd hh:mm:ss a";
//				SimpleDateFormat sdfSource2 = new SimpleDateFormat(fromDateFormat2);
//				date2 = sdfSource2.parse(sDateandTime);	
//			}
//			else {
//				fromDateFormat2 = "yyyy/MM/dd hh:mm:ss a";
//				SimpleDateFormat sdfSource2 = new SimpleDateFormat(fromDateFormat2);
//				date2 = sdfSource2.parse(sDateandTime);	
//			}
//			
//			SimpleDateFormat sdfDestination2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//			sDateandTime= sdfDestination2.format(date2);
			UtilityCustomFunctions.logWriteConsole("Formatted date & Time from sheet:" + sDateandTime);
			
			
			UtilityCustomFunctions.fSoftAssert(sActDTandTime, sDateandTime, "Summary Date & Time :  " + sMessage, node);
			
//			String sActRelatedModule = objDVP.getArraySummary(13).trim();
//			UtilityCustomFunctions.fSoftAssert(sActRelatedModule, sRelatedModule, "Summary Related Module :  " + sMessage, node);
			String sActUploadFile="";
			if(objDVP.getUploadFileText()!=null) {
				sActUploadFile= objDVP.getUploadFileText();
			}
			if(sActUploadFile.contains(sFilePath)) {
				UtilityCustomFunctions.fSoftAssert(sFilePath, sFilePath, "Summary File Upload :  " + sMessage, node);
			}
			else {
				UtilityCustomFunctions.fSoftAssert(sActUploadFile, sFilePath, "Summary File Upload :  " + sMessage, node);
			}
			System.out.println("Actual Summary sActUploadFile: " + sActUploadFile);
			String sExpName = sNamePrefix + " " + sName;
			String sActName = objDVP.getArraySummary(14).trim();
			String sNameArray[] = sActName.split("\\s+");
			sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
			UtilityCustomFunctions.logWriteConsole("ActualName:"+sActName+"ExpectedName:"+sExpName);
			UtilityCustomFunctions.fSoftAssert(sActName, sExpName, "Summary Name:  " + sMessage, node);
			String sActNumber = objDVP.getArraySummary(15).trim();
			UtilityCustomFunctions.fSoftAssert(sActNumber, sNumber, "Summary Number:  " + sMessage, node);
			
			String sActCurrency = objDVP.getArraySummary(16).trim();
			UtilityCustomFunctions.fSoftAssert(sActCurrency, sCurrency, "Summary Currency:  " + sMessage, node);
			
			String sActUrl = objDVP.getSummaryUrl().trim();
			UtilityCustomFunctions.fSoftAssert(sActUrl, sUrl, "Summary Url:  " + sMessage, node);
			
			String sActEnq_Name=objDVP.getArraySummary(18).trim();
			String sEnqNameArray[] = sActEnq_Name.split("\\s+");
			sActEnq_Name = sEnqNameArray[0].trim() + " " + sEnqNameArray[1].trim();
		
			System.out.println("Actual Summary Eqnuiry Name: " + sActEnq_Name);
			
			sEnquiry_Name = sEnqNamePrefix + " " + sEnquiry_Name;
			
			UtilityCustomFunctions.fSoftAssert(sActEnq_Name, sEnquiry_Name, "Summary Enquiry Name:  " + sMessage, node);
			
			String sActEnq_Email=objDVP.getArraySummary(19).trim();
			UtilityCustomFunctions.fSoftAssert(sActEnq_Email, sEnquiry_Email, "Summary Enquiry Email:  " + sMessage, node);
			
			String sActEnq_Text=objDVP.getArraySummary(20).trim();
			sActEnq_Text = UtilityCustomFunctions.fArrayConcat(sActEnq_Text);
			String sExpEnquiry_Text="";
			if(IsTarget==true) {
				sExpEnquiry_Text = sText + " " + sEnquiry_Text;	
			}
			sExpEnquiry_Text = UtilityCustomFunctions.fArrayConcat(sExpEnquiry_Text);
			UtilityCustomFunctions.fSoftAssert(sActEnq_Text, sEnquiry_Text, "Summary Enquiry Text:  " + sMessage, node);
			
			String sActEnq_TextArea=objDVP.getArraySummary(21).trim();
			sActEnq_TextArea = UtilityCustomFunctions.fArrayConcat(sActEnq_TextArea);
			String sExpEnquiryTextArea_Text="";
			if(IsTarget==true) {
				sExpEnquiryTextArea_Text = sText + " " + sEnquiry_Text + " " + sEnquiry_TextArea;	
			}
			sExpEnquiryTextArea_Text = UtilityCustomFunctions.fArrayConcat(sExpEnquiryTextArea_Text);
			sEnquiry_TextArea = UtilityCustomFunctions.fArrayConcat(sEnquiry_TextArea);
			UtilityCustomFunctions.fSoftAssert(sActEnq_TextArea, sEnquiry_TextArea, "Summary Enquiry Text Area:  " + sMessage, node);
			
			UtilityCustomFunctions.logWriteConsole("Expected enq Date:" + sEnquiry_Date);
			String sActEnq_Date=objDVP.getArraySummary(22).trim();
//			Date date4;
//			String fromDateFormat4;
//			if(IsDate==false) {
//				fromDateFormat4 = "yyyy-MM-dd";
//				SimpleDateFormat sdfSource4 = new SimpleDateFormat("yyyy-MM-dd");
//				date4 = sdfSource4.parse(sEnquiry_Date);
//			}
//			else {
//				fromDateFormat4 = "yyyy/MM/dd";
//				SimpleDateFormat sdfSource4 = new SimpleDateFormat("yyyy/MM/dd");
//				date4 = sdfSource4.parse(sEnquiry_Date);
//			}
//			
//			SimpleDateFormat sdfDestination4 = new SimpleDateFormat("yyyy-MM-dd");
//			sEnquiry_Date= sdfDestination4.format(date4);
			UtilityCustomFunctions.logWriteConsole("Formatted Enquiry Date from sheet:" + sEnquiry_Date);
				
			UtilityCustomFunctions.fSoftAssert(sActEnq_Date, sEnquiry_Date, "Summary Enquiry Date:  " + sMessage, node);
			
			
			sEnquiry_PhoneNumber = sEnquiry_PN_Prefix + " " + sEnquiry_PhoneNumber ; 
			
			String sActEnq_PhoneNumber=objDVP.getArraySummary(23).trim();
			System.out.println("Actual Enquiry Phone Number: " + sActEnq_PhoneNumber);
			
			
			String sEnqPNArray[] = sActEnq_PhoneNumber.split("\\s+");
			sActEnq_PhoneNumber = sEnqPNArray[0].trim() + " " + sEnqPNArray[1].trim();
			System.out.println("Actual: " + sActEnq_PhoneNumber);
			
			
			UtilityCustomFunctions.fSoftAssert(sActEnq_PhoneNumber, sEnquiry_PhoneNumber, "Summary Enquiry Phone Number:  " + sMessage, node);
			
			String sActEnq_Category=objDVP.getArraySummary(24).trim();
			UtilityCustomFunctions.fSoftAssert(sActEnq_Category, sEnquiry_category, "Summary Enquiry Category:  " + sMessage, node);
			

			//Leads Validation.
			
			String sLead_PhoneNumber = sLead_PN_Prefix + " " + sLead_PN ;
			
			String sActLead_PhoneNumber=objDVP.getArraySummary(25).trim();
			System.out.println("Actual Lead Summary Phone Number: " + sActLead_PhoneNumber);
			
			
			String sActLeadPNArray[] = sActLead_PhoneNumber.split("\\s+");
			sActLead_PhoneNumber = sActLeadPNArray[0].trim() + " " + sActLeadPNArray[1].trim();
			System.out.println("Actual: " + sActLead_PhoneNumber);
			
			UtilityCustomFunctions.fSoftAssert(sActLead_PhoneNumber, sLead_PhoneNumber, "Summary Lead PhoneNumber:  " + sMessage, node);
			//Lead Email
			String sActLead_Email=objDVP.getArraySummary(26).trim();
			UtilityCustomFunctions.fSoftAssert(sActLead_Email, sLead_Email, "Summary Lead Email:  " + sMessage, node);
			
			//Lead Text
			String sActLead_Text=objDVP.getArraySummary(27).trim();
			sActLead_Text = UtilityCustomFunctions.fArrayConcat(sActLead_Text);
			String sExpLead_Text = "";
			if(IsTarget==true) {
				sExpLead_Text = sText + " " + sEnquiry_Text +" " + sEnquiry_TextArea + " " + sLead_Text;	
			}
			sExpLead_Text = UtilityCustomFunctions.fArrayConcat(sExpLead_Text);
			UtilityCustomFunctions.fSoftAssert(sActLead_Text, sLead_Text, "Summary Lead Text:  " + sMessage, node);
			//Sales Validation
			String sSales_PhoneNumber = sSales_PN_Prefix + " " + sSales_PN ;
			
			String sActSales_PhoneNumber=objDVP.getArraySummary(28).trim();
			System.out.println("Actual Sales Summary Phone Number: " + sActSales_PhoneNumber);
			
			
			String sActSalesPNArray[] = sActSales_PhoneNumber.split("\\s+");
			sActSales_PhoneNumber = sActSalesPNArray[0].trim() + " " + sActSalesPNArray[1].trim();
			System.out.println("Actual: " + sActSales_PhoneNumber);
			
			UtilityCustomFunctions.fSoftAssert(sActSales_PhoneNumber, sSales_PhoneNumber, "Summary Sales PhoneNumber:  " + sMessage, node);
			
			
			String sActSales_Email=objDVP.getArraySummary(29).trim();
			UtilityCustomFunctions.fSoftAssert(sActSales_Email, sSales_Email, "Summary Sales Email:  " + sMessage, node);
		
			//Details View
			objDVP.fSetDetailVew(true);
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(3000);
			String sActDTAssignedTo = objDVP.getArrayDetails(4).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sExpAssignedTo, "Detail View AssignedTo:  " + sMessage, node);
			
			String sActDTText= objDVP.getArrayDetails(6).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTText, sText, "Detail View Text:  " + sMessage, node);
			
			String sActDTMobileNumber= objDVP.getArrayDetails(7).trim();
			UtilityCustomFunctions.logWriteConsole("sActDTMobileNumber "+ sActDTMobileNumber);
			
			String sDTMobileNMArray[] = sActDTMobileNumber.split("\\s+");
			if(sDTMobileNMArray.length>=1) {
			 sActDTMobileNumber = sDTMobileNMArray[0].trim() + " " + sDTMobileNMArray[1].trim();
			}
			System.out.println("actual mobile number: " + sActDTMobileNumber);
			UtilityCustomFunctions.fSoftAssert(sActDTMobileNumber, sExpMobileNumber, "Detail View Mobile Number:  " + sMessage, node);
			
			String sActDTEmail= objDVP.getArrayDetails(8).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEmail, sEmail, "Detail View Email :  " + sMessage, node);
			
			String sActDTPicList= objDVP.getArrayDetails(9).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTPicList, sPickListValue, "Detail View PicListValue :  " + sMessage, node);
			
			String sActDTMultiCombo= objDVP.getArrayDetails(10).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTMultiCombo, sMultiComboValues, "Detail View MuultiCombo Values :  " + sMessage, node);
			
			String sActDTCity= objDVP.getArrayDetails(11).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTCity, sCity, "Detail View City:  " + sMessage, node);
			
			String sActDTState= objDVP.getArrayDetails(12).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTState, sState, "Detail View Stat:  " + sMessage, node);
			
			String sActDTCountry= objDVP.getArrayDetails(13).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTCountry, sCountry, "Detail View Country:  " + sMessage, node);
			
			String sActDTChekBox= objDVP.getArrayDetails(14).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTChekBox, sCheckBox, "Detail View CheckBox:  " + sMessage, node);
			
			String sActDTDate= objDVP.getArrayDetails(15).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTDate, sDate, "Detail View Date:  " + sMessage, node);
			
			String sActDTTime= objDVP.getArrayDetails(16).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTTime, sTime, "Detail View Time:  " + sMessage, node);
			
			String sActDTDateandTime= objDVP.getArrayDetails(17).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTDateandTime, sDateandTime, "Detail View Date & Time :  " + sMessage, node);
			
//			String sActDTRelModule= objDVP.getArrayDetails(18).trim();
//			UtilityCustomFunctions.fSoftAssert(sActDTRelModule, sRelatedModule, "Detail View Related Module :  " + sMessage, node);
			
			String sActDTUploadFile = "";
			if(objDVP.getUploadFileText()!=null) {
				sActDTUploadFile= objDVP.getUploadFileText();
			}
			if(sActDTUploadFile.contains(sFilePath)) {
				UtilityCustomFunctions.fSoftAssert(sFilePath, sFilePath, "Detail View File Upload :  " + sMessage, node);
			}
			else {
				UtilityCustomFunctions.fSoftAssert(sActDTUploadFile, sFilePath, "Detail View Upload :  " + sMessage, node);
			}
			sExpName = sNamePrefix + " " + sName;
			
			sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
			
			String sActDTName= objDVP.getArrayDetails(20).trim();
			String sDTNameArray[] = sActDTName.split("\\s+");
			sActDTName = sDTNameArray[0].trim() + " " + sDTNameArray[1].trim();
			UtilityCustomFunctions.logWriteConsole("ActualNameDT:"+sActDTName+"ExpectedNameDT:"+sExpName);
			UtilityCustomFunctions.fSoftAssert(sActDTName, sExpName, "Detail View Name:  " + sMessage, node);
			
			String sActDTNumber= objDVP.getArrayDetails(21).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTNumber, sNumber, "Detail View Number:  " + sMessage, node);
			
			String sActDTCurrency= objDVP.getArrayDetails(22).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTCurrency, sCurrency, "Detail View Currency:  " + sMessage, node);
			
			String sActDTUrl= objDVP.getDTSummaryUrl().trim();
			UtilityCustomFunctions.fSoftAssert(sActDTUrl, sUrl, "Detail View Url:  " + sMessage, node);
			Thread.sleep(3000);
			objDVP.fClickDetailBlockB();
			Thread.sleep(3000);
			
			String sActDTEnqName = objDVP.getArrayDetails(24).trim();
			
			String sDTEnqNameArray[] = sActDTEnqName.split("\\s+");
			sActDTEnqName = sDTEnqNameArray[0].trim() + " " + sDTEnqNameArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEnqName, sEnquiry_Name, "Detail View Enquiry Name:  " + sMessage, node);
			
			String sActDTEnqEmail= objDVP.getArrayDetails(25).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEnqEmail, sEnquiry_Email, "Detail View Enquiry Email:  " + sMessage, node);
			
			String sActDTEnqText= objDVP.getArrayDetails(26).trim();
			sActDTEnqText = UtilityCustomFunctions.fArrayConcat(sActDTEnqText);
			UtilityCustomFunctions.fSoftAssert(sActDTEnqText, sEnquiry_Text, "Detail View Enquiry Text:  " + sMessage, node);
			
			String sActDTEnqTextArea= objDVP.getArrayDetails(27).trim();
			sActDTEnqTextArea = UtilityCustomFunctions.fArrayConcat(sActDTEnqTextArea);
			UtilityCustomFunctions.fSoftAssert(sActDTEnqTextArea, sEnquiry_TextArea, "Detail View Enquiry Text Area:  " + sMessage, node);
			
			String sActDTEnqDate= objDVP.getArrayDetails(28).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEnqDate, sEnquiry_Date, "Detail View Enquiry Date:  " + sMessage, node);
			
			String sActDTEnqPN= objDVP.getArrayDetails(29).trim();
			String sEnqActDTArray[] = sActDTEnqPN.split("\\s+");
			sActDTEnqPN = sEnqActDTArray[0].trim() + " " + sEnqActDTArray[1].trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEnqPN, sEnquiry_PhoneNumber, "Detail View Enquiry Phone Number:  " + sMessage, node);
			
			String sActDTEnqCategory= objDVP.getArrayDetails(30).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTEnqCategory, sEnquiry_category, "Detail View Enquiry Category:  " + sMessage, node);
			
			Thread.sleep(3000);
			objDVP.fClickDetailBlockC();
			Thread.sleep(3000);
			
			String sActLeadDTMobileNumber= objDVP.getArrayDetails(31).trim();
			String sActLeadDTMobileNMArray[] = sActLeadDTMobileNumber.split("\\s+");
			sActLeadDTMobileNumber = sActLeadDTMobileNMArray[0].trim() + " " + sActLeadDTMobileNMArray[1].trim();
			System.out.println("actual mobile number: " + sActLeadDTMobileNumber);
			
			String sDTLead_PN = sLead_PN_Prefix + " " + sLead_PN;
			UtilityCustomFunctions.fSoftAssert(sActLeadDTMobileNumber, sDTLead_PN, "DT Lead Mobile Number:  " + sMessage, node);
			//Created Time added which is not captured.
			
			String sActDTLeadEmail= objDVP.getArrayDetails(32).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTLeadEmail, sLead_Email, "DT View Lead Email  " + sMessage, node);
			
			String sActDTLeadText= objDVP.getArrayDetails(33).trim();
			sActDTLeadText = UtilityCustomFunctions.fArrayConcat(sActDTLeadText);
			sExpLead_Text = "";
			if(IsTarget==true) {
				sExpLead_Text = sText + " " + sEnquiry_Text +" " + sEnquiry_TextArea + " " + sLead_Text;	
			}
			sExpLead_Text = UtilityCustomFunctions.fArrayConcat(sExpLead_Text);
			UtilityCustomFunctions.fSoftAssert(sActDTLeadText, sLead_Text, "DT View Lead Text " + sMessage, node);
			
			//Sales
			String sActSalesDTMobileNumber= objDVP.getArrayDetails(34).trim();
			String sActSalesDTMobileNMArray[] = sActSalesDTMobileNumber.split("\\s+");
			sActSalesDTMobileNumber = sActSalesDTMobileNMArray[0].trim() + " " + sActSalesDTMobileNMArray[1].trim();
			
			System.out.println("actual mobile number: " + sActSalesDTMobileNumber );
			
			String sDTSales_PN = sSales_PN_Prefix + " " + sSales_PN;
			UtilityCustomFunctions.fSoftAssert(sActSalesDTMobileNumber, sDTSales_PN, "DT Sales Mobile Number:  " + sMessage, node);
			
			String sActDTSalesEmail= objDVP.getArrayDetails(35).trim();
			UtilityCustomFunctions.fSoftAssert(sActDTSalesEmail, sSales_Email, "DT View Sales Email" + sMessage, node);
			
			if(isNotify.equalsIgnoreCase("Yes")){
				driver.close();
			}
			
			System.out.println("Inside Validate All fields: isNotify" + isNotify);
		}
		
	//	Validate Entity with Single text field Summary/Detail View
		public void fValidateEntityResponse(String sEnv,String sExcelName,String sSheetName,String sMessage,String isNotify,ExtentTest node,boolean IsTarget) throws Exception {
		    CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		    String sPath="";
		    if(sEnv.equalsIgnoreCase("Test")){
		        sPath=".\\testData\\" + sExcelName + "Test.xlsx" ;	
		    }
		    else {
		        sPath=".\\testData\\" + sExcelName + "Live.xlsx" ;
		    }//get the test data sheet
		    
		    ExcelUtility xlValidObj = new ExcelUtility(sPath);
		    
		    logger.info("Excel file Utility instance created");

		    int iRowCount = xlValidObj.getRowCount(sSheetName);
		    System.out.println("Total rows: " + iRowCount);
		    logger.info("Row Count is: " + iRowCount);
		    

		    int iColCount = xlValidObj.getCellCount(sSheetName, iRowCount);
		    System.out.println("Cols: " + iColCount);
		    logger.info("Col Count is: " + iColCount);
		                
		    logger.info("Extracting DataSheet Values started...");
		    String sExpModuleName = xlValidObj.getCellData(sSheetName, 1, 0);
		    String sExpTrgModuleName = xlValidObj.getCellData(sSheetName, 1, 1);
		    String sExpWorkFlowName = xlValidObj.getCellData(sSheetName, 1, 2);
		    String sExpAssignedTo = xlValidObj.getCellData(sSheetName, 1, 3);
		    String sText=xlValidObj.getCellData(sSheetName, 1, 4);
		    
		    String sMobNumPrefix=xlValidObj.getCellData(sSheetName, 1, 5);
		    String sMobileNumber=xlValidObj.getCellData(sSheetName, 1, 6);
		    String sEmail=xlValidObj.getCellData(sSheetName, 1, 7);
		    String sPickListValue=xlValidObj.getCellData(sSheetName, 1, 8);
		    String sMultiComboValues=xlValidObj.getCellData(sSheetName, 1, 9);
		    String sCity=xlValidObj.getCellData(sSheetName, 1, 10);
		    String sState=xlValidObj.getCellData(sSheetName, 1, 11);
		    String sCountry=xlValidObj.getCellData(sSheetName, 1, 12);
		    String sCheckBox=xlValidObj.getCellData(sSheetName, 1, 13);
		    if(sCheckBox.equalsIgnoreCase("ON")) {
		        sCheckBox="Yes";
		    }
		    else {
		        sCheckBox="No";
		    }
		    String sDate =xlValidObj.getCellData(sSheetName, 1, 14);
		    String sTime =xlValidObj.getCellData(sSheetName, 1, 15);
		    String sDateandTime =xlValidObj.getCellData(sSheetName, 1, 16);
		    String sRelatedModule =xlValidObj.getCellData(sSheetName, 1, 17);
		    String sFilePath =xlValidObj.getCellData(sSheetName, 1, 18);
		    String sNamePrefix =xlValidObj.getCellData(sSheetName, 1, 19);
		    String sName=xlValidObj.getCellData(sSheetName, 1, 20);
		    String sNumber=xlValidObj.getCellData(sSheetName, 1, 21);
		    String sCurrency=xlValidObj.getCellData(sSheetName, 1, 22);
		    String sUrl=xlValidObj.getCellData(sSheetName, 1, 23);
		    String sEnqNamePrefix=xlValidObj.getCellData(sSheetName, 1, 24);
		    String sEnquiry_Name=xlValidObj.getCellData(sSheetName, 1, 25);
		    String sEnquiry_Email=xlValidObj.getCellData(sSheetName, 1, 26);
		    String sEnquiry_Text=xlValidObj.getCellData(sSheetName, 1, 27);
		    String sEnquiry_TextArea=xlValidObj.getCellData(sSheetName, 1, 28);
		    String sEnquiry_Date=xlValidObj.getCellData(sSheetName, 1, 29);
		    String sEnquiry_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 30);
		    String sEnquiry_PhoneNumber=xlValidObj.getCellData(sSheetName, 1, 31);
		    String sEnquiry_category=xlValidObj.getCellData(sSheetName, 1, 32);
		    String sExecutionCondition=xlValidObj.getCellData(sSheetName, 1, 33);
		    String sActionType=xlValidObj.getCellData(sSheetName, 1, 34);
		    String sActionTitle=xlValidObj.getCellData(sSheetName, 1, 35);
		    String sWorkFlowPos=xlValidObj.getCellData(sSheetName, 1, 36);
		    String sUser1MessageId=xlValidObj.getCellData(sSheetName, 1, 37);
		    String sUser2MessageId=xlValidObj.getCellData(sSheetName, 1, 38);
		    String sUser3MessageId=xlValidObj.getCellData(sSheetName, 1, 39);
		    String sUser1RecordId=xlValidObj.getCellData(sSheetName, 1, 40);
		    String sUser2RecordId=xlValidObj.getCellData(sSheetName, 1, 41);
		    String sUser3RecordId=xlValidObj.getCellData(sSheetName, 1, 42);
		    String sDisplayMod1=xlValidObj.getCellData(sSheetName, 1, 43);
		    String sDisplayMod2=xlValidObj.getCellData(sSheetName, 1, 44);
		    String sEditIndText=xlValidObj.getCellData(sSheetName, 1, 45);
		    String sLead_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 46);
		    String sLead_PN=xlValidObj.getCellData(sSheetName, 1, 47);
		    String sLead_Email=xlValidObj.getCellData(sSheetName, 1, 48);
		    String sLead_Text=xlValidObj.getCellData(sSheetName, 1, 49);
		    String sSales_PN_Prefix=xlValidObj.getCellData(sSheetName, 1, 50);
		    String sSales_PN=xlValidObj.getCellData(sSheetName, 1, 51);
		    String sSales_Email=xlValidObj.getCellData(sSheetName, 1, 52);
		    DetailViewPage objDVP = new DetailViewPage(driver);
		    UtilityCustomFunctions.logWriteConsole("Before clicking last record to Validate");
//					fClickFirstRecord();
		    Thread.sleep(6000);
		    objDVP.fSetToggleHeader(true);
		    objDVP.fSetDetailVew(false);
		    String sCurrentWinHandle="";
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
		    driver.navigate().refresh();
		    Thread.sleep(6000);
		    if(IsTarget==true) {
		        sExpModuleName = sExpTrgModuleName;
		    }
		    String aActModuleName = objDVP.getNavBarModuleName();
		    UtilityCustomFunctions.logWriteConsole("Entity Module Name: "+sExpModuleName);
		    UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName, "Module Name : " + sMessage, node);
		    
		    // Title Actual Values
		    String sActAssignedTo = objDVP.getGenericTitle(1).trim() + " "+objDVP.getGenericTitle(2).trim();
		    String sActPNTitle = objDVP.getGenericTitle(3).trim(); 
//					String sPNTitleArray[] = sActPNTitle.split("\\s+");
//					sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim();
//					String sActEMTitle = objDVP.getGenericTitle(4).trim();
		    String sActEMTitle = objDVP.getGenericTitle(4).trim();
		    String sExpMobileNumber= sMobNumPrefix+" " + sMobileNumber;
		    //Title Validations
		    UtilityCustomFunctions.fSoftAssert(sActAssignedTo, sExpAssignedTo, "Assigned To Title: " + sMessage, node);
		    UtilityCustomFunctions.fSoftAssert(sActPNTitle, sExpMobileNumber, "Mobile Number Title : " + sMessage, node);
		    UtilityCustomFunctions.fSoftAssert(sActEMTitle, sEmail, "Email Title: " + sMessage, node);
		    
		    
		    Thread.sleep(3000);
//					objDVP.clickSummaryView();
		    
		    String sCurrentTime =objDVP.getCurrentTime();
		    xlValidObj.setCellData(sSheetName, 0, 53, "CurrentTime");
		    xlValidObj.setCellData(sSheetName, 1, 53, sCurrentTime);
		    String sActSummaryAssignedTo = objDVP.getSummaryAssignTo().trim();
		    UtilityCustomFunctions.fSoftAssert(sActSummaryAssignedTo, sExpAssignedTo, "Summary AssignedTo:  " + sMessage, node);
		    
		    String sActSummaryText = objDVP.getArraySummary(1).trim();
		    UtilityCustomFunctions.fSoftAssert(sActSummaryText, sText, "Summary Text:  " + sMessage, node);
		    
		    String sActMobileNumber = objDVP.getArraySummary(2).trim();
		    String sMobNMArray[] = sActMobileNumber.split("\\s+");
		    sActMobileNumber = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
		            
		    System.out.println("Actual mobile number: " + sActMobileNumber);
		    UtilityCustomFunctions.fSoftAssert(sActMobileNumber, sExpMobileNumber, "Summary Mobile Number:  " + sMessage, node);
		    
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
		    UtilityCustomFunctions.logWriteConsole("Expected Date from sheet:" + sDate);
//					String fromDateFormat = "yyyy/MM/dd";
//					SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy/MM/dd");
//					Date date = sdfSource.parse(sDate);
//					SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
//					sDate= sdfDestination.format(date);
//					UtilityCustomFunctions.logWriteConsole("Formatted Date from sheet:" + sDate);
		    UtilityCustomFunctions.fSoftAssert(sActDate, sDate, "Summary Date:  " + sMessage, node);
		    
		    UtilityCustomFunctions.logWriteConsole("Expected Time from sheet:" + sTime);
		    String sActTime= objDVP.getArraySummary(11).trim();
		    
//				    fromDateFormat = "hh:mm:ss";
//					SimpleDateFormat sdfSource1 = new SimpleDateFormat(fromDateFormat);
//					Date date1 = sdfSource1.parse(sTime);
//					SimpleDateFormat sdfDestination1 = new SimpleDateFormat("hh:mm:ss aa");
//					sTime= sdfDestination1.format(date1);
		    UtilityCustomFunctions.logWriteConsole("Formatted Time from sheet:" + sTime);
		    UtilityCustomFunctions.fSoftAssert(sActTime, sTime, "Summary Time:  " + sMessage, node);
		    
		    UtilityCustomFunctions.logWriteConsole("Expected Date Time from sheet:" + sDateandTime);
		    String sActDTandTime = objDVP.getArraySummary(12).trim();
//					Date date2;
//					String fromDateFormat2;
//					if(IsDate==false) {
//						fromDateFormat2 = "yyyy-MM-dd hh:mm:ss a";
//						SimpleDateFormat sdfSource2 = new SimpleDateFormat(fromDateFormat2);
//						date2 = sdfSource2.parse(sDateandTime);	
//					}
//					else {
//						fromDateFormat2 = "yyyy/MM/dd hh:mm:ss a";
//						SimpleDateFormat sdfSource2 = new SimpleDateFormat(fromDateFormat2);
//						date2 = sdfSource2.parse(sDateandTime);	
//					}
//					
//					SimpleDateFormat sdfDestination2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
//					sDateandTime= sdfDestination2.format(date2);
		    UtilityCustomFunctions.logWriteConsole("Formatted date & Time from sheet:" + sDateandTime);
		    
		    
		    UtilityCustomFunctions.fSoftAssert(sActDTandTime, sDateandTime, "Summary Date & Time :  " + sMessage, node);
		    
//					String sActRelatedModule = objDVP.getArraySummary(13).trim();
//					UtilityCustomFunctions.fSoftAssert(sActRelatedModule, sRelatedModule, "Summary Related Module :  " + sMessage, node);
		    String sActUploadFile="";
		    if(objDVP.getUploadFileText()!=null) {
		        sActUploadFile= objDVP.getUploadFileText();
		    }
		    if(sActUploadFile.contains(sFilePath)) {
		        UtilityCustomFunctions.fSoftAssert(sFilePath, sFilePath, "Summary File Upload :  " + sMessage, node);
		    }
		    else {
		        UtilityCustomFunctions.fSoftAssert(sActUploadFile, sFilePath, "Summary File Upload :  " + sMessage, node);
		    }
		    System.out.println("Actual Summary sActUploadFile: " + sActUploadFile);
		    String sExpName = sNamePrefix + " " + sName;
		    String sActName = objDVP.getArraySummary(14).trim();
		    String sNameArray[] = sActName.split("\\s+");
		    sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
		    UtilityCustomFunctions.logWriteConsole("ActualName:"+sActName+"ExpectedName:"+sExpName);
		    UtilityCustomFunctions.fSoftAssert(sActName, sExpName, "Summary Name:  " + sMessage, node);
		    String sActNumber = objDVP.getArraySummary(15).trim();
		    UtilityCustomFunctions.fSoftAssert(sActNumber, sNumber, "Summary Number:  " + sMessage, node);
		    
		    String sActCurrency = objDVP.getArraySummary(16).trim();
		    UtilityCustomFunctions.fSoftAssert(sActCurrency, sCurrency, "Summary Currency:  " + sMessage, node);
		    
		    String sActUrl = objDVP.getSummaryUrl().trim();
		    UtilityCustomFunctions.fSoftAssert(sActUrl, sUrl, "Summary Url:  " + sMessage, node);
		    
		    String sActEnq_Name=objDVP.getArraySummary(18).trim();
		    String sEnqNameArray[] = sActEnq_Name.split("\\s+");
		    sActEnq_Name = sEnqNameArray[0].trim() + " " + sEnqNameArray[1].trim();

		    System.out.println("Actual Summary Eqnuiry Name: " + sActEnq_Name);
		    
		    sEnquiry_Name = sEnqNamePrefix + " " + sEnquiry_Name;
		    
		    UtilityCustomFunctions.fSoftAssert(sActEnq_Name, sEnquiry_Name, "Summary Enquiry Name:  " + sMessage, node);
		    
		    String sActEnq_Email=objDVP.getArraySummary(19).trim();
		    UtilityCustomFunctions.fSoftAssert(sActEnq_Email, sEnquiry_Email, "Summary Enquiry Email:  " + sMessage, node);
		    
		    String sActEnq_Text=objDVP.getArraySummary(20).trim();
		    sActEnq_Text = UtilityCustomFunctions.fArrayConcat(sActEnq_Text);
		    String sExpEnquiry_Text="";
		    if(IsTarget==true) {
		        sExpEnquiry_Text = sText + " " + sEnquiry_Text;	
		    }
		    sExpEnquiry_Text = UtilityCustomFunctions.fArrayConcat(sExpEnquiry_Text);
		    UtilityCustomFunctions.fSoftAssert(sActEnq_Text, sEnquiry_Text, "Summary Enquiry Text:  " + sMessage, node);
		    
		    String sActEnq_TextArea=objDVP.getArraySummary(21).trim();
		    sActEnq_TextArea = UtilityCustomFunctions.fArrayConcat(sActEnq_TextArea);
		    String sExpEnquiryTextArea_Text="";
		    if(IsTarget==true) {
		        sExpEnquiryTextArea_Text = sText + " " + sEnquiry_Text + " " + sEnquiry_TextArea;	
		    }
		    sExpEnquiryTextArea_Text = UtilityCustomFunctions.fArrayConcat(sExpEnquiryTextArea_Text);
		    sEnquiry_TextArea = UtilityCustomFunctions.fArrayConcat(sEnquiry_TextArea);
		    UtilityCustomFunctions.fSoftAssert(sActEnq_TextArea, sEnquiry_TextArea, "Summary Enquiry Text Area:  " + sMessage, node);
		    
		    UtilityCustomFunctions.logWriteConsole("Expected enq Date:" + sEnquiry_Date);
		    String sActEnq_Date=objDVP.getArraySummary(22).trim();
//					Date date4;
//					String fromDateFormat4;
//					if(IsDate==false) {
//						fromDateFormat4 = "yyyy-MM-dd";
//						SimpleDateFormat sdfSource4 = new SimpleDateFormat("yyyy-MM-dd");
//						date4 = sdfSource4.parse(sEnquiry_Date);
//					}
//					else {
//						fromDateFormat4 = "yyyy/MM/dd";
//						SimpleDateFormat sdfSource4 = new SimpleDateFormat("yyyy/MM/dd");
//						date4 = sdfSource4.parse(sEnquiry_Date);
//					}
//					
//					SimpleDateFormat sdfDestination4 = new SimpleDateFormat("yyyy-MM-dd");
//					sEnquiry_Date= sdfDestination4.format(date4);
		    UtilityCustomFunctions.logWriteConsole("Formatted Enquiry Date from sheet:" + sEnquiry_Date);
		        
		    UtilityCustomFunctions.fSoftAssert(sActEnq_Date, sEnquiry_Date, "Summary Enquiry Date:  " + sMessage, node);
		    
		    
		    sEnquiry_PhoneNumber = sEnquiry_PN_Prefix + " " + sEnquiry_PhoneNumber ; 
		    
		    String sActEnq_PhoneNumber=objDVP.getArraySummary(23).trim();
		    System.out.println("Actual Enquiry Phone Number: " + sActEnq_PhoneNumber);
		    
		    
		    String sEnqPNArray[] = sActEnq_PhoneNumber.split("\\s+");
		    sActEnq_PhoneNumber = sEnqPNArray[0].trim() + " " + sEnqPNArray[1].trim();
		    System.out.println("Actual: " + sActEnq_PhoneNumber);
		    
		    
		    UtilityCustomFunctions.fSoftAssert(sActEnq_PhoneNumber, sEnquiry_PhoneNumber, "Summary Enquiry Phone Number:  " + sMessage, node);
		    
		    String sActEnq_Category=objDVP.getArraySummary(24).trim();
		    UtilityCustomFunctions.fSoftAssert(sActEnq_Category, sEnquiry_category, "Summary Enquiry Category:  " + sMessage, node);
		    

		    //Leads Validation.
		    
		    String sLead_PhoneNumber = sLead_PN_Prefix + " " + sLead_PN ;
		    
		    String sActLead_PhoneNumber=objDVP.getArraySummary(25).trim();
		    System.out.println("Actual Lead Summary Phone Number: " + sActLead_PhoneNumber);
		    
		    
		    String sActLeadPNArray[] = sActLead_PhoneNumber.split("\\s+");
		    sActLead_PhoneNumber = sActLeadPNArray[0].trim() + " " + sActLeadPNArray[1].trim();
		    System.out.println("Actual: " + sActLead_PhoneNumber);
		    
		    UtilityCustomFunctions.fSoftAssert(sActLead_PhoneNumber, sLead_PhoneNumber, "Summary Lead PhoneNumber:  " + sMessage, node);
		    //Lead Email
		    String sActLead_Email=objDVP.getArraySummary(26).trim();
		    UtilityCustomFunctions.fSoftAssert(sActLead_Email, sLead_Email, "Summary Lead Email:  " + sMessage, node);
		    
		    //Lead Text
		    String sActLead_Text=objDVP.getArraySummary(27).trim();
		    sActLead_Text = UtilityCustomFunctions.fArrayConcat(sActLead_Text);
		    String sExpLead_Text = "";
		    if(IsTarget==true) {
		        sExpLead_Text = sText + " " + sEnquiry_Text +" " + sEnquiry_TextArea + " " + sLead_Text;	
		    }
		    sExpLead_Text = UtilityCustomFunctions.fArrayConcat(sExpLead_Text);
		    UtilityCustomFunctions.fSoftAssert(sActLead_Text, sLead_Text, "Summary Lead Text:  " + sMessage, node);
		    //Sales Validation
		    String sSales_PhoneNumber = sSales_PN_Prefix + " " + sSales_PN ;
		    
		    String sActSales_PhoneNumber=objDVP.getArraySummary(28).trim();
		    System.out.println("Actual Sales Summary Phone Number: " + sActSales_PhoneNumber);
		    
		    
		    String sActSalesPNArray[] = sActSales_PhoneNumber.split("\\s+");
		    sActSales_PhoneNumber = sActSalesPNArray[0].trim() + " " + sActSalesPNArray[1].trim();
		    System.out.println("Actual: " + sActSales_PhoneNumber);
		    
		    UtilityCustomFunctions.fSoftAssert(sActSales_PhoneNumber, sSales_PhoneNumber, "Summary Sales PhoneNumber:  " + sMessage, node);
		    
		    
		    String sActSales_Email=objDVP.getArraySummary(29).trim();
		    UtilityCustomFunctions.fSoftAssert(sActSales_Email, sSales_Email, "Summary Sales Email:  " + sMessage, node);

		    //Details View
		    objDVP.fSetDetailVew(true);
		    Thread.sleep(3000);
		    driver.navigate().refresh();
		    Thread.sleep(3000);
		    String sActDTAssignedTo = objDVP.getArrayDetails(4).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sExpAssignedTo, "Detail View AssignedTo:  " + sMessage, node);
		    
		    String sActDTText= objDVP.getArrayDetails(6).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTText, sText, "Detail View Text:  " + sMessage, node);
		    
		    String sActDTMobileNumber= objDVP.getArrayDetails(7).trim();
		    UtilityCustomFunctions.logWriteConsole("sActDTMobileNumber "+ sActDTMobileNumber);
		    
		    String sDTMobileNMArray[] = sActDTMobileNumber.split("\\s+");
		    if(sDTMobileNMArray.length>=1) {
		     sActDTMobileNumber = sDTMobileNMArray[0].trim() + " " + sDTMobileNMArray[1].trim();
		    }
		    System.out.println("actual mobile number: " + sActDTMobileNumber);
		    UtilityCustomFunctions.fSoftAssert(sActDTMobileNumber, sExpMobileNumber, "Detail View Mobile Number:  " + sMessage, node);
		    
		    String sActDTEmail= objDVP.getArrayDetails(8).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEmail, sEmail, "Detail View Email :  " + sMessage, node);
		    
		    String sActDTPicList= objDVP.getArrayDetails(9).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTPicList, sPickListValue, "Detail View PicListValue :  " + sMessage, node);
		    
		    String sActDTMultiCombo= objDVP.getArrayDetails(10).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTMultiCombo, sMultiComboValues, "Detail View MuultiCombo Values :  " + sMessage, node);
		    
		    String sActDTCity= objDVP.getArrayDetails(11).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTCity, sCity, "Detail View City:  " + sMessage, node);
		    
		    String sActDTState= objDVP.getArrayDetails(12).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTState, sState, "Detail View State:  " + sMessage, node);
		    
		    String sActDTCountry= objDVP.getArrayDetails(13).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTCountry, sCountry, "Detail View Country:  " + sMessage, node);
		    
		    String sActDTChekBox= objDVP.getArrayDetails(14).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTChekBox, sCheckBox, "Detail View CheckBox:  " + sMessage, node);
		    
		    String sActDTDate= objDVP.getArrayDetails(15).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTDate, sDate, "Detail View Date:  " + sMessage, node);
		    
		    String sActDTTime= objDVP.getArrayDetails(16).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTTime, sTime, "Detail View Time:  " + sMessage, node);
		    
		    String sActDTDateandTime= objDVP.getArrayDetails(17).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTDateandTime, sDateandTime, "Detail View Date & Time :  " + sMessage, node);
		    
//					String sActDTRelModule= objDVP.getArrayDetails(18).trim();
//					UtilityCustomFunctions.fSoftAssert(sActDTRelModule, sRelatedModule, "Detail View Related Module :  " + sMessage, node);
		    
		    String sActDTUploadFile = "";
		    if(objDVP.getUploadFileText()!=null) {
		        sActDTUploadFile= objDVP.getUploadFileText();
		    }
		    if(sActDTUploadFile.contains(sFilePath)) {
		        UtilityCustomFunctions.fSoftAssert(sFilePath, sFilePath, "Detail View File Upload :  " + sMessage, node);
		    }
		    else {
		        UtilityCustomFunctions.fSoftAssert(sActDTUploadFile, sFilePath, "Detail View Upload :  " + sMessage, node);
		    }
		    sExpName = sNamePrefix + " " + sName;
		    
		    sActName = sNameArray[0].trim() + " " + sNameArray[1].trim();
		    
		    String sActDTName= objDVP.getArrayDetails(20).trim();
		    String sDTNameArray[] = sActDTName.split("\\s+");
		    sActDTName = sDTNameArray[0].trim() + " " + sDTNameArray[1].trim();
		    UtilityCustomFunctions.logWriteConsole("ActualNameDT:"+sActDTName+"ExpectedNameDT:"+sExpName);
		    UtilityCustomFunctions.fSoftAssert(sActDTName, sExpName, "Detail View Name:  " + sMessage, node);
		    
		    String sActDTNumber= objDVP.getArrayDetails(21).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTNumber, sNumber, "Detail View Number:  " + sMessage, node);
		    
		    String sActDTCurrency= objDVP.getArrayDetails(22).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTCurrency, sCurrency, "Detail View Currency:  " + sMessage, node);
		    
		    String sActDTUrl= objDVP.getDTSummaryUrl().trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTUrl, sUrl, "Detail View Url:  " + sMessage, node);
		    Thread.sleep(3000);
		    objDVP.fClickDetailBlockB();
		    Thread.sleep(3000);
		    
		    String sActDTEnqName = objDVP.getArrayDetails(24).trim();
		    
		    String sDTEnqNameArray[] = sActDTEnqName.split("\\s+");
		    sActDTEnqName = sDTEnqNameArray[0].trim() + " " + sDTEnqNameArray[1].trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqName, sEnquiry_Name, "Detail View Enquiry Name:  " + sMessage, node);
		    
		    String sActDTEnqEmail= objDVP.getArrayDetails(25).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqEmail, sEnquiry_Email, "Detail View Enquiry Email:  " + sMessage, node);
		    
		    String sActDTEnqText= objDVP.getArrayDetails(26).trim();
		    sActDTEnqText = UtilityCustomFunctions.fArrayConcat(sActDTEnqText);
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqText, sEnquiry_Text, "Detail View Enquiry Text:  " + sMessage, node);
		    
		    String sActDTEnqTextArea= objDVP.getArrayDetails(27).trim();
		    sActDTEnqTextArea = UtilityCustomFunctions.fArrayConcat(sActDTEnqTextArea);
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqTextArea, sEnquiry_TextArea, "Detail View Enquiry Text Area:  " + sMessage, node);
		    
		    String sActDTEnqDate= objDVP.getArrayDetails(28).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqDate, sEnquiry_Date, "Detail View Enquiry Date:  " + sMessage, node);
		    
		    String sActDTEnqPN= objDVP.getArrayDetails(29).trim();
		    String sEnqActDTArray[] = sActDTEnqPN.split("\\s+");
		    sActDTEnqPN = sEnqActDTArray[0].trim() + " " + sEnqActDTArray[1].trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqPN, sEnquiry_PhoneNumber, "Detail View Enquiry Phone Number:  " + sMessage, node);
		    
		    String sActDTEnqCategory= objDVP.getArrayDetails(30).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTEnqCategory, sEnquiry_category, "Detail View Enquiry Category:  " + sMessage, node);
		    
		    Thread.sleep(3000);
		    objDVP.fClickDetailBlockC();
		    Thread.sleep(3000);
		    
		    String sActLeadDTMobileNumber= objDVP.getArrayDetails(31).trim();
		    String sActLeadDTMobileNMArray[] = sActLeadDTMobileNumber.split("\\s+");
		    sActLeadDTMobileNumber = sActLeadDTMobileNMArray[0].trim() + " " + sActLeadDTMobileNMArray[1].trim();
		    System.out.println("actual mobile number: " + sActLeadDTMobileNumber);
		    
		    String sDTLead_PN = sLead_PN_Prefix + " " + sLead_PN;
		    UtilityCustomFunctions.fSoftAssert(sActLeadDTMobileNumber, sDTLead_PN, "DT Lead Mobile Number:  " + sMessage, node);
		    //Created Time added which is not captured.
		    
		    String sActDTLeadEmail= objDVP.getArrayDetails(32).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTLeadEmail, sLead_Email, "DT View Lead Email  " + sMessage, node);
		    
		    String sActDTLeadText= objDVP.getArrayDetails(33).trim();
		    sActDTLeadText = UtilityCustomFunctions.fArrayConcat(sActDTLeadText);
		    sExpLead_Text = "";
		    if(IsTarget==true) {
		        sExpLead_Text = sText + " " + sEnquiry_Text +" " + sEnquiry_TextArea + " " + sLead_Text;	
		    }
		    sExpLead_Text = UtilityCustomFunctions.fArrayConcat(sExpLead_Text);
		    UtilityCustomFunctions.fSoftAssert(sActDTLeadText, sLead_Text, "DT View Lead Text " + sMessage, node);
		    
		    //Sales
		    String sActSalesDTMobileNumber= objDVP.getArrayDetails(34).trim();
		    String sActSalesDTMobileNMArray[] = sActSalesDTMobileNumber.split("\\s+");
		    sActSalesDTMobileNumber = sActSalesDTMobileNMArray[0].trim() + " " + sActSalesDTMobileNMArray[1].trim();
		    
		    System.out.println("actual mobile number: " + sActSalesDTMobileNumber );
		    
		    String sDTSales_PN = sSales_PN_Prefix + " " + sSales_PN;
		    UtilityCustomFunctions.fSoftAssert(sActSalesDTMobileNumber, sDTSales_PN, "DT Sales Mobile Number:  " + sMessage, node);
		    
		    String sActDTSalesEmail= objDVP.getArrayDetails(35).trim();
		    UtilityCustomFunctions.fSoftAssert(sActDTSalesEmail, sSales_Email, "DT View Sales Email" + sMessage, node);
		    
		    if(isNotify.equalsIgnoreCase("Yes")){
		        driver.close();
		    }
		    
		    System.out.println("Inside Validate All fields: isNotify" + isNotify);
		}
	//get RecordId of the Module
	public int getLastRecordId() throws MalformedURLException, InterruptedException{
		
		
		Thread.sleep(3000);
		int sRecordId=0;
		URL sCurrentUrl = new URL(driver.getCurrentUrl());
		String sUrlQuery = sCurrentUrl.getQuery();
		String sQryArray[] =sUrlQuery.split("=");
		for(int i = 0;i<sQryArray.length;i++) {
				System.out.println("Split - " + i + "Value is: " + sQryArray[i]);
				if(i==3) {
					String sArrayRecId[] = sQryArray[i].split("&");
					System.out.println("Record Id is:" + sArrayRecId[0]);
					sRecordId = Integer.parseInt(sArrayRecId[0]);
					break;
				}
			}
		return sRecordId;
	}
	
	//
	public void fAddMandatoryValuestoEntityModule(String sEnv,String sExcelName,String sSheetName) throws Exception {
	CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
	String sPath="";
	if(sEnv.equalsIgnoreCase("Test")){
		sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
	}
	else {
		sPath=".\\testData\\" + sExcelName +"Live.xlsx";
	}
	ExcelUtility xlAddObj = new ExcelUtility(sPath);

	logger.info("Excel file Utility instance created");

	int iRowCount = xlAddObj.getRowCount(sSheetName);
	System.out.println("Total rows: " + iRowCount);
	logger.info("Row Count is: " + iRowCount);
	

	int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);
	System.out.println("Cols: " + iColCount);
	logger.info("Col Count is: " + iColCount);
				
	logger.info("Extracting DataSheet Values started...");
	String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
	String sExpTrgModuleName = xlAddObj.getCellData(sSheetName, 1, 1);
	String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 2);
	String sExpAssignedTo = xlAddObj.getCellData(sSheetName, 1, 3);
	String sText=xlAddObj.getCellData(sSheetName, 1, 4);
	
//	sRecordId="";
	System.out.println("Module Name:  " + sExpModuleName);
	UtilityCustomFunctions.logWriteConsole("Before Adding Module data");
	Thread.sleep(3000);
	objCMD.clickArrayDropDown(1);
//	objCMD.clickAssignedTo();
	UtilityCustomFunctions.logWriteConsole("Assinged To Drop Down clicked");
	Thread.sleep(2000);
	objCMD.selectListValue(sExpAssignedTo);
	UtilityCustomFunctions.logWriteConsole("Assinged To Value Seleted is::  "+sExpAssignedTo);
	objCMD.setInputValue(sExpModuleName, "text",sText);
	UtilityCustomFunctions.logWriteConsole("Text Value added is::  "+sText);
	
	Thread.sleep(5000);		
	
	objCMD.clickSave();
	
	Thread.sleep(5000);
}
	public String getTodayMonth() {
		Calendar cal = Calendar.getInstance();
		return (new SimpleDateFormat("MMM").format(cal.getTime()));
	}
	
	public int getCurrentYear() {
		LocalDate currentdate = LocalDate.now();
		return currentdate.getYear();
		
	}
	
	//***************************
	public void fAddValuestoETBySpecificValues(String sEnv,String sExcelName,String sSheetName,boolean isAmend,String sAddType) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\"+ sExcelName +"Test.xlsx";
		}
		else {
			sPath=".\\testData\\" + sExcelName +"Live.xlsx";
		}
		ExcelUtility xlAddObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlAddObj.getRowCount(sSheetName);
		System.out.println("Total rows: " + iRowCount);
		UtilityCustomFunctions.logWriteConsole("Row Count to Add Values: " + iRowCount);

		int iColCount = xlAddObj.getCellCount(sSheetName, iRowCount);

		UtilityCustomFunctions.logWriteConsole("Col Count is: " + iColCount);	
		UtilityCustomFunctions.logWriteConsole("Extracting DataSheet Values started...");		

		String sExpModuleName = xlAddObj.getCellData(sSheetName, 1, 0);
		String sExpWorkFlowName = xlAddObj.getCellData(sSheetName, 1, 1);
		String sExpAssignedTo = xlAddObj.getCellData(sSheetName, 1, 2);
		String sMobilePrefix = xlAddObj.getCellData(sSheetName, 1, 3);
		String sMobileNumber = xlAddObj.getCellData(sSheetName, 1, 4);

		String sEmail=xlAddObj.getCellData(sSheetName, 1, 5);
		String sText=xlAddObj.getCellData(sSheetName, 1, 6);
		String sMultiComboValues=xlAddObj.getCellData(sSheetName, 1, 7);
		String sTextArea=xlAddObj.getCellData(sSheetName, 1, 8);
		String sCheckBox=xlAddObj.getCellData(sSheetName, 1, 9);
		String sDate=xlAddObj.getCellData(sSheetName, 1, 10);
		String sPickList=xlAddObj.getCellData(sSheetName, 1, 11);
		String sFile=xlAddObj.getCellData(sSheetName, 1, 12);
		String sDateandTime =xlAddObj.getCellData(sSheetName, 1, 13);
		String sTime =xlAddObj.getCellData(sSheetName, 1, 14);
		String sNamePrefix =xlAddObj.getCellData(sSheetName, 1, 15);
		String sName =xlAddObj.getCellData(sSheetName, 1, 16);
		String sNumber =xlAddObj.getCellData(sSheetName, 1, 17);
		String sCurrency=xlAddObj.getCellData(sSheetName, 1, 18);
		String sURL=xlAddObj.getCellData(sSheetName, 1, 19);
		String sCity=xlAddObj.getCellData(sSheetName, 1, 20);
		String sState=xlAddObj.getCellData(sSheetName, 1, 21);
		String sCountry=xlAddObj.getCellData(sSheetName, 1, 22);
		String sRelatedModText =xlAddObj.getCellData(sSheetName, 1, 23);
		String sTimeHour = xlAddObj.getCellData(sSheetName, 1, 33);
		String sTimeMinutes = xlAddObj.getCellData(sSheetName, 1, 34);
		String sTimeSeconds = xlAddObj.getCellData(sSheetName, 1, 34);
		System.out.println("Module Name:  " + sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Before Adding Module data");
		Thread.sleep(1000);
		//Assigned To
		objCMD.clickArrayDropDown(1);
//		objCMD.clickAssignedTo();
		UtilityCustomFunctions.logWriteConsole("Assinged To Drop Down clicked");
		Thread.sleep(2000);
		objCMD.selectListValue(sExpAssignedTo);
		//Phone Number
		objCMD.ClickListPhonePrefix(sExpModuleName,"mobilenumber_prefix-container");
		UtilityCustomFunctions.logWriteConsole("Phone NUmber prefix clicked :  ");
		objCMD.selectListValue(sMobilePrefix);
		UtilityCustomFunctions.logWriteConsole("Phone NUmber selected is :  "+sMobileNumber);
		objCMD.setMobileNumber(sMobileNumber);
		Thread.sleep(1000);
		//Email
		Thread.sleep(1000);
		objCMD.setEmailValue(sExpModuleName, sEmail);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Email Added is: "+sEmail);
		//Text
		objCMD.setInputValue(sExpModuleName, "text",sText);
		UtilityCustomFunctions.logWriteConsole("Text Value added is::  "+sText);
		Thread.sleep(1000);
		//MultiCombo Values
		Thread.sleep(1000);
		objCMD.clickMultiComboBox(sMultiComboValues,isAmend);
		UtilityCustomFunctions.logWriteConsole("MultiCombo box values: " + sMultiComboValues);
		Thread.sleep(1000);
		//Text Area Value
		objCMD.setTextAreaValue(sExpModuleName, "textarea", sTextArea);
		//CheckBox
		Thread.sleep(1000);
		objCMD.clickArrayCheckBox(1, sCheckBox);
		UtilityCustomFunctions.logWriteConsole("Checkbox clicked");
		//Date
		objCMD.clickDateBox(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("DateBox clicked");
		Thread.sleep(1000);
		if(sAddType.equalsIgnoreCase("sDate")) {
			boolean bFound = false;
			
			
			Date currentDate = new Date();
		    int iCurrDate = currentDate.getDate();
		    int jCounter=iCurrDate+3;
		    
			Date dMonthDate = new Date();
			@SuppressWarnings("deprecation")
			int iMonthIndex = dMonthDate.getMonth(); 
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.MONTH, iMonthIndex);
			int iMonthLastDay = calendar1.getActualMaximum(calendar1.DAY_OF_MONTH); 
			System.out.println("Last day of current month:" + iMonthLastDay);
			System.out.println("j counter:" + jCounter);
			int iApprValue = 0;
			if(jCounter >iMonthLastDay) {
				iApprValue = jCounter - iMonthLastDay;
				String sNextMonth = getNextMonth();
				objCMD.SelectMonthandYear("sDate",sNextMonth,"2024");
				jCounter = iApprValue;
			}
			System.out.println("Jcounter value now is : " + jCounter);
			List<WebElement> sTableDatas = driver.findElements(By.xpath("(//table[contains(@class,'table-condensed')])[1]//tr//td[contains(@class,'today') or contains(@class,'active') or @class='available' or @class='weekend available']"));
			for(int i=1;i<=sTableDatas.size();i++) {
					String sDay = sTableDatas.get(i).getText();
					System.out.println("Control day: " + sDay);
					if(jCounter==Integer.parseInt(sDay)) {
						sTableDatas.get(i).click();
						bFound = true;
						break;
					}
				}//for loop
		}else {
			objCMD.clickDayInDate(1,"sDate",null);
			UtilityCustomFunctions.logWriteConsole("Today Date Selected");
			Thread.sleep(1000);
		}
		//PickList
		objCMD.clickArrayDropDown(3);
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Picklist clicked");
		Thread.sleep(1000);
		objCMD.selectListValue2(sPickList);
		UtilityCustomFunctions.logWriteConsole("Picklist selected: "+sPickList);
		//File Upload
		objCMD.setUploadFile();
		//Date & Time
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "datetime");
		UtilityCustomFunctions.logWriteConsole("Date & Time Clicked");
		Thread.sleep(5000);
		//For Modifying record Index 2 value passed.
		System.out.println("Before Calling ClickDayInDate: " + sTimeHour);
		if(sAddType.equalsIgnoreCase("sDateandTime")) {
			boolean bFound = false;
			Date currentDate = new Date();
		    int iCurrDate = currentDate.getDate();
		    int jCounter=iCurrDate+3;
		    
			Date dMonthDate = new Date();
			@SuppressWarnings("deprecation")
			int iMonthIndex = dMonthDate.getMonth(); 
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.MONTH, iMonthIndex);
			int iMonthLastDay = calendar1.getActualMaximum(calendar1.DAY_OF_MONTH); 
			System.out.println("Last day of current month:" + iMonthLastDay);
			System.out.println("j counter:" + jCounter);
			int iApprValue = 0;
			if(jCounter >iMonthLastDay) {
				iApprValue = jCounter - iMonthLastDay;
				String sNextMonth = getNextMonth();
				objCMD.SelectMonthandYear("sDateandTime",sNextMonth,"2024");
				jCounter = iApprValue;
			}
			else {
				String sCurrMonth = getTodayMonth();
				UtilityCustomFunctions.logWriteConsole("Current Month:" + sCurrMonth);
//				Thread.sleep(1000);
//				UtilityCustomFunctions.selectFromComboBox(driver, eleMonth, sCurrMonth);
				Thread.sleep(1000);
//				WebElement eleYear= driver.findElement(By.xpath(sYXpath));
//				eleYear.click();
				Thread.sleep(2000);
				String sYear =  Integer.toString(getCurrentYear());
				Thread.sleep(1000);
				objCMD.SelectMonthandYear("sDateandTime",sCurrMonth,sYear);
			}
			System.out.println("Jcounter value now is : " + jCounter);
			List<WebElement> sTableDatas = driver.findElements(By.xpath("(//table[contains(@class,'table-condensed')])[3]//tr//td[contains(@class,'today') or contains(@class,'active') or @class='available' or @class='weekend available']"));
			for(int i=1;i<=sTableDatas.size();i++) {
					String sDay = sTableDatas.get(i).getText();
					System.out.println("Control day: " + sDay);
					if(jCounter==Integer.parseInt(sDay)) {
						sTableDatas.get(i).click();
						bFound = true;
						break;
					}
			}//for loop
			Thread.sleep(1000);
			objCMD.fSelectDTHour(sTimeHour);
			Thread.sleep(1000);
			objCMD.fSelectDTMinutes(sTimeMinutes);
			Thread.sleep(1000);
			objCMD.fSelectDTSeconds(sTimeSeconds);
			Thread.sleep(1000);
			objCMD.fSelectDTAMorPM("PM");
		}else {
			objCMD.clickDayInDate(2,"sDateandTime",sTimeHour);
		}
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Clicked Current Date in Date&Time");
		objCMD.clickDandTApply();
		UtilityCustomFunctions.logWriteConsole("Date & Time Selected");
		//Time
		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		UtilityCustomFunctions.logWriteConsole("Time Clicked");
		
		WebElement eleAMPM = driver.findElement(By.xpath("//button[normalize-space()='PM']"));
		eleAMPM.click();

		Thread.sleep(1000);
		objCMD.clickDateBox(sExpModuleName, "time");
		
		objCMD.clickTime(sTimeHour, sTimeMinutes,sTimeSeconds);
		Thread.sleep(1000);
		
		//Write Date, Time & Date&Time to Datasheet.
		String sActDate = objCMD.fGetModuleValue(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("Actual Date Selected :" + sActDate);
        xlAddObj.setCellData(sSheetName, 1, 10, sActDate);
        
        String sActDateandTime= objCMD.fGetModuleValue(sExpModuleName, "datetime");
		System.out.println("Date & Time" + sActDateandTime);
		xlAddObj.setCellData(sSheetName, 1, 13, sActDateandTime);
		
        String sActTime= objCMD.fGetModuleValue(sExpModuleName, "time");
		System.out.println("Time" + sActTime);
		xlAddObj.setCellData(sSheetName, 1, 14, sActTime);
		
		//Enter Name
		objCMD.clickArrayDropDown(4);
		objCMD.selectListValue(sNamePrefix);
		objCMD.setInputValue(sExpModuleName, "name", sName);
		
		//Enter Number
		objCMD.setInputNumber(sExpModuleName, "number", sNumber);
		
		//Enter Currency
		objCMD.setInputValue(sExpModuleName, "currency", sCurrency);
		
		//Enter URL
		objCMD.setGenericInputValue("url", sExpModuleName, "url", sURL);
		
		//City 
		Thread.sleep(1000);
		objCMD.clickArrayDropDown(5);
		UtilityCustomFunctions.logWriteConsole("City Drop Down clicked");
		objCMD.selectListValue(sCity);
		UtilityCustomFunctions.logWriteConsole("City Selected: " + sCity);
		Thread.sleep(1000);
		//State
		objCMD.clickArrayDropDown(6);
		UtilityCustomFunctions.logWriteConsole("State Drop Down clicked");
		objCMD.selectListValue(sState);
		UtilityCustomFunctions.logWriteConsole("State Selected: "+sState);
		Thread.sleep(1000);
		//Country
		objCMD.clickArrayDropDown(7);
		UtilityCustomFunctions.logWriteConsole("Country List clicked");
		objCMD.selectListValue(sCountry);
		UtilityCustomFunctions.logWriteConsole("Country Selected: " + sCountry);
		Thread.sleep(1000);
		
		//Related Module
//		objCMD.SelectRelModValue(sRelatedModText);
		Thread.sleep(1000);		
		objCMD.clickSave();
		Thread.sleep(1000);		
		
	}	
		public void getMOnth() {
			Calendar cal = Calendar.getInstance();
			System.out.println(new SimpleDateFormat("MMM").format(cal.getTime()));
			
		}
		
		public String getNextMonth() {
			Calendar cal = Calendar.getInstance();
			String sCurrMonth = new SimpleDateFormat("MMM").format(cal.getTime());
			String monthNames[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
		            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			String retValue="";
			int k = 0;
			for(int i=0;i<monthNames.length;i++) {
				if(sCurrMonth.equalsIgnoreCase(monthNames[i])) {
					k = i + 1;
					if(i==11) {
						k=0;
					}
					retValue = monthNames[k];
					break;
				}
			}
			return retValue;
			
		}
		public static void fLaunchUrl(WebDriver driver, String sUrl) {
			try {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(sUrl);
				Thread.sleep(3000);
				driver.manage().window().maximize();
			} catch (Exception e) {
				System.out.println(sUrl+" not launched");
			}
		}
		public void fConfigureWebForm(String sUsers,String sModuleName,String sWebFormName) throws Exception {
			WebFormsPage objWFP = new WebFormsPage(driver);
			objCRMSTngs.fCRMNavigate("Integration", "Web Forms");
			objWFP.fNavigateWFUserConfigPage(sModuleName,sWebFormName);
			objWFP.fSetRoundRobinUsers(true, sUsers);

			
		}
		public void fConfigureConditionRR(String sUsers,String sModuleName,String sWebFormName) {
			WebFormsPage objWFP = new WebFormsPage(driver);
			
			
			
			
		}
	}