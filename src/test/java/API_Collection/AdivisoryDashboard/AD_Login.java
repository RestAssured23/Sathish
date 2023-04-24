package API_Collection.AdivisoryDashboard;

import API_Collection.BaseURL.BaseURL;
import MFPojo.Signin;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AD_Login {
    static RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum2)
            .addHeader("x-api-version","2.0")
            .addHeader("channel-id","10")
            .setContentType(ContentType.JSON).build();

    static ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public static String qateam()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "qateam@fundsindia.com");
        //   login.put("emailId", "sat@gmail.com");
        login.put("password", "Oct@#789");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response =res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();

    }

}
