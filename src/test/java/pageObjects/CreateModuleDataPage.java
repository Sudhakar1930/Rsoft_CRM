package pageObjects;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;
import utilities.UtilityCustomFunctions;
public class CreateModuleDataPage extends BasePage{
	public static List<WebElement> objLists;
	public CreateModuleDataPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[@id='select2-assign_to-1y-container']")
	WebElement eleSelectAssignedTo;
	
	@FindBy(xpath="//input[@name='crmeditfieldmodule_phonenumber']")
	WebElement eletxtPhoneNumber;
	
	@FindBy(xpath="//input[@type='number' and @name='crmeditfieldmodule_numberfield']")
	WebElement eletxtNumberField;
	
	@FindBy(xpath="//input[@type='tel']")
	WebElement eletxtMobileNumber;
	
	@FindBy(xpath="//input[@type='email']")
	WebElement eletxtEmail;
	
	@FindBy(xpath="//span[@id='select2-selfield_5803-container' and @title='Select An Option']")
	WebElement eleSelectMnuList;
	
	@FindBy(xpath="//ul[@class='select2-results__options' and @role='tree']")
	WebElement eleUlSelect;
	
	@FindBy(xpath="//span[@role='combobox']//ul")
	WebElement eleMultiSelect;
	
	@FindBy(xpath="//input[@role='textbox']")
	WebElement eleMnuTextBox;
	
	@FindBy(xpath="//input[@type='number' and @name='crmeditfieldmodule_enteryournumber']")
	WebElement txtEnterYourNumber;
	
	
	@FindBy(xpath="//*[@id='select2-selfield_5803-container']")
	WebElement txtMnuList;
	
	@FindBy(xpath="//ul[@class='select2-selection__rendered']")
	WebElement txtMultiComboBox;
	
	@FindBy(xpath="//*[@id='select2-selfield_5803-results' and @role='tree']")
	WebElement eleMnuUl;
	
	@FindBy(xpath="//div[@class='form-actions right']//button[@type='submit'][normalize-space()='Save']")
	WebElement btnSave;
	
	@FindBy(xpath="//a[normalize-space()='Close']")
	WebElement btnClose;
	
	@FindBy(xpath="//div[@class='daterangepicker ltr single opensright show-calendar']//button[@type='button'][normalize-space()='Apply']")
	WebElement DandTApply;
	
	@FindBy(xpath="//input[@id='upload_0_crmmodonlyonfirstsave_file']")
	WebElement eleUploadFile;
	
	@FindBy(xpath="//span[@id='select2-selfield_5893-container']")
	WebElement eleEnqCatText;
	
	@FindBy(xpath="(//i[contains(@class,'fa fa-edit')])[1]")
	WebElement eleEllipsisEdit;
	
	@FindBy(xpath="//button[normalize-space()='edit_square']")
	WebElement eleEditButton;
	
	
	
