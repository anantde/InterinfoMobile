package mBean;

import java.io.File;
import java.util.ArrayList;



public class MobUserBean {
	private int userID;
	private String password;
	private int customerID;
	private String name;
	private String position;
	private String email;
	private String phone;
	private int isActive;
	private String fromDate;
	private String toDate;
	private String result;
	private String callFrom;
	private String userType;
	private String commerce;
	private int downloads;
	private int hscodeLength;
	private ArrayList<String> hscodeList;
	private ArrayList<String> hscodeIDList;
	private String hscode;
	private File hscodeFile;
	private String isFile;

	private String taxNo;
	private File taxNoFile;
	private ArrayList<String> taxNoList;
	private ArrayList<String> irsList;
	private String irsNo;
	private File irsFile;
	private String isMainUser;

	private ArrayList<MobKeyValuePairBean> customsList;
	private String customs;
	private String customsFile;
	private File customFile;
	private ArrayList<String> customsStringList;
	private String borderID;

	private String natTaxConsults;
	private String internatHSCODEConsults;
	private String internatTaxIDConsults;
	private String internatBordersConsults;

	private int totalConsults;
	private int assignedCount;
	private int remainingConsults;
	private boolean isCodeUsed;


	private ArrayList<MobKeyValuePairBean> hscodeBeanList;
	private ArrayList<MobKeyValuePairBean> taxNoBeanList;
	private ArrayList<MobKeyValuePairBean> irsBeanList;
	private String consultTillDate ;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCallFrom() {
		return callFrom;
	}
	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCommerce() {
		return commerce;
	}
	public void setCommerce(String commerce) {
		this.commerce = commerce;
	}
	public int getDownloads() {
		return downloads;
	}
	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}
	public int getHscodeLength() {
		return hscodeLength;
	}
	public void setHscodeLength(int hscodeLength) {
		this.hscodeLength = hscodeLength;
	}
	public ArrayList<String> getHscodeList() {
		return hscodeList;
	}
	public void setHscodeList(ArrayList<String> hscodeList) {
		this.hscodeList = hscodeList;
	}
	public ArrayList<String> getHscodeIDList() {
		return hscodeIDList;
	}
	public void setHscodeIDList(ArrayList<String> hscodeIDList) {
		this.hscodeIDList = hscodeIDList;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public File getHscodeFile() {
		return hscodeFile;
	}
	public void setHscodeFile(File hscodeFile) {
		this.hscodeFile = hscodeFile;
	}
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public File getTaxNoFile() {
		return taxNoFile;
	}
	public void setTaxNoFile(File taxNoFile) {
		this.taxNoFile = taxNoFile;
	}
	public ArrayList<String> getTaxNoList() {
		return taxNoList;
	}
	public void setTaxNoList(ArrayList<String> taxNoList) {
		this.taxNoList = taxNoList;
	}
	public ArrayList<String> getIrsList() {
		return irsList;
	}
	public void setIrsList(ArrayList<String> irsList) {
		this.irsList = irsList;
	}
	public String getIrsNo() {
		return irsNo;
	}
	public void setIrsNo(String irsNo) {
		this.irsNo = irsNo;
	}
	public File getIrsFile() {
		return irsFile;
	}
	public void setIrsFile(File irsFile) {
		this.irsFile = irsFile;
	}
	public String getIsMainUser() {
		return isMainUser;
	}
	public void setIsMainUser(String isMainUser) {
		this.isMainUser = isMainUser;
	}
	public ArrayList<MobKeyValuePairBean> getCustomsList() {
		return customsList;
	}
	public void setCustomsList(ArrayList<MobKeyValuePairBean> customsList) {
		this.customsList = customsList;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public String getCustomsFile() {
		return customsFile;
	}
	public void setCustomsFile(String customsFile) {
		this.customsFile = customsFile;
	}
	public File getCustomFile() {
		return customFile;
	}
	public void setCustomFile(File customFile) {
		this.customFile = customFile;
	}
	public ArrayList<String> getCustomsStringList() {
		return customsStringList;
	}
	public void setCustomsStringList(ArrayList<String> customsStringList) {
		this.customsStringList = customsStringList;
	}
	public String getBorderID() {
		return borderID;
	}
	public void setBorderID(String borderID) {
		this.borderID = borderID;
	}
	public String getNatTaxConsults() {
		return natTaxConsults;
	}
	public void setNatTaxConsults(String natTaxConsults) {
		this.natTaxConsults = natTaxConsults;
	}
	public String getInternatHSCODEConsults() {
		return internatHSCODEConsults;
	}
	public void setInternatHSCODEConsults(String internatHSCODEConsults) {
		this.internatHSCODEConsults = internatHSCODEConsults;
	}
	public String getInternatTaxIDConsults() {
		return internatTaxIDConsults;
	}
	public void setInternatTaxIDConsults(String internatTaxIDConsults) {
		this.internatTaxIDConsults = internatTaxIDConsults;
	}
	public String getInternatBordersConsults() {
		return internatBordersConsults;
	}
	public void setInternatBordersConsults(String internatBordersConsults) {
		this.internatBordersConsults = internatBordersConsults;
	}
	public int getTotalConsults() {
		return totalConsults;
	}
	public void setTotalConsults(int totalConsults) {
		this.totalConsults = totalConsults;
	}
	public int getAssignedCount() {
		return assignedCount;
	}
	public void setAssignedCount(int assignedCount) {
		this.assignedCount = assignedCount;
	}
	public int getRemainingConsults() {
		return remainingConsults;
	}
	public void setRemainingConsults(int remainingConsults) {
		this.remainingConsults = remainingConsults;
	}
	public boolean isCodeUsed() {
		return isCodeUsed;
	}
	public void setCodeUsed(boolean isCodeUsed) {
		this.isCodeUsed = isCodeUsed;
	}
	public ArrayList<MobKeyValuePairBean> getHscodeBeanList() {
		return hscodeBeanList;
	}
	public void setHscodeBeanList(ArrayList<MobKeyValuePairBean> hscodeBeanList) {
		this.hscodeBeanList = hscodeBeanList;
	}
	public ArrayList<MobKeyValuePairBean> getTaxNoBeanList() {
		return taxNoBeanList;
	}
	public void setTaxNoBeanList(ArrayList<MobKeyValuePairBean> taxNoBeanList) {
		this.taxNoBeanList = taxNoBeanList;
	}
	public ArrayList<MobKeyValuePairBean> getIrsBeanList() {
		return irsBeanList;
	}
	public void setIrsBeanList(ArrayList<MobKeyValuePairBean> irsBeanList) {
		this.irsBeanList = irsBeanList;
	}
	public String getConsultTillDate() {
		return consultTillDate;
	}
	public void setConsultTillDate(String consultTillDate) {
		this.consultTillDate = consultTillDate;
	}
}
