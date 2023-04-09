package API_Collection.Nominee;
import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import API_Collection.Login.Login;
import MFPojo.Nominee.NewDeclaration;
import MFPojo.Nominee.PostResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Eqt_Nomine {
    RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version","2.0")
            .addHeader("channel-id","10")
            .addHeader("x-fi-access-token", Login.equity())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

  String Investorid="169541";
    @Test
    public void Nominee_Declaration()	{
        RequestSpecification res=given().spec(req)
               .queryParam("holdingProfileId","166717");
       NewDeclaration.Root response= res.when().get("/core/investor/nominees/declaration")
                .then().log().all().spec(respec).extract().response().as(NewDeclaration.Root.class);
System.out.println(response.getData().getStatus());
    }
    @Test
    public void Add_Noiminee() {
        RequestSpecification res=given().spec(req)
                .body(eqt_payload.single());
        PostResponse.Root response= res.when().post("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
        for(int i=0;i<response.getData().getInvestors().size();i++)
        {
            System.out.println("OTP RefID : "+response.getData().getInvestors().get(i).getOtpReferenceId());
        }
    }
//Existing Nominee
@Test
public void Existing_GetNominee()	{           //Get API
        //Investor ID for Equity and Holding id for MF
    RequestSpecification res=given().spec(req)
            .queryParam("investorId","934332")      // 934332(saravanan)  , 177973(local)
            .queryParam("product","EQUITY");
    res.when().get("/core/investor/nominees/existing-declaration")
            .then().log().all().spec(respec);
    }
    @Test
    public void Put_Nominee()	{
    RequestSpecification res=given().spec(req)
                .queryParam("product","EQUITY")
                        .body(eqt_payload.test());
        res.when().put("/core/investor/nominees")
                .then().log().all().spec(respec);
    }

}
