package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_CWP_TBL_DTL database table.
 * 
 */
@Entity
@Table(name="IA_CWP_TBL_DTL")
public class CwpTblDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3645128927857966937L;

	@Id
	@SequenceGenerator(name="IA_CWP_TBL_DTL_GEN", sequenceName="IA_CWP_TBL_DTL_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_CWP_TBL_DTL_GEN")
	@Column(name="CWP_TBL_DTL_ID")
	private Long cwpTblDtlId;

	@Column(name="BRING_FORWARD")
	private BigDecimal bringForward;

	@Column(name="CARRY_FORWARD")
	private BigDecimal carryForward;

	private BigDecimal credit;

	@Column(name="CWP_TBL_HDR_ID")
	private Long cwpTblHdrId;

	private BigDecimal debit;

	@Column(name="LEDGER_ACCOUNT_NAME")
	private String ledgerAccountName;

	@Column(name="LEDGER_ACCOUNT_NUMBER")
	private String ledgerAccountNumber;
	
	@Transient
	private BigDecimal diff;

	public Long getCwpTblDtlId() {
		return cwpTblDtlId;
	}

	public void setCwpTblDtlId(Long cwpTblDtlId) {
		this.cwpTblDtlId = cwpTblDtlId;
	}

	public BigDecimal getBringForward() {
		return bringForward;
	}

	public void setBringForward(BigDecimal bringForward) {
		this.bringForward = bringForward;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public Long getCwpTblHdrId() {
		return cwpTblHdrId;
	}

	public void setCwpTblHdrId(Long cwpTblHdrId) {
		this.cwpTblHdrId = cwpTblHdrId;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
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

	public BigDecimal getDiff() {
		return diff;
	}

	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

}