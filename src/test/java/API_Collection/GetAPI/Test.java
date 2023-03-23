package API_Collection.GetAPI;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import MFPojo.HoldingProfile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Test {

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

@org.testng.annotations.Test(priority = 0)
    public void Feature()	{

        RequestSpecification res=given().spec(req);
        res.when().get("/core/features")
                .then().log().all().spec(respec);
    }
@org.testng.annotations.Test(priority = 0)
    public void User_Profile() {
    RequestSpecification res = given().spec(req)
            .queryParam("holdingProfileId", Holdingid);
             res.when().get("/core/user-profile")
            .then().log().all().spec(respec);

    }
@org.testng.annotations.Test(priority = 0)
    public void Holding_Profile() {
    RequestSpecification res = given().spec(req);
    HoldingProfile.Root hold_response= res.when().get("/core/investor/holding-profiles")
            .then().spec(respec).extract().response().as(HoldingProfile.Root.class);
    int size = hold_response.getData().size();  // Data Size
    for(int i=0;i<size;i++)
    {
        int count=hold_response.getData().get(i).getInvestors().size();                 // Investor count
        String holdinglist= String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
        for (int j=0;j<count;j++)
        {

                InvestorId= hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                Holdingid=hold_response.getData().get(i).getHoldingProfileId();
                System.out.println(Holdingid);

          /*  RequestSpecification res1=given().spec(req)
                    .queryParam("holdingProfileId",Holdingid);
            res1.when().get("/core/investor/transactions/authorization")
                    .then().log().all().spec(respec);

            RequestSpecification res2=given().spec(req)
                    .queryParam("holdingProfileId","1511045");
            res2.when().get("/core/investor/pending-payments")
                    .then().log().all().spec(respec);
*/
        }
    }
    }

    // Holding ID : 1505963 , 1511045  , saravanan--935406

    @org.testng.annotations.Test(priority = 1)
    public void Transactions_Authorization()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","1511045");
        res.when().get("/core/investor/transactions/authorization")
                .then().log().all().spec(respec);
    }
    @org.testng.annotations.Test(priority = 1)
    public void Pending_Payments()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","1505963");
        res.when().get("/core/investor/pending-payments")
                .then().log().all().spec(respec);
    }

}


