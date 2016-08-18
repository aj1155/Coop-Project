package Coop.mapper;

import java.util.HashMap;
import java.util.Map;

import Coop.model.Pro_User;

public interface ProUserMapper {
	
	void insertPro_user(Pro_User pro_user);
	void updateCont(Pro_User pro_user);
	void update(HashMap<String,Object> map);

}
