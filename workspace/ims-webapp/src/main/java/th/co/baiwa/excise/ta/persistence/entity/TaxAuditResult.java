package th.co.baiwa.excise.ta.persistence.entity;

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
@Table(name = "TA_TAX_AUDIT_RESULT")
public class TaxAuditResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1485361205790440277L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_TAX_AUDIT_RESULT_GEN")
	@SequenceGenerator(name = "TA_TAX_AUDIT_RESULT_GEN", sequenceName = "TA_TAX_AUDIT_RESULT_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "EXCISE_ID")
	private String exciseId;
	@Column(name = "ANALYS_NUMBER")
	private String analysNumber;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "ADDED_TAX")
	private BigDecimal addedTax;
	@Column(name = "FINE")
	private BigDecimal fine;
	@Column(name = "EXTRA_MONEY")
	private BigDecimal extraMoney;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getAnalysNumber() {
		return analysNumber;
	}

	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public BigDecimal getAddedTax() {
		return addedTax;
	}

	public void setAddedTax(BigDecimal addedTax) {
		this.addedTax = addedTax;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public BigDecimal getExtraMoney() {
		return extraMoney;
	}

	public void setExtraMoney(BigDecimal extraMoney) {
		this.extraMoney = extraMoney;
	}

}
