package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends BasePage{

	public UserPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@name='name']")
	WebElement txtUserSearch;
	
	public void fClickSearch(String sUserName) {
		txtUserSearch.clear();
		txtUserSearch.sendKeys(sUserName);
		txtUserSearch.sendKeys(Keys.ENTER);
	}

}