	public void clickEditRecord() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleEllipsisEdit);
	}
	
	public void clickEdit() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleEditButton);
	}
	
	public void clickEllipsis(int iEllipsisPos) throws InterruptedException {
		String sXpath = "(//i[@class='fa fa-ellipsis-v'])["+iEllipsisPos+"]";
		WebElement eEllipsis = driver.findElement(By.xpath(sXpath));
		Thread.sleep(1000);
		eEllipsis.click();
	}
	
	public void clickExistingModData(int iRowPos) throws InterruptedException {
		String sXpath = "(//td[@scope='row']/following-sibling::td)["+iRowPos+"]";
		WebElement eleModData = driver.findElement(By.xpath(sXpath));
		Thread.sleep(1000);
		eleModData.click();
	}
	public void clickDay(int iDateIndex) throws Exception {
		String sXpath="(//td[contains(@class,'today')])[" + iDateIndex + "]";
		UtilityCustomFunctions.logWriteConsole(sXpath);
		WebElement eleDate = driver.findElement(By.xpath(sXpath));
		Thread.sleep(1000);
		eleDate.click();
//		UtilityCustomFunctions.doClick(driver, eleDate);
	}
	public void clickEnqCategory() throws Exception {
//		UtilityCustomFunctions.doClick(driver, eleEnqCatText);
		UtilityCustomFunctions.doActionClick(driver, eleEnqCatText);
	}
	//get Methods
	
	public String fGetModuleText(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleText = driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getText(driver, eleText);
	}
	
	public String fGetModuleValue(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleText = driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getValue(driver, eleText);
	}
	
	public String fGetUploadFileName() {
		 WebElement fileName = driver.findElement(By.id("uploaded-files"));
		 return fileName.getText();
	}
	
	//click methods

	public void clickDandTApply() throws Exception {
		UtilityCustomFunctions.doClick(driver, DandTApply);
	}
	public void clickTime(String sHours,String sMinues) throws Exception {
		
	String xPathHours = "//div[normalize-space()='" +sHours+ "']";
	WebElement eleHours = driver.findElement(By.xpath(xPathHours));
	UtilityCustomFunctions.doActionClick(driver, eleHours);
	Thread.sleep(3000);
	String xPathMinutes = "//div[normalize-space()='" +sMinues+ "']";
	WebElement eleMinutes = driver.findElement(By.xpath(xPathMinutes));
	UtilityCustomFunctions.doActionClick(driver, eleMinutes);
	Thread.sleep(3000);
	
	}
	public void clickMultiComboBox(String sMS_Value) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", txtMultiComboBox);
		System.out.println("Expected values:" + sMS_Value);
		String arrValues[]=sMS_Value.split(",");
		Thread.sleep(1000);
		System.out.println("Array Lenght: " + arrValues.length);
		for(int i=0;i<arrValues.length;i++) {
			Thread.sleep(3000);
			txtMultiComboBox.click();
			Thread.sleep(3000);
			objLists = driver.findElements(By.xpath("//li[@role='treeitem']"));
			
			for(int j=0;j<objLists.size();j++) {
				
				System.out.println("acutal: " + objLists.get(j).getText());
				System.out.println("expected is: " + arrValues[i]);

					 if (arrValues[i].equalsIgnoreCase(objLists.get(j).getText().trim())) {
						 Thread.sleep(1000);
						 UtilityCustomFunctions.doActionClick(driver, objLists.get(j));
						 System.out.println(" Item selected in Multi combox Box : "+objLists.get(j));
						 BaseClass.logger.info(" Item selected in Multi combox Box: "+objLists.get(j));
						 Thread.sleep(3000);
						 break;
					 }//inner if
					 	 
			}//for loop of objList
			
		}// for loop of array items.
