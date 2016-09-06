package Coop.mapper;

import java.util.List;

import Coop.model.FileInner;

public interface FileInnerMapper {
	List<FileInner> selectByProjectId(int projectId);
	FileInner selectById(int id);
	List<FileInner> selectByUserId(String userId);
	void insert(FileInner file);

}
