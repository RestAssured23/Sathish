package API_Collection.Nominee;

import java.util.HashMap;
import java.util.Map;

public class payload {

    public static String Optout="{\"" +
            "holdingProfileId\":\"181558\"," +
            "\"optedOut\":true" +
            "}";
    public static String single()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Test A\",\n" +
                "      \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
    public static String single_guardian()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"FirstName\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"FS\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Test A\",\n" +
                "        \"lastName\": \"\",\n" +
                "        \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"Father\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
    public static String two()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"First\",\n" +
                "      \"lastName\": \"F\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"percentage\": 50,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Test A\",\n" +
                "      \"lastName\": \"\",\n" +
                "      \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "      \"percentage\": 50,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
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
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 40\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Second\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"S\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 30\n" +
                "    },\n" +
                "    {\n" +
                "      \"firstName\": \"Test A\",\n" +
                "      \"lastName\": \"\",\n" +
                "      \"dateOfBirth\": \"2000-10-10T00:00:00.000+0530\",\n" +
                "      \"percentage\": 30,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
    public static String AcceptedCases(){   // With two Same Guardian
        return "{\n" +
                "  \"holdingProfileId\": \"181557\",\n" +
                "  \"optedOut\": false,\n" +
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

    public static String live_single()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"1540585\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"Demo One\",\n" +
                "      \"dateOfBirth\": \"1962-11-20T00:00:00.000+0530\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }

    public static String live_single_guardian()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"1540585\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"FirstName\",\n" +
                "      \"middleName\": \"\",\n" +
                "      \"lastName\": \"\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"relationship\": \"Brother\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"guardian\": {\n" +
                "        \"firstName\": \"Demo One\",\n" +
                "        \"lastName\": \"\",\n" +
                "        \"dateOfBirth\": \"1962-11-20T00:00:00.000+0530\",\n" +
                "        \"relationship\": \"Father\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }


}
