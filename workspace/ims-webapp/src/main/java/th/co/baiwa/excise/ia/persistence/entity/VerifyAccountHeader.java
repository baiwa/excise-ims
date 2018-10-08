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
 * The persistent class for the IA_VERIFY_ACCOUNT_HEADER database table.
 * 
 */
@Entity
@Table(name="IA_VERIFY_ACCOUNT_HEADER")
public class VerifyAccountHeader extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2811501656352160101L;

	@Id
	@SequenceGenerator(name="IA_VERIFY_ACCOUNT_HEADER_GEN", sequenceName="IA_VERIFY_ACCOUNT_HEADER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_VERIFY_ACCOUNT_HEADER_GEN")
	@Column(name="VERIFY_ACCOUNT_HEADER_ID")
	private long verifyAccountHeaderId;

	private String department;

	@Column(name="DISBURSEMENT_CODE")
	private String disbursementCode;

	@Column(name="DISBURSEMENT_NAME")
	private String disbursementName;

	@Column(name="REPORT_DATE")
	private String reportDate;

	@Column(name="REPORT_TIME")
	private String reportTime;

	public VerifyAccountHeader() {
	}

	public long getVerifyAccountHeaderId() {
		return verifyAccountHeaderId;
	}

	public void setVerifyAccountHeaderId(long verifyAccountHeaderId) {
		this.verifyAccountHeaderId = verifyAccountHeaderId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDisbursementCode() {
		return disbursementCode;
	}

	public void setDisbursementCode(String disbursementCode) {
		this.disbursementCode = disbursementCode;
	}

	public String getDisbursementName() {
		return disbursementName;
	}

	public void setDisbursementName(String disbursementName) {
		this.disbursementName = disbursementName;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}