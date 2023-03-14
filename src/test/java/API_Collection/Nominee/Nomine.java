package API_Collection.Nominee;
import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import MFPojo.Nominee.AddNominee;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Nomine {
    RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri(BaseURL.dev)
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
        res.when().get("/core/investor/nominees/declaration")
                .then().log().all().spec(respec);
    }
    @Test
    public void Single_noiminee() {
        AddNominee.Root payload=new AddNominee.Root();
        payload.setHoldingProfileId("181554");
        payload.setOptedOut(false);

        String onenominee="";
        RequestSpecification res=given().spec(req)
                .body("{\r\n"
                        + "  \"holdingProfileId\": \"181554\",\r\n"
                        + "  \"optedOut\": false,\r\n"
                        + "  \"nominees\": [\r\n"
                        + "    {\r\n"
                        + "      \"firstName\": \"Primary\",\r\n"
                        //		+ "      \"middleName\": \"\",\r\n"
                        + "      \"lastName\": \"Investor\",\r\n"
                        + "      \"dateOfBirth\": \"10/10/2000\",\r\n"
                        + "      \"relationship\": \"Brother\",\r\n"
                        + "      \"gender\": \"male\",\r\n"
                        //	+ "      \"email\": \"\",\r\n"
                        //	+ "      \"mobile\": \"9234567890\",\r\n"
                        + "      \"salutation\": \"Mr\",\r\n"
                        + "      \"investorId\": \"fdsajgfj\",\r\n"
                        + "      \"percentage\": 100\r\n"
                        + "    }\r\n"
                        + "  ]\r\n"
                        + "}\r\n"
                        + "");
        res.when().post("/core/investor/nominees")
                .then().log().all().statusCode(200);
    }

}
