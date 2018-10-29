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

	@Column(name = "ACTIVE")
	private String active;
	
	@Column(name = "PERCENT")
	private BigDecimal percent;
	
//	###################### new Field ########################
	
	@Column(name = "CHECK_USER_TITLE")
	private String checkUserTitle;
	
	@Column(name = "CHECK_USER_NAME")
	private String checkUserName;
	
	@Column(name = "CHECK_POSITION")
	private String checkPosition;
	
	@Column(name = "CREATE_USER_TITLE")
	private String createUserTitle;
	
	@Column(name = "CREATE_USER_NAME")
	private String createUserName;
	
	@Column(name = "CREATE_POSITIONN")
	private String createPosition;
	
	@Column(name = "CREATE_LAST_NAME")
	private String createLastName;
	
	@Column(name = "CHECK_LAST_NAME")
	private String checkLastName;

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
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
	
	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getCheckUserTitle() {
		return checkUserTitle;
	}

	public void setCheckUserTitle(String checkUserTitle) {
		this.checkUserTitle = checkUserTitle;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}

	public String getCheckPosition() {
		return checkPosition;
	}

	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}

	public String getCreateUserTitle() {
		return createUserTitle;
	}

	public void setCreateUserTitle(String createUserTitle) {
		this.createUserTitle = createUserTitle;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreatePosition() {
		return createPosition;
	}

	public void setCreatePosition(String createPosition) {
		this.createPosition = createPosition;
	}

	public String getCreateLastName() {
		return createLastName;
	}

	public void setCreateLastName(String createLastName) {
		this.createLastName = createLastName;
	}

	public String getCheckLastName() {
		return checkLastName;
	}

	public void setCheckLastName(String checkLastName) {
		this.checkLastName = checkLastName;
	}
	
}
