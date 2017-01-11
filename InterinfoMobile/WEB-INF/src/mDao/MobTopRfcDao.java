package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mBean.MobTopRFCBean;
import mConnection.MobMyConnection;

public class MobTopRfcDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	/**
	 * 
	 */
	public MobTopRfcDao() {
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
	public MobTopRFCBean createTopRfcReport(MobTopRFCBean objTopRFCBean) {
		try {
			String insertQuery = "INSERT INTO save_top_rfc_report (report_name, user_id, rfc, top, " + " from_year, to_year, created_date, status) "
					+ " VALUES(?, ?, ?, ?, ?, ?, now(), 0)";

			pstmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, objTopRFCBean.getReportName());
			pstmt.setString(2, objTopRFCBean.getUserID());
			pstmt.setString(3, objTopRFCBean.getRfc());
			pstmt.setString(4, objTopRFCBean.getTop());
			pstmt.setString(5, objTopRFCBean.getFromYear());
			pstmt.setString(6, objTopRFCBean.getToYear());
			System.out.println("Insert Query  Top Import/Export Report by RFC : " + pstmt.toString());
			pstmt.executeUpdate();
			objTopRFCBean.setResult("created");
			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				objTopRFCBean.setReportId(rs.getInt(1));
		} catch (Exception e) {
			objTopRFCBean.setResult("error");
			System.out.println("Error While Creating  Top Import/Export Report by RFC...!");
			e.printStackTrace();
		}
		return objTopRFCBean;
	}

	public MobTopRFCBean insertIntoRunNow(int reportId) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "INSERT IGNORE INTO top_rfc_report_run_now(report_id)VALUES(?)";
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

	public MobTopRFCBean getReportData(int reportId) {

		ResultSet rs = null;
		MobTopRFCBean objbBean = null;
		try {
			String fetchquery = "SELECT report_id, report_name, user_id, rfc, from_year, to_year, " + " created_date, status, top "
					+ " FROM save_top_rfc_report WHERE report_id = ?";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setInt(1, reportId);
			rs = pstmt.executeQuery();
			System.out.println("Get  Top Import/Export Report by RFC Data To Edit: " + pstmt);
			while (rs.next()) {
				objbBean = new MobTopRFCBean();
				objbBean.setReportId(reportId);
				objbBean.setReportName(rs.getString("report_name"));
				objbBean.setUserID(rs.getString("user_id"));
				objbBean.setRfc(rs.getString("rfc"));
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

	public MobTopRFCBean editTopRFCReport(MobTopRFCBean objTopRFCBean) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "UPDATE save_top_rfc_report SET rfc = ?, from_year = ?, "
					+ " to_year = ?, modified_date = now(), status = 0, top = ? " + " WHERE report_id = ?";

			System.out.println("RFC LIST : " + objTopRFCBean.getRfc());
			pstmt = conn.prepareStatement(insertCustQuery);

			boolean a = objTopRFCBean.getRfc().startsWith(", ");
			System.out.println("START WITH : " + a);

			if (a == true)
				pstmt.setString(1, objTopRFCBean.getRfc().substring(2));
			else
				pstmt.setString(1, objTopRFCBean.getRfc());

			pstmt.setString(2, objTopRFCBean.getFromYear());
			pstmt.setString(3, objTopRFCBean.getToYear());
			pstmt.setString(4, objTopRFCBean.getTop());
			pstmt.setInt(5, objTopRFCBean.getReportId());
			System.out.println("Update Query: " + pstmt.toString());
			pstmt.executeUpdate();
			objTopRFCBean.setResult("updated");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("ERROR while updating...!");
			e.printStackTrace();
		} catch (Exception e) {
			objTopRFCBean.setResult("error");
			e.printStackTrace();
		}
		return objTopRFCBean;
	}

}
