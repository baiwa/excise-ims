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


/**
 * The persistent class for the IA_PROCUREMENT_LIST database table.
 * 
 */
@Entity
@Table(name="IA_PROCUREMENT_LIST")
public class IaProcurementList extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5503404191982673441L;

	@Id
	@SequenceGenerator(name="IA_PROCUREMENT_LIST_GEN", sequenceName="IA_PROCUREMENT_LIST_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_PROCUREMENT_LIST_GEN")
	@Column(name="PROCUREMENT_LIST_ID")
	private Long procurementListId;

	private Long amount;

	@Column(name="APPRAISAL_PRICE")
	private Long appraisalPrice;

	@Column(name="PRESET_PRICE")
	private Long presetPrice;

	private Long price;

	@Column(name="PROCUREMENT_ID")
	private BigDecimal procurementId;

	@Column(name="PROCUREMENT_LIST")
	private String procurementList;

	private String unit;

	@Column(name="UNIT_PRICE")
	private Long unitPrice;

	public Long getProcurementListId() {
		return procurementListId;
	}

	public void setProcurementListId(Long procurementListId) {
		this.procurementListId = procurementListId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getAppraisalPrice() {
		return appraisalPrice;
	}

	public void setAppraisalPrice(Long appraisalPrice) {
		this.appraisalPrice = appraisalPrice;
	}

	public Long getPresetPrice() {
		return presetPrice;
	}

	public void setPresetPrice(Long presetPrice) {
		this.presetPrice = presetPrice;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public BigDecimal getProcurementId() {
		return procurementId;
	}

	public void setProcurementId(BigDecimal procurementId) {
		this.procurementId = procurementId;
	}

	public String getProcurementList() {
		return procurementList;
	}

	public void setProcurementList(String procurementList) {
		this.procurementList = procurementList;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

}