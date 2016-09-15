package Coop.mapper;

import java.util.List;

import Coop.model.PngFiles;
public interface PngFilesMapper {
	List<PngFiles> selectById(int id);
	List<PngFiles> selectByFileId(int fileId);
	void insert(PngFiles pngFiles);
	void delete(int id);
	

}
