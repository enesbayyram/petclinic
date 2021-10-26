package com.enesbayram.petclinic.security;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.enesbayram.config.PetClinicStarter;
import com.enesbayram.model.User;
import com.enesbayram.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PetClinicStarter.class})
@ActiveProfiles("dev")
public class PetClinicWithoutAuthenctionTest {

	@Autowired
	private IUserService userServiceImpl;
	
	@org.junit.jupiter.api.Test
	public void getUsers()
	{
		List<User> userList = userServiceImpl.findAll();
		userList.forEach(user->{System.out.println(user);});
	}
}
