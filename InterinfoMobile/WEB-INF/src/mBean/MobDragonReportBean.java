package mBean;

public class MobDragonReportBean {

	
	
	private int report_id;
	private String reportName;
	private String hscode;
	private String userID;
	private String result;
	private String importExport;
	private String fromPercentile;
	private String toPercentile;
	
	public String getFromPercentile() {
		return fromPercentile;
	}
	public void setFromPercentile(String fromPercentile) {
		this.fromPercentile = fromPercentile;
	}
	public String getToPercentile() {
		return toPercentile;
	}
	public void setToPercentile(String toPercentile) {
		this.toPercentile = toPercentile;
	}
	public String getImportExport() {
		return importExport;
	}
	public void setImportExport(String importExport) {
		this.importExport = importExport;
	}
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
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
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
}
