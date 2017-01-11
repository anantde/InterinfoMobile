package mJsonDataSend;

import mBean.MobInputFieldsBean;
import mResponse.Response;

public class MobDragonReportResponse  extends Response{

	MobInputFieldsBean result = new MobInputFieldsBean();
	public MobInputFieldsBean getResult() {
		return result;
	}
	public void setResult(MobInputFieldsBean result) {
		this.result = result;
	}
	
	
}
