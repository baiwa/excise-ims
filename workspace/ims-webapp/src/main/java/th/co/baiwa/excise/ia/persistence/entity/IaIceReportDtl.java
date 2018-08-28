package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="IA_ICE_REPORT_DTL")
public class IaIceReportDtl extends BaseEntity {
	
	private static final long serialVersionUID = 5464541546731428734L;
	
	@Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_ICE_REPORT_DTL_GEN" )
	@SequenceGenerator(name = "IA_ICE_REPORT_DTL_GEN", sequenceName = "IA_ICE_REPORT_DTL_SEQ", allocationSize = 1)
	@Column(name="ICE_REPORT_DTL_ID")
	private Long iceReportDtlId;
	
	@Column(name="ICE_REPORT_HDR_ID")
	private Long iceReportHdrId;
	
	@Column(name="WORK_OF_EVALUATION")
	private String workOfEvaluation;
	
	@Column(name="ACTIVITIES")
	private String activities;
	
	@Column(name="OBJECTIVE")
	private String objective;
	
	@Column(name="EXISTING_CONTROL")
	private String exitingControl;
	
	@Column(name="EVALUATION_CONTROL")
	private String evaluationControl;
	
	@Column(name="RISK_STILL")
	private String riskStill;
	
	@Column(name="IMPROVEMENT_CONTROL")
	private String improvementControl;
	
	@Column(name="RESPONSIBLE_PERSON")
	private String responsiblePerson;
	
	@Column(name="NOTE")
	private String note;
	
	@Column(name="RESULT")
	private String result;

	public Long getIceReportDtlId() {
		return iceReportDtlId;
	}

	public void setIceReportDtlId(Long iceReportDtlId) {
		this.iceReportDtlId = iceReportDtlId;
	}

	public Long getIceReportHdrId() {
		return iceReportHdrId;
	}

	public void setIceReportHdrId(Long iceReportHdrId) {
		this.iceReportHdrId = iceReportHdrId;
	}

	public String getWorkOfEvaluation() {
		return workOfEvaluation;
	}

	public void setWorkOfEvaluation(String workOfEvaluation) {
		this.workOfEvaluation = workOfEvaluation;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getExitingControl() {
		return exitingControl;
	}

	public void setExitingControl(String exitingControl) {
		this.exitingControl = exitingControl;
	}

	public String getEvaluationControl() {
		return evaluationControl;
	}

	public void setEvaluationControl(String evaluationControl) {
		this.evaluationControl = evaluationControl;
	}

	public String getRiskStill() {
		return riskStill;
	}

	public void setRiskStill(String riskStill) {
		this.riskStill = riskStill;
	}

	public String getImprovementControl() {
		return improvementControl;
	}

	public void setImprovementControl(String improvementControl) {
		this.improvementControl = improvementControl;
	}

	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	

	
	
}
