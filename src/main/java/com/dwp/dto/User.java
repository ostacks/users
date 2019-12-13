package com.dwp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private double longitude;
    private double latitude;
    private String ip_address;

    @JsonCreator(mode = Mode.PROPERTIES)
    public User (@JsonProperty("id") long id,
                 @JsonProperty("first_name") String first_name,
                 @JsonProperty("last_name") String last_name,
                 @JsonProperty("email") String email,
                 @JsonProperty("longitude") double longitude,
                 @JsonProperty("latitude") double latitude,
                 @JsonProperty("ip_address") String ip_address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.longitude = longitude;
        this.latitude = latitude;
        this.ip_address = ip_address;
    }

    public long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getIp_address() {
        return ip_address;
    }
}