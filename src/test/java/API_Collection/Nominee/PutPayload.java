package API_Collection.Nominee;

public class PutPayload {
    public static String OptOut(){
        return "{  \n" +
                "  \"holdingProfileId\": \"string\",\n" +
                "  \"folios\": [\n" +
                "    {\n" +
                "      \"folioNo\": \"0123456789\",\n" +
                "      \"amc\": \"400015\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"declarationType\": \"opt-out\",\n" +
                "  \"processType\": \"online\",  \n" +
                "  \"otpReferenceId\": \"string\"\n" +
                "}";
    }
    public static String single(){
        return "{\n" +
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
                "      \"firstName\": \"sathish\",     \n" +
                "      \"lastName\": \"d\",\n" +
                "      \"dateOfBirth\": \"05/03/1980\",\n" +
                "      \"relationship\": \"Brother\",         \n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"string\"\n" +
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
