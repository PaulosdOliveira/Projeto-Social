package com.github.PaulosdOliveira.social;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class SocialApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void tt(){
		System.out.println("Hora: " + LocalTime.now());
	}

}
