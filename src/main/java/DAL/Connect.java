package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	public static Connection con;

	public static boolean openConnection() {
		try {
			String dbUrl = "jdbc:mysql://localhost:3306/school";
			String username = "root";
			String password = "";
			con = DriverManager.getConnection(dbUrl, username, password);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public static void closeConnection() {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
