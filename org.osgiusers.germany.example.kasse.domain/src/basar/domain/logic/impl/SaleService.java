package basar.domain.logic.impl;

import basar.domain.Sale;

public interface SaleService {

	boolean isValideBasarNumber(long number);
	
	void purchase(Sale sale);
	
	void storno(Sale sale);
	
}
