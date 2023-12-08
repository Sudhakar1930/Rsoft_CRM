package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;
public class AllListPage extends BasePage{
	
	public AllListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//*[@id='vertical_header_name']/i")
	WebElement eleAllList;
	
	@FindBy(xpath="//*[@id='vertical_header_list']/li[6]/ul")
	WebElement eleOthers;
	
	
	//Click Methods
	public void clickAllList() throws Exception {
		UtilityCustomFunctions.doClick(driver,eleAllList);
	}
	
	public void clickModuleOnListAll(WebDriver driver, String sModuleName) throws Exception {
//		String sXpath="//div[text()=" + sModuleName + "]";
//		WebElement lstModuleName = driver.findElement(By.xpath(sXpath));
//		UtilityCustomFunctions.doClick(driver, lstModuleName);
		
		UtilityCustomFunctions.SelectItemifContains(driver, eleOthers, sModuleName);
	}
	
	
}
