package com.dwp.controller;

import com.dwp.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.dwp.data.TestData.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = ApplicationController.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class, ApplicationController.class})
@AutoConfigureMockMvc
public class ApplicationControllerTest {

    @MockBean
    private RestTemplate mockRestTemplate;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void callShouldReturnUsersInAnAroundLondon() throws Exception {
        when(mockRestTemplate.getForObject(eq(URL_IN_LONDON), any()))
                .thenReturn(arrayUser1);
        when(mockRestTemplate.getForObject(eq(URL_GET_ALL_USERS), any()))
                .thenReturn(arrayUser2);
        this.mockMvc.perform(get(APPLICATION_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));
        verify(mockRestTemplate, times(2)).getForObject(anyString(), any());
    }

    @Test
    public void dealWithHttpClientErrorException() throws Exception {
        when(mockRestTemplate.getForObject(eq(URL_IN_LONDON), any()))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "VERY BAD CLIENT ERROR!"));
        when(mockRestTemplate.getForObject(eq(URL_GET_ALL_USERS), any()))
                .thenReturn(arrayUser2);
        this.mockMvc.perform(get("/users/city/london"))
                .andExpect(status().isBadRequest());
        verify(mockRestTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    public void dealWithHttpServerErrorException() throws Exception {
        when(mockRestTemplate.getForObject(eq(URL_IN_LONDON), any()))
                .thenReturn(arrayUser1);
        when(mockRestTemplate.getForObject(eq(URL_GET_ALL_USERS), any()))
                .thenThrow(new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "VERY BAD SERVER ERROR!"));
        this.mockMvc.perform(get(APPLICATION_URL))
                .andExpect(status().isServiceUnavailable());
        verify(mockRestTemplate, times(2)).getForObject(anyString(), any());
    }

    @Test
    public void dealRestClientException() throws Exception {
        when(mockRestTemplate.getForObject(eq(URL_IN_LONDON), any()))
                .thenThrow(new RestClientException("Client error"));
        when(mockRestTemplate.getForObject(eq(URL_GET_ALL_USERS), any()))
                .thenReturn(arrayUser2);
        this.mockMvc.perform(get(APPLICATION_URL))
                .andExpect(status().isBadRequest());
        verify(mockRestTemplate, times(1)).getForObject(anyString(), any());
    }
}

