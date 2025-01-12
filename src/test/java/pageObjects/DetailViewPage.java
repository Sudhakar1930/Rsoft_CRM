package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.UtilityCustomFunctions;
public class DetailViewPage extends BasePage{

	public DetailViewPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	
	@FindBy(xpath="(//div[starts-with(@id,'accordion')]/a)[2]")
	WebElement tabBlockB;
	
	@FindBy(xpath="(//div[starts-with(@id,'accordion')]/a)[3]")
	WebElement tabBlockC;
	
	@FindBy(xpath="//label[@for='cb4']")
	WebElement tglSummary;
	
	@FindBy(xpath="//label[@for='title_toggle']")
	WebElement tglHeaders;
	
	@FindBy(xpath="//input[@id='cb4']")
	WebElement tglChkSummary;
	
	@FindBy(xpath="//input[@id='title_toggle']")
	WebElement tglChkHeaders;
	
	@FindBy(xpath="//*[@id='createtime_CT']")
	WebElement lblCurrentTime;
	
	@FindBy(xpath="//span[@class='label_name']")
	WebElement tglSummaryLabel;
	
	@FindBy(xpath="//h3[@class='card-title']")
	WebElement tglHeaderLabel;
	
	@FindBy(xpath="//button[normalize-space()='add']")
	WebElement btnAddRecord;
	
	@FindBy(xpath="//button[text()='edit_square']")
	WebElement btnEditRecord;
	
	@FindBy(xpath="//button[normalize-space()='content_copy']")
	WebElement btnDuplicate;
	
	
	@FindBy(xpath="//div[@class='col-lg-9']/label")
	WebElement lblSummaryAssignedTo;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[1]")
	WebElement lblPhoneNumberTitle;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[2]")
	WebElement lblEmailTitle;
	
	@FindBy(xpath="(//div[@class='col-md-12']//b)[3]")
	WebElement lblMenuItemTitle;
	
	@FindBy(linkText="Summary")
	WebElement tabSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[1]")
	WebElement lblPhoneNumberSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[2]")
	WebElement lblNumberFieldSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[3]")
	WebElement lblEmailSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[4]")
	WebElement lblMenuItemSummary;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[5]")
	WebElement lblEnterYourNMSummary;
	
	@FindBy(xpath="//a[normalize-space()='Details']")
	WebElement lnkDetailView;
	
	@FindBy(xpath="//a[@class='header_dup_all_module_show_btn']")
	WebElement lnkNavBarModuleName;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[1]")
	WebElement lblAssignedToDetailView;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[2]")
	WebElement lblPhoneNumberDetailView;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[3]")
	WebElement lblNumberFieldDetailView;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[4]")
	WebElement lblEmailDetailView;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[5]")
	WebElement lblMenuItemDetailView;
	
	@FindBy(xpath="(//div[@class='col-lg-10'])[6]")
	WebElement lblEYPNDetailView;
	
	@FindBy(xpath="//span[contains(@class,'comment_text')]")
	WebElement lblUploadFile;
	
	@FindBy(xpath="//span[@data-label='Text']//i[contains(@class,'fa fa-check')]")
	WebElement btnTickSave;
	
	@FindBy(xpath="//span[@data-label='Number Field']//i[contains(@class,'fa fa-check')]")
	WebElement btnNumberTickSave;
	
	@FindBy(xpath="//span[@data-label='SourceText']//i[contains(@class,'fa fa-check')]")
	WebElement btnSourceTickSave;
	
