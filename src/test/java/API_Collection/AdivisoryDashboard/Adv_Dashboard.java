package API_Collection.AdivisoryDashboard;

import API_Collection.BaseURL.BaseURL;
import API_Collection.Login.Live_Login;
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
            .setBaseUri(BaseURL.scrum2)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Login.Admin())
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
    @Test
    public void Advisory_dashboard() {
        RequestSpecification res = given().spec(req);
        res.when().get("/tools/advisory-dashboard/filters/form")
                .then().log().all().spec(respec);
    }
    @Test
    public void Review() {
        RequestSpecification res = given().spec(req);
        res.when().get("/core/portfolio-review/dues")
                .then().log().all().spec(respec);
    }
    @Test
    public void Review_Clients() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"page\": 0,\n" +
                        "  \"size\": 0,\n" +
                        "  \"due\": \"month\",\n" +
                        "  \"status\": [\n" +
                        "    \"in_progress\"\n" +
                        "  ],\n" +
                        "  \"fromDate\": \"string\",\n" +
                        "  \"toDate\": \"string\",\n" +
                        "  \"heads\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"managers\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"advisors\": [\n" +
                        "    \"string\"\n" +
                        "  ]\n" +
                        "}");
        res.when().post("/core/portfolio-review/clients")
                .then().log().all().spec(respec);
    }
    @Test
    public void Review_communications() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"reviewId\": \"string\",\n" +
                        "  \"from\": \"string\",\n" +
                        "  \"to\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"mobiles\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"type\": [\n" +
                        "    \"whatsapp\"\n" +
                        "  ]\n" +
                        "}");
        res.when().post("/core/portfolio-review/communications")
                .then().log().all().spec(respec);
    }
    @Test
    public void Review_callback() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"data\": [\n" +
                        "    {\n" +
                        "      \"tableName\": \"string\",\n" +
                        "      \"condition\": \"string\",\n" +
                        "      \"set\": \"string\",\n" +
                        "      \"upsert\": true\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}");
        res.when().post("/core/portfolio-review/callback")
                .then().log().all().spec(respec);
    }
    @Test
    public void Review_completed() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "  \"page\": 0,\n" +
                        "  \"size\": 0,\n" +
                        "  \"types\": [\n" +
                        "    \"all\"\n" +
                        "  ],\n" +
                        "  \"status\": [\n" +
                        "    \"all\"\n" +
                        "  ],\n" +
                        "  \"fromDate\": \"string\",\n" +
                        "  \"toDate\": \"string\",\n" +
                        "  \"heads\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"managers\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"advisors\": [\n" +
                        "    \"string\"\n" +
                        "  ]\n" +
                        "}");
        res.when().post("/core/portfolio-review/completed")
                .then().log().all().spec(respec);
    }
}

