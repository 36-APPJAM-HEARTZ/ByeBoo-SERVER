package com.heartz.byeboo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ByebooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByebooApplication.class, args);
	}

}
