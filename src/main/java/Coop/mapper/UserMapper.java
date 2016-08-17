package Coop.mapper;

import java.util.List;

import Coop.model.User;

public interface UserMapper {
	
	List<User> selectAll();
	User selectById(String id);
	void insertUser(User user);
	void updateUserImage(User user);
	

}
