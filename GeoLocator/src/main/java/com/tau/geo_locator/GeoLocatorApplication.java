package com.tau.geo_locator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAsync
@SpringBootApplication
public class GeoLocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoLocatorApplication.class, args);
	}

}
