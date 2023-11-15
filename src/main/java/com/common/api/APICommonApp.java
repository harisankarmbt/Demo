package com.common.api;

import org.springframework.boot.SpringApplication;		
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 
@SpringBootApplication
@ComponentScan(basePackages = "com.common.api.resource") 
@PropertySource("classpath:application.properties")
public class APICommonApp {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        SpringApplication.run(APICommonApp.class, args);
    }
}
