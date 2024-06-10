package pageObjects;
import java.io.File;
import java.io.IOException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;

import testBase.BaseClass;

import utilities.UtilityCustomFunctions;

public class IndvControlsPage extends BasePage{
	public IndvControlsPage(WebDriver driver) {
		super(driver);
	}
		//public static WebElement eleCurrDate;
		public static List<WebElement> tcols;
		
		
		@FindBy(xpath="(//label[normalize-space()='Select an option..!']")
		WebElement eleMultiChoiceTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Please type an answer ..!']")
		WebElement eleTextQTitle;
		
		@FindBy(xpath="(//label[normalize-space()='What is your email address?']")
		WebElement eleEmailTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Please type your phone number']")
		WebElement elePNumberTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Book an appointment / meeting']")
		WebElement eleApptTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Select your choices..!")
		WebElement eleMultiSelectTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Type and select your answer..!")
		WebElement eleListTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Enter the Number?")
		WebElement eleNumberTitle;
		
		@FindBy(xpath="(//label[normalize-space()='How was your experience?")
		WebElement eleRangeTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Select a range")
		WebElement eleRatingTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Choose a number?")
		WebElement eleOSTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Select a date")
		WebElement eleDTTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Upload file")
		WebElement eleUFTitle;
		
		@FindBy(xpath="(//label[normalize-space()='Please contact us'")
		WebElement eleLNKTitle;
		
		
		
		@FindBy(xpath="(//label[@class='epsf-ans-ans ng-star-inserted'])[1]")
		WebElement elePhoneNoPrefix;
		
		@FindBy(xpath="(//label[@class='epsf-ans-ans'])[4]")
		WebElement elePhoneNo;
		
		@FindBy(xpath="//div[@id='errorMsg']")
		WebElement eleApptErrMsg;
		
		@FindBy(xpath="//input[@placeholder='Choose Date']")
		WebElement eleChooseDate;
		
		@FindBy(xpath="//*[@id='ui-container']")
		WebElement eleContainer;
		
		@FindBy(xpath="//*[@id='formList']//input")
		WebElement txtBuildName;
		
		@FindBy(xpath="//input[contains(@class,'mat-datepicker-input dateshow')]")
		WebElement txtDate;
		
		@FindBy(xpath="//label[normalize-space()='Please contact us']/parent::div/following-sibling::label/div")
		List<WebElement> eleRespLinks;
		
		@FindBy(xpath="//label[normalize-space()='Select the picture…!']/parent::div/following-sibling::label//label[2]")
		WebElement elePictMsgResp;
		
		@FindBy(xpath="//label[normalize-space()='Select the picture…!']/parent::div/following-sibling::label/label")
		WebElement eleSkippedPictMsg;
		
		@FindBy(xpath="//table[@role='presentation']/tbody/tr/td[contains(@class,'active')]")
		WebElement eleDay;
		
		//WebElements
		@FindBy(xpath="//*[@id='rootdiv']/div/h3")
		WebElement lblMessageTitle;
		
		@FindBy(xpath="//*[@id='rootdiv']/p/button/span")
		WebElement btnMessageNext;
		
		//Multi Choice WebElement
		
		@FindBy(xpath="//span[@class='dropdown-btn']")
		WebElement btnDropDown;
				
		@FindBy(xpath="//*[@id='rootdiv']/div/h3")
		WebElement lblMutiChoiceTitle;
		
		@FindBy(xpath="//*[@id='rootdiv']/div/p/span")
		WebElement lblNewMutiChoiceTitle;
		
		@FindBy(xpath="//*[@id='rootdiv']/ul")
		WebElement SelectOneChoice;
		
		@FindBy(xpath="//button[normalize-space()='next']")
		WebElement btnGeneralNext;
		
		@FindBy(css="div[class='ql-editor'] h3")
		WebElement lblGeneralTitle;
		
		@FindBy(xpath="//*[@id='textquestion_textarea']")
		WebElement txtTextQuestionInput;
	
		@FindBy(xpath="	//input[@id='ems_email']")
		WebElement txtEmailInput;
	
