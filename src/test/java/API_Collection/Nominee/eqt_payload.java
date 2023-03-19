package API_Collection.Nominee;

import java.util.HashMap;
import java.util.Map;

public class eqt_payload {

    public static String Optout="{\n" +
            "  \"investorId\": \"7\",\n" +
            "  \"declarationType\": \"opt-out\",\n" +
            "  \"processType\": \"online\"\n" +
            "}";
    public static String single()
    {


       return "{\n" +
               "  \"investorId\": \"169541\",\n" +
          //    "  \"holdingProfileId\": \"string\",\n" +
               "  \"declarationType\": \"opt-in\",\n" +
               "  \"processType\": \"online\",\n" +
               "  \"nominees\": [\n" +
               "    {\n" +
               "      \"firstName\": \"Sathish\",\n" +
          //     "      \"middleName\": \"R\",\n" +
          //     "      \"lastName\": \"R\",\n" +
               "      \"dateOfBirth\": \"12-05-1982\",\n" +
               "      \"relationship\": \"self\",\n" +
        //       "      \"gender\": \"male\",\n" +
         //      "      \"email\": \"abc@gmail.com\",\n" +
         //      "      \"mobile\": \"999999999\",\n" +
        //       "      \"salutation\": \"Mr\",\n" +
               "      \"address\": {\n" +
         //      "        \"addressId\": \"string\",\n" +
         //      "        \"addressType\": \"communication\",\n" +
               "        \"addressLine1\": \"address1\",\n" +
          //     "        \"addressLine2\": \"address2\",\n" +
               "        \"city\": \"Chennai\",\n" +
         //      "        \"cityOthers\": \"Erode\",\n" +
        //       "        \"cityId\": \"string\",\n" +
         //      "        \"stateId\": \"string\",\n" +
         //     "        \"countryId\": \"string\",\n" +
               "        \"state\": \"TN\",\n" +
               "        \"country\": \"India\",\n" +
               "        \"pincode\": \"600001\",\n" +
         //      "        \"landmark\": \"Park\"\n" +
               "      },\n" +
       //        "      \"nomineeId\": \"string\",\n" +
               "      \"guardian\": {\n" +
               "        \"firstName\": \"abc\",\n" +
        //       "        \"middleName\": \"string\",\n" +
         //      "        \"lastName\": \"cde\",\n" +
               "        \"dateOfBirth\": \"12-02-1800\",\n" +
               "        \"relationship\": \"self\",\n" +
       //        "        \"gender\": \"male\",\n" +
        //       "        \"email\": \"gdc@gmail.com\",\n" +
        //       "        \"mobile\": \"1234567899\",\n" +
        //       "        \"salutation\": \"Mr\",\n" +
               "        \"address\": {\n" +
       //        "          \"addressId\": \"string\",\n" +
       //        "          \"addressType\": \"permanent\",\n" +
               "          \"addressLine1\": \"rtyui\",\n" +
        //       "          \"addressLine2\": \"adfghjj\",\n" +
               "          \"city\": \"Chennai\",\n" +
       //        "          \"cityOthers\": \"Salem\",\n" +
       //        "          \"cityId\": \"string\",\n" +
        //       "          \"stateId\": \"string\",\n" +
        //       "          \"countryId\": \"string\",\n" +
               "          \"state\": \"Tamilnadu\",\n" +
               "          \"country\": \"India\",\n" +
               "          \"pincode\": \"600002\",\n" +
        //       "          \"landmark\": \"bustand\"\n" +
               "        }\n" +
               "      },\n" +
               "      \"percentage\": 50\n" +
               "    }\n" +
               "  ],\n" +
    //           "  \"otpReferenceId\": \"NA\"\n" +
               "}";
    }

