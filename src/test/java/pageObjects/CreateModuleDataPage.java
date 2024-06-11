package pageObjects;

import java.io.File;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;
public class CreateModuleDataPage extends BasePage{
	public static List<WebElement> objLists;
	public CreateModuleDataPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//table[contains(@class,'table table-striped')]//tbody/tr[2]/td[3]")
	WebElement eleOpenNotifyFirstItem;
	
	@FindBy(xpath="//span[@id='select2-assign_to-1y-container']")
	WebElement eleSelectAssignedTo;
	
	@FindBy(id="select2-assign_to-r1-container")
	WebElement eleClickAssignedTo;
	
	@FindBy(xpath="(//select[@class='monthselect'])[1]")
	WebElement eleDateMonthSelect;

	@FindBy(xpath="(//select[@class='monthselect'])[3]")
	WebElement eleDTMonthSelect;
	
	@FindBy(xpath="//div[@class='drp-calendar left single']//select[@class='yearselect']")
	WebElement eleYearSelect;
	
	@FindBy(xpath="//input[@name='crmeditfieldmodule_phonenumber']")
	WebElement eletxtPhoneNumber;
	
	@FindBy(xpath="//input[@type='number' and @name='crmeditfieldmodule_numberfield']")
	WebElement eletxtNumberField;
	
	@FindBy(xpath="//input[@type='tel']")
	WebElement eletxtMobileNumber;
	
	@FindBy(xpath="//input[@type='email']")
	WebElement eletxtEmail;
	
	@FindBy(xpath="//span[@id='select2-selfield_5803-container' and @class='select2-selection__rendered']")
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
	
	@FindBy(xpath="//ul[contains(@class,'select2-selection__rendered')]")
	WebElement txtMultiComboBox;
	
//	@FindBy(xpath="//span[@class='select2-selection select2-selection--multiple']")
//	WebElement txtMultiComboBox;
	
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
	
	@FindBy(xpath="//span[@id='select2-selfield_6068-container']")
	WebElement eleSchedEnqCatg;
	
	
	
	
	@FindBy(xpath="(//i[contains(@class,'fa fa-edit')])[1]")
	WebElement eleEllipsisEdit;
	
	@FindBy(xpath="//button[normalize-space()='edit_square']")
	WebElement eleEditButton;
	
	@FindBy(xpath = "//ul[contains(@class,'select2-selection__rendered')]/li/span")
	List<WebElement> lstMultiCombo;
	
	
	@FindBy(xpath = "//i[@class='fa fa-search searchicon']")
	WebElement btnRelativeSearch;
	
	@FindBy(xpath = "//div[@class='calendar-time']/select[@class='hourselect']") 
	WebElement eleDTHour;
	
	@FindBy(xpath = "//div[@class='calendar-time']/select[@class='minuteselect']") 
	WebElement eleDTMinute;
	
	@FindBy(xpath = "//div[@class='calendar-time']/select[@class='secondselect']") 
	WebElement eleDTSecond;
	
