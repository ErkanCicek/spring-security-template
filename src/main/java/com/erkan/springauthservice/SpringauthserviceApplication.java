package com.erkan.springauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringauthserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthserviceApplication.class, args);
	}

}
