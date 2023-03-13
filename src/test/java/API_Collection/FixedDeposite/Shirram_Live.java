package API_Collection.FixedDeposite;
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


public class Shirram_Live {

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

    String encryptdata;

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
        fdpojo.Encryp response= res.when()
                .post("/ApiCertService/ApiMasterWcfService.svc/RequestEncryptdata")
                .then().log().all().spec(respec).extract().response().as(fdpojo.Encryp.class);
        String encryptdata=response.getRequestEncryptdataResult();
        System.out.println(encryptdata);

    }

    @Test
    public void Schemedetail()
    {
        String cook="LzLl8w4gc2Qw+hyTPCTX2jED5oadRCP1uiyCjMiubT4=";

        RequestSpecification request = RestAssured.given();
        request.baseUri("https://invapi.stfc.in")
                .header("IncomingAuthKey",cook)	//cookies token
                .body("{\r\n"
                        + "    \"Request\": \""+encryptdata+"\"\r\n"
                        + "}");
        request.basePath("/INVCOSScheme/COSSchemeService.svc/Schemedetail");
        Response response = request.post();
        System.out.println(response.asString());

    }


    @Test
    public void LiveInterestRate()
    { // refer strdata from test.java class

        String strdata="uGoZ8eizUNaYr6qxFS0Tx+kMXaNP0ziv28cGj3j54ivGBjxsa9O0GBF308iA7riorenTJe3/u7iQSOoHpD37WnIapHtLGDI2FjDjsjPejMiqu2mVYhyDkOMkRQQ/Cy84q+i4JAuuzfFkS6GpJaaebWC9SQq6r+74KqyAiD485/CwVsyzfSsLRV297TJ1gIGPELR+QDltsptrDMHPqdMEzglnO+9JQ32zvJtkRwNyfpqsPvgLLXNxmcqfwLYLaYFljMz3IATyIwVL37IYa2jvkervuZEdCXR0zh6DCgkXmgrWMORS0DJoeuVLWV383hj1wnTqZ5xaj7Na1mUoeRspjQTUzT8lXBdOMxZU/SJATKRCj4vU5w5b2EkvvGprwy+nIpNV539U1SWEonI0OqX5tLkqYmvnJ3rMcWEBtXwtW7wlH4NcqqHh7W/jY8wZ3l+QvVnPNblnFcpJkhDsp6gVX6vYOna26SaE5/ESS4dnlF00xD4RnjGLPu7IuNIJsUiRXLRmbccG/ago0H8RESqkIf8wBjVhrAQMzXwEqq1y0ZYUzaObaUS26GLYsQ9BwC++qYNTw8uctqARUEpE5/PNfyMg0UyoRmyMTyiig3bb5u1cWPTuT4LBnRbl/xJOK0MMy0h+TiFcrTCOBNlg0Qon3cR+fnhVrOTyLsxpO4px8oCvH4E5u6Ys8XGVXQKTIJR9NHk1XOMJ90Zxp/Sm8TOV8vE80eiOh1B717qbxXzLk5FmhDlMB4L8rqpQqmgc4hjG35bVcn4Ci5ZkbTq2WY9BYnjjoAyyYhJ0t1oB1fvKP0rKDVDFLBSeW5JNCMoqxmYaroK4fvgP4wRBcAr03TR1Gw==";

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
