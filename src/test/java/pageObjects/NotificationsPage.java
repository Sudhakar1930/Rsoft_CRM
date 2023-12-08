package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;

public class NotificationsPage extends BasePage{

	public NotificationsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[@id='notification-count-badge']")
	WebElement iconNotification;
	
	@FindBy(xpath="//b[normalize-space()='TODAY']")
	WebElement lblToday;
	
	@FindBy(xpath="(//*[@id='today']//div/p)[1]")
	WebElement lblNotificUserName;
	
	
	@FindBy(xpath="(//p[@class='view-column-txt notification-msg-txt'])[1]")
	WebElement lnkFirstNotifyMsg;
			
			
	
	//Get Text Methods
	public String getNotificatonCount() throws Exception {
		String sValue="";
		sValue = UtilityCustomFunctions.getText(driver, iconNotification);
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
	
	
	
}
