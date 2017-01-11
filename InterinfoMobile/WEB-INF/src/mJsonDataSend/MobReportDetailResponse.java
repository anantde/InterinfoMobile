package mJsonDataSend;
import mBean.MobReportListBean;
import mResponse.Response;
public class MobReportDetailResponse extends Response {
	private MobReportListBean result = new MobReportListBean();
	public MobReportListBean getResult() {
		return result;
	}
	public void setResult(MobReportListBean result) {
		this.result = result;
	}
}
