package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int061103Vo {

	private String name;
	private String pition;
	private String belong;
	private String mate;
	private String mateDescription;
	private String pitionMate;
	private String belongMate;
	private String status;
	private String type;
	private String typeRecive;
	private String typeReciveAmount;
	private String offer;
	private String offerType;
	private String sumAmount;

	List<Int061103VoChild> items;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPition() {
		return pition;
	}

	public void setPition(String pition) {
		this.pition = pition;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getMate() {
		return mate;
	}

	public void setMate(String mate) {
		this.mate = mate;
	}

	public String getMateDescription() {
		return mateDescription;
	}

	public void setMateDescription(String mateDescription) {
		this.mateDescription = mateDescription;
	}

	public String getPitionMate() {
		return pitionMate;
	}

	public void setPitionMate(String pitionMate) {
		this.pitionMate = pitionMate;
	}

	public String getBelongMate() {
		return belongMate;
	}

	public void setBelongMate(String belongMate) {
		this.belongMate = belongMate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeRecive() {
		return typeRecive;
	}

	public void setTypeRecive(String typeRecive) {
		this.typeRecive = typeRecive;
	}

	public String getTypeReciveAmount() {
		return typeReciveAmount;
	}

	public void setTypeReciveAmount(String typeReciveAmount) {
		this.typeReciveAmount = typeReciveAmount;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public String getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}

	public List<Int061103VoChild> getItems() {
		return items;
	}

	public void setItems(List<Int061103VoChild> items) {
		this.items = items;
	}

}
