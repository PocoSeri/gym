package com.gym.gym;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Profile("test")
@ComponentScan(basePackages = "com.gym.gym")
class GymApplicationTests {

	@Test
	void contextLoads() {
	}

}
