 
package com.erkan.springauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringauthserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringauthserviceApplication.class, args);
	}

}