		@FindBy(xpath="//input[@id='phs_phonenumber']")
		WebElement txtPhoneNumberInput;
		
		
		@FindBy(xpath="//my[@class='ng-star-inserted']")
		WebElement btnPhoneNumberNext;
		
//		@FindBy(xpath="//button[@class='appintshow-input myanimation']")
//		WebElement btnBookAppointment;
		
		
		@FindBy(xpath="//button[@type='text']")
		WebElement btnBookAppointment;
		
		@FindBy(css="svg")
		WebElement btnApptDateIcon;
		
		
		@FindBy(xpath="//input[@placeholder='Choose Date']")
		WebElement btnChooseDate;
		
		@FindBy(xpath="(//*[name()='path'])[1]")
		WebElement btnSelectDate;
		
		@FindBy(xpath="//*[@id='mat-dialog-0']/app-timelist/div/div[2]/div/mat-datepicker-toggle/button")
		WebElement btnDateAgain;
		
		@FindBy(xpath="//div[contains(@class,'today')]")
		WebElement btnCurrentDate;
		
		@FindBy(xpath="//table[@class='mat-calendar-table']")
		WebElement WbTblAppointMent;
		
		@FindBy(css="button[type='text']")
		WebElement btnAppointmentTime;
		
		
		@FindBy(xpath="//table[@role='presentation']/tbody/tr")
		WebElement eleWbCalendarTbl;

		
		@FindBy(xpath="//div//h3/span")
		WebElement lblSecGenTitle;
		
		@FindBy(xpath="//div//h3")
		WebElement lblTitleWithStar;
		
		@FindBy(xpath="//div//h3")
		WebElement lblPicMsgTitle;
		
		@FindBy(xpath="//div//h3")
		WebElement lblThirdGenTitle;
		
		//@FindBy(css="ul[class='item2']")
		@FindBy(css="ul[class='item2']")
		WebElement lstMultiSelectValues;
		
		@FindBy(xpath="(//ul)[1]")
		WebElement lstListValue;
		
		@FindBy(xpath="//ul[@class='linkshow-container float']")
		WebElement btnUlLinks;
		
		@FindBy(xpath="//input[@type='text']")
		WebElement txtListControl;
		
		
		@FindBy(xpath="//input[@id='numbershow']")
		WebElement txtNumberInput;
		
//		@FindBy(xpath="//input[@id='rangeinput']")
//		WebElement eleRangeSlider;
		
		@FindBy(xpath = "//mat-slider[@role='slider']")
		WebElement eleRangeSlider;
		
		@FindBy(xpath="//div//ul")
		WebElement lstElement;
		
		@FindBy(xpath="//ul[@class='tl-container tl-float']")
		WebElement lstSectionElement;
		
		@FindBy(xpath="//app-timelist//div")
		WebElement lstSecElement;
		
		
		@FindBy(xpath="//iframe[@title='Form preview']")
		WebElement iFramePreview;
		
		
		@FindBy(xpath="//button[@aria-label='Next month']")
		WebElement btnNextMonth;
		
		@FindBy(xpath="//div[contains(@class,'msshow-container float')]")
		WebElement lstRatingLabels;
		
		
//		@FindBy(xpath="//button[@type='button']")
//		@FindBy(xpath="//i[@class='material-icons color_icon'][normalize-space()='date_range']")
		
		@FindBy(xpath="//input[contains(@class,'mat-datepicker-input')]")
		WebElement btnDateIcon;
		
		@FindBy(id="dateValue")
		WebElement btnDateInput;
		
		@FindBy(xpath="//input[@id='datebox']")
		WebElement btnSectionDateInput;
		
		
		@FindBy(xpath="//div[@id='rootdiv']//input")
		WebElement txtDateInput;
		//FileUpload
		@FindBy(xpath="//div[@class='fileupshow-body-div']")
		WebElement lnkFileUpload;
		
		@FindBy(xpath="//ul[@class='linkshow-container float']")
		WebElement btnLinksControl;
		
		@FindBy(css="div#rootdiv>p>button")
		WebElement btnGo;
		
		
		@FindBy(xpath="//label[@class='picmsgshow-valuelbl']")
		//List<WebElement> sLstPicMsgItems = driver.findElements(By.xpath("//label[@class='picmsgshow-valuelbl']"));
		List<WebElement> sLstPicMsgItems;
		//WebElement lstItemLabels;
		
