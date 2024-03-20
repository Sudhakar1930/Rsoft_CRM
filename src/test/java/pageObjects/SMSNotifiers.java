package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import utilities.UtilityCustomFunctions;

public class SMSNotifiers extends BasePage {

	public SMSNotifiers(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(name="sms_notifiers_receipentnumber")
	WebElement txtSearchReceipientNo;
	
	@FindBy(xpath="//table[@class='table table-striped rowcount']/tbody")
	WebElement WbTblRow;
	
	@FindBy(xpath="//button[normalize-space()='Search']")
	WebElement btnSearch;
	
	@FindBy(xpath="//a[normalize-space()='Summary']")
	WebElement tabSummary;
	
	@FindBy(xpath="//a[normalize-space()='Details']")
	WebElement tabDetails;
	
	
	
	public String ValidateSMSRecord(String sReceipientNo,String sType,String sStatus,ExtentTest node) throws IOException, InterruptedException {
		int iSMSCount = getSMSRowcount(); 
		if(iSMSCount<=1) {
			BaseClass.logger.info("Failed: SMS Not Received for the Mobile Number"+ sReceipientNo);
			UtilityCustomFunctions.fSoftAssert("Failed: SMS Not Received for the Mobile Number"+ sReceipientNo, "Failed: SMS Received for the Mobile Number"+ sReceipientNo, "SMS Receipient after Module change",node);
			BaseClass.sAssertinFn.assertEquals("Add New Record - SMS Not received", "Add New Record - SMS received");
			Assert.fail("SMS Not Received for the Mobile Number"+ sReceipientNo);
		}
		List<WebElement> tRowElements = WbTblRow.findElements(By.tagName("tr"));
		
		List<WebElement> tTDElements = tRowElements.get(1).findElements(By.tagName("td"));
		System.out.println("TD Size:" + tTDElements.size());
		String sActSMSReceipientNo = tTDElements.get(2).getText();
		String sActSMSType = tTDElements.get(3).getText();
		String sActSMSStatus= tTDElements.get(4).getText();
		String sActMessageId= tTDElements.get(5).getText();
		
		UtilityCustomFunctions.fSoftAssert(sActSMSReceipientNo, sReceipientNo,"SMS Receipient Number" , node);
		UtilityCustomFunctions.fSoftAssert(sActSMSType, sType,"SMS Type" , node);
		UtilityCustomFunctions.fSoftAssert(sActSMSStatus, sStatus,"SMS Status" , node);
		
		System.out.println("Actual Values: Receipt No:" + sActSMSReceipientNo + " : " + sActSMSType + " SMS Status : "+ sActSMSStatus + " Message Id:" + sActMessageId);
		
		return sActMessageId;
	}
	
	//Set Text
	public void setRecipient(String sNumber) {
		UtilityCustomFunctions.sendKeysNoEnter(driver, txtSearchReceipientNo, sNumber);
	}
	//get
	public int getSMSRowcount() throws InterruptedException {
		Thread.sleep(3000);
		List<WebElement> tRowCount = WbTblRow.findElements(By.tagName("tr"));
		UtilityCustomFunctions.logWriteConsole("SMS Count:" + tRowCount.size());
		return tRowCount.size();
	}
	//click
	public void clickSearch() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSearch);
	}
	public void clickFirstSMS() {
		List<WebElement> tRowElements= WbTblRow.findElements(By.tagName("tr"));
		tRowElements.get(1).click();
	}
	public void clickSummaryTab() throws Exception {
		UtilityCustomFunctions.doClick(driver, tabSummary);
	}
	public void clickDetailsTab() throws Exception {
		UtilityCustomFunctions.doClick(driver, tabDetails);
	}
	public String fValidateSMSNotification(String sAssignedTo,String sMobileNumber,String sSMSMsg,String sMessage,ExtentTest node) throws Exception {
		//Summary
		DetailViewPage objDVP = new DetailViewPage(driver); 
		UtilityCustomFunctions.doClick(driver, tabSummary);
		Thread.sleep(1000);
		String sActSMReceipNo= objDVP.getArraySummary(1);
		
		String sActSMStatus = objDVP.getArraySummary(2);
		String sActSMMsgId = objDVP.getArraySummary(3);
		String sActSMType = objDVP.getArraySummary(6);
		String sActSMMsg = objDVP.getArraySummary(10);
		String sActSMAssgnTo = objDVP.getSummaryAssignTo();
		
		//Summary Validations
		
		//Mobile Number
		String sMobNMArray[] = sActSMReceipNo.split("\\s+");
		sActSMReceipNo = sMobNMArray[0].trim() + " " + sMobNMArray[1].trim();
		System.out.println("Actual: " + sActSMReceipNo);
		
		UtilityCustomFunctions.fSoftAssert(sActSMReceipNo, sMobileNumber, "Summary Mobile Number:  " +sMessage , node);
		UtilityCustomFunctions.fSoftAssert(sActSMAssgnTo, sAssignedTo, "Summary AssignTo:  " +sMessage , node);
		UtilityCustomFunctions.fSoftAssert(sActSMStatus, "Sent", "Summary Status" +sMessage , node);
		UtilityCustomFunctions.fSoftAssert(sActSMType, "SMS", "Summary Type" +sMessage , node);
		UtilityCustomFunctions.fSoftAssert(sActSMMsg, sSMSMsg , "Summary Message"+ sMessage , node);
		
		//Click Detail View
//		objDVP.clickDetailView();
		objDVP.fSetDetailVew(true);
		Thread.sleep(3000);
		String sActDTAssignedTo = objDVP.getArrayDetails(1).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTAssignedTo, sAssignedTo, "Detail View AssignedTo:  " + sMessage, node);
		String sActDTReceipientNo = objDVP.getArrayDetails(2).trim();
		
		String sDTRecpntPNArray[] = sActDTReceipientNo.split("\\s+");
		sActDTReceipientNo = sDTRecpntPNArray[0].trim() + " " + sDTRecpntPNArray[1].trim();
		System.out.println("Actual: " + sActDTReceipientNo);
		UtilityCustomFunctions.fSoftAssert(sActDTReceipientNo,sMobileNumber , "Detail View MobileNo " +sMessage , node);
		//Status DTV
		String sActDTStatus = objDVP.getArrayDetails(3).trim();
		
		UtilityCustomFunctions.fSoftAssert(sActDTStatus, "Sent", "Detail View Status" +sMessage , node);
		String sActDTType = objDVP.getArrayDetails(6).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTType, "SMS", "Detail View Type " +sMessage , node);
		
		String sActDTMessage = objDVP.getArrayDetails(11).trim();
		UtilityCustomFunctions.fSoftAssert(sActDTMessage, sSMSMsg, "Detail View Message " +sMessage , node);
		return sActSMMsgId;
	}
	}

