package com.enesbayram.petclinic.test;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.enesbayram.dao.IUserRepository;
import com.enesbayram.dao.jpa.impl.UserRepositoryJPAImpl;
import com.enesbayram.model.User;
import com.enesbayram.model.UserDetail;
import com.enesbayram.model.UserList;
import com.enesbayram.service.IUserService;
import com.enesbayram.service.impl.UserServiceImpl;

public class PetClinicRestControllerTests {

	private RestTemplate restTemplate = new RestTemplate();
	
	
	private IUserRepository crudRepository = new UserRepositoryJPAImpl();
	
	@Test
	public void getUserList()
	{
		List<User> list = crudRepository.findAll();
		System.out.println("********************* USER LİSTESİ *********************");
		list.forEach(user->{System.out.println(user);});
	}

	@Test
	public void getUserById() {
		ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/rest/user/2", User.class);
		if (response != null) {
			System.out.println(response.getBody());
		}else {
			System.out.println("Girilen id 'ye ait user bulunamadı!");
		}
	}

	@Test
	public void getUserBySurname() {
		ResponseEntity<UserList> response = restTemplate.getForEntity("http://localhost:8080/rest/user?ln=Çamur",
				UserList.class);
		response.getBody().getUserList().forEach(user -> {
			System.out.println(user);
		});
	}

	@Test
	public void getUsers() {
		ResponseEntity<UserList> response = restTemplate.getForEntity("http://localhost:8080/rest/users",
				UserList.class);
		response.getBody().getUserList().forEach(user -> {
			System.out.println(user);
		});
	}

	@Test
	public void createUser() {
		
		UserDetail userDetail = new UserDetail();
		userDetail.setUsername("enes55");
		userDetail.setPassword("12345");
		userDetail.setBirthOfDate(new Date());
		
		User user = new User();
		user.setFirstName("Enes");
		user.setLastName("Bayram");
		user.setUserDetail(userDetail);
		

		restTemplate.postForLocation("http://localhost:8080/rest/user", user);
		ResponseEntity<UserList> response = restTemplate.getForEntity("http://localhost:8080/rest/users",
				UserList.class);
		response.getBody().getUserList().forEach(u->{System.out.println(u);});
	}
	@Test
	public void updateUser()
	{
		ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/rest/user/1", User.class);
		User dbUser =  response.getBody();
		dbUser.setFirstName("Yasin");
		dbUser.setLastName("Yazıcı");
		
		restTemplate.put("http://localhost:8080/rest/user/1", dbUser);
	}
	@Test
	public void deleteUser()
	{
		try {
			restTemplate.delete("http://localhost:8080/rest/user/1");
		} catch (Exception e) {
			System.out.println("it happened as delete a user");
		}
	}

}