    public static String test()
    {
        return "{\n" +
                "  \"investorId\": \"12\",\n" +
                //    "  \"holdingProfileId\": \"string\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                    "      \"middleName\": \"R\",\n" +
                    "      \"lastName\": \"R\",\n" +
                "      \"dateOfBirth\": \"12-05-1982\",\n" +
                "      \"relationship\": \"self\",\n" +
                       "      \"gender\": \"male\",\n" +
                     "      \"email\": \"abc@gmail.com\",\n" +
                     "      \"mobile\": \"999999999\",\n" +
                      "      \"salutation\": \"Mr\",\n" +
                "      \"address\": {\n" +
                     "        \"addressId\": \"string\",\n" +
                      "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                     "        \"addressLine2\": \"address2\",\n" +
                "        \"city\": \"Chennai\",\n" +
                     "        \"cityOthers\": \"Erode\",\n" +
                       "        \"cityId\": \"string\",\n" +
                      "        \"stateId\": \"string\",\n" +
                     "        \"countryId\": \"string\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600001\",\n" +
                      "        \"landmark\": \"Park\"\n" +
                "      },\n" +
                //        "      \"nomineeId\": \"string\",\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                      "        \"middleName\": \"string\",\n" +
                      "        \"lastName\": \"cde\",\n" +
                "        \"dateOfBirth\": \"12-02-1800\",\n" +
                "        \"relationship\": \"self\",\n" +
                       "        \"gender\": \"male\",\n" +
                       "        \"email\": \"gdc@gmail.com\",\n" +
                       "        \"mobile\": \"1234567899\",\n" +
                       "        \"salutation\": \"Mr\",\n" +
                "        \"address\": {\n" +
                      "          \"addressId\": \"string\",\n" +
                        "          \"addressType\": \"permanent\",\n" +
                "          \"addressLine1\": \"rtyui\",\n" +
                       "          \"addressLine2\": \"adfghjj\",\n" +
                "          \"city\": \"Chennai\",\n" +
                        "          \"cityOthers\": \"Salem\",\n" +
                        "          \"cityId\": \"string\",\n" +
                       "          \"stateId\": \"string\",\n" +
                       "          \"countryId\": \"string\",\n" +
                "          \"state\": \"Tamilnadu\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"600002\",\n" +
                       "          \"landmark\": \"bustand\"\n" +
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
                "  \"investorId\": \"6\",\n" +
                //    "  \"holdingProfileId\": \"string\",\n" +
                "  \"declarationType\": \"opt-in\",\n" +
                "  \"processType\": \"online\",\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"middleName\": \"R\",\n" +
                "      \"lastName\": \"R\",\n" +
                "      \"dateOfBirth\": \"12-05-1982\",\n" +
                "      \"relationship\": \"self\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"email\": \"abc@gmail.com\",\n" +
                "      \"mobile\": \"999999999\",\n" +
                "      \"salutation\": \"Mr\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"string\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"addressLine2\": \"address2\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"Erode\",\n" +
                "        \"cityId\": \"string\",\n" +
                "        \"stateId\": \"string\",\n" +
                "        \"countryId\": \"string\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600001\",\n" +
                "        \"landmark\": \"Park\"\n" +
                "      },\n" +
                //        "      \"nomineeId\": \"string\",\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                "        \"middleName\": \"string\",\n" +
                "        \"lastName\": \"cde\",\n" +
                "        \"dateOfBirth\": \"12-02-1800\",\n" +
                "        \"relationship\": \"self\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"email\": \"gdc@gmail.com\",\n" +
                "        \"mobile\": \"1234567899\",\n" +
                "        \"salutation\": \"Mr\",\n" +
                "        \"address\": {\n" +
                "          \"addressId\": \"string\",\n" +
                "          \"addressType\": \"permanent\",\n" +
                "          \"addressLine1\": \"rtyui\",\n" +
                "          \"addressLine2\": \"adfghjj\",\n" +
                "          \"city\": \"Chennai\",\n" +
                "          \"cityOthers\": \"Salem\",\n" +
                "          \"cityId\": \"string\",\n" +
                "          \"stateId\": \"string\",\n" +
                "          \"countryId\": \"string\",\n" +
                "          \"state\": \"Tamilnadu\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"600002\",\n" +
                "          \"landmark\": \"bustand\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"percentage\": 50\n" +
                "    },\n" +

                "    {\n" +
                "      \"firstName\": \"Sathish\",\n" +
                "      \"middleName\": \"R\",\n" +
                "      \"lastName\": \"R\",\n" +
                "      \"dateOfBirth\": \"12-05-1982\",\n" +
                "      \"relationship\": \"self\",\n" +
                "      \"gender\": \"male\",\n" +
                "      \"email\": \"abc@gmail.com\",\n" +
                "      \"mobile\": \"999999999\",\n" +
                "      \"salutation\": \"Mr\",\n" +
                "      \"address\": {\n" +
                "        \"addressId\": \"string\",\n" +
                "        \"addressType\": \"communication\",\n" +
                "        \"addressLine1\": \"address1\",\n" +
                "        \"addressLine2\": \"address2\",\n" +
                "        \"city\": \"Chennai\",\n" +
                "        \"cityOthers\": \"Erode\",\n" +
                "        \"cityId\": \"string\",\n" +
                "        \"stateId\": \"string\",\n" +
                "        \"countryId\": \"string\",\n" +
                "        \"state\": \"TN\",\n" +
                "        \"country\": \"India\",\n" +
                "        \"pincode\": \"600001\",\n" +
                "        \"landmark\": \"Park\"\n" +
                "      },\n" +
                //        "      \"nomineeId\": \"string\",\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"abc\",\n" +
                "        \"middleName\": \"string\",\n" +
                "        \"lastName\": \"cde\",\n" +
                "        \"dateOfBirth\": \"12-02-1800\",\n" +
                "        \"relationship\": \"self\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"email\": \"gdc@gmail.com\",\n" +
                "        \"mobile\": \"1234567899\",\n" +
                "        \"salutation\": \"Mr\",\n" +
                "        \"address\": {\n" +
                "          \"addressId\": \"string\",\n" +
                "          \"addressType\": \"permanent\",\n" +
                "          \"addressLine1\": \"rtyui\",\n" +
                "          \"addressLine2\": \"adfghjj\",\n" +
                "          \"city\": \"Chennai\",\n" +
                "          \"cityOthers\": \"Salem\",\n" +
                "          \"cityId\": \"string\",\n" +
                "          \"stateId\": \"string\",\n" +
                "          \"countryId\": \"string\",\n" +
                "          \"state\": \"Tamilnadu\",\n" +
                "          \"country\": \"India\",\n" +
                "          \"pincode\": \"600002\",\n" +
                "          \"landmark\": \"bustand\"\n" +
                "        }\n" +
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
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"First\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"F\",\n" +
                "      \"dateOfBirth\": \"03/03/1997\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 40\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Second\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"S\",\n" +
                "      \"dateOfBirth\": \"01/03/1999\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 30\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Third\",\n" +
                "      \"lastName\": \"t\",\n" +
                "      \"dateOfBirth\": \"03/03/1986\",\n" +
                "      \"percentage\": 30,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}
