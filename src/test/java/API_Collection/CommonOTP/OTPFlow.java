package API_Collection.CommonOTP;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.OTP.CommonOTP;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class OTPFlow {

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String refid = null;        String otp_refid;
    String dbotp,DB_refid;
// Common OTP
    @Test
public void Common_otp()
        {
            Map<String, Object> otppayload = new HashMap<String, Object>();
            otppayload.put("type", "mobile_and_email");
            otppayload.put("idType", "folio");
            otppayload.put("referenceId", "000001203");
            otppayload.put("workflow", "redemption");

            RequestSpecification otpres = given().spec(req)
                    .body(otppayload);
            CommonOTP.Root responce = otpres.when().post("/core/investor/common/otp")
                    .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
            otp_refid = responce.getData().getOtpReferenceId();
        }

// DB connection
    @Test(priority = 1)
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

// OTP Verify
    @Test(priority = 2)
    public void Otp_Verify(){
        VerifyOtpRequest.Root payload=new VerifyOtpRequest.Root();
     VerifyOtpRequest.Otp otp=new VerifyOtpRequest.Otp();
        otp.setSms("");
        otp.setEmail("");
        otp.setEmail_or_sms(dbotp);
        payload.setOtp(otp);
        payload.setOtpReferenceId(DB_refid);

/*        Map<String, Object> payload1 = new HashMap<String, Object>();
        Map<String, Object> payload2 = new HashMap<String, Object>();
        payload2.put("email", "");
        payload2.put("sms", "");
        payload2.put("email_or_sms", dbotp);
        payload1.put("otp", payload2);
        payload1.put("otpReferenceId", DB_refid);*/

        RequestSpecification res1 = given().spec(req)
                .body(payload);
        res1.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);

    }
}

