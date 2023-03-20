package API_Collection.Login;

import MFPojo.Signin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Live_Login {
    static RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri("https://staging-api.fundsindia.com")
            .addHeader("x-api-version","2.0")
            .addHeader("channel-id","10")
            .setContentType(ContentType.JSON).build();

    static ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public static String sathish()    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "dsathish0223@gmail.com");        login.put("password", "Koushik@26");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");
        RequestSpecification res=given().spec(req)
                .body(login);
       Signin.Root response =res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();

    }
    @Test
    public static String saravanan()    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "saravana0989@gmail.com");        login.put("password", "Sara_1689");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }

    @Test
    public static String Admin() {
        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "qateam@fundsindia.com");        login.put("password", "Oct@#789");
        login.put("grantType", "credentials");
        login.put("refreshToken", "string");

        RequestSpecification res = given().spec(req)
                .body(login);
        Signin.Root response = res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }

}
