package utilities;

import java.util.Set;

import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import pageObjects.DetailViewPage;
import pageObjects.NotificationsPage;
import utilities.UtilityCustomFunctions;

public class CRMReUsables extends BaseClass {

	public void CrmLogin() {

	}
	public String fOpenNotification(String sCurrWinHandle) throws Exception {
		String sActualWindow="";
		NotificationsPage objNotfy = new NotificationsPage(driver);
		objNotfy.clickNotificatons();
		Thread.sleep(5000);
		objNotfy.clickNotifyFirstMsg();
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

}
