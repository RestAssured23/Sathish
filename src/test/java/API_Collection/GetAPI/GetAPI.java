package API_Collection.GetAPI;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAPI {

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.dev)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.twofa())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String holdingid = "179605";
String Holdingid;


    @Test
    public void User_Profile() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", holdingid);
        res.when().get("/core/user-profile")
                .then().log().all().spec(respec);
    }

    @Test
    public void Holding_Profile() {

        RequestSpecification res = given().spec(req);
       res.when().get("/core/investor/holding-profiles")
                .then().log().all().spec(respec);
    }
    @Test
    public void Systematic_plan()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","179127");
        res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }

    @Test
    public void Feature()	{

        RequestSpecification res=given().spec(req);
        res.when().get("/core/features")
                .then().log().all().spec(respec);
    }

    @Test
    public void Dashboard()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/investor/dashboard")
                .then().log().all().spec(respec);

    }

    @Test
    public void Dashboard_Portfolio()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/investor/dashboard/portfolio")
                .then().log().all().spec(respec);

    }

    @Test
    public void Invested_Schemes()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182347");
        res.when().get("/core/investor/invested-schemes")
                .then().log().all().spec(respec);
    }

    @Test
    public void Recent_Transactions()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182347");
        res.when().get("/core/investor/recent-transactions")
                .then().log().all().spec(respec);
    }

    @Test
    public void Investor_Mandates()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("investorId", "283985");
        res.when().get("/core/investor/mandates")
                .then().log().all().spec(respec);
    }


    @Test
    public void Current_SIPS()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","179127");
        res.when().get("/core/investor/current-sips")
                .then().log().all().spec(respec);
    }


    @Test
    public void STP() {

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid)
                .queryParam("page","1")
                .queryParam("size","50");
        res.when().get("/core/investor/current-stps")
                .then().log().all().spec(respec);
    }
    @Test
    public void Power_STPs()
    {
        RequestSpecification res=given().spec(req);
        //	.queryParam("holdingProfileId",holdingid)

        res.when().get("/core/investor/power-stps")
                .then().log().all().spec(respec);
    }


    @Test
    public void Triggers() {

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/investor/current-triggers")
                .then().log().all().spec(respec);

    }

    @Test
    public void SWP() {

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid)
                .queryParam("page","1")
                .queryParam("size","50");

        res.when().get("/core/investor/current-swps")
                .then().log().all().spec(respec);
    }

    @Test
    public void Folio_Banklist()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/investor/folio-bank-list")
                .then().log().all().spec(respec);
    }

    @Test
    public void Contact_Info()
    {
        RequestSpecification res=given().spec(req);
        res.when().get("/core/investor/contacts")
                .then().log().all().spec(respec);
    }

    @Test
    public void Pending_Authorization()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182348");
        res.when().get("/core/investor/transactions/authorization")
                .then().log().all().spec(respec);
    }

    @Test
    public void Pending_Payments()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182347");
        res.when().get("/core/investor/pending-payments")
                .then().log().all().spec(respec);
    }


    @Test
    public void Investor_Goals()
    {
        RequestSpecification res=given().spec(req);
        res.when().get("/core/goals")
                .then().log().all().spec(respec);
    }

    @Test
    public void Investor_Goal()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/investor/goals")
                .then().log().all().spec(respec);
    }

    @Test
    public void product_search_mf_form()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",holdingid);
        res.when().get("/core/product-search/mf/form")
                .then().log().all().spec(respec);
    }


    @Test
    public void ProductSearch_MF_Form()
    {
        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "	\"page\": 1,\r\n"
                        + "	\"size\": 20,\r\n"
                        + "	\"orderBy\": \"rating\",\r\n"
                        + "	\"orderType\": \"DESC\",\r\n"
                        + "	\"amcs\": [],\r\n"
                        + "	\"categories\": [],\r\n"
                        + "	\"subCategories\": [],\r\n"
                        + "	\"risk\": [],\r\n"
                        + "	\"ratings\": [],\r\n"
                        + "	\"sip\": true,\r\n"
                        + "	\"schemeType\": [],\r\n"
                        + "	\"searchCode\": []\r\n"
                        + "}");
        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }
    @Test
    public void Super_Savings()
    {
        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "	\"page\": 1,\r\n"
                        + "	\"size\": 20,\r\n"
                        + "	\"orderBy\": \"rating\",\r\n"
                        + "	\"orderType\": \"DESC\",\r\n"
                        + "	\"amcs\": [],\r\n"
                        + "	\"categories\": [],\r\n"
                        + "	\"subCategories\": [],\r\n"
                        + "	\"risk\": [],\r\n"
                        + "	\"ratings\": [],\r\n"
                        + "	\"schemeType\": [],\r\n"
                        + "	\"searchCode\": [{\r\n"
                        + "		\"name\": \"\",\r\n"
                        + "		\"value\": \"super_savings\",\r\n"
                        + "		\"sort\": true\r\n"
                        + "	}]\r\n"
                        + "}");
        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }


    @Test
    public void NFO_Search()
    {
        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "	\"page\": 1,\r\n"
                        + "	\"size\": 20,\r\n"
                        + "	\"orderBy\": \"rating\",\r\n"
                        + "	\"orderType\": \"DESC\",\r\n"
                        + "	\"amcs\": [],\r\n"
                        + "	\"categories\": [],\r\n"
                        + "	\"subCategories\": [],\r\n"
                        + "	\"risk\": [],\r\n"
                        + "	\"ratings\": [],\r\n"
                        + "	\"schemeType\": [],\r\n"
                        + "	\"searchCode\": [{\r\n"
                        + "		\"name\": \"\",\r\n"
                        + "		\"value\": \"nfo\",\r\n"
                        + "		\"sort\": true\r\n"
                        + "	}]\r\n"
                        + "}");

        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }
    @Test
    public void Portfolio_View()
    {
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId", "179605");
        payload.put("showZeroHoldings", true);

        Map<String,Object> sort=new HashMap<String,Object>();
        sort.put("by", "investment_amount");

        /*investment_amount, current_amount, scheme_name, portfolio_name,
         * today_change, annualized_return, return_1yr, return_3yr,
         * return_5yr, since_inception_return
         */

        sort.put("type", "desc");				//desc , asc
        payload.put("sort", sort);
        payload.put("type", "portfolio");		//	portfolio, scheme_info, asset, tax

        RequestSpecification res=given().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio")
                .then().log().all().spec(respec);
    }

    @Test
    public void Dashboard_portfolio_Allocation_Asset()	{
/*
 * Sathish Test Holding ID
	   179127 - sathish , 	179341 - saran , 179400 - pradeep , 179529 - Testinvestor ,
		179605(testing name)  --181288 post login  180805 (283504)- SIP TEST
 */
        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "  \"holdingProfileId\": \"\",\r\n"
                        + "  \"portfolioId\": \"\",\r\n"
                        + "  \"detailType\": \"asset\"\r\n"
                        //		+ "  \"duration\": \"one_month\"\r\n"
                        + "}");

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_category()	{

        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "  \"holdingProfileId\": \"\",\r\n"
                        + "  \"portfolioId\": \"\",\r\n"
                        + "  \"detailType\": \"category\"\r\n"
                        //		+ "  \"duration\": \"one_month\"\r\n"
                        + "}");

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_fi_style()	{

        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "  \"holdingProfileId\": \"\",\r\n"
                        + "  \"portfolioId\": \"\",\r\n"
                        + "  \"detailType\": \"fi_style\"\r\n"
                        //		+ "  \"duration\": \"one_month\"\r\n"
                        + "}");

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_credit()	{

        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "  \"holdingProfileId\": \"\",\r\n"
                        + "  \"portfolioId\": \"\",\r\n"
                        + "  \"detailType\": \"credit_quality\"\r\n"
                        //		+ "  \"duration\": \"one_month\"\r\n"
                        + "}");

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }


}


