package Coop.mapper;

import java.util.List;

import Coop.model.Notice;
import Coop.model.NoticeUser;

public interface NoticeMapper {
	
	void insert(Notice notice);
	List<Notice> selectByProjectId(int id);
	List<Notice> select(NoticeUser notice);

}
