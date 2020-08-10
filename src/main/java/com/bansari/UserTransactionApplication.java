package com.bansari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.bansari.*")
public class UserTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserTransactionApplication.class, args);
	}

}
