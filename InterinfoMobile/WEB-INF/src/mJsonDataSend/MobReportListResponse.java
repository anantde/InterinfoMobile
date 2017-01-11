package mJsonDataSend;

import mBean.MobUserReportEditListbean;
import mResponse.Response;

public class MobReportListResponse extends Response {
	
	MobUserReportEditListbean result= new MobUserReportEditListbean();
	public MobUserReportEditListbean getResult() {
		return result;
	}
	public void setResult(MobUserReportEditListbean result) {
		this.result = result;
	}
	
}
