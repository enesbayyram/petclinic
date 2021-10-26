package com.enesbayram.model;

import java.util.ArrayList;
import java.util.List;

public class UserList {

	private  List<User> userList=null;
	
	public UserList() {
		userList = new ArrayList<User>();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
