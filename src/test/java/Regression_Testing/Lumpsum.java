package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.MFSearchForm;
import MFPojo.OTP.CommonOTP;
import MFPojo.PortfolioDashboard;
import MFPojo.TwoFA.AddScheme;
import MFPojo.TwoFA.GetCart;
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
import java.util.*;

import static io.restassured.RestAssured.given;

public class Lumpsum {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Regression())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
    String CartId,GroupId,otprefid,DB_Otp,DB_refid;    String Holdingid, InvestorId, Goal_ID;
    String Expected_HoldID = "183318";    String Goal_Name = "Test Portfolio"; //sathish

    //   String Holdingid;       String Expected_HoldID = "1540585";  String InvestorId;     //sathish live
    @Test
    public void Holding_Profile() {
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
                    System.out.println("Holding ID : " + Holdingid);
                    System.out.println("Investor ID : " + InvestorId);
                }
            }
        }
    }
    @Test(priority = 1)
    public void Dashboard_portfolio() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", Holdingid);
        payload.put("showZeroHoldings", true);
        Map<String, Object> sort = new HashMap<String, Object>();
        sort.put("by", "investment_amount");
        sort.put("type", "desc");
        payload.put("sort", sort);
        payload.put("type", "portfolio");

        RequestSpecification res = given().spec(req)
                .body(payload);
        PortfolioDashboard.Root response = res.when().post("/core/investor/dashboard/portfolio")
                .then().spec(respec).extract().response().as(PortfolioDashboard.Root.class);
        for (int i = 0; i < response.getData().size(); i++) {
            if (response.getData().get(i).getName().equalsIgnoreCase(Goal_Name)) {
                Goal_ID = response.getData().get(i).getId();
                System.out.println("Goal ID :" + Goal_ID);
            }
        }
    }
    @Test
    public void product_search_mf_form() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"page\": 1,\n" +
                        "  \"size\": 10,\n" +
                        "  \"orderBy\": \"rating\",\n" +
                        "  \"orderType\": \"DESC\",\n" +
                        "  \"categories\": [],\n" +
                        "  \"subCategories\": [],\n" +
                        "  \"query\": \"hdfc\",\n" +
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
       MFSearchForm.Root response= res.when().post("/core/product-search/mf")
                .then().log().all().spec(respec).extract().response().as(MFSearchForm.Root.class);
       /*for(int i=0;i<response.getData().getContent().size();i++){
           System.out.println(response.getData().getContent().get(i).getName());
           System.out.println(response.getData().getContent().get(i).getSchemeCode());
       }*/
    }
    @Test(priority = 2)
    public void Investor_Cart() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"product\": \"MF\",\n" +
                        "  \"id\": \"\",\n" +
                        "  \"appInfo\": {\n" +
                        "    \"os\": \"Web-FI\",\n" +
                        "    \"fcmId\": \"\"\n" +
                        "  },\n" +
                        "  \"holdingProfileId\": \"" + Holdingid + "\",\n" +
                        "  \"mf\": {\n" +
                        "    \"oti\": {\n" +
                        "      \"totalAmount\": 1000,\n" +
                        "      \"investmentType\": \"oti\",\n" +
                        "      \"paymentId\": \"\",\n" +
                        "      \"schemes\": [\n" +
                        "        {\n" +
                        "          \"folio\": \"-\",\n" +
                        "          \"bankId\": \"1\",\n" +
                        "          \"payment\": true,\n" +
                        "          \"option\": \"Growth\",\n" +
                        "          \"goalId\": \"" + Goal_ID + "\",\n" +
                        "          \"schemeCode\": \"8241\",\n" +
                        "          \"schemeName\": \"HDFC Corp Bond Fund(G)\",\n" +
                        "          \"amount\": 1000,\n" +
                        //        "          \"sipType\": \"\",\n" +
                        "          \"sipDate\": 0\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  }\n" +
                        "}");
        AddScheme.Root response=res.when().post("/core/investor/cart")
                .then().log().all().spec(respec).extract().response().as(AddScheme.Root.class);
        CartId= response.getData().getCartId();
        System.out.println(CartId);
    }

    @Test(priority = 3)
    public void Folio_Group_ID() {
        RequestSpecification getres = given().spec(req)
                .queryParam("cartId", CartId);
        GetCart.Root response = getres.when().get("/core/investor/cart/folio-groups")
                .then().log().all().spec(respec).extract().response().as(GetCart.Root.class);
        GroupId = response.getData().getGroups().get(0).getGroupId();
        System.out.println(GroupId);
    }
    @Test(priority = 4)
    public void Common_Otp() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio_group_id");
        otppayload.put("referenceId", GroupId);
        otppayload.put("workflow", "sip_oti_2fa");

        RequestSpecification otpres = given().spec(req)
                .body(otppayload);
        CommonOTP.Root response = otpres.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otprefid = response.getData().getOtpReferenceId();
        System.out.println(otprefid);
    }
    @Test(priority = 5)
    public void dbconnection() throws SQLException {
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
    @Test(priority = 6)
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
   @Test(priority = 7)
    public void Buy_Cart() {
        RequestSpecification buyres = given().spec(req)
                .queryParam("cartId", CartId);
        buyres.when().get("/core/investor/cart/buy")
                .then().log().all().spec(respec);
    }

 }


