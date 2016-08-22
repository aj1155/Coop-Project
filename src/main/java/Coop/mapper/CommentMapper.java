package Coop.mapper;

import java.util.List;
import Coop.model.Comment;
import Coop.model.User;

public interface CommentMapper {
	List<Comment> selectByFileId(int fileId);
	void insert(Comment comment);
}
