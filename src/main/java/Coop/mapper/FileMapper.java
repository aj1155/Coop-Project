package Coop.mapper;

import java.util.List;
import Coop.model.File;

public interface FileMapper {
	List<File> selectByProjectId(int projectId);
	File selectById(int id);
	List<File> selectByUserId(String userId);
	List<File> selectByNoData(int projectId);
	List<File> selectByName(String name);
	void insert(File file);
	void delete(int id);
}
