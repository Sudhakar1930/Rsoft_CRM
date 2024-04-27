package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.UtilityCustomFunctions;

public class SummaryViewPage extends BasePage{

	public SummaryViewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="(//tbody/tr/td/label)[1]")
	WebElement lblDocuFirst;
	
	@FindBy(css=".card-title.centers.bg-gradient-x-purple-blue")
	WebElement lblSequenceNo;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[1]")
	WebElement lblTitleAssignedTo1;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[2]")
	WebElement lblTitleAssignedTo2;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[3]")
	WebElement lblTitlePhoneNo;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[4]")
	WebElement lblTitleEmail;
	
	public String getTitleAssignedTo() throws Exception {
		String sGetText="";
		try {
			Thread.sleep(1000);
			String sAssign1 =UtilityCustomFunctions.getText(driver, lblTitleAssignedTo1);
			Thread.sleep(1000);
			String sAssign2 = UtilityCustomFunctions.getText(driver, lblTitleAssignedTo2);
			Thread.sleep(1000);
			UtilityCustomFunctions.logWriteConsole("sAssign1:"+sAssign1+"sAssign2:"+sAssign2);
			UtilityCustomFunctions.logWriteConsole("Get Text:"+sGetText);
			sGetText = sAssign1.trim() + " " + sAssign2.trim();
		}catch(Exception e) {
			System.out.println(e.getCause());
		}
		return sGetText; 
		
	}
	
	public String getTitlePhoneNumber() throws Exception {
		String sGetText="";
		sGetText = UtilityCustomFunctions.getText(driver, lblTitlePhoneNo);
		return sGetText.trim(); 
		
	}
	
	public String getTitleEmail() throws Exception {
		String sGetText="";
		sGetText = UtilityCustomFunctions.getText(driver, lblTitleEmail);
		return sGetText.trim(); 
		
	}
	
	
	public String getCurrentSequenceNo() throws Exception {
		String sGetText="";
		sGetText = UtilityCustomFunctions.getText(driver, lblSequenceNo);
		return sGetText;
	}
	
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
