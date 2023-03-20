package API_Collection.GetAPI;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import API_Collection.Login.Login;
import MFPojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostAPI {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    //Local DATA
    String Holdingid;
    String Expected_HoldID = "179605";
    String InvestorId;

    //Live Data
    //  String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
    //  String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan


    @Test(priority = 0)
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
                    System.out.println(Holdingid);
                    System.out.println(InvestorId);

                }
            }
        }
    }

    @Test(priority = 1)
    public void Invested_Schemes() {

        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "179605");
        InvestedScheme.Root response = res.when().get("/core/investor/invested-schemes")
                .then().spec(respec).extract().response().as(InvestedScheme.Root.class);
        int size = response.getData().size();
        for (int i = 0; i < size; i++) {
            if (response.getData().get(i).getGoalName().equalsIgnoreCase("API Automation")
                    && (response.getData().get(i).getFolio().equalsIgnoreCase("0000102"))) {

// Questinoari API
                RequestSpecification qid = given().spec(req)
                        .body(Payload.questionnaire());
                QuestionnaireResponse.Root response1 = qid.when().post("/core/questionnaire")
                        .then().spec(respec).extract().response().as(QuestionnaireResponse.Root.class);
                String qref_id = response1.getData().getReferenceId();
                System.out.println(qref_id);
//Redeem Payload
                Map<String, Object> payload = new HashMap<String, Object>();
                payload.put("holdingProfileId", "179605");
                payload.put("folio", response.getData().get(i).getFolio());
                payload.put("goalId", response.getData().get(i).getGoalId());
                payload.put("goalName", response.getData().get(i).getGoalName());
                payload.put("schemeCode", response.getData().get(i).getSchemeCode());
                payload.put("schemeName", response.getData().get(i).getSchemeName());
                payload.put("units", 5.0);
                payload.put("unitsFormatted", "5.0");
                payload.put("redemptionMode", "partial");
                payload.put("dividendOption", "Payout");
                payload.put("option", "Dividend");
                payload.put("bankId", "1");
                payload.put("redemptionType", "regular");
                payload.put("otpReferenceId", "9ca9fa02-d97f-4ef9-895d-f165b1ed4d22");
                payload.put("questionnaireReferenceId", qref_id);
//RedeemAPI
                RequestSpecification redeem = given().spec(req)
                        .body(payload);
                redeem.when().post("/core/investor/redeem")
                        .then().log().all().spec(respec);

            }
        }
    }

    @Test
    public void ques()  // Redeem
    {
        RequestSpecification qid = given().spec(req)
                .body(Payload.questionnaire());
        QuestionnaireResponse.Root response = qid.when().post("/core/questionnaire")
                .then().log().all().spec(respec).extract().response().as(QuestionnaireResponse.Root.class);
        String qref_id = response.getData().getReferenceId();
        System.out.println(qref_id);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "179605");
        payload.put("folio", "0000102");
        payload.put("goalId", "456451");
        payload.put("goalName", "API Automation");
        payload.put("schemeCode", "31059");
        payload.put("schemeName", "HDFC Corp Bond Fund(IDCW)");
        payload.put("units", 5.0);
        payload.put("unitsFormatted", "5.0");
        payload.put("redemptionMode", "partial");
        payload.put("dividendOption", "Payout");
        payload.put("option", "Dividend");
        payload.put("bankId", "1");
        payload.put("redemptionType", "regular");
        payload.put("otpReferenceId", "9ca9fa02-d97f-4ef9-895d-f165b1ed4d22");
        payload.put("questionnaireReferenceId", qref_id);

        RequestSpecification redeem = given().spec(req)
                .body(payload);
        redeem.when().post("/core/investor/redeem")
                .then().log().all().spec(respec);
    }

    @Test
    public void Recent_Transactions() {

        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "179605");
        res.when().get("/core/investor/recent-transactions")
                .then().log().all().spec(respec);
    }

    @Test
    public void RT_DeleteAll() {
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "179605")
                .queryParam("page", "1")
                .queryParam("size", "10");
        RecentTransaction.Root response = res.when().get("/core/investor/recent-transactions")
                .then().spec(respec).extract().response().as(RecentTransaction.Root.class);

        int count = response.getData().size();

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < response.getData().get(i).getMf().size(); j++) {
                for (int k=0;k< response.getData().get(i).getMf().get(j).getActions().size();k++)
                {
                   if(response.getData().get(i).getMf().get(j).getActions().get(k).equalsIgnoreCase("cancel"))
                   {
               String refno=response.getData().get(i).getMf().get(j).getReferenceNo();
//Del API Payload
                       Map<String, String> del = new HashMap<String, String>();
                       del.put("action", "cancel");
                       del.put("referenceNo", refno);
//Delete API
                       RequestSpecification can=given().spec(req).body(del);
                       can.when().post("/core/investor/recent-transactions")
                               .then().log().all().spec(respec);
                     }  }  }
        }
   }


}
