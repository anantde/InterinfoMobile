package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mBean.MobDragonReportBean;
import mBean.MobInputFieldsBean;
import mBean.MobKeyValuePairBean;
import mConnection.MobMyConnection;

public class MobDragonReportDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MobDragonReportDao() {
		initalize();
	}
	public void initalize(){
		conn = new MobMyConnection().getConnection();
	}
	public void closeall()
	{
		try {
			if(conn!=null)
				conn.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
		} catch (Exception e) {
			System.out.println("Error while closing connection");
			e.printStackTrace();
		}	
	}

	public MobInputFieldsBean getAllDropdownData(String userID) {

		MobInputFieldsBean objFieldsBean = new MobInputFieldsBean();
		objFieldsBean.setHscodeList(getHscodeList(userID));
		objFieldsBean.setReportList(getFullDetailReportListForEdit(userID));
		return objFieldsBean;
	
	}
	private ArrayList<MobKeyValuePairBean> getFullDetailReportListForEdit(
			String userID) {
		System.out.println("Collecting Report List For Edit...!");
		ArrayList<MobKeyValuePairBean> reportList = new ArrayList<MobKeyValuePairBean>();
		ResultSet rs = null;
		MobKeyValuePairBean objPairBean = null;
		try {
			String getReportListQuery = "SELECT report_id, report_name FROM save_dragon_report WHERE user_id = ? ORDER BY report_name";
			PreparedStatement pstmt = conn.prepareStatement(getReportListQuery);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			System.out.println("REPORTS : "+pstmt);
			while (rs.next()) {
				objPairBean = new MobKeyValuePairBean();
				objPairBean.setKey(rs.getString("report_id"));
				objPairBean.setValue(rs.getString("report_name"));
				reportList.add(objPairBean);
			}
		} catch (SQLException e) {
			System.out.println("Error: StandardReportDAO/getReportListForEdit()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! StandardReportDAO / getReportListForEdit()...!");
				e2.printStackTrace();
			}
		}
		return reportList;
	}
	
	private ArrayList<MobKeyValuePairBean> getHscodeList(String userID) {System.out.println("Collecting Hscode List...!");
	ArrayList<MobKeyValuePairBean> hscodeList = new ArrayList<MobKeyValuePairBean>();
	ResultSet rs = null;
	MobKeyValuePairBean objPairBean = null;
	try {
		String getHscodeListQuery = "select a.user_id,a.hscode,ifnull(b.hscode_description,'No Description') hscode_description "
				+ " from user_hscode_mapping a left outer join hscode_master b on a.hscode = b.hscode "
				+ " where a.user_id = ?;";
		PreparedStatement pstmt = conn.prepareStatement(getHscodeListQuery);
		pstmt.setString(1, userID);
		rs = pstmt.executeQuery();
		System.out.println("QUERY : "+pstmt);
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
	return hscodeList;}
	public MobDragonReportBean createDragonReport(
			MobDragonReportBean objDragonReportBean) {
		
		try {

			String insertQuery = "insert into save_dragon_report(`report_name`,`hscode`,`user_id`,`import_export`,`created_date`,`modified_date`,`status`,fromPercentile)"
					+ "VALUES(?,?,?,?,now(),now(),0,?);";

			pstmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, objDragonReportBean.getReportName());
			pstmt.setString(2, objDragonReportBean.getHscode());
			pstmt.setString(3, objDragonReportBean.getUserID());
			pstmt.setString(4, objDragonReportBean.getImportExport());
			pstmt.setString(5, objDragonReportBean.getFromPercentile());
		
			//pstmt.setString(7, "excel");
			System.out.println("insertQuery in createDragonReport="+pstmt.toString());
			pstmt.executeUpdate();
			objDragonReportBean.setResult("created");
			rs = pstmt.getGeneratedKeys();
			if(rs.next())
				objDragonReportBean.setReport_id(rs.getInt(1));
				System.out.println("Last Inserted Id = "+objDragonReportBean.getReport_id());
			//ResultSet rs = pstmt.getGeneratedKeys();
		
		}
		catch (Exception e)
		{
			objDragonReportBean.setResult("error");
			System.out.println("createDragonReport is-" + e);
			e.printStackTrace();
		}
		return objDragonReportBean;
	
	
	}
	public MobDragonReportBean insertintoRunNow(int report_id) {
		
		try {
			conn.setAutoCommit(false);
			String insertCustQuery = "insert ignore into dragon_report_run_now(report_id)VALUES(?);";

			pstmt = conn.prepareStatement(insertCustQuery);
			pstmt.setInt(1, report_id);
			System.out.println("Report ID: " + report_id);
			System.out.println("insertQuery in insertintoRunNow="+pstmt.toString());
			pstmt.executeUpdate();
			conn.commit();
		
		}
		catch (Exception e)
		{
			System.out.println("insertintoRunNow is-" + e);
			e.printStackTrace();
		}
		
		return null;
	}
	public MobDragonReportBean getReportData(int reportId) {
		ResultSet rs = null;
		MobDragonReportBean objDragonReportBean=null;
		try {
			// String fetchQuery = "select * from country_lookup;";
			String fetchquery="select * from save_dragon_report where report_id=?;";
			pstmt = conn.prepareStatement(fetchquery);
			pstmt.setInt(1, reportId);
			rs = pstmt.executeQuery();
			System.out.println("Query in getReportData-"+pstmt.toString());
			while (rs.next()) {
				objDragonReportBean=new MobDragonReportBean();               
				objDragonReportBean.setReport_id(reportId);
				objDragonReportBean.setReportName(rs.getString("report_name"));
				objDragonReportBean.setHscode(rs.getString("hscode"));
				objDragonReportBean.setImportExport(rs.getString("import_export"));
				objDragonReportBean.setFromPercentile(rs.getString("fromPercentile"));
				// listCustomerProfileBeans.add(objCustomerProfileBean);
			}
		} catch (Exception e) {
			System.out.println("getReportData is-" + e);
			System.out.println("Connection Not Established...");
		}
		return objDragonReportBean;
	}
	public MobDragonReportBean editDragonReport(
			MobDragonReportBean objDragonReportBean) {
		try {
				conn.setAutoCommit(false);
				String insertCustQuery = "update save_dragon_report set report_name=?,hscode=?,import_export=?,modified_date=now(),status=0,fromPercentile=? "
						+ "where report_id=?";
				pstmt = conn.prepareStatement(insertCustQuery);
				pstmt.setString(1, objDragonReportBean.getReportName());
				pstmt.setString(2, objDragonReportBean.getHscode());
				pstmt.setString(3, objDragonReportBean.getImportExport());
				pstmt.setInt(5, objDragonReportBean.getReport_id());
				pstmt.setString(4, objDragonReportBean.getFromPercentile());
				//pstmt.setString(7, "excel");
				System.out.println("updateQuery in editDragonReport="+pstmt.toString());
				pstmt.executeUpdate();
				objDragonReportBean.setResult("updated");
				conn.commit();
				//ResultSet rs = pstmt.getGeneratedKeys();
			} catch(SQLException e){
				System.out.println("ERROR while updating...!");
				e.printStackTrace();
			} catch (Exception e)
			{
				objDragonReportBean.setResult("update error");
				System.out.println("editDragonReport is-" + e);
				e.printStackTrace();
			}
			return objDragonReportBean;
		
	}

}
