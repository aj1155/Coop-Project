package Coop.mapper;

import java.util.List;

import Coop.model.User;
import Coop.model.UserKey;

public interface UserMapper {
	
	List<User> selectAll();
	User selectById(String id);
	UserKey selectByKey(String userId);
	void insertKey(UserKey userKey);
	void insertUser(User user);
	void updateUserImage(User user);
	void updateKey(UserKey userKey);
	void updateUser(User user);
	List<User> selectProject(int id);
	User loginProcess(String id,String password);
	

}
