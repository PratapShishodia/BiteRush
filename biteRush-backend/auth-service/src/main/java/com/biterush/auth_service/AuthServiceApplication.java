package com.biterush.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		System.out.println("Java timezone = " + java.time.ZoneId.systemDefault());
		System.out.println("user.timezone = " + System.getProperty("user.timezone"));

		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
