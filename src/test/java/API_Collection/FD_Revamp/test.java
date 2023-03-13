package FD_Revamp;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class test {

    RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri("https://invapi.stfc.in")
            .addHeader("Cookie","ASP.NET_SessionId=tnpovfgiklrpty11inftly0b")

            .setContentType(ContentType.JSON).build();


    ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public void Schemedetail()
    {

        String schemedetails="5xZjQ6Y2WCdKHUC0YG9OZGLUvlgcAMTIW4IirZ2ku1vJQ/IHxLvSVlRRIVWKfQeg1jKIAOmnALHAV1gPemn623iy9pPZoRV2hAh4Bqb+rSTSmKX2PyWYYcGxCPtahhGA\"";



        RequestSpecification res=given().spec(req)
                .header("IncomingAuthKey","QR242aDkDp5EuqEpqFNi0cl9kAvsYh783I5d6NgFhpg=")
                .body("{\n" +
                        "    \"Request\": " +
                        "\"5xZjQ6Y2WCdKHUC0YG9OZGLUvlgcAMTIW4IirZ2ku1vJQ/IHxLvSVlRRIVWKfQeg1jKIAOmnALHAV1gPemn6277gicrVg3AzR8ejc8Avu0BKCbDExhJfvExtw9WHvOve\"\n" +


                        "}");
        res.when()
                .post("/INVCOSScheme/COSSchemeService.svc/Schemedetail")
                .then().log().all().spec(respec);
    }
    @Test
   public void test()
   {

       String test = RestAssured.baseURI="https://invapi.stfc.in";
       given().header("IncomingAuthKey","bVGgSKCoR6VIYzxrplOs3tiCLMYJbTog0GoxGZ6aEY8=")
               .body("{\r\n"
                       + "    \"Request\": \"5xZjQ6Y2WCdKHUC0YG9OZGLUvlgcAMTIW4IirZ2ku1vJQ/IHxLvSVlRRIVWKfQeg1jKIAOmnALHAV1gPemn6277gicrVg3AzR8ejc8Avu0BKCbDExhJfvExtw9WHvOve\"\r\n"
                       + "}")
               .when().post("/INVCOSScheme/COSSchemeService.svc/Schemedetail")
               .then().log().all().extract().response().asString();
       JsonPath js3=new JsonPath(test);
       String SchemeDetail=js3.getString("SchemeDetailResult");
       System.out.println("SchemeDetail : "+SchemeDetail);
   }


}






