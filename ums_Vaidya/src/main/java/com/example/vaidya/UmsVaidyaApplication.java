package com.example.vaidya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.vaidya")
@EnableJpaRepositories(basePackages = "com.example.vaidya.repo")
public class UmsVaidyaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmsVaidyaApplication.class, args);
	}

}
