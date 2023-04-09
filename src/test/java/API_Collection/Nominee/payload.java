package API_Collection.Nominee;

import java.util.HashMap;
import java.util.Map;

public class payload {

    public static String Optout="{\"" +
            "holdingProfileId\":\"181556\"," +
            "\"optedOut\":true" +
            "}";
    public static String single()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"1540585\",\n" +
                "  \"optedOut\": false,\n" +
                "  \"nominees\": [\n" +
                "    {\n" +
                "      \"firstName\": \"New Nominee test\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
                "      \"percentage\": 100,\n" +
                "      \"relationship\": \"Brother\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
    public static String single_guardian()
    {
        return "{\n" +
                "  \"holdingProfileId\": \"181558\",\n" +
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
                "        \"firstName\": \"Guardian\",\n" +
                "        \"lastName\": \"G\",\n" +
                "        \"dateOfBirth\": \"02/03/1997\",\n" +
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
                "      \"firstName\": \"Second\",\n" +
                "      \"lastName\": \"S\",\n" +
                "      \"dateOfBirth\": \"2000-12-12T00:00:00.000+0530\",\n" +
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
