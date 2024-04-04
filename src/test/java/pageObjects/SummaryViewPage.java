package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SummaryViewPage extends BasePage{

	public SummaryViewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="(//tbody/tr/td/label)[1]")
	WebElement lblDocuFirst;
	
	public void clickEditCheckBox(int iIndex) {
//		WebElement eleEditCheckBox =   
		String sXpath = "(//div[@class='col-lg-2'])[" + iIndex + "]";
		WebElement eleEditCheckBox = driver.findElement(By.xpath(sXpath));
		eleEditCheckBox.click();
	}
	public void fWaitTillControlVisible() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(100));
//		wait.until(ExpectedConditions.textToBePresentInElement(lblDocuFirst, "Text"));
		wait.until(ExpectedConditions.visibilityOf(lblDocuFirst));
		
	}
	
	
	
}
