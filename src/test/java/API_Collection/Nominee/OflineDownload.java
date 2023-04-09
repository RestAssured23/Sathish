package API_Collection.Nominee;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.Nominee.ExistingPutResponse;
import MFPojo.Nominee.NewDeclaration;
import MFPojo.Nominee.PostResponse;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OflineDownload {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.live)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Live_Login.saravanan())
            .setContentType(ContentType.ANY)
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

     @Test
    public void Nominee_Download() {
        RequestSpecification res = given().spec(req)
                .queryParam("product","MF")
                .queryParam("holdingProfileId", "181558")
                .queryParam("folios","0123456794")
                .queryParam("amcCode","400004")
                .queryParam("declarationType","opt-out");
        //        .contentType("application/pdf");
       res.when().get("/core/investor/nominees/download")
                .then().log().all().spec(respec);
    }


    // Test B (181558) , //181596(3Joint)  181559(2 joint)
    @Test
    public void Existing()	{
        //Investor ID for Equity and Holding id for MF
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","181559")
                .queryParam("product","MF");;
        res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().all().spec(respec);
    }
}
