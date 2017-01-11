package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import mBean.MobReportAccessBean;
import mBean.MobReportDetailBean;
import mBean.MobStandardReportBean;
import mBean.MobUserBean;
import mConnection.MobMyConnection;

public class MobReportDAO {
	MobMyConnection objConnection = new MobMyConnection();
	
	public ArrayList<MobReportDetailBean> getassignedStandardReportList(String userID, String custID, String userType, MobReportAccessBean objAccessBean, String commerece, int startLimit) {
		ArrayList<MobReportDetailBean> objArrayList = new ArrayList<MobReportDetailBean>();
		MobReportDetailBean objBean = null;
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		try {
			System.out.println(userType+"*****************");
			//int endLimit = startLimit + 10;
			String getReportListQuery = "";
			if (userType.equalsIgnoreCase("limited")) {
				getReportListQuery = "SELECT temp.is_limited, temp.report_id, temp.report_name, temp.created_date, temp.report_type, temp.status, temp.isEditable, temp.isDeletable "
						+ " FROM (SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " CASE  report_type when 'common' then concat('International Query (Standard)') "
						+ " else concat('International Query (Extended)') end as report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' "

						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') "
						+ " else concat('International Query Monthly (Extended)') end as report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' and report_by = 'monthly' "
						
							
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Credit Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_credit_report a JOIN save_credit_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Top HSCODE Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_inc_top_hscode_report a JOIN inc_rfc_top_hscode b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Top Customer Supplier Report By RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_nc_rfc_client_supplier_compare a JOIN nc_rfc_client_supplier_compare b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
						+ " 'Top Customer Supplier Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_nc_rfc_top_client_supplier a JOIN nc_rfc_top_client_supplier b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'International Table One Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_inc_custom_report1 a JOIN international_custom_report1 b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'International Table Two Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_inc_custom_report2 a JOIN international_custom_report2 b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'National Table' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_national_custom_report a JOIN national_custom_report1 b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+"  UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Quintile Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_quintile_report a JOIN save_quintile_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Full Detail Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_dragon_report a JOIN save_dragon_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"

						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'National Query' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_nat_std_report a JOIN save_standard_report_nc b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' "
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
						+ " 'Example CI and CN Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_complete_individual_report a JOIN save_complete_individual_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						/*+ "UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'National Query Monthly' AS report_type, b.status  "
						+ " FROM assigned_nat_std_report a JOIN save_standard_report_nc b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"') temp "*/
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Competitive Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_competitive_report a JOIN save_competitive_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
	/* TOP RFC */		+ " 'Top Import/Export Report by RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_top_rfc_report a JOIN save_top_rfc_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
	/* TOP HSCODE */	+ " 'Top Import/Export Report by HSCODE' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_top_hscode_report a JOIN save_top_hscode_report b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
						
						+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
						+ " 'Top Customer Supplier Report By Activity' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
						+ " FROM assigned_customer_supplier_by_activity a JOIN save_customer_supplier_by_activity b "
						+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"') temp"
						
						+ " ORDER BY created_date desc, report_name, report_type limit " + startLimit + ", 10";
			} else if(userType.equalsIgnoreCase("package")){
				getReportListQuery= "SELECT temp.is_limited, temp.report_id, temp.report_name, temp.created_date, temp.report_type, temp.status, temp.isEditable, temp.isDeletable"
					+ " FROM (SELECT '' as is_limited, report_id, report_name, "
					+ " date(created_date) as created_date, 'National Query' AS report_type, status,  "
					+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isEditable, "
					+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isDeletable "
					+ "FROM save_standard_report_nc "
					+ " WHERE user_id = '"+userID+"' "
					
					+ "UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
					+ " CASE  report_type when 'common' then concat('International Query (Standard)') "
					+ " else concat('International Query (Extended)') end as report_type ,status,  "
					+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isEditable, "
					+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isDeletable "
					+ " FROM save_standard_report_inc "
					+ " WHERE user_id = '"+userID+"' "
					
					+ "UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
					+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') "
					+ " else concat('International Query Monthly (Extended)') end as report_type ,status, "
					+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isEditable, "
					+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isDeletable "
					+ " FROM save_standard_report_inc "
					+ " WHERE user_id = '"+userID+"' and report_by = 'monthly' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " CASE  report_type when 'common' then concat('International Query (Standard)') else concat('International Query (Extended)') end as report_type, b.status,  "
					+ " 'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
					+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') "
					+ " else concat('International Query Monthly (Extended)') end as report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' and report_by = 'monthly' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'National Query' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nat_std_report a JOIN save_standard_report_nc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'International Table One Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_custom_report1 a JOIN international_custom_report1 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'International Table Two Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_custom_report2 a JOIN international_custom_report2 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'National Table' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_national_custom_report a JOIN national_custom_report1 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top HSCODE Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_top_hscode_report a JOIN inc_rfc_top_hscode b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top Customer Supplier Report By RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nc_rfc_client_supplier_compare a JOIN nc_rfc_client_supplier_compare b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top Customer Supplier Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nc_rfc_top_client_supplier a JOIN nc_rfc_top_client_supplier b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Credit Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_credit_report a JOIN save_credit_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
					+ " 'Quintile Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM save_quintile_report b where b.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Quintile Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_quintile_report a JOIN save_quintile_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Full Detail Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_dragon_report a JOIN save_dragon_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Example CI and CN Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_complete_individual_report a JOIN save_complete_individual_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Competitive Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_competitive_report a JOIN save_competitive_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
	/* TOP RFC */	+ " 'Top Import/Export Report by RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_top_rfc_report a JOIN save_top_rfc_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)') as report_name, date(b.created_date) as created_date,  "
	/* TOP HSCODE */+ " 'Top Import/Export Report by HSCODE' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_top_hscode_report a JOIN save_top_hscode_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top Customer Supplier Report By Activity' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_customer_supplier_by_activity a JOIN save_customer_supplier_by_activity b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ ") temp ORDER BY created_date DESC, report_name, report_type limit " + startLimit + ", 10";
			
			} else {
				getReportListQuery= "SELECT temp.is_limited, temp.report_id, temp.report_name, temp.created_date, temp.report_type, temp.status, temp.isEditable, temp.isDeletable"
						+ " FROM (";
				
				if (objAccessBean.getUserNatQuery() != null && objAccessBean.getUserNatQuery().equals("on")) {
					getReportListQuery = getReportListQuery +
							"SELECT '' as is_limited, report_id, report_name, "
							+ " date(created_date) as created_date, 'National Query' AS report_type, status, "
							+ " if('"+commerece+"' = 'international', 'FALSE', 'FALSE') as isEditable, "
							+ " if('national' = 'international', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM save_standard_report_nc "
							+ " WHERE user_id = '"+userID+"' ";
				} else {
					getReportListQuery = getReportListQuery +
							"SELECT 'yes' as is_limited, report_id, report_name, "
							+ " date(created_date) as created_date, 'National Query' AS report_type "
							+ " , status, 'FALSE' isEditable, 'FALSE' isDeletable FROM save_standard_report_nc "
							+ " WHERE user_id = '"+userID+"' ";
				}
				
				if (objAccessBean.getUserIntQuery() != null && objAccessBean.getUserIntQuery().equals("on")) {
					getReportListQuery = getReportListQuery
							+ "UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " CASE  report_type when 'common' then concat('International Query (Standard)') "
							+ " else concat('International Query (Extended)') end as report_type ,status, "
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM save_standard_report_inc "
							+ " WHERE user_id = '"+userID+"' ";
				} else {
					getReportListQuery = getReportListQuery
							+ "UNION SELECT 'yes' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " CASE  report_type when 'common' then concat('International Query (Standard)') "
							+ " else concat('International Query (Extended)') end as report_type ,status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_standard_report_inc "
							+ " WHERE user_id = '"+userID+"' ";
				}
				
				if (objAccessBean.getUserIntQuery() != null && objAccessBean.getUserIntQuery().equals("on")) {
					getReportListQuery = getReportListQuery
							+ "UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') "
							+ " else concat('International Query Monthly (Extended)') end as report_type ,status,"
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM save_standard_report_inc "
							+ " WHERE user_id = '"+userID+"' and report_by = 'monthly' ";
				} else {
					getReportListQuery = getReportListQuery
							+ "UNION SELECT 'yes' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') "
							+ " else concat('International Query Monthly (Extended)') end as report_type ,status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_standard_report_inc "
							+ " WHERE user_id = '"+userID+"' and report_by = 'monthly' ";
				}
				
				if (objAccessBean.getUserIntTbleOne() != null && objAccessBean.getUserIntTbleOne().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'International Table One Variable' AS report_type ,status, "
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM international_custom_report1 WHERE user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'International Table One Variable' AS report_type ,status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM international_custom_report1 WHERE user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserIntTbleTwo() != null && objAccessBean.getUserIntTbleTwo().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'International Table Two Variable' AS report_type ,status, "
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+commerece+"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM international_custom_report2 WHERE user_id = '"+userID+"' ";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'International Table Two Variable' AS report_type ,status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM international_custom_report2 WHERE user_id = '"+userID+"' ";
				}
				
				if (objAccessBean.getUserNatTbl() != null && objAccessBean.getUserNatTbl().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'National Table' AS report_type ,status, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isEditable, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM national_custom_report1 WHERE user_id = '"+userID+"'  ";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, report_id, report_name, date(created_date) as created_date, "
							+ " 'National Table' AS report_type ,status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM national_custom_report1 WHERE user_id = '"+userID+"'  ";
				}
				
				if (objAccessBean.getUserTopHscode() != null && objAccessBean.getUserTopHscode().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top HSCODE Group Report' AS report_type, b.status, "
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM inc_rfc_top_hscode b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top HSCODE Group Report' AS report_type, b.status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM inc_rfc_top_hscode b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserTopCustSuppIndv() != null && objAccessBean.getUserTopCustSuppIndv().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top Customer Supplier Report By RFC' AS report_type, b.status, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'TRUE') as isEditable, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'TRUE') as isDeletable "
							+ " FROM nc_rfc_client_supplier_compare b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top Customer Supplier Report By RFC' AS report_type, b.status,  "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM nc_rfc_client_supplier_compare b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserTopCustSuppGroup() != null && objAccessBean.getUserTopCustSuppGroup().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top Customer Supplier Group Report' AS report_type, b.status,"
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isEditable, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM nc_rfc_top_client_supplier b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Top Customer Supplier Group Report' AS report_type, b.status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM nc_rfc_top_client_supplier b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserCredit() != null && objAccessBean.getUserCredit().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Credit Report' AS report_type, ifnull(b.status, '0') status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_credit_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Credit Report' AS report_type, ifnull(b.status, '0') status, "
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_credit_report b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserDragon() != null && objAccessBean.getUserDragon().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Full Detail Report' AS report_type, b.status,"
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isEditable,"
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isDeletable "
							+ " FROM save_dragon_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Full Detail Report' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_dragon_report b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserQuintile() != null && objAccessBean.getUserQuintile().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Quintile Report' AS report_type, b.status,"
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isEditable,"
							+ " if('"+ commerece +"' = 'national', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM save_quintile_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Quintile Report' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_quintile_report b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserCompleteIndividual() != null && objAccessBean.getUserCompleteIndividual().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Example CI and CN Report' AS report_type, b.status,"
							+ " 'TRUE' isEditable, 'TRUE' isDeletable"
							+ " FROM save_complete_individual_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Example CI and CN Report' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_complete_individual_report b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserCompetitiveReport() != null && objAccessBean.getUserCompetitiveReport().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Competitive Report' AS report_type, b.status,"
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isEditable, "
							+ " if('"+ commerece +"' = 'international', 'FALSE', 'FALSE') as isDeletable "
							+ " FROM save_competitive_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
							+ " 'Competitive Report' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_competitive_report b where b.user_id = '"+userID+"'";
				}
				
