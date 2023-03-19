package API_Collection.Nominee;
import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import MFPojo.Nominee.NewDeclaration;
import MFPojo.Nominee.PostResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class NomineeTest {
    RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version","2.0")
            .addHeader("channel-id","10")
            .addHeader("x-fi-access-token", Login.Nominee())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
    @Test
    public void Nominee_Declaration()	{
        RequestSpecification res=given().spec(req)
               .queryParam("holdingProfileId","181554");
       NewDeclaration.Root response= res.when().get("/core/investor/nominees/declaration")
                .then().log().all().spec(respec).extract().response().as(NewDeclaration.Root.class);
System.out.println(response.getData().getStatus());
    }
    @Test
    public void Opt_Out() {
        Map<String,Object> optout=new HashMap<String,Object>();
        optout.put("holdingProfileId","181557");
         optout.put("optedOut",true);

        RequestSpecification res=given().spec(req)
                .body(optout);
       PostResponse.Root response= res.when().post("/core/investor/nominees")
       .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
       for(int i=0;i<response.getData().getInvestors().size();i++)
       {
           System.out.println("OTP RefID : "+response.getData().getInvestors().get(i).getOtpReferenceId());
       }
    }

    @Test
    public void Single() {

        RequestSpecification res=given().spec(req)
                .body(payload.single());
        PostResponse.Root response= res.when().post("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
        for(int i=0;i<response.getData().getInvestors().size();i++)
        {
            System.out.println("OTP RefID : "+response.getData().getInvestors().get(i).getOtpReferenceId());
        }
    }



//Existing Nominee
@Test
public void Existing()	{
        //Investor ID for Equity and Holding id for MF
    RequestSpecification res=given().spec(req)
            .queryParam("holdingProfileId","181554")
            .queryParam("product","MF");;
    res.when().get("/core/investor/nominees/existing-declaration")
            .then().log().all().spec(respec);
}
    @Test
    public void Put_Nominee()	{
        RequestSpecification res=given().spec(req)
                .body(payload.single());
        res.when().put("/core/investor/nominees")
                .then().log().all().spec(respec);
    }
}
