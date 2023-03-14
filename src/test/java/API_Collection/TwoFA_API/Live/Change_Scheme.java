package API_Collection.TwoFA_API.Live;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import MFPojo.HoldingProfile;
import MFPojo.OTP.CommonOTP;
import MFPojo.SystematicPlans;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Change_Scheme {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.staging)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Live_Login.sathish())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
    String Holdingid;       String Expected_HoldID = "1511045";  String InvestorId;     //sathish(1403821) test-> 1511045
    String siptype;    String schemcode;    String folio;    String type; String refid;

    @Test(priority = 0)
    public void Holding_Profile() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response= res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);
        int size = hold_response.getData().size();  // Data Size
        for(int i=0;i<size;i++)
        {
            int count=hold_response.getData().get(i).getInvestors().size();                 // Investor count
            String holdinglist= String.valueOf(hold_response.getData().get(i).getHoldingProfileId());
            for (int j=0;j<count;j++)
            {
                if (holdinglist.equalsIgnoreCase(Expected_HoldID))
                {
                    InvestorId= hold_response.getData().get(i).getInvestors().get(j).getInvestorId();
                    Holdingid=hold_response.getData().get(i).getHoldingProfileId();
                    System.out.println("Holding profile ID : "+Holdingid);
                    System.out.println("Investor ID : "+InvestorId);
                }
            }
        }
    }
    @Test(priority = 1)
    public void Sys_Plans(){
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "1511045");
        SystematicPlans.Root response = res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec).extract().response().as(SystematicPlans.Root.class);
        int size = response.getData().getSips().size();
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            siptype = response.getData().getSips().get(i).getSipType();
            String ch_folio = response.getData().getSips().get(i).getFolio();
            if (siptype.equalsIgnoreCase("regular")
                    && (ch_folio.equalsIgnoreCase("-"))) {
                String check_refid=response.getData().getSips().get(i).getReferenceId();
                if(check_refid.equalsIgnoreCase("1618469"))
                {
                schemcode = response.getData().getSips().get(i).getSchemeCode();
                refid = response.getData().getSips().get(i).getReferenceId();
                folio = response.getData().getSips().get(i).getFolio();
                type = response.getData().getSips().get(i).getSipType();
                System.out.println("Scheme code:" + schemcode);
                System.out.println("Ref ID: " + refid);
                System.out.println("Folio: " + folio);
                System.out.println("SIP Type: " + type);
            }}
        }
    }
//Change Amount
    @Test(priority = 2)
    public void put() {

        /*scheme change
        {
  "holdingProfileId": "182350",
  "referenceId": "49221",
  "schemeCode": "16904",
  "portfolio": false,
  "otpReferenceId": "5eda5513-f870-4d25-bc7a-5010f49eeedc",
  "type": "regular",
  "change_scheme": {
    "folio": "-",
    "schemeCode": "16904",
    "newSchemeCode": "29550",
    "option": "Growth"
  }
}
         */
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", Holdingid);
        payload.put("referenceId", refid);
        payload.put("schemeCode", schemcode);
        payload.put("portfolio", false);
        payload.put("type", type);
        payload.put("otpReferenceId", "");
        payload.put("change_amount", 8500);
        RequestSpecification res = given().spec(req)
                .body(payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }
    @Test(priority = 2)
    public void Common_Otp() {
        Map<String, Object> otppayload = new HashMap<String, Object>();
        otppayload.put("type", "mobile_and_email");
        otppayload.put("idType", "holding_profile_id");         //  holding_profile_id(newfolio) / folio_group_id(exe folio)
        otppayload.put("referenceId", "");
        otppayload.put("workflow", "sip_oti_2fa");

        RequestSpecification otpres = given().spec(req)
                .body(otppayload);
        CommonOTP.Root response=otpres.when().post("/core/investor/common/otp")
                .then().log().all().spec(respec).extract().response().as(CommonOTP.Root.class);
        String otprefid=response.getData().getOtpReferenceId();
        System.out.println(otprefid);
    }

    @Test(priority = 4)
    public void OTP_Verify() {
        Map<String, Object> payload1 = new HashMap<String, Object>();
        Map<String, Object> payload2 = new HashMap<String, Object>();
        payload2.put("email", "");
        payload2.put("sms", "");
        payload2.put("email_or_sms", "");
        payload1.put("otp", payload2);
        payload1.put("otpReferenceId", "");

        RequestSpecification res = given().spec(req)
                .body(payload1);
        res.when().post("/core/investor/common/otp/verify")
                .then().log().all().spec(respec);

    }


}
