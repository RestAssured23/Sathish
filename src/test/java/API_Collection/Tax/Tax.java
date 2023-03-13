package API_Collection.Tax;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Tax {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.dev)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();
    @Test
    public void Dash_portfolio_Tax() {
        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("holdingProfileId", "179605");                //183121		183128	183135(NRI)
        payload.put("financialYear", "");            //previous , current
        payload.put("gainType", "unrealized");                    //[ realized, unrealized ]
        ArrayList<String> list = new ArrayList<String>();
        list.add("equity");
        list.add("non_equity");                            //[ equity, non_equity ]
        payload.put("categories", list);

        RequestSpecification res = given().spec(req)
                .body(payload);
        res.when().post("/investor/dashboard/portfolio/tax")
                .then().log().all().spec(respec).extract().response().asString();
    }
}
