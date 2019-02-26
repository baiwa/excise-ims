package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;
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
@Table(name = "TA_PLAN_WORKSHEET_DTL")
public class TaPlanWorksheetDtl extends BaseEntity {

	private static final long serialVersionUID = 196341662627564519L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_DTL_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_DTL_GEN", sequenceName = "TA_PLAN_WORKSHEET_DTL_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_DTL_ID")
	private Long planWorksheetDtlId;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "PLAN_TYPE")
	private String planType;
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;
	@Column(name = "AUDIT_TYPE")
	private String auditType;
	@Column(name = "AUDIT_START_DATE")
	private LocalDate auditStartDate;
	@Column(name = "AUDIT_END_DATE")
	private LocalDate auditEndDate;

	public Long getPlanWorksheetDtlId() {
		return planWorksheetDtlId;
	}

	public void setPlanWorksheetDtlId(Long planWorksheetDtlId) {
		this.planWorksheetDtlId = planWorksheetDtlId;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public LocalDate getAuditStartDate() {
		return auditStartDate;
	}

	public void setAuditStartDate(LocalDate auditStartDate) {
		this.auditStartDate = auditStartDate;
	}

	public LocalDate getAuditEndDate() {
		return auditEndDate;
	}

	public void setAuditEndDate(LocalDate auditEndDate) {
		this.auditEndDate = auditEndDate;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
