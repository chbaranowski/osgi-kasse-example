package basar.domain;

import java.io.Serializable;

public class DocumentPosition implements Serializable {

	private int position = -1;
	
	private String price = "";
	
	private String description = "";

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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
	
	
}
