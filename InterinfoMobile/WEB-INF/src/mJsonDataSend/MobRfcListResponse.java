package mJsonDataSend;

import mBean.MobClientSupplierCompareBean;
import mResponse.Response;

public class MobRfcListResponse extends Response{

	MobClientSupplierCompareBean result=new MobClientSupplierCompareBean();

	public MobClientSupplierCompareBean getResult() {
		return result;
	}

	public void setResult(MobClientSupplierCompareBean result) {
		this.result = result;
	}
}
