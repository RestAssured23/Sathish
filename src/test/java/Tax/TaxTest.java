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

public class TaxTest {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2ODE1NzUiLCJzY29wZXMiOiJyZWFkLHdyaXRlIiwibmFtZSI6IlJpdGVzaCBKYWluIiwiZW1haWwiOiJyaXRlc2hqbjRAZ21haWwuY29tIiwibW9iaWxlIjoiOTk5OTM4OTg5NSIsIm1hbmFnZW1lbnQtdXNlci1pZCI6MTg5MDc0MywibWFuYWdlbWVudC11c2VyLXJvbGVzIjoiYWRtaW4iLCJpc3MiOiJmdW5kc2luZGlhLmNvbSIsImp0aSI6IjA3NjA1ZGRlLWRlZGEtNGIyYy1iY2E0LTViNzhmOTAyY2Q5NCIsImlhdCI6MTY4MDU5MTMwMSwiZXhwIjoxNjgwNTk0OTYxfQ.S_kCx8xgUj_xWsM4plkXJhxg8uHtY5752WuaaZ3mSbg_9GYX9KDkzN4nYrxAgzyU-VhC3bxbJvnd-R8uRMeZ6Q";


	RequestSpecification req =new RequestSpecBuilder()
				.setBaseUri("https://hotfix-api.fundsindia.com/core")
	    .addHeader("x-api-version","2.0")
	    .addHeader("channel-id","10")
	//.addHeader("x-fi-access-token",Live_Login.sathish())
		.addHeader("x-fi-access-token",token)
		.setContentType(ContentType.JSON).build();
		
ResponseSpecification respec =new ResponseSpecBuilder()
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


	//Unrealized
	@Test
	public void Pojo_TaxTest() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("holdingProfileId", "306935");                //183121		183128	183135(NRI)
		payload.put("financialYear", "previous");            			//previous , current
		payload.put("gainType", "unrealized");                    //[ realized, unrealized ]
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
				double Total_STCG=tax.getData().get(i).getCategoryTax().getShortTermGain();
				double Total_LTCG=tax.getData().get(i).getCategoryTax().getLongTermGain();
				System.out.println("Total Investment : " + Total_inves);
				System.out.println("Total Redeem : " + Total_redeem);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				double cost = 0.0;
				double rcost = 0.0;
				double stcg=0.0;
				double ltcg=0.0;
				double Inv_cost,redeem_cost;
				double add_stcg,add_ltcg;
				//double ;
				for (int j = 0; j < Eq_count; j++) {
					Inv_cost = tax.getData().get(i).getSchemes().get(j).getInvestmentCost();
					redeem_cost = tax.getData().get(i).getSchemes().get(j).getRedemptionCost();
					add_stcg=tax.getData().get(i).getSchemes().get(j).getShortTermGain();
					add_ltcg=tax.getData().get(i).getSchemes().get(j).getLongTermGain();
					cost = cost + Inv_cost;
					rcost = rcost + redeem_cost;
					stcg=stcg+add_stcg;
					ltcg=ltcg+add_ltcg;
				}
				System.out.println("Added Investment Cost : " + cost);
				System.out.println("Added Redeem cost : " + rcost);
				System.out.println("Added STCG Price : " + stcg);
				System.out.println("Added LTCG Price : " + ltcg);
				Assert.assertEquals(Total_inves, cost);
				Assert.assertEquals(Total_redeem, rcost);
				Assert.assertEquals(Total_STCG,stcg);
				Assert.assertEquals(Total_LTCG,ltcg);
				System.out.println("------------------------------------------------");
			} else {
				int non_Eqcount = tax.getData().get(i).getSchemes().size();
				double Total_inves = tax.getData().get(i).getCategoryTax().getInvestmentCost();
				double Total_redeem = tax.getData().get(i).getCategoryTax().getRedemptionCost();
				double Total_STCG=tax.getData().get(i).getCategoryTax().getShortTermGain();
				double Total_LTCG=tax.getData().get(i).getCategoryTax().getLongTermGain();
				System.out.println("Non_Equity Count : " + non_Eqcount);
				System.out.println("Total Investment : " + Total_inves);
				System.out.println("Total Redeem : " + Total_redeem);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				double cost = 0.0;
				double rcost = 0.0;
				double stcg=0.0;
				double ltcg=0.0;
				double Inv_cost,redeem_cost;
				double add_stcg ,add_ltcg;
				for (int j = 0; j < Eq_count; j++) {
					Inv_cost = tax.getData().get(i).getSchemes().get(j).getInvestmentCost();
					redeem_cost = tax.getData().get(i).getSchemes().get(j).getRedemptionCost();
					add_stcg=tax.getData().get(i).getSchemes().get(j).getShortTermGain();
					add_ltcg=tax.getData().get(i).getSchemes().get(j).getLongTermGain();
					cost = cost + Inv_cost;
					rcost = rcost + redeem_cost;
					stcg= stcg + add_stcg;
					stcg= stcg + add_ltcg;
				}
				System.out.println("Added Investment Cost : " + cost);
				System.out.println("Added Redeem cost : " + rcost);
				System.out.println("Total STCG : " + Total_STCG);
				System.out.println("Total LTCG : " + Total_LTCG);
				Assert.assertEquals(Total_inves, cost);
				Assert.assertEquals(Total_redeem, rcost);
				Assert.assertEquals(Total_STCG,stcg);
				Assert.assertEquals(Total_LTCG,ltcg);
			}
		}
	}


	@Test
	public void Dash_portfolio_Tax()	{
		Map<String,Object> payload=new HashMap<String,Object>();
		payload.put("holdingProfileId", Fairvalue_id);				//183121		183128	183135(NRI)
		payload.put("financialYear", "previous");			//previous , current
		payload.put("gainType", "unrealized");					//[ realized, unrealized ]
		ArrayList<String> list = new ArrayList<String>();
		list.add("equity");
		list.add("non_equity");							//[ equity, non_equity ]
		payload.put("categories",list);

		RequestSpecification res=given().spec(req)
				.body(payload);
		res.when().post("/investor/dashboard/portfolio/tax")
				.then().log().all().spec(respec).extract().response().asString();
	}

	@Test
	public void tran_hist() {
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("holdingProfileId", "183128");
		payload.put("folio", "10000615");                // NRI 3813711/17   , Sathish : 79958299852
		payload.put("schemeCode", "2271");                // NRI - 744     ,  Sathsih : 9767
		payload.put("goalId", "458344");                // NRI - 805698  , sathish : 2932872
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
					.equalsIgnoreCase("Redemption")) {

				System.out.println("------------------------------------------------");
				System.out.println("Redeem Date : " + transac.getData().getTransactions().getContent().get(i).getTransactionDate());
				System.out.println("Redeem NAV : " + transac.getData().getTransactions().getContent().get(i).getNav());
				System.out.println("Redeem Units :" + transac.getData().getTransactions().getContent().get(i).getUnits());

				System.out.println("Total Amount :" + transac.getData().getTransactions().getContent().get(i)
						.getNav() * transac.getData().getTransactions().getContent().get(i).getUnits());

			} else {
				//	ArrayList sch=transac.getData().getTransactions().getContent().get(i).getUnits();
				System.out.println("Invested Units :" + transac.getData().getTransactions().getContent().get(i).getUnits());
			}
		}
	}



}
































