package API_Collection.Nominee;

public class ExistingPayload {
    public static String OptOut(){
        return "{ \n" +
                "  \"holdingProfileId\": \"181596\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"0123456780\",\n" +
                "      \"amc\": \"400004\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-out\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"otpReferenceId\": \"\"\n" +
                "}";
    }
    public static String single(){
        return "{\n" +
                "  \"investorId\": \"\",\n" +
                "  \"holdingProfileId\": \"181559\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"34649/46464\",\n" +
                "      \"amc\": \"400015\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"aaa1\",\n" +
                "      \"dateOfBirth\": \"10/10/2000\",\n" +
                "      \"relationship\": \"brother\",\n" +
                "      \"percentage\": 100\n" +
                "    }    \n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"\"\n" +
                "}";

    }
    public static String sameinvestorname(){
        return "{\n" +
                "  \"investorId\": \"\",\n" +
                "  \"holdingProfileId\": \"181596\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"0123456780\",\n" +
                "      \"amc\": \"400004\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Joint One\",\n" +
                "      \"dateOfBirth\": \"12/12/2000\",\n" +
                "      \"relationship\": \"brother\",\n" +
                "      \"percentage\": 100\n" +
                "    }    \n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"\"\n" +
                "}";

    }
    public static String Guardian(){
        return "{\n" +
                "  \"investorId\": \"\",\n" +
                "  \"holdingProfileId\": \"181596\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"0123456780\",\n" +
                "      \"amc\": \"400004\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"aaa1\",\n" +
                "      \"dateOfBirth\": \"10/10/2022\",\n" +
                "      \"relationship\": \"brother\",\n" +
                "      \"percentage\": 100\n" +
                "    },\n" +
               "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                "        \"dateOfBirth\": \"10/10/2000\",\n" +
                "        \"relationship\": \"brother\",\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"\"\n" +
                "}";

    }


    public static String two(){
        return "{\n" +
                "  \n" +
                "  \"holdingProfileId\": \"string\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"0123456789\",\n" +
                "      \"amc\": \"400015\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"string\",\n" +
                "      \"middleName\": \"string\",\n" +
                "      \"lastName\": \"string\",\n" +
                "      \"dateOfBirth\": \"string\",\n" +
                "      \"relationship\": \"string\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"email\": \"string\",\n" +
                "      \"mobile\": \"string\",\n" +
                "      \"salutation\": \"string\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"string\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"string\",\n" +
                "        \"addressLine2\": \"string\",\n" +
                "        \"city\": \"string\",\n" +
                "        \"cityOthers\": \"string\",\n" +
                "        \"cityId\": \"string\",\n" +
                "        \"stateId\": \"string\",\n" +
                "        \"countryId\": \"string\",\n" +
                "        \"state\": \"string\",\n" +
                "        \"country\": \"string\",\n" +
                "        \"pincode\": \"string\",\n" +
                "        \"landmark\": \"string\"\n" +
                "      },\n" +
                "      \"nomineeId\": \"string\",\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"string\",\n" +
                "        \"middleName\": \"string\",\n" +
                "        \"lastName\": \"string\",\n" +
                "        \"dateOfBirth\": \"string\",\n" +
                "        \"relationship\": \"string\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"email\": \"string\",\n" +
                "        \"mobile\": \"string\",\n" +
                "        \"salutation\": \"string\",\n" +
                "        \"address\": {\n" +
                "          \"addressId\": \"string\",\n" +
                "          \"addressType\": \"communication\",\n" +
                "          \"addressLine1\": \"string\",\n" +
                "          \"addressLine2\": \"string\",\n" +
                "          \"city\": \"string\",\n" +
                "          \"cityOthers\": \"string\",\n" +
                "          \"cityId\": \"string\",\n" +
                "          \"stateId\": \"string\",\n" +
                "          \"countryId\": \"string\",\n" +
                "          \"state\": \"string\",\n" +
                "          \"country\": \"string\",\n" +
                "          \"pincode\": \"string\",\n" +
                "          \"landmark\": \"string\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"string\"\n" +
                "}";
    }
}
