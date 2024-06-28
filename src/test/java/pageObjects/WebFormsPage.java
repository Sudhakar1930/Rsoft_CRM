package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.UtilityCustomFunctions;

public class WebFormsPage extends BasePage{
	
	public WebFormsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//ul[contains(@class,'select2-selection__rendered')]")
	WebElement txtMultiComboBox;
	
	@FindBy(xpath = "//ul[@class='select2-results__options' and @role='tree']/li[@aria-selected='true']")
    List<WebElement> lstMultiCombo;
	
	@FindBy(xpath="(//ul[@class='select2-selection__rendered'])[2]")
	WebElement cmbCondition1RR;
			
	@FindBy(xpath="//span[@class='select2-selection__rendered' and @role='textbox']")
	WebElement cmbWebForm;
	
	@FindBy(xpath="//ul[@class='select2-results__options' and @role='tree']")
	WebElement cmbDropDown;
	
	//ul[@class='select2-results__options' and @role='tree']/li
	
	
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
	
	
	public void clickWebFormSave() {
		btnSave.click();
	}
	@SuppressWarnings("restriction")
	public int fSelectWebForm(String sWebFormName, String sModuleName) throws InterruptedException {
		boolean bCurrentWFStatus = false;
		boolean bMatchWFEnabled = false;
		boolean bCurrentPage = false;
		String sMatchWebForm = "";
//		fSelectWbFrmModule("ETNotification Webform");
		Thread.sleep(2000);
		int iRowCount =lstWebForms.size(); 
		int iMatchRow=0;
		int iRowPos=0;
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
			
			bCurrentPage =true; //Whether record present in current  page.
			iRowPos =i;
			sMatchWebForm = sActWebFormName;
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
		
		System.out.println("Is WebForm Present in Current Page: " + bCurrentPage);
		System.out.println("WebForm:" + sMatchWebForm + " Is Enabled: " + bMatchWFEnabled);
		
		sMatchWebForm = "";
		if(bCurrentPage==false) {
			iRowPos= -1;
			String sBtnXpath = "//button[@class='btn btn-sm btn-bg-gradient-x-purple-blue module-buttons']";
			String sNextPagePath = "//i[@class='fa fa-chevron-right']";
			
			WebElement btnNextPage = driver.findElement(By.xpath(sNextPagePath));
			//button[@class='btn btn-sm btn-bg-gradient-x-purple-blue module-buttons']
			if(btnNextPage.isEnabled() && btnNextPage.isDisplayed()) {
				btnNextPage.click();
				WebElement btnAddWebForm = driver.findElement(By.xpath(sBtnXpath));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//				wait.until(ExpectedConditions.elementToBeClickable(btnAddWebForm));
				wait.until(ExpectedConditions.visibilityOf(btnAddWebForm));
			}
		}
		else {
			if(bMatchWFEnabled==false) {
				String sXpath="(//table[@class='table table-bordered table-striped']/tbody/tr//span[@class='switchery switchery-default']/small)[" + iMatchRow + "]";
				driver.findElement(By.xpath(sXpath)).click();
			}
		}
		return iRowPos;
	}
	public void fSelectWbFrmModule(String sModuleName) {
		cmbWebForm.click();
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,sModuleName);
	}
	public void fNavigateWFUserConfigPage(String sModuleName,String sWebFormName) throws InterruptedException {
		int iWebFormRowPos=0;
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		WebFormsPage objWSP = new WebFormsPage(driver);
		cmbWebForm.click();
		Thread.sleep(1000);
		UtilityCustomFunctions.selectOneItemfromListBox(driver,cmbDropDown,"ETNotification Webform");
		Thread.sleep(1000);
		do {
		iWebFormRowPos = objWSP.fSelectWebForm(sWebFormName,sModuleName);
		System.out.println("Return Value Within While: " +iWebFormRowPos);
		}while(iWebFormRowPos==-1);
		System.out.println("Return Value after While : " +iWebFormRowPos);
		Thread.sleep(3000);
		Thread.sleep(3000);
		String sElipsisXpath = "(//i[@class='fa fa-ellipsis-v'])[" + iWebFormRowPos + "]";
		System.out.println(sWebFormName + " Xpath: " + sElipsisXpath);
		WebElement btnEllipsis = driver.findElement(By.xpath(sElipsisXpath)); 
		btnEllipsis.click();
		Thread.sleep(1000);
		btnEdit.click();
		Thread.sleep(2000);
		btnNext.click();
	}
	public void fSetRoundRobinUsers(boolean IsAmend,String sUsersList) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sXpath="";
		objCMD.clickMultiComboDropDown();
		Thread.sleep(1000);
		if(IsAmend==true) {
			objCMD.fRemMultiComboValues();
		}
		System.out.println("Users In fSEtRoundRobinUser: " + sUsersList);
		String sUserArray[]= sUsersList.split(":");
		for(int i=0;i<sUserArray.length;i++) {
			Thread.sleep(3000);
			System.out.println("User:"+i+ "Is " +sUserArray[i]);
//			txtMultiComboBox.click();
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
	}
	public void fSetCond1RRUsers(boolean IsAmend,String sUsersList) throws Exception {
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		String sXpath="";
		cmbCondition1RR.click();
		Thread.sleep(1000);
		if(IsAmend==true) {
			fRemConditionRRValues();
		}
		String sUserArray[]= sUsersList.split(":");
		for(int i=0;i<sUserArray.length;i++) {
			Thread.sleep(3000);
			System.out.println("User:"+i+ "Is " +sUserArray[i]);
			cmbCondition1RR.click();
			Thread.sleep(1000);
			sXpath = "//ul[contains(@class,'select2-results__options')]/li[contains(text(),'"+sUserArray[i]+"')]";
			System.out.println("sXpath:" + sXpath);
			WebElement eleMultiCombo = driver.findElement(By.xpath(sXpath));
			System.out.println("Multi Combo Print Values " + i + "Values:" + eleMultiCombo.getText());
			UtilityCustomFunctions.doActionClick(driver, eleMultiCombo);
			Thread.sleep(3000);
			txtMultiComboBox.click();
			
		}
		txtMultiComboBox.click();
		Thread.sleep(1000);
	}
	
	//Removing All MultiSelected Values
	public void fRemConditionRRValues() throws InterruptedException {
		//ul[@class='select2-results__options' and @role='tree']/li[contains(text(),)]
		
		System.out.println("Inside remove existing values:");
		int iCount = lstMultiCombo.size();
		System.out.println("Multicombo: count" + iCount);
		if(iCount>0) {
			for(int i = iCount-1;i>=0;i--) {
				
				Thread.sleep(3000);
				System.out.println("i : " + i + " Icount:"+ iCount);
				lstMultiCombo.get(i).click();
				Thread.sleep(1000);
				cmbCondition1RR.click();
			}
				
		}
		else {
			cmbCondition1RR.click();
		}
		cmbCondition1RR.click();
	}

}