				if (objAccessBean.getUserTopRFCReport() != null && objAccessBean.getUserTopRFCReport().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
			/* TOP RFC */   + " 'Top Import/Export Report by RFC' AS report_type, b.status,"
			+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isEditable,"
			+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isDeletable "
							+ " FROM save_top_rfc_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
			/* TOP RFC */	+ " 'Top Import/Export Report by RFC' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_top_rfc_report b where b.user_id = '"+userID+"'";
				}
				
				
				if (objAccessBean.getUserTopHscodeReport() != null && objAccessBean.getUserTopHscodeReport().equals("on")) {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT '' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
		/* TOP HSCODE */   + " 'Top Import/Export Report by HSCODE' AS report_type, b.status,"
		+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isEditable,"
		+ " if('"+ commerece +"' = 'national', 'FALSE', 'TRUE') as isDeletable "
							+ " FROM save_top_hscode_report b where b.user_id = '"+userID+"'";
				} else {
					getReportListQuery = getReportListQuery
							+ " UNION SELECT 'yes' as is_limited, b.report_id, b.report_name, date(b.created_date) as created_date,  "
			/* TOP HSCODE */	+ " 'Top Import/Export Report by HSCODE' AS report_type, b.status,"
							+ " 'FALSE' isEditable, 'FALSE' isDeletable"
							+ " FROM save_top_hscode_report b where b.user_id = '"+userID+"'";
				}
				
