package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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
	
	@FindBy(xpath="//div[text()='Notifications']")
	WebElement eleNotifications;
	
//	@FindBy(xpath="//ul[@id='vertical_header_list']//ul")
	@FindBy(xpath="//ul[@class='mega-dropdown-menu dropdown-menu row show']//li/a//div")
	List<WebElement> eleAllULItems;
	
	//Click Methods
	public void clickAllList() throws Exception {
		UtilityCustomFunctions.doClick(driver,eleAllList);
	}
	public void clickAllNotifications() throws Exception {
		UtilityCustomFunctions.doClick(driver,eleNotifications);
	}
	
	public void clickModuleOnListAll(WebDriver driver, String sModuleName) throws Exception {
		boolean bFlag = false;
//		String sXpath="//div[text()=" + sModuleName + "]";
//		WebElement lstModuleName = driver.findElement(By.xpath(sXpath));
//		UtilityCustomFunctions.doClick(driver, lstModuleName);
		int iSize = eleAllULItems.size();
		for(int i = 1;i<iSize;i++)
		{	
			UtilityCustomFunctions.logWriteConsole("Actual List Item:" + i + "is: " + eleAllULItems.get(i).getText());
			UtilityCustomFunctions.logWriteConsole("Expected List Item:" + sModuleName);
			String sActModName = eleAllULItems.get(i).getText();
				if(sActModName.trim().equalsIgnoreCase(sModuleName)) {
					UtilityCustomFunctions.doClick(driver, eleAllULItems.get(i));
					bFlag = true;
					break;
				}
//			
		}
		if (bFlag == false){
			Assert.fail(sModuleName + "Not Displayed");
		}
		
//		String sXpath="//*[@id='vertical_header_list']/li[" +iColNumber + "]/ul";
//		WebElement MnuItemInAll = driver.findElement(By.xpath(sXpath));
//		Thread.sleep(2000);
//		UtilityCustomFunctions.logWriteConsole(sXpath);
//		UtilityCustomFunctions.SelectItemifContains(driver, MnuItemInAll, sModuleName);
	}
	
	
}
