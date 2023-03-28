package API_Collection.Nominee;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import DBConnection.DBconnection;
import MFPojo.Nominee.ExistingGetDeclaration;
import MFPojo.Nominee.ExistingPutResponse;
import MFPojo.Nominee.NewDeclaration;
import MFPojo.Nominee.PostResponse;
import MFPojo.OTP.VerifyOtpRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static io.restassured.RestAssured.given;

public class Test {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.test)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Nominee())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    String otp_refid, dbotp, DB_refid;


    @org.testng.annotations.Test
    public void JointAccount() {
//Get API
        RequestSpecification res = given().spec(req)
                .queryParam("holdingProfileId", "181559")
                .queryParam("product", "MF");

        ExistingGetDeclaration.Root response = res.when().get("/core/investor/nominees/existing-declaration")
                .then().log().all().spec(respec).extract().response().as(ExistingGetDeclaration.Root.class);
        String amccode = null;
        String folioid = null;

        for (int i = 0; i < response.getData().size(); i++) {
            if (response.getData().get(i).getMf().getFolio().equalsIgnoreCase("34649/46464")) {

                folioid = response.getData().get(i).getMf().getFolio();
                amccode = response.getData().get(i).getMf().getAmcCode();
            }
        }
 // Payload for Existing Nominee
/*        Map<String, Object> optout = new LinkedHashMap<String, Object>();
        optout.put("holdingProfileId", "181559");
        optout.put("optedOut", true);

        List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
        Map<String, Object> folio = new HashMap<String, Object>();
        folio.put("amc", amccode);
        folio.put("amcCode", amccode);
        folio.put("folioNo", folioid);
        list.add(folio);

        optout.put("folios", list);
        optout.put("declarationType", "opt-out");
        optout.put("processType", "online");*/

//Add Nominee
        Map<String, Object> SingleNominee = new LinkedHashMap<String, Object>();
        SingleNominee.put("holdingProfileId", "181559");
        SingleNominee.put("optedOut", false);
        SingleNominee.put("declarationType", "opt-in");
        SingleNominee.put("processType", "online");

    List<Map<String, Object>> nomineelist = new LinkedList<Map<String, Object>>();
        Map<String, Object> nomineedata = new HashMap<String, Object>();
        nomineedata.put("firstName","Sathish");
        nomineedata.put("dateOfBirth","2000-10-10T00:00:00.000+0530");
        nomineedata.put("relationship","Brother");
        nomineedata.put("percentage",100);
    nomineelist.add(nomineedata);

    List<Map<String, Object>> foliolist = new LinkedList<Map<String, Object>>();
        Map<String, Object> foliodata = new HashMap<String, Object>();
        foliodata.put("amc", amccode);
        foliodata.put("amcCode", amccode);
        foliodata.put("folioNo", folioid);
    foliolist.add(foliodata);

        SingleNominee.put("nominees",nomineelist);
        SingleNominee.put("folios", foliolist);

        
// PUT API
        RequestSpecification res1 = given().spec(req)
                .queryParam("product", "MF")
                .body(SingleNominee);
        ExistingPutResponse.Root response1 = res1.when().put("/core/investor/nominees")
                .then().log().all().spec(respec).extract().response().as(ExistingPutResponse.Root.class);

    }
}
