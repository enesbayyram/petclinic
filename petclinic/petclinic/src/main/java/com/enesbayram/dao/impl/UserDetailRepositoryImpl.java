package com.enesbayram.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.enesbayram.dao.IUserDetailRepository;
import com.enesbayram.model.UserDetail;

@Repository
public class UserDetailRepositoryImpl implements IUserDetailRepository{

	@Override
	public List<UserDetail> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetail findById(Long userDetailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(UserDetail userDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDetail update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long userDetailId) {
		// TODO Auto-generated method stub
		
	}

}
