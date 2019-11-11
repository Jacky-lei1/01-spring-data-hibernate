package com.bjsxt.dao;

import java.util.List;

import com.bjsxt.pojo.Users;

public interface UsersDao {
	
	void insertUsers(Users users);
	void updateUsers(Users users);
	void deleteUsers(Users users);
	Users selectUsersById(Integer userid);
	
	List<Users> selectUserByName(String username);

	List<Users> selectUserByNameUseSQL(String username);

	List<Users> selectUserByNameUseCriteria(String username);
}
