package com.common.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 
@SpringBootApplication
@PropertySource({ "classpath:application.properties"})
@PropertySource(value = {"classpath:response.properties"})
public class APICommonApp {   
 
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer(); 
	}
	 
	public static void main(String[] args) {  
		SpringApplication.run(APICommonApp.class, args); 
	} 
}
