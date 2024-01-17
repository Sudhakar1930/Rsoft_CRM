package pageObjects;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import utilities.UtilityCustomFunctions;

public class PHPMyAdminPage extends BasePage{

	public PHPMyAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(linkText="PRIYAN")
	WebElement aDBLink;
	
	@FindBy(linkText="SQL")
	WebElement aSQLWindow;
	
	@FindBy(linkText="Query")
	WebElement aQueryTAB;
	
	@FindBy(linkText="rsoft_workflowtask_queue")
	WebElement aTableLink;
	
	@FindBy(linkText="Edit inline")
	WebElement aEditInline;
	
	
	@FindBy(xpath="//a[@href='index.php?route=/server/sql']")
	WebElement eleSQLWindow;
	
	@FindBy(xpath="//div[@id='queryfieldscontainer']/div[1]/div[1]/div[1]/div[1]/div[6]")
	WebElement eleSQLTextWindow;

	@FindBy(id="button_submit_query")
	WebElement btnSubmitQuery;
	
	@FindBy(xpath="//*[@id='clear']")
	WebElement btnClear;
	
	@FindBy(xpath="//img[@class='icon ic_console']")
	WebElement btnConsole;
	
	@FindBy(xpath="(//pre[@role='presentation'])[1]")
	WebElement txtConsoleQuery;
	
	@FindBy(xpath="(//div[@class='CodeMirror-scroll']/div[@class='CodeMirror-sizer'])[2]")
	WebElement txtSQL;
	
	@FindBy(xpath="//table[contains(@class,'table table-striped')]")
	WebElement eleQueryWbTbl;
	
	@FindBy(xpath="(//pre[@class=' CodeMirror-line '])[2]")
	WebElement eleQueryTabWindow;
	
	@FindBy(xpath="//*[@id='submit_query']")
	WebElement btnSubmitQueryTab;
	
	@FindBy(xpath="//input[@class='searchClause form-control' and @name='searchClause2']")
	WebElement txtTableName;
	
	@FindBy(xpath="//input[@name='navig' and @class='btn btn-secondary ajax' and @title='End']")
	WebElement aNavigateLastPage;
	
	//click methods	
	public void aNavigtoLastPage() {
		aNavigateLastPage.click();
	}
	public void clickConsoleWindow() {
		btnConsole.click();
	}
	
	public void clickDBLink() {
		aDBLink.click();
	}
	
	public void clickaSqlWindow() {
		aSQLWindow.click();
	}
	public void clickaQueryTab() {
		aQueryTAB.click();
	}
	public void clickaSubmitQueryTab() {
		btnSubmitQueryTab.click();
	}
	public void clickaClearButton() {
		btnClear.click();
	}
	public void clickaTableLink() {
		aTableLink.click();
	}
	public void clickaEditInLine() {
		aEditInline.click();
	}
	public void clickGoQuery() throws Exception {
		UtilityCustomFunctions.doClick(driver, btnSubmitQuery);
	}
	public void setQuery(String sQuery) {
//		eleSQLTextWindow.sendKeys("test");
		
		UtilityCustomFunctions.sendKeys(driver, txtSQL, sQuery);
	}
	public void setTableInDB(String sTableName) throws InterruptedException {
		Thread.sleep(3000);
		txtTableName.sendKeys(sTableName);
//		UtilityCustomFunctions.sendKeys(driver, txtTableName, sTableName);
		txtTableName.sendKeys(Keys.ENTER);
	}
	
	
	public void setQueryTabQuery(String sValue){
//		eleQueryTabWindow.sendKeys(sValue);
		UtilityCustomFunctions.sendKeysNoEnter(driver, eleQueryTabWindow, sValue);
	}
	public void setQueryInConsole(String sQuery) throws Exception {
		Thread.sleep(1000);
		System.out.println("Inside method:" + sQuery);
		if(UtilityCustomFunctions.IsElementVisible(driver, txtConsoleQuery)) {
			UtilityCustomFunctions.sendKeys(driver, txtConsoleQuery, sQuery);
			Thread.sleep(1000);
			txtConsoleQuery.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		}
//		txtConsoleQuery.clear();
	}
	
