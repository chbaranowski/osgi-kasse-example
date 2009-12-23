package basar.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sale implements Serializable {

	private final List<Position> positions = new ArrayList<Position>();
	
	public void addPosition(Position position){
		positions.add(position);
	}
	
	public void removePosition(Position position){
		positions.remove(position);
	}
	
	public List<Position> getPositions(){
		return positions;
	}
	
	public long getSumme(){
		long summe = 0;
		for (Position position : positions) {
			summe += position.getPrice();
		}
		return summe;
	}
	
}
