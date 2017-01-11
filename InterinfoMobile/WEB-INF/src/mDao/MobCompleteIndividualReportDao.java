package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mBean.MobCompleteIndividualBean;
import mConnection.MobMyConnection;

public class MobCompleteIndividualReportDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	/**
	 * 
	 */
	public MobCompleteIndividualReportDao() {
		initalize();
	}

	public void initalize() {
		conn = new MobMyConnection().getConnection();
	}
	public void closeAll() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println("Error while closing connection");
			e.printStackTrace();
		}
	}

	public MobCompleteIndividualBean createCompleteIndividualReport(
			MobCompleteIndividualBean objComIndividualBean) {
		try {
			String insertQuery = "insert into save_complete_individual_report ( "
					+ " report_name, user_id, rfc, from_year, to_year, created_date, status, possible_rfc ) " + " values(?, ?, ?, ?, ?, now(), 0, ?)"; // 8-2
																																						// =
																																						// 6
			pstmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, objComIndividualBean.getReportName());
			pstmt.setString(2, objComIndividualBean.getUserID());
			pstmt.setString(3, objComIndividualBean.getRfc());
			pstmt.setString(4, objComIndividualBean.getFromYear());
			pstmt.setString(5, objComIndividualBean.getToYear());
			pstmt.setString(6, objComIndividualBean.getIncludePossibleRfc());

			System.out.println("insertQuery Complete Individual Report: " + pstmt.toString());
			pstmt.executeUpdate();
			objComIndividualBean.setResult("created");
			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				objComIndividualBean.setReport_id(rs.getInt(1));
		} catch (Exception e) {
			objComIndividualBean.setResult("error");
			System.out.println("Error While Creating Complete Individual Report...!");
			e.printStackTrace();
		}
		return objComIndividualBean;
	}

	public MobCompleteIndividualBean insertintoRunNow(int report_id) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "INSERT IGNORE INTO complete_individual_report_run_now(report_id)VALUES(?)";
			pstmt = conn.prepareStatement(insertCustQuery);
			pstmt.setInt(1, report_id);
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.out.println("Error while insert into run now...!");
			e.printStackTrace();
		}
		return null;
	}

	public MobCompleteIndividualBean getRecentYearFromConfigurationTable() {
		MobCompleteIndividualBean objBean = new MobCompleteIndividualBean();
		ResultSet rs = null;
		try {
			String getCodeQuery = "SELECT name, max(SUBSTRING(name, -4)) AS year FROM configuration_details";
			PreparedStatement pstmt = conn.prepareStatement(getCodeQuery);
			System.out.println("Get Max Year From Configuration Query: " + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBean.setResult(rs.getString("year"));
			}
		} catch (SQLException e) {
			System.out.println("Error: CompleteIndividualReportDao/getRecentYearFromConfigurationTable()...!");
			e.printStackTrace();
		}
		return objBean;
	}

	public MobCompleteIndividualBean getReportData(int reportId) {

		ResultSet rs = null;
		MobCompleteIndividualBean objbBean = null;
		try {
			String fetchquery = "SELECT report_id, report_name, user_id, rfc, from_year, to_year," + " created_date, status, possible_rfc "
					+ " FROM save_complete_individual_report " + " WHERE report_id = ?";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setInt(1, reportId);
			rs = pstmt.executeQuery();
			System.out.println("EDIT DATA : "+pstmt);
			while (rs.next()) {
				objbBean = new MobCompleteIndividualBean();
				objbBean.setReport_id(reportId);
				objbBean.setReportName(rs.getString("report_name"));
				objbBean.setUserID(rs.getString("user_id"));
				objbBean.setRfc(rs.getString("rfc"));
				objbBean.setFromYear(rs.getString("from_year"));
				System.out.println("From Year : " + rs.getString("from_year"));
				objbBean.setToYear(rs.getString("to_year"));
				objbBean.setIncludePossibleRfc(rs.getString("possible_rfc"));
			}
		} catch (Exception e) {
			System.out.println("Error While Getting Report Details...!");
			e.printStackTrace();
		}
		return objbBean;
	
	}

	public MobCompleteIndividualBean editCompleteIndividualReport(
			MobCompleteIndividualBean objComIndividualBean) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "UPDATE save_complete_individual_report SET rfc = ?, from_year = ?, to_year = ?, "
					+ " modified_date = now(), status = 0, possible_rfc = ? " + " WHERE report_id = ?";
			
			System.out.println("RFC LIST : "+objComIndividualBean.getRfc());
			pstmt = conn.prepareStatement(insertCustQuery);
			
			boolean a = objComIndividualBean.getRfc().startsWith(", ");
			System.out.println("START WITH : "+a);
			
			if(a == true)
				pstmt.setString(1, objComIndividualBean.getRfc().substring(2));
			else
				pstmt.setString(1, objComIndividualBean.getRfc());
			
			pstmt.setString(2, objComIndividualBean.getFromYear());
			pstmt.setString(3, objComIndividualBean.getToYear());
			pstmt.setString(4, objComIndividualBean.getIncludePossibleRfc());
			pstmt.setInt(5, objComIndividualBean.getReport_id());
			System.out.println("Update Query: " + pstmt.toString());
			pstmt.executeUpdate();
			objComIndividualBean.setResult("updated");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("ERROR while updating...!");
			e.printStackTrace();
		} catch (Exception e) {
			objComIndividualBean.setResult("error");
			e.printStackTrace();
		}
		return objComIndividualBean;
	}
}
