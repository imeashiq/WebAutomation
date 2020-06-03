package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

public class JdbcUtil {

	public HashMap<String, String> testData() {
		Connection con;
		HashMap<String, String> identityDetails = new HashMap<String, String>();
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/testusers?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "sH0sHw0t$");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users where status='Available'");
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				identityDetails.put(rsmd.getColumnName(1), rs.getString(1));
				identityDetails.put(rsmd.getColumnName(2), rs.getString(2));
				identityDetails.put(rsmd.getColumnName(3), rs.getString(3));
				identityDetails.put(rsmd.getColumnName(4), rs.getString(4));
				break;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return identityDetails;
	}
}
