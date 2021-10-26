package com.enesbayram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.enesbayram.service.IUserService;

@Controller
public class PetClinicController {
	
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = "/getUsers")
	public ModelAndView getUsers()
	{
		ModelAndView mav= new ModelAndView();
		mav.addObject("users", userService.findAll());
		mav.setViewName("index");
		return mav;
	}
	

	@GetMapping(value = "/hello")
	@ResponseBody
	public String hello()
	{
		return "Enes Bayram gururla sunar :)";
	}
}
