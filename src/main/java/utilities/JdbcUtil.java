package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {

	public ResultSet testData(String dbNameDetails, String query) {
		Connection con;
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbNameDetails);
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
}