package API_Collection;

import MFPojo.HoldingProfile;
import MFPojo.UserProfile;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class test {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri("https://dev-api.fundsindia.com")
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
 String Holdingid;

/*
    @Test(priority = 1)
    public void Holding_Profile() {
        String expectedholdingid = "179605";
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);

        int size = hold_response.getData().size();

        //  Holdingid = hold_response.getData().get(3).getHoldingProfileId();
        //  System.out.println(Holdingid);

        for(int i=0;i<size;i++)
        {
            int count=hold_response.getData().get(i).getBanks().size();                 // bank size
            String holdinglist= String.valueOf(hold_response.getData().get(i).getHoldingProfileId());

        if (holdinglist.equalsIgnoreCase(expectedholdingid))
            {
                Holdingid=hold_response.getData().get(i).getHoldingProfileId();
                System.out.println(Holdingid);

            }
        }
    }
    @Test(priority = 2)
    public void Systematic_plan()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId",Holdingid);
        res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }
*/

    @Test(priority = 1)
    public void User_Profile() {
        int ExpextedInvestorid=282306;
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", Holdingid);
       UserProfile.Root user_response= res.when().get("/core/user-profile")
                .then().spec(respec).extract().response().as(UserProfile.Root.class);
      int size= user_response.getData().getInvestors().size();
      for(int i=0;i<size;i++)
      {
          int InvestorList=user_response.getData().getInvestors().get(i).getInvestorId();
         // System.out.println(InvestorList);
          if (InvestorList==ExpextedInvestorid)
          {
              int invet_id=user_response.getData().getInvestors().get(i).getInvestorId();
              System.out.println(invet_id);

          }
      }
    }



}
