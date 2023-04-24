package Regression_Testing;

import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.MFSearchForm;
import MFPojo.OTP.CommonOTP;
import MFPojo.PortfolioDashboard;
import MFPojo.TwoFA.AddScheme;
import MFPojo.TwoFA.GetCart;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static Regression_Testing.Base_URI.req;
import static Regression_Testing.Base_URI.respec;
import static io.restassured.RestAssured.given;

public class Lumpsum {
    String CartId,GroupId,otprefid,DB_Otp,DB_refid;    String Holdingid, InvestorId, Goal_ID;
    String Expected_HoldID = "183318";    String Goal_Name = "Test Portfolio"; //sathish
    String AMC_Searc="";

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
        Map<String,Object> Payload=new LinkedHashMap<String,Object>();
        Payload.put("product","MF");
        Payload.put("id","");
        Map<String,Object> info=new HashMap<String,Object>();
            info.put("os","Web-FI");
            info.put("fcmId","");
        Payload.put("appInfo",info);
        Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> oti=new LinkedHashMap<String,Object>();
            oti.put("totalAmount",1000);
            oti.put("investmentType","oti");
            oti.put("paymentId","");
        List<Map<String, Object>> Schemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
            data.put("dividendOption","Payout");
            data.put("folio","-");
            data.put("bankId","1");
            data.put("payment",true);
            data.put("option","Growth");
            data.put("goalId",Goal_ID);
            data.put("schemeCode","8241");
            data.put("schemeName","HDFC Corp Bond Fund(G)");
            data.put("amount",1000);
            data.put("sipType","");
            data.put("sipDate",0);
        Schemelist.add(data);
            oti.put("schemes",Schemelist);
        Map<String,Object> investment=new LinkedHashMap<String,Object>();
            investment.put("oti",oti);
        Payload.put("mf",investment);

        RequestSpecification res = given().spec(req)
                .body(Payload);
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


