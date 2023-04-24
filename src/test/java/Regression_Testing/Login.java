package Regression_Testing;

import MFPojo.Signin;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static Regression_Testing.Base_URI.req;
import static Regression_Testing.Base_URI.respec;
import static io.restassured.RestAssured.given;


public class Login {
    @Test
    public static String Test()    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "Regression@gmail.com");
        login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response =res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }
    @Test
    public static String sathish_Live()    {
        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "dsathish0223@gmail.com");        login.put("password", "Koushik@26");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");
        RequestSpecification res=given().spec(req)
                .body(login);
       Signin.Root response =res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();

    }



}
