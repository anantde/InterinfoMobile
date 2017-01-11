package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mBean.MobClientSupplierCompareBean;
import mConnection.MobMyConnection;

public class MobClientSupplierCompareDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public MobClientSupplierCompareDao() {
		initalize();
	}
	
	public void initalize() {
		conn = new MobMyConnection().getConnection();
	}
	
	public void closeall() {
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
	public MobClientSupplierCompareBean createClientSupplierCompareReport(
			MobClientSupplierCompareBean objClientSupplierCompareBean) {
		try {
			String insertQuery = "insert into nc_rfc_client_supplier_compare(`report_name`,`rfc`,`user_id`,`num_of_years`,`created_date`,"
					+ " `modified_date`,`status`, possible_rfc, till_date, info_by, top)" + " VALUES(?,?,?,?,now(),now(),0, ?, LAST_DAY(?), ?, ?);";

			pstmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, objClientSupplierCompareBean.getReportName());
			pstmt.setString(2, objClientSupplierCompareBean.getRfc());
			pstmt.setString(3, objClientSupplierCompareBean.getUserID());
			pstmt.setString(4, objClientSupplierCompareBean.getYears());
			pstmt.setString(5, objClientSupplierCompareBean.getIncludePossibleRfc());
			pstmt.setString(6, objClientSupplierCompareBean.getTillDate());
			pstmt.setString(7, objClientSupplierCompareBean.getInfoBy());
			pstmt.setString(8, objClientSupplierCompareBean.getTop());
			System.out.println("Raj Create Client Supplier Compare: " + pstmt.toString());
			pstmt.executeUpdate();
			objClientSupplierCompareBean.setResult("created");
			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				objClientSupplierCompareBean.setReport_id(rs.getInt(1));
			System.out.println("Last Inserted Id = " + objClientSupplierCompareBean.getReport_id());
		} catch (Exception e) {
			objClientSupplierCompareBean.setResult("error");
			System.out.println("createClientSupplierCompareReport is-" + e);
			e.printStackTrace();
		}
		return objClientSupplierCompareBean;
	}

	public MobClientSupplierCompareBean insertintoRunNow(int report_id) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "insert ignore into nc_rfc_client_supplier_compare_run_now(report_id)VALUES(?);";

			pstmt = conn.prepareStatement(insertCustQuery);
			pstmt.setInt(1, report_id);
			System.out.println("Report ID: " + report_id);
			System.out.println("insertQuery in insertintoRunNow=" + pstmt.toString());
			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			System.out.println("insertintoRunNow is-" + e);
			e.printStackTrace();
		}

		return null;
	}

	public MobClientSupplierCompareBean getReportData(int reportId) {

		ResultSet rs = null;
		MobClientSupplierCompareBean objClientSupplierCompareBean = null;
		try {
			String fetchquery = "SELECT report_name, rfc, num_of_years, possible_rfc,"
					+ " substring(till_date, 1,7) as till_date, info_by, top"
					+ " FROM nc_rfc_client_supplier_compare WHERE report_id = ?";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setInt(1, reportId);
			rs = pstmt.executeQuery();
			System.out.println("Query in getReportData-" + pstmt.toString());
			while (rs.next()) {
				objClientSupplierCompareBean = new MobClientSupplierCompareBean();
				objClientSupplierCompareBean.setReport_id(reportId);
				objClientSupplierCompareBean.setReportName(rs.getString("report_name"));
				objClientSupplierCompareBean.setRfc(rs.getString("rfc"));
				objClientSupplierCompareBean.setYears(rs.getString("num_of_years"));
				objClientSupplierCompareBean.setIncludePossibleRfc(rs.getString("possible_rfc"));
				objClientSupplierCompareBean.setTillDate(rs.getString("till_date"));
				objClientSupplierCompareBean.setInfoBy(rs.getString("info_by"));
				objClientSupplierCompareBean.setTop(rs.getString("top"));
			}
		} catch (Exception e) {
			System.out.println("getReportData is-" + e);
			System.out.println("Connection Not Established...");
		}
		return objClientSupplierCompareBean;
	
	}

	public MobClientSupplierCompareBean editClientSupplierCompare(
			MobClientSupplierCompareBean objClientSupplierCompareBean) {
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "update nc_rfc_client_supplier_compare set report_name=?,rfc=?,"
					+ " modified_date=now(),status=0,num_of_years=?, possible_rfc = ?, till_date = LAST_DAY(?)," + " info_by = ?, top = ?"
					+ " where report_id=?";
			pstmt = conn.prepareStatement(insertCustQuery);
			pstmt.setString(1, objClientSupplierCompareBean.getReportName());
			pstmt.setString(2, objClientSupplierCompareBean.getRfc());
			pstmt.setString(3, objClientSupplierCompareBean.getYears());
			pstmt.setString(4, objClientSupplierCompareBean.getIncludePossibleRfc());
			pstmt.setString(5, objClientSupplierCompareBean.getTillDate());
			pstmt.setString(6, objClientSupplierCompareBean.getInfoBy());
			pstmt.setString(7, objClientSupplierCompareBean.getTop());
			pstmt.setInt(8, objClientSupplierCompareBean.getReport_id());
			System.out.println("updateQuery in editClientSupplierCompare=" + pstmt.toString());
			pstmt.executeUpdate();
			objClientSupplierCompareBean.setResult("updated");
			conn.commit();
		} catch (SQLException e) {
			System.out.println("ERROR while updating...!");
			e.printStackTrace();
		} catch (Exception e) {
			objClientSupplierCompareBean.setResult("update error");
			System.out.println("editClientSupplierCompare is-" + e);
			e.printStackTrace();
		}
		return objClientSupplierCompareBean;
	}

}
