package th.co.baiwa.excise.ia.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_CWP_TBL_HDR database table.
 * 
 */
@Entity
@Table(name="IA_CWP_TBL_HDR")
public class CwpTblHdr extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5422395866914211106L;

	@Id
	@SequenceGenerator(name="IA_CWP_TBL_HDR_GEN", sequenceName="IA_CWP_TBL_HDR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_CWP_TBL_HDR_GEN")
	@Column(name="CWP_TBL_HDR_ID")
	private long cwpTblHdrId;

	private String department;

	@Column(name="DISBURSEMENT_CODE")
	private String disbursementCode;

	@Column(name="DISBURSEMENT_NAME")
	private String disbursementName;

	@Column(name="REPORT_DATE")
	private Timestamp reportDate;

	@Column(name="TRIAL_BALANCE_TYPE")
	private String trialBalanceType;

	public CwpTblHdr() {
	}

	public long getCwpTblHdrId() {
		return this.cwpTblHdrId;
	}

	public void setCwpTblHdrId(long cwpTblHdrId) {
		this.cwpTblHdrId = cwpTblHdrId;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDisbursementCode() {
		return this.disbursementCode;
	}

	public void setDisbursementCode(String disbursementCode) {
		this.disbursementCode = disbursementCode;
	}

	public String getDisbursementName() {
		return this.disbursementName;
	}

	public void setDisbursementName(String disbursementName) {
		this.disbursementName = disbursementName;
	}

	public Timestamp getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	public String getTrialBalanceType() {
		return this.trialBalanceType;
	}

	public void setTrialBalanceType(String trialBalanceType) {
		this.trialBalanceType = trialBalanceType;
	}

}