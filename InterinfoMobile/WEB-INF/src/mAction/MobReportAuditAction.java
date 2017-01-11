package mAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mBean.MobReportListBean;
import mBean.MobReportAccessBean;
import mBean.MobReportDetailBean;
import mDao.MobReportDAO;
import mDao.MobUserDAO;
import mJsonDataSend.MobReportDetailResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MobReportAuditAction extends ActionSupport implements
		ServletRequestAware {
	HttpSession httpSession;
	HttpServletRequest request;
	MobReportDetailResponse objReportDetailResponse = new MobReportDetailResponse();
	
	private int startLimit;

	public MobReportDetailResponse getObjReportDetailResponse() {
		return objReportDetailResponse;
	}

	public void setObjReportDetailResponse(
			MobReportDetailResponse objReportDetailResponse) {
		this.objReportDetailResponse = objReportDetailResponse;
	}

	public int getStartLimit() {
		return startLimit;
	}

	public void setStartLimit(int startLimit) {
		this.startLimit = startLimit;
	}

	public String getAssignedReportListForUser() {

		objReportDetailResponse.setCode("success");
		objReportDetailResponse.setMessage(getText("report_loaded_success"));
		try {
			request = ServletActionContext.getRequest();
			httpSession = request.getSession(true);
			String userID = String.valueOf(httpSession.getAttribute("userId"));
			String userType = String.valueOf(httpSession
					.getAttribute("userType"));
			String commerece = String.valueOf(httpSession
					.getAttribute("commerece"));
			MobUserDAO objUserDAO = new MobUserDAO();
			String custID = objUserDAO.getCustID(userID);
			System.out.println("UserID: " + userID + "\tCustID: " + custID);
			MobReportDAO objDao = new MobReportDAO();
			MobReportAccessBean objAccessBean = new MobReportAccessBean();
			objAccessBean = (MobReportAccessBean) httpSession
					.getAttribute("objAccessBean");

			ArrayList<MobReportDetailBean> objReportDetailBeans = new ArrayList<MobReportDetailBean>();
			MobReportListBean listBean = new MobReportListBean();
			
			objReportDetailBeans = objDao.getassignedStandardReportList(userID,
					custID, userType, objAccessBean, commerece, startLimit);
			System.out.println("Total Standard Reports For User HOme Page: "
					+ objReportDetailBeans.size());
			listBean.setReportList(objReportDetailBeans);
			objReportDetailResponse.setResult(listBean);
		} catch (Exception e) {
			objReportDetailResponse.setCode("error");
			objReportDetailResponse.setMessage(getText("report_loaded_error"));
			e.printStackTrace();
		}

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
