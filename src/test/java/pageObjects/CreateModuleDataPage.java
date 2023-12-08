package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;
public class CreateModuleDataPage extends BasePage{

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
	
	
	
//	@FindBy(xpath="input[name='crmeditfieldmodule_email']")
	@FindBy(xpath="//input[@type='email']")
	WebElement eletxtEmail;
	
	@FindBy(xpath="//span[@id='select2-selfield_5803-container' and @title='Select An Option']")
	WebElement eleSelectMnuList;
	
	
	@FindBy(xpath="//input[@role='textbox']")
	WebElement eleMnuTextBox;
	
	@FindBy(xpath="//input[@type='number' and @name='crmeditfieldmodule_enteryournumber']")
	WebElement txtEnterYourNumber;
	
	
	@FindBy(xpath="//*[@id='select2-selfield_5803-container']")
	WebElement txtMnuList;
	
	
	@FindBy(xpath="//*[@id='select2-selfield_5803-results' and @role='tree']")
	WebElement eleMnuUl;
	
	@FindBy(xpath="//div[@class='form-actions right']//button[@type='submit'][normalize-space()='Save']")
	WebElement btnSave;
	
	@FindBy(xpath="//a[normalize-space()='Close']")
	WebElement btnClose;
	
	//get Methods
	public void selectMenuValue(String sValue) {
		UtilityCustomFunctions.selectOneItemfromListBox(driver, eleMnuUl, sValue);
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
	//Select,Set Values
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
	//isMethods
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
			
	
	
	
	
	
	
}
