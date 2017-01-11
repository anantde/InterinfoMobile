package mBean;

import java.util.ArrayList;

public class MobInputFieldsBean {
	private String userType;
	private ArrayList<MobKeyValuePairBean> importExportList;
	private ArrayList<MobKeyValuePairBean> taxNoList;
	private ArrayList<MobKeyValuePairBean> hscodeList;
	private ArrayList<MobKeyValuePairBean> valueByDollarList;
	private ArrayList<MobKeyValuePairBean> valueByQuantityList;
	private ArrayList<MobKeyValuePairBean> supplierList;
	private ArrayList<MobKeyValuePairBean> borderList;
	private ArrayList<MobKeyValuePairBean> motionKeyList;
	private ArrayList<MobKeyValuePairBean> regimeList;
	private ArrayList<MobKeyValuePairBean> transportationList;
	private ArrayList<MobKeyValuePairBean> ccOriginDestList;
	private ArrayList<MobKeyValuePairBean> ccBuyerSellerList;
	private ArrayList<MobKeyValuePairBean> boundedWarehousesList;
	private ArrayList<MobKeyValuePairBean> identifierList;
	private ArrayList<MobKeyValuePairBean> regulationsList;
	private ArrayList<MobKeyValuePairBean> containerList;
	private ArrayList<MobKeyValuePairBean> billingList;
	private ArrayList<MobKeyValuePairBean> merchandiseDestList;
	private ArrayList<MobKeyValuePairBean> barcodeList;
	private ArrayList<MobKeyValuePairBean> fiscalAreaList;
	private ArrayList<MobKeyValuePairBean> dayList;
	private ArrayList<MobKeyValuePairBean> monthList;
	private ArrayList<MobKeyValuePairBean> subReportList;
	private ArrayList<MobKeyValuePairBean> reportList;
	private ArrayList<MobKeyValuePairBean> rfcList;
	private ArrayList<MobKeyValuePairBean> stateList;

	private String reportID;
	private String reportName;
	private String createdDate;
	private String modifiedDate;
	private String taxNumber;
	private String hscode;
	private String assignedDay;
	private String assignedMonth;
	private String importExport;
	private String assignedBorder;
	private String assignedSuppliers;
	private String fromDate;
	private String toDate;
	private String valByQtyFrom;
	private String valByQtyTo;
	private String valByDollerFrom;
	private String valByDollerTo;
	private String assignedRegimes;
	private String assignedTransportation;
	private String assignedOriDestCC;
	private String assignedBuyerCellerCC;
	private String assignedRegulation;
	private String assignedMerchDest;
	private String outputAs;
	private String reportTitle;

	public ArrayList<MobKeyValuePairBean> getRfcList() {
		return rfcList;
	}

	public void setRfcList(ArrayList<MobKeyValuePairBean> rfcList) {
		this.rfcList = rfcList;
	}

	private ArrayList<MobSubReportBean> subreportForEdit;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public ArrayList<MobKeyValuePairBean> getImportExportList() {
		return importExportList;
	}

	public void setImportExportList(ArrayList<MobKeyValuePairBean> importExportList) {
		this.importExportList = importExportList;
	}

	public ArrayList<MobKeyValuePairBean> getTaxNoList() {
		return taxNoList;
	}

	public void setTaxNoList(ArrayList<MobKeyValuePairBean> taxNoList) {
		this.taxNoList = taxNoList;
	}

	public ArrayList<MobKeyValuePairBean> getHscodeList() {
		return hscodeList;
	}

	public void setHscodeList(ArrayList<MobKeyValuePairBean> hscodeList) {
		this.hscodeList = hscodeList;
	}

	public ArrayList<MobKeyValuePairBean> getValueByDollarList() {
		return valueByDollarList;
	}

	public void setValueByDollarList(ArrayList<MobKeyValuePairBean> valueByDollarList) {
		this.valueByDollarList = valueByDollarList;
	}

	public ArrayList<MobKeyValuePairBean> getValueByQuantityList() {
		return valueByQuantityList;
	}

