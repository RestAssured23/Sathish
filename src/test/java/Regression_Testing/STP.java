package Regression_Testing;

import DBConnection.DBconnection;
import MFPojo.*;
import MFPojo.OTP.CommonOTP;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static Regression_Testing.Base_URI.req;
import static Regression_Testing.Base_URI.respec;
import static io.restassured.RestAssured.given;

public class STP {

    //Local DATA

    String expected_freq="monthly",Expected_Folio = "343423/435",
   Expected_Target_Scheme = "IDFC Bond Fund - Short Term Plan-Reg(G)",Expected_GoalName = "Test Portfolio";
    String Holdingid, otp_refid, dbotp, DB_refid, AMC_Name, AMC_Code;
    String Expected_HoldID = "183318",InvestorId;
    String fromschemename, fromschemecode, folio, fromoption, goalid, bankid, toschemename, toschemcode, tooption, goalname;
    double minAmount, units, minUnits, currentamount,stpmin_amount_D,stpmin_amount_M,stpmin_amount_W;
    int No_installments,EcsDate,min_installment_D,min_installment_M,min_installment_W;
    String freq_D,freq_M,freq_W,Frequency;
    Date sdate,edate;
    String StartDate,EndDate,Edit_StartDate,Edit_EndDate,stp_delID;

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
                .queryParam("holdingProfileId", "183318");             //183318
        InvestedScheme.Root response = InvestedScheme.when().get("/core/investor/invested-schemes")
                .then().log().all().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase(Expected_GoalName)
                    && (response.getData().get(i).getFolio().equalsIgnoreCase(Expected_Folio))) {
                fromschemename = response.getData().get(i).getSchemeName();
                fromschemecode = response.getData().get(i).getSchemeCode();
                folio = response.getData().get(i).getFolio();
                units = response.getData().get(i).getUnits();
                fromoption = response.getData().get(i).getOption();
                goalid = response.getData().get(i).getGoalId();
                bankid = response.getData().get(i).getBankId();
                minAmount = response.getData().get(i).getSwitchOut().getMinimumAmount();
                minUnits = response.getData().get(i).getSwitchOut().getMinimumUnits();
                currentamount = response.getData().get(i).getCurrentAmount();
                goalname = response.getData().get(i).getGoalName();
            }
        }
    }

    @Test(priority = 2)
    public void product_search_mf_form() {
        RequestSpecification res = given().spec(req)
                .queryParam("page", 1)
                .queryParam("size", 20)
                .queryParam("schemeCodes", fromschemecode);
        MFscheme.Root response = res.when().get("/core/product-search/mf/schemes")
                .then().log().all().spec(respec).extract().response().as(MFscheme.Root.class);
        for (int i = 0; i < response.getData().getContent().size(); i++) {
            AMC_Name = response.getData().getContent().get(i).getAmc();
            AMC_Code = response.getData().getContent().get(i).getAmcCode();
        }
    }
    @Test(priority = 3)
    public void TargetScheme_Search() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"page\": 1,\n" +
                        "  \"size\": 10,\n" +
                        "  \"orderBy\": \"rating\",\n" +
                        "  \"orderType\": \"DESC\",\n" +
                        "  \"categories\": [],\n" +
                        "  \"subCategories\": [],\n" +
                        "  \"query\": \"\",\n" +
                        "  \"risk\": [],\n" +
                        "  \"ratings\": [],\n" +
                        "  \"amcs\": [\n" +
                        "    {\n" +
                        "      \"name\": \"" + AMC_Name + "\",\n" +
                        "      \"value\": \"" + AMC_Code + "\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"searchCode\": [\n" +
                        "    {\n" +
                        "      \"value\": \"\",\n" +
                        "      \"sort\": true\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
        MFscheme.Root response = res.when().post("/core/product-search/mf")
                .then().log().all().spec(respec).extract().response().as(MFscheme.Root.class);
        int size = response.getData().getContent().size();
        for (int i = 0; i < size; i++) {
            if (response.getData().getContent().get(i).getName().equalsIgnoreCase(Expected_Target_Scheme)) {
                toschemename = response.getData().getContent().get(i).getName();
                toschemcode = response.getData().getContent().get(i).getSchemeCode();
                tooption = response.getData().getContent().get(i).getOption();
                int stpinsize = response.getData().getContent().get(i).getStpFrequencies().getIn().size();
                for (int j = 0; j < stpinsize; j++) {
                    if (response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency()
                            .equalsIgnoreCase("daily")) {
                        freq_D = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency();
                        min_installment_D = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getMinimum();
                        stpmin_amount_D = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getAmounts().getMinimumAmount();
                        System.out.println("Frequency: " + freq_D);
                        System.out.println("Minimum Installment: " + min_installment_D);
                        System.out.println("Minimum Amount: " + stpmin_amount_D);
                    } else if (response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency()
                            .equalsIgnoreCase("monthly")) {
                        freq_M = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency();
                        min_installment_M = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getMinimum();
                        stpmin_amount_M = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getAmounts().getMinimumAmount();
                        System.out.println("Frequency: " + freq_M);
                        System.out.println("Minimum Installment: " + min_installment_M);
                        System.out.println("Minimum Amount: " + stpmin_amount_M);
                    }
                    else if (response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency()
                            .equalsIgnoreCase("weekly")) {
                        freq_W = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getFrequency();
                        min_installment_W = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getMinimum();
                        stpmin_amount_W = response.getData().getContent().get(i).getStpFrequencies().getIn().get(j).getAmounts().getMinimumAmount();
                        System.out.println("Frequency: " + freq_W);
                        System.out.println("Minimum Installment: " + min_installment_W);
                        System.out.println("Minimum Amount: " + stpmin_amount_W);
                    }
                    System.out.println("To SchemeName: " + toschemename);
                    System.out.println("To schemecode: " + toschemcode);
                    System.out.println("To Option: " + tooption);
                }
            }
        }
    }
    @Test(priority = 4)
    public void Installment_dates() {
        Map<String,Object> Daily=new HashMap<String,Object>();
        Daily.put("installments",min_installment_D);
        Daily.put("frequency",freq_D);
        Daily.put("feature","STP");

        Map<String,Object> Monthly=new HashMap<String,Object>();
        Monthly.put("installments",min_installment_D);
        Monthly.put("frequency",freq_M);
        Monthly.put("feature","STP");
        Monthly.put("ecsDate",7);

        Map<String,Object> Weekly=new HashMap<String,Object>();
        Weekly.put("installments",6);
        Weekly.put("frequency",freq_W);
        Weekly.put("feature","STP");
        Monthly.put("ecsDay","thursday");

if(expected_freq.equalsIgnoreCase(freq_D)){
    RequestSpecification res = given().spec(req).log().body()
            .body(Daily);
    InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
            .then().log().body().spec(respec).extract().response().as(InstallmentDates.Root.class);
    No_installments= response.getData().getInstallments();
    EcsDate=response.getData().getEcsDate();
    Frequency= response.getData().getFrequency();
    sdate=response.getData().getStartDate();
    edate=response.getData().getEndDate();
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    StartDate=df.format(sdate);
    EndDate = df.format(edate);
    System.out.println(StartDate);
    System.out.println(EndDate);
}
       else if(expected_freq.equalsIgnoreCase(freq_M)){
            RequestSpecification res = given().spec(req).log().body()
                    .body(Monthly);
            InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
                    .then().log().body().spec(respec).extract().response().as(InstallmentDates.Root.class);
            No_installments= response.getData().getInstallments();
            EcsDate=response.getData().getEcsDate();
            Frequency= response.getData().getFrequency();

    sdate=response.getData().getStartDate();
    edate=response.getData().getEndDate();
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    StartDate=df.format(sdate);
    EndDate = df.format(edate);

            System.out.println(StartDate);
            System.out.println(EndDate);
        }
else if(expected_freq.equalsIgnoreCase(freq_W)){
    RequestSpecification res = given().spec(req).log().body()
            .body(Monthly);
    InstallmentDates.Root response=res.when().post("/core/calculators/installment-dates")
            .then().log().body().spec(respec).extract().response().as(InstallmentDates.Root.class);
    No_installments= response.getData().getInstallments();
    EcsDate=response.getData().getEcsDate();
    Frequency= response.getData().getFrequency();
    sdate=response.getData().getStartDate();
    edate=response.getData().getEndDate();
    DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    StartDate=df.format(sdate);
    EndDate = df.format(edate);
    System.out.println(StartDate);
    System.out.println(EndDate);
}

    }

    @Test(priority = 5)
    public void Common_OTP() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio");
        otppayload.put("referenceId", folio);
        otppayload.put("workflow", "stp");

        RequestSpecification commonotp = given().spec(req)
                .body(otppayload);
        CommonOTP.Root responce = commonotp.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otp_refid = responce.getData().getOtpReferenceId();
    }

    @Test(priority = 6)
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

    @Test(priority = 7)
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

    @Test(priority = 8)
    public void STP_API() {

        Map<String, Object> STP_Daily = new LinkedHashMap<String,Object>();
        STP_Daily.put("holdingProfileId", Holdingid);
        STP_Daily.put("folio", folio);
        STP_Daily.put("goalId", goalid);
        STP_Daily.put("goalName", goalname);
        STP_Daily.put("fromSchemeCode", fromschemecode);
        STP_Daily.put("toSchemeCode", toschemcode);
        STP_Daily.put("toOption", tooption);
        STP_Daily.put("monthlyAmount",stpmin_amount_D);
        STP_Daily.put("stpType", "regular");
        STP_Daily.put("accountClosure", false);
        STP_Daily.put("bankId", bankid);
        STP_Daily.put("startDate",StartDate);
        STP_Daily.put("endDate",EndDate);
        STP_Daily.put("otpReferenceId", DB_refid);
        Map<String, Object> D_type = new LinkedHashMap<String,Object>();
        D_type.put("noOfInstallments",min_installment_D);
        D_type.put("frequency","daily");
        STP_Daily.put("regular",D_type);


        Map<String, Object> STP_Monthly = new LinkedHashMap<String,Object>();
        STP_Monthly.put("holdingProfileId", Holdingid);
        STP_Monthly.put("folio", folio);
        STP_Monthly.put("goalId", goalid);
        STP_Monthly.put("goalName", goalname);
        STP_Monthly.put("fromSchemeCode", fromschemecode);
        STP_Monthly.put("toSchemeCode", toschemcode);
        STP_Monthly.put("toOption", tooption);
        STP_Monthly.put("monthlyAmount",stpmin_amount_M);
        STP_Monthly.put("stpDate",EcsDate);
        STP_Monthly.put("stpType", "regular");
        STP_Monthly.put("accountClosure", false);
        STP_Monthly.put("bankId", bankid);
        STP_Monthly.put("startDate",StartDate);
        STP_Monthly.put("endDate",EndDate);
        STP_Monthly.put("otpReferenceId", DB_refid);
        Map<String, Object> type = new LinkedHashMap<String,Object>();
        type.put("noOfInstallments",min_installment_M);
        type.put("frequency","monthly");
        STP_Monthly.put("regular",type);


        Map<String, Object> STP_Weekly = new LinkedHashMap<String,Object>();
        STP_Weekly.put("holdingProfileId", Holdingid);
        STP_Weekly.put("folio", folio);
        STP_Weekly.put("goalId", goalid);
        STP_Weekly.put("goalName", goalname);
        STP_Weekly.put("fromSchemeCode", fromschemecode);
        STP_Weekly.put("toSchemeCode", toschemcode);
        STP_Weekly.put("toOption", tooption);
        STP_Weekly.put("monthlyAmount",stpmin_amount_W);
        STP_Weekly.put("stpDay", "monday");
        STP_Weekly.put("stpType", "regular");
        STP_Weekly.put("accountClosure", false);
        STP_Weekly.put("bankId", bankid);
        STP_Weekly.put("startDate",StartDate);
        STP_Weekly.put("endDate",EndDate);
        STP_Weekly.put("otpReferenceId", DB_refid);
        Map<String, Object> W_type = new LinkedHashMap<String,Object>();
        W_type.put("noOfInstallments",min_installment_W);
        W_type.put("frequency","weekly");
        STP_Weekly.put("regular",W_type);

if(expected_freq.equalsIgnoreCase(freq_D)){
    RequestSpecification redeem = given().spec(req)
            .body(STP_Daily);
    redeem.when().post("/core/investor/current-stps")
            .then().log().all().spec(respec);
}
else if(expected_freq.equalsIgnoreCase(freq_M)){
    RequestSpecification redeem = given().spec(req)
            .body(STP_Monthly);
    redeem.when().post("/core/investor/current-stps")
            .then().log().all().spec(respec);
}
else if(expected_freq.equalsIgnoreCase(freq_W)){
    RequestSpecification redeem = given().spec(req)
            .body(STP_Weekly);
    redeem.when().post("/core/investor/current-stps")
            .then().log().all().spec(respec);
    }
    }
    @Test(priority = 9)
    public void Current_STP() {
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("holdingProfileId", Holdingid)
                .queryParam("page", "1")
                .queryParam("size", "10")
                .queryParam("revert", true);

        CurrentSTP.Root response=  res.when().get("/core/investor/current-stps")
                .then().log().body().spec(respec).extract().response().as(CurrentSTP.Root.class);
        int size=response.getData().getSchemes().size();
     stp_delID=response.getData().getSchemes().get(size-1).getStpId();
        System.out.println(stp_delID);
    }
   @Test(priority = 10)
    public void STP_Cancel(){
        RequestSpecification res = given().log().method().log().uri().spec(req)
                .queryParam("stpId",stp_delID).log().params().log().uri();
        res.when().delete("/core/investor/current-stps")
                .then().log().body().spec(respec);
    }

}
