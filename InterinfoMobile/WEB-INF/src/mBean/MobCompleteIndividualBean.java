package mBean;

import java.util.ArrayList;

public class MobCompleteIndividualBean {
	private int report_id;
	private String reportName;
	private String rfc;
	private String fromYear;
	private String toYear;
	private String userID;
	private String result;
	private String includePossibleRfc;
	private ArrayList<MobKeyValuePairBean> rfcList;
	private ArrayList<MobKeyValuePairBean> reportList;

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

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public ArrayList<MobKeyValuePairBean> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<MobKeyValuePairBean> reportList) {
		this.reportList = reportList;
	}

	public ArrayList<MobKeyValuePairBean> getRfcList() {
		return rfcList;
	}

	public void setRfcList(ArrayList<MobKeyValuePairBean> rfcList) {
		this.rfcList = rfcList;
	}
	
	
	
}
