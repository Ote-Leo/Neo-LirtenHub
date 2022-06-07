package com.tau.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LirtenhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(LirtenhubApplication.class, args);
	}

}
