package com.dwp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class ApplicationConfig {
    @Value("${connection.timeout.seconds:60}") private int connectionTimeout;
    @Value("${read.timeout.seconds:30}") private int readTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        builder.setConnectTimeout(Duration.of(connectionTimeout, ChronoUnit.SECONDS));
        builder.setReadTimeout(Duration.of(readTimeout, ChronoUnit.SECONDS));
        return builder.build();
    }
}
