package testCases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AllListPage;
import pageObjects.AllModuleValues;
import pageObjects.BasePage;
import pageObjects.CreateModuleDataPage;
import pageObjects.DetailViewPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.PHPMyAdminPage;
import pageObjects.SummaryViewPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.ExcelUtility;
import utilities.UtilityCustomFunctions;


public class SimpleTest extends BaseClass{
	@BeforeTest()
	public void testName() {
		System.out.println("Before Test Method");
		test = extent.createTest("SimpleTest");
	}
	
	@Test
	public void testDateFunctions() throws InterruptedException, Exception {
		node = test.createNode("NT_ExecuteTask_MTAfterHours");
		UtilityCustomFunctions.logWriteConsole("******starting TC005_NT_ExecuteTask_MTAfterHours ****");
		String sBrowserName=UtilityCustomFunctions.getBrowserName(driver);
		UtilityCustomFunctions.logWriteConsole("Browser Execution on: "+ sBrowserName);
		
//		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_MTAfterHours_Live" + ".xlsx" ;
		String sPath=".\\testData\\ExecuteTask\\Notification\\ET_NT_OOFS_DateAfterDays_Test" + ".xlsx" ;
		
		ExcelUtility xlObj = new ExcelUtility(sPath);
		UtilityCustomFunctions.logWriteConsole("Excel file Utility instance created");
		
		int iRowCount = xlObj.getRowCount("Sheet1");
		UtilityCustomFunctions.logWriteConsole("Total rows: " + iRowCount);
			
		int iColCount = xlObj.getCellCount("Sheet1", iRowCount);
		UtilityCustomFunctions.logWriteConsole("Cols Count: " + iColCount);
		
		String sExpModuleName = xlObj.getCellData("Sheet1", 1, 0);
		String sExpWorkFlowName = xlObj.getCellData("Sheet1", 1, 1);
//		String sAssignedTo = xlObj.getCellData("Sheet1", 1, 2);
		String sExecutionCondition=xlObj.getCellData("Sheet1", 1, 24);
		String sActionType=xlObj.getCellData("Sheet1", 1, 25);
		String sActionTitle=xlObj.getCellData("Sheet1", 1, 26);
		String sDisplayModuleName=xlObj.getCellData("Sheet1", 1, 29);

		String sEditIndText=xlObj.getCellData("Sheet1", 1, 30);
		String sNotifyTemplateMsg=xlObj.getCellData("Sheet1", 1, 31);
		String sSelectedDate=xlObj.getCellData("Sheet1", 1, 33);
		LoginPage objLP = new LoginPage(driver);
		HomePage objHP = new HomePage(driver);
		AllListPage objALP = new AllListPage(driver);
		AllModuleValues objEDT = new AllModuleValues(driver);
		CreateModuleDataPage objCMD = new CreateModuleDataPage(driver);
		CRMReUsables objCRMRs = new CRMReUsables();
		DetailViewPage objDVP = new DetailViewPage(driver);
		SummaryViewPage objSVP = new SummaryViewPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		BaseClass objBase = new BaseClass();
		
		driver.get(rb.getString("appURL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String sAppUrl = rb.getString("appURL");
		String sCompName =  rb.getString("companyName");
		String sUserName =  rb.getString("userName");
		String sPassword =  rb.getString("passWord");
		String sAssignedTo = rb.getString("AssignedTo");
		String sUserName1 =  rb.getString("userName4");
		String sPassword1 =  rb.getString("passWord4");
		String sAssignedTo1 = rb.getString("AssignedTo4");
		String sUserName2 =  rb.getString("userName2");
		String sPassword2 =  rb.getString("passWord2");
		String sAssignedTo2 = rb.getString("AssignedTo2");
		String sMySqlUid = rb.getString("MySqlUid");
		String sMySqlPwd = rb.getString("MySqlPwd");
		String sMySqlUrl= rb.getString("MySqlUrl");
		
		if(objLP.isLoginPageDisplayed(sAppUrl)) {
			objLP.setCompanyName(sCompName);
			objLP.setUserName(sUserName);
			objLP.setPassword(sPassword);
			objLP.clickLoginSubmit();
			
		}
		else {
			logger.info("CRM Login failed");
			System.out.println("Login Page Not Displayed");
			Assert.fail("Login Page not displayed");
			
		}
		Thread.sleep(3000);
		
		Thread.sleep(3000);
		objALP.clickAllList();
		Thread.sleep(1000);
		objALP.clickModuleOnListAll(driver, sExpModuleName);
		
		UtilityCustomFunctions.logWriteConsole("Click Source Module:"+sExpModuleName);
		Thread.sleep(1000);
		objEDT.clickModule(sExpModuleName);
		UtilityCustomFunctions.logWriteConsole("Add New Record clicked:"+sExpModuleName);
		Thread.sleep(2000);
		
		//Date
		objCMD.clickDateBox(sExpModuleName, "date");
		UtilityCustomFunctions.logWriteConsole("DateBox clicked");
		Thread.sleep(3000);
		List<WebElement> sDateRows = driver.findElements(By.xpath("(//table[contains(@class,'table-condensed')])[1]//tr"));
		
		boolean bFound = false;
		int iCurrDate=0;
		String sDateXpath="(//td[contains(@class,'today')])[1]";
		WebElement eleCurrDate = driver.findElement(By.xpath(sDateXpath));
		String sActCurrDate = eleCurrDate.getText();
		iCurrDate = Integer.parseInt(sActCurrDate);
//		int iCounter=1;
		int jCounter=iCurrDate+3;
		Date dMonthDate = new Date();
		@SuppressWarnings("deprecation")
		int iMonthIndex = dMonthDate.getMonth(); 
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.MONTH, iMonthIndex);
		int iMonthLastDay = calendar1.getActualMaximum(calendar1.DAY_OF_MONTH); 
		System.out.println("Last day of current month:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
		System.out.println("j counter:" + jCounter);
		if(jCounter >iMonthLastDay) {
			System.out.println("Select next month");
		}
		for(int i=2;i<sDateRows.size();i++) {
			System.out.println("Table Row: " + i);
			List<WebElement> sDateCols = sDateRows.get(i).findElements(By.tagName("td"));
			for(int j=0;j<sDateCols.size();j++) {
				String sDay = sDateCols.get(j).getText();
				if(jCounter==Integer.parseInt(sDay)) {
					sDateCols.get(j).click();
					bFound = true;
					break;
				}
			}
			if(bFound == true) {
				break;
			}
			
		}
		Thread.sleep(1000);
				
		
//		LocalDate date = LocalDate.now();
//				
//		LocalDate yesterday = date.minusDays(1);    
//	    LocalDate tomorrow = yesterday.plusDays(2);
//	    date.getMonth();
//		LocalDate LastDateofMonth = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
//		
//		
//		calendar1.set(Calendar.MONTH, 1);
//		System.out.println("Last day of month 1:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 2);
//		System.out.println("Last day of month 2:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 3);
//		System.out.println("Last day of month 3:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 4);
//		System.out.println("Last day of month 4:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 5);
//		System.out.println("Last day of month 5:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 6);
//		System.out.println("Last day of month 6:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 7);
//		System.out.println("Last day of month 7:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 8);
//		System.out.println("Last day of month 8:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 9);
//		System.out.println("Last day of month 9:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 10);
//		System.out.println("Last day of month 10:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 11);
//		System.out.println("Last day of month 11:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		calendar1.set(Calendar.MONTH, 12);
//		System.out.println("Last day of month 12:" + calendar1.getActualMaximum(calendar1.DAY_OF_MONTH));
//		
//		
//		
//		
//		
//	    System.out.println("Today date: "+date);    
//	    System.out.println("Yesterday date: "+yesterday);    
//	    System.out.println("Tomorrow date: "+tomorrow);    
//	    System.out.println("get day of month"+date.getDayOfMonth());
//	    System.out.println("get day of week"+date.getDayOfWeek());
//	    System.out.println("get day of year"+date.getDayOfYear());
//	    System.out.println("Last Day of Month: "+LastDateofMonth);
	    
	    
	    
	}

	@Test
	public void testphplogout() throws Exception {
//		// TODO Auto-generated method stub
////		DB_Operations DBObj = new DB_Operations();
////		String sQuery = "Select * from student where Sno=3";
////		DBObj.getSQLResultInMap(sQuery);
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		HashMap<String, String> data_map = new HashMap<String, String>();
//		String SQL = "SELECT * FROM STUDENT WHERE SNO=5";
//		
//		
//		Connection con = DriverManager.getConnection("jdbc.mysql://localhost:3306/test?serverTimeZone=UTC","root","");
//		PreparedStatement ps = con.prepareStatement(SQL);
//		ResultSet rs  = ps.executeQuery();
//		while(rs.next()) {
//			long id = rs.getInt(0);
//			String sName = rs.getString(1);
//			String sSubject = rs.getString(2);
//			System.out.println("SNo: " + id + "Name : " + sName + "Subject: " + sSubject);
//		}//while
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String	sActExecTime = "2024-01-30 14:30:00";
//		Date d1 = null;
//		d1 = format.parse(sActExecTime);	
		
//		System.out.println(d1.getDate());
//		driver.get("https://www.rdot.in/public/phpMyAdmin/");
//		String sUrl = "https://www.rdot.in/public/phpMyAdmin/";
//		LoginPage objLP = new LoginPage(driver);
//		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
//		
//		String sMySqlUid = rb.getString("MySqlUid");
//		String sMySqlPwd = rb.getString("MySqlPwd");
//		String sMySqlUrl= rb.getString("MySqlUrl");
//		
////		objLP.setMySqlUserId(sMySqlUid);
////		objLP.setMySqlPasswd(sMySqlPwd);
////		Thread.sleep(3000);
////		objLP.clickMySqlSubmit();
////		Thread.sleep(3000);
//		String sCreatedTime="";
//		String sModifiedTime="";
//		String sReturnValue = objPAP.getEntityCTandMT(sUrl, sMySqlUid, sMySqlPwd, "CT", "124082","rsoft_crmentity");
//		String sRetArr[] = sReturnValue.split(",");
//		
//		if(sRetArr[0]!="0") {
//			sCreatedTime = sRetArr[0]; 
//		}
//		if(sRetArr[0]!="0") {
//			sModifiedTime = sRetArr[1]; 
//		}
//		UtilityCustomFunctions.logWriteConsole(sCreatedTime  + "  " + sModifiedTime);
//		
//		objLP.clickPHPMyAdminLogout();
		
		
//		CRMReUsables objCRMRs = new CRMReUsables();
//		System.out.println("Current Month:" + objCRMRs.getTodayMonth());
//		System.out.println("Current Year:" + objCRMRs.getCurrentYear());
		
		
	    
	    
		
	}//test

}//class