	public String getToggleViewText() {
		try {
		return tglSummaryLabel.getText();
		}catch(Exception e) {
			return "Detail view";
		}
	}
	public String getToggleHeaderText() {
		return tglHeaders.getText();
	}
	public String getCurrentTime() throws Exception {
		return UtilityCustomFunctions.getText(driver, lblCurrentTime);
	}
	//click Methods
	public void fSetToggleHeader(boolean bFlag) {
		if(bFlag==true) {
		if(tglChkHeaders.isSelected()==false) {
			tglHeaders.click();
			UtilityCustomFunctions.logWriteConsole("Header Toggle set  ON");
		}
		else {
			UtilityCustomFunctions.logWriteConsole("Header Toggle Already ON");
		}
		}
		else {
			if(tglChkHeaders.isSelected()==true) {
				tglHeaders.click();
				UtilityCustomFunctions.logWriteConsole("Header Toggle set Off");
			}else {
				UtilityCustomFunctions.logWriteConsole("Header Toggle Already Off");
			}
		}
	}
	public void fSetDetailVew(boolean bFlag) {
		if(bFlag==true) {
		if(tglChkSummary.isSelected()==false) {
			tglSummary.click();
			UtilityCustomFunctions.logWriteConsole("Summary View Set ON");
			}
		else {
			UtilityCustomFunctions.logWriteConsole("Summary View Already ON");
		}
		}
		else {
			if(tglChkSummary.isSelected()==true) {
				tglSummary.click();
			}
			else {
				UtilityCustomFunctions.logWriteConsole("Summary View Already OFF");
			}
		}
	}
	public void fClickDetailBlockB() throws Exception {
		UtilityCustomFunctions.doClick(driver, tabBlockB);
	}
	public void fClickDetailBlockC() throws Exception {
		UtilityCustomFunctions.doClick(driver, tabBlockC);
	}
	public void fdefaultToggleStatus() {
		System.out.println("Toggle Summary default: "+tglChkSummary.isSelected());
		System.out.println("Toggle Headers default: "+ tglChkHeaders.isSelected());
	}
	public void clickSummaryView() throws InterruptedException{
		Thread.sleep(3000);
//		System.out.println("Toggle View: Before Click:" + getToggleViewText());
//		tglSummary.click();
		
//		Thread.sleep(3000);
//		System.out.println("Toggle View: After click:" + getToggleViewText());
		
		Thread.sleep(3000);
		System.out.println("Toggle Header: Before click:" + getToggleHeaderText());
		tglHeaders.click();
		Thread.sleep(3000);
		System.out.println("Toggle Header: After click:" + getToggleHeaderText());
		Thread.sleep(3000);
		
//		System.out.println(tglSummary.getAttribute("checked"));
//		String tglAttribute = tglSummary.getAttribute("innerHTML");
//		System.out.println(tglAttribute);
//		System.out.println(tglSummary.getAttribute("innerText"));
//		System.out.println(tglSummary.getAttribute("value"));
//		System.out.println(tglSummary.getAttribute("class"));
//		tglSummary.click();
//		Thread.sleep(3000);
//		System.out.println("Toggle Text:" + getToggleViewText());
//		tglAttribute = tglSummary.getAttribute("innerHTML");
//		System.out.println(tglAttribute);
//		System.out.println(tglSummary.getAttribute("innerText"));
//		System.out.println(tglSummary.getAttribute("value"));
//		System.out.println(tglSummary.getAttribute("class"));
//		System.out.println(tglSummary.getAttribute("checked"));
//		
	}
	
	public void clickEditRecordItem() throws InterruptedException {
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[1]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		Thread.sleep(2000);
		action.moveToElement(eleArrSummary).perform();
		String sXpath="//table[contains(@class,'table table-bordered')]/tbody[1]/tr[6]/td[2]/div[1]/div[2]/i[1]";
		WebElement eleRecEdit = driver.findElement(By.xpath(sXpath));
		eleRecEdit.click();
	}
	
	public void clickEditNotificationItem() throws InterruptedException {
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[1]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		Thread.sleep(2000);
		action.moveToElement(eleArrSummary).click().perform();
		//String sXpath="//td[@class='rs_col_2']//div[@class='col-lg-2']/i[@data_fieldid='5800']";
		String sXpath = "(//div[contains(@class,'col-lg-2')])[1]";
		WebElement eleRecEdit = driver.findElement(By.xpath(sXpath));
		eleRecEdit.click();
	}
	public void clickEditSMSNotifyEdit() throws InterruptedException {
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[1]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		action.moveToElement(eleArrSummary).click().perform();
		Thread.sleep(2000);
		//String sXpath="//td[@class='rs_col_2']//div[@class='col-lg-2']/i[@data_fieldid='5800']";
		String sXpath2 = "//i[@class='fa fa-edit 5862_icon']";
		WebElement eleRecEdit = driver.findElement(By.xpath(sXpath2));
		eleRecEdit.click();
	}
	
	public void clickSendNotifyEdit() throws InterruptedException {
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[1]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		Thread.sleep(2000);
		action.moveToElement(eleArrSummary).click().perform();
		String sXpath2 = "//i[@class='fa fa-edit 5800_icon']";
		WebElement chkPhoneNUmber = driver.findElement(By.xpath(sXpath2));
		chkPhoneNUmber.click();
		
	}
	public void clickEdtSngFldMod() throws InterruptedException {
		Actions action = new Actions(driver);
		String sXpath1 = "(//div[@class='col-lg-10'])[2]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath1));
		Thread.sleep(2000);
		action.moveToElement(eleArrSummary).perform();
		String sXpath="//i[@class='fa fa-edit 5815_icon']";
		WebElement eleRecEdit = driver.findElement(By.xpath(sXpath));
		eleRecEdit.click();
	}
	public void clickRecItemSave(String sModuleName) throws Exception {
		
		String sXpath="//span[@data-colname='"+sModuleName.toLowerCase()+"_text']//i[contains(@class,'fa fa-check')]";
		WebElement btnSingleLineSave = driver.findElement(By.xpath(sXpath));	
		UtilityCustomFunctions.doClick(driver, btnSingleLineSave);
	}
	public void clickNotifyRecItemSave(String sModuleName,String sControlType) throws Exception {
		
		String sXpath="//span[@data-colname='"+sModuleName.toLowerCase()+"_"+sControlType+"']";
		WebElement btnSingleLineSave = driver.findElement(By.xpath(sXpath));
		Thread.sleep(2000);
		UtilityCustomFunctions.doClick(driver, btnSingleLineSave);
	}
	
