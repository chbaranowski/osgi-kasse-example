package org.osgiusers.germany.example.basar.domain.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import org.osgiusers.germany.example.basar.dao.PositionDao;
import org.osgiusers.germany.example.basar.dao.SellerDao;
import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.PositionKey;
import org.osgiusers.germany.example.basar.domain.PositionType;
import org.osgiusers.germany.example.basar.domain.Sale;
import org.osgiusers.germany.example.basar.domain.Seller;
import org.osgiusers.germany.example.basar.domain.logic.BasarKasseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasarKasseImpl implements BasarKasseFacade {

	private PositionDao positionDao;
	
	private SellerDao sellerDao;
	
	private SaleService saleService;
		
	@Autowired
	public void setPositionDao(PositionDao positionDao){
		this.positionDao = positionDao;
	}
	
	@Autowired
	public void setSellerDao(SellerDao sellerDao){
		this.sellerDao = sellerDao;
	}
	
	@Autowired
	public void setSaleService(SaleService saleService){
		this.saleService = saleService;
	}
		
	public void deleteSeller(Seller seller) {
		sellerDao.deleteSeller(seller);
	}

	public Seller getSeller(long basarNumber) {
		return sellerDao.getSeller(basarNumber);
	}

	public List<Seller> getSellerList() {
		return sellerDao.getSellerList();
	}

	public void insertSeller(Seller seller) {
		sellerDao.insertSeller(seller);
	}

	public void updateSeller(Seller seller) {
		sellerDao.updateSeller(seller);
	}
	
	public boolean isValideBasarNumber(long number) {
		return saleService.isValideBasarNumber(number);
	}

	public void purchase(Sale sale) {
		saleService.purchase(sale);
	}

	public List<Position> getPositionList() {
		return positionDao.getPositionList();
	}

	public void deletePosition(Position position) {
		positionDao.deletePosition(position);
	}

	public void updatePosition(Position position) {
		positionDao.updatePosition(position);
	}

	public void storno(Sale sale) {
		saleService.storno(sale);
	}

	public Position getPosition(PositionKey positionKey) {
		return positionDao.getPosition(positionKey);
	}
	
	public void insertPosition(Position position){
		positionDao.insertPosition(position);
	}

	public String getUmsatz() {
		long umsatz = getUmsatzAsLong();
		String str = formatUmsatz(umsatz);
		return str;
	}

	private long getUmsatzAsLong() {
		long umsatz = 0;
		List<Position> positionList = positionDao.getPositionList();
		for (Position position : positionList) {
			if(position.getType().equals(PositionType.SALE))
				umsatz += position.getPrice();
			else
				umsatz -= position.getPrice();
		}
		return umsatz;
	}

	public String formatUmsatz(long umsatz) {
		if(umsatz == 0)
			return "0,00";
		String str = String.valueOf(umsatz);
		if(str.length() == 1)
			return "0,0" + str;
		if(str.length() == 2)
			return "0," + str;
		str = str.substring(0, str.length()-2) + "," + str.substring(str.length()-2);
		return str;
	}

	public String getGewinn() {
		long umsatz = getUmsatzAsLong();
		BigDecimal prozent = new BigDecimal(umsatz).divide(new BigDecimal(100));
		long gewinn = Math.round(prozent.doubleValue() * 20);
		return formatUmsatz(gewinn);
	}

}
