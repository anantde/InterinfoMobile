package mAction;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mBean.MobKeyValuePairBean;
import mBean.MobUserReportEditListbean;
import mDao.MobGetReportListDAO;
import mJsonDataSend.MobReportListResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.opensymphony.xwork2.ActionSupport;
public class MobGetReportList extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpSession httpSession;
	HttpServletRequest request;
	MobReportListResponse listResponse = new MobReportListResponse();
	public MobReportListResponse getListResponse() {
		return listResponse;
	}
	public void setListResponse(MobReportListResponse listResponse) {
		this.listResponse = listResponse;
	}

	public String getReportListForEdit() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession();
		String reportType =request.getParameter("reportType");
		String userID = String.valueOf(httpSession.getAttribute("userId"));
		String tableName = null;
		String tableNameRunNow = null;
		System.out.println("reportType :::::::::"+reportType);
		if (reportType.equalsIgnoreCase("National Query")) {
			tableName = "save_standard_report_nc";
			tableNameRunNow = "standard_report_nc_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Query (Standard)")
				|| reportType
						.equalsIgnoreCase("International Query (Extended)")
				|| reportType
						.equalsIgnoreCase("International Query Monthly (Standard)")
				|| reportType
						.equalsIgnoreCase("International Query Monthly (Extended)")) {
			tableName = "save_standard_report_inc";
			tableNameRunNow = "standard_report_inc_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Table One Variable")) {
			tableName = "international_custom_report1";
			tableNameRunNow = "international_custom_report1_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Table Two Variable")) {
			tableName = "international_custom_report2";
			tableNameRunNow = "international_custom_report2_run_now";
		} else if (reportType.equalsIgnoreCase("National Table")) {
			tableName = "national_custom_report1";
			tableNameRunNow = "national_custom_report1_run_now";
		} else if (reportType.equalsIgnoreCase("Top HSCODE Group Report")) {
			tableName = "inc_rfc_top_hscode";
			tableNameRunNow = "inc_rfc_top_hscode_run_now";
		} else if (reportType.equalsIgnoreCase("Credit Report")) {
			tableName = "save_credit_report";
			tableNameRunNow = "credit_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Customer Supplier Group Report")) {
			tableName = "nc_rfc_top_client_supplier";
			tableNameRunNow = "nc_rfc_top_client_supplier_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Customer Supplier Report By RFC")) {
			tableName = "nc_rfc_client_supplier_compare";
			tableNameRunNow = "nc_rfc_client_supplier_compare_run_now";
		} else if (reportType.equalsIgnoreCase("Full Detail Report")) {
			tableName = "save_dragon_report";
			tableNameRunNow = "dragon_report_run_now";
		} else if (reportType.equalsIgnoreCase("Quintile Report")) {
			tableName = "save_quintile_report";
			tableNameRunNow = "quintile_report_run_now";
		} else if (reportType.equalsIgnoreCase("Example CI and CN Report")) {
			tableName = "save_complete_individual_report";
			tableNameRunNow = "complete_individual_report_run_now";
		} else if (reportType.equalsIgnoreCase("Competitive Report")) {
			tableName = "save_competitive_report";
			tableNameRunNow = "competitive_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Import/Export Report by RFC")) {
			tableName = "save_top_rfc_report";
			tableNameRunNow = "top_rfc_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Import/Export Report by HSCODE")) {
			tableName = "save_top_hscode_report";
			tableNameRunNow = "top_hscode_report_run_now";
		}
		System.out.println("Table Name: " + tableName);
		System.out.println("table name runnow::::::::"+tableNameRunNow);
		MobGetReportListDAO objListDAO = new MobGetReportListDAO();
		ArrayList<MobKeyValuePairBean> editReportList = objListDAO.getUserReports(userID,tableName);
		objListDAO.closeall();
		MobUserReportEditListbean editListbean = new MobUserReportEditListbean();
		editListbean.setReportList(editReportList);
		listResponse.setCode("success");
		listResponse.setMessage("Report List..!");
		listResponse.setResult(editListbean);
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
	this.request = request;	
	}

	
}
