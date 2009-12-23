package basar.web;

import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import basar.domain.Seller;
import basar.domain.logic.BasarKasseFacade;

@Controller("sellerController")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class SellerController {

	private BasarKasseFacade basarKasse;
	
	private ListDataModel sellerList;
	
	private long basarNumber;
	
	private String name;
	
	private long basarNumberAt;
	
	private long basarNumberTo;
		
	public long getBasarNumberAt() {
		return basarNumberAt;
	}

	public void setBasarNumberAt(long basarNumberAt) {
		this.basarNumberAt = basarNumberAt;
	}

	public long getBasarNumberTo() {
		return basarNumberTo;
	}

	public void setBasarNumberTo(long basarNumberTo) {
		this.basarNumberTo = basarNumberTo;
	}

	@Autowired
	public void setBasarKase(BasarKasseFacade basarKasse){
		this.basarKasse = basarKasse;
	}
	
	public long getBasarNumber() {
		return basarNumber;
	}

	public void setBasarNumber(long basarNumber) {
		this.basarNumber = basarNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ListDataModel getSellerList(){
		this.sellerList = new ListDataModel(basarKasse.getSellerList());
		return sellerList;
	}
	
	public String newSellerAction(){
		return "new_seller";
	}

	private Seller mapToSeller() {
		Seller seller = new Seller();
		seller.setBasarNumber(basarNumber);
		seller.setName(name);
		return seller;
	}
	
	public String editSellerAction(){
		Seller seller = (Seller) sellerList.getRowData();
		basarNumber = seller.getBasarNumber();
		name = seller.getName();
		return "edit_seller";
	}
	
	public String insertSellerAction(){
		Seller seller = mapToSeller();
		basarKasse.insertSeller(seller);
		return "save_seller";
	}
	
	public String updateSellerAction(){
		Seller seller = mapToSeller();
		basarKasse.updateSeller(seller);
		return "save_seller";
	}
	
	public String deleteSellerAction(){
		Seller seller = (Seller) sellerList.getRowData();
		basarKasse.deleteSeller(seller);
		return "save_seller";
	}
	
	public String insertSellerListAction(){
		for (long number = basarNumberAt; number <= basarNumberTo; number++) {
			Seller seller = new Seller();
			seller.setBasarNumber(number);
			basarKasse.insertSeller(seller);
		}
		return "ok";
	}
	
}
