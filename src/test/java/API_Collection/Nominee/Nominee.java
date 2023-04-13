package API_Collection.Nominee;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.Nominee.ExistingGetDeclaration;
import MFPojo.Nominee.ExistingPutResponse;
import MFPojo.Nominee.NewDeclaration;
import MFPojo.Nominee.PostResponse;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static io.restassured.RestAssured.given;

public class Nominee {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Nominee())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String otp_refid, dbotp, DB_refid;

    @Test
    public void Nominee_Declaration() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "181558");
        NewDeclaration.Root response = res.when().get("/core/investor/nominees/declaration")
                .then().log().all().spec(respec).extract().response().as(NewDeclaration.Root.class);
        System.out.println(response.getData().getStatus());
    }
    @Test
    public void Nominee_Add() {         // Post API

        RequestSpecification res = given().spec(req)
                .body(payload.single())
                .cookie("Test","Test");
        PostResponse.Root response = res.when().log().body().log().method().post("/core/investor/nominees")
                .then()
                .log().ifValidationFails(LogDetail.STATUS)
                .log().body().spec(respec).extract().response().as(PostResponse.Root.class);
        for (int i = 0; i < response.getData().getInvestors().size(); i++) {
            otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
            System.out.println("OTP RefID : " + otp_refid);
        }
    }

// Test B (181558) , //181596(3Joint)  181559(2 joint)
    @Test
    public void Existing()	{
        //Investor ID for Equity and Holding id for MF
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","183213")
                .queryParam("product","MF");;
        res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().all().spec(respec);
    }
    @Test
    public void Put_Nominee()	{       //PUT API
        RequestSpecification res=given().spec(req)
                .queryParam("product","MF")
                .body(ExistingPayload.One_Nomoinee());
        res.when().put("/core/investor/nominees")
                .then().log().all().spec(respec);
    }

    @Test
    public void JointAccount() {
//Get API
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "181559")
                .queryParam("product", "MF");
        ExistingGetDeclaration.Root response = res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().all().spec(respec).extract().response().as(ExistingGetDeclaration.Root.class);
        String amccode = null;
        String folioid = null;

        for (int i = 0; i < response.getData().size(); i++) {
            if (response.getData().get(i).getMf().getFolio().equalsIgnoreCase("34649/46464")) {

                folioid = response.getData().get(i).getMf().getFolio();
                amccode = response.getData().get(i).getMf().getAmcCode();
            }
        }
 // Payload for Existing Nominee
/*        Map<String, Object> optout = new LinkedHashMap<String, Object>();
         optout.put("holdingProfileId", "181559");

        List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
        Map<String, Object> folio = new HashMap<String, Object>();
        folio.put("amc", amccode);
        folio.put("amcCode", amccode);
        folio.put("folioNo", folioid);
        list.add(folio);

        optout.put("folios", list);
        optout.put("declarationType", "opt-out");
        optout.put("processType", "online");*/

//Add Nominee
        Map<String, Object> SingleNominee = new LinkedHashMap<String, Object>();
        SingleNominee.put("holdingProfileId", "181559");
        SingleNominee.put("declarationType", "opt-in");
        SingleNominee.put("processType", "online");
//NomineeList
    List<Map<String, Object>> nomineelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> nomineedata = new HashMap<String, Object>();
        nomineedata.put("firstName","Sathish");
        nomineedata.put("dateOfBirth","2000-10-10T00:00:00.000+0530");
        nomineedata.put("relationship","Brother");
        nomineedata.put("percentage",100);
    nomineelist.add(nomineedata);
//FolioList
    List<Map<String, Object>> foliolist = new LinkedList<Map<String, Object>>();
        Map<String, Object> foliodata = new HashMap<String, Object>();
        foliodata.put("amc", amccode);
     //   foliodata.put("amcCode", amccode);
        foliodata.put("folioNo", folioid);
    foliolist.add(foliodata);
        SingleNominee.put("nominees",nomineelist);
        SingleNominee.put("folios", foliolist);
        
// PUT API
        RequestSpecification res1 = given().spec(req)
                .queryParam("product", "MF")
                .body(SingleNominee);
        ExistingPutResponse.Root response1 = res1.when().put("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(ExistingPutResponse.Root.class);
    }

    @Test
    public void Test()	{       //PUT API
        RequestSpecification res=given().spec(req)
                .queryParam("product","MF")
                .body(ExistingPayload.One_Nomoinee());
        ExistingPutResponse.Root response= res.when().put("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(ExistingPutResponse.Root.class);
        for (int i = 0; i < response.getData().getInvestors().size(); i++) {
            otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
            System.out.println("OTP RefID : " + otp_refid);
        }
    }


// With DB Connection
    @Test
    public void Add_Nominee() throws SQLException {   // Post Nominee
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

    // With DB Connection ExistingNominee
    @Test
    public void Exe_Nominee() throws SQLException {
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            RequestSpecification res=given().spec(req)
                    .queryParam("product","MF")
                    .body(ExistingPayload.One_Nomoinee());
            ExistingPutResponse.Root response= res.when().put("/core/investor/nominees")
                    .then().log().all().spec(respec).extract().response().as(ExistingPutResponse.Root.class);
            for (int i = 0; i < response.getData().getInvestors().size(); i++) {
                otp_refid = response.getData().getInvestors().get(i).getOtpReferenceId();
                System.out.println("OTP RefID : " + otp_refid);
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
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (s1 != null) s1.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }

    }



}
