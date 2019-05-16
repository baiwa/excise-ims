
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_LICDUP_D")
public class IaAuditLicdupD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2290835067801091771L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LICDUP_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LICDUP_D_GEN", sequenceName = "IA_AUDIT_LICDUP_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LICDUP_D_SEQ")
	private Long auditLicdupDSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "RUN_CHECK")
	private Integer runCheck;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "LIC_DATE")
	private Date licDate;
	@Column(name = "PRINT_COUNT")
	private BigDecimal printCount;
	@Column(name = "AUDIT_FLAG")
	private String auditFlag;
	@Column(name = "CONDITION_TEXT")
	private String conditionText;
	@Column(name = "CRITERIA_TEXT")
	private String criteriaText;

	public Long getAuditLicdupDSeq() {
		return auditLicdupDSeq;
	}

	public void setAuditLicdupDSeq(Long auditLicdupDSeq) {
		this.auditLicdupDSeq = auditLicdupDSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public Integer getRunCheck() {
		return runCheck;
	}

	public void setRunCheck(Integer runCheck) {
		this.runCheck = runCheck;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public Date getLicDate() {
		return licDate;
	}

	public void setLicDate(Date licDate) {
		this.licDate = licDate;
	}

	public BigDecimal getPrintCount() {
		return printCount;
	}

	public void setPrintCount(BigDecimal printCount) {
		this.printCount = printCount;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getConditionText() {
		return conditionText;
	}

	public void setConditionText(String conditionText) {
		this.conditionText = conditionText;
	}

	public String getCriteriaText() {
		return criteriaText;
	}

	public void setCriteriaText(String criteriaText) {
		this.criteriaText = criteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
