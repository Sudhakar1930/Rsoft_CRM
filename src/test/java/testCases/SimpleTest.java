package testCases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class SimpleTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
//		DB_Operations DBObj = new DB_Operations();
//		String sQuery = "Select * from student where Sno=3";
//		DBObj.getSQLResultInMap(sQuery);
		Class.forName("com.mysql.cj.jdbc.Driver");
		HashMap<String, String> data_map = new HashMap<String, String>();
		String SQL = "SELECT * FROM STUDENT WHERE SNO=5";
		
		
		Connection con = DriverManager.getConnection("jdbc.mysql://localhost:3306/test?serverTimeZone=UTC","root","");
		PreparedStatement ps = con.prepareStatement(SQL);
		ResultSet rs  = ps.executeQuery();
		while(rs.next()) {
			long id = rs.getInt(0);
			String sName = rs.getString(1);
			String sSubject = rs.getString(2);
			System.out.println("SNo: " + id + "Name : " + sName + "Subject: " + sSubject);
		}//while
		
	}//main

}//class
