package com.dwp.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.List;

import static com.dwp.data.TestData.*;
import static org.junit.Assert.assertEquals;

public class UserTest {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @BeforeClass
    public static void setUp(){
        objectMapper.registerModule(new Jdk8Module());
    }

    @Test
    public void willCorrectlyDeserialise() throws JsonProcessingException {
        List<User> users = Arrays.asList(objectMapper.readValue(userJson, User[].class));

        assertEquals(2, users.size());
        assertEquals(1, users.get(0).getId());
        assertEquals("Bendix", users.get(1).getFirst_name());
    }

    @Test
    public void willCorrectlySerialise() throws JsonProcessingException, JSONException {
        String serialisedUsers = objectMapper.writeValueAsString(Arrays.asList(user1, user2));
        JSONAssert.assertEquals(userJson, serialisedUsers, false);
    }
}
