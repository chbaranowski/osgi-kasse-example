package basar.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Position implements Serializable {

	@EmbeddedId
	private PositionKey positionKey;
		
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Seller.class, optional=false)
	private Seller seller;
	
	@Basic(optional=false)
	private Date createTime;

	@Basic(optional=false)
	private long price;
	
	@Basic
	private String description;
	
	@Basic(optional=false)
	private PositionType type = PositionType.SALE;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public PositionType getType() {
		return type;
	}

	public void setType(PositionType type) {
		this.type = type;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PositionKey getPositionKey() {
		return positionKey;
	}

	public void setPositionKey(PositionKey positionKey) {
		this.positionKey = positionKey;
	}
	
	public String getPrintPrice() {
		return PriceFormatUtil.formatPriceLongToString(price);
	}
	
}
