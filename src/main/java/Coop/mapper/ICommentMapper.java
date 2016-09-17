package Coop.mapper;

import java.util.List;

import Coop.model.IComment;

public interface ICommentMapper {
	List<IComment> selectByIssueId(int fileId);
	IComment selectById(int id);
	void insert(IComment comment);
	void delete(int id);

}
