package API_Collection.Nominee;

import java.util.HashMap;
import java.util.Map;

public class eqt_payload {

    public static String Optout="{\n" +
            "  \"investorId\": \"177973\",\n" +
            "  \"declarationType\": \"opt-out\",\n" +
            "  \"processType\": \"online\"\n" +
            "}";

    public static String single()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"05/12/1989\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
               "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"1AB67890\",\n" +
               "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }

    public static String Guardian()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"13/05/2022\",\n" +
                "      \"relationship\": \"self\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"Erode\",\n" +
                "        \"cityId\": \"string\",\n" +
                "        \"stateId\": \"string\",\n" +
                "        \"countryId\": \"string\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600001\",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                "        \"dateOfBirth\": \"13/05/2000\",\n" +
                "        \"relationship\": \"self\",\n" +
                "        \"address\": {\n" +
                "          \"addressType\": \"permanent\",\n" +
                "          \"addressLine1\": \"rtyui\",\n" +
                "          \"city\": \"Chennai\",\n" +
                "          \"cityOthers\": \"Salem\",\n" +
                "          \"cityId\": \"string\",\n" +
                "          \"stateId\": \"string\",\n" +
                "          \"countryId\": \"string\",\n" +
                "          \"state\": \"Tamilnadu\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"600002\",\n" +
                "          \"landmark\": \" \"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }


    public static String two()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"12/05/2000\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"6000012 \",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    },\n" +
                "{\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"13/05/2000\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"6000012 \",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }

    public static String three()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish D\",\n" +
                "      \"dateOfBirth\": \"12/05/2000\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"6000012 \",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\":30\n" +
                "    },\n" +

                "{\n" +                                                           //second Nominee
                "      \"firstName\": \"Sathish Dhamodharan\",\n" +
                "      \"dateOfBirth\": \"12/05/2000\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"6000012 \",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 20\n" +
                "    },\n" +

                "{\n" +                                                            // Third Nominee
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"12/05/2000\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"6000012 \",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }

    public static String sameinvestorname()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Saravanan Elumalai\",\n" +
                "      \"dateOfBirth\": \"05/12/1979\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
                "        \"countryId\": \"\",\n" +
                "        \"state\": \"TamilNadu\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600we22w\",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }

    public static String Guardianwithsameinvestorname()
    {
        return "{\n" +
                "  \"investorId\": \"177973\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"dateOfBirth\": \"05/12/2022\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"Erode\",\n" +
                "        \"cityId\": \"string\",\n" +
                "        \"stateId\": \"string\",\n" +
                "        \"countryId\": \"string\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600001\",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Saravanan Elumalai\",\n" +
                "        \"dateOfBirth\": \"05/12/1979\",\n" +
                "        \"relationship\": \"Brother\",\n" +
                "        \"address\": {\n" +
                "          \"addressType\": \"permanent\",\n" +
                "          \"addressLine1\": \"rtyui\",\n" +
                "          \"city\": \"Chennai\",\n" +
                "          \"cityOthers\": \"Salem\",\n" +
                "          \"cityId\": \"string\",\n" +
                "          \"stateId\": \"string\",\n" +
                "          \"countryId\": \"string\",\n" +
                "          \"state\": \"Tamilnadu\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"600002\",\n" +
                "          \"landmark\": \" \"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 100\n" +
                "    }\n" +
                "  ],\n" +
                "  \"otpReferenceId\": \"NA\"\n" +
                "}";
    }


}
