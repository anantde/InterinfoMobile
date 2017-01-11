package mJsonDataSend;

import mBean.MobTopHSCODEBean;
import mResponse.Response;

public class MobHscodeIEResponse extends Response {
MobTopHSCODEBean result = new MobTopHSCODEBean();

public MobTopHSCODEBean getResult() {
	return result;
}

public void setResult(MobTopHSCODEBean result) {
	this.result = result;
}

}
