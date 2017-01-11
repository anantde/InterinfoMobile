package mAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mBean.MobEmptyBean;
import mBean.MobStandardReportBean;
import mBean.MobUserBean;
import mDao.MobReportDAO;
import mJsonDataSend.MobEmptyResponse;
import mResponse.Response;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
public class MobReportAction extends ActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpSession httpSession;
	private String reportID;
	private String reportType;
	private MobEmptyResponse emptyResponse = new MobEmptyResponse();
	private Response deleteResponse = new Response();

	public Response getDeleteResponse() {
		return deleteResponse;
	}

	public void setDeleteResponse(Response deleteResponse) {
		this.deleteResponse = deleteResponse;
	}

	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}

	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}

	public String deleteReportFromUserHome() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		reportType = request.getParameter("reportType");
		reportID = request.getParameter("reportID");
		System.out.println("Report Type: " + reportType + " | Report ID: "
				+ reportID);

		String tableName = null;
		String tableNameRunNow = null;
		if (reportType.equalsIgnoreCase("National Query")) {
			tableName = "save_standard_report_nc";
			tableNameRunNow = "standard_report_nc_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Query (Standard)")
				|| reportType
						.equalsIgnoreCase("International Query (Extended)")
				|| reportType
						.equalsIgnoreCase("International Query Monthly (Standard)")
				|| reportType
						.equalsIgnoreCase("International Query Monthly (Extended)")) {
			tableName = "save_standard_report_inc";
			tableNameRunNow = "standard_report_inc_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Table One Variable")) {
			tableName = "international_custom_report1";
			tableNameRunNow = "international_custom_report1_run_now";
		} else if (reportType
				.equalsIgnoreCase("International Table Two Variable")) {
			tableName = "international_custom_report2";
			tableNameRunNow = "international_custom_report2_run_now";
		} else if (reportType.equalsIgnoreCase("National Table")) {
			tableName = "national_custom_report1";
			tableNameRunNow = "national_custom_report1_run_now";
		} else if (reportType.equalsIgnoreCase("Top HSCODE Group Report")) {
			tableName = "inc_rfc_top_hscode";
			tableNameRunNow = "inc_rfc_top_hscode_run_now";
		} else if (reportType.equalsIgnoreCase("Credit Report")) {
			tableName = "save_credit_report";
			tableNameRunNow = "credit_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Customer Supplier Group Report")) {
			tableName = "nc_rfc_top_client_supplier";
			tableNameRunNow = "nc_rfc_top_client_supplier_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Customer Supplier Report By RFC")) {
			tableName = "nc_rfc_client_supplier_compare";
			tableNameRunNow = "nc_rfc_client_supplier_compare_run_now";
		} else if (reportType.equalsIgnoreCase("Full Detail Report")) {
			tableName = "save_dragon_report";
			tableNameRunNow = "dragon_report_run_now";
		} else if (reportType.equalsIgnoreCase("Quintile Report")) {
			tableName = "save_quintile_report";
			tableNameRunNow = "quintile_report_run_now";
		} else if (reportType.equalsIgnoreCase("Example CI and CN Report")) {
			tableName = "save_complete_individual_report";
			tableNameRunNow = "complete_individual_report_run_now";
		} else if (reportType.equalsIgnoreCase("Competitive Report")) {
			tableName = "save_competitive_report";
			tableNameRunNow = "competitive_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Import/Export Report by RFC")) {
			tableName = "save_top_rfc_report";
			tableNameRunNow = "top_rfc_report_run_now";
		} else if (reportType
				.equalsIgnoreCase("Top Import/Export Report by HSCODE")) {
			tableName = "save_top_hscode_report";
			tableNameRunNow = "top_hscode_report_run_now";
		}
		System.out.println("Table Name: " + tableName);

		MobReportDAO objReportDao = new MobReportDAO();
		MobEmptyBean mobEmptyBean = new MobEmptyBean();
		emptyResponse.setCode(objReportDao.deleteReportFromSystem(tableName,
				reportID));
		if (emptyResponse.getCode().equalsIgnoreCase("success")) {
			emptyResponse.setMessage(getText("report_deleted"));
		} else {
			emptyResponse.setMessage(getText("error_while_uploading_file"));
		}
		emptyResponse.setResult(mobEmptyBean);
		System.out.println("emptyResponse::::::::::" + emptyResponse.getCode());
		objReportDao.deleteReportFromSystem(tableNameRunNow, reportID);
		return SUCCESS;
	}

	public void downloadStandardReport() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		MobEmptyBean objEmptyBean = new MobEmptyBean();
		emptyResponse = new MobEmptyResponse();
		emptyResponse.setCode("error");
		emptyResponse.setMessage("Error While Downloading..!");
		emptyResponse.setResult(objEmptyBean);
		reportType = request.getParameter("reportType");
		reportID = request.getParameter("reportID");
		String userID = String.valueOf(httpSession.getAttribute("userId"));
		MobReportDAO objDao = new MobReportDAO();
		MobUserBean objBean1 = objDao.getUserType(userID);
		System.out.println("User Type: " + objBean1.getUserType() + " userID: "
				+ userID);
		HttpServletResponse response = ServletActionContext.getResponse();
		// ---- Since We have decided not to restrict package user even
		// downloads are over --- compare with -1
		if (objBean1.getUserType().equalsIgnoreCase("package")
				&& objBean1.getDownloads() == -1) {
			emptyResponse.setMessage("Maximum download limit done. Please contact administration..!");
		} else {
			System.out.println("Download Report Having ReportID: " + reportID
					+ " And Report Type: " + reportType + " User ID: "
					+ objBean1.getUserID());
			MobStandardReportBean objBean = objDao.getStandardFileName(reportID,
					reportType, userID, objBean1.getUserID());
			String fileExtension = "xlsx";
			// ------------- If Report Not Present ------------
			System.out.println("IsReportPresent ---> enteredID: " + reportID
					+ "\tActualReportID: " + objBean.getReportId());
			if (reportID == null
					&& !objBean.getReportId().equalsIgnoreCase(reportID)) {
				System.out.println("Report Not Present...!");
				emptyResponse.setMessage("Report Not Present..!");
			} else {
				String getfileName = objBean.getReportName().replace(" ", "");
				getfileName = getfileName.replace("/", "");
				// ----- File is present with name having structure like-
				// reportName_uuserid --------
				if (reportType
						.equalsIgnoreCase("International Query Monthly (Standard)")
						|| reportType
								.equalsIgnoreCase("International Query Monthly (Extended)")) {
					getfileName = getfileName + "_u" + objBean.getUserId()
							+ "_monthly";
				} else {
					getfileName = getfileName + "_u" + objBean.getUserId();
				}
				System.out.println("Report TYPE : " + reportType);
				try {
					String pathName = objDao.getStandardReportFilePath();
					if (reportType.equalsIgnoreCase("National Table")
							|| reportType
							.equalsIgnoreCase("Top Customer Supplier Report By RFC")
							|| reportType
									.equalsIgnoreCase("Example CI and CN Report")
							|| reportType
									.equalsIgnoreCase("Competitive Report")
							|| reportType
									.equalsIgnoreCase("Top Import/Export Report by RFC")
							|| reportType.equalsIgnoreCase("Credit Report")
							|| reportType
									.equalsIgnoreCase("Top Import/Export Report by HSCODE")
							|| reportType.equalsIgnoreCase("Quintile Report")
							|| reportType
									.equalsIgnoreCase("Full Detail Report")) {
						fileExtension = "pdf";
					} else {
						fileExtension = "xlsx";
					}
					System.out.println("Search File To Download Having Name: "
							+ getfileName + "." + fileExtension);
					String fileName = pathName + File.separatorChar
							+ getfileName + "." + fileExtension;
					if (reportType
							.equalsIgnoreCase("International Query Monthly (Standard)")
							|| reportType
									.equalsIgnoreCase("International Query Monthly (Extended)")) {
						getfileName = "CI " + objBean.getReportName() + " "
								+ objBean.getCustomerName() + " "
								+ objBean.getDate() + " Monthly";
					} else if (reportType
							.equalsIgnoreCase("International Query (Standard)")
							|| reportType
									.equalsIgnoreCase("International Query (Extended)")) {
						getfileName = "CI " + objBean.getReportName() + " "
								+ objBean.getCustomerName() + " "
								+ objBean.getDate();
					} else if (reportType.equals("National Query")) {
						getfileName = "CN " + objBean.getReportName() + " "
								+ objBean.getCustomerName() + " "
								+ objBean.getDate();
					}
					System.out.println("File Path: " + pathName);
					System.out
							.println("File Name Before Download: " + fileName);
					System.out.println("File Name After Download: "
							+ getfileName);
					FileInputStream filein = null;
					File file = new File(fileName);
					int len = 0;
					filein = new FileInputStream(file);
					if (fileExtension.equals("xls")
							|| fileExtension.equals("xlsx")) {
						response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
						response.setHeader("Content-Disposition",
								"attachment;filename=" + getfileName + ".xlsx");
					} else if (fileExtension.equals("pdf")) {
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition",
								"attachment;filename=" + getfileName + ".pdf");
					}
					OutputStream out = response.getOutputStream();
					while ((len = filein.read()) >= 0) {
						out.write(len);
					}
					response.flushBuffer();
					response.getOutputStream().flush();
					response.getOutputStream().close();
					out.flush();
					out.close();
					filein.close();
					emptyResponse.setCode("success");
					emptyResponse.setMessage("Downloading Stared..!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
