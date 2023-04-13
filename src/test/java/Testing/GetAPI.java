package Testing;

import API_Collection.GetAPI.Payload;
import API_Collection.Login.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAPI {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Nominee())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
String Holdingid;       String Expected_HoldID = "183318";      String InvestorId;

   /* //Live Data
    String Holdingid;    String Expected_HoldID = "1403821";    String InvestorId;     //sathish
 //    String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan*/

    @BeforeTest
    public void setOutputDirectory(ITestContext context) {
        TestRunner runner = (TestRunner) context;
        String path=System.getProperty("C:/Users/FI user/IntelJ project/Sathish_APIAutomation");
        runner.setOutputDirectory(path+"/output-testng");
    }
    @Test(description = "FeatureAPI", priority = 0)
    public void Feature() {
        RequestSpecification res = given().log().all().spec(req);
        res.when().get("/core/features")
                .then().log().all().spec(respec);
    }

    @Test(priority = 0)
    public void User_Profile() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        ValidatableResponse v = res.when().get("/core/user-profile")
                .then().log().body().spec(respec);
        v.time(Matchers.lessThan(1000l));
    }
    @Test(priority = 0)
    public void Holding_Profile() {
        RequestSpecification res = given().log().method().log().uri().spec(req);
        Testing.HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().log().body().spec(respec).extract().response().as(HoldingProfile.Root.class);

        int size = hold_response.getData().size();  // Data Size
        for (int i = 0; i < size; i++) {
            int count = hold_response.getData().get(i).getInvestors().size();                 // Investor count
            String holdinglist = String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
            for (int j = 0; j < count; j++) {
                if (holdinglist.equalsIgnoreCase(Expected_HoldID)) {
                    InvestorId = hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                    Holdingid = hold_response.getData().get(i).getHoldingProfileId();
                    System.out.println("Holding ID :"+Holdingid);
                    System.out.println("Investor ID : "+InvestorId);
                }
            }
        }
    }
    @Test(priority = 1)
    public void Dashboard() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/dashboard")
                .then().log().body().spec(respec);
    }

    @Test(priority = 1)
    public void Dashboard_Portfolio() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/dashboard/portfolio")
                .then().log().body().spec(respec);

    }
    @Test(priority = 1)
    public void Systematic_plan() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/systematic-plan/sips")
                .then().log().body().spec(respec);
    }

    @Test(priority = 1)
    public void Invested_Schemes() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", "0");
        res.when().get("/core/investor/invested-schemes")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Recent_Transactions() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/recent-transactions")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Investor_Mandates() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("investorId", InvestorId);
        res.when().get("/core/investor/mandates")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Current_SIPS() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/current-sips")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void STP() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid)
                .queryParam("page", "1")
                .queryParam("size", "50");
        res.when().get("/core/investor/current-stps")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Power_STPs() {
        RequestSpecification res = given().log().method().log().uri().spec(req);
        res.when().get("/core/investor/power-stps")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Triggers() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/current-triggers")
                .then().log().body().spec(respec);
    }

    @Test(priority = 1)
    public void SWP() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid)
                .queryParam("page", "1")
                .queryParam("size", "50");

        res.when().get("/core/investor/current-swps")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Folio_Banklist() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/folio-bank-list")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Contact_Info() {
        RequestSpecification res = given().log().method().log().uri().spec(req);
        res.when().get("/core/investor/contacts")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Transactions_Authorization() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/transactions/authorization")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Pending_Payments() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/pending-payments")
                .then().log().body().spec(respec);
    }
    @Test
    public void Investor_Goals() {
        RequestSpecification res = given().log().method().log().uri().spec(req);
        res.when().get("/core/goals")
                .then().log().body().spec(respec);
    }
    @Test
    public void Investor_Goal() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/goals")
                .then().log().body().spec(respec);
    }
    @Test
    public void product_search_mf_form() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/product-search/mf/form")
                .then().log().body().spec(respec);
    }
    @Test
    public void Announcements() {
        RequestSpecification res = given().log().method().log().uri().spec(req);
        res.when().get("/core/user/sign-up/announcements")
                .then().log().body().spec(respec);
    }
    @Test
    public void lookup() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("types", "Location");  //State,Location,country,fd_nominee_salutation
        res.when().get("/core/lookups")
                .then().log().body().spec(respec);
    }
    @Test
    public void ProductSearch_MF_Form() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(Payload.product_Search());
        res.when().post("/core/product-search/mf")
                .then().log().body().statusCode(200);
    }
    @Test
    public void Super_Savings() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(Payload.Super_Savings());
        res.when().post("/core/product-search/mf")
                .then().log().body().statusCode(200);
    }
    @Test
    public void NFO_Search() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(Payload.NFO());
        res.when().post("/core/product-search/mf")
                .then().log().body().statusCode(200);
    }
    @Test(priority = 1)
    public void Scheme_info() //Scheme_Info
    {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "0");
        payload.put("showZeroHoldings", true);
        Map<String, Object> sort = new HashMap<String, Object>();
        sort.put("by", "investment_amount");
        /*investment_amount, current_amount, scheme_name, portfolio_name,
         * today_change, annualized_return, return_1yr, return_3yr,
         * return_5yr, since_inception_return
         */
        sort.put("type", "desc");                //desc , asc
        payload.put("sort", sort);
        payload.put("type", "portfolio");        //	portfolio, scheme_info, asset, tax

        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio")
                .then().log().body().spec(respec);
    }
    //Asset Allocation
    @Test
    public void Dashboard_portfolio_Allocation_Asset_All() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "0");
        payload.put("portfolioId", "0");
        payload.put("detailType", "asset");
        payload.put("duration", "three_month");

        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().body().spec(respec).extract().response().asString();
    }

    /*@Test
    public void Dashboard_portfolio_Allocation_Asset_single() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);
        int size = hold_response.getData().size();  // Data Size
        for (int i = 0; i < size; i++) {
            int count = hold_response.getData().get(i).getInvestors().size();                 // Investor count
            String holdinglist = String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
            for (int j = 0; j < count; j++) {

                InvestorId = hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                Holdingid = hold_response.getData().get(i).getHoldingProfileId();
                System.out.println(Holdingid);
//Asset
                Map<String, Object> payload = new HashMap<String, Object>();
                payload.put("holdingProfileId", Holdingid);
                payload.put("portfolioId", "0");
                payload.put("detailType", "asset");
                payload.put("duration", "three_month");

                RequestSpecification res1 = given().spec(req)
                        .body(payload);
                res1.when().post("/core/investor/dashboard/portfolio/allocations")
                        .then().log().all().spec(respec);
//category
                Map<String, Object> payload1 = new HashMap<String, Object>();
                payload1.put("holdingProfileId", Holdingid);
                payload1.put("portfolioId", "0");
                payload1.put("detailType", "category");
                payload1.put("duration", "three_month");
                RequestSpecification res2 = given().spec(req)
                        .body(payload);
                res2.when().post("/core/investor/dashboard/portfolio/allocations")
                        .then().log().all().spec(respec);

                //FI_Style
                Map<String, Object> payload2 = new HashMap<String, Object>();
                payload2.put("holdingProfileId", Holdingid);
                payload2.put("portfolioId", "0");
                payload2.put("detailType", "fi_style");
                payload2.put("duration", "three_month");
                RequestSpecification res3 = given().spec(req)
                        .body(payload);
                res3.when().post("/core/investor/dashboard/portfolio/allocations")
                        .then().log().all().spec(respec);
//credit_quality
                Map<String, Object> payload4 = new HashMap<String, Object>();
                payload4.put("holdingProfileId", Holdingid);
                payload4.put("portfolioId", "0");
                payload4.put("detailType", "credit_quality");
                payload4.put("duration", "three_month");
                RequestSpecification res4 = given().spec(req)
                        .body(payload);
                res4.when().post("/core/investor/dashboard/portfolio/allocations")
                        .then().log().all().spec(respec);

            }
        }
    }
*/
    @Test
    public void Dashboard_portfolio_Allocation_category() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "0");
        payload.put("portfolioId", "0");
        payload.put("detailType", "category");
        payload.put("duration", "three_month");
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().body().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_fi_style() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "0");
        payload.put("portfolioId", "0");
        payload.put("detailType", "fi_style");
        payload.put("duration", "three_month");
        RequestSpecification res = given().spec(req)
                .body(payload);
        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().all().spec(respec).extract().response().asString();
    }
    @Test
    public void Dashboard_portfolio_Allocation_credit() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "0");
        payload.put("portfolioId", "0");
        payload.put("detailType", "credit_quality");
        payload.put("duration", "three_month");
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(payload);

        res.when().post("/core/investor/dashboard/portfolio/allocations")
                .then().log().body().spec(respec).extract().response().asString();
    }
    @Test
    public void Select_Funds() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .body(Payload.Select_Funds());
        res.when().post("/core/product-search/mf/select-funds")
                .then().log().body().spec(respec);
    }
 @Test(priority = 1)
    public void New_Nominee_Declaration() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        res.when().get("/core/investor/nominees/declaration")
                .then().log().body().spec(respec);
    }
@Test(priority = 1)
    public void Get_Existing_Nominee_MF()	{
        //Investor ID for Equity and Holding id for MF
        RequestSpecification res=given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId",Holdingid)
                .queryParam("product","MF");;
        res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().body().spec(respec);
    }
    @Test(priority = 1)
    public void Get_Equity_GetNominee()	{           //Get API
        //Investor ID for Equity and Holding id for MF
        RequestSpecification res=given().log().method().log().uri().spec(req)
                .queryParam("investorId",InvestorId)      // 934332(saravanan)  , 177973(local)
                .queryParam("product","EQUITY");
        res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().body().spec(respec);
    }

}







