package API_Collection.GetAPI;

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
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Redeem_Flow {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.dev)    
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    //Local DATA
    String Holdingid;    String Expected_HoldID = "179605";    String InvestorId;
    String Expected_Folio="00005789"; String Expected_GoalName="API Automation";

    //Live Data
     /* String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
      String Expected_Folio="00005789"; String Expected_GoalName="";*/

   /*   String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan
    String Expected_Folio=" "; String Expected_GoalName="";*/

    @Test
    public void Redeemption_Flow() throws SQLException {
        Statement s1 = null;
        Connection con = null;
        ResultSet rs = null;
 //Holding Profil API
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);
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

System.out.println("=========================Invested Scheme API==========================================================");
//Invested Scheme
        RequestSpecification InvestedScheme = given().spec(req)
                .queryParam("holdingProfileId", Holdingid);
        InvestedScheme.Root response = InvestedScheme.when().get("/core/investor/invested-schemes")
                .then().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase(Expected_GoalName)
                    && (response.getData().get(i).getFolio().equalsIgnoreCase(Expected_Folio))) {
System.out.println(response.getData().get(i).getSchemeName());
System.out.println("=========================Questinoari API==========================================================");

// Questinoari API
                RequestSpecification qid = given().spec(req)
                        .body(Payload.questionnaire());
                QuestionnaireResponse.Root quesresponse = qid.when().post("/core/questionnaire")
                        .then().spec(respec).extract().response().as(QuestionnaireResponse.Root.class);
                String qref_id = quesresponse.getData().getReferenceId();
                System.out.println(qref_id);

System.out.println("=========================Common OTP API==========================================================");
//Common OTP:
                Map<String, Object> otppayload = new HashMap<String, Object>();
                otppayload.put("type", "mobile_and_email");
                otppayload.put("idType", "folio");
                otppayload.put("referenceId", response.getData().get(i).getFolio());
                otppayload.put("workflow", "redemption");

                RequestSpecification commonotp = given().spec(req)
                        .body(otppayload);
                CommonOTP.Root responce = commonotp.when().post("/core/investor/common/otp")
                        .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
                String otp_refid = responce.getData().getOtpReferenceId();
//DB Conection
                String DB_refid = null;
                String dbotp = null;
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
System.out.println("=========================Verify OTP API==========================================================");
// Verify OTP
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
//Redeem Payload
                Map<String, Object> RedeemPayload = new HashMap<String, Object>();
                RedeemPayload.put("holdingProfileId", Holdingid);
                RedeemPayload.put("folio", response.getData().get(i).getFolio());
                RedeemPayload.put("goalId", response.getData().get(i).getGoalId());
                RedeemPayload.put("goalName", response.getData().get(i).getGoalName());
                RedeemPayload.put("schemeCode", response.getData().get(i).getSchemeCode());
                RedeemPayload.put("schemeName", response.getData().get(i).getSchemeName());
                RedeemPayload.put("units",response.getData().get(i).getUnits());
                RedeemPayload.put("unitsFormatted", response.getData().get(i).getUnitsFormatted());
                RedeemPayload.put("redemptionMode", "full");
                RedeemPayload.put("dividendOption", response.getData().get(i).getDividendOption());
                RedeemPayload.put("option", response.getData().get(i).getOption());
                RedeemPayload.put("bankId", response.getData().get(i).getBankId());
                RedeemPayload.put("redemptionType", "regular");
                RedeemPayload.put("otpReferenceId", DB_refid);
                RedeemPayload.put("questionnaireReferenceId", qref_id);

System.out.println("=========================Redeem API==========================================================");
//RedeemAPI
                RequestSpecification redeem = given().spec(req)
                        .body(RedeemPayload);
                redeem.when().post("/core/investor/redeem")
                        .then().log().all().spec(respec);
            }
        }
    }

    @Test(priority = 1)
    public void RT_DeleteAll() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId",Holdingid)
                .queryParam("page", "1")
                .queryParam("size", "10");
        RecentTransaction.Root response = res.when().get("/core/investor/recent-transactions")
                .then().spec(respec).extract().response().as(RecentTransaction.Root.class);
        int count = response.getData().size();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < response.getData().get(i).getMf().size(); j++) {
                for (int k=0;k< response.getData().get(i).getMf().get(j).getActions().size();k++){
                if (response.getData().get(i).getMf().get(j).getFolio().equalsIgnoreCase(Expected_Folio)==
               (response.getData().get(i).getMf().get(j).getActions().get(k).equalsIgnoreCase("cancel"))){
                   String refno=response.getData().get(i).getMf().get(j).getReferenceNo();
//Delete_API Payload
                    Map<String, String> del = new HashMap<String, String>();
                    del.put("action", "cancel");
                    del.put("referenceNo", refno);
//Delete API
                    RequestSpecification can=given().spec(req).body(del);
                    can.when().post("/core/investor/recent-transactions")
                            .then().log().all().spec(respec);
                }
            }  }
        }
    }


    }
