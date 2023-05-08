package Regression_Testing;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import MFPojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class SIP_ChangeAmount {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Regression())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String Holdingid, InvestorId;
      String Expected_HoldID = "183318";
    String Reg_refid, Reg_schemecode, Alert_refid, Alert_schemecode, Flexi_refid, Flexi_schemecode;

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
                .queryParam("holdingProfileId", Holdingid);
        CurrentSIP.Root response = res.when().get("/core/investor/current-sips")
                .then().log().all().spec(respec).extract().response().as(CurrentSIP.Root.class);
        int sipsize = response.getData().getSips().size();

        for (int i = 0; i < sipsize; i++) {
            if (response.getData().getSips().get(i).getSipType().equalsIgnoreCase("regular")) {
                Reg_refid = response.getData().getSips().get(i).getReferenceId();
                Reg_schemecode = response.getData().getSips().get(i).getSchemeCode();
                System.out.println("Regular_id :" + Reg_refid);
                System.out.println("Regular Schemcode :" + Reg_schemecode);
                break;
            }
        }
        for (int j = 0; j < sipsize; j++) {
            if (response.getData().getSips().get(j).getSipType().equalsIgnoreCase("alert")) {
                Alert_refid = response.getData().getSips().get(j).getReferenceId();
                Alert_schemecode = response.getData().getSips().get(j).getSchemeCode();
                System.out.println("AlertSIP id :" + Alert_refid);
                System.out.println("Alert Schemcode :" + Alert_schemecode);
                break;
            }
        }
        for (int k = 0; k < sipsize; k++) {
            if (response.getData().getSips().get(k).getSipType().equalsIgnoreCase("flexi")) {
                Flexi_refid = response.getData().getSips().get(k).getReferenceId();
                Flexi_schemecode = response.getData().getSips().get(k).getSchemeCode();
                System.out.println("Flexi_refid :" + Flexi_refid);
                System.out.println("Regular Schemcode :" + Flexi_schemecode);
                break;
            }
        }
    }

    @Test(priority = 2)
    public void SIP_Amount_Change_Regular() {
        Map<String, Object> Payload = new HashMap<String, Object>();
        Payload.put("holdingProfileId", Holdingid);
        Payload.put("referenceId", Reg_refid);
        Payload.put("schemeCode", Reg_schemecode);
        Payload.put("portfolio", false);
        Payload.put("otpReferenceId", "");
        Payload.put("type", "regular");
        Payload.put("change_amount", 5000);

        RequestSpecification res = given().spec(req)
                .body(Payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }

    @Test(priority = 2)
    public void SIP_Amount_Change_Alert() {
        Map<String, Object> Payload = new HashMap<String, Object>();
        Payload.put("holdingProfileId", Holdingid);
        Payload.put("referenceId", Alert_refid);
        Payload.put("schemeCode", Alert_schemecode);
        Payload.put("portfolio", false);
        Payload.put("otpReferenceId", "");
        Payload.put("type", "alert");
        Payload.put("change_amount", 3000);

        RequestSpecification res = given().spec(req)
                .body(Payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }

    @Test(priority = 2)
    public void SIP_Amount_Change_FlexiSIP() {
        Map<String, Object> Payload = new HashMap<String, Object>();
        Payload.put("change_flexi_amount",1500);
        Payload.put("holdingProfileId", Holdingid);
        Payload.put("referenceId", Flexi_refid);
        Payload.put("schemeCode", Flexi_schemecode);
        Payload.put("portfolio", false);
        Payload.put("otpReferenceId", "");
        Payload.put("type", "flexi");


        RequestSpecification res = given().spec(req)
                .body(Payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }
}

