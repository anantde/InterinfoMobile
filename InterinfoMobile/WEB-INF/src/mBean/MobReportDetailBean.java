package mBean;


public class MobReportDetailBean {
	private boolean isEditable;
	private boolean isDeletable;
	private String reportName;
	private String reportID;
	private String createdDate;
	private String reportType;
	public int status;
	private String createdByAdmin;

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public boolean isDeletable() {
		return isDeletable;
	}

	public void setDeletable(boolean isDeletable) {
		this.isDeletable = isDeletable;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreatedByAdmin() {
		return createdByAdmin;
	}

	public void setCreatedByAdmin(String createdByAdmin) {
		this.createdByAdmin = createdByAdmin;
	}

}
