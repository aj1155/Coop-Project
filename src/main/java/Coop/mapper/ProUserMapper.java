package Coop.mapper;

import java.util.HashMap;
import java.util.List;

import Coop.model.ChartData;
import Coop.model.Pro_User;
import Coop.model.User;

public interface ProUserMapper {
	
	void insertPro_user(Pro_User pro_user);
	void updateCont(Pro_User pro_user);
	void update(HashMap<String,Object> map);
	List<User> selectByProjectId(int id);
	List<ChartData> selectCont(int id);

}