	public void clickNMRecItemSave() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnNumberTickSave);
	}
	public void clickRecItemSourceSave() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSourceTickSave);
	}
	
	
	
//	public void clickSummaryView() throws Exception {
//		UtilityCustomFunctions.doClick(driver, tabSummary);
//	}
	public void clickAddRecord() throws Exception {
		UtilityCustomFunctions.doActionClick(driver, btnAddRecord);
	}
	public void clickEditRecord() throws Exception {
		UtilityCustomFunctions.doActionClick(driver, btnEditRecord);
	}
	public void clickDuplicateRecord() throws Exception {
		UtilityCustomFunctions.doActionClick(driver, btnDuplicate);
	}
	
	//Get Methods.
	
	public String getGenericTitle(int ititlePos) throws Exception {
		String sXpath="(//div[@class='col-md-12']//b)[" + ititlePos + "]";
		WebElement eleTitle = driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getText(driver, eleTitle);
				
	}
	public String getUploadFileText() throws Exception
	{
		String sActValue="";
		try {
		sActValue = UtilityCustomFunctions.getText(driver, lblUploadFile);
		}catch(Exception e) {
			sActValue="";
		}
		return sActValue;
	}
	public String getPhoneNMTitle() throws Exception {
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblPhoneNumberTitle);
		return sActValue;
	}
	public String getEmailTitle() throws Exception {
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblEmailTitle);
		return sActValue;
	}
	public String getMenuItemTitle() throws Exception {
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblMenuItemTitle);
		return sActValue;
	}
	
	public String getNavBarModuleName() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lnkNavBarModuleName);
		return sActValue;
	}
	
	//Get Summary Details
	public String getArraySummary(int ielePos) throws Exception {
		String sXpath = "(//div[@class='col-lg-10'])["+ ielePos+ "]";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getText(driver, eleArrSummary); 
	}
	public String getSummaryUrl() throws Exception {
		String sXpath = "//div[@class='col-lg-10']/label/p/a";
//		String sXpath = "//a[normalize-space()='Click Here']";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath));
		return  eleArrSummary.getAttribute("href");
	}
	public String getDTSummaryUrl() throws Exception {
//		String sXpath = "(//div[@class='col-lg-10'])[19]//a";
		String sXpath = "//a[normalize-space()='Click Here']";
		WebElement eleArrSummary = driver.findElement(By.xpath(sXpath));
		return  eleArrSummary.getAttribute("href");
	}
	
	public String getDetailUrl() throws Exception {
		String sXpath = "(//td[starts-with(@id, 'hide')])[19]";
		WebElement eleArrDetail= driver.findElement(By.xpath(sXpath));
		return  eleArrDetail.getAttribute("href");
	}
	
	public String getSummaryAssignTo() throws Exception {
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblSummaryAssignedTo);
		return sActValue;
	}
	public String getArrayDetails(int ielePos) throws Exception {
		String sXpath = "(//td[starts-with(@id, 'hide')])["+ ielePos+ "]";
		WebElement eleArrDetais= driver.findElement(By.xpath(sXpath));
		return UtilityCustomFunctions.getText(driver, eleArrDetais); 
	}
			
	
	public String getPhoneNMSummary() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblPhoneNumberSummary);
		return sActValue;
	}
	public String getNumberFieldSummary() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblNumberFieldSummary);
		return sActValue;
	}
	public String getEmailSummary() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblEmailSummary);
		return sActValue;
	}
	public String getMenuItemSummary() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblMenuItemSummary);
		return sActValue;
	}
	public String getEYNumberSummary() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblEnterYourNMSummary);
		return sActValue;
	}
	
	
	//Detail View 
	public String getAssignedToDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblAssignedToDetailView);
		return sActValue;
	}
	public String getPhoneNMDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblPhoneNumberDetailView);
		return sActValue;
	}
	public String getNumberFieldDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblNumberFieldDetailView);
		return sActValue;
	}
	public String getEmailDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblEmailDetailView);
		return sActValue;
	}
	public String getMenuListDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblMenuItemDetailView);
		return sActValue;
	}
	public String getEYVDTView() throws Exception{
		String sActValue="";
		sActValue = UtilityCustomFunctions.getText(driver, lblEYPNDetailView);
		return sActValue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
