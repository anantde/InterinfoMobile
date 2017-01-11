package mBean;

import java.util.ArrayList;

public class MobUserReportEditListbean {

	ArrayList<MobKeyValuePairBean> reportList = new ArrayList<MobKeyValuePairBean>();

	public ArrayList<MobKeyValuePairBean> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<MobKeyValuePairBean> reportList) {
		this.reportList = reportList;
	}
	
}
