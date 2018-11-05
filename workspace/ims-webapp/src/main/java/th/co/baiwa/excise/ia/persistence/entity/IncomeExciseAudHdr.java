package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_INCOME_EXCISE_AUD_RPT")
public class IncomeExciseAudHdr extends BaseEntity {

	private static final long serialVersionUID = 229504010338469300L;

	@Id
	@Column(name="IA_INCOME_EXCISE_AUD_RPT_ID")
	private Long iaIncomeExciseAudRptId;

	@Column(name="OFFICE_CODE")
	private String officeCode;

	@Column(name="OFFICE_NAME")
	private String officeName;

	@Column(name="ORIGIN")
	private String origin;

	@Column(name="RISK")
	private String risk;

	@Column(name="RISK_SCORE")
	private String riskScore;

	

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public String getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}

	public Long getIaIncomeExciseAudRptId() {
		return iaIncomeExciseAudRptId;
	}

	public void setIaIncomeExciseAudRptId(Long iaIncomeExciseAudRptId) {
		this.iaIncomeExciseAudRptId = iaIncomeExciseAudRptId;
	}

	

}