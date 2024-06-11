package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;
import utilities.UtilityCustomFunctions;
	public class CRMSettingsPage extends BasePage{
	public CRMSettingsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
//	@FindBy(xpath="(//a[@class='dropdown-item'])[3]")
//	WebElement mnuCRMSetting;
	
	@FindBy(linkText="CRM Setting")
	WebElement mnuCRMSetting;
	
	@FindBy(xpath="//label[normalize-space()='Other Settings']")
	WebElement mnuOtherSetting;
	
	@FindBy(xpath="//label[normalize-space()='Workflow']")
	WebElement mnuItemWorkflow;

	@FindBy(xpath="//label[normalize-space()='User & Access Control']")
	WebElement mnuUserAccessCtrl;
	
	@FindBy(xpath="//label[normalize-space()='User']")
	WebElement mnuItemUser;
	
	
	//Click Methods
	public void clickMnuItemWorkflow() throws Exception {
		UtilityCustomFunctions.doClick(driver, mnuItemWorkflow);
	
	}
	public void clickMnuOtherSetting() throws Exception {
		UtilityCustomFunctions.doClick(driver, mnuOtherSetting);
	
	}
	public void clickMnuCRMSetting() throws Exception {
		UtilityCustomFunctions.doClick(driver, mnuCRMSetting);
	}
	public void clickMnuUserAccesCtrl() throws Exception {
		UtilityCustomFunctions.doClick(driver, mnuUserAccessCtrl);
	}
	public void clickMnuItemUser() throws Exception {
		UtilityCustomFunctions.doClick(driver, mnuItemUser);
	}
	
	public void fCRMNavigate(String sMenuItem,String sMenuItem2) throws Exception {
		HomePage objHP = new HomePage(driver);
		CRMSettingsPage objCRMs = new CRMSettingsPage(driver);
		WorkFlowPage objWFP = new WorkFlowPage(driver);
		// Click Avatar
		BaseClass.logger.info("Clicked Avatar");
		System.out.println("Clicked Avatar");
		objHP.clickAvatar();
		Thread.sleep(2000);
		objCRMs.clickMnuCRMSetting();
		BaseClass.logger.info("Clicked Menu CRM Setting");
		System.out.println("Clicked Menu CRM Setting");
		String sXpath="//label[normalize-space()='" + sMenuItem + "']";
		driver.findElement(By.xpath(sXpath)).click();
		BaseClass.logger.info("Clicked " + sMenuItem);
		System.out.println("Clicked " + sMenuItem);
		Thread.sleep(2000);
		String sXpath1="//label[normalize-space()='" + sMenuItem2 + "']";
		driver.findElement(By.xpath(sXpath1)).click();
		Thread.sleep(2000);
		
	}
	
	
}
