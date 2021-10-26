package com.enesbayram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.enesbayram.model.User;
import com.enesbayram.model.UserDetail;
import com.enesbayram.service.IUserService;

@Controller
public class PetClinicNewUserController {

	@Autowired
	private IUserService userService;

	@GetMapping(value = "/user/new")
	public String createUser() {
		return "createUser";
	}

	@ModelAttribute
	public User createModel() {
		User user = new User();
		user.setUserDetail(new UserDetail());
		return user;
	}

	@PostMapping(value = "/user/new")
	public String handleFormSubmit(@ModelAttribute User user) {
		userService.create(user);
		return "redirect:/getUsers";
	}
}