	@FindBy(xpath = "//div[@class='calendar-time']/select[@class='ampmselect']") 
	WebElement eleDTAMPM;
	
	
	public void clickRelativeSearch() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnRelativeSearch);
	}
	public void clickMultiComboDropDown() throws Exception {
		UtilityCustomFunctions.doClick(driver, txtMultiComboBox);
	}
	public void SelectRelModValue(String sRelModText) throws Exception {
		Thread.sleep(5000);
		RelatedModulePage objRMP = new RelatedModulePage(driver);
		clickRelativeSearch();
		Thread.sleep(3000);
		String currentWindow = driver.getWindowHandle();
		Set<String> sWinHandles = driver.getWindowHandles();
		for(String window:sWinHandles) {
			driver.switchTo().window(window);
			Thread.sleep(3000);
			if(!window.equals(currentWindow)) {
				objRMP.SelectRelatedModuleRow(sRelModText);
				Thread.sleep(3000);
				UtilityCustomFunctions.logWriteConsole("Related Module Value Selected");
//				driver.close();
				Thread.sleep(3000);
			}
		}
		driver.switchTo().window(currentWindow);
	}
	public void SelectCurrMonthInDate() throws Exception {
		CRMReUsables objCRMRs = new CRMReUsables();
		Thread.sleep(1000);
//		eleMonthSelect.click();
		UtilityCustomFunctions.doClick(driver, eleDateMonthSelect);
		Thread.sleep(2000);
		UtilityCustomFunctions.selectFromComboBox(driver, eleDateMonthSelect, objCRMRs.getTodayMonth());
	}
	public void SelectCurrMonthInDT() throws Exception {
		CRMReUsables objCRMRs = new CRMReUsables();
		Thread.sleep(1000);
//		eleMonthSelect.click();
		UtilityCustomFunctions.doClick(driver, eleDTMonthSelect);
		Thread.sleep(2000);
		UtilityCustomFunctions.selectFromComboBox(driver, eleDTMonthSelect, objCRMRs.getTodayMonth());
	}
	
	public void SelectCurrYear() throws InterruptedException {
		CRMReUsables objCRMRs = new CRMReUsables(); 
		eleYearSelect.click();
		Thread.sleep(1000);
		String sYear =  Integer.toString(objCRMRs.getCurrentYear());
		UtilityCustomFunctions.selectFromComboBox(driver, eleYearSelect, sYear);
	}
	
	public void fRemMultiComboValues() throws InterruptedException {
		
		System.out.println("Inside remove existing values:");
		int iCount = lstMultiCombo.size();
		System.out.println("Multicombo: count" + iCount);
		if(iCount>0) {
			for(int i = iCount-1;i>=0;i--) {
				System.out.println("iCount Value within each iteration:" + i + ":"+ iCount);
				Thread.sleep(3000);
//				txtMultiComboBox.click();
				lstMultiCombo.get(i).click();
				Thread.sleep(1000);
			}
				
		}
		else {
			txtMultiComboBox.click();
		}
	}
	
	public void clickMultiComboBox() throws Exception {
		UtilityCustomFunctions.doClick(driver, txtMultiComboBox);
	}
	public void clickAssignedTo() throws Exception {
		UtilityCustomFunctions.doClick(driver, eleClickAssignedTo);
	}
	
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
	
	public void clickLatestNotification() throws Exception {
		try {
			UtilityCustomFunctions.doClick(driver, eleOpenNotifyFirstItem);
		}catch(Exception e) {
			System.out.println(e.getCause());
		}
	}
	public void SelectMonthandYear(String sControlType,String sMonth,String sYear) throws InterruptedException {
		String sXpath="";
		String sYXpath="";
		switch(sControlType) {
		case "sDate":
			sXpath = "(//select[@class='monthselect'])[1]";
			sYXpath = "(//select[@class='yearselect'])[1]";
		break;
		case "sDateandTime":
			sXpath = "(//select[@class='monthselect'])[3]";
			sYXpath = "(//select[@class='yearselect'])[3]";
		break;
		case "sEnquiryDate":
			sXpath = "(//select[@class='monthselect'])[3]";
			sYXpath = "(//select[@class='yearselect'])[3]";
		break;
		}
		Thread.sleep(1000);		
		WebElement eleMonth = driver.findElement(By.xpath(sXpath));
		WebElement eleYear= driver.findElement(By.xpath(sYXpath));
		Thread.sleep(1000);
		UtilityCustomFunctions.selectFromComboBox(driver, eleMonth, sMonth);
		Thread.sleep(1000);
		UtilityCustomFunctions.selectFromComboBox(driver, eleYear, sYear);
			
	}
	public void SelectDayandTime(int iDateIndex,String sHour,String sMinutes,String sSeconds,String sTimeFormat) throws InterruptedException {
		Thread.sleep(1000);
		String sDateXpath="(//td[contains(@class,'today')])[" + iDateIndex + "]";
		UtilityCustomFunctions.logWriteConsole(sDateXpath);
		
		WebElement eleDate = driver.findElement(By.xpath(sDateXpath));
		Thread.sleep(1000);
		eleDate.click();
		Thread.sleep(1000);
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTHour,sHour,"option");
		Thread.sleep(1000);
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTMinute,sMinutes,"option");
		Thread.sleep(1000);
        UtilityCustomFunctions.selectItemfromListBox(driver,eleDTSecond,sSeconds,"option");
        Thread.sleep(1000);
        UtilityCustomFunctions.selectItemfromListBox(driver,eleDTAMPM,sTimeFormat,"option");
        Thread.sleep(1000);
	}
	
	public void clickDayInDate(int iDateIndex,String sControlType,String sTimeHour) throws Exception {
		CRMReUsables objCRMRs = new CRMReUsables();
		String sXpath="";
		String sYXpath="";
		switch(sControlType) {
		case "sDate":
			sXpath = "(//select[@class='monthselect'])[1]";
			sYXpath = "(//select[@class='yearselect'])[1]";
		break;
		case "sDateandTime":
			sXpath = "(//select[@class='monthselect'])[3]";
			sYXpath = "(//select[@class='yearselect'])[3]";
		break;
		case "sEnquiryDate":
			sXpath = "(//select[@class='monthselect'])[3]";
			sYXpath = "(//select[@class='yearselect'])[3]";
		break;
		}
		Thread.sleep(1000);		
		WebElement eleMonth = driver.findElement(By.xpath(sXpath)); 
		
		Thread.sleep(2000);
//		UtilityCustomFunctions.doClick(driver, eleMonth);
		eleMonth.click();
		Thread.sleep(2000);
		String sCurrMonth = objCRMRs.getTodayMonth();
		UtilityCustomFunctions.logWriteConsole("Current Month:" + sCurrMonth);
		Thread.sleep(1000);
		UtilityCustomFunctions.selectFromComboBox(driver, eleMonth, sCurrMonth);
		Thread.sleep(1000);
		WebElement eleYear= driver.findElement(By.xpath(sYXpath));
		eleYear.click();
		Thread.sleep(2000);
		String sYear =  Integer.toString(objCRMRs.getCurrentYear());
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("Year to be selected is:" + sYear);
		UtilityCustomFunctions.selectFromComboBox(driver, eleYear, sYear);
		UtilityCustomFunctions.logWriteConsole("Control Type:" + sControlType);
		Thread.sleep(3000);		
		if(!sControlType.equalsIgnoreCase("sDateandTime")) {
			Thread.sleep(1000);
			String sDateXpath="(//td[contains(@class,'today')])[" + iDateIndex + "]";
			UtilityCustomFunctions.logWriteConsole(sDateXpath);
			WebElement eleDate = driver.findElement(By.xpath(sDateXpath));
			Thread.sleep(2000);
			eleDate.click();
		}
		else {
			Thread.sleep(2000);
//			List<WebElement> sDateRows = driver.findElements(By.xpath("(//table[contains(@class,'table-condensed')])[1]//tr"));
			List<WebElement> sDateRows = driver.findElements(By.xpath("//div[@class='daterangepicker ltr single opensright show-calendar']//div[@class='drp-calendar left single']//table//tr"));
			boolean bFound = false;

//			String sDateXpath="(//td[contains(@class,'today')])[2]";
//			String sCurrDateXPath="//div[@class='daterangepicker ltr single opensright show-calendar']/div[@class='drp-calendar left single']//table//tr//td[contains(@class,'today')]";
//			Index changed back from 2 to 1 while ETRS DateTime: again changed to 2			
//			String sDateXpath="(//td[contains(@class,'today')])[1]";
//			WebElement eleCurrDate = driver.findElement(By.xpath(sCurrDateXPath));
//			String sActCurrDate = eleCurrDate.getText();
//			System.out.println("Current Date In DT Control:" + sActCurrDate);
//			iCurrDate = Integer.parseInt(sActCurrDate);
			 Date currentDate = new Date();
		     int iCurrDate = currentDate.getDate();
		      
			
			Date dMonthDate = new Date();
			@SuppressWarnings("deprecation")
			int iMonthIndex = dMonthDate.getMonth(); 
			Calendar calendar1 = Calendar.getInstance();
			calendar1.set(Calendar.MONTH, iMonthIndex);
			int iMonthLastDay = calendar1.getActualMaximum(calendar1.DAY_OF_MONTH); 
			System.out.println("Last day of current month:" + iMonthLastDay);
			
			int jCounter = 0;
			if(iCurrDate < iMonthLastDay) {
				jCounter=iCurrDate+3;
//				if(jCounter>iMonthLastDay) {
//					jCounter = 2;
//				}
				System.out.println("Select next month");
			}
			else {
				jCounter = iMonthLastDay;
			}
//			List<WebElement> sDateCols = driver.findElements(By.xpath("(//table[@class='table-condensed'])[3]//td"));
			// ETRS Date&Time control index c hanged to 1 
//			List<WebElement> sDateCols = driver.findElements(By.xpath("(//table[@class='table-condensed'])[1]//td"));
			
			List<WebElement> sDateCols = driver.findElements(By.xpath("//div[@class='daterangepicker ltr single opensright show-calendar']/div[@class='drp-calendar left single']//table//tr//td[contains(@class,'today') or contains(@class,'active') or @class='available' or @class='weekend available']"));
			for(int i = 0;i<sDateCols.size();i++) {
				try {
				System.out.println("J Counter: " + jCounter);
				String sDay = sDateCols.get(i).getText();
				System.out.println("sDay Value is: " + sDay);
				if(jCounter==Integer.parseInt(sDay)) {
					System.out.println("J Counter: " + jCounter + "Day is :  " + sDay);
					Thread.sleep(1000);
//					UtilityCustomFunctions.selectFromComboBox(driver, eleMonth, sCurrMonth);
//					Thread.sleep(3000);
//					UtilityCustomFunctions.selectFromComboBox(driver, eleYear, sYear);
//					Thread.sleep(3000);
					sDateCols.get(i).click();
					Thread.sleep(1000);
					System.out.println("Inside if sDay Value is: " + i + "Column: " + sDay + "And sTimeHour: " + sTimeHour);
					
					Thread.sleep(1000);
					if(sTimeHour!=null) {
						UtilityCustomFunctions.selectItemfromListBox(driver,eleDTHour,sTimeHour,"option");
					}else {
						UtilityCustomFunctions.selectItemfromListBox(driver,eleDTHour,"5","option");
					}
                    Thread.sleep(1000);
                    UtilityCustomFunctions.selectItemfromListBox(driver,eleDTMinute,"00","option");
                    Thread.sleep(1000);
                    UtilityCustomFunctions.selectItemfromListBox(driver,eleDTSecond,"00","option");
                    Thread.sleep(1000);
                    UtilityCustomFunctions.selectItemfromListBox(driver,eleDTAMPM,"PM","option");
                    Thread.sleep(1000);
                    bFound = true;
                    break;
				}
				}catch(NumberFormatException e) {
					System.out.println("Value: " + i + " not an Integer");
				}
			}//for loop
			
		}//if sDateandTime
		
	}
	public void fSelectDTHour(String sHour) throws InterruptedException {
		Thread.sleep(1000);		
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTHour,sHour,"option");
	}
	public void fSelectDTMinutes(String sMinutes) throws InterruptedException {
		Thread.sleep(1000);		
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTMinute,sMinutes,"option");
	}
	public void fSelectDTSeconds(String sSeconds) throws InterruptedException {
		Thread.sleep(1000);		
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTSecond,sSeconds,"option");
	}
	public void fSelectDTAMorPM(String sTimeFormat) throws InterruptedException {
		Thread.sleep(1000);		
		UtilityCustomFunctions.selectItemfromListBox(driver,eleDTAMPM,sTimeFormat,"option");
	}
	
	public void clickEnqCategory() throws Exception {
//		UtilityCustomFunctions.doClick(driver, eleEnqCatText);
		UtilityCustomFunctions.doActionClick(driver, eleEnqCatText);
	}
	//get Methods
	public void clickSchedEnqCategory() throws Exception{
		UtilityCustomFunctions.doActionClick(driver, eleSchedEnqCatg);
	}
	public String fGetModuleText(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
		WebElement eleText = driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getText(driver, eleText);
	}
	
	public String fGetModuleValue(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
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
	public void clickTime(String sHours,String sMinues,String sSeconds) throws Exception {
	Actions action = new Actions(driver);
	Thread.sleep(3000);	
	//String xPathHours = "//div[normalize-space()='" +sHours+ "']";
	String  xPathHours = "//div[@class='popover-content']//div[@class='clockpicker-tick' and text()='"+sHours+"']";
	UtilityCustomFunctions.logWriteConsole("Hours:" + xPathHours);
	WebElement eleHours = driver.findElement(By.xpath(xPathHours));
	eleHours.click();
	Thread.sleep(3000);
//	String xPathMinutes = "//div[normalize-space()='" +sMinues+ "']";
	String xPathMinutes = "//div[@class='popover-content']//div[@class='clockpicker-tick' and text()='"+sMinues+"']";
	WebElement eleMinutes = driver.findElement(By.xpath(xPathMinutes));
	eleMinutes.click();
//	UtilityCustomFunctions.doActionClick(driver, eleMinutes);
	Thread.sleep(3000);
//	int  sSeconds = 55;
	String xPathSeconds = "(//div[@class='popover-content']//div[@class='clockpicker-tick' and text()='"+ sSeconds +"'])[2]";
	WebElement eleZeroZero = driver.findElement(By.xpath(xPathSeconds));
	Thread.sleep(2000);
//	action.doubleClick(eleZeroZero).build().perform();
	eleZeroZero.click();
//	UtilityCustomFunctions.doActionClick(driver, eleZeroZero);
	Thread.sleep(2000);
	
	}
	public void clickMultiComboBox(String sMS_Value,boolean IsAmend) throws Exception {
		txtMultiComboBox.click();
		Thread.sleep(1000);
		if(IsAmend==true) {
			fRemMultiComboValues();
		}
		Thread.sleep(1000);
		System.out.println("After get attribute in combo box");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", txtMultiComboBox);
		Thread.sleep(1000);
		System.out.println("Expected values:" + sMS_Value);
		String arrValues[]=sMS_Value.split(",");
		Thread.sleep(1000);
//		System.out.println("Array Lenght: " + arrValues.length);
		String sXpath = "//ul[@class='select2-results__options']//li[text()='"+arrValues[0]+"']";
		System.out.println(sXpath);
//		txtMultiComboBox.click();
		for(int i=0;i<arrValues.length;i++) {
//			txtMultiComboBox.click();
			Thread.sleep(3000);
			System.out.println("Inside outer for loop: expected values");
			Thread.sleep(3000);
			sXpath = "//ul[@class='select2-results__options']//li[text()='"+arrValues[i]+"']";
//			System.out.println(sXpath);
			WebElement eleMultiCombo = driver.findElement(By.xpath(sXpath));
			System.out.println("Multi Combo Print Values " + i + "Values:" + eleMultiCombo.getText());
			UtilityCustomFunctions.doActionClick(driver, eleMultiCombo);
//			UtilityCustomFunctions.doClick(driver, eleMultiCombo);
			Thread.sleep(1000);
			txtMultiComboBox.click();
		}
			txtMultiComboBox.click();
	}
	
	
	
	//click Methods
	public void selectMenuValue(String sValue) {
		UtilityCustomFunctions.selectOneItemfromListBox(driver, eleMnuUl, sValue);
	}
	public void selectListValue(String sValue) {
		UtilityCustomFunctions.selectOneItemfromListBox(driver, eleUlSelect, sValue);
//		String sXpath = "//ul[@class='select2-results__options']//li[text()='" + sValue + "']";
	}
	public void selectListValue2(String sValue) {
		String sXpath = "//ul[@class='select2-results__options']//li[text()='" + sValue + "']";
		driver.findElement(By.xpath(sXpath)).click();
	}
	//click Methods
	public void clickSave() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSave);
	}
	
	public void clickClose() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnClose);
	}
	public void clickSelectControl(String sModuleName,String sFieldLabel) throws Exception {
//		String sXpath = "//select[@name='"+ sModuleName.toLowerCase().trim() + "_" + sFieldLabel +"']";
		String sXpath = "(//span[contains(@id,'select2-selfield')])[5]";
		System.out.println("Select Control xpath:" + sXpath);
		WebElement elecomboxBox = driver.findElement(By.xpath(sXpath));
//		UtilityCustomFunctions.doActionClick(driver, elecomboxBox);
//		UtilityCustomFunctions.doClick(driver, elecomboxBox);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(elecomboxBox)); 
		elecomboxBox.click();
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
	
	public void clickArrayCheckBox(int iObjIndex,String sValue) throws InterruptedException {
		String xPath = "(//input[@type='checkbox'])[" + iObjIndex + "]";  
		WebElement eleCheckBox = driver.findElement(By.xpath(xPath));
		Thread.sleep(1000);
		UtilityCustomFunctions.logWriteConsole("check box value: "+sValue);
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
	String sXpath = "//span[@id='select2-" + sModuleName.toLowerCase().trim() + "_" + sText +"']";
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
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
		System.out.println("Xpath: " + sXpath);
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		eleTextBox.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setInputNumber(String sModuleName,String sText,String sValue) throws Exception {
		String sXpath = "//input[@type ='number' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		eleTextBox.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setGenericInputValue(String sType,String sModuleName,String sText,String sValue) throws Exception {
		String sXpath = "//input[@type ='"+ sType + "' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		eleTextBox.clear();
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	public void setTextAreaValue(String sModuleName,String sText,String sValue) {
		String sXpath="//textarea[@name='"+sModuleName.toLowerCase().trim()+ "_" + sText + "']";
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.sendKeysNoEnter(driver,eleTextBox,sValue);
	}
	
	public void clickDateBox(String sModuleName,String sText) throws Exception {
		String sXpath = "//input[@type ='text' and @name='" + sModuleName.toLowerCase().trim()+"_"+sText+"']";
		System.out.println("Date Box Xpath: " + sXpath);
		WebElement eleTextBox = driver.findElement(By.xpath(sXpath));
		UtilityCustomFunctions.doClick(driver, eleTextBox);
	}
	
	public void setEmailValue(String sModuleName,String sText) {
		String sXpath = "//input[@type ='email' and @name='" + sModuleName.toLowerCase().trim()+"_"+"email']";
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
		List<WebElement> lstOldfiles = driver.findElements(By.xpath("//i[@class='fa fa-remove']"));
		int iCount = lstOldfiles.size();
		if(iCount>0) {
		for(int i=0;i<iCount;i++) {
			lstOldfiles.get(i).click();
			Thread.sleep(1000);
			}
		}
		Thread.sleep(1000);
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
