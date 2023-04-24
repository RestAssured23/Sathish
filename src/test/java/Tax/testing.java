package Tax;

import MFPojo.Tax.Root;
import MFPojo.Transac_History.History;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class testing {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTE1OTc5Iiwic2NvcGVzIjoicmVhZCx3cml0ZSIsIm5hbWUiOiJHaXJpcmFqYW4iLCJlbWFpbCI6ImdpcmlyYWphbkBmdW5kc2luZGlhLmNvbSIsIm1vYmlsZSI6Ijc0MDI3NTkwMDEiLCJtYW5hZ2VtZW50LXVzZXItaWQiOjE4OTA3NDMsIm1hbmFnZW1lbnQtdXNlci1yb2xlcyI6ImFkbWluIiwiaXNzIjoiZnVuZHNpbmRpYS5jb20iLCJqdGkiOiI1Mjg5YjJmOC1jMjIwLTRhNjYtOTg5MS1mOTA0Y2Q0ZThmODYiLCJpYXQiOjE2ODA2MDEyOTMsImV4cCI6MTY4MDYwNDk1M30.4BNU7kVeIE8wQIW3-S0DZKAzP1FHhqYVhjtwk7XRLm9Gnck60zWZM3OhQT2biVzxRfFG5KxIDdzyA4e86GqEMA";
	RequestSpecification req = new RequestSpecBuilder()
			//	.setBaseUri("https://dev-api.fundsindia.com/core")
			.setBaseUri("https://hotfix-api.fundsindia.com/core")
			//.setBaseUri("https://api.fundsindia.com/core")
			.addHeader("x-api-version", "2.0")
			.addHeader("channel-id", "10")
			//	.addHeader("x-fi-access-token", Tax_Login.tax())
		//	.addHeader("x-fi-access-token", Live_Login.sathish())
		.addHeader("x-fi-access-token",token)
			.setContentType(ContentType.JSON).build();

	ResponseSpecification respec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();

	String indexation_holdid="";		// user id= 1446718
	String transfer_holdid="2060";         // User id=2597
	String more_holdid="389154"	;		//userid 807906
	String NRIhold_id="348466";  //351925   352143   Userid =748189
	String Nri_folio="3813711/17"; String Nri_scheme="744"; String Nari_goal="805698";
	String sat_hid="1403821"; String sat_folio="79958299852";  String sat_schem="9767"; String sat_goal="2932872";
	String sara_hid="935406";

	// G_User Id= 1515979
	String G_holdid="912186";	String G_folio="6063546/63";	String G_scheme="42237";	String G_goal="1726608";


	String S_holdid="920307";   		// S_user id=1527625
	String S_schem="2637";	String S_folio="499247821994";	String S_goal="1844415";

	String Fairvalue_id="306935";			// user id= 681575  // merger folio= 11177626/22
	String Fair_folio="91021629211";	String Fair_schem="8408"; 	String Fair_goal="730433";


	//Unrealized final
	@Test
	public void Pojo_TaxTest() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("holdingProfileId", "912186");                //183121		183128	183135(NRI)
		payload.put("financialYear", "");            //previous , current
		payload.put("gainType", "realized");                    //[ realized, unrealized ]
		ArrayList<String> list = new ArrayList<String>();
		list.add("equity");
		list.add("non_equity");                            //[ equity, non_equity ]
		payload.put("categories", list);

		RequestSpecification res = given().spec(req)
				.body(payload);
		Root tax = res.when().post("/investor/dashboard/portfolio/tax")
				.then().log().all().spec(respec).extract().response().as(Root.class);
		int datacount = tax.getData().size();

		//System.out.println(datacount);
		for (int i = 0; i < datacount; i++) {
			int Eq_count = tax.getData().get(i).getSchemes().size();

			if (tax.getData().get(i).getCategoryTax().getAsset().equalsIgnoreCase("equity")) {
				System.out.println("Equity Count :" + Eq_count);
				double Total_inves = tax.getData().get(i).getCategoryTax().getInvestmentCost();
				double Total_redeem = tax.getData().get(i).getCategoryTax().getRedemptionCost();
				double Total_STCG = tax.getData().get(i).getCategoryTax().getShortTermGain();
				double Total_LTCG = tax.getData().get(i).getCategoryTax().getLongTermGain();
				System.out.println("Total Investment : " + Total_inves);
				System.out.println("Total Redeem : " + Total_redeem);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				double cost = 0.0;
				double rcost = 0.0;
				double stcg = 0.0;
				double ltcg = 0.0;
				double Inv_cost, redeem_cost;
				double add_stcg, add_ltcg;
				//double ;
				for (int j = 0; j < Eq_count; j++) {   // list the scheme transaction
					int trns_size=tax.getData().get(i).getSchemes().get(j).getTransactions().size();
					Inv_cost = tax.getData().get(i).getSchemes().get(j).getInvestmentCost();
					redeem_cost = tax.getData().get(i).getSchemes().get(j).getRedemptionCost();
					add_stcg = tax.getData().get(i).getSchemes().get(j).getShortTermGain();
					add_ltcg = tax.getData().get(i).getSchemes().get(j).getLongTermGain();
					double tets=tax.getData().get(i).getSchemes().get(j).getTransactions().size();
					System.out.println("Investment Cost List : " + trns_size);
					cost = cost + Inv_cost;
					rcost = rcost + redeem_cost;
					stcg = stcg + add_stcg;
					ltcg = ltcg + add_ltcg;
					System.out.println("===========================================");
					for(int m=0;m<trns_size;m++)   // Listing the transaction
					{
						double trns=tax.getData().get(i).getSchemes().get(j).getTransactions().get(m).getInvestmentCost();
						double red=tax.getData().get(i).getSchemes().get(j).getTransactions().get(m).getAmount();
						System.out.println("Transaction : " +trns);
						System.out.println("Redem Cost : " +red);
						System.out.println("===========================================");
					}
				}
				System.out.println("Added Investment Cost : " + cost);
				System.out.println("Added Redeem cost : " + rcost);
				System.out.println("Added STCG Price : " + stcg);
				System.out.println("Added LTCG Price : " + ltcg);
				Assert.assertEquals(Total_inves, cost);
				Assert.assertEquals(Total_redeem, rcost);
				Assert.assertEquals(Total_STCG, stcg);
				Assert.assertEquals(Total_LTCG, ltcg);
				System.out.println("------------------------------------------------");
			} else {
				int non_Eqcount = tax.getData().get(i).getSchemes().size();
				double Total_inves = tax.getData().get(i).getCategoryTax().getInvestmentCost();
				double Total_redeem = tax.getData().get(i).getCategoryTax().getRedemptionCost();
				double Total_STCG = tax.getData().get(i).getCategoryTax().getShortTermGain();
				double Total_LTCG = tax.getData().get(i).getCategoryTax().getLongTermGain();
				System.out.println("Non_Equity Count : " + non_Eqcount);
				System.out.println("Total Investment : " + Total_inves);
				System.out.println("Total Redeem : " + Total_redeem);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				double cost = 0.0;
				double rcost = 0.0;
				double stcg = 0.0;
				double ltcg = 0.0;
				double Inv_cost, redeem_cost;
				double add_stcg, add_ltcg;
				for (int j = 0; j < Eq_count; j++) {
					Inv_cost = tax.getData().get(i).getSchemes().get(j).getInvestmentCost();
					redeem_cost = tax.getData().get(i).getSchemes().get(j).getRedemptionCost();
					add_stcg = tax.getData().get(i).getSchemes().get(j).getShortTermGain();
					add_ltcg = tax.getData().get(i).getSchemes().get(j).getLongTermGain();
					cost = cost + Inv_cost;
					rcost = rcost + redeem_cost;
					stcg = stcg + add_stcg;
					stcg = stcg + add_ltcg;
				}
				System.out.println("Added Investment Cost : " + cost);
				System.out.println("Added Redeem cost : " + rcost);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				Assert.assertEquals(Total_inves, cost);
				Assert.assertEquals(Total_redeem, rcost);
				Assert.assertEquals(Total_STCG, stcg);
				Assert.assertEquals(Total_LTCG, ltcg);
			}
		}
	}



	@Test
	public void tran_hist() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("holdingProfileId", sat_hid);
		payload.put("folio", sat_folio);
		payload.put("schemeCode", sat_schem);
		payload.put("goalId", sat_goal);
		payload.put("page", 1);
		payload.put("size", 50);
		payload.put("sortBy", "transactionDate");
		payload.put("filterBy", "");
		payload.put("orderBy", "asc");

		RequestSpecification res = given().spec(req)
				.body(payload);
		History transac = res.when().post("/investor/transaction-history")
				.then().spec(respec).extract().response().as(History.class);

		Double tot_inv = transac.getData().getInfo().getTotalInvestmentCost();
		Double tot_redeem = transac.getData().getInfo().getTotalRedemptionAmount();
		System.out.println("TotalInvestmentCost : " + tot_inv);
		System.out.println("TotalRedemptionAmount : " + tot_redeem);
		int trac_size = transac.getData().getTransactions().getContent().size();
		System.out.println("Transactions Count: " + trac_size);

		for (int i = 0; i < trac_size; i++) {

			if (transac.getData().getTransactions().getContent().get(i).getTransactionType()
					.equalsIgnoreCase("New Investment (SIP)") |
					transac.getData().getTransactions().getContent().get(i).getTransactionType()
							.equalsIgnoreCase("Additional Investment (SIP)")) {

				System.out.println("------------------------------------------------");

				System.out.println("Invested Date : " + transac.getData().getTransactions().getContent().get(i).getTransactionDate());
				System.out.println("NAV as On : " + transac.getData().getTransactions().getContent().get(i).getNav());
				System.out.println("Total  Units :" + transac.getData().getTransactions().getContent().get(i).getUnits());
				double amt = transac.getData().getTransactions().getContent().get(i).getAmount();
				double unit = transac.getData().getTransactions().getContent().get(i).getUnits();
				double NAV = transac.getData().getTransactions().getContent().get(i).getNav();
				double stamp = transac.getData().getTransactions().getContent().get(i).getStampDuty();
				double stt = transac.getData().getTransactions().getContent().get(i).getStt();
				double tds = transac.getData().getTransactions().getContent().get(i).getTds();
				System.out.println("Stamp Duty: " + stamp);
				System.out.println("STT :" + stt);
				System.out.println("TDS :" + tds);
				double total_amt = transac.getData().getTransactions().getContent().get(i).getNav() *
						transac.getData().getTransactions().getContent().get(i).getUnits() + stamp;
				System.out.println("Total Amount for tax: " + total_amt);
			} else {
				System.out.println("-----------Redemption--------------------");
				System.out.println("Redeem Date : " + transac.getData().getTransactions().getContent().get(i).getTransactionDate());
				System.out.println("NAV as On : " + transac.getData().getTransactions().getContent().get(i).getNav());
				System.out.println("Total  Units :" + transac.getData().getTransactions().getContent().get(i).getUnits());
				double r_amt = transac.getData().getTransactions().getContent().get(i).getAmount();
				double r_unit = transac.getData().getTransactions().getContent().get(i).getUnits();
				double NAV = transac.getData().getTransactions().getContent().get(i).getNav();
				System.out.println("Total Amount :" + r_unit * NAV);
			}
		}
	}


}




