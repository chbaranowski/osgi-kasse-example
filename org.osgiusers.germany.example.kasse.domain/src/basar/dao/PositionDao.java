package basar.dao;

import java.util.List;

import basar.domain.Position;
import basar.domain.PositionKey;

public interface PositionDao  {

	Position getPosition(PositionKey positionKey);
	
	void insertPosition(Position position);
	
	void updatePosition(Position position);
	
	void deletePosition(Position position);
	
	PositionKey createPositionKey();
	
	List<Position> getPositionList();
	
}