	public void setValueByQuantityList(ArrayList<MobKeyValuePairBean> valueByQuantityList) {
		this.valueByQuantityList = valueByQuantityList;
	}

	public ArrayList<MobKeyValuePairBean> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(ArrayList<MobKeyValuePairBean> supplierList) {
		this.supplierList = supplierList;
	}

	public ArrayList<MobKeyValuePairBean> getBorderList() {
		return borderList;
	}

	public void setBorderList(ArrayList<MobKeyValuePairBean> borderList) {
		this.borderList = borderList;
	}

	public ArrayList<MobKeyValuePairBean> getMotionKeyList() {
		return motionKeyList;
	}

	public void setMotionKeyList(ArrayList<MobKeyValuePairBean> motionKeyList) {
		this.motionKeyList = motionKeyList;
	}

	public ArrayList<MobKeyValuePairBean> getRegimeList() {
		return regimeList;
	}

	public void setRegimeList(ArrayList<MobKeyValuePairBean> regimeList) {
		this.regimeList = regimeList;
	}

	public ArrayList<MobKeyValuePairBean> getTransportationList() {
		return transportationList;
	}

	public void setTransportationList(ArrayList<MobKeyValuePairBean> transportationList) {
		this.transportationList = transportationList;
	}

	public ArrayList<MobKeyValuePairBean> getCcOriginDestList() {
		return ccOriginDestList;
	}

	public void setCcOriginDestList(ArrayList<MobKeyValuePairBean> ccOriginDestList) {
		this.ccOriginDestList = ccOriginDestList;
	}

	public ArrayList<MobKeyValuePairBean> getCcBuyerSellerList() {
		return ccBuyerSellerList;
	}

	public void setCcBuyerSellerList(ArrayList<MobKeyValuePairBean> ccBuyerSellerList) {
		this.ccBuyerSellerList = ccBuyerSellerList;
	}

	public ArrayList<MobKeyValuePairBean> getBoundedWarehousesList() {
		return boundedWarehousesList;
	}

	public void setBoundedWarehousesList(ArrayList<MobKeyValuePairBean> boundedWarehousesList) {
		this.boundedWarehousesList = boundedWarehousesList;
	}

	public ArrayList<MobKeyValuePairBean> getIdentifierList() {
		return identifierList;
	}

	public void setIdentifierList(ArrayList<MobKeyValuePairBean> identifierList) {
		this.identifierList = identifierList;
	}

	public ArrayList<MobKeyValuePairBean> getRegulationsList() {
		return regulationsList;
	}

	public void setRegulationsList(ArrayList<MobKeyValuePairBean> regulationsList) {
		this.regulationsList = regulationsList;
	}

	public ArrayList<MobKeyValuePairBean> getContainerList() {
		return containerList;
	}

	public void setContainerList(ArrayList<MobKeyValuePairBean> containerList) {
		this.containerList = containerList;
	}

	public ArrayList<MobKeyValuePairBean> getBillingList() {
		return billingList;
	}

	public void setBillingList(ArrayList<MobKeyValuePairBean> billingList) {
		this.billingList = billingList;
	}

	public ArrayList<MobKeyValuePairBean> getMerchandiseDestList() {
		return merchandiseDestList;
	}

	public void setMerchandiseDestList(ArrayList<MobKeyValuePairBean> merchandiseDestList) {
		this.merchandiseDestList = merchandiseDestList;
	}

	public ArrayList<MobKeyValuePairBean> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(ArrayList<MobKeyValuePairBean> barcodeList) {
		this.barcodeList = barcodeList;
	}

	public ArrayList<MobKeyValuePairBean> getFiscalAreaList() {
		return fiscalAreaList;
	}

	public void setFiscalAreaList(ArrayList<MobKeyValuePairBean> fiscalAreaList) {
		this.fiscalAreaList = fiscalAreaList;
	}

	public ArrayList<MobKeyValuePairBean> getDayList() {
		return dayList;
	}

	public void setDayList(ArrayList<MobKeyValuePairBean> dayList) {
		this.dayList = dayList;
	}

	public ArrayList<MobKeyValuePairBean> getMonthList() {
		return monthList;
	}

