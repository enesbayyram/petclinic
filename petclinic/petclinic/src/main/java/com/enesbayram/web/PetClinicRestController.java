package com.enesbayram.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.UserList;
import com.enesbayram.service.IUserService;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

	@Autowired
	private IUserService userService;

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		try {
			userService.delete(id);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping(value = "/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			User dbUser = userService.findById(id);
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			userService.update(dbUser);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping(value = "/user")
	public ResponseEntity<UserList> createUser(@RequestBody User user) {
		UserList userList = new UserList();
		userService.create(user);
		List<User> allList = userService.findAll();
		userList.setUserList(allList);
		return ResponseEntity.ok(userList);
	}

	@Cacheable("getUsersRegion")
	@GetMapping(value = "/users")
	public ResponseEntity<UserList> getUsers() {
		System.out.println("getUsers metodu içerisindeyim....--->>>>>");
		UserList userList = new UserList();
		List<User> list = userService.findAll();
		userList.setUserList(list);
		return ResponseEntity.ok(userList);
	}

	@GetMapping(value = "/user")
	public ResponseEntity<UserList> getUsersBySurname(@RequestParam("ln") String lasName) {
		UserList userList = new UserList();
		List<User> list = userService.findByLastname(lasName);
		userList.setUserList(list);
		return ResponseEntity.ok(userList);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
		User user = new User();
		try {
			user = userService.findById(userId);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
