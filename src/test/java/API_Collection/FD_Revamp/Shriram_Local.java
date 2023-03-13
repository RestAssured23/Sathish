package FD_Revamp;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Shriram_Local {
	//https://uatinv.stfc.me/uatbankbazaar/userdetails1.aspx?Qvalue=S2212297145&Parameter16=fdrevamp@gmail.com&Parameter21=5000.0
	RequestSpecification req =new RequestSpecBuilder()
			.setBaseUri("https://uatinv.stfc.me")
		    .addHeader("Cookie","ASP.NET_SessionId=tnpovfgiklrpty11inftly0b")

			.setContentType(ContentType.JSON).build();
			
			ResponseSpecification respec =new ResponseSpecBuilder()
			.expectStatusCode(200)		
			.expectContentType(ContentType.JSON).build();

// in strCompany //STFC(Non Cumulative)SDR (for Cumulative)			
			
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
		+ "\\\"strGender\\\": \\\"A\\\"}}\"\r\n"		//A --> Men F--> women
		+ "}";

		
@Test
public void cookiee()
{	
	RequestSpecification res=given().spec(req);
	res.when()
	.get("/UATApiCertService/ApiMasterWcfService.svc/GetEncryptedtoken/digi")
	.then().log().all().spec(respec).extract().response().asString();	
	}

@Test
public void InterestRate()
{
	String cookiee="QR242aDkDp5EuqEpqFNi0UURxg8H0ooKQL2ZU2LD/tw=";


	RequestSpecification res=given().spec(req);
	String cookie=res.when()
	.get("/UATApiCertService/ApiMasterWcfService.svc/GetEncryptedtoken/digi")
	.then().spec(respec).extract().response().asString();	
	//JsonPath js= new JsonPath(cookie);
	//String cook=js.getString(cookie);
	System.out.println("Cookies: "+cookie);
	
	RequestSpecification encr=given().spec(req)
			.body(Cumulative);
	String reqenc=encr.when()
	.post("/UATApiCertService/ApiMasterWcfService.svc/RequestEncryptdata")
	.then().spec(respec).extract().response().asString();	
	JsonPath js1=new JsonPath(reqenc);
	String encrydate=js1.getString("RequestEncryptdataResult");
	System.out.println("Encrypt Data: " +encrydate);
	
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
	
	
	
	
	
	
