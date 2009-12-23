package basar.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import basar.domain.Position;
import basar.domain.PositionType;
import basar.domain.Sale;
import basar.domain.Seller;
import basar.domain.logic.BasarKasseFacade;

@Controller("positionController")
public class PositionController {

	private String stornoBasarNumber;
	
	private String stornoPrice;
	
	private BasarKasseFacade basarKasse;
	
	private ListDataModel positionList;
	
	public String getStornoBasarNumber() {
		return stornoBasarNumber;
	}

	public void setStornoBasarNumber(String stornoBasarNumber) {
		this.stornoBasarNumber = stornoBasarNumber;
	}

	public String getStornoPrice() {
		return stornoPrice;
	}

	public void setStornoPrice(String stornoPrice) {
		this.stornoPrice = stornoPrice;
	}

	@Autowired
	public void setBasatKasse(BasarKasseFacade basarKasse){
		this.basarKasse = basarKasse;
	}
	
	public ListDataModel getPositionList(){
		positionList = new ListDataModel(basarKasse.getPositionList());
		return positionList;
	}
	
	public String stornoAction(){
		Seller seller = basarKasse.getSeller(Long.valueOf(stornoBasarNumber));
		
		Sale sale = new Sale();
		
		double priceDouble = priceToDouble()*100;
		long priceLong = Math.round(priceDouble);
		
		Position position = new Position();
		position.setPrice(priceLong);
		position.setSeller(seller);
		position.setType(PositionType.STORNO);
		position.setDescription("STORNO");
		
		sale.addPosition(position);
		
		basarKasse.purchase(sale);
		
		stornoBasarNumber = null;
		stornoPrice = "";
		
		return "ok";
	}
	
	
	private double priceToDouble(){
		String str = stornoPrice.replace(',', '.');
		Double d = Double.valueOf(str);
		return d.doubleValue();
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
	
	
	
}
