package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_TUITION_FEE")

public class TuitionFee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5442232110852382418L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_TUITION_FEE_GEN")
	@SequenceGenerator(name = "IA_TUITION_FEE_GEN", sequenceName = "IA_TUITION_FEE_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PITION")
	private String pition;
	@Column(name = "BELONG")
	private String belong;
	@Column(name = "MATE")
	private String mate;
	@Column(name = "PITION_MATE")
	private String pitionMate;
	@Column(name = "BELONG_MATE")
	private String belongMate;
	@Column(name = "MATE_DESCRIPTION")
	private String mateDescription;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "TYPE_RECIVE")
	private String typeRecive;
	@Column(name = "TYPE_RECIVE_AMOUNT")
	private BigDecimal typeReciveAmount;
	@Column(name = "OFFER")
	private String offer;
	@Column(name = "OFFER_TYPE")
	private String offerType;
	@Column(name = "SUM_AMOUNT")
	private BigDecimal sumAmount;
	@Column(name = "STATUS_CHECK")
	private String statusCheck;
	@Column(name = "IA_DIS_REQ_ID")
	private BigDecimal iaDisReqId;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
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
	public String getMateDescription() {
		return mateDescription;
	}
	public void setMateDescription(String mateDescription) {
		this.mateDescription = mateDescription;
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
	public BigDecimal getTypeReciveAmount() {
		return typeReciveAmount;
	}
	public void setTypeReciveAmount(BigDecimal typeReciveAmount) {
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
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getStatusCheck() {
		return statusCheck;
	}
	public void setStatusCheck(String statusCheck) {
		this.statusCheck = statusCheck;
	}
	public BigDecimal getIaDisReqId() {
		return iaDisReqId;
	}
	public void setIaDisReqId(BigDecimal iaDisReqId) {
		this.iaDisReqId = iaDisReqId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}