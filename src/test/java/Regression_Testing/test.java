package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.InvestedScheme;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class test {
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
    String Scheme_Name,Scheme_code,Folio,Bankid,Option;
    String Expected_HoldID = "183318";    String Goal_Name = "Test Portfolio"; //sathish
    String Expected_Folio="94764646/47", Expected_Scheme="HDFC Corp Bond Fund(G)";

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
    public void InvestedSchem_API() {
        RequestSpecification InvestedScheme = given().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        MFPojo.InvestedScheme.Root response = InvestedScheme.when().get("/core/investor/invested-schemes")
                .then().log().all().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase(Goal_Name)
                    && (response.getData().get(i).getFolio().equalsIgnoreCase(Expected_Folio))) {
                Scheme_Name=response.getData().get(i).getSchemeName();
                Scheme_code=response.getData().get(i).getSchemeCode();
                Folio=response.getData().get(i).getFolio();
                Bankid=response.getData().get(i).getBankId();
                Goal_ID=response.getData().get(i).getGoalId();
                Option=response.getData().get(i).getOption();
            }
        }
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
                        "          \"dividendOption\": \"Payout\",\n" +
                        "          \"folio\": \""+Folio+"\",\n" +
                        "          \"bankId\": \""+Bankid+"\",\n" +
                        "          \"payment\": true,\n" +
                        "          \"option\": \""+Option+"\",\n" +
                        "          \"goalId\": \"" + Goal_ID + "\",\n" +
                        "          \"schemeCode\": \""+Scheme_code+"\",\n" +
                        "          \"schemeName\": \""+Scheme_Name+"\",\n" +
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


