package ru.procoders.webinarspamservice.config;


import com.github.javafaker.Faker;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class BeansConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }


    @Bean
    public Faker faker(){
        return new Faker();
    }
}
