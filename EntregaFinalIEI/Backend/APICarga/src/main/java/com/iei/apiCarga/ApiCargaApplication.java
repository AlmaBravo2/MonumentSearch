package com.iei.apiCarga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiCargaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCargaApplication.class, args);
	}

}
