package Coop.mapper;

import java.util.HashMap;

import Coop.model.NoticeUser;

public interface NoticeUserMapper {
	void insert(NoticeUser noticeUser);
	void updateConfirm(HashMap<String,Object> map);
}
