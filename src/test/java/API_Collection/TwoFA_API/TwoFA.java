package API_Collection.TwoFA_API;

import API_Collection.BaseURL;
import API_Collection.Login;
import DBConnection.DBconnection;
import MFPojo.OTP.CommonOTP;
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
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TwoFA {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.staging)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.twofa())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
    String CartId; String GroupId; String otprefid;
    String DB_Otp;   String DB_refid;

    @Test(priority = 0)
    public void Add_Schecm()
    {
        String payload="{\"product\":\"MF\",\"id\":\"\",\"appInfo\":{\"os\":\"Web-FI\",\"fcmId\":\"\"},\"investorName\":\"First Investor\",\"investorId\":\"285020\",\"holdingProfileId\":\"182347\",\"holdingProfileName\":\"First Investor\",\"holdingProfilePan\":\"OEXPX1162B\",\"mf\":{\"sip\":{\"totalAmount\":1000,\"investmentType\":\"sip\",\"paymentId\":\"\",\"schemes\":[{\"sipType\":\"regular\",\"sipDate\":4,\"folio\":\"12345/01\",\"bankId\":\"1\",\"id\":\"p3XYgtb9M9CSiI-FzsgZO\",\"payment\":false,\"option\":\"Growth\",\"goalId\":\"457078\",\"schemeCode\":\"453\",\"schemeName\":\"Aditya Birla SL Corp Bond Fund(G)\",\"amount\":1000,\"regular\":{\"amount\":1000,\"frequency\":\"monthly\",\"tenure\":999,\"consumerCode\":\"10000000111362\"}}]}}}";
        RequestSpecification res = given().spec(req)
                .body(payload);
        AddScheme.Root response=res.when().post("/core/investor/cart")
                .then().log().all().spec(respec).extract().response().as(AddScheme.Root.class);
        CartId= response.getData().getCartId();
        System.out.println(CartId);
    }
    @Test(priority = 1)
    public void Get_Cart() {
        RequestSpecification getres = given().spec(req)
                .queryParam("cartId", CartId);
        GetCart.Root response=getres.when().get("/core/investor/cart/folio-groups")
                .then().log().all().spec(respec).extract().response().as(GetCart.Root.class);
        GroupId=response.getData().getGroups().get(0).getGroupId();
        System.out.println(GroupId);
    }
    @Test(priority = 2)
    public void Common_Otp() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "folio_group_id");
        otppayload.put("referenceId", GroupId);
        otppayload.put("workflow", "sip_oti_2fa");

        RequestSpecification otpres = given().spec(req)
                .body(otppayload);
        CommonOTP.Root response=otpres.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        otprefid= response.getData().getOtpReferenceId();
        System.out.println(otprefid);
    }
    @Test(priority = 4)
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
    @Test(priority = 5)
    public void Buy_Cart() {
        RequestSpecification buyres=given().spec(req)
                .queryParam("cartId",CartId);
        buyres.when().get("/core/investor/cart/buy")
                .then().log().all().spec(respec);
    }
    @Test(priority = 3)
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
    @Test
    public void Contact_Info()
    {
        RequestSpecification res=given().spec(req);
        res.when().get("/core/investor/contacts")
                .then().log().all().spec(respec);
    }

}
