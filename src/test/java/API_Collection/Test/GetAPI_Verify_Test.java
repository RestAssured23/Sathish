package API_Collection.Test;

import API_Collection.BaseURL;
import API_Collection.Live_Login;
import MFPojo.HoldingProfile;
import MFPojo.TwoFA.AddScheme;
import MFPojo.TwoFA.GetCart;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAPI_Verify_Test {
    RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri(BaseURL.staging)
            .addHeader("x-api-version", "2.0")
            .addHeader("channel-id", "10")
            .addHeader("x-fi-access-token", Live_Login.sathish())
            .setContentType(ContentType.JSON).build();
    ResponseSpecification respec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON).build();

//Local DATA
//String Holdingid;       String Expected_HoldID = "179605";      String InvestorId;

//Live Data
    String Holdingid;       String Expected_HoldID = "1403821";  String InvestorId;     //sathish
 //  String Holdingid;       String Expected_HoldID = "935406";  String InvestorId;   // Saravanan
    String CartId; String GroupId; String otprefid;

@Test(priority = 0)
    public void Holding_Profile() {
    RequestSpecification res = given().spec(req);
    HoldingProfile.Root hold_response= res.when().get("/core/investor/holding-profiles")
            .then().log().all().spec(respec).extract().response().as(HoldingProfile.Root.class);
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
                System.out.println(Holdingid);
                System.out.println(InvestorId);

            }
        }
    }
    }
    @Test(priority = 1)
    public void Add_Schecm()
    {
        String payload="{\n" +
                "  \"product\": \"MF\",\n" +
                "  \"id\": \"\",\n" +
                "  \"appInfo\": {\n" +
                "    \"os\": \"Web-FI\",\n" +
                "    \"fcmId\": \"\"\n" +
                "  },\n" +
                "  \"investorName\": \"Sathish D\",\n" +
                "  \"investorId\": \""+InvestorId+"\",\n" +
                "  \"holdingProfileId\": \""+Holdingid+"\",\n" +
                "  \"holdingProfileName\": \"Test\",\n" +
                "  \"holdingProfilePan\": \"OEXPX9571B\",\n" +
                "  \"mf\": {\n" +
                "    \"oti\": {\n" +
                "      \"totalAmount\": 1000,\n" +
                "      \"investmentType\": \"oti\",\n" +
                "      \"paymentId\": \"\",\n" +
                "      \"schemes\": [\n" +
                "        {\n" +
                "          \"folio\": \"-\",\n" +
                "          \"bankId\": \"1\",\n" +
                "          \"id\": \"cGUh9pdR30wB4jQHAl2Jh\",\n" +
                "          \"payment\": true,\n" +
                "          \"option\": \"Growth\",\n" +
                "          \"goalId\": \"2932872\",\n" +
                "          \"schemeCode\": \"453\",\n" +
                "          \"schemeName\": \"Aditya Birla SL Corp Bond Fund(G)\",\n" +
                "          \"amount\": 1000,\n" +
                "          \"sipType\": \"\",\n" +
                "          \"sipDate\": 0\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        RequestSpecification res = given().spec(req)
                .body(payload);
        AddScheme.Root response=res.when().post("/core/investor/cart")
                .then().log().all().spec(respec).extract().response().as(AddScheme.Root.class);
        CartId= response.getData().getCartId();
        System.out.println(CartId);
    }
    @Test(priority = 2)
    public void Get_Cart() {
        RequestSpecification getres = given().spec(req)
                .queryParam("cartId", CartId);
        GetCart.Root response=getres.when().get("/core/investor/cart/folio-groups")
                .then().log().all().spec(respec).extract().response().as(GetCart.Root.class);
        GroupId=response.getData().getGroups().get(0).getGroupId();
        System.out.println(GroupId);
    }
}


