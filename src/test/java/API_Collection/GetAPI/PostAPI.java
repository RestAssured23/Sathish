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

public class PostAPI {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
String Holdingid;       String Expected_HoldID = "179605";      String InvestorId;

    //Live Data
  //  String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
    //  String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan


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
