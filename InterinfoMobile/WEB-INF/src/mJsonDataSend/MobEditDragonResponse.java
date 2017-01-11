package mJsonDataSend;

import mBean.MobDragonReportBean;
import mResponse.Response;

public class MobEditDragonResponse extends Response {

	MobDragonReportBean result = new MobDragonReportBean();
	public MobDragonReportBean getResult() {
		return result;
	}
	public void setResult(MobDragonReportBean result) {
		this.result = result;
	}
	
}
