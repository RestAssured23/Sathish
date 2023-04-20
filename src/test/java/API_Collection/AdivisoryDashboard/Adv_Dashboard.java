package API_Collection.AdivisoryDashboard;

import API_Collection.BaseURL.BaseURL;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Adv_Dashboard {

   String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzNDg2Iiwic2NvcGVzIjoicmVhZCx3cml0ZSIsIm5hbWUiOiJXSUZTIiwiZW1haWwiOiJhZG1pbkB3aWZzLmNvbSIsIm1vYmlsZSI6Ijk5OTk5OTk5OTkiLCJtYW5hZ2VtZW50LXVzZXItaWQiOjM0ODYsIm1hbmFnZW1lbnQtdXNlci1yb2xlcyI6ImFkbWluIiwiaXNzIjoiZnVuZHNpbmRpYS5jb20iLCJqdGkiOiI4ODZkN2FkZi05ODdkLTQyMzYtYmQ0Ni1iOGNiZjE5OTVlODIiLCJpYXQiOjE2ODA3MDEwMTYsImV4cCI6MTY4Mjg2MTAxNn0.YVFBYdCV8hPuRpf9j3gEKzqwFQg-k8bfrqf8ta7vh-Ni5RiixxUL2bp2LwIuJvT4q_DCfmNBhy-tOR1HNcu1lg" ;

    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.scrum2)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token",AD_Login.qateam())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

    @Test
    public void Communication() {
        RequestSpecification res = given().spec(req)
                .queryParam("reviewId",20879);
        res.when().get("/core/portfolio-review/communications/content")
                .then().log().all().spec(respec);
    }
    @Test
    public void Communication_mail() {
        RequestSpecification res = given().spec(req)
                .body("{\n" +
                        "    \"reviewId\":\"3040\",\n" +
                        "    \"from\": \"megha.n@fundsindia.com\",\n" +
                        "    \"to\": [\"shamelikumarcr7@gmail.com\"],\n" +
                        "    \"mobiles\": [\"09790790876\"],\n" +
                        "    \"type\":[\"whatsapp\",\"email\"]\n" +
                        "}");
        res.when().post("/core/portfolio-review/communications")
                .then().log().all().spec(respec);
    }


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

