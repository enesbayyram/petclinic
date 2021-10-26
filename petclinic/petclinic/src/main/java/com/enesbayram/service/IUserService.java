package com.enesbayram.service;

import java.util.List;

import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.Vet;

public interface IUserService {

	List<User> findAll();

	List<User> findByLastname(String lastName);

	User findById(Long userId) throws UserNotFoundException;

	void create(User user);

	User update(User user);

	void delete(Long userId) throws UserNotFoundException;

	List<Vet> getVetList();

	Vet getVetById(Long id);
}