	public void jSTypeValue(WebDriver driver,String sValue) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','"+ sValue + "')",txtConsoleQuery);
		Thread.sleep(3000);
//		UtilityCustomFunctions.sendKeys(driver, txtConsoleQuery, sValue);
//		js.executeScript("document.getElementById('q')")
	}
	
	
	public void checkWebTblValues(String sEntityId,ExtentTest node) throws IOException, InterruptedException {
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		
		
		for(int i=0;i<tRows.size();i++) {
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
			String sActTaskId = tCols.get(4).getText();
			String sActModTblName = tCols.get(7).getText();
			String sActModName = tCols.get(9).getText();
			String sActSchdType =tCols.get(10).getText();
			String sActSchdName =tCols.get(11).getText();
			String sActExecTime =tCols.get(14).getText();
			String sActSendType =tCols.get(17).getText();
			String sActCreatdTime = tCols.get(18).getText();
			 
			System.out.println("taskid:" + tCols.get(4).getText());
			System.out.println("Entity Id:" + tCols.get(5).getText());
			System.out.println("Module Table Name:" + tCols.get(7).getText());
			System.out.println("Module Name:" + tCols.get(9).getText());
			System.out.println("Schedule Type:" + tCols.get(10).getText());
			System.out.println("Schedule Name:" + tCols.get(11).getText());
			System.out.println("Execution Time:" + tCols.get(14).getText());
			System.out.println("Send Type:" + tCols.get(17).getText());
			System.out.println("Created Time:" + tCols.get(18).getText());
			
			fCustomDateDiff(sEntityId,sActTaskId,sActExecTime,sActCreatdTime,node);
			}//if Entity Id
		}
	}
	
	public void fCustomDateDiff(String sEntityId,String sTaskId,String DateStart,String DateEnd,ExtentTest node) throws IOException, InterruptedException {
		BaseClass objReport = new BaseClass();
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d1 = null;
		 Date d2 = null;
		 try {
		   d1 = format.parse(DateStart);
		   d2 = format.parse(DateEnd);
		 } catch (Exception e) {
		   e.printStackTrace();
		 }
		 
		 
		 long duration  = d1.getTime() - d2.getTime();
		 long diffMinutes = duration / (60 * 1000);
		 //long diffHours = duration / (60 * 60 * 1000);     
		 //long diffSeconds = diff / 1000;         
		 //long diffMinutes = diff / (60 * 1000);  
		 
		 System.out.println("Differene in Time in Minutes: " + diffMinutes);
		 if(diffMinutes==60) {
			 objReport.freport("Passed: Scheduled Worflow task displayed in DB: "+ sEntityId + "and Task Id:" + sTaskId, "pass",node);
			 UtilityCustomFunctions.logWriteConsole("Passed: Scheduled Worflow task displayed in DB :"+ sEntityId + "and Task Id:" + sTaskId);
			 BaseClass.sAssertinFn.assertEquals("Scheduled Workflow triggered for Entity:" + sEntityId +"and Task Id:" + sTaskId, "Scheduled Workflow triggered for Entity:" + sEntityId + "and Task Id:" + sTaskId);
		 }
		 else
		 {
			 objReport.freport("Failed: Scheduled Worflow task not displayed in DB: "+ sEntityId, "fail",node);
			 UtilityCustomFunctions.logWriteConsole("Failed: Scheduled Worflow task not displayed in DB :"+ sEntityId);
			 BaseClass.sAssertinFn.assertEquals("Scheduled Workflow not triggered for Entity:" + sEntityId , "Scheduled Workflow triggered for Entity:" + sEntityId); 
		 }
	}
	}



