package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.UtilityCustomFunctions;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	@FindBy(xpath="(//a[@class='dropdown-toggle nav-link dropdown-user-link']//img)[1]")
//	@FindBy(xpath="//a[@class='dropdown-toggle nav-link dropdown-user-link']//img")
	@FindBy(xpath="(//a[@class='dropdown-toggle nav-link dropdown-user-link'])[1]/span")
	WebElement eleAvatar;
	
//	@FindBy(xpath="//a[normalize-space()='Logout']")
	@FindBy(xpath="//div[@class='dropdown-menu dropdown-menu-left profile_dropdown show']//a[@class='dropdown-item'][normalize-space()='Logout']")
	WebElement eleLogout;
	
	public boolean isAvatarDisplayed() {
		boolean bFlag = false;
		bFlag = UtilityCustomFunctions.IsElementVisible(driver, eleAvatar);
		return bFlag;
	}
	
	//Click Methods
	public void clickAvatar() throws Exception {
//		WebElement eleAvatar1 = driver.findElement(By.xpath("//li[@class='nav-item mobile-menu d-md-none flex-start']//preceding::li/a"));
		WebElement eleAvatar2 = driver.findElement(By.xpath("//a[@class='dropdown-toggle nav-link dropdown-user-link']"));
		
		
		AllListPage objALP = new AllListPage(driver);  
//		WebElement eleLeadMod= driver.findElement(By.xpath("//*[@id='MoreMod_Lead_module']/span"));
//		Actions Action = new Actions(driver);
//		Action.moveToElement(eleAvatar1).click().perform();
		
//		objALP.clickAllList();
//		eleAvatar1.click();
		UtilityCustomFunctions.doClick(driver, eleAvatar2);
	}
	
	public void clickLogout() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleLogout);
	}
	
	public void clickLogoutCRM() throws Exception {
		Thread.sleep(2000);;
		clickAvatar();
		Thread.sleep(2000);
		clickLogout();
//		driver.close();
		
	}
	
}
