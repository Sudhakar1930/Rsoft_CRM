//package utilities;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//
//import com.mysql.cj.jdbc.result.ResultSetMetaData;
//
//public class DB_Operations {
//	
//	public synchronized HashMap<String, String>getSQLResultInMap() throws ClassNotFoundException, SQLException {
//		HashMap<String, String> data_map = new HashMap<String, String>();
//		String SQL = "SELECT * FROM STUDENT WHERE SNO=5";
//		try {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		Connection con = DriverManager.getConnection("jdbc.mysql://localhost:3306/test?serverTimeZone=UTC");
//		PreparedStatement ps = con.prepareStatement(SQL)
//		ResultSet rs  = ps.executeQuery()){
//		while(rs.next()) {
//			long id = rs.getInt(0);
//			String sName = rs.getString(1);
//			String sSubject = rs.getString(2);
//			System.out.println("SNo: " + id + "Name : " + sName + "Subject: " + sSubject);
//		}
//		}
//		
////		Statement stmt = con.createStatement();
////		ResultSet rs = stmt.executeQuery(sql);
////		ResultSetMetaData md = rs.getMetaData();
//		
////		while(rs.next()) {
////			for(int i=1;i<=md.getColumnCount();i++) {
////				data_map.put(md.getColumnName(i), rs.getString(i));
////			}
////		}
////		System.out.println(data_map);
//		con.close();
//		}catch(Exception e) {
//			System.out.println(e.getCause());
//		}
////		return data_map;
//		
//	}
//	
//}
