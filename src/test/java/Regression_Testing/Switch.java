package Regression_Testing;
import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.InvestedScheme;
import MFPojo.MFscheme;
import MFPojo.OTP.CommonOTP;
import MFPojo.OTP.VerifyOtpRequest;
import MFPojo.RecentTransaction;
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

public class Switch {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.dev)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Regression())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    //Local DATA
    String Holdingid, otp_refid, dbotp, DB_refid, AMC_Name, AMC_Code,RT_refno;
    String Expected_HoldID = "183318";
    String InvestorId;
    String Expected_Folio = "124702100";
    String Expected_Target_Scheme = "Aditya Birla SL Floating Rate Fund(DD-IDCW)";
    String Expected_GoalName = "Test Portfolio";
    String fromschemename, fromschemecode, folio, fromoption, goalid, bankid, toschemename, toschemcode, tooption, goalname;
    double minAmount, units, minUnits, currentamount;

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
                .queryParam("size", 100)
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
                .then().spec(respec).extract().response().as(MFscheme.Root.class);
        int size = response.getData().getContent().size();
        for (int i = 0; i < size; i++) {
            if (response.getData().getContent().get(i).getName().equalsIgnoreCase(Expected_Target_Scheme)) {
                toschemename = response.getData().getContent().get(i).getName();
                toschemcode = response.getData().getContent().get(i).getSchemeCode();
                tooption = response.getData().getContent().get(i).getOption();
                System.out.println("To SchemeName: " + toschemename);
                System.out.println("To schemecode: " + toschemcode);
                System.out.println("To Option: " + tooption);
            }
        }
    }

    @Test(priority = 4)
    public void Common_OTP() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio");
        otppayload.put("referenceId", folio);
        otppayload.put("workflow", "switch");

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
    public void Switch_API() {
        Map<String, Object> Growth_Growth = new HashMap<String, Object>();
        Growth_Growth.put("holdingProfileId", Holdingid);
        Growth_Growth.put("folio", folio);
        Growth_Growth.put("goalId", goalid);
        Growth_Growth.put("goalName", goalname);
        Growth_Growth.put("fromSchemeName", fromschemename);
        Growth_Growth.put("fromSchemeCode", fromschemecode);
        Growth_Growth.put("toSchemeName", toschemename);
        Growth_Growth.put("toSchemeCode", toschemcode);
        Growth_Growth.put("units", minUnits);
        //  Growth_Growth.put("amount","");
        Growth_Growth.put("fromOption", fromoption);
        Growth_Growth.put("toOption", tooption);
        Growth_Growth.put("switchMode", "partial");              //partial or all
        Growth_Growth.put("switchType", "regular");
        Growth_Growth.put("bankId", bankid);
        Growth_Growth.put("otpReferenceId", DB_refid);

        Map<String, Object> Growth_Div = new HashMap<String, Object>();
        Growth_Div.put("holdingProfileId", Holdingid);
        Growth_Div.put("folio", folio);
        Growth_Div.put("goalId", goalid);
        Growth_Div.put("goalName", goalname);
        Growth_Div.put("fromSchemeName", fromschemename);
        Growth_Div.put("fromSchemeCode", fromschemecode);
        Growth_Div.put("toSchemeName", toschemename);
        Growth_Div.put("toSchemeCode", toschemcode);
        Growth_Growth.put("units", minUnits);
        //   Growth_Div.put("amount","");
        Growth_Div.put("fromOption", fromoption);
        Growth_Div.put("toDividendOption", "Payout");       // Payout / Reinvestment
        Growth_Div.put("toOption", tooption);
        Growth_Div.put("switchMode", "partial");              //partial or all
        Growth_Div.put("switchType", "regular");
        Growth_Div.put("bankId", bankid);
        Growth_Div.put("otpReferenceId", DB_refid);

        Map<String, Object> Div_Div = new HashMap<String, Object>();
        Div_Div.put("holdingProfileId", Holdingid);
        Div_Div.put("folio", folio);
        Div_Div.put("goalId", goalid);
        Div_Div.put("goalName", goalname);
        Div_Div.put("fromSchemeName", fromschemename);
        Div_Div.put("fromSchemeCode", fromschemecode);
        Div_Div.put("toSchemeName", toschemename);
        Div_Div.put("toSchemeCode", toschemcode);
        Div_Div.put("amount", 1000);
        //   Growth_Growth.put("units",minUnits);
        Div_Div.put("fromOption", fromoption);
        Div_Div.put("fromDividendOption", "Payout");
        Div_Div.put("toOption", tooption);
        Div_Div.put("toDividendOption", "Reinvestment");       // Payout / Reinvestment
        Div_Div.put("switchMode", "partial");              //partial or all
        Div_Div.put("switchType", "regular");
        Div_Div.put("bankId", bankid);
        Div_Div.put("otpReferenceId", DB_refid);

        Map<String, Object> Div_Growth = new HashMap<String, Object>();
        Div_Growth.put("holdingProfileId", Holdingid);
        Div_Growth.put("folio", folio);
        Div_Growth.put("goalId", goalid);
        Div_Growth.put("goalName", goalname);
        Div_Growth.put("fromSchemeName", fromschemename);
        Div_Growth.put("fromSchemeCode", fromschemecode);
        Div_Growth.put("toSchemeName", toschemename);
        Div_Growth.put("toSchemeCode", toschemcode);
        Div_Growth.put("units", minUnits);
        Div_Growth.put("fromOption", fromoption);
        Div_Growth.put("fromDividendOption", "Payout");
        Div_Growth.put("toOption", tooption);
        Div_Growth.put("switchMode", "partial");              //partial or full
        Div_Growth.put("switchType", "regular");
        Div_Growth.put("bankId", bankid);
        Div_Growth.put("otpReferenceId", DB_refid);


        if (fromoption.equalsIgnoreCase("Growth") && (tooption.equalsIgnoreCase("Growth"))) {
            RequestSpecification redeem = given().spec(req)
                    .body(Growth_Growth);
            redeem.when().post("/core/investor/switch")
                    .then().log().all().spec(respec);
        } else if (fromoption.equalsIgnoreCase("Growth") && (tooption.equalsIgnoreCase("Dividend"))) {
            RequestSpecification redeem = given().spec(req)
                    .body(Growth_Div);
            redeem.when().post("/core/investor/switch")
                    .then().log().all().spec(respec);
        } else if (fromoption.equalsIgnoreCase("Dividend") && (tooption.equalsIgnoreCase("Dividend"))) {
            RequestSpecification redeem = given().spec(req)
                    .body(Div_Div);
            redeem.when().post("/core/investor/switch")
                    .then().log().all().spec(respec);
        } else if (fromoption.equalsIgnoreCase("Dividend") && (tooption.equalsIgnoreCase("Growth"))) {
            RequestSpecification redeem = given().spec(req)
                    .body(Div_Growth);
            redeem.when().post("/core/investor/switch")
                    .then().log().all().spec(respec);
        }
    }

    @Test(priority = 8)
    public void Recent_Transaction() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "183318")
                .queryParam("page", "1")
                .queryParam("size", "10");
        RecentTransaction.Root response = res.when().get("/core/investor/recent-transactions")
                .then().spec(respec).extract().response().as(RecentTransaction.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < response.getData().get(i).getMf().size(); j++) {
                for (int k = 0; k < response.getData().get(i).getMf().get(j).getActions().size(); k++) {
                    if (response.getData().get(i).getMf().get(j).getFolio().equalsIgnoreCase(Expected_Folio) ==
                            (response.getData().get(i).getMf().get(j).getActions().get(k).equalsIgnoreCase("cancel"))) {
                        RT_refno = response.getData().get(i).getMf().get(j).getReferenceNo();
                        System.out.println(RT_refno);
                    }
                }
            }
        }
    }

    @Test(priority = 9)
 public void Delete_API() {
     Map<String, String> del = new HashMap<String, String>();
     del.put("action", "cancel");
     del.put("referenceNo", RT_refno);

     RequestSpecification can=given().spec(req).body(del);
     can.when().post("/core/investor/recent-transactions")
             .then().log().all().spec(respec);
 }

}
