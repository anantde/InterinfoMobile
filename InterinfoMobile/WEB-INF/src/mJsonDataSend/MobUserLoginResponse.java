package mJsonDataSend;

import mBean.MobLoginBean;
import mResponse.Response;

public class MobUserLoginResponse extends Response {
	MobLoginBean result = new MobLoginBean();

	public MobLoginBean getResult() {
		return result;
	}

	public void setResult(MobLoginBean result) {
		this.result = result;
	}

}
