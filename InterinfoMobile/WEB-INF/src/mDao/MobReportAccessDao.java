package mDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mBean.MobReportAccessBean;
import mConnection.MobMyConnection;


public class MobReportAccessDao {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MobReportAccessDao() {
		initalize();
	}

	public void initalize() {
		conn = new MobMyConnection().getConnection();
	}

	public void closeall() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			System.out.println("Error While Closing Connection...! ReportAccessDao...!");
			e.printStackTrace();
		}
	}
	public MobReportAccessBean getUserAccessInfo(int customerID, String userID) {
		MobReportAccessBean objBean = new MobReportAccessBean();
		System.out.println("In MobReportAccessDAO");
		try {
			String getUserListQuery = "";
			if (userID.equals("0")) {
				getUserListQuery = "select customer_id, user_id, national_query, international_query, "
						+ " international_table_one, international_table_two, national_table, top_hscode, "
						+ " top_cust_supp_group, top_cust_supp_individual, dragon_report, credit_report, "
						+ " quintile_report, export_prod_supp_graph, informant_informed_graph, "
						+ " prod_exporter_country_fc_graph, prod_imp_prod_graph, prod_imp_client_client_graph, "
						+ " prod_fc_imp_client_graph, rfc_activity_state_rfc_client_graph, two_way_graph, "
						+ " complete_individual_report, competitive_report, company_supplier_supplier, "
						+ " rfc_activity_state_rfc_supplier_graph, customer_supplier_activity, top_rfc_report as top_rfc_report,top_hscode_report " 
						+ " from user_report_access where "
						+ " customer_id = ? and user_id = (select user_id from user where customer_id = "+customerID+" limit 1)";
			} else{
				getUserListQuery = "select customer_id, user_id, national_query, international_query, "
						+ " international_table_one, international_table_two, national_table, top_hscode, "
						+ " top_cust_supp_group, top_cust_supp_individual, dragon_report, credit_report, "
						+ " quintile_report, export_prod_supp_graph, informant_informed_graph, "
						+ " prod_exporter_country_fc_graph, prod_imp_prod_graph, prod_imp_client_client_graph, "
						+ " prod_fc_imp_client_graph, rfc_activity_state_rfc_client_graph, two_way_graph, "
						+ " complete_individual_report, competitive_report, company_supplier_supplier, "
						+ " rfc_activity_state_rfc_supplier_graph, customer_supplier_activity, top_rfc_report as top_rfc_report,top_hscode_report " 
						+ " from user_report_access where "
						+ " customer_id = ? and user_id ="+ userID;
			}
			PreparedStatement pstmt = conn.prepareStatement(getUserListQuery);
			pstmt.setInt(1, customerID);
			System.out.println("getUserListQuery: " + pstmt.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBean.setCustomerId(rs.getInt("customer_id"));
				objBean.setUserID(rs.getString("user_id"));
				objBean.setUserNatQuery(rs.getString("national_query"));
				objBean.setUserIntQuery(rs.getString("international_query"));
				objBean.setUserIntTbleOne(rs.getString("international_table_one"));
				objBean.setUserIntTbleTwo(rs.getString("international_table_two"));
				objBean.setUserNatTbl(rs.getString("national_table"));
				objBean.setUserTopHscode(rs.getString("top_hscode"));
				objBean.setUserTopCustSuppGroup(rs.getString("top_cust_supp_group"));
				objBean.setUserTopCustSuppIndv(rs.getString("top_cust_supp_individual"));
				objBean.setUserDragon(rs.getString("dragon_report"));
				objBean.setUserCredit(rs.getString("credit_report"));
				objBean.setUserQuintile(rs.getString("quintile_report"));
				objBean.setUserExportProdSupp(rs.getString("export_prod_supp_graph"));
				objBean.setUserInformantInformed(rs.getString("informant_informed_graph"));
				objBean.setUserProdExporterCountryFC(rs.getString("prod_exporter_country_fc_graph"));
				objBean.setUserProdImpProd(rs.getString("prod_imp_prod_graph"));
				objBean.setUserProdImpClientClient(rs.getString("prod_imp_client_client_graph"));
				objBean.setUserProdFCImpClient(rs.getString("prod_fc_imp_client_graph"));
				objBean.setUserRfcActStateRfcClient(rs.getString("rfc_activity_state_rfc_client_graph"));
				objBean.setUserTwoWay(rs.getString("two_way_graph"));
				objBean.setUserCompleteIndividual(rs.getString("complete_individual_report"));
				objBean.setUserCompetitiveReport(rs.getString("competitive_report"));
				objBean.setUserCompanySuppliersSuppliers(rs.getString("company_supplier_supplier"));
				objBean.setUserRfcActivityStateRfcSupplier(rs.getString("rfc_activity_state_rfc_supplier_graph"));
				objBean.setUserCustomerSupplierActivity(rs.getString("customer_supplier_activity"));
				objBean.setUserTopRFCReport(rs.getString("top_rfc_report"));
				objBean.setUserTopHscodeReport(rs.getString("top_hscode_report"));
			}
		} catch (SQLException e) {
			System.out.println("Error: ReportAccessDao/getCustomerAccessInfo()...!");
			e.printStackTrace();
		}
		return objBean;
	}
}
