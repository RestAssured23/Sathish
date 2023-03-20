package API_Collection.Nominee;

public class ExistingPayload {
    public static String OptOut(){
        return "{ \n" +
                "  \"holdingProfileId\": \"182348\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"12345/11\",\n" +
                "      \"amc\": \"P\"\n" +
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
                "  \"holdingProfileId\": \"181554\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"5001\",\n" +
                "      \"amc\": \"B\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"aaa1\",\n" +
                "      \"middleName\": \"bbb1\",\n" +
                "      \"lastName\": \"ccc1\",\n" +
                "      \"dateOfBirth\": \"10/10/2000\",\n" +
                "      \"relationship\": \"brother\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"email\": \"abc@gmail.com\",\n" +
                "      \"mobile\": \"9999999999\",\n" +
                "      \"salutation\": \"Mr.\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"1\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"al1\",\n" +
                "        \"addressLine2\": \"al2\",\n" +
                "        \"city\": \"c\",\n" +
                "        \"cityOthers\": \"co\",\n" +
                "        \"cityId\": \"cid\",\n" +
                "        \"stateId\": \"sid\",\n" +
                "        \"countryId\": \"cid\",\n" +
                "        \"state\": \"s\",\n" +
                "        \"country\": \"c\",\n" +
                "        \"pincode\": \"000000\",\n" +
                "        \"landmark\": \"ckbcskcbk\"\n" +
                "      },\n" +
                "      \"nomineeId\": \"string\",\n" +
                "      \"percentage\": 100\n" +
                "    }    \n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"\"\n" +
                "}";

    }
    public static String sample(){
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
