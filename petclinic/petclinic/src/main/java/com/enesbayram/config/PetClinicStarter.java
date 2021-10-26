package com.enesbayram.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(PetClinicProperties.class)
@ComponentScan(basePackages = {"com.enesbayram.*"})
@ServletComponentScan
@EntityScan(basePackages = {"com.enesbayram.*"})
public class PetClinicStarter {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicStarter.class, args);
	}
}
