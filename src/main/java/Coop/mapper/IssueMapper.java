package Coop.mapper;

import java.util.List;

import Coop.model.Issue;

public interface IssueMapper {
	
	Issue selectById(int id);
	List<Issue> selectByProjectId(int id);
	void insert(Issue issue);

}
