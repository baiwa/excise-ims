package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PLAN_WORKSHEET_HDR")
public class TaPlanWorksheetHdr extends BaseEntity {

	private static final long serialVersionUID = 2222944496322667057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_WORKSHEET_HDR_GEN")
	@SequenceGenerator(name = "TA_PLAN_WORKSHEET_HDR_GEN", sequenceName = "TA_PLAN_WORKSHEET_HDR_SEQ", allocationSize = 1)
	@Column(name = "PLAN_WORKSHEET_HDR_ID")
	private Long planWorksheetHdrId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	@Column(name = "SEND_ALL_FLAG")
	private String sendAllFlag;
	@Column(name = "PLAN_STATUS")
	private String planStatus;
	@Column(name = "AUTH_COMMENT")
	private String authComment;
	@Column(name = "PLAN_COMMENT")
	private String planComment;

	public Long getPlanWorksheetHdrId() {
		return planWorksheetHdrId;
	}

	public void setPlanWorksheetHdrId(Long planWorksheetHdrId) {
		this.planWorksheetHdrId = planWorksheetHdrId;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getSendAllFlag() {
		return sendAllFlag;
	}

	public void setSendAllFlag(String sendAllFlag) {
		this.sendAllFlag = sendAllFlag;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getAuthComment() {
		return authComment;
	}

	public void setAuthComment(String authComment) {
		this.authComment = authComment;
	}

	public String getPlanComment() {
		return planComment;
	}

	public void setPlanComment(String planComment) {
		this.planComment = planComment;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
