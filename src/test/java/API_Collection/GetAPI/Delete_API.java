package API_Collection.GetAPI;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
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

public class Delete_API {

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.staging)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Live_Login.saravanan())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
//String Holdingid;       String Expected_HoldID = "179605";      String InvestorId;

//Live Data
 //   String Holdingid;       String Expected_HoldID = "1511045";  String InvestorId;    //sathish(1403821) test-> 1511045
   String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan

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
    public void Delete_Pending_Payment() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "935406")
                .queryParam("paymentId","24889529");            //24908794
        res.when().delete("/core/investor/pending-payments")
                .then().log().all().spec(respec);
    }
}


