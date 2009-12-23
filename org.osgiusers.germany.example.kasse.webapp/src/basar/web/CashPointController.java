package basar.web;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import basar.domain.Position;
import basar.domain.PriceFormatUtil;
import basar.domain.Seller;
import basar.domain.logic.BasarKasseFacade;

@Controller("cashPointController")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CashPointController {

	private String basarNumber;
	
	private String price;
	
	private String description;
	
	private ListDataModel salePositions;
	
	private CashPointSession saleSession;
	
	private BasarKasseFacade basarKasse;
	
	@Autowired
	public void setSaleSession(CashPointSession saleSession){
		this.saleSession = saleSession;
	}
	
	@Autowired
	public void setBasarKasse(BasarKasseFacade basarKasse){
		this.basarKasse = basarKasse;
	}
	
	
	public String getBasarNumber() {
		return basarNumber;
	}

	public void setBasarNumber(String basarNumber) {
		this.basarNumber = basarNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ListDataModel getSalePositions(){
		salePositions = new ListDataModel(saleSession.getPositions());
		return salePositions;
	}
	
	public String addPositionAction() throws NumberFormatException, ParseException{
		
		Seller seller = basarKasse.getSeller(Long.valueOf(basarNumber)); 
		
		double priceDouble = priceToDouble()*100;
		long priceLong = Math.round(priceDouble);
		
		
		Position position = new Position();
		position.setPrice(priceLong);
		position.setSeller(seller);
		position.setDescription(description);
		
		saleSession.addPosition(position);
		
		price = "1,0";
		basarNumber = "";
		description = "";
		
		return "position_add";
	}
	
	private double priceToDouble(){
		String str = price.replace(',', '.');
		Double d = Double.valueOf(str);
		return d.doubleValue();
	}
	
	public String removePositionAction(){
		Position position = (Position) salePositions.getRowData();
		saleSession.removePosition(position);
		return "postion_remove";
	}
	
	public String purchaseAction(){
		basarKasse.purchase(saleSession.getSale());
		saleSession.reset();
		return "new_sale";
	}
	
	public String stornoAction(){
		saleSession.reset();
		return "new_sale";
	}
	
	
	public String getSumme(){
		return PriceFormatUtil.formatPriceLongToString(saleSession.getSale().getSumme());
	}
	
	public void validateBasarNumber(FacesContext context, UIComponent component, Object value){
		Pattern p = Pattern.compile("[+]?[0-9]+");
		Matcher m = p.matcher(((String) value));
		if(!m.matches()){
			throw new ValidatorException(new FacesMessage("Keine gültige Basarnummer"));
		}
		Long basarNumber = Long.valueOf((String) value);
		Seller seller = basarKasse.getSeller(basarNumber);
		if(seller == null){
			throw new ValidatorException(new FacesMessage("Verkäufer mit der Basar Nummer: "+basarNumber+" nicht gefunden!"));
		}
	}
	
	public void validatePreis(FacesContext context, UIComponent component, Object value){
		String str = (String) value;
		
		Pattern p = Pattern.compile("[+]?[0-9]+");
		Matcher m = p.matcher(str.replace(",", ""));
		if(!m.matches()){
			throw new ValidatorException(new FacesMessage("Der Preis :"+str +" ist kein gültiger Geldbetrag"));
		}
		
		if(str.contains(",")){
		 String komma = str.substring(str.indexOf(',')+1);
		 if(komma.length() > 2){
		   throw new ValidatorException(new FacesMessage("Der Preis :"+str +" ist kein gültiger Geldbetrag zu viele Kommastellen"));
		 }
		 if(!komma.equals("00") && !komma.equals("50") && !komma.equals("0") && !komma.equals("5")){
			 throw new ValidatorException(new FacesMessage("Der Preis :"+str +" ist kein gültiger Geldbetrag"));
		 }
		}
	}
	
	public String getPositionPrice(){
		Position postion = (Position) salePositions.getRowData();
		return NumberFormat.getCurrencyInstance().format(postion.getPrice() / 100);
	}
	
	public String getUmsatz(){
		return basarKasse.getUmsatz();
	}
	
	public String getGewinn(){
		return basarKasse.getGewinn();
	}

	
}
