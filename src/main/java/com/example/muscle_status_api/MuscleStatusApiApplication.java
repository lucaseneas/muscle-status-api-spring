package com.example.muscle_status_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class MuscleStatusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuscleStatusApiApplication.class, args);
	}

}
