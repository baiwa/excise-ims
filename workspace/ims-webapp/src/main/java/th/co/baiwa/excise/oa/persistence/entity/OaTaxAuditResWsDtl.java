package th.co.baiwa.excise.oa.persistence.entity;

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
@Table(name="OA_TAX_AUDIT_RES_WS_DTL")
public class OaTaxAuditResWsDtl extends BaseEntity {
	
	private static final long serialVersionUID = -2829873714240214603L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_TAX_AUDIT_RES_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_TAX_AUDIT_RES_WS_DTL_GEN", sequenceName = "OA_TAX_AUDIT_RES_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="TAX_AUDIT_RESULT_WS_DTL_ID")
	private Long taxAuditResultWsDtlId;
	
	@Column(name="COST")
	private BigDecimal cost;

	@Column(name="EXTRA_MONEY")
	private BigDecimal extraMoney;
	
	@Column(name="FINE")
	private BigDecimal fine;
	
	@Column(name="FINE_TAX")
	private BigDecimal fineTax;

	@Column(name="LOCAL_TAX")
	private BigDecimal localTax;
	
	@Column(name="MONTH")
	private String month;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="TAX_AUDIT_RESULT_WS_HDR_ID")
	private String taxAuditResultWsDtlOrder;
	
	@Column(name="TAX_AUDIT_RESULT_WS_DTL_ORDER")
	private Long taxAuditResultWsHdrId;
	
	@Column(name="TAX_PERCENTAGE")
	private BigDecimal taxPercentage;

	@Column(name="VALUE")
	private String value;

	public Long getTaxAuditResultWsDtlId() {
		return taxAuditResultWsDtlId;
	}

	public void setTaxAuditResultWsDtlId(Long taxAuditResultWsDtlId) {
		this.taxAuditResultWsDtlId = taxAuditResultWsDtlId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getExtraMoney() {
		return extraMoney;
	}

	public void setExtraMoney(BigDecimal extraMoney) {
		this.extraMoney = extraMoney;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public BigDecimal getFineTax() {
		return fineTax;
	}

	public void setFineTax(BigDecimal fineTax) {
		this.fineTax = fineTax;
	}

	public BigDecimal getLocalTax() {
		return localTax;
	}

	public void setLocalTax(BigDecimal localTax) {
		this.localTax = localTax;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTaxAuditResultWsDtlOrder() {
		return taxAuditResultWsDtlOrder;
	}

	public void setTaxAuditResultWsDtlOrder(String taxAuditResultWsDtlOrder) {
		this.taxAuditResultWsDtlOrder = taxAuditResultWsDtlOrder;
	}

	public Long getTaxAuditResultWsHdrId() {
		return taxAuditResultWsHdrId;
	}

	public void setTaxAuditResultWsHdrId(Long taxAuditResultWsHdrId) {
		this.taxAuditResultWsHdrId = taxAuditResultWsHdrId;
	}

	public BigDecimal getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(BigDecimal taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	

}