package mJsonDataSend;

import mBean.MobEmptyBean;
import mResponse.Response;

public class MobEmptyResponse extends Response{
  MobEmptyBean result = new MobEmptyBean();

public MobEmptyBean getResult() {
	return result;
}

public void setResult(MobEmptyBean result) {
	this.result = result;
}

}
