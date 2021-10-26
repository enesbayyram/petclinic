package com.enesbayram.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_userdetail")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "userpassword")
	private String password;
	
	@Column(name = "birthofdate")
	@Temporal(TemporalType.DATE)
	private Date birthOfDate;

	

	public UserDetail() {
	}
	
	public UserDetail(Long id)
	{
		this.id=id;
	}

	public UserDetail(Long id, String username, String password, Date birthOfDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthOfDate = birthOfDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthOfDate() {
		return birthOfDate;
	}

	public void setBirthOfDate(Date birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", username=" + username + ", password=" + password + ", birthOfDate="
				+ birthOfDate + "]";
	}
}