				getReportListQuery = getReportListQuery
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'International Table One Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_custom_report1 a JOIN international_custom_report1 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'International Table Two Variable' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_custom_report2 a JOIN international_custom_report2 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'National Table' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_national_custom_report a JOIN national_custom_report1 b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top Customer Supplier Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nc_rfc_top_client_supplier a JOIN nc_rfc_top_client_supplier b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " CASE  report_type when 'common' then concat('International Query (Standard)') else concat('International Query (Extended)') end as report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " CASE  report_type when 'common' then concat('International Query Monthly (Standard)') else concat('International Query Monthly (Extended)') end as report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_int_std_report a JOIN save_standard_report_inc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' and report_by = 'monthly' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top HSCODE Group Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_inc_top_hscode_report a JOIN inc_rfc_top_hscode b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Top Customer Supplier Report By RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nc_rfc_client_supplier_compare a JOIN nc_rfc_client_supplier_compare b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Credit Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_credit_report a JOIN save_credit_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Full Detail Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_dragon_report a JOIN save_dragon_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'National Query' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_nat_std_report a JOIN save_standard_report_nc b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"' "
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Quintile Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_quintile_report a JOIN save_quintile_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Example CI and CN Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_complete_individual_report a JOIN save_complete_individual_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
					+ " 'Competitive Report' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_competitive_report a JOIN save_competitive_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
	/* TOP RFC */	+ " 'Top Import/Export Report by RFC' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_top_rfc_report a JOIN save_top_rfc_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ " UNION SELECT 'yes' as is_limited, b.report_id, concat(b.report_name,' (By Admin)'), date(b.created_date) as created_date,  "
	/* TOP HSCODE */+ " 'Top Import/Export Report by HSCODE' AS report_type, b.status,  'FALSE' isEditable, 'FALSE' isDeletable  "
					+ " FROM assigned_top_hscode_report a JOIN save_top_hscode_report b "
					+ " ON a.report_id = b.report_id AND a.user_id = '"+userID+"'"
					
