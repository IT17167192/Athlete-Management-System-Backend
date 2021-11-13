package com.olympic.athletemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
public class AthleteManagementSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AthleteManagementSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AthleteManagementSystemApplication.class);
	}
}
