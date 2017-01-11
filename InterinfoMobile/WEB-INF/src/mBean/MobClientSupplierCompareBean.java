package mBean;

import java.util.ArrayList;

public class MobClientSupplierCompareBean {

	private int report_id;
	private String reportName;
	private String rfc;
	private String userID;
	private String years;
	private String result;
	private String includePossibleRfc;
	private ArrayList<MobKeyValuePairBean> rfcList;
	private ArrayList<MobKeyValuePairBean> reportList;
	private String tillDate;
	private String infoBy;
	private String top;

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIncludePossibleRfc() {
		return includePossibleRfc;
	}

	public void setIncludePossibleRfc(String includePossibleRfc) {
		this.includePossibleRfc = includePossibleRfc;
	}


	public String getTillDate() {
		return tillDate;
	}

	public void setTillDate(String tillDate) {
		this.tillDate = tillDate;
	}

	public String getInfoBy() {
		return infoBy;
	}

	public void setInfoBy(String infoBy) {
		this.infoBy = infoBy;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public ArrayList<MobKeyValuePairBean> getRfcList() {
		return rfcList;
	}

	public void setRfcList(ArrayList<MobKeyValuePairBean> rfcList) {
		this.rfcList = rfcList;
	}

	public ArrayList<MobKeyValuePairBean> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<MobKeyValuePairBean> reportList) {
		this.reportList = reportList;
	}
	
	
}
