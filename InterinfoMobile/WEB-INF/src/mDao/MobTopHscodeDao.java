package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mBean.MobTopHSCODEBean;
import mConnection.MobMyConnection;

public class MobTopHscodeDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	/**
	 * 
	 */
	public MobTopHscodeDao() {
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
	public MobTopHSCODEBean saveTopHscodeReport(
			MobTopHSCODEBean objTopHSCODEBean, String valueOf) {
		try {
			String insertQuery = "INSERT INTO save_top_hscode_report (report_name, user_id, hscode, top, " + " from_year, to_year, created_date, status) "
					+ " VALUES(?, ?, ?, ?, ?, ?, now(), 0)";

			pstmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, objTopHSCODEBean.getReportName());
			pstmt.setString(2, objTopHSCODEBean.getUserID());
			pstmt.setString(3, objTopHSCODEBean.getHscode());
			pstmt.setString(4, objTopHSCODEBean.getTop());
			pstmt.setString(5, objTopHSCODEBean.getFromYear());
			pstmt.setString(6, objTopHSCODEBean.getToYear());
			System.out.println("Insert Query  Top Import/Export Report by HSCODE : " + pstmt.toString());
			pstmt.executeUpdate();
			objTopHSCODEBean.setResult("created");
			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				objTopHSCODEBean.setReportId(rs.getInt(1));
		} catch (Exception e) {
			objTopHSCODEBean.setResult("error");
			System.out.println("Error While Creating  Top Import/Export Report by RFC...!");
			e.printStackTrace();
		}
		return objTopHSCODEBean;
	}

	public MobTopHSCODEBean insertIntoRunNow(int reportId) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "INSERT IGNORE INTO top_hscode_report_run_now(report_id)VALUES(?)";
			pstmt = conn.prepareStatement(insertCustQuery);
			pstmt.setInt(1, reportId);
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			System.out.println("Error while insert into top_rfc_report_run_now...!");
			e.printStackTrace();
		}
		return null;
	}

	public MobTopHSCODEBean getReportData(int reportId) {
		ResultSet rs = null;
		MobTopHSCODEBean objbBean = null;
		try {
			String fetchquery = "SELECT report_id, report_name, user_id, hscode, from_year, to_year, " + " created_date, status, top "
					+ " FROM save_top_hscode_report WHERE report_id = ?";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setInt(1, reportId);
			rs = pstmt.executeQuery();
			System.out.println("Get Top Import/Export Report by HSCODE Data To Edit: " + pstmt);
			while (rs.next()) {
				objbBean = new MobTopHSCODEBean();
				objbBean.setReportId(reportId);
				objbBean.setReportName(rs.getString("report_name"));
				objbBean.setUserID(rs.getString("user_id"));
				objbBean.setHscode(rs.getString("hscode"));
				objbBean.setFromYear(rs.getString("from_year"));
				System.out.println("From Year : " + rs.getString("from_year"));
				objbBean.setToYear(rs.getString("to_year"));
				objbBean.setTop(rs.getString("top"));
			}
		} catch (Exception e) {
			System.out.println("Error While Getting Report Details...!");
			e.printStackTrace();
		}
		return objbBean;
	}

	public MobTopHSCODEBean editTopHscodeReport(
			MobTopHSCODEBean objTopHSCODEBean) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "UPDATE save_top_hscode_report SET hscode = ?, from_year = ?, "
					+ " to_year = ?, modified_date = now(), status = 0, top = ? " + " WHERE report_id = ?";

			System.out.println("RFC LIST : " + objTopHSCODEBean.getHscode());
			pstmt = conn.prepareStatement(insertCustQuery);

			boolean a = objTopHSCODEBean.getHscode().startsWith(", ");
			System.out.println("START WITH : " + a);

			if (a == true)
				pstmt.setString(1, objTopHSCODEBean.getHscode().substring(2));
			else
				pstmt.setString(1, objTopHSCODEBean.getHscode());

			pstmt.setString(2, objTopHSCODEBean.getFromYear());
			pstmt.setString(3, objTopHSCODEBean.getToYear());
			pstmt.setString(4, objTopHSCODEBean.getTop());
			pstmt.setInt(5, objTopHSCODEBean.getReportId());
			System.out.println("Update Query: " + pstmt.toString());
			pstmt.executeUpdate();
			objTopHSCODEBean.setResult("updated");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("ERROR while updating...!");
			e.printStackTrace();
		} catch (Exception e) {
			objTopHSCODEBean.setResult("error");
			e.printStackTrace();
		}
		return objTopHSCODEBean;
	}

}
