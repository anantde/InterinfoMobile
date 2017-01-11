package mAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mBean.MobEmptyBean;
import mDao.MobProfileDAO;
import mJsonDataSend.MobEmptyResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class MobProfileAction extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpSession httpSession;
	String responseCode;
	MobEmptyResponse emptyResponse;

	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}

	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}

	public String changePassword() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		MobEmptyBean objEmptyBean = new MobEmptyBean();
		emptyResponse = new MobEmptyResponse();
		String newPassword = request.getParameter("newPassword");
		String userID = String.valueOf(httpSession.getAttribute("userId"));
		System.out.println("New Password: " + newPassword + "\ttuserID: "
				+ userID);
		MobProfileDAO objDao = new MobProfileDAO();
		responseCode = objDao.updatePassword(newPassword, userID);
		emptyResponse.setResult(objEmptyBean);
		if (responseCode.equalsIgnoreCase("success")) {
			emptyResponse.setCode("success");
			emptyResponse.setMessage(getText("change_password_success"));
			httpSession.setAttribute("password", newPassword);
		} else {
			emptyResponse.setCode("error");
			emptyResponse.setMessage(getText("change_password_error"));
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
