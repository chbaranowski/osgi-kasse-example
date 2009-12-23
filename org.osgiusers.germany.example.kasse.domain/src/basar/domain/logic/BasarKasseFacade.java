package basar.domain.logic;

import java.util.List;

import basar.domain.Position;
import basar.domain.PositionKey;
import basar.domain.Sale;
import basar.domain.Seller;

public interface BasarKasseFacade {

	Seller getSeller(long basarNumber);
	
	void insertSeller(Seller seller);
	
	void updateSeller(Seller seller);
	
	void deleteSeller(Seller seller);
	
	List<Seller> getSellerList();
		
	boolean isValideBasarNumber(long number);
	
	void purchase(Sale sale);
	
	void storno(Sale sale);
		
	List<Position> getPositionList();
	
	void updatePosition(Position position);
	
	void deletePosition(Position position);
	
	Position getPosition(PositionKey positionKey);
	
	void insertPosition(Position position);
	
	String getUmsatz();
	
	String getGewinn();

}
