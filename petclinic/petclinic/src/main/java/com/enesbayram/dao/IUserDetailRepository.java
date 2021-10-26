package com.enesbayram.dao;

import java.util.List;

import com.enesbayram.model.UserDetail;

public interface IUserDetailRepository {

	List<UserDetail> findAll();

	UserDetail findById(Long userDetailId);

	void create(UserDetail userDetail);

	UserDetail update(UserDetail userDetail);

	void delete(Long userDetailId);
}
