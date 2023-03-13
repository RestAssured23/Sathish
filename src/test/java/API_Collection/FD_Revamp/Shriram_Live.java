package FD_Revamp;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Shriram_Live {


// in strCompany //STFC-->(Non Cumulative) SDR -->(for Cumulative)

	String NonCummulative="{\r\n"
			+ "\"strdata\": "
			+ "\"{\\\"objsch\\\": "
			+ 		"{\\\"strAction\\\": \\\"F\\\", "
			+ 		"\\\"strCompany\\\": \\\"STFC\\\", "
			+		 "\\\"strType\\\": \\\"FDR\\\", "
			+ 		"\\\"strGender\\\": \\\"A\\\"}}\"\r\n"
			+ "}";

	String Cumulative="{\r\n"
			+ "\"strdata\": \"{\\\"objsch\\\": "
			+ "{\\\"strAction\\\": \\\"F\\\", "
			+ "\\\"strCompany\\\": \\\"STFC\\\", "
			+ "\\\"strType\\\": \\\"SDR\\\", "
			+ "\\\"strGender\\\": \\\"F\\\"}}\"\r\n"		//A --> Men F--> women
			+ "}";



	RequestSpecification req =new RequestSpecBuilder()
			.setBaseUri("https://shridocs.stfc.in")
		    .addHeader("Cookie","ASP.NET_SessionId=tnpovfgiklrpty11inftly0b")

			.setContentType(ContentType.JSON).build();


			ResponseSpecification respec =new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();

	@Test
	public void cookiee()
	{
		RequestSpecification res=given().spec(req);
		res.when()
				.get("/ApiCertService/ApiMasterWcfService.svc/GetEncryptedtoken/digi")
				.then().log().all().spec(respec).extract().response().asString();
	}
@Test
public void RequestEncryptdata()
{	
	
	RequestSpecification res=given().spec(req)
			.body(NonCummulative);
	res.when()
	.post("/ApiCertService/ApiMasterWcfService.svc/RequestEncryptdata")
	.then().log().all().spec(respec);	
	}

	@Test
	public void Schemedetail()
	{
		String cook="bVGgSKCoR6VIYzxrplOs3ujwQuioKG2FkBbjqXNkH7g=";
		String encrydate="5xZjQ6Y2WCdKHUC0YG9OZGLUvlgcAMTIW4IirZ2ku1vJQ/IHxLvSVlRRIVWKfQeg1jKIAOmnALHAV1gPemn6277gicrVg3AzR8ejc8Avu0BKCbDExhJfvExtw9WHvOve";
		RequestSpecification request = RestAssured.given();
		request.baseUri("https://invapi.stfc.in")
		.header("IncomingAuthKey",cook)	//cookies token
			.body("");
		request.basePath("/INVCOSScheme/COSSchemeService.svc/Schemedetail");
		Response response = request.post();
		System.out.println(response.asString());

	}


@Test
	public void LiveInterestRate()
	{ // refer strdata from test.java class

		String strdata="uGoZ8eizUNaYr6qxFS0Tx4jqDOVBkb8LEjL8b4ARd22rx2Ghf48je+dRXenMEeJdIFuyrxzrN/mikeFDrqiLB6Xlk/Dx6kwM5trWJBciCikpOWtRwaAT8MBVAkRC8yuecInp6odtRDeJsbYpbzeLLfbDRDAbzQ7b6yvHnhf12OF3L5P+QOOQFGKfysdROvh/EhRE9Q4c4+dLnSgKD65U96Jz+1y2QQoPvmqyRCfCrGO2XHx/Cd05mIxf519Rd0jUyFan6xd6tmv3C9umb3hxsy9eTEnCxUinily6WRwQz4GZBWAyJ78QXrAWc9y9zGwcmp1bGEdlMrOoMhxyWdpSmDKyHfuCmhjijvMaTHTLb0GVHKUuNRTdVLNABOMLRfZPsGO4To6Fx521Ukhlc0UAYA2AiC7VBQEYghRjmbVLGTU8YSVBasNS7o9AjDL2w6pG+YxLKkX5Os7MnjIEvTgBwjGywZW6+pVSAczW56tO5aUPzk0lqjSvVWu5u33Olb79HIwn8OSBbqUEDFXACEVuSofK3QQcW7Tsg8G+5vrs9xaryJbBhjaGkWaQOzgkO4scSYuOlxe4+9OHPZ6i5L83XY/QplwSodVjALsm5WBNZrmrJ6v+JsP7c/WY15fPXwvkygHDVMAxtRTj8ENH/3Ly3sVYaQEHupdFEtYINPIEdyR2oLU/XIchF42bFgu3BLnHTRyuYyHWD4i+9MaElSfrLzMsCPdtoZn9QmkT1e41ydaf3Ni7GBMfFGgFBy/rkZTDqpwgoNCJOy5hVxiCOlT4UEYya4UuaKZ252ZSekpXPPfwhMgrr+dIz++uS6WwdO176H04IWznyhsI4y3ClttwOqqcnu5RFbaUAe0Sf7WjMmvoo02RbZmLLxEAFT8wUL06rs+B2IiLaOJ95ooYnX19Q6Bx0v1BPAgWPHb1R81pTyg=";


		RequestSpecification res=given().spec(req)
				.body("{\n" +
						"    \"strdata\":  \" "+strdata+"  \"\n" +
						"}");
		res.when()
				.post("/ApiCertService/ApiMasterWcfService.svc/ResponseDecryptdata")
				.then().log().all().spec(respec);
	}




	@Test
	public void InterestRate()
	{
		String cookiee="QR242aDkDp5EuqEpqFNi0UURxg8H0ooKQL2ZU2LD/tw=";

		RequestSpecification encr=given().spec(req)
				.body(Cumulative);
		String reqenc=encr.when()
				.post("/ApiCertService/ApiMasterWcfService.svc/RequestEncryptdata")
				.then().spec(respec).extract().response().asString();
		JsonPath js1=new JsonPath(reqenc);
		String encrydate=js1.getString("RequestEncryptdataResult");
		System.out.println("Encrypt Data: " +encrydate);

		String test = RestAssured.baseURI="https://invapi.stfc.in";
		given().header("IncomingAuthKey",cookiee)
				.body("{\r\n"
						+ "    \"Request\": \""+encrydate+"\"\r\n"
						+ "}")
		.when().post("/INVCOSScheme/COSSchemeService.svc/Schemedetail")
				.then().log().all().extract().response().asString();
		JsonPath js3=new JsonPath(test);
		String SchemeDetails=js3.getString("SchemeDetailResult");
		System.out.println("SchemeDetail : "+SchemeDetails);


		RequestSpecification resp=given().spec(req)
				.header("IncomingAuthKey",cookiee)
				.body("{\r\n"
						+ "    \"Request\": \""+encrydate+"\"\r\n"
						+ "}");
		String scheme=resp.when()
				.post("/UATCOSScheme/COSSchemeService.svc/SchemeDetail")
				.then().spec(respec).extract().response().asString();
		JsonPath js2=new JsonPath(scheme);
		String SchemeDetail=js2.getString("SchemeDetailResult");
		System.out.println("SchemeDetail : "+SchemeDetail);



		RequestSpecification responce=given().spec(req)
				.body("{\r\n"
						+ "    \"strdata\":  \""+SchemeDetail+"\"\r\n"
						+ "}");
		responce.when()
				.post("/UATApiCertService/ApiMasterWcfService.svc/ResponseDecryptdata")
				.then().log().all().spec(respec);

	}





	

}
	
	
	
	
	
	
