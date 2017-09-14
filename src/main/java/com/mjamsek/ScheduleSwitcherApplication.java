package com.mjamsek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ScheduleSwitcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleSwitcherApplication.class, args);
	}
}
