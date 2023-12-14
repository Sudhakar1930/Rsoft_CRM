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
		String sActualWindow="";
		NotificationsPage objNotfy = new NotificationsPage(driver);
		objNotfy.clickNotificatons();
		Thread.sleep(5000);
		objNotfy.clickOpnNotifyPage();
//		objNotfy.clickNotifyFirstMsg();
		Thread.sleep(5000);
		Set<String> Handles = driver.getWindowHandles();
		for(String actual:Handles) {
			System.out.println("Current Window Handle: " + sCurrWinHandle);
			System.out.println("Window Handle: " + actual);
			if(!actual.equalsIgnoreCase(sCurrWinHandle)) {
				driver.switchTo().window(actual);
				sActualWindow = actual;
				break;
			}
		}
		return sActualWindow;
		
	}
	
	public void fValModuleView(String sExpModuleName, String sAssignedTo, String sPhoneNoumber, String sNumberField, String sEmail, String sPickListItem, String sEnterYourNumber,ExtentTest node) throws Exception {
		DetailViewPage objDVP = new DetailViewPage(driver);
		String aActModuleName = objDVP.getNavBarModuleName();
		System.out.println("Actual Module Name: " + aActModuleName);
		System.out.println("Expected Module Name: " + sExpModuleName);
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName,"Module Name" , node);
		//Title Actual Values
		String sActPNTitle= objDVP.getPhoneNMTitle();
		String sPNTitleArray[]= sActPNTitle.split("\\s+");
		sActPNTitle = sPNTitleArray[0].trim() + " " + sPNTitleArray[1].trim(); 
		System.out.println("Actual: " + sActPNTitle);
		String sActEMTitle=objDVP.getEmailTitle();
		String sActMnuTitle=objDVP.getMenuItemTitle();
		
		//Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName, sExpModuleName,"Module Name" , node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle,sPhoneNoumber,"Phone Number",node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle,sEmail,"Email",node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle,sPickListItem,"Piclist Value",node);
		
		objDVP.clickSummaryView();
		Thread.sleep(3000);
		//Summary Actual Values
		String sActPNSummary=objDVP.getPhoneNMSummary();
		System.out.println("Actual raw: " + sActPNSummary.trim());
		sActPNSummary = sActPNSummary.trim();
		String sPNArray[]= sActPNSummary.split("\\s+");
		System.out.println("Array size: " + sPNArray.length);
		sActPNSummary = sPNArray[0].trim() + " " + sPNArray[1].trim(); 
		System.out.println("Actual: Summary" + sActPNSummary);
		String sActNFSummary=objDVP.getNumberFieldSummary();
		String sActEMSummary=objDVP.getEmailSummary();
		String sActMnuSummary=objDVP.getMenuItemSummary();
		String sActEYNSummary=objDVP.getEYNumberSummary();
		
		//Summary Values Validation
		UtilityCustomFunctions.fSoftAssert(sActPNSummary, sPhoneNoumber,"Summary Phone Number" , node);
		UtilityCustomFunctions.fSoftAssert(sActNFSummary, sNumberField,"Summary Number Field" , node);
		UtilityCustomFunctions.fSoftAssert(sActEMSummary, sEmail,"Summary Email" , node);
		UtilityCustomFunctions.fSoftAssert(sActMnuSummary, sPickListItem,"Summary Picklist Item" , node);
		UtilityCustomFunctions.fSoftAssert(sActEYNSummary, sEnterYourNumber,"Summary Enter Your Number" , node);
				
		//Click Detail View
		objDVP.clickDetailView();
		Thread.sleep(3000);
		//Detail View Actual Values
		String sActAsgnToDTView = objDVP.getAssignedToDTView();
		
		String sActPNDTView = objDVP.getPhoneNMDTView();
		
		String sPNArrayDT[]= sActPNDTView.split("\\s+");
		sActPNDTView = sPNArrayDT[0].trim() + " " + sPNArrayDT[1].trim();
		System.out.println("Actual: DTView : " + sActPNDTView);
		String sActEMDTView = objDVP.getEmailDTView();
		String sActMnuDTView = objDVP.getMenuListDTView();
		String sActEYVDT = objDVP.getEYVDTView();
		
		//Detail View Validation
		System.out.println("Actual Assigned To:" + sActAsgnToDTView);
		System.out.println("Expected Assigned To:" + sAssignedTo);
		UtilityCustomFunctions.fSoftAssert(sActAsgnToDTView, sAssignedTo,"Detail View Assigned To" , node);
		UtilityCustomFunctions.fSoftAssert(sActPNDTView, sPhoneNoumber,"Detail View Phone Numer" , node);
		UtilityCustomFunctions.fSoftAssert(sActEMDTView ,sEmail,"Detail View Email" , node);
		UtilityCustomFunctions.fSoftAssert(sActMnuDTView,sPickListItem ,"Detail View Menu Item" , node);
		UtilityCustomFunctions.fSoftAssert(sActEYVDT, sEnterYourNumber,"Detail View Enter Your Number" , node);
		 
		//Title & NavBar Validation again
		String aActModuleName1 = objDVP.getNavBarModuleName();
		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName,"Module Name" , node);
		//Title Actual Values
		String sActPNTitle1= objDVP.getPhoneNMTitle();
		String sPNTitleArray1[]= sActPNTitle1.split("\\s+");
		sActPNTitle1 = sPNTitleArray1[0].trim() + " " + sPNTitleArray1[1].trim(); 
		System.out.println("Actual: " + sActPNTitle1);
		
		String sActEMTitle1=objDVP.getEmailTitle();
		String sActMnuTitle1=objDVP.getMenuItemTitle();
		
		//Validations
		UtilityCustomFunctions.fSoftAssert(aActModuleName1, sExpModuleName,"Module Name" , node);
		UtilityCustomFunctions.fSoftAssert(sActPNTitle1,sPhoneNoumber,"Phone Number",node);
		UtilityCustomFunctions.fSoftAssert(sActEMTitle1,sEmail,"Email",node);
		UtilityCustomFunctions.fSoftAssert(sActMnuTitle1,sPickListItem,"Piclist Value",node);
		
		
	}
	
	public String fValNotifySummaryAndDetail(String sAssignedTo,String sUserName,String sActionTitle,ExtentTest node) throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		
		
		String sStatus="0";
		
