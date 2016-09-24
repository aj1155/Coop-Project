package Coop.mapper;

import Coop.model.ActivePoint;

public interface ActivePointMapper {
	
	ActivePoint selectAll(int projectId);
	void insert(ActivePoint activePoint);

}
