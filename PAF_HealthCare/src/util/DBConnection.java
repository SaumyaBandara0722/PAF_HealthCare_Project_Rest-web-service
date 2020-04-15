package util;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	
	public Connection connect() {
		Connection con = null;

		try {

			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection("jdbc:mysql://localhost/health-system", "root", "");
//			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health-system?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			// For testing
			//con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb", "root", "");
			con = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/healthcaredb?useTimezone=true&serverTimezone=UTC",
					"root", "");
			System.out.print("Successfully connected");

		} catch (Exception e) {

			System.out.print("connection fail");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	
	

}