//		txtMultiComboBox.sendKeys(Keys.ENTER);
	}
	
	
	
	//click Methods
	public void selectMenuValue(String sValue) {
		UtilityCustomFunctions.selectOneItemfromListBox(driver, eleMnuUl, sValue);
	}
	public void selectListValue(String sValue) {
		UtilityCustomFunctions.selectOneItemfromListBox(driver, eleUlSelect, sValue);
	}
	//click Methods
	public void clickSave() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSave);
	}
	
	public void clickClose() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnClose);
	}
	public void clickMenuList(String sMenuItem) throws Exception {
		System.out.println("Before clicking MenuItem");
		boolean bFlag = UtilityCustomFunctions.IsElementVisible(driver, eleSelectMnuList);
		if(bFlag==true) {
//		UtilityCustomFunctions.doClick(driver, eleSelectMnuList);
		eleSelectMnuList.click();
		System.out.println("After clicking combobox");
		Thread.sleep(2000);
		
		}
		else {
		 System.out.println("Combo Box missing");	
		}
		
	}
	public void clickArrayDropDown(int iObjIndex) throws Exception {
		String sXpath="(//span[@class='select2-selection__rendered' and @role='textbox'])[" + iObjIndex + "]";
		WebElement eleDropDown = driver.findElement(By.xpath(sXpath));
//		UtilityCustomFunctions.doClick(driver, eleDropDown);
		eleDropDown.click();
	}
	public void clickArrayCheckBox(int iObjIndex,String sValue) {
		String xPath = "(//input[@type='checkbox'])[" + iObjIndex + "]";  
		WebElement eleCheckBox = driver.findElement(By.xpath(xPath));		
		if(sValue.equalsIgnoreCase("ON")) {
			if(eleCheckBox.isSelected()) {
				
			}
			else {
				eleCheckBox.click();
			}
		}
		else {
			if(eleCheckBox.isSelected()) {
				eleCheckBox.click();
			}
		}
	}
	
	//Select,Set Values
	public void setMobileNumber(String sNumber) {
		UtilityCustomFunctions.sendKeysNoEnter(driver,eletxtMobileNumber,sNumber);
	}
	
	public void ClickListPhonePrefix(String sModuleName,String sText) throws Exception {
	String sXpath = "//span[@id='select2-" + sModuleName.toLowerCase() + "_" + sText +"']";
	System.out.println("Phone Prefix xpath: " + sXpath);
	WebElement elePhonePrefix = driver.findElement(By.xpath(sXpath));
	elePhonePrefix.click();
//	UtilityCustomFunctions.doClick(driver, elePhonePrefix);
	
	}
	public void setArrayTextBoxValue(int iObjIndex,String sTxtValue) {
		String sXpath="(//span[@class='select2-selection__rendered' and @role='textbox'])[" + iObjIndex + "]";
		WebElement eleTxtBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTxtBox,sTxtValue);
	}
	public void setInputValue(String sModuleName,String sText,String sValue) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setInputNumber(String sModuleName,String sText,String sValue) throws Exception {
		String sXpath = "//input[@type ='number' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setGenericInputValue(String sType,String sModuleName,String sText,String sValue) throws Exception {
		String sXpath = "//input[@type ='"+ sType + "' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		eleTextBox.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setTextAreaValue(String sModuleName,String sText,String sValue) {
		String sXpath="//textarea[@name='"+sModuleName.toLowerCase()+ "_" + sText + "']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	
	public void clickDateBox(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.doClick(driver, eleTextBox);
	}
	
	public void setEmailValue(String sModuleName,String sText) {
		String sXpath = "//input[@type ='email' and @name='" + sModuleName.toLowerCase()+"_"+"email']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sText);
	}
	
	public void SetAssignedTo(String sAssignedTo) {
//		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelectAssignedTo, sAssignedTo, "span");
		eleSelectAssignedTo.sendKeys(sAssignedTo);
		
	}
	public void SetPhoneNumber(String sPhoneNumber) {
		System.out.println("Phone number to be entered:" + sPhoneNumber);
		UtilityCustomFunctions.sendKeysNoEnter(driver, eletxtPhoneNumber, sPhoneNumber);
		System.out.println("Phone number entered:" + sPhoneNumber);
//		eletxtPhoneNumber.sendKeys(sPhoneNumber);
	}
	
	public void SetNumberField(String sNumber) {
		System.out.println("Number field to be entered:" + sNumber);
		UtilityCustomFunctions.sendKeysNoEnter(driver, eletxtNumberField, sNumber);
		System.out.println("Number field entered:" + sNumber);
	}
	public void SetEmail(String sEmail) {
		System.out.println("Email to be entered:" + sEmail);
		UtilityCustomFunctions.sendKeysNoEnter(driver, eletxtEmail, sEmail);
		System.out.println("Email entered:" + sEmail);
	}
	public void SetEnterYourNumber(String sNumber) {
		System.out.println("Number to be entered:" + sNumber);
		UtilityCustomFunctions.sendKeys(driver, txtEnterYourNumber, sNumber);
		System.out.println("Number entered:" + sNumber);
	}
	
	public void setUploadFile() throws Exception {
//		UtilityCustomFunctions.doClick(driver, eleUploadFile);
		File uploadfile = new File("./src/test/resources/annie-spratt.jpg");
		WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
		fileInput.sendKeys(uploadfile.getAbsolutePath());
//		 driver.findElement(By.id("file-submit")).click();
	}
	
	//IsMethods
		public boolean isEmailDisplayed() {
			boolean bFlag = false;
			try {
			if(UtilityCustomFunctions.IsElementVisible(driver, eletxtEmail)) {
				bFlag = true;
			}
			else {
				bFlag = false;
			}
			}catch(Exception e) {
				bFlag = false;
			}
			return bFlag;
		}
		
	public void displayComboBoxItems() {
		List<WebElement> list = txtMnuList.findElements(By.tagName("span"));
		System.out.println("List Item:" + list.size());
		for(WebElement item : list) {
			System.out.println(item);
		}
	}
			
	public void selDropdownMS(WebDriver webDriver, List<WebElement> lstMsControl, WebElement eleMultiSelect,String sMS_Value) {
		List<WebElement> listMSControl = (List<WebElement>) lstMsControl;
		System.out.println("MultiSelect size: "+listMSControl.size());
		eleMultiSelect.click();
		
		try {
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", lstMsControl);
			
			
			
			System.out.println("Expected values:" + sMS_Value);
			String arrValues[]=sMS_Value.split(",");
			Thread.sleep(1000);
			;
			Thread.sleep(1000);
			for(int i=0;i<arrValues.length;i++) {
				for (WebElement option : listMSControl) {
					System.out.println("Option is: " + option.getText());
					if (arrValues[i].equalsIgnoreCase(option.getText().trim())) {
						option.click();
					}
				}
			}
			eleMultiSelect.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.out.println(e.getCause());
			Assert.fail("MultiSelection Failed");
		}
		
	}//function end
	
	
	
	
	
	
}
