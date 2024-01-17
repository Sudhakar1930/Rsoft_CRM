package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;

public class NotificationsPage extends BasePage{

	public NotificationsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="(//ul[@class='select2-selection__rendered'])[1]")
	WebElement txtAssignedTo                                                                                                                                                                                                                                                                           ;
			
	@FindBy(xpath="//input[@name='notifications_modulerecid']")
	WebElement txtSearchRecordId;
	
	@FindBy(xpath="//span[@id='notification-count-badge']")
	WebElement iconNotification;
	
	@FindBy(xpath="//b[normalize-space()='TODAY']")
	WebElement lblToday;
	
	@FindBy(xpath="(//*[@id='today']//div/p)[1]")
	WebElement lblNotificUserName;
	
	
	@FindBy(xpath="(//p[@class='view-column-txt notification-msg-txt'])[1]")
	WebElement lnkFirstNotifyMsg;
	
	@FindBy(xpath="(//div[@class='msg-container']/h6)[1]")
	WebElement lnkNotification;
	
	//NotificationDetail
	@FindBy(xpath="(//div[@class='col-lg-10']//p)[1]")
	WebElement lblDTAssignedTo;
	
	@FindBy(xpath="(//div[@class='col-lg-10']//p)[2]")
	WebElement lblDTStatus;
	
	@FindBy(xpath="(//div[@class='col-lg-10']//p)[3]")
	WebElement lblDTSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10']//p)[4]")
	WebElement lblDTTitle;
	
	@FindBy(xpath="(//div[@class='col-lg-10']//p)[5]")
	WebElement lblDTModRecId;
	
	@FindBy(xpath="//a[normalize-space()='Summary']")
	WebElement tabSummary;
		
	//Notificatin Summary
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[3]")
	WebElement lblActionTitle;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[2]")
	WebElement lblSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[1]")
	WebElement lblSummaryStatus;
	
	@FindBy(xpath="//label[@class='instance_field_208_label']")
	WebElement lblSummaryAssignedTo;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement btnSearch;
	
//	@FindBy(xpath="//li[@class='select2-search select2-search--inline']")
//	WebElement txtAssignedTo;
	
	//Get Text Methods
	public String getSMAssignedTo() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblSummaryAssignedTo);
		return sValue;
		
	}
	public String getSMStatus() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblSummaryStatus);
		return sValue;
		
	}
	public String getSMSummary() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblSummary);
		return sValue;
		
	}
	public String getSMActionTitle() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblActionTitle);
		return sValue;
		
	}
	public String getDTAssignedTo() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblDTAssignedTo);
		return sValue;
		
	}
	public String getDTStatus() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblDTStatus);
		return sValue;
		
	}
	public String getDTSummary() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblDTSummary);
		return sValue;
	}
	public String getDTTitle() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblDTTitle);
		return sValue;
	}
	public String getDTModRecId() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, lblDTModRecId);
		return sValue;
	}
	
	public String getNotificatonCount() throws Exception {
		String sValue="";
		try {
			sValue = UtilityCustomFunctions.getText(driver, iconNotification);
		}catch(Exception e) {
			sValue = "0";
		}
		return sValue;
	}
	public String getTodayText() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver,lblToday);
		return sValue;
	}
	
	public String getNotifyUserName() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver,lblNotificUserName);
		return sValue;
	}
	
	
	//click Methods
	public void clickNotificatons() throws Exception {
		UtilityCustomFunctions.doClick(driver, iconNotification);
	}	
	
	public void clickNotifyUser() throws Exception {
		UtilityCustomFunctions.doClick(driver, lblNotificUserName);
	}
	public void clickNotifyFirstMsg() throws Exception {
		UtilityCustomFunctions.doClick(driver, lnkFirstNotifyMsg);
	}
	public void clickOpnNotifyPage() throws Exception {
		UtilityCustomFunctions.doClick(driver, lnkNotification);
	}
	public void clickSummaryTab() throws Exception {
		UtilityCustomFunctions.doClick(driver, tabSummary);
	}
	public void clickSearchButton() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSearch);
	}
	
	//Set Methods
	public void setRecordId(String sRecordId) {
		UtilityCustomFunctions.sendKeysNoEnter(driver, txtSearchRecordId, sRecordId);
	}
	
	public void setAssignedTo(String sAssignedTo) throws Exception {
		txtAssignedTo.click();
		String sArrAssignTo[] = sAssignedTo.split("\\s+");
		String sXpath="//ul[@class='select2-results__options']/li[contains(text(),'"+sArrAssignTo[1].trim()+"')]"; 
		UtilityCustomFunctions.logWriteConsole(sXpath);
		WebElement eleAssignedTo = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.doActionClick(driver, eleAssignedTo);
		
	}
	
	
	
}
