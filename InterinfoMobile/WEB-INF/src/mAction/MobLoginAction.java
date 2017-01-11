package mAction;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mBean.MobLoginBean;
import mBean.MobMenuBean;
import mBean.MobReportAccessBean;
import mDao.MobLoginDAO;
import mDao.MobReportAccessDao;
import mJsonDataSend.MobEmptyResponse;
import mJsonDataSend.MobUserLoginResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.I18nInterceptor;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class MobLoginAction extends ActionSupport implements ServletRequestAware {
	// Project key: AIzaSyCqOeqigLfUfHPEyJJJrSuW5xKwnICr1gY
	HttpServletRequest request;
	HttpSession httpSession;
	private MobLoginBean objBean = new MobLoginBean();
	private MobUserLoginResponse loginResponse;
	private MobEmptyResponse emptyResponse;
	public MobEmptyResponse getEmptyResponse() {
		return emptyResponse;
	}

	public void setEmptyResponse(MobEmptyResponse emptyResponse) {
		this.emptyResponse = emptyResponse;
	}

	public MobUserLoginResponse getLoginResponse() {
		return loginResponse;
	}

	public void setLoginResponse(MobUserLoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}

	public MobLoginBean getObjBean() {
		return objBean;
	}

	public void setObjBean(MobLoginBean objBean) {
		this.objBean = objBean;
	}

	@Override
	public void validate() {
		super.validate();
	}

	@Override
	public String execute() throws Exception {
		System.out.println("On Action Execute");
		return super.execute();
	}

	public String login() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		loginResponse = new MobUserLoginResponse();
		objBean = new Gson().fromJson(request.getParameter("data"),MobLoginBean.class);
		if (objBean != null && objBean.getEmail() != null) {
			MobLoginDAO objDao = new MobLoginDAO();
			objBean = objDao.isEmailPresent(objBean);

			if (objBean.getUserType().equals("admin")) {
				objBean = objDao.validateAdminLogin(objBean, ipAddress);
			} else if (objBean.getUserType().equals("noEmail")) {
				objBean.setResult("invalidUser");
				objBean.setMessage("Invalid User...!");
			} else {
				objBean = objDao.validateNormalUserLogin(objBean);
			}

			request.setAttribute("result", objBean.getResult());
			MobReportAccessBean objAccessBean = new MobReportAccessBean();
			MobReportAccessDao objAccessDao = new MobReportAccessDao();
			objAccessBean = objAccessDao.getUserAccessInfo(
					objBean.getCustomerId(),
					String.valueOf(objBean.getUserID()));
			
			System.out.println("objAccessBean:::::::::::::"+new Gson().toJson(objAccessBean));
			
			objAccessDao.closeall();
			if (objBean.getResult().equals("userSuccess") || objBean.getResult().equals("adminSuccess")) {
				Locale locale = null;
				if (objBean.getLanguage() != null) {
					locale = new Locale(objBean.getLanguage());
				} else {
					locale = new Locale("en");
				}
				ActionContext.getContext().setLocale(locale);			
				httpSession.setAttribute(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE, locale);
				objBean.setResult("success");
				// ------- Store Device ID into Database -------
				objDao.storeDeviceId(objBean);
				objBean.setObjReportAccessBean(objAccessBean);
				ArrayList<MobMenuBean> objMenuList = new ArrayList<MobMenuBean>();
				ArrayList<String> objSubMenu = null;
				
				MobMenuBean objMenuBean = new MobMenuBean();
				objMenuBean.setMenu(getText("home-lbl"));
				objMenuList.add(objMenuBean);
				if (objBean.getUserType().equalsIgnoreCase("unLimited")) {
					/*if (objBean.getUserType().equalsIgnoreCase("unLimited") && 
						objBean.getCommerce().equalsIgnoreCase("national") &&
						((objAccessBean.getUserTopCustSuppIndv()!= null &&  objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")) || 
						(objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on")))) {
						objMenuBean = new MobMenuBean();
						objMenuBean.setMenu(getText("report-lbl"));
						objSubMenu = new ArrayList<String>();
						if( objAccessBean.getUserTopCustSuppIndv()!= null &&  objAccessBean.getUserTopCustSuppIndv() != null && objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_customer_supplier_by_rfc"));
						}
						if( objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual() != null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on")){
							objSubMenu.add(getText("complete_individual_report"));
						}
						
					} else if (objBean.getUserType().equalsIgnoreCase("unLimited") && 
							objBean.getCommerce().equalsIgnoreCase("international") &&
							((objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on"))|| 
							(objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on"))||
							(objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")) ||
							(objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")))) {
						objMenuBean = new MobMenuBean();
						objMenuBean.setMenu(getText("report-lbl"));
						objSubMenu = new ArrayList<String>();
						if( objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on")){
							objSubMenu.add(getText("complete_individual_report"));				
						}
						if( objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_rfc_report"));
						}
						if( objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_hscode_report"));
						}
						if( objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")){
							objSubMenu.add(getText("dragon_report_lbl"));
						}
					} else if (objBean.getUserType().equalsIgnoreCase("unLimited") && 
							objBean.getCommerce().equalsIgnoreCase("both") &&
							((objAccessBean.getUserTopCustSuppIndv()!= null && objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")) ||
							( objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on")) || 
							( objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on"))||
							( objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")) ||
							( objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")))) 
					{
						objMenuBean = new MobMenuBean();
						objMenuBean.setMenu(getText("report-lbl"));
						objSubMenu = new ArrayList<String>();
						if(objAccessBean.getUserTopCustSuppIndv()!= null && objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_customer_supplier_by_rfc"));
						}
						if(objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on")){
							objSubMenu.add(getText("complete_individual_report"));					
						}
						if(objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_rfc_report"));
						}
						if(objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_hscode_report"));
						}
						if(objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")){
							objSubMenu.add(getText("dragon_report_lbl"));
						}
					}*/
					if (objBean.getUserType().equalsIgnoreCase("unLimited") && 
							((objAccessBean.getUserTopCustSuppIndv()!= null && objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")) ||
							( objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on")) || 
							( objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on"))||
							( objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")) ||
							( objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")))) 
						{
						objMenuBean = new MobMenuBean();
						objMenuBean.setMenu(getText("report-lbl"));
						objSubMenu = new ArrayList<String>();
						if(objAccessBean.getUserTopCustSuppIndv()!= null && objAccessBean.getUserTopCustSuppIndv().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_customer_supplier_by_rfc"));
						}
						if(objAccessBean.getUserCompleteIndividual()!=null && objAccessBean.getUserCompleteIndividual().equalsIgnoreCase("on")){
							objSubMenu.add(getText("complete_individual_report"));					
						}
						if(objAccessBean.getUserTopRFCReport()!= null && objAccessBean.getUserTopRFCReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_rfc_report"));
						}
						if(objAccessBean.getUserTopHscodeReport()!= null && objAccessBean.getUserTopHscodeReport().equalsIgnoreCase("on")){
							objSubMenu.add(getText("top_hscode_report"));
						}
						if(objAccessBean.getUserDragon()!= null && objAccessBean.getUserDragon().equalsIgnoreCase("on")){
							objSubMenu.add(getText("dragon_report_lbl"));
						}
					}
					objMenuBean.setSubMenu(objSubMenu);
					objMenuList.add(objMenuBean);
				}
				objMenuBean = new MobMenuBean();
				objMenuBean.setMenu(getText("profile-lbl"));
				objSubMenu = new ArrayList<String>();
				objSubMenu.add("Change Password");
				objMenuBean.setSubMenu(objSubMenu);
				objMenuList.add(objMenuBean);
				objBean.setObjMenuList(objMenuList);
				// ------ Set All Values In Session -------
				httpSession.setAttribute("password", objBean.getPassword());
				httpSession.setAttribute("userId", objBean.getUserID());
				httpSession.setAttribute("isMainUser", objBean.getIsMainUser());
				httpSession.setAttribute("commerece", objBean.getCommerce());
				httpSession.setAttribute("language", objBean.getLanguage());
				httpSession.setAttribute("userType", objBean.getUserType());
				httpSession.setAttribute("objAccessBean", objAccessBean);
				httpSession.setAttribute("userName", objBean.getName());
				httpSession.setAttribute("email", objBean.getEmail());
				httpSession.setAttribute("consultTillDate",objBean.getConsultTillDate());
				httpSession.setAttribute("consultTillDateForAlert",objBean.getConsultTillDateAlert());
				httpSession.setAttribute("userType", objBean.getUserType());
				httpSession.setAttribute("objAccessBean", objAccessBean);
				httpSession.setAttribute("password", objBean.getPassword());
				httpSession.setAttribute("userId", objBean.getUserID());
			} else {
				objBean.setResult("error");
			}
			loginResponse.setCode(objBean.getResult());
			loginResponse.setMessage(objBean.getMessage());
			loginResponse.setResult(objBean);
			System.out.println("Code: " + objBean.getResult());
			System.out.println("Message: " + objBean.getMessage());

		}
		return SUCCESS;
	}

	public String logout() {
		request = ServletActionContext.getRequest();
		httpSession = request.getSession(true);
		emptyResponse = new MobEmptyResponse();
		if (ServletActionContext.getRequest().getSession() != null) {
			httpSession.removeAttribute("password");
			httpSession.removeAttribute("userId");
			httpSession = ServletActionContext.getRequest().getSession();
			httpSession.invalidate();
			System.out.println("Session Invalidate Successfully...!");
			emptyResponse.setCode("success");
			emptyResponse.setMessage("User logged out successfully..!");
		}
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