	public void setMonthList(ArrayList<MobKeyValuePairBean> monthList) {
		this.monthList = monthList;
	}

	public ArrayList<MobKeyValuePairBean> getSubReportList() {
		return subReportList;
	}

	public void setSubReportList(ArrayList<MobKeyValuePairBean> subReportList) {
		this.subReportList = subReportList;
	}

	public ArrayList<MobKeyValuePairBean> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<MobKeyValuePairBean> reportList) {
		this.reportList = reportList;
	}

	public String getAssignedDay() {
		return assignedDay;
	}

	public void setAssignedDay(String assignedDay) {
		this.assignedDay = assignedDay;
	}

	public String getAssignedMonth() {
		return assignedMonth;
	}

	public void setAssignedMonth(String assignedMonth) {
		this.assignedMonth = assignedMonth;
	}

	public String getImportExport() {
		return importExport;
	}

	public void setImportExport(String importExport) {
		this.importExport = importExport;
	}

	public String getAssignedBorder() {
		return assignedBorder;
	}

	public void setAssignedBorder(String assignedBorder) {
		this.assignedBorder = assignedBorder;
	}

	public String getAssignedSuppliers() {
		return assignedSuppliers;
	}

	public void setAssignedSuppliers(String assignedSuppliers) {
		this.assignedSuppliers = assignedSuppliers;
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

	public String getValByQtyFrom() {
		return valByQtyFrom;
	}

	public void setValByQtyFrom(String valByQtyFrom) {
		this.valByQtyFrom = valByQtyFrom;
	}

	public String getValByQtyTo() {
		return valByQtyTo;
	}

	public void setValByQtyTo(String valByQtyTo) {
		this.valByQtyTo = valByQtyTo;
	}

	public String getValByDollerFrom() {
		return valByDollerFrom;
	}

	public void setValByDollerFrom(String valByDollerFrom) {
		this.valByDollerFrom = valByDollerFrom;
	}

	public String getValByDollerTo() {
		return valByDollerTo;
	}

	public void setValByDollerTo(String valByDollerTo) {
		this.valByDollerTo = valByDollerTo;
	}

	public String getReportID() {
		return reportID;
	}

	public void setReportID(String reportID) {
		this.reportID = reportID;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getHscode() {
		return hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	public String getAssignedRegimes() {
		return assignedRegimes;
	}

	public void setAssignedRegimes(String assignedRegimes) {
		this.assignedRegimes = assignedRegimes;
	}

	public String getAssignedTransportation() {
		return assignedTransportation;
	}

	public void setAssignedTransportation(String assignedTransportation) {
		this.assignedTransportation = assignedTransportation;
	}

	public String getAssignedOriDestCC() {
		return assignedOriDestCC;
	}

	public void setAssignedOriDestCC(String assignedOriDestCC) {
		this.assignedOriDestCC = assignedOriDestCC;
	}

	public String getAssignedBuyerCellerCC() {
		return assignedBuyerCellerCC;
	}

	public void setAssignedBuyerCellerCC(String assignedBuyerCellerCC) {
		this.assignedBuyerCellerCC = assignedBuyerCellerCC;
	}

	public String getAssignedRegulation() {
		return assignedRegulation;
	}

	public void setAssignedRegulation(String assignedRegulation) {
		this.assignedRegulation = assignedRegulation;
	}

	public String getAssignedMerchDest() {
		return assignedMerchDest;
	}

	public void setAssignedMerchDest(String assignedMerchDest) {
		this.assignedMerchDest = assignedMerchDest;
	}

	public String getOutputAs() {
		return outputAs;
	}

	public void setOutputAs(String outputAs) {
		this.outputAs = outputAs;
	}

	public ArrayList<MobSubReportBean> getSubreportForEdit() {
		return subreportForEdit;
	}

	public void setSubreportForEdit(ArrayList<MobSubReportBean> subreportForEdit) {
		this.subreportForEdit = subreportForEdit;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public ArrayList<MobKeyValuePairBean> getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList<MobKeyValuePairBean> stateList) {
		this.stateList = stateList;
	}
}
