package org.osgiusers.germany.example.basar.domain.logic.impl;

import java.util.Date;
import java.util.List;

import org.osgiusers.germany.example.basar.dao.PositionDao;
import org.osgiusers.germany.example.basar.dao.SellerDao;
import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.PositionKey;
import org.osgiusers.germany.example.basar.domain.PositionType;
import org.osgiusers.germany.example.basar.domain.Sale;
import org.osgiusers.germany.example.basar.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class SaleServiceImpl implements SaleService {

	private PositionDao positionDao;
	
	private SellerDao sellerDao;
	
	public void setPositionDao(PositionDao positionDao){
		this.positionDao = positionDao;
	}
	
	public void setSellerDao(SellerDao sellerDao){
		this.sellerDao = sellerDao;
	}
		
	public void purchase(Sale sale) {
		List<Position> positions = sale.getPositions();
		for (Position position : positions) {
			Seller seller = position.getSeller();
			Seller localSeller = sellerDao.getSeller(seller.getBasarNumber());
			if(localSeller == null){
				sellerDao.insertSeller(seller);
			}
			if(position.getPositionKey() == null){
				PositionKey key = positionDao.createPositionKey();
				position.setPositionKey(key);
			}
			position.setCreateTime(new Date());
			positionDao.insertPosition(position);
		}
	}

	public boolean isValideBasarNumber(long number) {
		return sellerDao.getSeller(number) != null;
	}

	public void storno(Sale sale) {
		List<Position> positions = sale.getPositions();
		for (Position position : positions) {
			if(position.getPositionKey() == null){
				PositionKey key = positionDao.createPositionKey();
				position.setPositionKey(key);
			}
			position.setType(PositionType.STORNO);
			position.setCreateTime(new Date());
			positionDao.insertPosition(position);
		}
	}

}
