package com.tau.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootTest
@EnableAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class ProjectApplicationTests {

	@Test
	void contextLoads() {
	}

}
