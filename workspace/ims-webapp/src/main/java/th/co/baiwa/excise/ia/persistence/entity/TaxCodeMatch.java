package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/**
 * The persistent class for the IA_TAX_CODE_MATCH database table.
 * 
 */
@Entity
@Table(name = "IA_TAX_CODE_MATCH")
public class TaxCodeMatch extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3605183852693667791L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_TAX_CODE_MATCH_GEN")
	@SequenceGenerator(name = "IA_TAX_CODE_MATCH_GEN", sequenceName = "IA_TAX_CODE_MATCH_SEQ", allocationSize = 1)
	@Column(name = "TAX_CODE_ID")
	private Long taxCodeId;

	@Column(name = "LEDGER_ACCOUNT_NAME")
	private String ledgerAccountName;

	@Column(name = "LEDGER_ACCOUNT_NUMBER")
	private String ledgerAccountNumber;

	@Column(name = "TAX_NAME")
	private String taxName;

	@Column(name = "TAX_NUMBER")
	private String taxNumber;

	public Long getTaxCodeId() {
		return taxCodeId;
	}

	public void setTaxCodeId(Long taxCodeId) {
		this.taxCodeId = taxCodeId;
	}

	public String getLedgerAccountName() {
		return ledgerAccountName;
	}

	public void setLedgerAccountName(String ledgerAccountName) {
		this.ledgerAccountName = ledgerAccountName;
	}

	public String getLedgerAccountNumber() {
		return ledgerAccountNumber;
	}

	public void setLedgerAccountNumber(String ledgerAccountNumber) {
		this.ledgerAccountNumber = ledgerAccountNumber;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
}