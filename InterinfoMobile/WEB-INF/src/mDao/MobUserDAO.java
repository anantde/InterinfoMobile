package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import mConnection.MobMyConnection;

public class MobUserDAO {
	MobMyConnection objConnection = new MobMyConnection();
	
	public String getCustID(String userID) {
		String custID = "0";
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		try {
			String isUserPresentQuery = "SELECT customer_id FROM user WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(isUserPresentQuery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				custID = rs.getString("customer_id");
			}
		} catch (SQLException e) {
			System.out.println("Error: UserDAO/getCustID()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! UserDAO / getCustID()...!");
				e2.printStackTrace();
			}
		}
		return custID;
	}
	
	
	
}
