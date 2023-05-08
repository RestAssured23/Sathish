package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.HoldingProfile;
import MFPojo.MFSearchForm;
import MFPojo.MandateAPI;
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

public class SIP {
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
    String Excpeted_AMC="ICICI", Expected_Scheme="ICICI Pru Balanced Advantage Fund(IDCW)";
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
    @Test(priority = 3)
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
    @Test(priority = 4)
    public void Investor_Cart_SIP() {
        Map<String,Object> Reg_Payload=new LinkedHashMap<String,Object>();
        Reg_Payload.put("product","MF");
        Reg_Payload.put("id","");
        Map<String,Object> info=new HashMap<String,Object>();
            info.put("os","Web-FI");
            info.put("fcmId","");
        Reg_Payload.put("appInfo",info);
        Reg_Payload.put("holdingProfileId",Holdingid);
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
            investment.put("sip",SIP);
        Reg_Payload.put("mf",investment);
//Dividend
        Map<String,Object> RegDiv_Payload=new LinkedHashMap<String,Object>();
        RegDiv_Payload.put("product","MF");
        RegDiv_Payload.put("id","");
        Map<String,Object> info_D=new HashMap<String,Object>();
        info_D.put("os","Web-FI");
        info_D.put("fcmId","");
        RegDiv_Payload.put("appInfo",info_D);
        RegDiv_Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> SIP_D=new LinkedHashMap<String,Object>();
        SIP_D.put("totalAmount",1000);
        SIP_D.put("investmentType","sip");
        SIP_D.put("paymentId","");
        List<Map<String, Object>> Schemelist_D = new LinkedList<Map<String, Object>>();
        Map<String, Object> data_D = new HashMap<String, Object>();
        data_D.put("dividendOption","Reinvestment");
        data_D.put("folio","-");
        data_D.put("bankId",Bank_Id);
        data_D.put("payment",false);
        data_D.put("option",Option);
        data_D.put("goalId",Goal_ID);
        data_D.put("schemeCode",Scheme_Code);
        data_D.put("schemeName",Scheme_Name);
        data_D.put("amount",1000);
        data_D.put("sipType","regular");
        data_D.put("sipDate",1);
        Map<String, Object> Reg_Type_D = new HashMap<String, Object>();
        Reg_Type_D.put("amount",Min_Amount);
        Reg_Type_D.put("frequency","monthly");
        Reg_Type_D.put("tenure",Min_Tenure);
        Reg_Type_D.put("consumerCode",ConsumerCode);
        data_D.put("regular",Reg_Type_D);
        Schemelist_D.add(data_D);
        SIP_D.put("schemes",Schemelist_D);
        Map<String,Object> investment_D=new LinkedHashMap<String,Object>();
        investment_D.put("sip",SIP_D);
        RegDiv_Payload.put("mf",investment_D);

        //FlexiSIP
        Map<String,Object> Flexi_Payload=new LinkedHashMap<String,Object>();
        Flexi_Payload.put("product","MF");
        Flexi_Payload.put("id","");
        Map<String,Object> flexi_info=new HashMap<String,Object>();
        flexi_info.put("os","Web-FI");
        flexi_info.put("fcmId","");
        Flexi_Payload.put("appInfo",flexi_info);
        Flexi_Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> flexi_SIP=new LinkedHashMap<String,Object>();
        flexi_SIP.put("totalAmount",1000);
        flexi_SIP.put("investmentType","sip");
        flexi_SIP.put("paymentId","");
        List<Map<String, Object>> flexiSchemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> flexi_data = new HashMap<String, Object>();
        flexi_data.put("folio","-");
        flexi_data.put("bankId",Bank_Id);
        flexi_data.put("payment",false);
        flexi_data.put("option",Option);
        flexi_data.put("goalId",Goal_ID);
        flexi_data.put("schemeCode",Scheme_Code);
        flexi_data.put("schemeName",Scheme_Name);
        flexi_data.put("amount",1000);
        flexi_data.put("sipType","flexi");
        flexi_data.put("sipDate",1);
        Map<String, Object> flexi_Reg_Type = new HashMap<String, Object>();
        flexi_Reg_Type.put("amount",Min_Amount);
        flexi_Reg_Type.put("frequency","monthly");
        flexi_Reg_Type.put("maximumAmount",3000);
        flexi_Reg_Type.put("flexiAmount",Min_Amount);
        flexi_Reg_Type.put("tenure",Min_Tenure);
        flexi_Reg_Type.put("consumerCode",ConsumerCode);
        flexi_data.put("flexi",flexi_Reg_Type);
        flexiSchemelist.add(flexi_data);
        flexi_SIP.put("schemes",flexiSchemelist);
        Map<String,Object> flexi_investment=new LinkedHashMap<String,Object>();
        flexi_investment.put("sip",flexi_SIP);
        Flexi_Payload.put("mf",flexi_investment);

        //FlexiSIP _ Divdend
        Map<String,Object> FlexiDiv_Payload=new LinkedHashMap<String,Object>();
        FlexiDiv_Payload.put("product","MF");
        FlexiDiv_Payload.put("id","");
        Map<String,Object> flexiDiv_info=new HashMap<String,Object>();
        flexiDiv_info.put("os","Web-FI");
        flexiDiv_info.put("fcmId","");
        FlexiDiv_Payload.put("appInfo",flexiDiv_info);
        FlexiDiv_Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> flexiDiv_SIP=new LinkedHashMap<String,Object>();
        flexiDiv_SIP.put("totalAmount",1000);
        flexiDiv_SIP.put("investmentType","sip");
        flexiDiv_SIP.put("paymentId","");
        List<Map<String, Object>> flexiDiv_Schemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> flexiDiv_data = new HashMap<String, Object>();
        flexiDiv_data.put("dividendOption","Reinvestment");
        flexiDiv_data.put("folio","-");
        flexiDiv_data.put("bankId",Bank_Id);
        flexiDiv_data.put("payment",false);
        flexiDiv_data.put("option",Option);
        flexiDiv_data.put("goalId",Goal_ID);
        flexiDiv_data.put("schemeCode",Scheme_Code);
        flexiDiv_data.put("schemeName",Scheme_Name);
        flexiDiv_data.put("amount",1000);
        flexiDiv_data.put("sipType","flexi");
        flexiDiv_data.put("sipDate",1);
        Map<String, Object> flexiDiv_Reg_Type = new HashMap<String, Object>();
        flexiDiv_Reg_Type.put("amount",Min_Amount);
        flexiDiv_Reg_Type.put("frequency","monthly");
        flexiDiv_Reg_Type.put("maximumAmount",3000);
        flexiDiv_Reg_Type.put("flexiAmount",Min_Amount);
        flexiDiv_Reg_Type.put("tenure",Min_Tenure);
        flexiDiv_Reg_Type.put("consumerCode",ConsumerCode);
        flexiDiv_data.put("flexi",flexiDiv_Reg_Type);
        flexiDiv_Schemelist.add(flexiDiv_data);
        flexiDiv_SIP.put("schemes",flexiDiv_Schemelist);
        Map<String,Object> flexiDiv_investment=new LinkedHashMap<String,Object>();
        flexiDiv_investment.put("sip",flexiDiv_SIP);
        FlexiDiv_Payload.put("mf",flexiDiv_investment);

        //Step_UP SIP
        Map<String,Object> Step_Payload=new LinkedHashMap<String,Object>();
        Step_Payload.put("product","MF");
        Step_Payload.put("id","");
        Map<String,Object> Step_info=new HashMap<String,Object>();
        Step_info.put("os","Web-FI");
        Step_info.put("fcmId","");
        Step_Payload.put("appInfo",Step_info);
        Step_Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> Step_SIP=new LinkedHashMap<String,Object>();
        Step_SIP.put("totalAmount",1000);
        Step_SIP.put("investmentType","sip");
        Step_SIP.put("paymentId","");
        List<Map<String, Object>> Step_Schemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> Step_data = new HashMap<String, Object>();
        Step_data.put("folio","-");
        Step_data.put("bankId",Bank_Id);
        Step_data.put("payment",false);
        Step_data.put("option",Option);
        Step_data.put("goalId",Goal_ID);
        Step_data.put("schemeCode",Scheme_Code);
        Step_data.put("schemeName",Scheme_Name);
        Step_data.put("amount",1000);
        Step_data.put("sipType","stepup");
        Step_data.put("sipDate",1);
        Map<String, Object> Step_Reg_Type = new HashMap<String, Object>();
        Step_Reg_Type.put("amount",Min_Amount);
        Step_Reg_Type.put("frequency","monthly");
        Step_Reg_Type.put("stepupFrequency","half-yearly");                 //  annually /half-yearly
        Step_Reg_Type.put("stepupAmount",Min_Amount);
        Step_Reg_Type.put("finalAmount",0);
        Step_Reg_Type.put("tenure",Min_Tenure);
        Step_Reg_Type.put("consumerCode",ConsumerCode);
        Step_data.put("stepup",Step_Reg_Type);
        Step_Schemelist.add(Step_data);
        Step_SIP.put("schemes",Step_Schemelist);
        Map<String,Object> Step_investment=new LinkedHashMap<String,Object>();
        Step_investment.put("sip",Step_SIP);
        Step_Payload.put("mf",Step_investment);

        //Step_UP SIP _ Divdend
        Map<String,Object> StepDiv_Payload=new LinkedHashMap<String,Object>();
        StepDiv_Payload.put("product","MF");
        StepDiv_Payload.put("id","");
        Map<String,Object> StepDiv_info=new HashMap<String,Object>();
        StepDiv_info.put("os","Web-FI");
        StepDiv_info.put("fcmId","");
        StepDiv_Payload.put("appInfo",StepDiv_info);
        StepDiv_Payload.put("holdingProfileId",Holdingid);
        Map<String,Object> StepDiv_SIP=new LinkedHashMap<String,Object>();
        StepDiv_SIP.put("totalAmount",1000);
        StepDiv_SIP.put("investmentType","sip");
        StepDiv_SIP.put("paymentId","");
        List<Map<String, Object>> StepDiv_Schemelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> StepDiv_data = new HashMap<String, Object>();
        StepDiv_data.put("dividendOption","Reinvestment");
        StepDiv_data.put("folio","-");
        StepDiv_data.put("bankId",Bank_Id);
        StepDiv_data.put("payment",false);
        StepDiv_data.put("option",Option);
        StepDiv_data.put("goalId",Goal_ID);
        StepDiv_data.put("schemeCode",Scheme_Code);
        StepDiv_data.put("schemeName",Scheme_Name);
        StepDiv_data.put("amount",1000);
        StepDiv_data.put("sipType","stepup");
        StepDiv_data.put("sipDate",1);
        Map<String, Object> StepDiv_Reg_Type = new HashMap<String, Object>();
        StepDiv_Reg_Type.put("amount",Min_Amount);
        StepDiv_Reg_Type.put("frequency","monthly");
        StepDiv_Reg_Type.put("stepupFrequency","half-yearly");                 //  annually /half-yearly
        StepDiv_Reg_Type.put("stepupAmount",Min_Amount);
        StepDiv_Reg_Type.put("finalAmount",0);
        StepDiv_Reg_Type.put("tenure",Min_Tenure);
        StepDiv_Reg_Type.put("consumerCode",ConsumerCode);
        StepDiv_data.put("stepup",StepDiv_Reg_Type);
        StepDiv_Schemelist.add(StepDiv_data);
        StepDiv_SIP.put("schemes",StepDiv_Schemelist);
        Map<String,Object> StepDiv_investment=new LinkedHashMap<String,Object>();
        StepDiv_investment.put("sip",StepDiv_SIP);
        StepDiv_Payload.put("mf",StepDiv_investment);


        if(Option.equalsIgnoreCase("Growth")) {
            RequestSpecification res = given().spec(req)
                    .body(Reg_Payload);
            AddScheme.Root response = res.when().post("/core/investor/cart")
                    .then().log().all().spec(respec).extract().response().as(AddScheme.Root.class);
            CartId = response.getData().getCartId();
            System.out.println(CartId);
        }
        else{
            RequestSpecification res = given().spec(req)
                    .body(RegDiv_Payload);
            AddScheme.Root response = res.when().post("/core/investor/cart")
                    .then().log().all().spec(respec).extract().response().as(AddScheme.Root.class);
            CartId = response.getData().getCartId();
            System.out.println(CartId);
        }
    }

    @Test(priority = 5)
    public void Folio_Group_ID() {
        RequestSpecification getres = given().spec(req)
                .queryParam("cartId", CartId);
        GetCart.Root response = getres.when().get("/core/investor/cart/folio-groups")
                .then().log().all().spec(respec).extract().response().as(GetCart.Root.class);
        GroupId = response.getData().getGroups().get(0).getGroupId();
        System.out.println(GroupId);
    }
    @Test(priority = 6)
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
    @Test(priority = 7)
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
    @Test(priority = 8)
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
   @Test(priority = 9)
    public void Buy_Cart() {
        RequestSpecification buyres = given().spec(req)
                .queryParam("cartId", CartId);
        buyres.when().get("/core/investor/cart/buy")
                .then().log().all().spec(respec);
    }

 }


