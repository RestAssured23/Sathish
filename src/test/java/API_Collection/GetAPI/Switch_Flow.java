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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Switch_Flow {
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
    String Expected_Folio="000001203"; String Expected_GoalName="API Automation";

    //Live Data
     /* String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
      String Expected_Folio=""; String Expected_GoalName="";*/

   /*   String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan
    String Expected_Folio=" "; String Expected_GoalName="";*/

    @Test
    public void Switch_Flow() throws SQLException {
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
                String Schemecode = response.getData().get(i).getSchemeCode();
                System.out.println(Schemecode);






            }}

/*
//ProductSearch_MF_Form
                RequestSpecification product=given().spec(req)
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
                                "      \"name\": \"HDFC\",\n" +
                                "      \"value\": \"400013\"\n" +
                                "    }\n" +
                                "  ],\n" +
                                "  \"searchCode\": [\n" +
                                "    {\n" +
                                "      \"value\": \"\",\n" +
                                "      \"sort\": true\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}");
                product.when().post("/core/product-search/mf")
                        .then().log().all().statusCode(200);









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
                Map<String, Object> SwitchPayload = new HashMap<String, Object>();
                SwitchPayload.put("holdingProfileId", Holdingid);
                SwitchPayload.put("folio", response.getData().get(i).getFolio());
                SwitchPayload.put("goalId", response.getData().get(i).getGoalId());
                SwitchPayload.put("goalName", response.getData().get(i).getGoalName());
                SwitchPayload.put("schemeCode", response.getData().get(i).getSchemeCode());
                SwitchPayload.put("schemeName", response.getData().get(i).getSchemeName());
                SwitchPayload.put("units",response.getData().get(i).getUnits());
                SwitchPayload.put("unitsFormatted", response.getData().get(i).getUnitsFormatted());
                SwitchPayload.put("redemptionMode", "full");
                SwitchPayload.put("dividendOption", response.getData().get(i).getDividendOption());
                SwitchPayload.put("option", response.getData().get(i).getOption());
                SwitchPayload.put("bankId", response.getData().get(i).getBankId());
                SwitchPayload.put("redemptionType", "regular");
                SwitchPayload.put("otpReferenceId", DB_refid);


System.out.println("=========================Redeem API==========================================================");
//RedeemAPI
                RequestSpecification redeem = given().spec(req)
                        .body(SwitchPayload);
                redeem.when().post("/core/investor/switch")
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
        }*/
    }

 @Test
    public void product_search_mf_form()
    {
        RequestSpecification res=given().spec(req)
                        .queryParam("page",1)
                        .queryParam("size",100)
                        .queryParam("schemeCodes","454");
       MFscheme.Root response= res.when().get("/core/product-search/mf/schemes")
                .then().spec(respec).extract().response().as(MFscheme.Root.class);
      for(int i=0;i<response.getData().getContent().size();i++){
          System.out.println(response.getData().getContent().get(i).getAmc());
          System.out.println(response.getData().getContent().get(i).getAmcCode());
      }
    }

}
