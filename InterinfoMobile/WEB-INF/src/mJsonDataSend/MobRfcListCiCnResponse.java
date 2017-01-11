package mJsonDataSend;

import mBean.MobCompleteIndividualBean;
import mResponse.Response;

public class MobRfcListCiCnResponse extends Response{

	MobCompleteIndividualBean result = new MobCompleteIndividualBean();

	public MobCompleteIndividualBean getResult() {
		return result;
	}

	public void setResult(MobCompleteIndividualBean result) {
		this.result = result;
	}
	
}
