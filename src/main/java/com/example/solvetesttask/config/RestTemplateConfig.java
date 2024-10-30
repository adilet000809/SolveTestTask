package com.example.solvetesttask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {
    private static final String BASE_URL = "https://date.nager.at/api/v3/IsTodayPublicHoliday/";

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(BASE_URL);
        restTemplate.setUriTemplateHandler(uriBuilderFactory);
        return restTemplate;
    }
}
