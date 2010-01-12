package org.osgiusers.germany.example.basar.domain.logic.impl;

import org.osgiusers.germany.example.basar.domain.Sale;

public interface SaleService {

	boolean isValideBasarNumber(long number);
	
	void purchase(Sale sale);
	
	void storno(Sale sale);
	
}
