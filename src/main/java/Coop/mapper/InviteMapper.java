package Coop.mapper;

import java.util.HashMap;
import java.util.List;

import Coop.model.Invite;

public interface InviteMapper {
	void insert(Invite invite);
	List<Invite> selectByRecipient(String recipient);
	void updateConfirm(HashMap<String,Object> map);

}
