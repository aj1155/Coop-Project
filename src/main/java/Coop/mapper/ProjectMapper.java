package Coop.mapper;

import java.util.List;

import Coop.model.ChartData;
import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.model.User;


public interface ProjectMapper {
	
	void insertProject(Project project);
	void insertPro_user(Pro_User pro_user);
	List<Project> selectById(User user);
	List<Project> selectByNotice(String id);
	List<Project> selectById2(String id);
	List<Project> selectBySearch(String search);
	Project selectByProjectId(int id);
	List<ChartData> selectCont(String userId);
	void update(Project project);
}
