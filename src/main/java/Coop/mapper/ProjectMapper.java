package Coop.mapper;

import java.util.List;

import Coop.model.Pro_User;
import Coop.model.Project;
import Coop.model.User;


public interface ProjectMapper {
	
	void insertProject(Project project);
	void insertPro_user(Pro_User pro_user);
	List<Project> selectById(User user);
	List<Project> selectById2(String id);
	Project selectByProjectId(int id);
}
