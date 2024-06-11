package pageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.UtilityCustomFunctions;

public class UserPage extends BasePage{

	public UserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@name='name']")
	WebElement txtUserSearch;
	
	@FindBy(xpath="//table[@class='table table-striped']//tr//td")
	List<WebElement> tblUsersTd;
	
	@FindBy(xpath="//table[@class='table table-striped']//tr//div[@class='col-sm-9']")
	List<WebElement> tblUsersTr;
	
	@FindBy(xpath="//td/label[contains(text(),'Availability')]/following::td[1]")
	WebElement lblAvailabilityStatus;
	
	@FindBy(xpath="//td/label[contains(text(),'Admin')]/following::td[1]")
	WebElement lblAdminStatus;
	
	public int fSearchUser(String sUserName) {
		UserPage objUP = new UserPage(driver);
		int i=0;
		txtUserSearch.clear();
		txtUserSearch.sendKeys(sUserName);
		txtUserSearch.sendKeys(Keys.ENTER);
		if(tblUsersTr.size()>=1) {
			tblUsersTr.get(0).click();
			i = 1;
		}
		else {
			i=0;
		}
		return i;
	}
	public int fGetUserTableSize() {
		return tblUsersTr.size(); 
	}
	public String fGetUserAvailability() throws Exception {
		String sAvailStatus="";
		String sReturnValue="";
		
			sAvailStatus = UtilityCustomFunctions.getText(driver, lblAvailabilityStatus);
			sReturnValue = sAvailStatus;
		
		return sReturnValue;
	}
	public String fGetUserAdmin() throws Exception {
		String sAdminStatus="";
		String sReturnValue="";
		
			sAdminStatus = UtilityCustomFunctions.getText(driver, lblAdminStatus);
			sReturnValue = sAdminStatus;
		
		return sReturnValue; 
		
	}
}
