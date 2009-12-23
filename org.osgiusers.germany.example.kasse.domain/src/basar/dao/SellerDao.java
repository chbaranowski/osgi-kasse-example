package basar.dao;

import java.util.List;

import basar.domain.Seller;

public interface SellerDao {

	Seller getSeller(long basarNumber);
	
	void insertSeller(Seller seller);
	
	void updateSeller(Seller seller);
	
	void deleteSeller(Seller seller);
	
	List<Seller> getSellerList();
	
}
