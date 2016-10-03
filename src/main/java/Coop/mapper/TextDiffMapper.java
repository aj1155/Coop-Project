package Coop.mapper;

import java.util.List;

import Coop.model.TextDiff;

public interface TextDiffMapper {
	List<TextDiff> selectByfileId(int id);
	void insert(TextDiff textDiff);
	void delete(int id);
	

}
