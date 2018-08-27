package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name = "IA_RISK_ASS_RISK_WS_HDR")
public class RiskAssRiskWsHdr extends BaseEntity{
	
	
	private static final long serialVersionUID = 7005171568952161795L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_RISK_WS_HDR_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_RISK_WS_HDR_GEN", sequenceName = "IA_RISK_ASS_RISK_WS_HDR_SEQ", allocationSize = 1)
	@Column(name = "RISK_HRD_ID")
	private Long riskHrdId;
	
	@Column(name = "RISK_HDR_NAME")
	private String riskHdrName;
	
	@Column(name = "ACTIVE")
	private String active;
	
	@Column(name = "RISK_HDR_PAPER_NAME")
	private String riskHrdPaperName;
	
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	
	@Column(name = "USER_CHECK")
	private String userCheck;

	@Column(name = "PERCENT")
	private BigDecimal percent;
	
	public Long getRiskHrdId() {
		return riskHrdId;
	}

	public void setRiskHrdId(Long riskHrdId) {
		this.riskHrdId = riskHrdId;
	}

	public String getRiskHdrName() {
		return riskHdrName;
	}

	public void setRiskHdrName(String riskHdrName) {
		this.riskHdrName = riskHdrName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRiskHrdPaperName() {
		return riskHrdPaperName;
	}

	public void setRiskHrdPaperName(String riskHrdPaperName) {
		this.riskHrdPaperName = riskHrdPaperName;
	}

	public String getUserCheck() {
		return userCheck;
	}

	public void setUserCheck(String userCheck) {
		this.userCheck = userCheck;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	
	
}