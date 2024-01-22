package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//input[@id='companyname']")
	WebElement txtCompanyName;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[contains(@class,'loginsubmit')]")
	WebElement btnSubmit;
	
	@FindBy(xpath="//*[@id='Changeicon']")
	WebElement lnkEyeIcon;
	
	@FindBy(xpath="//*[@id='input_username']")
	WebElement txtMySqlUserId;
	
	@FindBy(xpath="//*[@id='input_password']")
	WebElement txtMySqlPwd;
	
	@FindBy(xpath="//*[@id='input_go']")
	WebElement txtMysqlGo;
	
	
	
	
	
	//Set Methods
	public void setMySqlUserId(String sUserName) {
		UtilityCustomFunctions.sendKeys(driver, txtMySqlUserId, sUserName);
	}
	public void setMySqlPasswd(String sPassword) {
		UtilityCustomFunctions.sendKeys(driver, txtMySqlPwd, sPassword);
	}
	
	public void setCompanyName(String sCompanyName) {
		UtilityCustomFunctions.sendKeys(driver, txtCompanyName, sCompanyName);
	}
	public void setUserName(String sUserName) {
		txtUserName.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver, txtUserName, sUserName);
		}
	public void setPassword(String sPassword) {
		txtPassword.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver, txtPassword, sPassword);
	}
	
	//Click Methods
	public void clickMySqlSubmit() throws Exception {
		UtilityCustomFunctions.doClick(driver, txtMysqlGo);
	}
	
	public void clickLoginSubmit() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSubmit);
	}
	public void clickEyeIcon() throws Exception {
		UtilityCustomFunctions.doClick(driver, lnkEyeIcon);
	}
	
	
	//IsMethods
	public boolean isLoginPageDisplayed(String sCRMUrl) throws InterruptedException {
		Thread.sleep(2000);
		boolean bFlag = false;
		if(driver.getCurrentUrl().equalsIgnoreCase(sCRMUrl)) {
			bFlag = true;
		}
		else {
			Assert.fail("Invalid CRM Url " + driver.getCurrentUrl());
			bFlag = false;
		}
		return bFlag;
	}
}
