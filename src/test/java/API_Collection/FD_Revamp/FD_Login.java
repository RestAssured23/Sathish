package FD_Revamp;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class FD_Login {

	static RequestSpecification req =new RequestSpecBuilder()
			.setBaseUri("https://scrum4-api.fundsindia.com")
			 .addHeader("x-api-version","2.0")
		    .addHeader("channel-id","12")
			.setContentType(ContentType.JSON).build();
			
			static ResponseSpecification respec =new ResponseSpecBuilder()
			.expectStatusCode(200)		
			.expectContentType(ContentType.JSON).build();
			
	@Test
	public static String sathish()
	{
		
		HashMap<String, String> login = new HashMap<String, String>();
		login.put("emailId", "sat@gmail.com");
		login.put("password", "asdfasdf12");
		login.put("grantType", "credentials");
		login.put("refreshToken", "string");
		
		RequestSpecification res=given().spec(req)
		.body(login);
		String rs=res.when().post("/core/auth/sign-in")
		.then().spec(respec).extract().response().asString();
					
		JsonPath js=new JsonPath(rs);  							//parsing JSON
		String  access_token=js.getString("data.accessToken");
		return  access_token;	
		}
	@Test
	public static String fdrevamp()
	{
		
		HashMap<String, String> login = new HashMap<String, String>();
		login.put("emailId", "fdrevamp@gmail.com");
		login.put("password", "asdfasdf12");
		login.put("grantType", "credentials");
		login.put("refreshToken", "string");
		
		RequestSpecification res=given().spec(req)
		.body(login);
		String rs=res.when().post("/core/auth/sign-in")
		.then().spec(respec).extract().response().asString();
					
		JsonPath js=new JsonPath(rs);  							//parsing JSON
		String  access_token=js.getString("data.accessToken");
		return  access_token;	
		}
	public static String male()
	{

		HashMap<String, String> login = new HashMap<String, String>();
		login.put("emailId", "fdmale@gmail.com");
		login.put("password", "asdfasdf12");
		login.put("grantType", "credentials");
		login.put("refreshToken", "string");

		RequestSpecification res=given().spec(req)
				.body(login);
		String rs=res.when().post("/core/auth/sign-in")
				.then().spec(respec).extract().response().asString();

		JsonPath js=new JsonPath(rs);  							//parsing JSON
		String  access_token=js.getString("data.accessToken");
		return  access_token;
	}
	@Test
	public void testing()
	{

		HashMap<String, String> login = new HashMap<String, String>();
		login.put("emailId", "sat@gmail.com");
		login.put("password", "asdfasdf12");
		login.put("grantType", "credentials");
		login.put("refreshToken", "string");

		RequestSpecification res=given().spec(req)
				.body(login);
		res.when().post("/core/auth/sign-in")
				.then().log().all().spec(respec);
	}



}
