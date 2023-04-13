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
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"TestA\",\n" +
                "      \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"amc\": \"400013\",\n" +
                "      \"folioNo\": \"1234/458\"\n" +
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
    public static String test(){
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"holdingProfileId\": \"\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"otpReferenceId\": \"NA\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Ritu Sikarwar\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"\",\n" +
                "      \"dateOfBirth\": \"2005-09-15T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"03\",\n" +
                "      \"email\": \"\",\n" +
                "      \"mobile\": \"\",\n" +
                "      \"salutation\": \"\",\n" +
                "      \"nomineeId\": \"\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"RZ-33A, Hans Park, West Sagar Pur\",\n" +
                "        \"addressLine2\": \"\",\n" +
                "        \"city\": \"New Delhi\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"New Delhi\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"110046\",\n" +
                "        \"landmark\": \"\"\n" +
                "      },\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Seema SinghA\",\n" +
                "        \"middleName\": \"\",\n" +
                "        \"lastName\": \"\",\n" +
                "        \"dateOfBirth\": \"1983-07-14T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"05\",\n" +
                "        \"email\": \"\",\n" +
                "        \"mobile\": \"\",\n" +
                "        \"salutation\": \"\",\n" +
                "        \"address\": {\n" +
                "          \"addressId\": \"\",\n" +
                "          \"addressType\": \"communication\",\n" +
                "          \"addressLine1\": \"RZ-33A, Hans Park, West Sagar Pur\",\n" +
                "          \"addressLine2\": \"\",\n" +
                "          \"city\": \"New Delhi\",\n" +
                "          \"cityOthers\": \"\",\n" +
                "          \"cityId\": \"\",\n" +
                "          \"stateId\": \"\",\n" +
                "          \"countryId\": \"\",\n" +
                "          \"state\": \"New Delhi\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"110046\",\n" +
                "          \"landmark\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Riya Sikarwar\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"\",\n" +
                "      \"dateOfBirth\": \"2007-11-27T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"03\",\n" +
                "      \"email\": \"\",\n" +
                "      \"mobile\": \"\",\n" +
                "      \"salutation\": \"\",\n" +
                "      \"nomineeId\": \"\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"RZ-33A, Hans Park, West Sagar Pur\",\n" +
                "        \"addressLine2\": \"\",\n" +
                "        \"city\": \"New Delhi\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"New Delhi\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"110046\",\n" +
                "        \"landmark\": \"\"\n" +
                "      },\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Seema Singh\",\n" +
                "        \"middleName\": \"\",\n" +
                "        \"lastName\": \"\",\n" +
                "        \"dateOfBirth\": \"1983-07-14T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"05\",\n" +
                "        \"email\": \"\",\n" +
                "        \"mobile\": \"\",\n" +
                "        \"salutation\": \"\",\n" +
                "        \"address\": {\n" +
                "          \"addressId\": \"\",\n" +
                "          \"addressType\": \"communication\",\n" +
                "          \"addressLine1\": \"RZ-33A, Hans Park, West Sagar Pur\",\n" +
                "          \"addressLine2\": \"\",\n" +
                "          \"city\": \"New Delhi\",\n" +
                "          \"cityOthers\": \"\",\n" +
                "          \"cityId\": \"\",\n" +
                "          \"stateId\": \"\",\n" +
                "          \"countryId\": \"\",\n" +
                "          \"state\": \"New Delhi\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"110046\",\n" +
                "          \"landmark\": \"\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }



}