					+ ") temp ORDER BY created_date DESC, report_name, report_type limit " + startLimit + ", 10";
			}
			PreparedStatement pstmt = conn.prepareStatement(getReportListQuery);
			System.out.println("getReportListQuery: "+ getReportListQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBean = new MobReportDetailBean();
				objBean.setEditable(rs.getBoolean("isEditable"));
				objBean.setDeletable(rs.getBoolean("isDeletable"));
				objBean.setReportName(rs.getString("report_name"));
				objBean.setReportID(rs.getString("report_id"));
				objBean.setCreatedDate(rs.getString("created_date"));
				objBean.setReportType(rs.getString("report_type"));
				objBean.setStatus(rs.getInt("status"));
				objBean.setCreatedByAdmin(rs.getString("is_limited"));
				objArrayList.add(objBean);
			}
			System.out.println("Total assigned reports: " + objArrayList.size());
		} catch (SQLException e) {
			System.out.println("Error: ReportDAO/getassignedStandardReportList()...!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: ReportDAO/getassignedStandardReportList()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! ReportDAO / getassignedStandardReportList()...!");
				e2.printStackTrace();
			}
		}
		return objArrayList;
	}

	public String deleteReportFromSystem(String tableName, String reportID) {
		Connection conn = objConnection.getConnection();
		String result = "success";
		try {
			String query = "DELETE FROM "+tableName+" WHERE report_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reportID);
			System.out.println("Query: "+ pstmt.toString());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result ="error";
			e.printStackTrace();
		} catch (Exception e) {
			result ="error";
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public MobUserBean getUserType(String userID) {
		MobUserBean objBean = new MobUserBean();
		ResultSet rs = null;
		Connection conn = objConnection.getConnection();
		try {
			System.out.println("UserID: "+ userID);
			String query = "SELECT user_type, total_downloads FROM user WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			System.out.println("query: " + query);
			while (rs.next()) {
				objBean.setUserType(rs.getString("user_type"));
				objBean.setDownloads(rs.getInt("total_downloads"));
			}
		} catch (SQLException e) {
			System.out.println("Error: UserDAO/getUserType()...!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: UserDAO/getUserType()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! UserDAO / getUserType()...!");
				e2.printStackTrace();
			}
		}
		return objBean;
	}

	public MobStandardReportBean getStandardFileName(String reportID,
			String reportType, String userID, int userID2) {
		MobStandardReportBean objBean = null;
		Connection conn = objConnection.getConnection();
		ResultSet rs = null;
		try {
			String query = "";
			System.out.println("report type "+reportType);
			if (reportType.equalsIgnoreCase("National Query")) {
				query = "SELECT a.user_id, a.report_name, a.report_id, c.name, date(ifnull(a.modified_date, a.created_date)) date FROM save_standard_report_nc a, user b, customer c WHERE report_id = ? and "+userID+" = b.user_id and b.customer_id = c.customer_id";
			} else if (reportType.equalsIgnoreCase( "International Query (Standard)")
					|| reportType.equalsIgnoreCase("International Query (Extended)") || reportType.equalsIgnoreCase("International Query Monthly (Standard)")
					||reportType.equalsIgnoreCase("International Query Monthly (Extended)")){
				query = "SELECT a.user_id, a.report_name, a.report_id, c.name, date(ifnull(a.modified_date, a.created_date)) date FROM save_standard_report_inc a, user b, customer c WHERE report_id = ? and "+userID+" = b.user_id and b.customer_id = c.customer_id";
			} else if (reportType.equalsIgnoreCase("International Table One Variable")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM international_custom_report1 WHERE report_id = ?";
			} else if (reportType.equalsIgnoreCase("International Table Two Variable")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM international_custom_report2 WHERE report_id = ?";
			} else if (reportType.equalsIgnoreCase("National Table")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM national_custom_report1 WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top HSCODE Group Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM inc_rfc_top_hscode WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Credit Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_credit_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top Customer Supplier Group Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM nc_rfc_top_client_supplier WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top Customer Supplier Report By RFC") || reportType.equalsIgnoreCase("Customer Supplier Individual Report") ){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM nc_rfc_client_supplier_compare WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Full Detail Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_dragon_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Quintile Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_quintile_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Example CI and CN Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_complete_individual_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Competitive Report")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_competitive_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top Customer Supplier Report By Activity")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_customer_supplier_by_activity WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top Import/Export Report by RFC")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_top_rfc_report WHERE report_id = ?";
			}else if (reportType.equalsIgnoreCase("Top Import/Export Report by HSCODE")){
				query = "SELECT user_id, report_name, report_id, '' name, '' date FROM save_top_hscode_report WHERE report_id = ?";
			}
			PreparedStatement pstmt = conn.prepareStatement(query);
			System.out.println("query "+query);
			pstmt.setString(1, reportID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBean = new MobStandardReportBean();
				objBean.setReportName(rs.getString("report_name"));
				objBean.setReportId(rs.getString("report_id"));
				objBean.setUserId(rs.getString("user_id"));
				objBean.setCustomerName(rs.getString("name"));
				objBean.setDate(rs.getString("date"));
			}
		} catch (SQLException e) {
			System.out.println("Error: RegistrationDAO/getStandardFileName()...!");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				System.out.println("Error while closing Connection...! RegistrationDAO / getStandardFileName()...!");
				e2.printStackTrace();
			}
		}
		return objBean;
	}

	public String getStandardReportFilePath() {
		String path = "";
		try {
			Context env = (Context) new InitialContext().lookup("java:comp/env");
			path = (String) env.lookup("standardReportFilePath");
		} catch (Exception ex) {
			System.out.println("Error reading context");
		}
		return path;
	}
}
