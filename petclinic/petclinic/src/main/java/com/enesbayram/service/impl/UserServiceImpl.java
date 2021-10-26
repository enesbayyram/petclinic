package com.enesbayram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.enesbayram.dao.IUserRepository;
import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.Vet;
import com.enesbayram.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	
	private IUserRepository userRepository;

	@Autowired
	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long userId) throws UserNotFoundException {
		return userRepository.findById(userId);
	}

	@Override
	@CacheEvict(cacheNames = "getUsersRegion", allEntries = true)
	public void create(User user) {
		userRepository.create(user);
	}

	@Override
	@CacheEvict(cacheNames = "getUsersRegion" , allEntries = true)
	public User update(User user) {
		return userRepository.update(user);
	}

	
	@Override
	@CacheEvict(cacheNames = "getUsersRegion" , allEntries = true)
	public void delete(Long userId) throws UserNotFoundException{
		userRepository.delete(userId);
	}

	@Override
	public List<User> findByLastname(String lastName) {
		
		return userRepository.findByLastname(lastName);
	}


	@Override
	public List<Vet> getVetList() {
		return userRepository.getVetList();
	}


	@Override
	public Vet getVetById(Long id) {
		return userRepository.getVetById(id);
	}

}
