package com.dwp.controller;

import com.dwp.dto.User;
import com.dwp.utils.DistanceCalculatorUtils;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@RestController
public class ApplicationController {
    @Value("${base.url}") private String baseUrl;
    @Value("${distance.miles:50}") private int distance;
    private RestTemplate restTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    public ApplicationController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/users/city/{city}")
    public @ResponseBody List<User> getUsers(@PathVariable String city) {
        List<User> usersInCity = getUsersInCity(city);
        List<User> usersInAndAroundCity = getUsersAroundCity();
        usersInAndAroundCity.addAll(usersInCity);

        return usersInAndAroundCity;
    }

    private List<User> getUsersInCity(String city) throws RestClientException {
        String urlUsersInCity = String.format("%s/city/%s/users", baseUrl, WordUtils.capitalizeFully(city));
        LOGGER.debug("Calling: " + urlUsersInCity);
        return asList(restTemplate.getForObject(urlUsersInCity, User[].class));
    }

    private List<User> getUsersAroundCity() throws RestClientException {
        LOGGER.debug("Getting all users");
        User[] usersInRadius = restTemplate.getForObject(baseUrl + "/users", User[].class);
        return Arrays.stream(usersInRadius)
                .filter(user -> DistanceCalculatorUtils.isWithinXMilesOfLondon(user.getLatitude(), user.getLongitude(), distance))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity handleRestExceptions(RestClientException exception) {
        LOGGER.error("Unexpected client error", exception);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleClientExceptions(HttpClientErrorException exception) {
        LOGGER.error("Client error", exception);
        return new ResponseEntity(HttpStatus.valueOf(exception.getRawStatusCode()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity handleServerExceptions(HttpServerErrorException exception) {
        LOGGER.error("Server error", exception);
        return new ResponseEntity(HttpStatus.valueOf(exception.getRawStatusCode()));
    }
}