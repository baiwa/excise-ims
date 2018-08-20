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
@Table(name = "IA_RISK_ASS_INF_HDR")
public class RiskAssInfHdr extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3325157235147358432L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_INF_HDR_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_INF_HDR_GEN", sequenceName = "IA_RISK_ASS_INF_HDR_SEQ", allocationSize = 1)
	@Column(name = "RISK_ASS_INF_HDR_ID")
	private Long riskAssInfHdrId;

	@Column(name = "RISK_ASS_INF_HDR_NAME")
	private String riskAssInfHdrName;

	@Column(name = "RISK_INF_PAPER_NAME")
	private String riskInfPaperName;

	@Column(name = "BUDGET_YEAR")
	private String budgetYear;

	@Column(name = "USER_CHECK")
	private String userCheck;

	@Column(name = "ACTIVE")
	private String active;

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getUserCheck() {
		return userCheck;
	}

	public void setUserCheck(String userCheck) {
		this.userCheck = userCheck;
	}

	public Long getRiskAssInfHdrId() {
		return riskAssInfHdrId;
	}

	public void setRiskAssInfHdrId(Long riskAssInfHdrId) {
		this.riskAssInfHdrId = riskAssInfHdrId;
	}

	public String getRiskAssInfHdrName() {
		return riskAssInfHdrName;
	}

	public void setRiskAssInfHdrName(String riskAssInfHdrName) {
		this.riskAssInfHdrName = riskAssInfHdrName;
	}

	public String getRiskInfPaperName() {
		return riskInfPaperName;
	}

	public void setRiskInfPaperName(String riskInfPaperName) {
		this.riskInfPaperName = riskInfPaperName;
	}
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
