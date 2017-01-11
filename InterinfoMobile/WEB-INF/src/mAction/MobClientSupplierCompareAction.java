package mAction;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mBean.MobClientSupplierCompareBean;
import mBean.MobEmptyBean;
import mBean.MobKeyValuePairBean;
import mDao.MobClientSupplierCompareDao;
import mDao.MobStandardReportDAO;
import mJsonDataSend.MobEmptyResponse;
import mJsonDataSend.MobRfcListResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class MobClientSupplierCompareAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	private static final long serialVersionUID = 1L;
	MobClientSupplierCompareBean objClientSupplierCompareBean = null;	
	ArrayList<MobKeyValuePairBean> reportList;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	MobRfcListResponse listResponse = new MobRfcListResponse();
	MobEmptyResponse emptyResponse = new MobEmptyResponse();
	
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}
	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}
	public MobRfcListResponse getListResponse() {
		return listResponse;
	}
	public void setListResponse(MobRfcListResponse listResponse) {
		this.listResponse = listResponse;
	}
	public String loadCreateClientSupplierComparePage() throws IOException {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		System.out.println("***************in load method for top customer suppiler by rfc**************");
			MobStandardReportDAO objDao = new MobStandardReportDAO();
			String userID = String.valueOf(httpSession.getAttribute("userId"));
			objClientSupplierCompareBean = new MobClientSupplierCompareBean();
			objClientSupplierCompareBean.setRfcList(objDao.getInformant(userID));
			listResponse.setCode("success");
			listResponse.setMessage("RFC list..!");
			listResponse.setResult(objClientSupplierCompareBean);
			return SUCCESS;
	}

	public String submitClientSupplierCompareReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		MobEmptyBean emptyBean = new MobEmptyBean();
		objClientSupplierCompareBean = new Gson().fromJson(request.getParameter("data"),MobClientSupplierCompareBean.class);
		System.out.println("-----------------in Submit top customer supplier report by rfc------------");
		if (objClientSupplierCompareBean.getIncludePossibleRfc() != null && objClientSupplierCompareBean.getIncludePossibleRfc().equals("on")) {
			objClientSupplierCompareBean.setIncludePossibleRfc("yes");
		} else {
			objClientSupplierCompareBean.setIncludePossibleRfc("no");
		}
		
		objClientSupplierCompareBean.setTillDate(objClientSupplierCompareBean.getTillDate()+"-01");
		objClientSupplierCompareBean.setInfoBy(objClientSupplierCompareBean.getInfoBy().toLowerCase());
		MobClientSupplierCompareDao objClientSupplierCompareDao = new MobClientSupplierCompareDao();
		MobStandardReportDAO objStandardReportDAO = new MobStandardReportDAO();
		boolean flag = objStandardReportDAO.isStandardReportNamePresent(objClientSupplierCompareBean.getReportName(),objClientSupplierCompareBean.getUserID());
		System.out.println("Flag====" + flag);
		emptyResponse.setResult(emptyBean);
		if (flag || objClientSupplierCompareBean.getReportName() == null) {
			addFieldError("errorFieldName", getText("Report_Name_Already_Exists_alert"));
			objClientSupplierCompareDao.closeall();
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Report_Name_Already_Exists_alert"));
		} else {
			objClientSupplierCompareBean = objClientSupplierCompareDao.createClientSupplierCompareReport(objClientSupplierCompareBean);
			if (objClientSupplierCompareBean.getResult().toString().equals("created")) {
				objClientSupplierCompareBean = objClientSupplierCompareDao.insertintoRunNow(objClientSupplierCompareBean.getReport_id());
				addFieldError("successFieldName", getText("Report_Saved_Successfully_alert"));
				emptyResponse.setCode("success");
				emptyResponse.setMessage(getText("Report_Saved_Successfully_alert"));
			} else {
				emptyResponse.setCode("error");
				emptyResponse.setMessage(getText("Error_in_saving_alert"));
				addFieldError("errorFieldName", getText("Error_in_saving_alert"));
			}
			objClientSupplierCompareDao.closeall();

		}
		return SUCCESS;
	}
	public String getClientSupplierCompareData() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		System.out.println("Report id=" + reportId);
		MobClientSupplierCompareDao objClientSupplierCompareDao = new MobClientSupplierCompareDao();
		objClientSupplierCompareBean = objClientSupplierCompareDao.getReportData(reportId);
		objClientSupplierCompareDao.closeall();
		listResponse.setCode("success");
		listResponse.setMessage("Selected Report Details..!");
		listResponse.setResult(objClientSupplierCompareBean);
		return SUCCESS;
	}
	
	public String editClientSupplierCompareReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		MobEmptyBean emptyBean = new MobEmptyBean();
		objClientSupplierCompareBean = new Gson().fromJson(request.getParameter("data"), MobClientSupplierCompareBean.class);
		if (objClientSupplierCompareBean.getIncludePossibleRfc() != null && objClientSupplierCompareBean.getIncludePossibleRfc().equals("on")) {
			objClientSupplierCompareBean.setIncludePossibleRfc("yes");
		} else {
			objClientSupplierCompareBean.setIncludePossibleRfc("no");
		}
		
		objClientSupplierCompareBean.setTillDate(objClientSupplierCompareBean.getTillDate()+"-01");
		System.out.println("Till Date: "+ objClientSupplierCompareBean.getTillDate());
		
		MobClientSupplierCompareDao objClientSupplierCompareDao = new MobClientSupplierCompareDao();
		objClientSupplierCompareBean = objClientSupplierCompareDao.editClientSupplierCompare(objClientSupplierCompareBean);
		emptyResponse.setResult(emptyBean);
		if (objClientSupplierCompareBean.getResult().toString().equals("updated")) {
			objClientSupplierCompareBean = objClientSupplierCompareDao.insertintoRunNow(objClientSupplierCompareBean.getReport_id());
			addFieldError("successFieldName", getText("Report_updated_Successfully_alert"));
			emptyResponse.setCode("success");
			emptyResponse.setMessage(getText("Report_updated_Successfully_alert"));
		} else {
			addFieldError("errorFieldName", getText("Error_in_saving_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Error_in_saving_alert"));
		}
		//reportList = objClientSupplierCompareDao.getAllReportList(userId);
		objClientSupplierCompareDao.closeall();
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
	this.request = 	request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		// TODO Auto-generated method stub
	}

}
