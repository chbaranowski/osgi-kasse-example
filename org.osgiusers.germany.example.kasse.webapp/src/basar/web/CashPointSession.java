package basar.web;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import basar.domain.Position;
import basar.domain.Sale;

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
