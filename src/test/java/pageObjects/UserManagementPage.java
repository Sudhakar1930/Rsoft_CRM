package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserManagementPage extends BasePage{

	public UserManagementPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//i[@class='fa fa-edit field_1 editicon']")
	WebElement icnUserName;
	
	@FindBy(xpath="//input[@name='name']")
	WebElement lblEditUserName;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_2 editicon']")
	WebElement icnEmail;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement lblEditEmail;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_5 editicon']")
	WebElement icnFirstName;
	
	@FindBy(xpath="//input[@name='users_firstname']")
	WebElement lblEditFirstName;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_6 editicon']")
	WebElement icnLastName;
	
	@FindBy(xpath="//input[@name='users_lastname']")
	WebElement lblEditLastName;
	
	
	@FindBy(xpath="//i[@class='fa fa-edit field_10 editicon']")
	WebElement icnRole;
	
	@FindBy(id="select2-selfield_10-container")
	WebElement SelectRoleTextBox;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_2089 editicon']")
	WebElement icnLoginAccess;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_2103 editicon']")
	WebElement icnAvailability;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_2116 editicon']")
	WebElement icnPhoneNumber;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_2160 editicon']")
	WebElement icnEmpCode;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_5454 editicon']")
	WebElement icnRelatedUser;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_5646 editicon']")
	WebElement icnCreatedAt;
	
	@FindBy(xpath="//i[@class='fa fa-edit field_0 editicon']")
	WebElement icnAdmin;
	
	
	
}
