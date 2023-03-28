package API_Collection.Nominee;

public class ExistingPayload {

    public static String OptOut(){
        return "{\n" +
                "  \"holdingProfileId\": \"181559\",\n" +
          //      "  \"optedOut\": true,\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400004\",\n" +
                "      \"amcCode\": \"400004\",\n" +
                "      \"folioNo\": \"0123456794\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-out\",\n" +
                "  \"processType\": \"online\"\n" +
                "}";
    }
    public static String One_Nomoinee(){
        return "{\n" +
                "  \"holdingProfileId\": \"181559\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"sathish\",\n" +
                "      \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400015\",\n" +
                "      \"folioNo\": \"34649/46464\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";

    }
    public static String Guardian(){
        return "{\n" +
                "  \"holdingProfileId\": \"181559\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish Test Tesgvsajsa\",\n" +
                "      \"dateOfBirth\": \"2022-10-10T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Grand Daughter\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"TestB\",\n" +
                "        \"dateOfBirth\": \"2000-11-11T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"Brother\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400015\",\n" +
           //     "      \"amcCode\": \"400013\",\n" +
                "      \"folioNo\": \"34649/46464\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";

    }
    public static String Two_Nominee() {
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"sathish\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 50\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"sathish\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Mother\",\n" +
                "      \"percentage\": 50\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400013\",\n" +
                "      \"amcCode\": \"400013\",\n" +
                "      \"folioNo\": \"1234/461\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";
    }

    public static String Three_Nominee() {
    return "{\n" +
            "  \"holdingProfileId\": \"181557\",\n" +
            "  \"optedOut\": false,\n" +
            "  \"nominees\": [\n" +
            "    {\n" +
            "      \"firstName\": \"Third Nominee\",\n" +
            "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
            "      \"relationship\": \"Mother\",\n" +
            "      \"percentage\": 50\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstName\": \"Third Nominee\",\n" +
            "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
            "      \"relationship\": \"Brother\",\n" +
            "      \"percentage\": 30\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstName\": \"Third Nominee\",\n" +
            "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
            "      \"relationship\": \"Mother\",\n" +
            "      \"percentage\": 20\n" +
            "    }\n" +
            "  ],\n" +
            "  \"folios\": [\n" +
            "    {\n" +
            "      \"amc\": \"400013\",\n" +
            "      \"amcCode\": \"400013\",\n" +
            "      \"folioNo\": \"1234/461\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"processType\": \"online\",\n" +
            "  \"declarationType\": \"opt-in\"\n" +
            "}";
    }
    public static String twonomineewithsameguardian(){
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"dateOfBirth\": \"2022-10-10T00:00:00.000+0530\",\n" +
                "      \"firstName\": \"ABC\",\n" +
                "      \"relationship\": \"Daughter\",\n" +
                "      \"percentage\": 50,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Guardian\",\n" +
                "        \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"Mother\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"dateOfBirth\": \"2022-12-12T00:00:00.000+0530\",\n" +
                "      \"firstName\": \"cde\",\n" +
                "      \"relationship\": \"Son\",\n" +
                "      \"percentage\": 50,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Guardian\",\n" +
                "        \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"Mother\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400013\",\n" +
                "      \"amcCode\": \"400013\",\n" +
                "      \"folioNo\": \"1234/458\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";

    }
}
