package pageObjects;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
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
	public void check_SN_Yearly(String sEntityId,String sModuleName,String sScheduleTypeName,String sActionType,ExtentTest node) throws InterruptedException, ParseException, IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int iDay = 0;
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		
		for(int i=0;i<tRows.size();i++) {
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			Thread.sleep(3000);
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			Thread.sleep(3000);
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				String sActTaskId 	= tCols.get(4).getText();
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
				UtilityCustomFunctions.logWriteConsole("Taask Id:" + sActTaskId );
				//ModuleName
				String sActModName =tCols.get(9).getText();	
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
				UtilityCustomFunctions.logWriteConsole("Module Name:" + sActModName);
				//Schedule Type
				String sActSchdType =tCols.get(10).getText();
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
				UtilityCustomFunctions.logWriteConsole("Schedule Type:" + sActSchdType);
				//Schedule Type Name
				String sActSchdName =tCols.get(11).getText();
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
				UtilityCustomFunctions.logWriteConsole("Schedule Type Name:" + sActSchdName);
				//Execution Time
				String	sActExecTime = tCols.get(14).getText();
				System.out.println("Actual Execution Time: " + sActExecTime);
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
				String sActCreatdTime = tCols.get(18).getText();
				System.out.println("Actual Creation Time: " + sActCreatdTime);
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
				Date d1 = null;
//				Date d2 = null;
				d1 = format.parse(sActExecTime);
//				d2 = format.parse(sActCreatdTime);
//				System.out.println("Created Time:" + d2.toString());
//				System.out.println("Executed Time:" + d1.toString());
				String aCreatedYear[] = sActCreatdTime.split("-");
				String aExecutedYear[] = sActExecTime.split("-");
				String sActCreatedYear = aCreatedYear[0];
				String sActExecutionYear =  aExecutedYear[0];
				System.out.println("Execution Year:" + sActExecutionYear);
				System.out.println("Creation Year:" + sActCreatedYear);
				Date newDate = DateUtils.addMinutes(d1, 330);
				String sConfigDateTime = newDate.toString();
				System.out.println("IST Act Execution Time: " + sConfigDateTime); 
				String sActSendType =tCols.get(17).getText();
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
				if(sConfigDateTime.contains("08:00:00") && sActModName.equalsIgnoreCase(sModuleName) && sActSchdName.equalsIgnoreCase(sScheduleTypeName) && sActSendType.equalsIgnoreCase(sActionType)) {
					 objBase.freport("Passed: Schedule by Yearly Worflow scheduled to run at 08:00:00 AM on UTC:" + sActExecTime + ", Schedule Type: " + sActSchdName + ", Action type: " + sActSendType + ", for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "pass",node);
					 UtilityCustomFunctions.logWriteConsole("Passed: Schedule by Yearly Worflow scheduled to run at 08:00:00 AM on UTC:" + sActExecTime + ", Schedule Type: " + sActSchdName + ", Action type: " + sActSendType + ", for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
					 BaseClass.sAssertinFn.assertEquals("Worflow scheduled Yearly to run at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, " Worflow scheduled Yearly to run at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
					 System.out.println("Execution Year:" + sActExecutionYear);
					 System.out.println("Creation Year:" + sActCreatedYear);
						
					if(Integer.parseInt(sActCreatedYear)+1 == Integer.parseInt(sActExecutionYear)) 
					{
						objBase.freport("Passed: Schedule by Yearly workflow showing execution year as " + sActExecutionYear,"pass",node);
						UtilityCustomFunctions.logWriteConsole("Passed: Schedule by Yearly workflow showing execution year as " + sActExecutionYear);
						BaseClass.sAssertinFn.assertEquals("Passed: Execution Year:" + sActExecutionYear,"Passed: Execution Year:" + sActExecutionYear);
					}
					else {
						objBase.freport("Failed: Schedule by Yearly workflow showing execution year as " + sActExecutionYear,"fail",node);
						UtilityCustomFunctions.logWriteConsole("Failed: Schedule by Yearly workflow showing execution year as " + sActExecutionYear);
						BaseClass.sAssertinFn.assertEquals("Failed: Execution Year:" + sActExecutionYear,"Failed: Execution Year:" + sActExecutionYear);
					}
				}
				else {
					 objBase.freport("Failed: Workflow scheduled to run Yearly at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "fail",node);
					 UtilityCustomFunctions.logWriteConsole("Failed: Workflow scheduled to run Yearly at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
					 BaseClass.sAssertinFn.assertEquals("Worflow not scheduled Yearly to run at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "Worflow scheduled Yearly to run at 08:00:00 AM on UTC " + sActExecTime + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
				}
				
				
			}
			
		}
		
		
		
	}
	public void check_SN_MonthByDate(String sEntityId,String sModuleName,String sDayArray,String sScheduleTypeName,String sActionType,ExtentTest node) throws ParseException, IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();

		String sDateArray[] = sDayArray.split(":");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 
		int iDay = 0;
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		for(int i=0;i<tRows.size();i++) {

		List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
		String sActEntityId = tCols.get(5).getText();
		Thread.sleep(3000);
		js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
		Thread.sleep(3000);
		if(sEntityId.equalsIgnoreCase(sActEntityId)) {
			System.out.println("taskid:" + tCols.get(4).getText());
			System.out.println("Entity Id:" + tCols.get(5).getText());
			System.out.println("Module Table Name:" + tCols.get(7).getText());
			System.out.println("Module Name:" + tCols.get(9).getText());
			System.out.println("Schedule Type:" + tCols.get(10).getText());
			System.out.println("Schedule Name:" + tCols.get(11).getText());
			System.out.println("Execution Time:" + tCols.get(14).getText());
			System.out.println("Send Type:" + tCols.get(17).getText());
			System.out.println("Created Time:" + tCols.get(18).getText());
		String sActTaskId 	= tCols.get(4).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
		//ModuleName
		String sActModName =tCols.get(9).getText();	
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
		//Schedule Type
		String sActSchdType =tCols.get(10).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
		//Schedule Type Name
		String sActSchdName =tCols.get(11).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
		//Execution Time
		String	sActExecTime = tCols.get(14).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
		Date d1 = null;
		d1 = format.parse(sActExecTime);
		Date newDate = DateUtils.addMinutes(d1, 330);
		String sConfigDateTime = newDate.toString();
		 
		String sActSendType =tCols.get(17).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
		String sActCreatdTime = tCols.get(18).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
//		String	sActExecTime = "2024-01-01 14:30:00";
			
		int sActDate = d1.getDate(); 	 
		int sExpDate = Integer.parseInt(sDateArray[iDay]); 
		
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(5));
		if(sConfigDateTime.contains("08:00:00") && sActModName.equalsIgnoreCase(sModuleName) && sActSchdName.equalsIgnoreCase(sScheduleTypeName) && sActDate==sExpDate && sActSendType.equalsIgnoreCase(sActionType)) {
			 objBase.freport("Passed: MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC:" + sActExecTime + " On Day: "+sActDate + ", Schedule Type: " + sActSchdName + ", Action type: " + sActSendType + ", for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "pass",node);
			 UtilityCustomFunctions.logWriteConsole("Passed: MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: "+sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + ", and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: " + sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: " + sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
		}
		else {
			objBase.freport("Failed: MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: "+sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "fail",node);
			 UtilityCustomFunctions.logWriteConsole("Failed: MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: "+sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("MonthlybyDate Worflow not scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: " + sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId, "MonthlybyDate Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "On Day: " + sActDate + "Schedule Type: " + sActSchdName + " Action type: " + sActSendType + " for EntityId: "+ sEntityId + "and Task Id:" + sActTaskId);
		}
		
			iDay = iDay + 1;
		}//if Entity Id	
		}//for loop
	}
	public void check_SN_Weekly(String sEntityId,String sWeekday,String schTypeName, String sSendType,ExtentTest node) throws ParseException, IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		for(int i=0;i<tRows.size();i++) {

		List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
		String sActEntityId = tCols.get(5).getText();
		
		if(sEntityId.equalsIgnoreCase(sActEntityId)) {
		js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
		String sActTaskId = tCols.get(4).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
		String sActModTblName = tCols.get(7).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(7));
		String sActModName = tCols.get(9).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
		String sActSchdType =tCols.get(10).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
		String sActSchdName =tCols.get(11).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
		String sActWeeklyDay = tCols.get(13).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(13));
		String sActExecTime =tCols.get(14).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
		String sActSendType =tCols.get(17).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
		String sActCreatdTime = tCols.get(18).getText();
		js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
		 
		System.out.println("taskid:" + tCols.get(4).getText());
		System.out.println("Entity Id:" + tCols.get(5).getText());
		System.out.println("Module Table Name:" + tCols.get(7).getText());
		System.out.println("Module Name:" + tCols.get(9).getText());
		System.out.println("Schedule Type:" + tCols.get(10).getText());
		System.out.println("Schedule Name:" + tCols.get(11).getText());
		System.out.println("Execution Time:" + tCols.get(14).getText());
		System.out.println("Send Type:" + tCols.get(17).getText());
		System.out.println("Created Time:" + tCols.get(18).getText());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d1 = null;
		 d1 = format.parse(sActExecTime);
		 Date newDate = DateUtils.addMinutes(d1, 330);
		 String sDateTime = newDate.toString();
		 Thread.sleep(3000);
		 js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
		 if(sDateTime.contains("08:00:00") && sActWeeklyDay.equalsIgnoreCase(sWeekday) && sActSchdName.equalsIgnoreCase(schTypeName) && sActSendType.equalsIgnoreCase(sSendType)) {
			 objBase.freport("Passed: Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "Created Time:" + sActCreatdTime +", Schedule Type" + sActSchdName + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "pass",node);
			 UtilityCustomFunctions.logWriteConsole("Passed: Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "Created Time:" + sActCreatdTime +", Schedule Type" + sActSchdName + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("Worflow scheduled to run at 08:00:00 AM on UTC:"+ sActExecTime + "Created Time:" + sActCreatdTime + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "Worflow scheduled to run at 08:00:00 AM on UTC:"+ sActExecTime + "Created Time:" + sActCreatdTime + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
		 }
		 else {
			 objBase.freport("Failed: Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "Created Time:" + sActCreatdTime +"Schedule Type" + sActSchdName + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "fail",node);
			 UtilityCustomFunctions.logWriteConsole("Failed: Worflow scheduled to run at 08:00:00 AM on UTC " + sActExecTime + "Created Time:" + sActCreatdTime + "Schedule Type" + sActSchdName + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("Worflow not scheduled to run at 08:00:00 AM on UTC:"+ sActExecTime + "Created Time:" + sActCreatdTime + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "Worflow scheduled to run at 08:00:00 AM on UTC:"+ sActExecTime + "Created Time:" + sActCreatdTime + "Scheduled Type: " + schTypeName + " Action type:" + sSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
		 }
		}//if Entity Id
		}
		
	}
	public void check_SN_Daily(String sEntityId,ExtentTest node) throws ParseException, IOException, InterruptedException {
		BaseClass objBase = new BaseClass();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		for(int i=0;i<tRows.size();i++) {
			
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
			
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			
			String sActTaskId = tCols.get(4).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
			String sActModTblName = tCols.get(7).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(7));
			String sActModName = tCols.get(9).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
			String sActSchdType =tCols.get(10).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
			String sActSchdName =tCols.get(11).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
			String sActExecTime =tCols.get(14).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
			String sActSendType =tCols.get(17).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
			String sActCreatdTime = tCols.get(18).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
			 
			System.out.println("taskid:" + tCols.get(4).getText());
			System.out.println("Entity Id:" + tCols.get(5).getText());
			System.out.println("Module Table Name:" + tCols.get(7).getText());
			System.out.println("Module Name:" + tCols.get(9).getText());
			System.out.println("Schedule Type:" + tCols.get(10).getText());
			System.out.println("Schedule Name:" + tCols.get(11).getText());
			System.out.println("Execution Time:" + tCols.get(14).getText());
			System.out.println("Send Type:" + tCols.get(17).getText());
			System.out.println("Created Time:" + tCols.get(18).getText());
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date d1 = null;
			 d1 = format.parse(sActExecTime);
			 Date newDate = DateUtils.addMinutes(d1, 330);
			 String sDateTime = newDate.toString();
			 js.executeScript("arguments[0].scrollIntoView();", tCols.get(5));
			 if(sDateTime.contains("08:00:00")) {
				 objBase.freport("Passed: Worflow scheduled to run daily at 08:00:00 AM for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "pass",node);
				 UtilityCustomFunctions.logWriteConsole("Passed: Worflow scheduled to run daily at 08:00:00 AM for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Workflow schedule to run at 8 AM for Entity:" + sEntityId +"and Task Id:" + sActTaskId, "Workflow schedule to run at 8 AM for Entity:"  + sEntityId + "and Task Id:" + sActTaskId);
			 }
			 else {
				 objBase.freport("Failed: Worflow not scheduled to run daily at 08:00:00 AM for EntityId "+ sEntityId + "and Task Id:" + sActTaskId, "fail",node);
				 UtilityCustomFunctions.logWriteConsole("Failed: Worflow not scheduled to run daily at 08:00:00 AM for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Workflow not scheduled for daily run for Entity:" + sEntityId +"and Task Id:" + sActTaskId, "Workflow scheduled for daily run for Entity:"  + sEntityId + "and Task Id:" + sActTaskId);
			 }
			}//if Entity Id
		}
		
	}
	public void check_SN_Hourly(String sEntityId,ExtentTest node) throws IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		
		int iTaskId = 0; 
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr"));
		
		
		for(int i=0;i<tRows.size();i++) {
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			UtilityCustomFunctions.logWriteConsole("Exp EntityId:"+sEntityId + " Actual Entity Id:" +sActEntityId);
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			String sActTaskId = tCols.get(4).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
			String sActModTblName = tCols.get(7).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(7));
			String sActModName = tCols.get(9).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
			String sActSchdType =tCols.get(10).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
			String sActSchdName =tCols.get(11).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
			String sActExecTime =tCols.get(14).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
			String sActSendType =tCols.get(17).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
			String sActCreatdTime = tCols.get(18).getText();
			js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
			 
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
	
	public void fValidateDailyNotify(String sExecutionDate) throws ParseException {
		BaseClass objBase = new BaseClass();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d1 = null;
		 d1 = format.parse(sExecutionDate);
		 Date newDate = DateUtils.addMinutes(d1, 330);
		 String sDateTime = newDate.toString();
		 if(sDateTime.contains("08:00:00")) {
			 System.out.println("Daily scheduled: Pass");
		 }
		 else {
			 System.out.println("Daily scheduled: Fail");
		 }
		 
	}
	public void fCustomDateDiff(String sEntityId,String sTaskId,String sExecTime,String sCreatedTime,ExtentTest node) throws IOException, InterruptedException {
		UtilityCustomFunctions.logWriteConsole("Within Date Diff Validation");
		BaseClass objReport = new BaseClass();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date d1 = null;
		 Date d2 = null;
		 try {
		   d1 = format.parse(sExecTime);
		   d2 = format.parse(sCreatedTime);
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



