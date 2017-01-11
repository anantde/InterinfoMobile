package mAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mBean.MobEmptyBean;
import mBean.MobTopHSCODEBean;
import mDao.MobStandardReportDAO;
import mDao.MobTopHscodeDao;
import mJsonDataSend.MobEmptyResponse;
import mJsonDataSend.MobHscodeIEResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class MobTopHscodeNewReportAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession httpSession;
	HttpServletRequest request;
	private MobTopHSCODEBean objTopHSCODEBean;
	MobHscodeIEResponse objHscodeIEResponse = new MobHscodeIEResponse();
	MobEmptyResponse emptyResponse = new MobEmptyResponse();
	
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}
	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}
	public MobHscodeIEResponse getObjHscodeIEResponse() {
		return objHscodeIEResponse;
	}
	public void setObjHscodeIEResponse(MobHscodeIEResponse objHscodeIEResponse) {
		this.objHscodeIEResponse = objHscodeIEResponse;
	}

	public String loadHscodeReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
			MobStandardReportDAO objDao = new MobStandardReportDAO();
			String userID = String.valueOf(httpSession.getAttribute("userId"));
			objTopHSCODEBean = new MobTopHSCODEBean();
			objTopHSCODEBean.setUserID(userID);
			objTopHSCODEBean.setHscodeList(objDao.getHscodeList(userID));
			objHscodeIEResponse.setCode("success");
			objHscodeIEResponse.setMessage("HSCODE List for Top Import/Export Report by HSCODE Report..!");
			objHscodeIEResponse.setResult(objTopHSCODEBean);
			return SUCCESS;
	}
	
	public String createTopHscodeReport(){
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		objTopHSCODEBean = new Gson().fromJson(request.getParameter("data"), MobTopHSCODEBean.class);
		MobTopHscodeDao objTopHscodeDao = new MobTopHscodeDao();
		MobEmptyBean emptyBean =new MobEmptyBean();
		System.out.println("HSCODE : "+objTopHSCODEBean.getHscode());
		MobStandardReportDAO objStandardReportDAO = new MobStandardReportDAO();
		boolean isReportNamePresent = objStandardReportDAO.isStandardReportNamePresent(objTopHSCODEBean.getReportName(),objTopHSCODEBean.getUserID());
		System.out.println("isReportNamePresent: " + isReportNamePresent);
		emptyResponse.setResult(emptyBean);
		if (isReportNamePresent || objTopHSCODEBean.getReportName() == null) {
			addFieldError("errorFieldName", getText("Report_Name_Already_Exists_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Report_Name_Already_Exists_alert"));
		} else {
			objTopHSCODEBean = objTopHscodeDao.saveTopHscodeReport(objTopHSCODEBean, String.valueOf(httpSession.getAttribute("userType")));
			if (objTopHSCODEBean.getResult().toString().equals("created")) {
				objTopHSCODEBean = objTopHscodeDao.insertIntoRunNow(objTopHSCODEBean.getReportId());
				addFieldError("successFieldName", getText("Report_Saved_Successfully_alert"));
				emptyResponse.setCode("success");
				emptyResponse.setMessage(getText("Report_Saved_Successfully_alert"));
			} else {
				addFieldError("errorFieldName", getText("Error_in_saving_alert"));
				emptyResponse.setCode("error");
				emptyResponse.setMessage(getText("Error_in_saving_alert"));
			}
			objTopHscodeDao.closeAll();
		}
		return SUCCESS;
	}
	
	public String getTopHscodeReportData() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		MobTopHscodeDao objDao = new MobTopHscodeDao();
		objTopHSCODEBean = objDao.getReportData(reportId);
		objDao.closeAll();
		objHscodeIEResponse.setCode("success");
		objHscodeIEResponse.setMessage("Selected Report List..!");
		objHscodeIEResponse.setResult(objTopHSCODEBean);
		return SUCCESS;
	}
	
	public String EditTopHscodeReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		objTopHSCODEBean = new Gson().fromJson(request.getParameter("data"), MobTopHSCODEBean.class);
		MobTopHscodeDao objDao = new MobTopHscodeDao();
		objTopHSCODEBean = objDao.editTopHscodeReport(objTopHSCODEBean);
		if (objTopHSCODEBean.getResult().toString().equals("updated")) {
			objTopHSCODEBean = objDao.insertIntoRunNow(objTopHSCODEBean.getReportId());
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
		this.request = request;
	}
	

}
