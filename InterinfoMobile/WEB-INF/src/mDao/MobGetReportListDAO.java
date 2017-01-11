package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import mBean.MobKeyValuePairBean;
import mConnection.MobMyConnection;

public class MobGetReportListDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public MobGetReportListDAO() {
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

	public ArrayList<MobKeyValuePairBean> getUserReports(String userID,
			String tableName) {
		ResultSet rs = null;
		ArrayList<MobKeyValuePairBean> list = new ArrayList<MobKeyValuePairBean>();
		MobKeyValuePairBean objKeyValuePairBean = null;
		try {
			String fetchquery = "select report_id,report_name from "+tableName+" where user_id=?;";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			System.out.println("Query in getAllReportList-" + pstmt.toString());
			while (rs.next()) {
				objKeyValuePairBean = new MobKeyValuePairBean();
				objKeyValuePairBean.setKey(rs.getString("report_id"));
				objKeyValuePairBean.setValue(rs.getString("report_name"));
				list.add(objKeyValuePairBean);
			}
		} catch (Exception e) {
			System.out.println("getAllReportList is-" + e);
			System.out.println("Connection Not Established...");
		}
		return list;
	}

	

	
}