		@FindBy(xpath="//div[@data-flg='1']")
		WebElement btnYes;
		
		@FindBy(xpath="//div[@data-flg='0']")
		WebElement btnNo;
		
		
		@FindBy(xpath="//div[@id='agreement_txt']")
		WebElement lblAgreementText;
		
		@FindBy(id="agg_check_btn")
		WebElement btnCheckAgreement;
		
		@FindBy(xpath="//table[@class='table-rscss']")
		WebElement btnRankMatrixTbl;
		
		//@FindBy(xpath="//i[normalize-space()='mic']")
		@FindBy(xpath="//*[contains(@id,'playoption')]/li[1]/i")
		WebElement btnVRMic;
		
		//@FindBy(xpath="//*[@id='playoption']/li[2]/i")
		@FindBy(xpath="//*[contains(@id,'playoption')]/li[2]/i")
		WebElement btnVRStop;
		
		@FindBy(xpath="//input[contains(@class,'gmshow-input myanimation')]")
		WebElement txtGoogleMapInput;
		
		
		@FindBy(xpath="p[class='ql-align-left'] span")
		WebElement lblSecMsgcontrol;
		
		@FindBy(xpath="//div[@class='epsf-ans-mydata']//table//td//span")
		List<WebElement> lstRankMatrixResp;
		
		
		List<WebElement> lstMsControl = driver.findElements(By.xpath("//*[@id='rootdiv']//ul//li/div"));
		
		//@FindBy(xpath="//span[@class='dropdown-btn']")
		//WebElement eleDropDown = driver.findElement(By.className("dropdown-btn"));
		@FindBy(className="//span[@class='dropdown-btn']")
		WebElement btnMultiSelectDropDown;
		
		@FindBy(xpath="//*[@id='ssIFrame_google']")
		WebElement iFrameForClose;
		
		@FindBy(xpath="//button[contains(@class,'fp-close')]")
		WebElement lnkCloseResponse;
		
		
		@FindBy(xpath="//button[normalize-space()='skip']")
		WebElement btnSkip;
		
		@FindBy(xpath="//button[normalize-space()='submit']")
		WebElement btnSubmit;
		
		@FindBy(xpath="//*[@id='sidebar_footer']/button/label")
		WebElement btnLogout;
		
		@FindBy(xpath="//div[@class='secshow-rootdiv']")
		WebElement eleSectionControl;
		
		
		
		@FindBy(xpath="//*[@id='rootdiv']//div//p/img")
		WebElement eleThankUImage;
		
		@FindBy(xpath="//*[@id='rootdiv']/p/button")
		WebElement btnThankYouGo;
		
		@FindBy(xpath="//*[@id='rootdiv']/ul[@class='opnscal-container float']")
		WebElement eleOpinionScale;
		
		@FindBy(xpath="//span[normalize-space()='Please contact us']")
		WebElement eleLinks;
		
		@FindBy(xpath="//ul[@class='linkshow-container float']/li")
		List<WebElement> linkButton;
		
