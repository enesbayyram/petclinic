package com.enesbayram.petclinic.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.enesbayram.config.PetClinicStarter;
import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.Vet;
import com.enesbayram.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PetClinicStarter.class })
@ActiveProfiles(value = "dev")
public class PetClinicIntegrationTest {

	// Test sınıfımızı yazdık sorunsuz calisiyor , dikkat etmemmiz gereken nokta
	// şurası
	// @SpringBootTest sınıfında classess attirubute 'a starterı vermemiz gerekiyor.

	@Autowired
	private IUserService userServiceImpl;

	@Test
	public void getUsers() {
		List<User> userList = userServiceImpl.findAll();
		userList.forEach(u -> {
			System.out.println(u);
		});
	}

	@Test
	public void getUserById() {
		try {
			User user = userServiceImpl.findById(2L);
			if (user != null) {
				System.out.println("******** USER INFORMATION ********");
				System.out.println(user);
			}
		} catch (UserNotFoundException e) {
			System.out.println("User Bulunamadı : " + e.getMessage());
		}
	}

	@Test
	public void getUserByLastname() {
		List<User> userList = userServiceImpl.findByLastname("Çelebi");
		if (userList != null && userList.size() > 0) {
			userList.forEach(u -> {
				System.out.println(u);
			});
		}
	}

	@Test
	public void getVetList() {
		List<Vet> vetList = userServiceImpl.getVetList();
		vetList.forEach(vet -> {
			System.out.println(vet);
		});
	}

	@Test
	public void getVetById() {
		Long vetId = 15L;
		Vet vet = userServiceImpl.getVetById(vetId);
		if (vet != null) {
			System.out.println(vet);
		} else {
			System.out.println("Vet not found with id : " + vetId);
		}
	}

}
