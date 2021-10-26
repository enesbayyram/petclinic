package com.enesbayram.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetClinicConfig {

	@Autowired
	private PetClinicProperties petClinicProperties;
	
	@PostConstruct
	public void init()
	{
		System.out.println("Username : " + petClinicProperties.getUsername());
		System.out.println("Password :" + petClinicProperties.getPassword());
	}
}
