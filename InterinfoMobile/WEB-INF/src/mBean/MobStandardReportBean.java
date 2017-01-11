package mBean;

import java.io.File;
import java.util.ArrayList;



public class MobStandardReportBean {
	private String reportId;
	private String reportName;
	private String customerId;
	private String userId;
	private String userType;
	private String fromDate;
	private String toDate;
	private String hsCode;
	private String taxNumber;
	private String downloads;
	private String result;
	private String callFrom;
	private String reportType;
	private ArrayList<String> hs;

	private String importExport2;
	private String supplier2;
	private String fromDate2;
	private String toDate2;
	private String valueByQuantityFrom2;
	private String valueByQuantityTo2;
	private String valueByDollarFrom2;
	private String valueByDollarTo2;
	private String border2;
	private String regimes2;
	private String transportation2;
	private String ccOriginDest2;
	private String ccBuyerSeller2;
	private String regulations2;
	private String merchandDest2;

	private String importExport;
	private String supplier;
	private String valueByQuantityFrom;
	private String valueByQuantityTo;
	private String valueByDollarFrom;
	private String valueByDollarTo;
	private String border;
	private String motionKey;
	private String regimes;
	private String transportation;
	private String ccOriginDest;
	private String ccBuyerSeller;
	private String warehouses;
	private String identifiers;
	private String regulations;
	private String merchandDest;
	private String billing;
	private String container;
	private String barcode;
	private String fiscalArea;
	private String hscodeLength;
	private String hscodeString;
	private String taxNoString;
	private String isHscodeFile;
	private String isTaxNoFile;
	private File hscodeFile;
	private File taxNoFile;
	private String supplierId;
	private String hscodeDescription;

	private String userName;
	private String outputAs;
	private String refreshDate;
	private String natInternat;
	private String refreshDay;
	private String refreshMonth;
	private String subreport;

	private String reportBy;
	private ArrayList<MobKeyValuePairBean> subReportList;
	private ArrayList<MobKeyValuePairBean> supplierList;
	private String reportIDEdit;

	private String mexicanInformant;
	private String mexSupplier;
	private String foreignSupplier;
	private String date;
	private String country;
	private String supplierType;
	private String supplierActivity;
	private String state;
	private String reportTitle;
	private String valueRangeFrom;
	private String valueRangeTo;
	private String isRFCFile;
	private File rfcFile;
	private String rfc;
	private String rfcString;
	private String refreshYear;
	private int status;

	private String timeBy;
	private String year;
	private String valueBy;
	private String categoryBy;

	private String rowCategory;
	private String columnCategory;
	private String taxNumberIds;

	private String includePossibleRfc;
	private String customerName;

	public String getTaxNumberIds() {
		return taxNumberIds;
	}

