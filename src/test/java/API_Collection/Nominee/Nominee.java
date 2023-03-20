package API_Collection.Nominee;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
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

public class Nominee {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.twofa())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String otp_refid, dbotp, DB_refid;

    @Test
    public void Nominee_Declaration() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "182348");
        NewDeclaration.Root response = res.when().get("/core/investor/nominees/declaration")
                .then().log().all().spec(respec).extract().response().as(NewDeclaration.Root.class);
        System.out.println(response.getData().getStatus());
    }
    @Test
    public void Opt_Out() {
        Map<String, Object> optout = new HashMap<String, Object>();
        optout.put("holdingProfileId", "182348");
        optout.put("optedOut", true);

        RequestSpecification res = given().spec(req)
                .body(optout);
        PostResponse.Root response = res.when().post("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
        for (int i = 0; i < response.getData().getInvestors().size(); i++) {
            otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
            System.out.println("OTP RefID : " + otp_refid);
        }
    }

    @Test
    public void Nominee_Add() {

        RequestSpecification res = given().spec(req)
                .body(payload.single());
        PostResponse.Root response = res.when().post("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
        for (int i = 0; i < response.getData().getInvestors().size(); i++) {
            otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
            System.out.println("OTP RefID : " + otp_refid);
        }
    }

    // DB connection
    @Test(priority = 1)
    public void DB_Connection() throws SQLException {
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            DBconnection ds = new DBconnection();
            con = ds.getConnection();
            s1 = con.createStatement();
            rs = s1.executeQuery("select * from dbo.OTP_GEN_VERIFICATION ogv where referenceId ='" + otp_refid + "'");
            rs.next();
            dbotp = rs.getString("otp");
            DB_refid = rs.getString("referenceid");
            System.out.println("OTP :" + dbotp);
            System.out.println("OTPReferenceID :" + DB_refid);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (s1 != null) s1.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

    // OTP Verify
    @Test(priority = 2)
    public void Otp_Verify() {
        VerifyOtpRequest.Root payload = new VerifyOtpRequest.Root();
        VerifyOtpRequest.Otp otp = new VerifyOtpRequest.Otp();
                 otp.setSms("");
                 otp.setEmail("");
                 otp.setEmail_or_sms(dbotp);
        payload.setOtp(otp);
        payload.setOtpReferenceId(DB_refid);

        RequestSpecification res1 = given().spec(req)
                .body(payload);
        res1.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);

    }

// With DB Connection
    @Test
    public void Add_Nominee() throws SQLException {
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            RequestSpecification res = given().spec(req)
                    .body(payload.single());
            PostResponse.Root response = res.when().post("/core/investor/nominees")
                    .then().log().all().spec(respec).extract().response().as(PostResponse.Root.class);
            for (int i = 0; i < response.getData().getInvestors().size(); i++) {
                otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
                System.out.println("OTP RefID : " + otp_refid);
            }
//DB Conection
            DBconnection ds = new DBconnection();
            con = ds.getConnection();
            s1 = con.createStatement();
            rs = s1.executeQuery("select * from dbo.OTP_GEN_VERIFICATION ogv where referenceId ='" + otp_refid + "'");
            rs.next();
            dbotp = rs.getString("otp");
            DB_refid = rs.getString("referenceid");
            System.out.println("OTP :" + dbotp);
            System.out.println("OTPReferenceID :" + DB_refid);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (s1 != null) s1.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
  // Verify OTP
        VerifyOtpRequest.Root payload = new VerifyOtpRequest.Root();
        VerifyOtpRequest.Otp otp = new VerifyOtpRequest.Otp();
        otp.setSms("");
        otp.setEmail("");
        otp.setEmail_or_sms(dbotp);
        payload.setOtp(otp);
        payload.setOtpReferenceId(DB_refid);
        RequestSpecification res1 = given().spec(req)
                .body(payload);
        res1.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);
    }

    @Test
    public void Existing()	{
        //Investor ID for Equity and Holding id for MF
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182348")
                .queryParam("product","MF");;
        res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().all().spec(respec);
    }

    @Test
    public void Put_Nominee()	{
        RequestSpecification res=given().spec(req)
                .queryParam("product","MF")
                .body(ExistingPayload.OptOut());
        res.when().put("/core/investor/nominees")
                .then().log().all().spec(respec);
    }

}
