package th.co.baiwa.excise.oa.persistence.entity;

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
	
	@Column(name="MINIS_FINANCE_TARGET")
	private String minisFinanceTarget;
	
	@Column(name="MINIS_FINANCE_STRAT")
	private String minisFinanceStrat;
	
	@Column(name="MINIS_FINANCE_EXCISE")
	private String minisFinanceExcise;
	
	@Column(name="OBJECTIVE_1")
	private String objective1;
	
	@Column(name="OBJECTIVE_2")
	private String objective2;
	
	@Column(name="OBJECTIVE_3")
	private String objective3;
	
	@Column(name="OBJECTIVE_4")
	private String objective4;
	
	@Column(name="OBJECTIVE_5")
	private String objective5;
	
	@Column(name="STRATEGY")
	private String strategy;
	
	@Column(name="KEY_INDICATORS")
	private String keyIndicators;
	
	@Column(name="PLANNER")
	private String planner;
	
	@Column(name="PRIMARY_RESPONSE")
	private String primaryResponse;
	
	@Column(name="PRIMARY_NAME")
	private String primaryName;
	
	@Column(name="PRIMARY_POSITION")
	private String primaryPosition;
	
	@Column(name="APPROVER")
	private String approver;
	
	@Column(name="APPROVER_NAME")
	private String approverName;
	
	@Column(name="APPROVER_POSITION")
	private String approverPosition;
	
	@Column(name="PLAN_START")
	private String planStart;
	
	@Column(name="PLAN_END")
	private String planEnd;
	
	@Column(name="PHONE")
	private String phone;

	@Column(name="PLAN_DETAIL")
	private String planDetail;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	
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

	public String getObjective1() {
		return objective1;
	}

	public void setObjective1(String objective1) {
		this.objective1 = objective1;
	}

	public String getObjective2() {
		return objective2;
	}

	public void setObjective2(String objective2) {
		this.objective2 = objective2;
	}

	public String getObjective3() {
		return objective3;
	}

	public void setObjective3(String objective3) {
		this.objective3 = objective3;
	}

	public String getObjective4() {
		return objective4;
	}

	public void setObjective4(String objective4) {
		this.objective4 = objective4;
	}

	public String getObjective5() {
		return objective5;
	}

	public void setObjective5(String objective5) {
		this.objective5 = objective5;
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

	public String getPlanStart() {
		return planStart;
	}

	public void setPlanStart(String planStart) {
		this.planStart = planStart;
	}

	public String getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(String planEnd) {
		this.planEnd = planEnd;
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

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	
	

}