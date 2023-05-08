package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.CurrentSIP;
import MFPojo.HoldingProfile;
import MFPojo.MFSearchForm;
import MFPojo.OTP.CommonOTP;
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

public class SIP_ChangeScheme {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Regression())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String Holdingid, InvestorId;
    String CartId, GroupId, otprefid, DB_Otp, DB_refid;
    String Expected_HoldID = "183318";
    String Excpeted_AMC = "ICICI";
    String Expected_Scheme = "ICICI Pru Balanced Advantage Fund(IDCW)";
    String Reg_refid, Reg_schemecode, Alert_refid, Alert_schemecode, Flexi_refid, Flexi_schemecode, Reg_Folio, Step_Folio, Flex_Folio;
    String Scheme_Name, Scheme_Code, Option;
    double Min_Amount;
    int Min_Tenure;

    @Test
    public void HoldingProfile_API() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().log().all().spec(respec).extract().response().as(HoldingProfile.Root.class);
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
    public void Current_SIPS() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        CurrentSIP.Root response = res.when().get("/core/investor/current-sips")
                .then().log().all().spec(respec).extract().response().as(CurrentSIP.Root.class);
        int sipsize = response.getData().getSips().size();

        for (int i = 0; i < sipsize; i++) {
            if (response.getData().getSips().get(i).getSipType().equalsIgnoreCase("regular")) {
                Reg_refid = response.getData().getSips().get(i).getReferenceId();
                Reg_schemecode = response.getData().getSips().get(i).getSchemeCode();
                Reg_Folio = response.getData().getSips().get(i).getFolio();
                System.out.println("Regular_id :" + Reg_refid);
                System.out.println("Regular Schemcode :" + Reg_schemecode);
                break;
            }
        }
        for (int j = 0; j < sipsize; j++) {
            if (response.getData().getSips().get(j).getSipType().equalsIgnoreCase("stepup")) {
                Alert_refid = response.getData().getSips().get(j).getReferenceId();
                Alert_schemecode = response.getData().getSips().get(j).getSchemeCode();
                System.out.println("AlertSIP id :" + Alert_refid);
                System.out.println("Alert Schemcode :" + Alert_schemecode);
                break;
            }
        }
        for (int k = 0; k < sipsize; k++) {
            if (response.getData().getSips().get(k).getSipType().equalsIgnoreCase("flexi")) {
                Flexi_refid = response.getData().getSips().get(k).getReferenceId();
                Flexi_schemecode = response.getData().getSips().get(k).getSchemeCode();
                System.out.println("Flexi_refid :" + Flexi_refid);
                System.out.println("Regular Schemcode :" + Flexi_schemecode);
                break;
            }
        }
    }

    @Test(priority = 2)
    public void product_search_mf_form() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"page\": 1,\n" +
                        "  \"size\": 10,\n" +
                        "  \"orderBy\": \"rating\",\n" +
                        "  \"orderType\": \"DESC\",\n" +
                        "  \"categories\": [],\n" +
                        "  \"subCategories\": [],\n" +
                        "  \"query\": \"" + Excpeted_AMC + "\",\n" +
                        "  \"risk\": [],\n" +
                        "  \"ratings\": [],\n" +
                        "  \"amcs\": [],\n" +
                        "  \"searchCode\": [\n" +
                        "    {\n" +
                        "      \"value\": \"\",\n" +
                        "      \"sort\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"oti\": true\n" +
                        "}");
        MFSearchForm.Root response = res.when().post("/core/product-search/mf")
                .then().log().all().spec(respec).extract().response().as(MFSearchForm.Root.class);
        for (int i = 0; i < response.getData().getContent().size(); i++) {
            if (response.getData().getContent().get(i).getName().equalsIgnoreCase(Expected_Scheme)) {
                Scheme_Name = response.getData().getContent().get(i).getName();
                Scheme_Code = response.getData().getContent().get(i).getSchemeCode();
                Min_Amount = response.getData().getContent().get(i).getSipMinimumInvestment();
                Option = response.getData().getContent().get(i).getOption();
                Min_Tenure = response.getData().getContent().get(i).getMinimumSipTenure();
                System.out.println(Scheme_Name);
                System.out.println(Scheme_Code);
                System.out.println(Min_Amount);
                System.out.println(Option);
                System.out.println(Min_Tenure);
            }
        }
    }

    @Test(priority = 3)
    public void Common_Otp() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio");
        otppayload.put("referenceId", Reg_Folio);
        otppayload.put("workflow", "sip_oti_2fa");

        RequestSpecification otpres = given().spec(req)
                .body(otppayload);
        CommonOTP.Root response = otpres.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otprefid = response.getData().getOtpReferenceId();
        System.out.println(otprefid);
    }

    @Test(priority = 4)
    public void DB_Connection() throws SQLException {
        // DB connection
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
        try {
            DBconnection ds = new DBconnection();
            con = ds.getConnection();
            s1 = con.createStatement();
            rs = s1.executeQuery("select TOP 5* from dbo.OTP_GEN_VERIFICATION ogv where referenceId ='" + otprefid + "'");
            rs.next();
            DB_Otp = rs.getString("otp");
            DB_refid = rs.getString("referenceid");
            System.out.println("OTP :" + DB_Otp);
            System.out.println("OTPReferenceID :" + DB_refid);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (s1 != null) s1.close();
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
    }

    @Test(priority = 5)
    public void OTP_Verify() {
        Map<String, Object> payload1 = new HashMap<String, Object>();
        Map<String, Object> payload2 = new HashMap<String, Object>();
        payload2.put("email", "");
        payload2.put("sms", "");
        payload2.put("email_or_sms", DB_Otp);
        payload1.put("otp", payload2);
        payload1.put("otpReferenceId", DB_refid);

        RequestSpecification res = given().spec(req)
                .body(payload1);
        res.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);
    }

    @Test(priority = 6)
    public void Change_Scheme_Reg() {
        Map<String, Object> Reg_Growth = new HashMap<String, Object>();
        Reg_Growth.put("holdingProfileId", Holdingid);
        Reg_Growth.put("referenceId", Reg_refid);
        Reg_Growth.put("schemeCode", Reg_schemecode);
        Reg_Growth.put("portfolio", false);
        Reg_Growth.put("otpReferenceId", DB_refid);
        Reg_Growth.put("type", "regular");
        Reg_Growth.put("change_amount", 2000);

        Map<String, Object> scheme = new HashMap<String, Object>();
        scheme.put("folio", Reg_Folio);
        scheme.put("schemeCode", Reg_schemecode);
        scheme.put("newSchemeCode", Scheme_Code);
        scheme.put("option", Option);
        Reg_Growth.put("change_scheme", scheme);
        //Dividend
        Map<String, Object> Reg_Div = new HashMap<String, Object>();
        Reg_Div.put("holdingProfileId", Holdingid);
        Reg_Div.put("referenceId", Reg_refid);
        Reg_Div.put("schemeCode", Reg_schemecode);
        Reg_Div.put("portfolio", false);
        Reg_Div.put("otpReferenceId", DB_refid);
        Reg_Div.put("type", "regular");
        Reg_Div.put("change_amount", 2000);

        Map<String, Object> Div_scheme = new HashMap<String, Object>();
        Div_scheme.put("folio", Reg_Folio);
        Div_scheme.put("schemeCode", Reg_schemecode);
        Div_scheme.put("newSchemeCode", Scheme_Code);
        Div_scheme.put("option", Option);
        Div_scheme.put("dividendOption", "Reinvestment");
        Reg_Div.put("change_scheme", Div_scheme);

        if (Option.equalsIgnoreCase("Growth")) {
            RequestSpecification res = given().spec(req)
                    .body(Reg_Growth);
            res.when().put("/core/investor/systematic-plan/sips")
                    .then().log().all().spec(respec);
        } else {
            RequestSpecification res = given().spec(req)
                    .body(Reg_Div);
            res.when().put("/core/investor/systematic-plan/sips")
                    .then().log().all().spec(respec);
        }
    }
}


