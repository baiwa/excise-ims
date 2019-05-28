package th.go.excise.ims.ta.persistence.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_BA_H")
public class TaPaperBaH extends BaseEntity {

	private static final long serialVersionUID = -368103151689269120L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_H_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_H_GEN", sequenceName = "TA_PAPER_BA_H_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_H_SEQ")
	private Long paperBaHSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DUTY_GROUP_ID")
	private String dutyGroupId;
	@Column(name = "BA_DATE_START")
	private LocalDate baDateStart;
	@Column(name = "BA_DATE_END")
	private LocalDate baDateEnd;
	@Column(name = "BA_TEXT")
	private String baText;

	public Long getPaperBaHSeq() {
		return paperBaHSeq;
	}

	public void setPaperBaHSeq(Long paperBaHSeq) {
		this.paperBaHSeq = paperBaHSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String getPaperBaCode() {
		return paperBaCode;
	}

	public void setPaperBaCode(String paperBaCode) {
		this.paperBaCode = paperBaCode;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public LocalDate getBaDateStart() {
		return baDateStart;
	}

	public void setBaDateStart(LocalDate baDateStart) {
		this.baDateStart = baDateStart;
	}

	public LocalDate getBaDateEnd() {
		return baDateEnd;
	}

	public void setBaDateEnd(LocalDate baDateEnd) {
		this.baDateEnd = baDateEnd;
	}

	public String getBaText() {
		return baText;
	}

	public void setBaText(String baText) {
		this.baText = baText;
	}

}
