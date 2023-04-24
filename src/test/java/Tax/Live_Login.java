package Tax;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Live_Login {

	static RequestSpecification req =new RequestSpecBuilder()
			.setBaseUri("https://hotfix-api.fundsindia.com")
			 .addHeader("x-api-version","2.0")
		    .addHeader("channel-id","10")
			.setContentType(ContentType.JSON).build();
			
			static ResponseSpecification respec =new ResponseSpecBuilder()
			.expectStatusCode(200)		
			.expectContentType(ContentType.JSON).build();
			
	@Test
	public static String sathish()
	{
		
		HashMap<String, String> login = new HashMap<String, String>();
		login.put("emailId", "dsathish0223@gmail.com");
		login.put("password", "Koushik@26");
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


}
