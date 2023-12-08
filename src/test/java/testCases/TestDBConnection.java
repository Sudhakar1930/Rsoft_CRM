package testCases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class TestDBConnection {
	@Test
	public void executeQuery() throws ClassNotFoundException, SQLException {
		String sSQL = "SELECT * FROM `appointments_appointmentstatus` WHERE id=3";
		//Set Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Open connection to DB
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AUTOMATION?","testing","dCr2KGiHBrC2!@");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sSQL);
		
		while(rs.next()) {
			System.out.println(rs.getInt(0));
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			
		}
	}
}
