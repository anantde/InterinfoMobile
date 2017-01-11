package mAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mBean.MobEmptyBean;
import mBean.MobTopRFCBean;
import mDao.MobStandardReportDAO;
import mDao.MobTopRfcDao;
import mJsonDataSend.MobEmptyResponse;
import mJsonDataSend.MobRfcIEResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
public class MobTopRFCReportAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession httpSession;
	HttpServletRequest request;
	private MobTopRFCBean objTopRFCBean;
	MobRfcIEResponse objIeResponse = new MobRfcIEResponse();
	MobEmptyResponse emptyResponse = new MobEmptyResponse();
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}
	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}
	public MobRfcIEResponse getObjIeResponse() {
		return objIeResponse;
	}
	public void setObjIeResponse(MobRfcIEResponse objIeResponse) {
		this.objIeResponse = objIeResponse;
	}
	public String loadTopRfcReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
			MobStandardReportDAO objDao = new MobStandardReportDAO();
			String userID = String.valueOf(httpSession.getAttribute("userId"));
			objTopRFCBean = new MobTopRFCBean();
			objTopRFCBean.setUserID(userID);
			objTopRFCBean.setRfcList(objDao.getRFCList(userID));
			objIeResponse.setCode("success");
			objIeResponse.setMessage("RFC list for  Top Import/Export Report by RFC report");
			objIeResponse.setResult(objTopRFCBean);
			return SUCCESS;
	}
	
	public String createTopRfcReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		objTopRFCBean = new Gson().fromJson(request.getParameter("data"), MobTopRFCBean.class);
		MobTopRfcDao objDao = new MobTopRfcDao();
		MobEmptyBean emptyBean = new MobEmptyBean();
		MobStandardReportDAO objStandardReportDAO = new MobStandardReportDAO();
		boolean isReportNamePresent = objStandardReportDAO.isStandardReportNamePresent(objTopRFCBean.getReportName(),objTopRFCBean.getUserID());
		emptyResponse.setResult(emptyBean);
		if (isReportNamePresent || objTopRFCBean.getReportName() == null) {
			addFieldError("errorFieldName", getText("Report_Name_Already_Exists_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Report_Name_Already_Exists_alert"));
		} else {
			objTopRFCBean = objDao.createTopRfcReport(objTopRFCBean);
			if (objTopRFCBean.getResult().toString().equals("created")) {
				objTopRFCBean = objDao.insertIntoRunNow(objTopRFCBean.getReportId());
				addFieldError("successFieldName", getText("Report_Saved_Successfully_alert"));
				emptyResponse.setCode("success");
				emptyResponse.setMessage(getText("Report_Saved_Successfully_alert"));
			} else {
				addFieldError("errorFieldName", getText("Error_in_saving_alert"));
				emptyResponse.setCode("error");
				emptyResponse.setMessage(getText("Error_in_saving_alert"));
			}
			objDao.closeAll();
		}
		return SUCCESS;
	}
	
	public String getTopRfcReportData() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		MobTopRfcDao objDao = new MobTopRfcDao();
		objTopRFCBean = objDao.getReportData(reportId);
		objDao.closeAll();
		objIeResponse.setCode("success");
		objIeResponse.setMessage("Select Report List..!");
		objIeResponse.setResult(objTopRFCBean);
		return SUCCESS;
	}
	
	public String EditTopRfcReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		objTopRFCBean = new Gson().fromJson(request.getParameter("data"), MobTopRFCBean.class);
		MobTopRfcDao objDao = new MobTopRfcDao();
		MobEmptyBean emptyBean = new MobEmptyBean();
		objTopRFCBean = objDao.editTopRFCReport(objTopRFCBean);
		emptyResponse.setResult(emptyBean);
		if (objTopRFCBean.getResult().toString().equals("updated")) {
			objTopRFCBean = objDao.insertIntoRunNow(objTopRFCBean.getReportId());
			addFieldError("successFieldName", getText("Report_updated_Successfully_alert"));
			emptyResponse.setCode("success");
			emptyResponse.setMessage(getText("Report_updated_Successfully_alert"));
		} else {
			addFieldError("errorFieldName", getText("Error_in_saving_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Error_in_saving_alert"));
		}
		objDao.closeAll();
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
	this.request =	request;
	}
	

}
