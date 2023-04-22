package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
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
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;

public class SWP_SWPEdit {
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
    String Holdingid,InvestorId,otp_refid, dbotp, DB_refid, Swp_ID,dbotp1,DB_refid1;
    String Expected_HoldID = "183318";
    String Expected_Folio = "124702100" ,Expected_GoalName = "Test Portfolio",scheme_name;
    int Min_Installment,No_installments,Edit_EcsDate,Edit_Installments;     double Min_Amount;
    String goalid,folio,schemecode,Scheme_Frequency,Frequency;
   Date sdate,edate;
    String StartDate,EndDate,Edit_StartDate,Edit_EndDate,swp_delID,EcsDate;
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
                .queryParam("holdingProfileId", "183318").log().params();
        InvestedScheme.Root response = InvestedScheme.when().get("/core/investor/invested-schemes")
                .then().log().body().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase(Expected_GoalName)
                    && (response.getData().get(i).getFolio().equalsIgnoreCase(Expected_Folio)))   {
                schemecode = response.getData().get(i).getSchemeCode();
                scheme_name=response.getData().get(i).getSchemeName();
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
                .queryParam("schemeCodes", schemecode).log().params().log().uri();
        MFscheme.Root response = res.when().get("/core/product-search/mf/schemes")
                .then().log().all().spec(respec).extract().response().as(MFscheme.Root.class);
        for (int i = 0; i < response.getData().getContent().size(); i++) {
            for(int j = 0; j<response.getData().getContent().get(i).getSwpFrequencies().size(); j++)
            {
               System.out.println(response.getData().getContent().get(i).getSwpFrequencies().get(j).getDays());
               Min_Installment=response.getData().getContent().get(i).getSwpFrequencies().get(j).getMinimum();
               Scheme_Frequency=response.getData().getContent().get(i).getSwpFrequencies().get(j).getFrequency();
               Min_Amount=response.getData().getContent().get(i).getSwpFrequencies().get(j).getAmounts().getMinimumAmount();
                System.out.println(response.getData().getContent().get(i).getSwpFrequencies().get(j).getDays());

            }
        }
    }
    @Test(priority = 3)
    public void Installment_dates() {
            Map<String,Object> payload=new HashMap<String,Object>();
            payload.put("installments",Min_Installment);
            payload.put("frequency","monthly");
            payload.put("feature","SWP");
            payload.put("ecsDate",1);

        RequestSpecification res = given().spec(req)
                .body(payload);
       InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
                .then().log().body().spec(respec).extract().response().as(InstallmentDates.Root.class);
        No_installments= response.getData().getInstallments();
        int ecs_date=response.getData().getEcsDate();
        EcsDate=Integer.toString(ecs_date);
        Frequency= response.getData().getFrequency();
        sdate=response.getData().getStartDate();
        edate=response.getData().getEndDate();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        StartDate=df.format(sdate);
        EndDate = df.format(edate);
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
        payload.put("dayOrDate",EcsDate);
        payload.put("numberOfInstallments",No_installments);
        payload.put("frequency",Frequency);
        payload.put("amount",1000);
        payload.put("startDate",StartDate);
        payload.put("endDate",EndDate);
        payload.put("otpReferenceId",DB_refid);

        RequestSpecification res = given().spec(req).body(payload);
        SWPResponse.Root response=res.when().post("/core/investor/current-swps")
                .then().log().all().spec(respec).extract().response().as(SWPResponse.Root.class);
        Swp_ID=response.getData().getSwpId();
        System.out.println(Swp_ID);
        System.out.println(response.getData().getSwpAction());
    }
@Test(priority = 8)
    public void Installment_dates_Edit() {
        Map<String,Object> payload=new HashMap<String,Object>();
        payload.put("installments",10);
        payload.put("frequency","monthly");
        payload.put("feature","SWP");
        payload.put("ecsDate",7);
        RequestSpecification res = given().spec(req)
                .body(payload);
        InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
                .then().log().body().spec(respec).extract().response().as(InstallmentDates.Root.class);

       Edit_EcsDate=response.getData().getEcsDate();
       Edit_Installments=response.getData().getInstallments();
        sdate=response.getData().getStartDate();
        edate=response.getData().getEndDate();
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Edit_StartDate=df.format(sdate);
        Edit_EndDate = df.format(edate);
     System.out.println(Edit_StartDate);
     System.out.println(Edit_EndDate);
    System.out.println(Edit_EcsDate);
    }

    @Test(priority = 9)
    public void Common_OTP_Edit() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio");
        otppayload.put("referenceId", folio);
        otppayload.put("workflow", "swp_edit");

        RequestSpecification commonotp = given().spec(req)
                .body(otppayload);
        CommonOTP.Root responce = commonotp.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otp_refid = responce.getData().getOtpReferenceId();
    }

    @Test(priority = 10)
    public void DB_Connection_Edit() throws SQLException {
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            DBconnection ds = new DBconnection();
            con = ds.getConnection();
            s1 = con.createStatement();
            rs = s1.executeQuery("select * from dbo.OTP_GEN_VERIFICATION ogv where referenceId ='" + otp_refid + "'");
            rs.next();
            dbotp1 = rs.getString("otp");
            DB_refid1 = rs.getString("referenceid");
            System.out.println("OTP :" + dbotp1);
            System.out.println("OTPReferenceID :" + DB_refid1);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (s1 != null) s1.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }
    @Test(priority = 11)
    public void Verify_OTP_Edit() {
        VerifyOtpRequest.Root payload = new VerifyOtpRequest.Root();
        VerifyOtpRequest.Otp otp = new VerifyOtpRequest.Otp();
        otp.setSms("");
        otp.setEmail("");
        otp.setEmail_or_sms(dbotp1);
        payload.setOtp(otp);
        payload.setOtpReferenceId(DB_refid1);
        RequestSpecification res1 = given().spec(req)
                .body(payload);
        res1.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);
    }
    @Test(priority = 12)
    public void SWP_Edit(){
         Map<String,Object> payload_Edit=new HashMap<String,Object>();
        payload_Edit.put("holdingProfileId",Holdingid);
        payload_Edit.put("swpId",Swp_ID);
        payload_Edit.put("change_installments",Edit_Installments);
        payload_Edit.put("change_amount",2000);
        payload_Edit.put("change_swp_date",Edit_EcsDate);
        payload_Edit.put("changeStartDate",Edit_StartDate);
        payload_Edit.put("changeEndDate",Edit_EndDate);
        payload_Edit.put("otpReferenceId",DB_refid1);

         RequestSpecification res = given().log().method().log().uri().spec(req)
                 .body(payload_Edit);
        res.when().put("/core/investor/current-swps")
                    .then().log().body().spec(respec);
           }
    @Test(priority = 13)
    public void Current_SWP() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid)
                .queryParam("page", "1")
                .queryParam("size", "20")
                .queryParam("revert", true);

      CurrentSWP.Root response=  res.when().get("/core/investor/current-swps")
                .then().log().body().spec(respec).extract().response().as(CurrentSWP.Root.class);
        int size=response.getData().getSchemes().size();
        swp_delID=response.getData().getSchemes().get(size-1).getSwpId();
        System.out.println(swp_delID);
    }
@Test(priority = 14)
     public void SWP_Cancel(){
    RequestSpecification res = given().log().method().log().uri().spec(req)
            .queryParam("swpId",swp_delID).log().params().log().uri();
    res.when().delete("/core/investor/current-swps")
            .then().log().body().spec(respec);
    }

}



