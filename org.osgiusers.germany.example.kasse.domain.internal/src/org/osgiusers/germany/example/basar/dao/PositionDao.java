package org.osgiusers.germany.example.basar.dao;

import java.util.List;

import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.PositionKey;


public interface PositionDao  {

	Position getPosition(PositionKey positionKey);
	
	void insertPosition(Position position);
	
	void updatePosition(Position position);
	
	void deletePosition(Position position);
	
	PositionKey createPositionKey();
	
	List<Position> getPositionList();
	
}
