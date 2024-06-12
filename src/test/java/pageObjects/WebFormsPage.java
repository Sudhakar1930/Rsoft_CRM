package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.UtilityCustomFunctions;

public class WebFormsPage extends BasePage{
	
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
	
	@SuppressWarnings("restriction")
	@FindBy(xpath="//table[@class='table table-bordered table-striped']/tbody/tr")
	List<WebElement> lstWebForms;
	
	@SuppressWarnings("restriction")
	public void fSelectWebForm(String sWebFormName, String sModuleName) throws InterruptedException {
		boolean bCurrentWFStatus = false;
		boolean bMatchWFEnabled = false;
		fSelectWbFrmModule("ETNotification Webform");
		Thread.sleep(2000);
		int iRowCount =lstWebForms.size(); 
		int iMatchRow=0;
		for(int i=1;i<=iRowCount;i++) {
		String sXpathWFName="//table[@class='table table-bordered table-striped']/tbody/tr[" + i + "]/td[2]";
		String sXpathModName="//table[@class='table table-bordered table-striped']/tbody/tr[" + i + "]/td[3]";
		
		String sActWebFormName = driver.findElement(By.xpath(sXpathWFName)).getText();
		String sActModuleName = driver.findElement(By.xpath(sXpathModName)).getText();
		System.out.println("WF Name: " + sActWebFormName + "Module Name: " + sActModuleName);
		String sXpath="(//table[@class='table table-bordered table-striped']/tbody/tr//span[@class='switchery switchery-default']/small)[" + i + "]";
		WebElement eleStatus = driver.findElement(By.xpath(sXpath));
		String sAttrValues =eleStatus.getAttribute("style");
		if(sAttrValues.contains("0px")) {
			bCurrentWFStatus = false;
		}
		else
		{
			bCurrentWFStatus = true;
		}
		if(sActWebFormName.equalsIgnoreCase(sWebFormName) && sActModuleName.equalsIgnoreCase(sModuleName)) {
			
			if(bCurrentWFStatus==false) {
				iMatchRow = i;
				bMatchWFEnabled = false;
			}
			else {
				bMatchWFEnabled = true;
			}
		}
		else {
			if(bCurrentWFStatus==true) {
				eleStatus.click(); 
			}
		}
		}//for loop
		
		if(bMatchWFEnabled==false) {
			String sXpath="(//table[@class='table table-bordered table-striped']/tbody/tr//span[@class='switchery switchery-default']/small)[" + iMatchRow + "]";
			driver.findElement(By.xpath(sXpath)).click();
		}
	}
	public void fSelectWbFrmModule(String sModuleName) {
		cmbWebForm.click();
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,sModuleName);
	}
	public void fSetWebFormConfigValues(boolean IsAmend,String sUsersList) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
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
