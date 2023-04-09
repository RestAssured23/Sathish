package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.GetAPI.Payload;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.*;
import MFPojo.OTP.CommonOTP;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SWP {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Regression())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)  
            .expectContentType(ContentType.JSON).build();

    //Local DATA
    String Holdingid,InvestorId,otp_refid, dbotp, DB_refid, qref_id, RT_refno;
    String Expected_HoldID = "183318";    String Expected_Folio = "128754555";    String Expected_GoalName = "Test Portfolio";
    int Min_Installment;    String Scheme_Frequency;    double Min_Amount;
String goalid,folio,schemecode;
    String Frequency;    int No_installments,EcsDate;    Date StartDate;    Date EndDate;
    @Test
    public void HoldingProfile_API() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().log().all().spec(respec).extract().response().as(HoldingProfile.Root.class);
        System.out.println("=========================HoldingAPI==========================================================");
        int size = hold_response.getData().size();  // Data Size
        for (int i = 0; i < size; i++) {
            int count = hold_response.getData().get(i).getInvestors().size();                 // Investor count
            String holdinglist = String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
            for (int j = 0; j < count; j++) {
                if (holdinglist.equalsIgnoreCase(Expected_HoldID)) {
                    InvestorId = hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                    Holdingid = hold_response.getData().get(i).getHoldingProfileId();
                    System.out.println(Holdingid);
                    System.out.println(InvestorId);
                }
            }
        }
    }

    @Test(priority = 1)
    public void InvestedSchem_API() {
        RequestSpecification InvestedScheme = given().spec(req)
                .queryParam("holdingProfileId", Holdingid);             //183318
        InvestedScheme.Root response = InvestedScheme.when().get("/core/investor/invested-schemes")
                .then().log().all().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase(Expected_GoalName)
                    && (response.getData().get(i).getFolio().equalsIgnoreCase(Expected_Folio))) {
                schemecode = response.getData().get(i).getSchemeCode();
                goalid=response.getData().get(i).getGoalId();
                folio=response.getData().get(i).getFolio();
            }
        }
    }

    @Test(priority = 2)
    public void product_search_mf_form() {
        RequestSpecification res = given().spec(req)
                .queryParam("page", 1)
                .queryParam("size", 100)
                .queryParam("schemeCodes", "20747");    //schemecode
        MFscheme.Root response = res.when().get("/core/product-search/mf/schemes")
                .then().log().all().spec(respec).extract().response().as(MFscheme.Root.class);
        for (int i = 0; i < response.getData().getContent().size(); i++) {
            for(int j = 0; j<response.getData().getContent().get(i).getSwpFrequencies().size(); j++)
            {
               System.out.println(response.getData().getContent().get(i).getSwpFrequencies().get(j).getDays());
               Min_Installment=response.getData().getContent().get(i).getSwpFrequencies().get(j).getMinimum();
               Scheme_Frequency=response.getData().getContent().get(i).getSwpFrequencies().get(j).getFrequency();
               Min_Amount=response.getData().getContent().get(i).getSwpFrequencies().get(j).getAmounts().getMinimumAmount();
            /*   System.out.println(Min_Installment);
                System.out.println(Frequency);
                System.out.println(Min_Amount);System.out.println(Min_Unit);*/
            }
        }
    }
    @Test(priority = 3)
    public void Installment_dates() {
            Map<String,Object> payload=new HashMap<String,Object>();
            payload.put("installments",6);
            payload.put("frequency","monthly");
            payload.put("feature","SWP");
            payload.put("ecsDate",4);

        RequestSpecification res = given().spec(req)
                .body(payload);
       InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
                .then().log().all().spec(respec).extract().response().as(InstallmentDates.Root.class);
        No_installments= response.getData().getInstallments();
        EcsDate=response.getData().getEcsDate();
        Frequency= response.getData().getFrequency();
        StartDate=response.getData().getStartDate();
        EndDate=response.getData().getEndDate();
        System.out.println(StartDate);
        System.out.println(EndDate);
        System.out.println(No_installments);System.out.println(EcsDate);System.out.println(Frequency);
    }
    @Test(priority = 4)
    public void Common_OTP() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio");
        otppayload.put("referenceId", folio);
        otppayload.put("workflow", "swp");

        RequestSpecification commonotp = given().spec(req)
                .body(otppayload);
        CommonOTP.Root responce = commonotp.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otp_refid = responce.getData().getOtpReferenceId();
    }

    @Test(priority = 5)
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

    @Test(priority = 6)
    public void Verify_OTP() {
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

    @Test(priority = 7)
    public void SWP_API() {
      Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("holdingProfileId",Holdingid);
        payload.put("goalId",goalid);
        payload.put("folio",folio);
        payload.put("schemeCode",schemecode);
        payload.put("dayOrDate","5");
        payload.put("numberOfInstallments",No_installments);
        payload.put("frequency",Frequency);
        payload.put("amount",Min_Amount);
        payload.put("startDate","2023-05-04T00:00:00.000+0530");
        payload.put("endDate","2023-10-04T00:00:00.000+0530");
        payload.put("otpReferenceId",DB_refid);

        RequestSpecification can = given().spec(req).body(payload);
        can.when().post("/core/investor/current-swps")
                .then().log().all().spec(respec);
    }

/*
    @Test(priority = 9)
    public void Delete_API() {
        Map<String, String> del = new HashMap<String, String>();
        del.put("action", "cancel");
        del.put("referenceNo", RT_refno);

        RequestSpecification can = given().spec(req).body(del);
        can.when().post("/core/investor/recent-transactions")
                .then().log().all().spec(respec);
    }*/
}
