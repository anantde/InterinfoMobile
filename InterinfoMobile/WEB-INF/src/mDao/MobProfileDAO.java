package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mConnection.MobMyConnection;
public class MobProfileDAO {
	MobMyConnection objConnection = new MobMyConnection();
	
	public String updatePassword(String newPassword, String userID) {
		Connection conn = objConnection.getConnection();
		String code = "success";
		try {
			conn.setAutoCommit(false);
			// ------------ Update Password -------------
			String updatePassQuery = "UPDATE user SET password = ? WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(updatePassQuery);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userID);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			code = "error";
			e.printStackTrace();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			code = "error";
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return code;
	}
}
