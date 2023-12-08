package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.UtilityCustomFunctions;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//a[@class='dropdown-toggle nav-link dropdown-user-link']//img")
	WebElement eleAvatar;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement eleLogout;
	
	
	
	//IsMethods
	public boolean isAvatarDisplayed() {
		boolean bFlag = false;
		bFlag = UtilityCustomFunctions.IsElementVisible(driver, eleAvatar);
		return bFlag;
	}
	
	//Click Methods
	public void clickAvatar() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleAvatar);
	}
	
	public void clickLogout() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleLogout);
	}
	
}
