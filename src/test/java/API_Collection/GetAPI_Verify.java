package API_Collection;

import MFPojo.HoldingProfile;
import MFPojo.UserProfile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAPI_Verify {
    //BaseURI
    String dev = "https://dev-api.fundsindia.com";          String test = "https://testapi.fundsindia.com";
    String scrum1 = "https://scrum1-api.fundsindia.com";    String scrum2 = "https://scrum2-api.fundsindia.com";
    String scrum3 = "https://scrum3-api.fundsindia.com";    String scrum4 = "https://scrum4-api.fundsindia.com";
    String staging = "https://staging-api.fundsindia.com";   String live = "https://api.fundsindia.com";
    String hotfix = "https://hotfix-api.fundsindia.com";

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(dev)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
String Holdingid;       String Expected_HoldID = "179605";
int Invetor_id;         int ExpextedInvestorid=282306;

/*// Live Data
    String Holdingid;       String Expected_HoldID = "1403821";
    int Invetor_id;         int ExpextedInvestorid=1401246;*/
@Test(priority = 0)
    public void Feature()	{

        RequestSpecification res=given().spec(req);
        res.when().get("/core/features")
                .then().log().all().spec(respec);
    }
@Test(priority = 0)
    public void User_Profile() {
    RequestSpecification res = given().spec(req)
            .queryParam("holdingProfileId", Holdingid);
    UserProfile.Root user_response= res.when().get("/core/user-profile")
            .then().spec(respec).extract().response().as(UserProfile.Root.class);
    int size= user_response.getData().getInvestors().size();
    for(int i=0;i<size;i++)
    {
        int InvestorList=user_response.getData().getInvestors().get(i).getInvestorId();
        if (InvestorList==ExpextedInvestorid)
        {
            Invetor_id=user_response.getData().getInvestors().get(i).getInvestorId();
            System.out.println(Invetor_id);

        }
    }
    }
@Test(priority = 0)
    public void Holding_Profile() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);

        int size = hold_response.getData().size();
    for(int i=0;i<size;i++)
        {
            String holdinglist= String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
            if (holdinglist.equalsIgnoreCase(Expected_HoldID))
            {
                Holdingid=hold_response.getData().get(i).getHoldingProfileId();
                System.out.println(Holdingid);
            }
        }
    }
@Test(priority = 1)
    public void Dashboard()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/dashboard")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Dashboard_Portfolio()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/dashboard/portfolio")
                .then().log().all().spec(respec);

    }
@Test(priority = 1)
    public void Systematic_plan()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Invested_Schemes()	{
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","0");
        res.when().get("/core/investor/invested-schemes")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Recent_Transactions()	{

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/recent-transactions")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Investor_Mandates()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("investorId", Invetor_id);
        res.when().get("/core/investor/mandates")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Current_SIPS()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/current-sips")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void STP() {

        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid)
                .queryParam("page","1")
                .queryParam("size","50");
        res.when().get("/core/investor/current-stps")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Power_STPs()    {
        RequestSpecification res=given().spec(req);
         res.when().get("/core/investor/power-stps")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Triggers() {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/current-triggers")
                .then().log().all().spec(respec);

    }
@Test(priority = 1)
    public void SWP() {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid)
                .queryParam("page","1")
                .queryParam("size","50");

        res.when().get("/core/investor/current-swps")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Folio_Banklist()	{
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/folio-bank-list")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
public void Contact_Info()
    {
        RequestSpecification res=given().spec(req);
        res.when().get("/core/investor/contacts")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Transactions_Authorization()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/transactions/authorization")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Pending_Payments()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
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
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/goals")
                .then().log().all().spec(respec);
    }

    @Test
    public void product_search_mf_form()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
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
@Test(priority = 1)
    public void Portfolio_View()
    {
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId", Holdingid);
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


