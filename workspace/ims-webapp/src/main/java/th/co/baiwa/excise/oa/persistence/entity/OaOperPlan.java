package th.co.baiwa.excise.oa.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name="OA_OPER_PLAN")
public class OaOperPlan extends BaseEntity {
	
	
	private static final long serialVersionUID = 3383104075726354863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_OPER_PLAN_GEN")
	@SequenceGenerator(name = "OA_OPER_PLAN_GEN", sequenceName = "OA_OPER_PLAN_SEQ", allocationSize = 1)
	@Column(name="OPER_PLAN_ID")
	private Long operPlanId;
	
	@Column(name="ANALYSIS_ID")
	private Long analysisId;
	
	@Column(name="APPROVER")
	private String approver;
	
	@Column(name="APPROVER_NAME")
	private String approverName;
	
	@Column(name="APPROVER_POSITION")
	private String approverPosition;
	
	@Column(name="KEY_INDICATORS")
	private String keyIndicators;
	
	@Column(name="MINIS_FINANCE_EXCISE")
	private String minisFinanceExcise;
	
	@Column(name="MINIS_FINANCE_STRAT")
	private String minisFinanceStrat;
	
	@Column(name="MINIS_FINANCE_TARGET")
	private String minisFinanceTarget;
	
	@Column(name="OBJECTIVE")
	private String objective;
	
	@Column(name="PHONE")
	private String phone;
	
	@Column(name="PLAN_DETAIL")
	private String planDetail;
	
	@Column(name="PLAN_END")
	private Timestamp planEnd;
	
	@Column(name="PLAN_START")
	private Timestamp planStart;
	
	@Column(name="PLANNER")
	private String planner;
	
	@Column(name="PRIMARY_NAME")
	private String primaryName;
	
	@Column(name="PRIMARY_POSITION")
	private String primaryPosition;
	
	@Column(name="PRIMARY_RESPONSE")
	private String primaryResponse;

	public Long getOperPlanId() {
		return operPlanId;
	}

	public void setOperPlanId(Long operPlanId) {
		this.operPlanId = operPlanId;
	}

	public Long getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getApproverPosition() {
		return approverPosition;
	}

	public void setApproverPosition(String approverPosition) {
		this.approverPosition = approverPosition;
	}

	public String getKeyIndicators() {
		return keyIndicators;
	}

	public void setKeyIndicators(String keyIndicators) {
		this.keyIndicators = keyIndicators;
	}

	public String getMinisFinanceExcise() {
		return minisFinanceExcise;
	}

	public void setMinisFinanceExcise(String minisFinanceExcise) {
		this.minisFinanceExcise = minisFinanceExcise;
	}

	public String getMinisFinanceStrat() {
		return minisFinanceStrat;
	}

	public void setMinisFinanceStrat(String minisFinanceStrat) {
		this.minisFinanceStrat = minisFinanceStrat;
	}

	public String getMinisFinanceTarget() {
		return minisFinanceTarget;
	}

	public void setMinisFinanceTarget(String minisFinanceTarget) {
		this.minisFinanceTarget = minisFinanceTarget;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPlanDetail() {
		return planDetail;
	}

	public void setPlanDetail(String planDetail) {
		this.planDetail = planDetail;
	}

	public Timestamp getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(Timestamp planEnd) {
		this.planEnd = planEnd;
	}

	public Timestamp getPlanStart() {
		return planStart;
	}

	public void setPlanStart(Timestamp planStart) {
		this.planStart = planStart;
	}

	public String getPlanner() {
		return planner;
	}

	public void setPlanner(String planner) {
		this.planner = planner;
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public String getPrimaryPosition() {
		return primaryPosition;
	}

	public void setPrimaryPosition(String primaryPosition) {
		this.primaryPosition = primaryPosition;
	}

	public String getPrimaryResponse() {
		return primaryResponse;
	}

	public void setPrimaryResponse(String primaryResponse) {
		this.primaryResponse = primaryResponse;
	}
	
	
	

}