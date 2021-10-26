package com.enesbayram.petclinic.security;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.enesbayram.config.PetClinicStarter;
import com.enesbayram.model.User;
import com.enesbayram.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PetClinicStarter.class})
@ActiveProfiles(value = "dev")
public class PetClinicInvalidAuthenctionTest {

	@Autowired
	private IUserService userServiceImpl;
	
	{
		TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "123", "ROLE_XXX");
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void getUsers()
	{
		List<User> userList = userServiceImpl.findAll();
		userList.forEach(user->{System.out.println(user);});
	}
}
