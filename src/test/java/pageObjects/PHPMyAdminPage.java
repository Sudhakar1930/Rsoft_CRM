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
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import testBase.BaseClass;
import utilities.UtilityCustomFunctions;

public class PHPMyAdminPage extends BasePage{

	public PHPMyAdminPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(linkText="//*[@id='navipanellinks']/a[2]/img")
	WebElement aLogoutPHPMyAdmin;
	
	@FindBy(xpath="(//input[@type='text'][@class='filter_rows'])[1]")
	WebElement txtFilterRow;
			
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
	
	@FindBy(xpath="(//input[@title='Previous'])[1]")
	WebElement lnkTopPreviousPage;
	
	@FindBy(xpath="(//input[@title='End'][1]")
	WebElement lnkTopLastPage;
	
	@FindBy(xpath="(//input[@title='Next'])[1]")
	WebElement lnkTopNextPage;
	
	public String getEntityCTandMT(String Url,String Uid,String pwd,String sReturnType,String sEntityId,String sTable) throws Exception {
		LoginPage objLP = new LoginPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		driver.get(Url);
		objLP.setMySqlUserId(Uid);
		objLP.setMySqlPasswd(pwd);
		Thread.sleep(3000);
		objLP.clickMySqlSubmit();
		Thread.sleep(3000);
		objPAP.clickDBLink();
		Thread.sleep(3000);
		objPAP.setTableInDB(sTable);
		Thread.sleep(2000);
		objPAP.clickTableLink(sTable);
		Thread.sleep(3000);
		objPAP.aLastPage();
		Thread.sleep(2000);
		WebElement eleSelect = driver.findElement(By.xpath("(//select[@name='sql_query'][@class='autosubmit'])[1]"));
		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelect, "PRIMARY (DESC)", "option");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		objPAP.setRecordId(sEntityId);
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		boolean bFlag = false;
		String sReturnValue="0,0";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr[not(@style='display: none;')]"));
		UtilityCustomFunctions.logWriteConsole("After WebTable Rows Retrieved");
		UtilityCustomFunctions.logWriteConsole("DB List Count:"+tRows.size());
		for(int i=0;i<tRows.size();i++) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			Thread.sleep(500);
			UtilityCustomFunctions.logWriteConsole("Within WebTable Rows Loop" + i);
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			Thread.sleep(1000);
			String sActEntityId = tCols.get(4).getText();
			String sActModuleName = tCols.get(8).getText();
			String sActCreatedTime = tCols.get(10).getText();
			String sActModifiedTime  = tCols.get(11).getText();
			
