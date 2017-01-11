package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import mBean.MobLoginBean;
import mConnection.MobMyConnection;



public class MobLoginDAO {
	MobMyConnection objConnection = new MobMyConnection();
	HttpServletRequest request;
	
	
	public MobLoginBean isEmailPresent(MobLoginBean objBean) {
		Connection conn = objConnection.getConnection();
		MobLoginBean objMobLoginBean = new MobLoginBean();
		objMobLoginBean.setUserType("noEmail");
		objMobLoginBean.setEmail(objBean.getEmail());
		objMobLoginBean.setPassword(objBean.getPassword());
		objMobLoginBean.setDeviceId(objBean.getDeviceId());
		ResultSet rs = null;
		try {
			String isUserPresentQuery = "SELECT email, user_type FROM user WHERE email=?";
			PreparedStatement pstmt = conn.prepareStatement(isUserPresentQuery);
			pstmt.setString(1, objBean.getEmail());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("Email Present...!");
				objMobLoginBean.setUserType(rs.getString("user_type"));
			}else {
				//objMobLoginBean.setUserType("noEmail");
				System.out.println("Email Not Present...!");
			}
		} catch (SQLException e) {
			System.out.println("Error: LoginDAO / isEmailPresent()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.out
						.println("Error while closing Connection...! LoginDAO / isEmailPresent()...!");
				e2.printStackTrace();
			}
		}
		return objMobLoginBean;
	}

	/**
	 * @param objBean
	 * @param ipAddress
	 * @return
	 */
	public MobLoginBean validateAdminLogin(MobLoginBean objBean, String ipAddress) {
		Connection conn = objConnection.getConnection();
		MobLoginBean objMobLoginBean = new MobLoginBean();
		ResultSet rs1 = null;
		PreparedStatement pstmt = null;
		try {
			objMobLoginBean.setEmail(objBean.getEmail());
			objMobLoginBean.setPassword(objBean.getPassword());
			objMobLoginBean.setResult("invalidPassword");
			System.out.println("ip_address = " + ipAddress);

				// -------- Check for valid password --------
				String isValidPassordQuery = "SELECT u.user_id, u.name, u.is_active, u.user_type, u.password, "
						+ " im.ip_address,ifnull(u.language,'en') as language,consult_till_date,  "
						+ " substring(consult_till_date, 1,7) consult_till_date_alert"
						+ " FROM user u JOIN ip_mapping im on u.user_id = im.user_id "
						+ " WHERE email= ?  AND password = ?";
				pstmt = conn.prepareStatement(isValidPassordQuery);
				pstmt.setString(1, objBean.getEmail());
				pstmt.setString(2, objBean.getPassword());
				System.out.println("isValidPassordQuery: "+isValidPassordQuery);
				rs1 = pstmt.executeQuery();
				while (rs1.next()) {
					// ---------- Check IP is registered ----------
					if (rs1.getString("ip_address").equals(ipAddress) || rs1.getString("ip_address").equals("*")) {
						objMobLoginBean.setUserID(rs1.getInt("user_id"));
						objMobLoginBean.setResult("adminSuccess");
						objMobLoginBean.setMessage("Login Successful...!");
						objMobLoginBean.setUserType(rs1.getString("user_type"));
						objMobLoginBean.setLanguage(rs1.getString("language"));
						objMobLoginBean.setName(rs1.getString("name"));
						objMobLoginBean.setConsultTillDate(rs1.getString("consult_till_date"));
						objMobLoginBean.setConsultTillDateAlert(rs1.getString("consult_till_date_alert"));
						break;
					}else {
						objMobLoginBean.setResult("notRegistered");
						objMobLoginBean.setMessage("User is not register...!");
					}
				}
		} catch (SQLException e) {
			objMobLoginBean.setResult("error");
			System.out.println("Error: LoginDAO / validateAdminLogin()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				System.out
						.println("Error while closing Connection...! LoginDAO / validateAdminLogin()...!");
				e2.printStackTrace();
			}
		}
		return objMobLoginBean;
	}

	/**
	 * @param objBean
	 * @return
	 */
	public MobLoginBean validateNormalUserLogin(MobLoginBean objBean) {
		Connection conn = objConnection.getConnection();
		MobLoginBean objMobLoginBean = new MobLoginBean();
		ResultSet rs1 = null;
		PreparedStatement pstmt = null;
		try {
			objMobLoginBean.setEmail(objBean.getEmail());
			objMobLoginBean.setPassword(objBean.getPassword());
			objMobLoginBean.setDeviceId(objBean.getDeviceId());
				// -------- Check for valid password --------
				String isValidPassordQuery = "SELECT b.customer_type, a.user_id, a.customer_id, a.name, a.is_active, "
						+ " a.password, a.is_main_user, ifnull(a.language,'en') as language ,b.nat_or_internat,  "
						+ " if  ( to_date < DATE_FORMAT(now(),'%Y-%m'),'Expired','Live')  as userStatus , consult_till_date, "
						+ " substring(consult_till_date, 1,7) consult_till_date_alert "
						+ " FROM user a, customer b  "
						+ " WHERE a.customer_id = b.customer_id  AND a.email= ? AND a.password = ? ";
				pstmt = conn.prepareStatement(isValidPassordQuery);
				pstmt.setString(1, objBean.getEmail());
				pstmt.setString(2, objBean.getPassword());
				System.out.println("isValidPassordQuery: "+isValidPassordQuery);
				rs1 = pstmt.executeQuery();
				if (rs1.next()) {
					if (rs1.getString("is_active").equals("0")) {
						objMobLoginBean.setResult("inActiveUser");
						objMobLoginBean.setMessage("Inactive User, Please Contact Administrator...!");
					}else if(rs1.getString("userStatus").equalsIgnoreCase("expired")){
						objMobLoginBean.setResult("expired");
						objMobLoginBean.setMessage("Time Period Expired, Please Contact Administrator...!");
					} else {
						objMobLoginBean.setResult("userSuccess");
						objMobLoginBean.setMessage("Login Successful...!");
						objMobLoginBean.setUserID(rs1.getInt("user_id"));
						objMobLoginBean.setIsMainUser(rs1.getString("is_main_user"));
						objMobLoginBean.setCommerce(rs1.getString("nat_or_internat"));
						objMobLoginBean.setLanguage(rs1.getString("language"));
						objMobLoginBean.setUserType(rs1.getString("customer_type"));
						objMobLoginBean.setCustomerId(rs1.getInt("customer_id"));
						objMobLoginBean.setName(rs1.getString("name"));
						objMobLoginBean.setIsActive(rs1.getInt("is_active"));
						objMobLoginBean.setConsultTillDate(rs1.getString("consult_till_date"));
						objMobLoginBean.setConsultTillDateAlert(rs1.getString("consult_till_date_alert"));
					}
				} else {
					objMobLoginBean.setResult("invalidPassword");
					objMobLoginBean.setMessage("Please Check Password...!");
				}
		} catch (SQLException e) {
			objMobLoginBean.setResult("error");
			System.out.println("Error: LoginDAO / validateNormalUserLogin()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
			} catch (Exception e2) {
				System.out
						.println("Error while closing Connection...! LoginDAO / validateNormalUserLogin()...!");
				e2.printStackTrace();
			}
		}
		return objMobLoginBean;
	}

	public void storeDeviceId(MobLoginBean objBean) {
		Connection conn = objConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			String insertDeviceIDQuery = "REPLACE INTO gcm_register (device_id, user_id) VALUES(?,?);";
			pstmt = conn.prepareStatement(insertDeviceIDQuery);
			pstmt.setString(1, objBean.getDeviceId());
			pstmt.setInt(2, objBean.getUserID());
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error While Inserting Device ID...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<String> getDeviceIds(int userid) {
		ArrayList<String> objIdList = new ArrayList<String>();
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		try {
			String isUserPresentQuery = "SELECT device_id FROM gcm_register WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(isUserPresentQuery);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objIdList.add(rs.getString("device_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return objIdList;
	}
}
