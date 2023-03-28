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
                "  \"investorId\": \"169541\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Shreya Udayakumar shett\",\n" +
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Daughter\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"401, Laxmi Height, Prabhakar Dattu Mhatre Marg, Bhayander East\",\n" +
                "        \"city\": \"Thane\",\n" +
                "        \"cityOthers\": \"\",\n" +
                "        \"cityId\": \"\",\n" +
                "        \"stateId\": \"\",\n" +
               "        \"countryId\": \"\",\n" +
                "        \"state\": \"Maharashtra\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"400105\",\n" +
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
                "      \"firstName\": \"Shreya Udayakumar shett\",\n" +
                "      \"dateOfBirth\": \"2022-04-01T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"address\": {\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"401, Laxmi Height, Prabhakar Dattu Mhatre Marg, Bhayander East\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"Thane\",\n" +
                "        \"cityId\": \" \",\n" +
                "        \"stateId\": \" \",\n" +
                "        \"countryId\": \" \",\n" +
                "        \"state\": \"Maharashtra\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"400105\",\n" +
                "        \"landmark\": \" \"\n" +
                "      },\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                "        \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "  \"investorId\": \"169541\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish D\",\n" +
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "  \"investorId\": \"169541\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish D\",\n" +
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "      \"firstName\": \"Sathish S\",\n" +
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "  \"investorId\": \"169541\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Saravanan Elumalai\",\n" +
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
                "        \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
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
public static String test(){
    return "{\n" +
            "  \"investorId\": \"169541\",\n" +
            "  \"holdingProfileId\": \"\",\n" +
            "  \"declarationType\": \"opt-in\",\n" +
            "  \"processType\": \"online\",\n" +
            "  \"otpReferenceId\": \"NA\",\n" +
            "  \"nominees\": [\n" +
            "    {\n" +
            "      \"firstName\": \"Shreya Udayakumar shett\",\n" +
            "      \"middleName\": \"\",\n" +
            "      \"lastName\": \"\",\n" +
            "      \"dateOfBirth\": \"2004-04-01T00:00:00.000+0530\",\n" +
            "      \"relationship\": \"03\",\n" +
            "      \"email\": \"\",\n" +
            "      \"mobile\": \"\",\n" +
            "      \"salutation\": \"\",\n" +
            "      \"nomineeId\": \"\",\n" +
            "      \"address\": {\n" +
            "        \"addressId\": \"\",\n" +
            "        \"addressType\": \"communication\",\n" +
            "        \"addressLine1\": \"401, Laxmi Height, Prabhakar Dattu Mhatre Marg, Bhayander East \",\n" +
            "        \"addressLine2\": \"\",\n" +
            "        \"city\": \"Thane\",\n" +
            "        \"cityOthers\": \"\",\n" +
            "        \"cityId\": \"\",\n" +
            "        \"stateId\": \"\",\n" +
            "        \"countryId\": \"\",\n" +
            "        \"state\": \"Maharashtra\",\n" +
            "        \"country\": \"India\",\n" +
            "        \"pincode\": \"400105\",\n" +
            "        \"landmark\": \"\"\n" +
            "      },\n" +
            "      \"guardian\": {\n" +
            "        \"firstName\": \"\",\n" +
            "        \"middleName\": \"\",\n" +
            "        \"lastName\": \"\",\n" +
            "        \"dateOfBirth\": \"\",\n" +
            "        \"relationship\": \"\",\n" +
            "        \"relationCode\": \"\",\n" +
            "        \"gender\": \"\",\n" +
            "        \"email\": \"\",\n" +
            "        \"mobile\": \"\",\n" +
            "        \"salutation\": \"\",\n" +
            "        \"address\": {\n" +
            "          \"addressId\": \"\",\n" +
            "          \"addressType\": \"communication\",\n" +
            "          \"addressLine1\": \"\",\n" +
            "          \"addressLine2\": \"\",\n" +
            "          \"city\": \"\",\n" +
            "          \"cityOthers\": \"\",\n" +
            "          \"cityId\": \"\",\n" +
            "          \"stateId\": \"\",\n" +
            "          \"countryId\": \"\",\n" +
            "          \"state\": \"\",\n" +
            "          \"country\": \"\",\n" +
            "          \"pincode\": \"\",\n" +
            "          \"landmark\": \"\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"percentage\": 100\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}

}
