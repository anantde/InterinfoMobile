package mJsonDataSend;

import mBean.MobTopRFCBean;
import mResponse.Response;

public class MobRfcIEResponse extends Response {

	MobTopRFCBean result = new MobTopRFCBean();

	public MobTopRFCBean getResult() {
		return result;
	}

	public void setResult(MobTopRFCBean result) {
		this.result = result;
	}
	
}
