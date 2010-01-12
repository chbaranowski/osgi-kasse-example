package org.osgiusers.germany.example.basar.web;

import java.io.Serializable;
import java.util.List;

import org.osgiusers.germany.example.basar.domain.Position;
import org.osgiusers.germany.example.basar.domain.Sale;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CashPointSession implements Serializable {

	private Sale sale = new Sale();
	
	public void addPosition(Position position) {
		sale.addPosition(position);
	}

	public List<Position> getPositions() {
		return sale.getPositions();
	}

	public void removePosition(Position position) {
		sale.removePosition(position);
	}
	
	public Sale getSale(){
		return sale;
	}

	public void reset(){
		sale = new Sale();
	}
	
}
