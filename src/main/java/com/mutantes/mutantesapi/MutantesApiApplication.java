package com.mutantes.mutantesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MutantesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantesApiApplication.class, args);
	}

}
