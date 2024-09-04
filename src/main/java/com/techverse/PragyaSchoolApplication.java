package com.techverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
@EnableAsync
@SpringBootApplication
public class PragyaSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(PragyaSchoolApplication.class, args);
	}
	 

}
