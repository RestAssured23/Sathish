package API_Collection.AdivisoryDashboard;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Login;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Adv_Dashboard {

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri("https://scrum2-api.fundsindia.com")
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.twofa())
            .setContentType(ContentType.JSON).build();

    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public void mail_content() {
        RequestSpecification res = given().spec(req)
                .queryParam("reviewId", "3002");
        res.when().get("/core/portfolio-review/mail-content")
                .then().log().all().spec(respec);
    }
}

