package API_Collection.TwoFA_API.Live;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
import MFPojo.HoldingProfile;
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

public class Change_Amount {
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
                .then().spec(respec).extract().response().as(SystematicPlans.Root.class);
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
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", Holdingid);
        payload.put("referenceId", refid);
        payload.put("schemeCode", schemcode);
        payload.put("portfolio", false);
        payload.put("type", type);
        payload.put("change_amount", 3000);
        RequestSpecification res = given().spec(req)
                .body(payload);
        res.when().put("/core/investor/systematic-plan/sips")
                .then().log().all().spec(respec);
    }

}
