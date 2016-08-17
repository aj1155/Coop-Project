package Coop.mapper;

import java.util.List;
import Coop.model.File;

public interface FileMapper {
	List<File> selectByProjectId(int projectId);
	File selectById(int id);
	List<File> selectByUserId(String userId);
	void insert(File file);
}
