package API_Collection.GetAPI;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import API_Collection.Login.Login;
import MFPojo.HoldingProfile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ResponseTime_Verify {

String token="";

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.staging)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Live_Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
//String Holdingid;       String Expected_HoldID = "179605";      String InvestorId;

//Live Data
   String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
 //  String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan

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
             res.when().get("/core/user-profile")
            .then().log().all().spec(respec);

    }
@Test(priority = 0)
    public void Holding_Profile() {
    RequestSpecification res = given().spec(req);
    HoldingProfile.Root hold_response= res.when().get("/core/investor/holding-profiles")
            .then().log().all().spec(respec).extract().response().as(HoldingProfile.Root.class);
    int size = hold_response.getData().size();  // Data Size
    for(int i=0;i<size;i++)
    {
        int count=hold_response.getData().get(i).getInvestors().size();                 // Investor count
        String holdinglist= String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
        for (int j=0;j<count;j++)
        {
            if (holdinglist.equalsIgnoreCase(Expected_HoldID))
            {
                InvestorId= hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                Holdingid=hold_response.getData().get(i).getHoldingProfileId();
                System.out.println(Holdingid);
                System.out.println(InvestorId);

            }
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
                .queryParam("investorId", InvestorId);
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
    public void Announcements()
    {
        RequestSpecification res=given().spec(req);
        res.when().get("/core/user/sign-up/announcements")
                .then().log().all().spec(respec);
    }
@Test(priority = 1)
    public void Investor_Nominee_Declaration()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/nominees/declaration")
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
    public void ProductSearch_MF_Form()
    {
        RequestSpecification res=given().spec(req)
                .body(Payload.product_Search());
        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }
    @Test
    public void Super_Savings()
    {
        RequestSpecification res=given().spec(req)
                .body(Payload.Super_Savings());
        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }
    @Test
    public void NFO_Search()
    {
        RequestSpecification res=given().spec(req)
                .body(Payload.NFO());
        res.when().post("/core/product-search/mf")
                .then().log().all().statusCode(200);
    }
    @Test(priority = 1)
    public void Scheme_info() //Scheme_Info
    {
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId", "0");
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

    //Asset Allocation
    @Test
    public void Dashboard_portfolio_Allocation_Asset()	{
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId","0");
        payload.put("portfolioId","0");
        payload.put("detailType","asset");
        payload.put("duration","three_month");

        RequestSpecification res=given().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_category()	{
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId","0");
        payload.put("portfolioId","0");
        payload.put("detailType","category");
        payload.put("duration","three_month");
        RequestSpecification res=given().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_fi_style()	{
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId","0");
        payload.put("portfolioId","0");
        payload.put("detailType","fi_style");
        payload.put("duration","three_month");
        RequestSpecification res=given().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_credit()	{
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId","0");
        payload.put("portfolioId","0");
        payload.put("detailType","credit_quality");
        payload.put("duration","three_month");
        RequestSpecification res=given().spec(req)
                .body(payload);

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Select_Funds()
    {
        RequestSpecification res=given().spec(req)
                .body(Payload.Select_Funds());
        res.when().post("/core/product-search/mf/select-funds")
                .then().log().all().statusCode(200);
    }


}