			UtilityCustomFunctions.logWriteConsole(sActEntityId+" " + sActModuleName + " " + sActCreatedTime + " " + sActModifiedTime );
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				switch (sReturnType) {
				case "CT":
					sReturnValue = sActCreatedTime + ",0"; 	
				case "MT":
					sReturnValue =  "0" + "," +  sActModifiedTime;
				case "Both":	
					sReturnValue = sActCreatedTime + "," + sActModifiedTime;
			}
				
				
			}
			
		}	
		Thread.sleep(1000);
		objLP.clickPHPMyAdminLogout();
		Thread.sleep(1000);
		return sReturnValue;
	}
	
	
	
	//************************ Click methods *****************************	
	
	public void clickTableLink(String sTableName) throws Exception {
		WebElement eleLink = driver.findElement(By.linkText(sTableName));
		Thread.sleep(2000);
		eleLink.click();
//		UtilityCustomFunctions.doClick(driver, eleLink);
		
	}
	
	
	public void aNavigtoLastPage() {
	boolean sPresent=false;

	try {	
		sPresent = UtilityCustomFunctions.IsElementVisible(driver, aNavigateLastPage);

		if(sPresent==true) {
			aNavigateLastPage.click();
			Thread.sleep(2000);
			UtilityCustomFunctions.doClick(driver, lnkTopPreviousPage);
		}
		}catch(Exception e) {
			UtilityCustomFunctions.logWriteConsole("No EndPage Link");
		}

	}
	public void aLastPage() {
		boolean sPresent=false;

		try {	
			sPresent = UtilityCustomFunctions.IsElementVisible(driver, aNavigateLastPage);

			if(sPresent==true) {
				aNavigateLastPage.click();
				Thread.sleep(1000);
			}
			}catch(Exception e) {
				UtilityCustomFunctions.logWriteConsole("No EndPage Link");
			}

		}
	
	public boolean aClickNextPage() throws Exception {
		
		boolean sPresent=false;

		try {	
			sPresent = UtilityCustomFunctions.IsElementVisible(driver, lnkTopNextPage);
			if(sPresent==true) {
				UtilityCustomFunctions.doClick(driver, lnkTopNextPage);
				Thread.sleep(2000);
				sPresent = true;
				
			}
			}catch(Exception e) {
				UtilityCustomFunctions.logWriteConsole("No EndPage or NextPage Link");
				sPresent = false;
			}
		
		return sPresent;
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
	public void clickaTableLink() throws Exception {
		UtilityCustomFunctions.doClick(driver, aTableLink);
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
	public void setRecordId(String sRecordId) throws InterruptedException {
		Thread.sleep(1000);
		txtFilterRow.sendKeys(sRecordId);
		txtFilterRow.sendKeys(Keys.ENTER);
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
	public void Check_No_Entity(String sTable,String sEntityId, String sTaskId,ExtentTest Node,String sMessage,String sCreatedTime,String sModifiedTime) throws Exception {
		boolean bTaskId = false;
		boolean bEntityId = false;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		
		Thread.sleep(3000);
		clickDBLink();
		Thread.sleep(3000);
		setTableInDB(sTable);
		Thread.sleep(2000);
		clickTableLink(sTable);
		Thread.sleep(3000);
		aLastPage();
		Thread.sleep(2000);
		
		WebElement eleSelect = driver.findElement(By.xpath("(//select[@name='sql_query'][@class='autosubmit'])[1]"));
		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelect, "PRIMARY (DESC)", "option");

		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		
		setRecordId(sEntityId);
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr[not(@style='display: none;')]"));
		UtilityCustomFunctions.logWriteConsole("DB Check_No_Entity List Count:"+tRows.size());
		for(int i=0;i<tRows.size();i++) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			String sActEntityId = "";
			String sActTaskId = "";
			String sActModName="";
			String sActSchdType = ""; 
			String sActSchdName="";
			String	sActExecTime="";
			String	sActSendType="";
			String sActCreatdTime="";
			String sActModifiedTime = "";
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			sActEntityId = tCols.get(5).getText();
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				bEntityId = true;
				js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
				sActTaskId 	= tCols.get(4).getText();
				//ModuleName
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
				sActModName =tCols.get(9).getText();	
				UtilityCustomFunctions.logWriteConsole("Module Name:" + sActModName);
				//Schedule Type
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
				sActSchdType =tCols.get(10).getText();
				UtilityCustomFunctions.logWriteConsole("Schedule Type:" + sActSchdType);
				//Schedule Type Name
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
				sActSchdName =tCols.get(11).getText();
				UtilityCustomFunctions.logWriteConsole("Schedule Type Name:" + sActSchdName);
				//Execution Time
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
				sActExecTime = tCols.get(14).getText();
				UtilityCustomFunctions.logWriteConsole("Actual Execution Time: " + sActExecTime);
				//Send Type
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
				sActSendType = tCols.get(17).getText();
				UtilityCustomFunctions.logWriteConsole("Actual Send Type: " + sActSendType);
				
				//Created Time
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
				sActCreatdTime = tCols.get(18).getText();
				
				UtilityCustomFunctions.logWriteConsole("Actual Creation Time: " + sActCreatdTime);
				//Modified Time
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(19));
				sActModifiedTime = tCols.get(19).getText();
				
				UtilityCustomFunctions.logWriteConsole("Actual Creation Time: " + sActModifiedTime);
				
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = format.parse(sActExecTime);
			
//				Date newDate = DateUtils.addMinutes(d1, 330);
//				String sActExecutionTime = newDate.toString();
				 
				Date d2 =format.parse(sActCreatdTime);
				
				if(sCreatedTime.equalsIgnoreCase(sActCreatdTime)) {
					objBase.freport("Created Time Passed: "+sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
					UtilityCustomFunctions.logWriteConsole(sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals(sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				else {
					
					objBase.freport("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "fail",Node);
					UtilityCustomFunctions.logWriteConsole("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,"Created Time Failed: " + sMessage + " Actual Created Time is : "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				
				if(sModifiedTime.equalsIgnoreCase(sActModifiedTime)) {
					objBase.freport(sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
					UtilityCustomFunctions.logWriteConsole(sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals(sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				else {
					objBase.freport("Modified Time Failed: " +sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "fail",Node);
					UtilityCustomFunctions.logWriteConsole("Modified Time Failed: " +sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals("Modified Time Failed: " + sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,sMessage + " Actual Modified Time: "+sActModifiedTime + " Expected Modified Time: " +sModifiedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				
			    UtilityCustomFunctions.logWriteConsole("Task Id:" + sActTaskId );
				if(sActTaskId.equalsIgnoreCase(sTaskId)) {
					bTaskId = true;
				}	
				else {
					bTaskId = false;
					
				}	
				UtilityCustomFunctions.logWriteConsole(" Is Entity Id: " + bEntityId + "And Is TaskId: " + bTaskId);
				if(bEntityId==true && bTaskId==false)
				{
					 objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "fail",Node);
					 UtilityCustomFunctions.logWriteConsole(sMessage + ": Failed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActExecTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId); 
					 BaseClass.sAssertinFn.assertEquals(sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId,sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
			
				}
				if(bEntityId==true && bTaskId==true)
				{
					 objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
					 UtilityCustomFunctions.logWriteConsole(sMessage + ": Passed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActExecTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId); 
					 BaseClass.sAssertinFn.assertEquals(sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId,sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId);
			
				}
					//break;
				}
			}
			if(bEntityId==false) {
				objBase.freport(sMessage + " EntityId: "+sEntityId +  " and Task Id:" + sTaskId + " Not Present in DB", "fail",Node);
				UtilityCustomFunctions.logWriteConsole(sMessage + " EntityId: "+sEntityId +  " and Task Id:" + sTaskId + " Not Present in DB");
				
			}
	}
	public boolean IsEntityInQueue(String Url,String Uid,String pwd,String sTable,String sEntityId) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LoginPage objLP = new LoginPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		boolean bFlag = false;
		driver.get(Url);
		objLP.setMySqlUserId(Uid);
		objLP.setMySqlPasswd(pwd);
		Thread.sleep(3000);
		objLP.clickMySqlSubmit();
		Thread.sleep(3000);
		objPAP.clickDBLink();
		Thread.sleep(3000);
		objPAP.setTableInDB(sTable);
		Thread.sleep(2000);
		objPAP.clickTableLink(sTable);
		Thread.sleep(3000);
		objPAP.aLastPage();
		Thread.sleep(2000);
		WebElement eleSelect = driver.findElement(By.xpath("(//select[@name='sql_query'][@class='autosubmit'])[1]"));
		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelect, "PRIMARY (DESC)", "option");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		objPAP.setRecordId(sEntityId);
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr[contains(not(@class='off ends')])"));
		for(int i=0;i<tRows.size();i++) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				bFlag = true;
				break;
			}
		}
		return bFlag;
	}
	public String fGetExecutionStartTime(String Url,String Uid,String pwd,String sTable,String sEntityId) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		LoginPage objLP = new LoginPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		String sExec_StartTime="";
		driver.get(Url);
		objLP.setMySqlUserId(Uid);
		objLP.setMySqlPasswd(pwd);
		Thread.sleep(3000);
		objLP.clickMySqlSubmit();
		Thread.sleep(3000);
		objPAP.clickDBLink();
		Thread.sleep(3000);
		objPAP.setTableInDB(sTable);
		Thread.sleep(2000);
		objPAP.clickTableLink(sTable);
		Thread.sleep(3000);
//		objPAP.aLastPage();
		Thread.sleep(2000);
		WebElement eleSelect = driver.findElement(By.xpath("(//select[@name='sql_query'][@class='autosubmit'])[1]"));
		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelect, "PRIMARY (DESC)", "option");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		objPAP.setRecordId(sEntityId);
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr[not(@style='display: none;')]"));
		for(int i=0;i<tRows.size();i++) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				sExec_StartTime = tCols.get(20).getText();
				break;
			}
		}
		return sExec_StartTime;
	}
	
	public String check_ET_Notify_CT(String Url,String Uid,String pwd,String sTable,String sEntityId,ExtentTest Node,String sMessage,String sDiffType,String sCreatedTime) throws Exception {
		LoginPage objLP = new LoginPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		driver.get(Url);
		objLP.setMySqlUserId(Uid);
		objLP.setMySqlPasswd(pwd);
		Thread.sleep(3000);
		objLP.clickMySqlSubmit();
		Thread.sleep(3000);
		objPAP.clickDBLink();
		Thread.sleep(3000);
		objPAP.setTableInDB(sTable);
		Thread.sleep(2000);
		objPAP.clickTableLink(sTable);
		Thread.sleep(3000);
		objPAP.aLastPage();
		Thread.sleep(2000);
		WebElement eleSelect = driver.findElement(By.xpath("(//select[@name='sql_query'][@class='autosubmit'])[1]"));
		UtilityCustomFunctions.selectItemfromListBox(driver, eleSelect, "PRIMARY (DESC)", "option");
		Thread.sleep(5000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		objPAP.setRecordId(sEntityId);
		Thread.sleep(3000);
		UtilityCustomFunctions.logWriteConsole("Entity Id Searched: " + sEntityId);
		
		boolean bFlag = false;
		String sActTaskId = "0"; 
		String sRetValue = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		BaseClass objBase = new BaseClass();
		List<WebElement> tRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]/tbody/tr[not(@*)]"));
		UtilityCustomFunctions.logWriteConsole("After WebTable Rows Retrieved");
		UtilityCustomFunctions.logWriteConsole("DB List Count:"+tRows.size());
		for(int i=0;i<tRows.size();i++) {
			js.executeScript("arguments[0].scrollIntoView();", tRows.get(i));
			Thread.sleep(500);
			UtilityCustomFunctions.logWriteConsole("Within WebTable Rows Loop" + i);
			List<WebElement> tCols = tRows.get(i).findElements(By.tagName("td"));
			String sActEntityId = tCols.get(5).getText();
			
			UtilityCustomFunctions.logWriteConsole("DB List Count Row: "+ i + " " + sActEntityId);
			if(sEntityId.equalsIgnoreCase(sActEntityId)) {
				UtilityCustomFunctions.logWriteConsole("Within If when Entity Id Matches" + i);
				bFlag = true;
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(4));
				sActTaskId 	= tCols.get(4).getText();
				UtilityCustomFunctions.logWriteConsole("Taask Id:" + sActTaskId );
				//ModuleName
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(9));
				String sActModName =tCols.get(9).getText();	
				UtilityCustomFunctions.logWriteConsole("Module Name:" + sActModName);
				//Schedule Type
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(10));
				String sActSchdType =tCols.get(10).getText();
				UtilityCustomFunctions.logWriteConsole("Schedule Type:" + sActSchdType);
				//Schedule Type Name
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(11));
				String sActSchdName =tCols.get(11).getText();
				UtilityCustomFunctions.logWriteConsole("Schedule Type Name:" + sActSchdName);
				//Execution Time
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(14));
				String	sActExecTime = tCols.get(14).getText();
				UtilityCustomFunctions.logWriteConsole("Actual Execution Time: " + sActExecTime);
				//Send Type
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(17));
				String	sActSendType = tCols.get(17).getText();
				UtilityCustomFunctions.logWriteConsole("Actual Send Type: " + sActSendType);
				
				//Created Time
				js.executeScript("arguments[0].scrollIntoView();", tCols.get(18));
				String sActCreatdTime = tCols.get(18).getText();
				UtilityCustomFunctions.logWriteConsole("Actual Creation Time: " + sActCreatdTime);
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d1 = format.parse(sActExecTime);
			
//				Date newDate = DateUtils.addMinutes(d1, 330);
//				String sActExecutionTime = newDate.toString();
				 
//				Date d2 =format.parse(sActCreatdTime);
				Date d2 = format.parse(sCreatedTime);
				
				if(sCreatedTime.equalsIgnoreCase(sActCreatdTime)) {
					objBase.freport("Created Time Passed: "+sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
					UtilityCustomFunctions.logWriteConsole(sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals(sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				else {
					
					objBase.freport("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "fail",Node);
					UtilityCustomFunctions.logWriteConsole("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId); 
					BaseClass.sAssertinFn.assertEquals("Created Time Failed: " + sMessage + " Actual Created Time: "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId,"Created Time Failed: " + sMessage + " Actual Created Time is : "+sActCreatdTime + " Expected Created Time: " +sCreatedTime +" ExecutionTime:" + sActExecTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
				}
				
				
//				Date createdDate = DateUtils.addMinutes(d2, 330);
//				String sActCreatedTime = createdDate.toString();
				
				UtilityCustomFunctions.logWriteConsole("Actual Creation Time: " + d2.toString() + "Actual Execution Time: " + d1.toString());
				long lDiffTime = fCustomDateDiff(d1,d2,sDiffType);
				
				if(sDiffType.equalsIgnoreCase("M")) {
					if(lDiffTime ==45) {
						 objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
						 UtilityCustomFunctions.logWriteConsole(sMessage + ": Passed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +"Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId); 
						 BaseClass.sAssertinFn.assertEquals(sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+ lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId,sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+ lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
					}
					else {
						objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName:" +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime: " + sActExecTime + " Difference In Minutes:="+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id:" + sActTaskId, "fail",Node);
						UtilityCustomFunctions.logWriteConsole(sMessage + " Failed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
						BaseClass.sAssertinFn.assertEquals(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId," Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference Not 5 Minutes:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
					}
				}else if(sDiffType.equalsIgnoreCase("H")) {
					if(lDiffTime ==60) {
						 objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
						 UtilityCustomFunctions.logWriteConsole(sMessage + ": Passed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +"Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId); 
						 BaseClass.sAssertinFn.assertEquals(sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+ lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId,sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+ lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
					}
					else {
						objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName:" +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime: " + sActExecTime + " Difference In Minutes:="+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id:" + sActTaskId, "fail",Node);
						UtilityCustomFunctions.logWriteConsole(sMessage + " Failed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
						BaseClass.sAssertinFn.assertEquals(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Minutes:= "+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId," Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Created Time: "+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference Not 5 Minutes:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
					}
					
				}else if(sDiffType.equalsIgnoreCase("D")) {
					if(lDiffTime ==24) {
						 objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId , "pass",Node);
						 UtilityCustomFunctions.logWriteConsole(sMessage + ": Passed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:= "+lDiffTime +"Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId); 
						 BaseClass.sAssertinFn.assertEquals(sMessage+ " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:= "+ lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId,sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:= "+ lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
					}
					else {
						objBase.freport(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName:" +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime: " + sActExecTime + " Difference In Hours:="+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id:" + sActTaskId, "fail",Node);
						UtilityCustomFunctions.logWriteConsole(sMessage + " Failed: " + "Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:= "+lDiffTime +" Send Type: " + sActSendType + " Module Name: "+sActModName + " and Task Id: " + sActTaskId);
						BaseClass.sAssertinFn.assertEquals(sMessage + " Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference In Hours:= "+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id: " + sActTaskId," Actual EntityId: "+sActEntityId + " ScheduleName: " +sActSchdName +" Actual Created Time: "+ sActCreatdTime + "Expected Created Time: " + sCreatedTime + " ExecutionTime:" + sActExecTime + " Difference Not 24 Hours:="+lDiffTime +" Send Type: " + sActSendType + "Module Name: "+sActModName + " and Task Id:" + sActTaskId);
					}
					
					
				}
				
				
			}
	
		}	
		sRetValue = bFlag + ":"+  sActTaskId;
		return sRetValue;
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
			 objBase.freport("Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at: " + sActWeeklyDay + " Execution Time: "+ sActExecTime + " Created Time: " + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId, "pass",node);
			 UtilityCustomFunctions.logWriteConsole("Passed: Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at: " + sActWeeklyDay + " Execution Time: "+ sActExecTime + " Created Time: " + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Action type:" + sActSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at:" + sActWeeklyDay + " Execution Time:"+ sActExecTime + " Created Time:" + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId, "Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at:" + sActWeeklyDay + " Execution Time:"+ sActExecTime + " Created Time:" + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId);
		 }
		 else {
			 objBase.freport("Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at: " + sActWeeklyDay + " Execution Time: "+ sActExecTime + " Created Time: " + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId, "fail",node);
			 UtilityCustomFunctions.logWriteConsole("Failed: Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at: " + sActWeeklyDay + " Execution Time: "+ sActExecTime + " Created Time: " + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Action type:" + sActSendType + " for EntityId "+ sEntityId + "and Task Id:" + sActTaskId);
			 BaseClass.sAssertinFn.assertEquals("Worflow not schedule to run at " + sDateTime + " on UTC and WeekDay at: " + sActWeeklyDay + " Execution Time:"+ sActExecTime + " Created Time:" + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId, "Worflow scheduled to run at " + sDateTime + " on UTC and WeekDay at:" + sActWeeklyDay + " Execution Time:"+ sActExecTime + " Created Time:" + sActCreatdTime  +  " Scheduled Type: " + sActSchdName + " Send type:" + sActSendType + " for EntityId "+ sEntityId + " and Task Id:" + sActTaskId);
		 }
		}//if Entity Id
		}
		
	}
	public void check_SN_Daily(String sEntityId,ExtentTest node) throws ParseException, IOException, InterruptedException {
		BaseClass objBase = new BaseClass();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
//		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
//		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
//		int itRowCount = eleTblRows.size();
		
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
				 objBase.freport("Worflow execution time : " +sActExecTime +" add 300 minutes added to GMT that is 08:00:00 AM "+" Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " and Task Id:" + sActTaskId, "pass",node);
				 UtilityCustomFunctions.logWriteConsole("Worflow execution time : " +sActExecTime +" add 300 minutes added to GMT that is 08:00:00 AM "+" Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + "and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Workflow schedule to run Daily at 8 AM for Entity:" + sEntityId +"and Task Id:" + sActTaskId, "Workflow schedule to run Daily at 8 AM for Entity:"  + sEntityId + "and Task Id:" + sActTaskId);
			 }
			 else {
				 objBase.freport("Worflow execution time : " +sActExecTime +" add 300 minutes added to GMT that is not 8:00:00AM which actually is :"+sDateTime +" Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " and Task Id:" + sActTaskId, "fail",node);
				 UtilityCustomFunctions.logWriteConsole("Failed: " + "Worflow execution time : " +sActExecTime +" add 300 minutes added to GMT that is not 8:00:00 AM which actually is :"+sDateTime +" Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Workflow not scheduled for daily run for Entity:" + sEntityId +"and Task Id:" + sActTaskId, "Workflow scheduled for daily run for Entity:"  + sEntityId + "and Task Id:" + sActTaskId);
			 }
			}//if Entity Id
		}
		
	}
	
	public void check_PAP_Record(String sEntityId,ExtentTest node) {
		BaseClass objBase = new BaseClass();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
	}
	public void check_SN_Hourly(String sEntityId,ExtentTest node) throws IOException, InterruptedException {
		BaseClass objBase = new BaseClass();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String sXpath = "//table[contains(@class,'table table-striped')]//tr";
		List<WebElement> eleTblRows = driver.findElements(By.xpath(sXpath));
		int itRowCount = eleTblRows.size();
		UtilityCustomFunctions.logWriteConsole("RowCount:"+itRowCount);
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
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = null;
			 Date d2 = null;
			 try {
			   d1 = format.parse(sActExecTime);
			   d2 = format.parse(sActCreatdTime);
			 } catch (Exception e) {
			   e.printStackTrace();
			 }
			
			long lDiffMinutes = fCustomDateDiff(d1,d2,"M");
			 if(lDiffMinutes==60) {
				 objBase.freport("Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffMinutes +" and Task Id:" + sActTaskId, "pass",node);
				 UtilityCustomFunctions.logWriteConsole("Passed: " + " Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffMinutes +" and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Scheduled Workflow triggered for Entity:" + sEntityId +"and Task Id:" + sActTaskId, "Scheduled Workflow triggered for Entity:" + sEntityId + "and Task Id:" + sActTaskId);
			 }
			 else
			 {
				 objBase.freport("Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffMinutes +" and Task Id:" + sActTaskId, "fail",node);
				 UtilityCustomFunctions.logWriteConsole("Failed: " + " Actual EntityId:"+sActEntityId + " ScheduleName:" +sActSchdName +" Created Time:"+ sActCreatdTime +" ExecutionTime:" + sActExecTime + " Difference In Minutes:="+lDiffMinutes +" and Task Id:" + sActTaskId);
				 BaseClass.sAssertinFn.assertEquals("Scheduled Workflow not triggered for Entity:" + sEntityId , "Scheduled Workflow triggered for Entity:" + sActTaskId); 
			 }
			 
			
			
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
	
	public void fLogoutPHPMyAdmin() throws Exception {
		UtilityCustomFunctions.doClick(driver, aLogoutPHPMyAdmin);
		Thread.sleep(2000);
	}
	public long fCustomDateDiff(Date sExecTime,Date sCreatedTime,String sDiffType) throws IOException, InterruptedException {
		UtilityCustomFunctions.logWriteConsole("Within Date Diff Validation");
		long sReturnDiff=0;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 Date d1 = null;
//		 Date d2 = null;
//		 try {
//		   d1 = format.parse(sExecTime);
//		   d2 = format.parse(sCreatedTime);
//		 } catch (Exception e) {
//		   e.printStackTrace();
//		 }
		 long duration  = sExecTime.getTime() - sCreatedTime.getTime();
		 long diffMinutes = duration / (60 * 1000);
		 long diffHours = duration / (60 * 60 * 1000);     
		 //long diffSeconds = diff / 1000;         
		 //long diffMinutes = diff / (60 * 1000);  
		 System.out.println("Difference in Time in Minutes: " + diffMinutes);
		 if(sDiffType.equalsIgnoreCase("M")) {
			 sReturnDiff = diffMinutes;
		 }
		 else if(sDiffType.equalsIgnoreCase("H")) {
			 sReturnDiff = diffMinutes;
		 }
		 else if(sDiffType.equalsIgnoreCase("D")) {
			 sReturnDiff = diffHours;
		 }
		 return sReturnDiff; 
	}	
	}



