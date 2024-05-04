package testCases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.LoginPage;
import pageObjects.PHPMyAdminPage;
import testBase.BaseClass;
import utilities.CRMReUsables;
import utilities.UtilityCustomFunctions;


public class SimpleTest extends BaseClass{


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
		String sUrl = "https://www.rdot.in/public/phpMyAdmin/";
		LoginPage objLP = new LoginPage(driver);
		PHPMyAdminPage objPAP = new PHPMyAdminPage(driver);
		
		String sMySqlUid = rb.getString("MySqlUid");
		String sMySqlPwd = rb.getString("MySqlPwd");
		String sMySqlUrl= rb.getString("MySqlUrl");
		
//		objLP.setMySqlUserId(sMySqlUid);
//		objLP.setMySqlPasswd(sMySqlPwd);
//		Thread.sleep(3000);
//		objLP.clickMySqlSubmit();
//		Thread.sleep(3000);
		String sCreatedTime="";
		String sModifiedTime="";
		String sReturnValue = objPAP.getEntityCTandMT(sUrl, sMySqlUid, sMySqlPwd, "CT", "124082","rsoft_crmentity");
		String sRetArr[] = sReturnValue.split(",");
		
		if(sRetArr[0]!="0") {
			sCreatedTime = sRetArr[0]; 
		}
		if(sRetArr[0]!="0") {
			sModifiedTime = sRetArr[1]; 
		}
		UtilityCustomFunctions.logWriteConsole(sCreatedTime  + "  " + sModifiedTime);
		
		objLP.clickPHPMyAdminLogout();
		
		
//		CRMReUsables objCRMRs = new CRMReUsables();
//		System.out.println("Current Month:" + objCRMRs.getTodayMonth());
//		System.out.println("Current Year:" + objCRMRs.getCurrentYear());
		
		
	}//main

}//class
