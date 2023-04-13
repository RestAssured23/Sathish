package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import API_Collection.TwoFA_API.Live.Change_Amount;
import DBConnection.DBconnection;
import MFPojo.*;
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
import java.util.*;

import static io.restassured.RestAssured.given;

public class SIP_Edit {
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
    String Excpeted_AMC="icici", Expected_Scheme="ICICI Pru Balanced Advantage Fund(G)";
    String Expected_HoldID = "183318";    String Goal_Name = "Test Portfolio"; //sathish
    String ConsumerCode,Bank_Id; double Avaiable_Amount;   String Scheme_Name,Scheme_Code,Option;
    double Min_Amount;int Min_Tenure;

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
                .queryParam("holdingProfileId", "183318");
        CurrentSIP.Root response= res.when().get("/core/investor/current-sips")
                .then().log().all().spec(respec).extract().response().as(CurrentSIP.Root.class);
        for(int i=0;i<response.getData().getSips().size();i++){
            if(response.getData().getSips().get(i).getSchemeName().equalsIgnoreCase(Expected_Scheme)){
               String sip_refid,sip_schemecode,sip_type;
                sip_refid= response.getData().getSips().get(i).getReferenceId();
                sip_schemecode= response.getData().getSips().get(i).getSchemeCode();
                sip_type=response.getData().getSips().get(i).getSipType();
                System.out.println(sip_refid);
                System.out.println(sip_schemecode);
                System.out.println(sip_type);
            }
        }
    }
    @Test(priority = 1)
    public void SIP_Amount_Change() {
        Map<String,Object> Payload=new HashMap<String,Object>();
        RequestSpecification res = given().spec(req)
                        .body(Payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec).extract().response().as(CurrentSIP.Root.class);

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
                        "  \"query\": \""+Excpeted_AMC+"\",\n" +
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
       for(int i=0;i<response.getData().getContent().size();i++){
           if(response.getData().getContent().get(i).getName().equalsIgnoreCase(Expected_Scheme)){


               Scheme_Name=response.getData().getContent().get(i).getName();
               Scheme_Code=response.getData().getContent().get(i).getSchemeCode();
               Min_Amount=response.getData().getContent().get(i).getSipMinimumInvestment();
               Option=response.getData().getContent().get(i).getOption();
               Min_Tenure=response.getData().getContent().get(i).getMinimumSipTenure();
               System.out.println(Scheme_Name);
               System.out.println(Scheme_Code);
               System.out.println(Min_Amount);
               System.out.println(Option);
               System.out.println(Min_Tenure);
           }
       }
    }
    @Test
    public void Mandate_API(){
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","183318")
                .queryParam("ecsDate",1)
                .queryParam("sipType","regular");
       MandateAPI.Root response= res.when().get("/core/investor/mandates")
                .then().log().all().spec(respec).extract().response().as(MandateAPI.Root.class);
       for(int i=0;i<response.getData().size();i++){
          if(response.getData().get(i).getStatus().equalsIgnoreCase("Approved")){
             ConsumerCode= response.getData().get(i).getConsumerCode();
             Avaiable_Amount=response.getData().get(i).getAvailableAmount();
             Bank_Id=response.getData().get(i).getBank().getBankId();
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
        Map<String,Object> SIP=new LinkedHashMap<String,Object>();
            SIP.put("totalAmount",1000);
            SIP.put("investmentType","sip");
            SIP.put("paymentId","");
        List<Map<String, Object>> Schemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
            data.put("folio","-");
            data.put("bankId",Bank_Id);
            data.put("payment",false);
            data.put("option",Option);
            data.put("goalId",Goal_ID);
            data.put("schemeCode",Scheme_Code);
            data.put("schemeName",Scheme_Name);
            data.put("amount",1000);
            data.put("sipType","regular");
            data.put("sipDate",1);
        Map<String, Object> Reg_Type = new HashMap<String, Object>();
            Reg_Type.put("amount",Min_Amount);
            Reg_Type.put("frequency","monthly");
            Reg_Type.put("tenure",Min_Tenure);
            Reg_Type.put("consumerCode",ConsumerCode);
        data.put("regular",Reg_Type);
        Schemelist.add(data);
            SIP.put("schemes",Schemelist);
        Map<String,Object> investment=new LinkedHashMap<String,Object>();
            investment.put("oti",SIP);
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


