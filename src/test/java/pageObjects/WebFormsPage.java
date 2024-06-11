package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.UtilityCustomFunctions;

public class WebFormsPage extends BasePage{
	CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
	public WebFormsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//ul[contains(@class,'select2-selection__rendered')]")
	WebElement txtMultiComboBox;
	
	@FindBy(xpath="//span[@class='select2-selection__rendered' and @role='textbox']")
	WebElement cmbWebForm;
	
	@FindBy(xpath="//ul[@class='select2-results__options' and @role='tree']")
	WebElement cmbDropDown;
	
	@FindBy(xpath="//i[@class='fa fa-ellipsis-v']")
	WebElement btnEllipsis;
	
	@FindBy(linkText="Edit")
	WebElement btnEdit;

	@FindBy(xpath="//button[normalize-space()='Next']")
	WebElement btnNext;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement btnSave;
	
	@FindBy(xpath="select2-selection__rendered ui-sortable")
	WebElement lstRoundRobinUsers;
	
	public void fSetWebFormConfigValues(boolean IsAmend,String sUsersList) throws Exception {
		String sXpath="";
		cmbWebForm.click();
		Thread.sleep(1000);
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,"ETNotification Webform");
		Thread.sleep(1000);
		btnEllipsis.click();
		Thread.sleep(1000);
		btnEdit.click();
		Thread.sleep(2000);
		btnNext.click();
		objCMD.clickMultiComboDropDown();
		Thread.sleep(1000);
		if(IsAmend==true) {
			objCMD.fRemMultiComboValues();
		}
		String sUserArray[]= sUsersList.split(":");
		for(int i=0;i<sUserArray.length;i++) {
			Thread.sleep(3000);
			System.out.println("User:"+i+ "Is " +sUserArray[i]);
			
			Thread.sleep(1000);
			sXpath = "//ul[contains(@class,'select2-results__options')]/li[contains(text(),'"+sUserArray[i]+"')]";
			System.out.println("sXpath:" + sXpath);
			WebElement eleMultiCombo = driver.findElement(By.xpath(sXpath));
			System.out.println("Multi Combo Print Values " + i + "Values:" + eleMultiCombo.getText());
			UtilityCustomFunctions.doActionClick(driver, eleMultiCombo);
			Thread.sleep(1000);
			txtMultiComboBox.click();
			
		}
		txtMultiComboBox.click();
		Thread.sleep(1000);
		btnSave.click();
		Thread.sleep(2000);
	}
	
}
