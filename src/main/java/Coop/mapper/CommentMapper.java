package Coop.mapper;

import java.util.List;
import Coop.model.Comment;
import Coop.model.User;

public interface CommentMapper {
	List<Comment> selectByFileId(int fileId);
	Comment selectById(int id);
	void insert(Comment comment);
	void delete(int id);
}
