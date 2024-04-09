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

import utilities.CRMReUsables;


public class SimpleTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
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
		CRMReUsables objCRMRs = new CRMReUsables();
		System.out.println("Current Month:" + objCRMRs.getTodayMonth());
		System.out.println("Current Year:" + objCRMRs.getCurrentYear());
		
		
	}//main

}//class
