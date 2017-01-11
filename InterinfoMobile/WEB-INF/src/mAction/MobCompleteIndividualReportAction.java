package mAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mBean.MobCompleteIndividualBean;
import mBean.MobEmptyBean;
import mDao.MobCompleteIndividualReportDao;
import mDao.MobStandardReportDAO;
import mJsonDataSend.MobEmptyResponse;
import mJsonDataSend.MobRfcListCiCnResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class MobCompleteIndividualReportAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MobCompleteIndividualBean objComIndividualBean;
	MobRfcListCiCnResponse objListCiCnResponse = new MobRfcListCiCnResponse();
	MobEmptyResponse emptyResponse = new MobEmptyResponse();
	HttpSession httpSession;
	HttpServletRequest request;
	
	
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}
	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}
	public MobRfcListCiCnResponse getObjListCiCnResponse() {
		return objListCiCnResponse;
	}
	public void setObjListCiCnResponse(MobRfcListCiCnResponse objListCiCnResponse) {
		this.objListCiCnResponse = objListCiCnResponse;
	}
	public String loadCreateCompleteIndividualReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
			MobStandardReportDAO objDao = new MobStandardReportDAO();
			String userID = String.valueOf(httpSession.getAttribute("userId"));
			objComIndividualBean = new MobCompleteIndividualBean();
			objComIndividualBean.setUserID(userID);
			objComIndividualBean.setRfcList(objDao.getInformant(userID));
			objListCiCnResponse.setCode("success");
			objListCiCnResponse.setMessage("RFC List for example CI and CN report..!");
			objListCiCnResponse.setResult(objComIndividualBean);
		return SUCCESS;
	}
	
	public String createCompleteIndividualReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		MobEmptyBean emptyBean = new MobEmptyBean();
		objComIndividualBean = new Gson().fromJson(request.getParameter("data"), MobCompleteIndividualBean.class);
		if (objComIndividualBean.getIncludePossibleRfc() != null && objComIndividualBean.getIncludePossibleRfc().equals("on")) {
			objComIndividualBean.setIncludePossibleRfc("yes");
		} else {
			objComIndividualBean.setIncludePossibleRfc("no");
		}
		System.out.println("RFC : "+objComIndividualBean.getRfc());
		MobCompleteIndividualReportDao objDao = new MobCompleteIndividualReportDao();
		MobStandardReportDAO objStandardReportDAO = new MobStandardReportDAO();
		boolean isReportNamePresent = objStandardReportDAO.isStandardReportNamePresent(objComIndividualBean.getReportName(),objComIndividualBean.getUserID());
		System.out.println("isReportNamePresent: " + isReportNamePresent);
		emptyResponse.setResult(emptyBean);
		if (isReportNamePresent || objComIndividualBean.getReportName() == null) {
			addFieldError("errorFieldName", getText("Report_Name_Already_Exists_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Report_Name_Already_Exists_alert"));
		} else {
			objComIndividualBean = objDao.createCompleteIndividualReport(objComIndividualBean);
			if (objComIndividualBean.getResult().toString().equals("created")) {
				objComIndividualBean = objDao.insertintoRunNow(objComIndividualBean.getReport_id());
				addFieldError("successFieldName", getText("Report_Saved_Successfully_alert"));
				emptyResponse.setCode("success");
				emptyResponse.setMessage(getText("Report_Saved_Successfully_alert"));
			} else {
				addFieldError("errorFieldName", getText("Error_in_saving_alert"));
				emptyResponse.setCode("error");
				emptyResponse.setMessage( getText("Error_in_saving_alert"));
			}
			objDao.closeAll();
		}
		return SUCCESS;
	}
	
	public String getMaxRecentYearFromConfigurationTable() {
		MobCompleteIndividualReportDao objDao = new MobCompleteIndividualReportDao();
		objComIndividualBean = objDao.getRecentYearFromConfigurationTable();
		objDao.closeAll();
		objListCiCnResponse.setCode("success");
		objListCiCnResponse.setMessage("Max recent year..!");
		objListCiCnResponse.setResult(objComIndividualBean);
		return SUCCESS;
	}
	
	public String getCompleteIndividualReportData() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		MobCompleteIndividualReportDao objCompleteDao = new MobCompleteIndividualReportDao();
		objComIndividualBean = objCompleteDao.getReportData(reportId);
		objCompleteDao.closeAll();
		objListCiCnResponse.setCode("success");
		objListCiCnResponse.setMessage("Selected Report Details..!");
		objListCiCnResponse.setResult(objComIndividualBean);
		
		return SUCCESS;
	}
	
	public String EditCompleteIndividualReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		MobEmptyBean emptyBean = new MobEmptyBean();
		objComIndividualBean = new Gson().fromJson(request.getParameter("data"), MobCompleteIndividualBean.class);
		if (objComIndividualBean.getIncludePossibleRfc() != null && objComIndividualBean.getIncludePossibleRfc().equals("on")) {
			objComIndividualBean.setIncludePossibleRfc("yes");
		} else {
			objComIndividualBean.setIncludePossibleRfc("no");
		}
		MobCompleteIndividualReportDao objDao = new MobCompleteIndividualReportDao();
		objComIndividualBean = objDao.editCompleteIndividualReport(objComIndividualBean);
		emptyResponse.setResult(emptyBean);
		if (objComIndividualBean.getResult().toString().equals("updated")) {
			objComIndividualBean = objDao.insertintoRunNow(objComIndividualBean.getReport_id());
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
