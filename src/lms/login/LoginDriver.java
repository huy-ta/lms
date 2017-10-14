package lms.login;

import java.sql.*;

public class LoginDriver {
	public static final String dbUrl = "jdbc:mysql://localhost/quanly_thuvien"; 
	public static final String username = "admin";
	public static final String password = "password";
	
	public static String resultText = "";
	public static final int loginSuccess = 2;
	public static final int wrongPassword = 1;
	public static final int noUsername = 0;
	
	public static final String noUsernameWarning = "Username doesn't exist.\nPlease try again.";
	public static final String loginSuccessText = "You have logged in successfully!";
	public static final String wrongPasswordWarning = "Incorrect password!";

	
	public static int loginActions(String inputUsername, String inputPassword) {

		try {
			// 1 Get a conection to the database
			Connection loginCon = DriverManager.getConnection(dbUrl, username, password);
			
			// 2 Create a statement
			Statement myStmt = loginCon.createStatement();
			
			// 3 Execute SQL Query
			ResultSet loginRs = myStmt.executeQuery("select Username, Password from taikhoan");
			
			// 4 Process the result set
			boolean noUsername = true;
			while (loginRs.next()) {
				String tmpUsername = loginRs.getString("Username");
				if (tmpUsername.equals(inputUsername)) {
					noUsername = false;
					if (inputPassword.equals(loginRs.getString("Password"))) {
						resultText = loginSuccessText;
						return 2;
					}
					else {
						resultText = wrongPasswordWarning;
						return 1;
					}
				}
			}
			if (noUsername) {
				resultText = noUsernameWarning;
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

}
