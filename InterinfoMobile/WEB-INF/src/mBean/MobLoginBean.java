package mBean;

import java.util.ArrayList;

/**
 * @author admin1
 * 
 */
public class MobLoginBean {
	private String email;
	private String password;
	private String result;
	private int isActive;
	private String name;
	private int userID;
	private String userType;
	private String isMainUser;
	private String commerce;
	private String language;
	private int customerId;
	private String consultTillDate;
	private String consultTillDateAlert;
	private String deviceId;
	private String message;
	private MobReportAccessBean objReportAccessBean;
	private ArrayList<MobMenuBean> objMenuList;

	public String getConsultTillDate() {
		return consultTillDate;
	}

	public void setConsultTillDate(String consultTillDate) {
		this.consultTillDate = consultTillDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsMainUser() {
		return isMainUser;
	}

	public void setIsMainUser(String isMainUser) {
		this.isMainUser = isMainUser;
	}

	public String getCommerce() {
		return commerce;
	}

	public void setCommerce(String commerce) {
		this.commerce = commerce;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getConsultTillDateAlert() {
		return consultTillDateAlert;
	}

	public void setConsultTillDateAlert(String consultTillDateAlert) {
		this.consultTillDateAlert = consultTillDateAlert;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MobReportAccessBean getObjReportAccessBean() {
		return objReportAccessBean;
	}

	public void setObjReportAccessBean(MobReportAccessBean objReportAccessBean) {
		this.objReportAccessBean = objReportAccessBean;
	}

	public ArrayList<MobMenuBean> getObjMenuList() {
		return objMenuList;
	}

	public void setObjMenuList(ArrayList<MobMenuBean> objMenuList) {
		this.objMenuList = objMenuList;
	}

}
