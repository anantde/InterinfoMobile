package mAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mBean.MobDragonReportBean;
import mBean.MobEmptyBean;
import mBean.MobInputFieldsBean;
import mDao.MobDragonReportDao;
import mDao.MobStandardReportDAO;
import mJsonDataSend.MobDragonReportResponse;
import mJsonDataSend.MobEditDragonResponse;
import mJsonDataSend.MobEmptyResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;


public class MobDragonReportAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpSession httpSession;
	HttpServletRequest request;
	MobDragonReportBean objDragonReportBean;
	private MobInputFieldsBean objInBean;
	MobDragonReportResponse objDragonReportResponse = new MobDragonReportResponse();
	MobEmptyResponse emptyResponse = new MobEmptyResponse();
	MobEditDragonResponse dragonResponse = new MobEditDragonResponse();
	public MobEditDragonResponse getDragonResponse() {
		return dragonResponse;
	}
	public void setDragonResponse(MobEditDragonResponse dragonResponse) {
		this.dragonResponse = dragonResponse;
	}
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}
	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}
	public MobDragonReportResponse getObjDragonReportResponse() {
		return objDragonReportResponse;
	}
	public void setObjDragonReportResponse(
			MobDragonReportResponse objDragonReportResponse) {
		this.objDragonReportResponse = objDragonReportResponse;
	}
	public MobInputFieldsBean getObjInBean() {
		return objInBean;
	}
	public void setObjInBean(MobInputFieldsBean objInBean) {
		this.objInBean = objInBean;
	}
	public String loadCreateDragonReportPage() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		String userID = String.valueOf(httpSession.getAttribute("userId"));
		System.out.println("UserID: " + userID);
		MobDragonReportDao objDragonReportDao=new MobDragonReportDao();               
		objInBean = objDragonReportDao.getAllDropdownData(userID);
		objDragonReportDao.closeall();
		objDragonReportResponse.setCode("success");
		objDragonReportResponse.setMessage("HSCODE List for Full Detail Report..!");
		objDragonReportResponse.setResult(objInBean);
		return SUCCESS;
	
	}
	
	public String submitDragonReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		Gson gson = new Gson(); 
		objDragonReportBean = gson.fromJson(request.getParameter("data"), MobDragonReportBean.class);
		System.out.println("userId = "+objDragonReportBean.getUserID());
		MobDragonReportDao objDragonReportDao=new MobDragonReportDao();
		System.out.println(objDragonReportBean.getReportName());
		MobStandardReportDAO objStandardReportDAO = new MobStandardReportDAO();
		MobEmptyBean emptyBean = new MobEmptyBean();
		emptyResponse.setResult(emptyBean);
		boolean flag = objStandardReportDAO.isStandardReportNamePresent(objDragonReportBean.getReportName(),objDragonReportBean.getUserID());
		System.out.println("Flag===="+flag);
		if(flag)
		{
			addFieldError("errorFieldName", getText("Report_Name_Already_Exists_alert"));
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("Report_Name_Already_Exists_alert"));
			objDragonReportDao.closeall();
		}else{
			objDragonReportBean=objDragonReportDao.createDragonReport(objDragonReportBean);
			if (objDragonReportBean.getResult().toString().equals("created"))
			{
			  objDragonReportBean=objDragonReportDao.insertintoRunNow(objDragonReportBean.getReport_id());
			  addFieldError("successFieldName", getText("Report_Saved_Successfully_alert"));
			  emptyResponse.setCode("success");
			  emptyResponse.setMessage(getText("Report_Saved_Successfully_alert"));
			}
			else{
			  addFieldError("errorFieldName", getText("Error_in_saving_alert"));	
			  emptyResponse.setCode("error");
			  emptyResponse.setMessage(getText("Error_in_saving_alert"));
			}
			objDragonReportDao.closeall();
		}
		return SUCCESS;
	
	}
	
	public String getDragonReportData(){
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		System.out.println("Report id="+reportId);
		MobDragonReportDao objDragonReportDao=new MobDragonReportDao();
		objDragonReportBean=objDragonReportDao.getReportData(reportId);
		//System.out.println("****"+objDragonReportBean.getReportName());
		//reportList=objDragonReportDao.getAllReportList();
		dragonResponse.setCode("success");
		dragonResponse.setMessage("Selected Report List..!");
		dragonResponse.setResult(objDragonReportBean);
		objDragonReportDao.closeall();
		return SUCCESS;
	}
	
	public String editDragonReport(){
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true); 
		objDragonReportBean = new Gson().fromJson(request.getParameter("data"), MobDragonReportBean.class);
		MobDragonReportDao objDragonReportDao=new MobDragonReportDao();
		objDragonReportBean=objDragonReportDao.editDragonReport(objDragonReportBean);
		if (objDragonReportBean.getResult().toString().equals("updated"))
		{
		  objDragonReportBean=objDragonReportDao.insertintoRunNow(objDragonReportBean.getReport_id());
		  addFieldError("successFieldName", getText("Report_updated_Successfully_alert"));
		  emptyResponse.setCode("success");
		  emptyResponse.setMessage(getText("Report_updated_Successfully_alert"));
		}
		else{
		  addFieldError("errorFieldName", getText("Error_in_saving_alert"));	
		  emptyResponse.setCode("error");
		  emptyResponse.setMessage(getText("Error_in_saving_alert"));
		}
		objDragonReportDao.closeall();
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	

}
