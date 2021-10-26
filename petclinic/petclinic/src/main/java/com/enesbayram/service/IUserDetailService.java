package com.enesbayram.service;

import java.util.List;

import com.enesbayram.model.UserDetail;
import com.enesbayram.model.Vet;

public interface IUserDetailService {

	List<UserDetail> findAll();
	
	UserDetail findById(Long userDetailId);
	
	void create(UserDetail userDetail);
	
	UserDetail update(UserDetail userDetail);
	
	void delete(Long userDetailId);
	
	
}
