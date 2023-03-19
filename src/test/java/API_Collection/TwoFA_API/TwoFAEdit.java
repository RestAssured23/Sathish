package API_Collection.TwoFA_API;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import MFPojo.Authorization;
import MFPojo.HoldingProfile;
import MFPojo.PendingPayment;
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

public class TwoFAEdit {

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum1)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.twofa())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String Holdingid;    String Expected_HoldID = "182350";    String InvestorId;    String refid;
    String siptype;    String schemcode;    String folio;    String type;

    @Test
    public void Feature()	{

        RequestSpecification res=given().spec(req);
        res.when().get("/core/features")
                .then().log().all().spec(respec);
    }


    @Test(priority = 0)
    public void Sys_plan() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "182350");
        res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }
    @Test(priority = 0)
    public void Holding_Profile() {
        RequestSpecification res = given().spec(req);
        HoldingProfile.Root hold_response = res.when().get("/core/investor/holding-profiles")
                .then().spec(respec).extract().response().as(HoldingProfile.Root.class);
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

    @Test(priority = 0)
    public void Systematic_plan() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "182350");
        SystematicPlans.Root response = res.when().get("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec).extract().response().as(SystematicPlans.Root.class);
        int size = response.getData().getSips().size();
        System.out.println(size);

        for (int i = 0; i < size; i++) {
            siptype = response.getData().getSips().get(i).getSipType();
            String ch_folio = response.getData().getSips().get(i).getFolio();
            if (siptype.equalsIgnoreCase("regular")
                    && (ch_folio.equalsIgnoreCase("-"))) {
                schemcode = response.getData().getSips().get(i).getSchemeCode();
                refid = response.getData().getSips().get(i).getReferenceId();
                folio = response.getData().getSips().get(i).getFolio();
                type = response.getData().getSips().get(i).getSipType();
                System.out.println("Scheme code:" + schemcode);
                System.out.println("Ref ID: " + refid);
                System.out.println("Folio: " + folio);
                System.out.println("SIP Type: " + type);
            }
        }
    }

    @Test(priority = 1)
    public void put() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", Holdingid);
        payload.put("referenceId", refid);
        payload.put("schemeCode", schemcode);
        payload.put("portfolio", false);
        payload.put("type", type);
        payload.put("change_amount", 6500);

        RequestSpecification res = given().spec(req)
                .body(payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }

    @Test
    public void Pending_Payments_NRI()
    {
        RequestSpecification res=given().spec(req)
          .queryParam("holdingProfileId","182766");         //NRI 182766 female_s_c 182350
     PendingPayment.Root response= res.when().get("/core/investor/pending-payments")
                .then().spec(respec).extract().response().as(PendingPayment.Root.class);
        for (int i=0;i<response.getData().getTransactions().size();i++){
            System.out.println("Payment ID : "+response.getData().getTransactions().get(i).getPaymentId());
            for(int j=0;j<response.getData().getTransactions().get(i).getSip().getSchemes().size();j++) {
                System.out.println("Group ID: "+response.getData().getTransactions().get(i).getSip().getSchemes().get(j).getFolioGroupId());
                System.out.println("Scheme Name : "+response.getData().getTransactions().get(i).getSip().getSchemes().get(j).getSchemeName());
             }
        }
    }

@Test
    public void Pending_Payments_Investor()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182350");         //NRI 182766 female_s_c 182350
        PendingPayment.Root response= res.when().get("/core/investor/pending-payments")
                .then().spec(respec).extract().response().as(PendingPayment.Root.class);
        for (int i=0;i<response.getData().getTransactions().size();i++){
            System.out.println("Payment ID : "+response.getData().getTransactions().get(i).getPaymentId());

           }

    }


    @Test
    public void Pending_Authorization_NRI()
    {
        RequestSpecification res=given().spec(req)
                .queryParam("holdingProfileId","182766");       //NRI 182766    female_s_c 182350
       Authorization.Root response= res.when().get("/core/investor/transactions/authorization")
                .then().spec(respec).extract().response().as(Authorization.Root.class);
        for (int i = 0; i < response.getData().getSips().size(); i++) {
            if (response.getData().getSips().get(i).getFolioGroupId().equalsIgnoreCase("")) {
                System.out.println("Group ID :"+response.getData().getSips().get(i).getFolioGroupId());
                System.out.println("Reference ID: "+response.getData().getSips().get(i).getReferenceId());
                System.out.println("SchemeName : " + response.getData().getSips().get(i).getSchemeName());
                System.out.println("SIP Type : " + response.getData().getSips().get(i).getSipType());
            } else {
                System.out.println("Group ID :"+response.getData().getSips().get(i).getFolioGroupId());
                System.out.println("Reference ID: "+response.getData().getSips().get(i).getReferenceId());
                System.out.println("SchemeName: " + response.getData().getSips().get(i).getSchemeName());
                System.out.println("SIP Type : " + response.getData().getSips().get(i).getSipType());
            }

       }
    }
    @Test
    public void Pending_Authorization_Invet() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "182350");       //NRI 182766    female_s_c 182350
        Authorization.Root response = res.when().get("/core/investor/transactions/authorization")
                .then().spec(respec).extract().response().as(Authorization.Root.class);

        for (int i = 0; i < response.getData().getSips().size(); i++) {
            if (response.getData().getSips().get(i).getFolioGroupId().equalsIgnoreCase("")) {
                System.out.println("Group ID :"+response.getData().getSips().get(i).getFolioGroupId());
                System.out.println("Reference ID: "+response.getData().getSips().get(i).getReferenceId());
                System.out.println("SchemeName : " + response.getData().getSips().get(i).getSchemeName());
                System.out.println("SIP Type : " + response.getData().getSips().get(i).getSipType());
            } else {
                System.out.println("Group ID :"+response.getData().getSips().get(i).getFolioGroupId());
                System.out.println("Reference ID: "+response.getData().getSips().get(i).getReferenceId());
                System.out.println("SchemeName: " + response.getData().getSips().get(i).getSchemeName());
                System.out.println("SIP Type : " + response.getData().getSips().get(i).getSipType());
            }
        }
    }


}
