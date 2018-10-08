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
 * The persistent class for the IA_VERIFY_ACCOUNT_DETIL database table.
 * 
 */
@Entity
@Table(name="IA_VERIFY_ACCOUNT_DETIL")
public class VerifyAccountDetil extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 700817299726675684L;

	@Id
	@SequenceGenerator(name="IA_VERIFY_ACCOUNT_DETIL_GEN", sequenceName="IA_VERIFY_ACCOUNT_DETIL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_VERIFY_ACCOUNT_DETIL_GEN")
	@Column(name="VERIFY_ACCOUNT_DETIL_ID")
	private long verifyAccountDetilId;

	@Column(name="BRING_FORWARD")
	private BigDecimal bringForward;

	@Column(name="CARRY_FORWARD")
	private BigDecimal carryForward;

	private BigDecimal credit;

	private BigDecimal debit;

	@Column(name="LEDGER_ACCOUNT_NAME")
	private String ledgerAccountName;

	@Column(name="LEDGER_ACCOUNT_NUMBER")
	private String ledgerAccountNumber;

	@Column(name="VERIFY_ACCOUNT_HEADER_ID")
	private long verifyAccountHeaderId;

	public VerifyAccountDetil() {
	}

	public long getVerifyAccountDetilId() {
		return this.verifyAccountDetilId;
	}

	public void setVerifyAccountDetilId(long verifyAccountDetilId) {
		this.verifyAccountDetilId = verifyAccountDetilId;
	}

	public BigDecimal getBringForward() {
		return this.bringForward;
	}

	public void setBringForward(BigDecimal bringForward) {
		this.bringForward = bringForward;
	}

	public BigDecimal getCarryForward() {
		return this.carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDebit() {
		return this.debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public String getLedgerAccountName() {
		return this.ledgerAccountName;
	}

	public void setLedgerAccountName(String ledgerAccountName) {
		this.ledgerAccountName = ledgerAccountName;
	}

	public String getLedgerAccountNumber() {
		return this.ledgerAccountNumber;
	}

	public void setLedgerAccountNumber(String ledgerAccountNumber) {
		this.ledgerAccountNumber = ledgerAccountNumber;
	}

	public long getVerifyAccountHeaderId() {
		return verifyAccountHeaderId;
	}

	public void setVerifyAccountHeaderId(long verifyAccountHeaderId) {
		this.verifyAccountHeaderId = verifyAccountHeaderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}