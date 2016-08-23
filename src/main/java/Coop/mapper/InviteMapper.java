package Coop.mapper;

import java.util.List;

import Coop.model.Invite;

public interface InviteMapper {
	void insert(Invite invite);
	List<Invite> selectByRecipient(String recipient);

}
