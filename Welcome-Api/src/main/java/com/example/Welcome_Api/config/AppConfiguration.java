package com.example.Welcome_Api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {


    @Bean
    public RestTemplate getInstance()
    {
        return new RestTemplate();
    }

}
