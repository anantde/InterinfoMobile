package mBean;

import java.util.ArrayList;

public class MobTopRFCBean {
	private int reportId;
	private String reportName;
	private String rfc;
	private String fromYear;
	private String toYear;
	private String userID;
	private String result;
	private String top;
	private ArrayList<MobKeyValuePairBean> rfcList;
	private ArrayList<MobKeyValuePairBean> reportList;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
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