	public void setTaxNumberIds(String taxNumberIds) {
		this.taxNumberIds = taxNumberIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<String> getHs() {
		return hs;
	}

	public void setHs(ArrayList<String> hs) {
		this.hs = hs;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getDownloads() {
		return downloads;
	}

	public void setDownloads(String downloads) {
		this.downloads = downloads;
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

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getImportExport() {
		return importExport;
	}

	public void setImportExport(String importExport) {
		this.importExport = importExport;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getValueByQuantityFrom() {
		return valueByQuantityFrom;
	}

	public void setValueByQuantityFrom(String valueByQuantityFrom) {
		this.valueByQuantityFrom = valueByQuantityFrom;
	}

	public String getValueByQuantityTo() {
		return valueByQuantityTo;
	}

	public void setValueByQuantityTo(String valueByQuantityTo) {
		this.valueByQuantityTo = valueByQuantityTo;
	}

	public String getValueByDollarFrom() {
		return valueByDollarFrom;
	}

	public void setValueByDollarFrom(String valueByDollarFrom) {
		this.valueByDollarFrom = valueByDollarFrom;
	}

	public String getValueByDollarTo() {
		return valueByDollarTo;
	}

	public void setValueByDollarTo(String valueByDollarTo) {
		this.valueByDollarTo = valueByDollarTo;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getMotionKey() {
		return motionKey;
	}

	public void setMotionKey(String motionKey) {
		this.motionKey = motionKey;
	}

	public String getRegimes() {
		return regimes;
	}

	public void setRegimes(String regimes) {
		this.regimes = regimes;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public String getCcOriginDest() {
		return ccOriginDest;
	}

	public void setCcOriginDest(String ccOriginDest) {
		this.ccOriginDest = ccOriginDest;
	}

	public String getCcBuyerSeller() {
		return ccBuyerSeller;
	}

	public void setCcBuyerSeller(String ccBuyerSeller) {
		this.ccBuyerSeller = ccBuyerSeller;
	}

	public String getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(String warehouses) {
		this.warehouses = warehouses;
	}

	public String getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(String identifiers) {
		this.identifiers = identifiers;
	}

	public String getRegulations() {
		return regulations;
	}

	public void setRegulations(String regulations) {
		this.regulations = regulations;
	}

	public String getMerchandDest() {
		return merchandDest;
	}

	public void setMerchandDest(String merchandDest) {
		this.merchandDest = merchandDest;
	}

	public String getBilling() {
		return billing;
	}

	public void setBilling(String billing) {
		this.billing = billing;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFiscalArea() {
		return fiscalArea;
	}

	public void setFiscalArea(String fiscalArea) {
		this.fiscalArea = fiscalArea;
	}

	public String getHscodeLength() {
		return hscodeLength;
	}

	public void setHscodeLength(String hscodeLength) {
		this.hscodeLength = hscodeLength;
	}

	public String getHscodeString() {
		return hscodeString;
	}

	public void setHscodeString(String hscodeString) {
		this.hscodeString = hscodeString;
	}

	public String getTaxNoString() {
		return taxNoString;
	}

	public void setTaxNoString(String taxNoString) {
		this.taxNoString = taxNoString;
	}

	public String getIsHscodeFile() {
		return isHscodeFile;
	}

	public void setIsHscodeFile(String isHscodeFile) {
		this.isHscodeFile = isHscodeFile;
	}

	public String getIsTaxNoFile() {
		return isTaxNoFile;
	}

	public void setIsTaxNoFile(String isTaxNoFile) {
		this.isTaxNoFile = isTaxNoFile;
	}

	public File getHscodeFile() {
		return hscodeFile;
	}

	public void setHscodeFile(File hscodeFile) {
		this.hscodeFile = hscodeFile;
	}

	public File getTaxNoFile() {
		return taxNoFile;
	}

	public void setTaxNoFile(File taxNoFile) {
		this.taxNoFile = taxNoFile;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getHscodeDescription() {
		return hscodeDescription;
	}

	public void setHscodeDescription(String hscodeDescription) {
		this.hscodeDescription = hscodeDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOutputAs() {
		return outputAs;
	}

	public void setOutputAs(String outputAs) {
		this.outputAs = outputAs;
	}

	public String getRefreshDate() {
		return refreshDate;
	}

	public void setRefreshDate(String refreshDate) {
		this.refreshDate = refreshDate;
	}

	public String getNatInternat() {
		return natInternat;
	}

	public void setNatInternat(String natInternat) {
		this.natInternat = natInternat;
	}

	public String getRefreshDay() {
		return refreshDay;
	}

	public void setRefreshDay(String refreshDay) {
		this.refreshDay = refreshDay;
	}

	public String getRefreshMonth() {
		return refreshMonth;
	}

	public void setRefreshMonth(String refreshMonth) {
		this.refreshMonth = refreshMonth;
	}

	public String getSubreport() {
		return subreport;
	}

	public void setSubreport(String subreport) {
		this.subreport = subreport;
	}

	public String getImportExport2() {
		return importExport2;
	}

	public void setImportExport2(String importExport2) {
		this.importExport2 = importExport2;
	}

	public String getSupplier2() {
		return supplier2;
	}

	public void setSupplier2(String supplier2) {
		this.supplier2 = supplier2;
	}

	public String getFromDate2() {
		return fromDate2;
	}

	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}

	public String getToDate2() {
		return toDate2;
	}

	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}

	public String getValueByQuantityFrom2() {
		return valueByQuantityFrom2;
	}

	public void setValueByQuantityFrom2(String valueByQuantityFrom2) {
		this.valueByQuantityFrom2 = valueByQuantityFrom2;
	}

	public String getValueByQuantityTo2() {
		return valueByQuantityTo2;
	}

	public void setValueByQuantityTo2(String valueByQuantityTo2) {
		this.valueByQuantityTo2 = valueByQuantityTo2;
	}

	public String getValueByDollarFrom2() {
		return valueByDollarFrom2;
	}

	public void setValueByDollarFrom2(String valueByDollarFrom2) {
		this.valueByDollarFrom2 = valueByDollarFrom2;
	}

	public String getValueByDollarTo2() {
		return valueByDollarTo2;
	}

	public void setValueByDollarTo2(String valueByDollarTo2) {
		this.valueByDollarTo2 = valueByDollarTo2;
	}

	public String getBorder2() {
		return border2;
	}

	public void setBorder2(String border2) {
		this.border2 = border2;
	}

	public String getRegimes2() {
		return regimes2;
	}

	public void setRegimes2(String regimes2) {
		this.regimes2 = regimes2;
	}

	public String getTransportation2() {
		return transportation2;
	}

	public void setTransportation2(String transportation2) {
		this.transportation2 = transportation2;
	}

	public String getCcOriginDest2() {
		return ccOriginDest2;
	}

	public void setCcOriginDest2(String ccOriginDest2) {
		this.ccOriginDest2 = ccOriginDest2;
	}

	public String getCcBuyerSeller2() {
		return ccBuyerSeller2;
	}

	public void setCcBuyerSeller2(String ccBuyerSeller2) {
		this.ccBuyerSeller2 = ccBuyerSeller2;
	}

	public String getRegulations2() {
		return regulations2;
	}

	public void setRegulations2(String regulations2) {
		this.regulations2 = regulations2;
	}

	public String getMerchandDest2() {
		return merchandDest2;
	}

	public void setMerchandDest2(String merchandDest2) {
		this.merchandDest2 = merchandDest2;
	}

	public String getReportBy() {
		return reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}

	

	public String getReportIDEdit() {
		return reportIDEdit;
	}

	public void setReportIDEdit(String reportIDEdit) {
		this.reportIDEdit = reportIDEdit;
	}

	public String getMexicanInformant() {
		return mexicanInformant;
	}

	public void setMexicanInformant(String mexicanInformant) {
		this.mexicanInformant = mexicanInformant;
	}

	public String getMexSupplier() {
		return mexSupplier;
	}

	public void setMexSupplier(String mexSupplier) {
		this.mexSupplier = mexSupplier;
	}

	public String getForeignSupplier() {
		return foreignSupplier;
	}

	public void setForeignSupplier(String foreignSupplier) {
		this.foreignSupplier = foreignSupplier;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierActivity() {
		return supplierActivity;
	}

	public void setSupplierActivity(String supplierActivity) {
		this.supplierActivity = supplierActivity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getValueRangeFrom() {
		return valueRangeFrom;
	}

	public void setValueRangeFrom(String valueRangeFrom) {
		this.valueRangeFrom = valueRangeFrom;
	}

	public String getValueRangeTo() {
		return valueRangeTo;
	}

	public void setValueRangeTo(String valueRangeTo) {
		this.valueRangeTo = valueRangeTo;
	}

	public String getIsRFCFile() {
		return isRFCFile;
	}

	public void setIsRFCFile(String isRFCFile) {
		this.isRFCFile = isRFCFile;
	}

	public File getRfcFile() {
		return rfcFile;
	}

	public void setRfcFile(File rfcFile) {
		this.rfcFile = rfcFile;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRfcString() {
		return rfcString;
	}

	public void setRfcString(String rfcString) {
		this.rfcString = rfcString;
	}

	public String getRefreshYear() {
		return refreshYear;
	}

	public void setRefreshYear(String refreshYear) {
		this.refreshYear = refreshYear;
	}

	public String getTimeBy() {
		return timeBy;
	}

	public void setTimeBy(String timeBy) {
		this.timeBy = timeBy;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getValueBy() {
		return valueBy;
	}

	public void setValueBy(String valueBy) {
		this.valueBy = valueBy;
	}

	public String getCategoryBy() {
		return categoryBy;
	}

	public void setCategoryBy(String categoryBy) {
		this.categoryBy = categoryBy;
	}

	public String getRowCategory() {
		return rowCategory;
	}

	public void setRowCategory(String rowCategory) {
		this.rowCategory = rowCategory;
	}

	public String getColumnCategory() {
		return columnCategory;
	}

	public void setColumnCategory(String columnCategory) {
		this.columnCategory = columnCategory;
	}

	public String getIncludePossibleRfc() {
		return includePossibleRfc;
	}

	public void setIncludePossibleRfc(String includePossibleRfc) {
		this.includePossibleRfc = includePossibleRfc;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ArrayList<MobKeyValuePairBean> getSubReportList() {
		return subReportList;
	}

	public void setSubReportList(ArrayList<MobKeyValuePairBean> subReportList) {
		this.subReportList = subReportList;
	}

	public ArrayList<MobKeyValuePairBean> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(ArrayList<MobKeyValuePairBean> supplierList) {
		this.supplierList = supplierList;
	}

	
}
