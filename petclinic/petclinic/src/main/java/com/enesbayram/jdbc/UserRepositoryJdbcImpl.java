package com.enesbayram.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.enesbayram.dao.IUserRepository;
import com.enesbayram.exception.UserNotFoundException;
import com.enesbayram.model.User;
import com.enesbayram.model.UserDetail;
import com.enesbayram.model.Vet;

//@Repository
public class UserRepositoryJdbcImpl implements IUserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcTemplate template;

	RowMapper<User> rowMapper=new RowMapper<User>(){

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user =new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			
			UserDetail userDetail = new UserDetail();
			userDetail.setId(rs.getLong("userdetailid"));
			userDetail.setUsername(rs.getString("username"));
			userDetail.setPassword(rs.getString("userpassword"));
			userDetail.setBirthOfDate(rs.getDate("birthofdate"));
			
			user.setUserDetail(userDetail);
			return user;		}
	};



	@Override
	public List<User> findAll() {
		String query = "select * from t_user u left join t_userdetail ud on u.userdetailid=ud.id";
		List<User> userList = null;
		try {
			userList =  jdbcTemplate.query(query, rowMapper);
		} catch (Exception e) {
			System.out.println("Hata : "  +e.getMessage());
		}
		return userList;
	}

	@Override
	public List<User> findByLastname(String lastName) {
		String query = "select * from t_user u left join t_userdetail ud on u.userdetailid=ud.id"
				+ " where u.lastname=:lastname";

		Map<String, String> paraMap = new TreeMap<String, String>();
		paraMap.put("lastname", lastName);
		List<User> userList = jdbcTemplate.query(query, paraMap, rowMapper);
		return userList;
	}

	@Override
	public User findById(Long userId) throws UserNotFoundException {
		User resultUser = null;
		String query = "select * from t_user u left join t_userdetail ud on u.userdetailid=ud.id"
				+ " where u.id=:id";
		Map<String, Long> mapRow = new HashMap<String, Long>();
		mapRow.put("id", userId);
		try {
			resultUser = jdbcTemplate.queryForObject(query, mapRow,rowMapper);
		} catch (Exception e) {
			System.out.println("Hata : " + e.getMessage());
		}
		return resultUser;
	}

	@Override
	@Transactional
	public void create(User user) {

		// Büyük ihtimal transaction işlemleri olacak daha sonra yaparım insallah

		String userQuery = "insert into t_user(id,firstname,lastname,userdetailid)"
				+ "values(:id,:firstname,:lastname,:userdetailid)";
		
		String userDetailQuery = "insert into t_userdetail(id,username,userpassword,birthofdate)"
				+ "values(:id,:username,:userpassword,:birthofdate)";
		try {
			//t_userdetail tablosuna insert işlemi yapıldı..
			
			Map<String, Object> userDetailMap = new HashMap<String, Object>();
			userDetailMap.put("id", user.getUserDetail().getId());
			userDetailMap.put("username", user.getUserDetail().getUsername());
			userDetailMap.put("userpassword", user.getUserDetail().getPassword());
			userDetailMap.put("birthofdate", user.getUserDetail().getBirthOfDate());
			jdbcTemplate.update(userDetailQuery, userDetailMap);
			
			
			//t_user tablosuna insert işlemi yapıldı..
			Map<String, Object> userMap =new HashMap<String, Object>();
			userMap.put("id", user.getId());
			userMap.put("firstname", user.getFirstName());
			userMap.put("lastname", user.getLastName());
			userMap.put("userdetailid", user.getUserDetail().getId());
			jdbcTemplate.update(userQuery, userMap);
			
			System.out.println("User Eklendi...");
			
		} catch (Exception e) {
			System.out.println("Hata : " + e.getMessage());
		}

	}

	@Override
	public User update(User user) {
		// sonra yaparız create ile aynı mantık .
		return null;
	}

	@Transactional
	@Override
	public void delete(Long userId) throws UserNotFoundException {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", userId);
		Long userDetailId=0L;
		try {
			User user = jdbcTemplate.queryForObject("select * from t_user u left join t_userdetail ud on u.userdetailid=ud.id"
					+ " where u.id=:id", map, rowMapper);
			if(user!=null)
			{
				userDetailId= user.getUserDetail().getId();
				Map<String, Integer> userMap = new HashMap<String, Integer>();
				userMap.put("id", user.getId());
				
				Map<String, Long> userDetailMap = new HashMap<String, Long>();
				userDetailMap.put("id", userDetailId);
				
				jdbcTemplate.update("delete from t_user where id=:id", userMap);
				jdbcTemplate.update("delete from t_userdetail where id=:id", userDetailMap);
			}
		} catch (Exception e) {
			System.out.println("Hata : " + e.getMessage());
		}
		
		
		

	}

	@Override
	public List<Vet> getVetList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vet getVetById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
