package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mBean.MobKeyValuePairBean;
import mConnection.MobMyConnection;

public class MobStandardReportDAO {
	MobMyConnection objConnection = new MobMyConnection();

	public ArrayList<MobKeyValuePairBean> getInformant(String userID) {
		ArrayList<MobKeyValuePairBean> objArrayList = new ArrayList<MobKeyValuePairBean>();
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		MobKeyValuePairBean objPairBean = null;
		try {
			String getListQuery = "select a.irs,ifnull(b.razon,'No Description')razon "
					+ " from user_irs_mapping a left outer join  "
					+ " interinfo.rfc_lookup_backup b on a.irs = b.rfc where user_id = ?  order by irs ";

			PreparedStatement pstmt = conn.prepareStatement(getListQuery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			System.out.println("Query: "+ pstmt.toString());
			while (rs.next()) {
				objPairBean = new MobKeyValuePairBean();
				objPairBean.setKey(rs.getString("irs"));
				objPairBean.setValue(rs.getString("irs") + "(" + rs.getString("razon") + ")");
				objArrayList.add(objPairBean);
			}
			if (objArrayList.size() == 0) {
				objPairBean = new MobKeyValuePairBean();
			}
		} catch (SQLException e) {
			System.out.println("Error: StandardReportDAO/getInformant()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! StandardReportDAO / getInformant()...!");
				e2.printStackTrace();
			}
		}
		return objArrayList;
	}
	public boolean isStandardReportNamePresent(String reportName, String userID) {
		boolean isReportNamePresent = false;
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		try {
			reportName = reportName.replace(" ", "").toUpperCase();
				String isReportNamePresentQuery = "SELECT REPLACE(report_name, ' ', '') as report_name  "
						+ " FROM save_standard_report_nc WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_standard_report_inc WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM international_custom_report1 WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM international_custom_report2 WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM national_custom_report1 WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM inc_rfc_top_hscode WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM nc_rfc_top_client_supplier WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM nc_rfc_client_supplier_compare WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_complete_individual_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_dragon_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_credit_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_quintile_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_competitive_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_customer_supplier_by_activity WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ?"
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_top_rfc_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? "
						+ " UNION SELECT REPLACE(report_name, ' ', '') as report_name "
						+ " FROM save_top_hscode_report WHERE REPLACE(report_name, ' ', '') = ? AND user_id = ? ";
			
			PreparedStatement pstmt = conn.prepareStatement(isReportNamePresentQuery);
			pstmt.setString(1, reportName);
			pstmt.setString(2, userID);
			pstmt.setString(3, reportName);
			pstmt.setString(4, userID);
			pstmt.setString(5, reportName);
			pstmt.setString(6, userID);
			pstmt.setString(7, reportName);
			pstmt.setString(8, userID);
			pstmt.setString(9, reportName);
			pstmt.setString(10, userID);
			pstmt.setString(11, reportName);
			pstmt.setString(12, userID);
			pstmt.setString(13, reportName);
			pstmt.setString(14, userID);
			pstmt.setString(15, reportName);
			pstmt.setString(16, userID);
			pstmt.setString(17, reportName);
			pstmt.setString(18, userID);
			pstmt.setString(19, reportName);
			pstmt.setString(20, userID);
			pstmt.setString(21, reportName);
			pstmt.setString(22, userID);
			pstmt.setString(23, reportName);
			pstmt.setString(24, userID);
			pstmt.setString(25, reportName);
			pstmt.setString(26, userID);
			pstmt.setString(27, reportName);
			pstmt.setString(28, userID);
			pstmt.setString(29, reportName);
			pstmt.setString(30, userID);
			pstmt.setString(31, reportName);
			pstmt.setString(32, userID);
			System.out.println("isReportNamePresentQuery  "+pstmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				isReportNamePresent = true;
			}
		} catch (SQLException e) {
			isReportNamePresent = true;
			System.out.println("Error: SatandardReportDAO/isReportNamePresent()...!");
			e.printStackTrace();
		} catch (Exception e) {
			isReportNamePresent = true;
			System.out.println("Error: SatandardReportDAO/isReportNamePresent()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! CustomReport / isReportNamePresent()...!");
				e2.printStackTrace();
			}
		}
		return isReportNamePresent;
	}

	public ArrayList<MobKeyValuePairBean> getRFCList(String userID) {
		ArrayList<MobKeyValuePairBean> rfcList = new ArrayList<MobKeyValuePairBean>();
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		MobKeyValuePairBean objPairBean = null;
		try {
			String getIRScodeListQuery = "select a.user_id,a.irs,ifnull(b.razon,'No Description') razon "
					+ "from user_irs_mapping_inc a left outer join interinfo.rfc_lookup_backup b  "
					+ " on  a.irs = b.rfc where user_id = ? ";

			PreparedStatement pstmt = conn.prepareStatement(getIRScodeListQuery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			System.out.println("@@ QUERY : "+pstmt);
			
			while (rs.next()) {
				objPairBean = new MobKeyValuePairBean();
				objPairBean.setKey(rs.getString("irs"));
				objPairBean.setValue(rs.getString("irs") + "(" + rs.getString("razon") + ")");
				rfcList.add(objPairBean);
			}
		} catch (SQLException e) {
			System.out.println("Error: StandardReportDAO/getRFCList()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! StandardReportDAO / getRFCList()...!");
				e2.printStackTrace();
			}
		}
		return rfcList;
	}

	public ArrayList<MobKeyValuePairBean> getHscodeList(String userID) {
		System.out.println("Collecting Hscode List...!");
		ArrayList<MobKeyValuePairBean> hscodeList = new ArrayList<MobKeyValuePairBean>();
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		MobKeyValuePairBean objPairBean = null;
		try {
			// String getHscodeListQuery =
			// "SELECT user_id, hscode FROM user_hscode_mapping WHERE user_id = ?";
			String getHscodeListQuery = "select a.user_id,a.hscode,ifnull(b.hscode_description,'No Description') hscode_description "
					+ " from user_hscode_mapping a left outer join hscode_master b on a.hscode = b.hscode "
					+ " where a.user_id = ? ;";
			PreparedStatement pstmt = conn.prepareStatement(getHscodeListQuery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objPairBean = new MobKeyValuePairBean();
				objPairBean.setKey(rs.getString("hscode"));
				objPairBean.setValue(rs.getString("hscode") + "(" + rs.getString("hscode_description") + ")");
				hscodeList.add(objPairBean);
			}
		} catch (SQLException e) {
			System.out.println("Error: StandardReportDAO/getTaxNoList()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! StandardReportDAO / getTaxNoList()...!");
				e2.printStackTrace();
			}
		}
		return hscodeList;
	}
}
