package com.dwp.data;

import com.dwp.dto.User;

public class TestData {
    public static final String URL_IN_LONDON = "https://bpdts-test-app.herokuapp.com/city/London/users";
    public static final String URL_GET_ALL_USERS = "https://bpdts-test-app.herokuapp.com/users";
    public static final String APPLICATION_URL = "/users/city/london";
    public static String userJson = "[ \n" +
            "   { \n" +
            "      \"id\":1,\n" +
            "      \"first_name\":\"Maurise\",\n" +
            "      \"last_name\":\"Shieldon\",\n" +
            "      \"email\":\"mshieldon0@squidoo.com\",\n" +
            "      \"ip_address\":\"192.57.232.111\",\n" +
            "      \"latitude\":34.003135,\n" +
            "      \"longitude\":-117.7228641\n" +
            "   },\n" +
            "   { \n" +
            "      \"id\":2,\n" +
            "      \"first_name\":\"Bendix\",\n" +
            "      \"last_name\":\"Halgarth\",\n" +
            "      \"email\":\"bhalgarth1@timesonline.co.uk\",\n" +
            "      \"ip_address\":\"4.185.73.82\",\n" +
            "      \"latitude\":51.376495,\n" +
            "      \"longitude\":-0.100594\n" +
            "   }\n" +
            "]";

    public static User user1 = new User(1, "Maurise", "Shieldon", "mshieldon0@squidoo.com",
            -117.7228641, 34.003135, "192.57.232.111");
    public static User user2 = new User(2, "Bendix", "Halgarth", "bhalgarth1@timesonline.co.uk",
            -0.100594, 51.376495, "4.185.73.82");
    public static User[] arrayUser1=new User[]{TestData.user1};
    public static User[] arrayUser2=new User[]{TestData.user2};
}
