package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {

	static String url = "jdbc:mysql://localhost:3306/test_management_system";
	static String user = "root";
	static String password = "";
	static Connection con;
	static Statement stt;
	static ResultSet res;
	
	public static void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            con = DriverManager.getConnection(url, user, password);
            stt = con.createStatement();	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
		}
	}
}
