package th.co.baiwa.excise.ta.persistence.entity;

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
@Table(name = "TA_YEAR_PLAN")
public class YearPlan extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8238842258229454796L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_YEAR_PLAN_GEN")
	@SequenceGenerator(name = "TA_YEAR_PLAN_GEN", sequenceName = "TA_YEAR_PLAN_SEQ", allocationSize = 1)
	@Column(name = "TA_YEAR_PLAN_ID")
	private BigDecimal taYearPlanId;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "EXCISE_ID")
	private String exciseId;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "COMPANY_ADDRESS")
	private String companyAddress;
	
	@Column(name = "EXCISE_AREA")
	private String exciseArea;
	
	@Column(name = "EXCISE_SUB_AREA")
	private String exciseSubArea;
	
	@Column(name = "PRODUCT")
	private String product;
	
	@Column(name = "RISK_TYPE")
	private String riskType;
	
	@Column(name = "FLAG")
	private String flag;
	
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;

	public BigDecimal getTaYearPlanId() {
		return taYearPlanId;
	}

	public void setTaYearPlanId(BigDecimal taYearPlanId) {
		this.taYearPlanId = taYearPlanId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getExciseArea() {
		return exciseArea;
	}

	public void setExciseArea(String exciseArea) {
		this.exciseArea = exciseArea;
	}

	public String getExciseSubArea() {
		return exciseSubArea;
	}

	public void setExciseSubArea(String exciseSubArea) {
		this.exciseSubArea = exciseSubArea;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

}
