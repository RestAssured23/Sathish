package API_Collection.Nominee;

public class ExistingPayload {

    public static String OptOut(){
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": true,\n" +
                "  \"folios\": [\n" +
                "    {\n" +
          //      "      \"amc\": \"400004\",\n" +
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
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"sathish\",\n" +
                "      \"dateOfBirth\": \"02/02/1981\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400015\",\n" +
                "      \"amcCode\": \"400015\",\n" +
                "      \"folioNo\": \"34649/46464\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";

    }
    public static String Guardian(){
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish Test Tesgvsajsa\",\n" +
                "      \"dateOfBirth\": \"10/10/2022\",\n" +
                "      \"relationship\": \"Grand Daughter\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Guardian Details\",\n" +
                "        \"dateOfBirth\": \"10/10/2000\",\n" +
                "        \"relationship\": \"Brother\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400013\",\n" +
           //     "      \"amcCode\": \"400013\",\n" +
                "      \"folioNo\": \"1234/461\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";

    }
    public static String Two_Nominee() {
        return "{\n" +
                "  \"holdingProfileId\": \"181558\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"sathish\",\n" +
                "      \"dateOfBirth\": \"02/02/1981\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 50\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Second Nominee\",\n" +
                "      \"dateOfBirth\": \"01/01/1964\",\n" +
                "      \"relationship\": \"Mother\",\n" +
                "      \"percentage\": 50\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400004\",\n" +
                "      \"amcCode\": \"400004\",\n" +
                "      \"folioNo\": \"0123456794\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"processType\": \"online\",\n" +
                "  \"declarationType\": \"opt-in\"\n" +
                "}";
    }

    public static String Three_Nominee() {
    return "{\n" +
            "  \"holdingProfileId\": \"181558\",\n" +
            "  \"optedOut\": false,\n" +
            "  \"nominees\": [\n" +
            "    {\n" +
            "      \"firstName\": \"Second Nominee\",\n" +
            "      \"dateOfBirth\": \"01/01/1964\",\n" +
            "      \"relationship\": \"Mother\",\n" +
            "      \"percentage\": 50\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstName\": \"sathish\",\n" +
            "      \"dateOfBirth\": \"02/02/1981\",\n" +
            "      \"relationship\": \"Brother\",\n" +
            "      \"percentage\": 30\n" +
            "    },\n" +
            "    {\n" +
            "      \"firstName\": \"Third Nominee\",\n" +
            "      \"dateOfBirth\": \"08/01/1985\",\n" +
            "      \"relationship\": \"Mother\",\n" +
            "      \"percentage\": 20\n" +
            "    }\n" +
            "  ],\n" +
            "  \"folios\": [\n" +
            "    {\n" +
            "      \"amc\": \"400004\",\n" +
            "      \"amcCode\": \"400004\",\n" +
            "      \"folioNo\": \"0123456794\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"processType\": \"online\",\n" +
            "  \"declarationType\": \"opt-in\"\n" +
            "}";
    }

}
