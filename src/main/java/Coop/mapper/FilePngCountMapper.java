package Coop.mapper;

import java.util.List;

import Coop.model.FilePngCount;

public interface FilePngCountMapper {
	
	List<FilePngCount> selectByFileId(int fileId);
	void insert(FilePngCount filePngCount);

}