		public boolean bIsControlTitleVisible(String sLabelText) {
			boolean bFlag = false;
			try {
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);	
			String sXpath = "//label[normalize-space()='" + sLabelText + "']";
			UtilityCustomFunctions.logWriteConsole(sXpath);
			WebElement eleControlTitle = driver.findElement(By.xpath(sXpath));
			bFlag = IsElementVisible(driver,eleControlTitle);
			driver.switchTo().defaultContent();
			}catch(Exception e) {
				bFlag = false;
			}
			return bFlag;
		}
		public boolean bIsMultChoiceDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,eleMultiChoiceTitle);
			return bFlag;
		}
		
		
		public boolean bIsLinksControlDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,eleLinks);
			return bFlag;
		}
		public boolean bIsOpinionScaleDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,eleOpinionScale);
			return bFlag;
		}
		public boolean bIsRatingDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,lstRatingLabels);
			return bFlag;
		}
		public boolean bIsThankYouDisplayed() {
			boolean bFlag1 = false;
			boolean bFlag2 = false;
			boolean bFlag = false;
			bFlag1 = IsElementVisible(driver,eleThankUImage); 
			bFlag2 = IsElementVisible(driver,btnThankYouGo);
			System.out.println("bflag1:" + bFlag1);
			System.out.println("bflag2:" + bFlag2);
			if(bFlag1==true && bFlag2==true) {
				bFlag = true;
			}
			else {
				bFlag = false;
			}
			return bFlag;
		}	
		public void clickLogout() {
			if(btnLogout.isDisplayed()) {
				btnLogout.click();
			}
		}
		
		public boolean bIsMultiChoiceDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,SelectOneChoice); 
			return bFlag;
		}
		public boolean bIsPictureMessageDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementsVisible(driver,sLstPicMsgItems); 
			return bFlag;
		}
		
		public boolean bIsMultiSelectDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,btnDropDown); 
			return bFlag;
		}
		
		
		public boolean bIsMessageTitleDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,lblMessageTitle); 
			return bFlag;
		}
		
		public boolean bIsAppointmentDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,btnBookAppointment); 
			return bFlag;
		}
		public boolean bIsNumberDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtNumberInput); 
			return bFlag;
		}
		public boolean bIsTextQuestionDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtTextQuestionInput); 
			return bFlag;
		}
		public boolean bIsRankMatrixDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,btnRankMatrixTbl); 
			return bFlag;
		}
		public boolean bIsRangeDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,eleRangeSlider); 
			return bFlag;
		}
		public boolean bIsEmailDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtEmailInput); 
			return bFlag;
		}
		
		public boolean bIsYesorNoDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,btnYes); 
			return bFlag;
		}
		
		
		public boolean bIsGoogleMapDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtGoogleMapInput); 
			return bFlag;
		}
		public boolean bIsPhoneNoDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtPhoneNumberInput); 
			return bFlag;
		}
		public boolean bIsListDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtListControl); 
			return bFlag;
		}
		
		public boolean bIsFileUploadDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,lnkFileUpload); 
			return bFlag;
		}
		
		// isDisplayed
		public boolean bIsSectionDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,eleSectionControl); 
			return bFlag;
		}
		public boolean bIsAgreementDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,lblAgreementText); 
			return bFlag;
		}
		public boolean bIsDateDisplayed() {
			boolean bFlag = false;
			bFlag = IsElementVisible(driver,txtDateInput); 
			return bFlag;
		}
		
		
		public boolean bIsVRDisplayed() {
			boolean bFlag1,bFlag2,bFlag = false;
			bFlag1 = IsElementVisible(driver,btnVRMic); 
			bFlag2 = IsElementVisible(driver,btnVRStop);
			if(bFlag1==true && bFlag2==true) {
				bFlag = true;
			}
			else {
				bFlag = false;
			}	
			return bFlag;
		}
		
		public boolean IsElementVisible(WebDriver driver,WebElement element) {
			boolean bIsElementVisible=false;
			try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			wait.until(ExpectedConditions.visibilityOf(element));
			js.executeScript("arguments[0].scrollIntoView();", element);
			bIsElementVisible = true;
			System.out.println(element.getTagName() + " Exist" + " True");
			}catch(Exception e) {
				bIsElementVisible = false;
				System.out.println(element.getTagName() + " Exist" + " False");
			}
			return bIsElementVisible;
		}
		public boolean IsElementsVisible(WebDriver driver,List<WebElement> element) {
			System.out.println("List Rank Matrix: " + element.size());
			boolean bIsElementVisible=false;
			try {
				System.out.println("Before List element size");
				int size = element.size();
			System.out.println("After  list element size " + size);
			if(size>0) {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].scrollIntoView();", element);
			bIsElementVisible = true;
			}
			else {
				bIsElementVisible = false;
			}
			}catch(Exception e) {
				bIsElementVisible = false;
			}
			return bIsElementVisible;
		}
		public boolean bSkipPresent() {
			boolean bSkip = false;
			try {
			 bSkip = btnSkip.isDisplayed();
			}catch(Exception e) {
				bSkip=false;
			}
			return bSkip;
		}
		
		public boolean bEmailPresent() {
			boolean bEmail = false;
			try {
				bEmail = txtEmailInput.isDisplayed();
			}catch(Exception e) {
				bEmail=false;
			}
			return bEmail;
		}
	
		
		
		public void clickSkip() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnSkip);
		}
		
		public void clickResponseClose(WebDriver driver) {
//			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
//			driver.switchTo().frame(iframe1);
			driver.switchTo().defaultContent();
			lnkCloseResponse.click();
			
			
		}
		public void clickDate() throws Exception {
			UtilityCustomFunctions.logWriteConsole("Before Clicking Date control");
			utilities.UtilityCustomFunctions.doClick(driver, txtDate);
			UtilityCustomFunctions.logWriteConsole("After Clicking Date control");
		}
		
		//Get Methods
		public String getPictMsgValue() throws Exception {
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);	
			String sGetText = "";
			sGetText = UtilityCustomFunctions.getValue(driver, elePictMsgResp);
			driver.switchTo().defaultContent();
			return sGetText;
		}
		public String getSkipPictMsg() throws Exception {
			String sGetText = "";
			try {
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);	
			sGetText = UtilityCustomFunctions.getValue(driver, eleSkippedPictMsg);
			driver.switchTo().defaultContent();
			}catch(Exception e) {
				sGetText = null;
			}
			return sGetText;
		}
		
		public String getResponseValue(String sTitleLabel) throws Exception {
			String sGetText = "";
			String sXpath = "";
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);	
			try {
			sXpath = "//label[normalize-space()='" + sTitleLabel + "']/parent::div/following-sibling::label/label"; 
			WebElement eleSingleLabel = driver.findElement(By.xpath(sXpath));
			sGetText = UtilityCustomFunctions.getValue(driver, eleSingleLabel);
			}catch(Exception e) {
				UtilityCustomFunctions.logWriteConsole(e.getMessage());
				sGetText="";
			}
			driver.switchTo().defaultContent();
			return sGetText;
		}
		public String getResponseValues(String sTitleLabel) throws Exception {
			String sGetText = "";
			String sXpath = "";
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);	
			try {
			sXpath = "//label[normalize-space()='" + sTitleLabel + "']/parent::div/following-sibling::label/label";
			
			List<WebElement> eleLabels =driver.findElements(By.xpath(sXpath));
			for(int i=0;i<eleLabels.size();i++) {
				sGetText = sGetText + " " + UtilityCustomFunctions.getValue(driver, eleLabels.get(i)); 
			}
			}catch(Exception e) {
				UtilityCustomFunctions.logWriteConsole(e.getMessage());
				sGetText="";
			}
			driver.switchTo().defaultContent();
			return sGetText.trim();
		}
		
		public String getRespPhoneNo() {
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);
			String sPhoneNo = elePhoneNo.getText();
			driver.switchTo().defaultContent();
			return sPhoneNo;
		}
		public String getPicMsgTitle() {
			String sSFMessage= lblPicMsgTitle.getText();
			return sSFMessage;
		}
		
		public ArrayList<String> ArraySplitList(String sExpLinks){
			ArrayList<String> arrList = new ArrayList<String>();
			String sArrayValues[] = sExpLinks.split(",");
			for(int i=0;i<sArrayValues.length;i++) {
				arrList.add(sArrayValues[i]);
			}
			
			return arrList;
		}
		public ArrayList<String> arrMyListElements(List<WebElement> eleListElements){
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);
			ArrayList<String> arrList = new ArrayList<String>();
			int iCount = eleListElements.size();
			for(int i=0;i<iCount;i++) {
				arrList.add(eleListElements.get(i).getText());
			}
			driver.switchTo().defaultContent();
			return(arrList);
		}
		
		
		
		public String getSectIndvMsg() {
			String sSFMessage = lblSecMsgcontrol.getText();
			return sSFMessage;
		}
		public String getIndvPhonePrefix() {
			WebElement iframe1 = driver.findElement(By.xpath("(//iframe[@title='Form preview'])[1]"));
			driver.switchTo().frame(iframe1);
			String sPhonePrefix = elePhoneNoPrefix.getText();
			driver.switchTo().defaultContent();
			return sPhonePrefix;
		}
		public String getAgreementMsg() {
			String sSFMessage = lblAgreementText.getText();
			return sSFMessage;
		}
		public String getSFMessage() {
			String sSFMessage = lblMessageTitle.getText();
			return sSFMessage;
		}
		
		public String getMultiChoiceTitle() {
			String sSFMessage = lblMutiChoiceTitle.getText();
			return sSFMessage;
		}
		public String getGeneralTitle() {
			String sSFMessage = lblGeneralTitle.getText();
			return sSFMessage;
		}
		public String getSecGenTitle() {
			String sSFMessage = lblSecGenTitle.getText();
			return sSFMessage;
		}
		public String getHeadThreeTitle() {
			String sSFMessage = lblTitleWithStar.getText();
			return sSFMessage;
		}
		
		public String getThirdGenTitle() {
			String sSFMessage = lblThirdGenTitle.getText();
			return sSFMessage;
		}
		public String getBtnApptMsg() {
			String sSFMessage = btnAppointmentTime.getText();
			return sSFMessage;
		}
		public static int getTimeSlots(WebElement lstElement) {
			List<WebElement> options = lstElement.findElements(By.tagName("li"));
			return options.size();
		}
		public String getDateSelected() {
			return txtDate.getAttribute("value");
		}
		
		
	//Click Methods
		public void clickMic() throws Exception {
//			utilities.UtilityCustomFunctions.doClick(driver,btnVRMic);
			btnVRMic.click();
		}
		public void clickStop() throws Exception {
//			utilities.UtilityCustomFunctions.doClick(driver,btnVRStop);
			btnVRStop.click();
			
		}
		public void clickYesorNo(String strItem) throws Exception {
			if(strItem.equals("Yes")) {
				utilities.UtilityCustomFunctions.doClick(driver,btnYes);
			}
			else {
				utilities.UtilityCustomFunctions.doClick(driver,btnNo);
			}
		}
	public boolean clickMatchingLabel(String strItem) throws Exception {
			boolean bItemRating = false;
			bItemRating = clickMatchItem(strItem,lstRatingLabels);
			return bItemRating;
	}		
		 public  boolean clickMatchItem(String strItem,WebElement lstLabelDivs) throws InterruptedException {
			 	
			 	boolean bRating = false;
			 	Thread.sleep(1000);
				List<WebElement> sMatchItems = lstLabelDivs.findElements(By.tagName("label"));
				UtilityCustomFunctions.logWriteConsole("Rating Lable Count: " +sMatchItems.size());
				Thread.sleep(1000);
				for(WebElement item: sMatchItems) {
					if(strItem.equalsIgnoreCase(item.getText().trim())) {
						System.out.println("Matched item:"+ item.getText());
						item.click();
						bRating = true;
						break;
					}

				}//for loop
				return bRating;
				
				
			}//method
		 
		
		public void clickMessageNext() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnMessageNext);
		}
		public void clickGeneralNext() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnGeneralNext);
		}
		public void clickSubmit() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnSubmit);
		}
		
		public void clickPhoneNumberNext() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnPhoneNumberNext);
		}
		
		public void clickMSDropDown() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, btnMultiSelectDropDown);
		}
		public void clickListInput() throws Exception {
			utilities.UtilityCustomFunctions.doClick(driver, txtListControl);
		}
		public  static void clickFirstItem(WebElement lstElement) throws Exception {
			List<WebElement> Slots = lstElement.findElements(By.tagName("li"));
			Thread.sleep(1000);
			for(WebElement Slot: Slots) {
				System.out.println("Time slot selected: " + Slot.getText());
				Thread.sleep(1000);
				Slot.click();
				Thread.sleep(1000);
//				utilities.UtilityCustomFunctions.doClick(driver, Slot);
				
				break;
			}
		}
		
		
		public void setDropdownMS(String sExpValue) {
			WebElement eleDropDown = driver.findElement(By.xpath("//span[@class='dropdown-btn']"));
//			List<WebElement> lstMsCoontrol = driver.findElements(By.xpath("//*[@id='rootdiv']//ul//li/div"));
			List<WebElement> lstMsCoontrol = driver.findElements(By.xpath("//li[@class='multiselect-item-checkbox ng-star-inserted']"));
			
			selectMS(driver, lstMsCoontrol, eleDropDown,sExpValue);
		}

		
		public static void selectMS(WebDriver webDriver, List<WebElement> lstMsCoontrol, WebElement eleDropDown,String sMS_Value) {

			
			try {
				//((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", lstMsCoontrol);
				
				eleDropDown.click();
				System.out.println(lstMsCoontrol.size());
				System.out.println("Expected values:" + sMS_Value);
				String arrValues[]=sMS_Value.split(",");
				
				UtilityCustomFunctions.logWriteConsole("MultiSelect Drop Down Button Clicked");
				UtilityCustomFunctions.logWriteConsole("Expected MultiSelect Value count: " + arrValues.length);
				UtilityCustomFunctions.logWriteConsole("Items in Multiselect Count: " + lstMsCoontrol.size());
				
				Thread.sleep(1000);
				for(int i=0;i<arrValues.length;i++) {
					for (WebElement option : lstMsCoontrol) {
						
						Thread.sleep(1000);
						System.out.println("Option is: " + option.getText());
						if (arrValues[i].trim().equalsIgnoreCase(option.getText().trim())) {
							UtilityCustomFunctions.logWriteConsole("Item Selected in MultiSelect: " + option.getText());
							option.click();
							Thread.sleep(1000);
							eleDropDown.click();
						}
					}
				}
				eleDropDown.click();
				eleDropDown.sendKeys(Keys.ENTER);
				
			} catch (Exception e) {
				System.out.println(e.getCause());
				Assert.fail("MultiSelection Failed");
			}
			
		}//function end
		//@SuppressWarnings("deprecation")
		public static void selDropdownMS(WebDriver webDriver, List<WebElement> lstMsControl, WebElement eleDropDown,String sMS_Value) {
			List<WebElement> listMSControl = (List<WebElement>) lstMsControl;
			System.out.println("MultiSelect size: "+listMSControl.size());
			eleDropDown.click();
			
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
				eleDropDown.sendKeys(Keys.ENTER);
			} catch (Exception e) {
				System.out.println(e.getCause());
				Assert.fail("MultiSelection Failed");
			}
			
		}//function end

		
		public boolean selectOneItem(String strItem) throws Exception{
			boolean bFlag = false;
			System.out.println("SelectOneChoice:" + SelectOneChoice);
			bFlag = utilities.UtilityCustomFunctions.selectOneItemfromListBox(driver, SelectOneChoice, strItem);
			return bFlag;
		}
		public void selectListItem(String strItem) throws Exception{
			
			utilities.UtilityCustomFunctions.selectOneItemfromListBox(driver, lstListValue, strItem);
		}
		
		
		
		
		
		public void selectMultiItems(String strValues) throws Exception{
			utilities.UtilityCustomFunctions.selectMultiItemfromListBox(driver, lstMultiSelectValues, strValues);
		}
		public void clickLinks(String strLinks) throws Exception{
			utilities.UtilityCustomFunctions.clickLinkItems(driver, btnLinksControl, strLinks);
		}
		public void setTextAnswer(String sTextQuestion) throws Exception{
			utilities.UtilityCustomFunctions.sendKeys(driver, txtTextQuestionInput, sTextQuestion);
		}
		public void setEmail(String sEmail) throws Exception{
			utilities.UtilityCustomFunctions.sendKeys(driver, txtEmailInput, sEmail);
		}
		public void setPhoneNumber(String sPhoneNumber) throws Exception{
			System.out.println("PhoneNumber in set: " + sPhoneNumber);
			utilities.UtilityCustomFunctions.sendKeys(driver, txtPhoneNumberInput, sPhoneNumber);
		}
		
		public void setNumber(String sNumber) throws Exception{
			utilities.UtilityCustomFunctions.sendKeys(driver, txtNumberInput, sNumber);
		}
		
		
		

		
		
		
		
		
		
}
