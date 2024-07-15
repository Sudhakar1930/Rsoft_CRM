package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.UtilityCustomFunctions;
public class AllListPage extends BasePage{
	
	public AllListPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	@FindBy(xpath="//*[@id='vertical_header_name']/i")
//	@FindBy(xpath="//i[normalize-space()='list']")
	@FindBy(xpath="//a[contains(@class,'dropdown-toggle nav-link') and @id='vertical_header_name']")
	WebElement eleAllList;
	
	@FindBy(xpath="//*[@id='vertical_header_list']/li[6]/ul")
	WebElement eleOthers;
	
	@FindBy(xpath="//div[normalize-space()='Others']")
	WebElement eleColumnOthers;
	
	
	@FindBy(xpath="//div[text()='Notifications']")
	WebElement eleNotifications;
	
//	@FindBy(xpath="//ul[@id='vertical_header_list']//ul")
	@FindBy(xpath="//ul[@class='mega-dropdown-menu dropdown-menu row show']//li/a//div")
	List<WebElement> eleAllULItems;
	
	//Click Methods
	public void clickAllList() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("Within clickAllList before wait");
		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(50));
		webWait.until(ExpectedConditions.visibilityOf(eleAllList));
		webWait.until(ExpectedConditions.elementToBeClickable(eleAllList));
		System.out.println("Within clickAllList before click");
		js.executeScript("arguments[0].scrollIntoView(true);", eleAllList);
		eleAllList.click();
//		UtilityCustomFunctions.doClick(driver,eleAllList);
		System.out.println("Within clickAllList after click");
//		WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(50));
		Thread.sleep(5000);
	}
	public void clickAllNotifications() throws Exception {
		UtilityCustomFunctions.doClick(driver,eleNotifications);
	}
	
	public void clickModuleOnListAll(WebDriver driver, String sModuleName) throws Exception {
		boolean bFlag = false;
//		String sXpath="//div[text()=" + sModuleName + "]";
//		WebElement lstModuleName = driver.findElement(By.xpath(sXpath));
//		UtilityCustomFunctions.doClick(driver, lstModuleName);
		WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(50));
		webWait.until(ExpectedConditions.visibilityOf(eleColumnOthers));
		webWait.until(ExpectedConditions.elementToBeClickable(eleColumnOthers));
		int iSize = eleAllULItems.size();
		for(int i = 1;i<iSize;i++)
		{	
//			UtilityCustomFunctions.logWriteConsole("Actual List Item:" + i + "is: " + eleAllULItems.get(i).getText());
//			UtilityCustomFunctions.logWriteConsole("Expected List Item:" + sModuleName);
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
