package com.heartz.byeboo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ByebooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByebooApplication.class, args);
	}

}
