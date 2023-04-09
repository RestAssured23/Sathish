package API_Collection.Login;

import API_Collection.BaseURL.BaseURL;
import MFPojo.Signin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Login {
    static RequestSpecification req =new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum2)
            .addHeader("x-api-version","2.0")
            .addHeader("channel-id","10")
            .setContentType(ContentType.JSON).build();

    static ResponseSpecification respec =new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();


    @Test
    public static String Regression()
    {

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
    public static String sathish()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "saravanan.e@fundsindia.com");
     //   login.put("emailId", "sat@gmail.com");
        login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
       Signin.Root response =res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();

    }
    public static String saravanan()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "saravanan.e@fundsindia.com");
        login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");
        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }

//Nominee credential
    public static String Nominee()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "nomine@gmail.com");         login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");                  login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }


   //Tax Credential
    @Test
    public static String tax()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "taxtest@gmail.com");
        login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");
        login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }
    @Test
    public static String twofa()
    {

        HashMap<String, String> login = new HashMap<String, String>();
       login.put("emailId","mfrevamp@gmail.com");
      //  login.put("emailId", "fdrevamp@gmail.com");
        login.put("password", "asdfasdf12");
        login.put("grantType", "credentials");             login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }
//Admin
@Test
public static String Admin()    {

    HashMap<String, String> login = new HashMap<String, String>();
    login.put("emailId", "admin@wifs.com");        login.put("password", "asdfasdf");
    login.put("grantType", "credentials");        login.put("refreshToken", "string");

    RequestSpecification res=given().spec(req)
            .body(login);
    Signin.Root response=res.when().post("/core/auth/sign-in")
            .then().spec(respec).extract().response().as(Signin.Root.class);
    return response.getData().getAccessToken();
}

    @Test
    public static String testadmin()
    {
        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "testadmin@gmail.com");        login.put("password", "asdfasdf");
        login.put("grantType", "credentials");              login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }

    public static String equity()
    {

        HashMap<String, String> login = new HashMap<String, String>();
        login.put("emailId", "mailtossv@gmail.com");         login.put("password", "asdfasdf");
        login.put("grantType", "credentials");                  login.put("refreshToken", "string");

        RequestSpecification res=given().spec(req)
                .body(login);
        Signin.Root response=res.when().post("/core/auth/sign-in")
                .then().spec(respec).extract().response().as(Signin.Root.class);
        return response.getData().getAccessToken();
    }

}
