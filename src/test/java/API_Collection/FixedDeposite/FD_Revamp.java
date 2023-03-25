package API_Collection.FixedDeposite;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import API_Collection.Login.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class FD_Revamp {

String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3Njc5Iiwic2NvcGVzIjoicmVhZCx3cml0ZSIsIm5hbWUiOiJTeWVkIEdob3VzZSIsImVtYWlsIjoiU3llZC5HaG91c2UwMDZAR21haWwuQ29tIiwibW9iaWxlIjoiOTg0OTcyNjAwNiIsIm1hbmFnZW1lbnQtdXNlci1pZCI6MTg5MDc0MywibWFuYWdlbWVudC11c2VyLXJvbGVzIjoiYWRtaW4iLCJpc3MiOiJmdW5kc2luZGlhLmNvbSIsImp0aSI6ImE4MWExY2FlLTNlMGEtNDVkMy05MTVmLTkyNmRkYWZkMWZlNCIsImlhdCI6MTY3OTU3MTAyMCwiZXhwIjoxNjc5NTc0NjgwfQ.wBtELBoMwV6i4hWcFQGfe3BHZbpChc8MQrv73u2ejlPKKzg6GLohwDv2cIR0StY4bJwsPosZNWc-JhIUAIAORA";

	RequestSpecification req = new RequestSpecBuilder()
			.setBaseUri(BaseURL.live)
			.addHeader("x-api-version", "2.0")
			.addHeader("channel-id", "10")
			.addHeader("x-fi-access-token", token)
			.setContentType(ContentType.JSON).build();

	ResponseSpecification respec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();
			
/* FD_Revamp 	(fdrevamp@gmail.com)
			invt_ID		Hold_ID		Invest_Name
			285020		182347		First Investor
			285021		182348		Female Investor
			285022		182349		Male Senior Citizen
			285023		182350		Female Senior Citizen
			285024		182351		Minor Male
			285025		182352		Minor Female
			285026		182353		Corporate Account
			285439		182766		NRI(Activated)
						182767		Joint Account
*/

	@Test
	public void Holding_Profile_FD() {
		RequestSpecification res = given().spec(req)
				.queryParam("product", "FD");
		res.when().get("/core/investor/holding-profiles")
				.then().log().all().spec(respec);

	}

	@Test
	public void personal_information() {
		RequestSpecification res = given().spec(req)
				.queryParam("fdId", "FD41634")
				.body("{	}");
		res.when().post("/core/internal/fixed-deposit/shri-ram/personal-information")
				.then().log().all().spec(respec);
	}

	@Test
	public void Feature() {

		RequestSpecification res = given().spec(req);
		res.when().get("/core/features")
				.then().log().all().spec(respec);
	}

	@Test
	public void User_Profile() {
		RequestSpecification res = given().spec(req)
				.queryParam("holdingProfileId", "0");
		res.when().get("/core/user-profile")
				.then().log().all().spec(respec);
	}

	@Test
	public void Nominee_Declaration() {
		RequestSpecification res = given().spec(req)
				.queryParam("holdingProfileId", "179127");        //live holdID 1403821
		res.when().get("/investor/nominees/declaration")
				.then().log().all().spec(respec);
	}

	@Test
	public void select_funds() {
		String empty = "{}";

		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("amount", 15000);
	
	/*		ArrayList<String> comp=new ArrayList<String>();
			comp.add("bajaj");
		payload.put("companies", comp); */

		ArrayList<String> mode = new ArrayList<String>();
		mode.add("online");
		payload.put("modes", mode);

		payload.put("seniorCitizen", false);
		payload.put("women", false);
		payload.put("tenure", 28);
		ArrayList<String> payout = new ArrayList<String>();
		payout.add("cumulative");
// 
		/*[ non_cumulative_quarterly, non_cumulative_half_yearly, 
		non_cumulative_yearly, non_cumulative_monthly, cumulative */

		payload.put("payout", payout);

		RequestSpecification res = given().spec(req)
				.body(payload);
		res.when().post("/core/product-search/fixed-deposit/select-funds")
				.then().log().all().spec(respec);
	}


	@Test
	public void Transaction() {
		HashMap<String, Object> trans = new HashMap<String, Object>();
		trans.put("holdingProfileId", "0");

		ArrayList<String> type = new ArrayList<String>();
		//type.add("holdings");            // [ pendings, holdings ]
		type.add("holdings");
		trans.put("type", type);

		RequestSpecification res = given().spec(req)
				.body(trans);
		res.when().post("/core/investor/fixed-deposit/transactions")
				.then().log().all().spec(respec);
	}

	@Test
	public void Search_Fund() {

		String empty = "{ }";
		HashMap<String, Object> search = new HashMap<String, Object>();
//	search.put("query", "baja");
		search.put("page", 1);
		search.put("size", 50);
		search.put("orderBy", "asc");                //asc, desc
		search.put("sortBy", "company_name");        //[ company_name, fund_name, returns, tenure, one_year, three_years, five_years ]

		RequestSpecification res = given().spec(req)
				.body(search);
		res.when().post("/core/product-search/fixed-deposit/search-funds")
				.then().log().all().spec(respec);
	}

	@Test
	public void lookup() {
		RequestSpecification res = given().spec(req)
				.queryParam("types", "State,Location,country,fd_nominee_salutation");
		res.when().get("/core/lookups")
				.then().log().all().spec(respec);
	}

	@Test
	public void Get_Internal_Investment() {
		RequestSpecification res = given().spec(req)
				.queryParam("fdId", "FD45353");
		res.when().get("/core/internal/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}

	@Test
	public void Post_Internal_investment() {
		RequestSpecification res = given().spec(req)
				.body("{\n" +
						"  \"fdId\": \"FD45353\",\n" +
						"  \"referenceNo\": \"string\",\n" +
						"  \"message\": \"string\",\n" +
						"  \"gatewayPaymentId\": \"string\"\n" +
						"}");
		res.when().post("/core/internal/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}

	@Test
	public void Delete() {

		RequestSpecification res = given().spec(req)
				.queryParam("fdId", "FD45403");
		res.when().delete("/core/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}

	@Test
	public void Get_Investor_Investment() {
		RequestSpecification res = given().spec(req)
				.queryParam("fdId", "FD45404");
		res.when().get("/core/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}

	@Test
	public void Retry() {
		RequestSpecification res = given().spec(req)
				.queryParam("fdId", "FD43143");
		res.when().get("/core/investor/fixed-deposit/investment/retry")
				.then().log().all().spec(respec);
	}


	@Test
	public void Company() {
		RequestSpecification res = given().spec(req)
				.body("{companyId:[19]}");
		res.when().post("/core/product-search/fixed-deposit/companies")
				.then().log().all().spec(respec);
	}

	@Test
	public void Investor_holding() {
		RequestSpecification res = given().spec(req)
				.queryParam("holdingProfileId", "1403821");
		res.when().get("/core/investor/holding-profiles")
				.then().log().all().spec(respec);
	}

	@Test
	public void Post_Investment() {
		RequestSpecification res = given().spec(req)        //182347  --> 285020(local) , live-->
				.body("{\n" +
						"    \"holdingProfileId\": \"179605\",\n" +
						"    \"schemeId\": \"88\",\n" +
						"    \"companyId\": \"2\",\n" +
						"    \"amount\": 6000,\n" +
						"    \"tenure\": 12,\n" +
						"    \"tenureUnit\": \"monthly\",\n" +
						"    \"userBankId\": \"2\",\n" +
						"    \"payoutMode\": \"cumulative\",\n" +
						"    \"renewalType\": \"1\",\n" +
						"    \"nominee\": true,\n" +
						"    \"nomineeDetails\": [\n" +
						"        {\n" +
						"            \"firstName\": \"Te	\",\n" +
						//"            \"middleName\": \"hdk\",\n" +
						"            \"lastName\": \"s\",\n" +
						"            \"dateOfBirth\": \"30/03/1993\",\n" +
						"            \"relationship\": \"Son\",\n" +
						"            \"gender\": \"male\",\n" +
						"            \"email\": \"\",\n" +
						"            \"mobile\": \"\",\n" +
						"            \"salutation\": \"1\",\n" +
						"            \"address\": {\n" +
						"                \"addressId\": \"string\",\n" +
						"                    \"addressType\": \"communication\",\n" +
						"                    \"addressLine1\": \"abc %^^&*()hiii ssss,\",\n" +
						"                    \"addressLine2\": \"string\",\n" +
						"                    \"city\": \"chennai\",\n" +
						"                    \"cityOthers\": \"chennai\",\n" +
						"                    \"cityId\": \"\",\n" +
						"                    \"stateId\": \"\",\n" +
						"                    \"countryId\": \"\",\n" +
						"                    \"state\": \"Tamil Nau\",\n" +
						"                    \"country\": \"India\",\n" +
						"                    \"pincode\": \"626123\",\n" +
						"                    \"landmark\": \"\"\n" +
						"            },\n" +
						"            \"investorId\": \"282306\",\n" +
			/*		"            \"guardian\": {\n" +
					"                \"firstName\": \"shamel\",\n" +
					"                \"middleName\": \"hello\",\n" +
					"                \"lastName\": \"sgam\",\n" +
					"                \"dateOfBirth\": \"11/02/2000\",\n" +
					"                \"relationship\": \"son\",\n" +
					"                \"gender\": \"male\",\n" +
					"                \"email\": \"\",\n" +
					"                \"mobile\": \"\",\n" +
					"                \"salutation\": \"1\",\n" +
					"                \"address\": {\n" +
					"                    \"addressId\": \"string\",\n" +
					"                    \"addressType\": \"communication\",\n" +
					"                    \"addressLine1\": \"Po Box @#$$%^^3,Selaiyur,151Velachery Rd 600059\",\n" +
					"                    \"addressLine2\": \"string\",\n" +
					"                    \"city\": \"chennai\",\n" +
					"                    \"cityOthers\": \"chennai\",\n" +
					"                    \"cityId\": \"string\",\n" +
					"                    \"stateId\": \"string\",\n" +
					"                    \"countryId\": \"string\",\n" +
					"                    \"state\": \"Tamil Nau\",\n" +
					"                    \"country\": \"India\",\n" +
					"                    \"pincode\": \"626123\",\n" +
					"                    \"landmark\": \"\"\n" +
					"                }\n" +
					"            },\n" + */
						"            \"percentage\": 100.0\n" +
						"        }\n" +
						"    ]\n" +
						"}");

		res.when().post("/core/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}

	@Test
	public void Post_Invesmenttest() {

		Map<String, Object> payload = new LinkedHashMap<String, Object>();
		payload.put("holdingProfileId", "179127");
		payload.put("schemeId", "");
		payload.put("companyId", "2");
		payload.put("amount", 5000);
		payload.put("tenure", 12);
		payload.put("tenureUnit", "monthly");
		payload.put("userBankId", "2");
		payload.put("payoutMode", "cumulative");

		/*[ non_cumulative_quarterly, non_cumulative_half_yearly,
		 * non_cumulative_yearly, non_cumulative_monthly, cumulative ]
		 */
		payload.put("renewalType", "1");
		payload.put("nominee", true);

		List<Map<String, Object>> nomdetlist = new LinkedList<Map<String, Object>>();

		Map<String, Object> nominee = new HashMap<String, Object>();
		nominee.put("firstName", "sat");
//	nominee.put("middleName", "d");
		nominee.put("lastName", "werw");
		nominee.put("dateOfBirth", "2000-12-31");
		nominee.put("relationship", "son");
		nominee.put("gender", "male");
//	nominee.put("email", "s@gmail.com");
//	nominee.put("mobile", "9790790876");
//	nominee.put("salutation", "1");
		nominee.put("investorId", "281829");
		nominee.put("percentage", 100.0);

		Map<String, Object> add = new HashMap<String, Object>();
//	add.put("addressId", "asasda");
		add.put("addressType", "communication");
		add.put("addressLine1", "15/dshg3dbjbakb");
//	add.put("addressLine2", "dasd");
		add.put("city", "chennai");
		add.put("cityOthers", "cdsafd");
		add.put("cityId", "");
		add.put("stateId", "");
		add.put("countryId", "");
		add.put("state", "dada");
		add.put("country", "dada");
		add.put("pincode", "600045");
//	add.put("landmark", "dasds");


		Map<String, Object> guad = new LinkedHashMap<String, Object>();
		guad.put("firstName", "fdsfds");
//	guad.put("middleName", "fdsf");
		guad.put("lastName", "fsdfsd");
		guad.put("dateOfBirth", "1990-12-31");
		guad.put("relationship", "son");
		guad.put("gender", "Male");
//	guad.put("email", "dshgj@gmail.com");
//	guad.put("mobile", "9790790876");
//	guad.put("salutation", "1");

		Map<String, Object> guardadd = new HashMap<String, Object>();
//	guardadd.put("addressId", "asasda");
		guardadd.put("addressType", "communication");
		guardadd.put("addressLine1", "dsvshvnasv");
//	guardadd.put("addressLine2", "dasd");
		guardadd.put("city", "chennai");
		guardadd.put("cityOthers", "cdsafd");
		guardadd.put("cityId", "");
		guardadd.put("stateId", "");
		guardadd.put("countryId", "");
		guardadd.put("state", "dada");
		guardadd.put("country", "dada");
		guardadd.put("pincode", "646464");
//	guardadd.put("landmark", "dasds");

		guad.put("address", guardadd);

		nominee.put("address", add);
		nominee.put("guardian", guad);

		nomdetlist.add(nominee);
		payload.put("nomineeDetails", nomdetlist);

		RequestSpecification res = given().spec(req)
				.body(payload);
		res.when().post("/core/investor/fixed-deposit/investment")
				.then().log().all().spec(respec);
	}
}
	
	
	
	