//		sActionTitle
		//get Details
		String sActDTAssignedTo = objNFP.getDTAssignedTo();
		String sActDTStatus = objNFP.getDTStatus();
		String sActDTSummary= objNFP.getDTSummary();
		String sActDTTitle= objNFP.getDTTitle();
		String sActDTModRecId= objNFP.getDTModRecId();
		//Validations
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo,sAssignedTo ,"Detail View Assigned To" , node);
		UtilityCustomFunctions.fSoftAssert(sActDTStatus,sStatus,"Detail View Status" , node);
		UtilityCustomFunctions.fSoftAssert(sActDTSummary,sUserName,"Detail View Summary/UserName" , node);
		UtilityCustomFunctions.fSoftAssert(sActDTTitle,sActionTitle,"Detail View Action Title" , node);
		objNFP.clickSummaryTab();
		//getActualSummaryDetails
		Thread.sleep(1000);
		String sActSMAssignedTo= objNFP.getSMAssignedTo();
		String sActSMStatus= objNFP.getSMStatus();
		String sActSMSummary= objNFP.getSMSummary();
		String sActSMTitle= objNFP.getSMActionTitle();
		//Validations
		UtilityCustomFunctions.fSoftAssert(sAssignedTo,sActSMAssignedTo,"Summary View Assigned To" , node);
		UtilityCustomFunctions.fSoftAssert(sStatus,sActSMStatus,"Summary View Status" , node);
		UtilityCustomFunctions.fSoftAssert(sActSMSummary,sUserName,"Summary View User" , node);
		UtilityCustomFunctions.fSoftAssert(sActSMTitle, sActionTitle,"Summary View Action Title" , node);
		
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
	public void fgetTablevalues(String sAssignedTo,String sStatus,String sCreatedBy,String sUserName,String sExpTitle,String sRecordId,ExtentTest node) throws Exception {
		NotificationsPage objNFP = new NotificationsPage(driver);
		List<WebElement> tRowCount = driver.findElements(By.xpath("//tbody/tr"));
		for(int i=2;i<=tRowCount.size();i++) {
			System.out.println("Current Row Number: " + i);
			List<WebElement> tColCount =tRowCount.get(i).findElements(By.tagName("td"));
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
			
			UtilityCustomFunctions.fSoftAssert(sAssignedTo,sAssignedTo,"AssignedTo Record Details" , node);
			UtilityCustomFunctions.fSoftAssert(sActStatus,sStatus,"Status in Record Details" , node);
			UtilityCustomFunctions.fSoftAssert(sActCreatedBy,sCreatedBy,"Created By in Record Details" , node);
			UtilityCustomFunctions.fSoftAssert(sActSummary,sUserName,"UserName in Record Details" , node);
			UtilityCustomFunctions.fSoftAssert(sActTitle,sExpTitle,"Workflow Title in Record Details" , node);
			UtilityCustomFunctions.fSoftAssert(sActModRecId,sRecordId,"Record Id in Record Details" , node);
			break;
		}
	}
	
	public void fNavigatetoWorkflow(String sModule) throws Exception {
		HomePage objHP = new HomePage(driver);
		CRMSettingsPage objCRMs = new CRMSettingsPage(driver);
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		//Click Avatar
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
	public String IsCheckWorkflowStatus(String sModule,String sWorkflow, String sExecCondition) {
		String sWFStatusReturn="";
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		sWFStatusReturn = objWFP.fWorkFlowStatus(sModule, sWorkflow, sExecCondition);
		BaseClass.logger.info(sWFStatusReturn);
		return sWFStatusReturn;
	}
	public void fClickWorkFlowAndGotoTask(int iEditPos) throws Exception {
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		objWFP.fNavigateToTask(iEditPos);
		
	}
	public boolean fCheckTaskStatus(String sWorkflow,String sActionType,String sActionTitle) throws Exception {
		boolean bTaskStatus = false;
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		return bTaskStatus = objWFP.fValidateTaskStatus(sWorkflow, sActionType, sActionTitle);
	}

	public void fAddValuestoModulePage(String sEnv) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sPath="";
		if(sEnv.equalsIgnoreCase("Test")){
			sPath=".\\testData\\Notification\\" + "WF2_Only_On_the_first_Save_Test" + ".xlsx" ;
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
		
		System.out.println("Enquiriy prefix: " + sEnquiry_PN_Prefix);
		
		objCMD.ClickListPhonePrefix(sExpModuleName,"enquiryphonenumber_prefix-container");
		Thread.sleep(1000);
		objCMD.selectListValue(sEnquiry_PN_Prefix);
		
		Thread.sleep(1000);
		objCMD.setGenericInputValue("tel", sExpModuleName, "enquiryphonenumber", sEnquiry_PhoneNumber);
		
		objCMD.clickEnqCategory();
		
		objCMD.selectListValue(sEnquiry_category);
		
		Thread.sleep(1000);
		
//		objCMD.clickSave();
		
		
		
	}
	
	
	
}
