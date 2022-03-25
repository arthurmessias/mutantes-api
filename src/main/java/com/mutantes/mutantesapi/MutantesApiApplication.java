package com.mutantes.mutantesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class MutantesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantesApiApplication.class, args);
	}

}
