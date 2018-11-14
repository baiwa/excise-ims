package th.co.baiwa.excise.ta.persistence.vo;

import java.math.BigDecimal;

public class Tsl010600Vo {

	private BigDecimal taYearPlanId;
	private String userId;
	private String exciseId;
	private String companyName;
	private String companyAddress;
	private String exciseArea;
	private String exciseSubArea;
	private String product;
	private String riskType;
	private String riskTypeDesc;
	private String flag;
	private String flagDesc;

	public BigDecimal getTaYearPlanId() {
		return taYearPlanId;
	}

	public String getUserId() {
		return userId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public String getExciseArea() {
		return exciseArea;
	}

	public String getExciseSubArea() {
		return exciseSubArea;
	}

	public String getProduct() {
		return product;
	}

	public String getRiskType() {
		return riskType;
	}

	public String getRiskTypeDesc() {
		return riskTypeDesc;
	}

	public String getFlag() {
		return flag;
	}

	public String getFlagDesc() {
		return flagDesc;
	}

	public void setTaYearPlanId(BigDecimal taYearPlanId) {
		this.taYearPlanId = taYearPlanId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public void setExciseArea(String exciseArea) {
		this.exciseArea = exciseArea;
	}

	public void setExciseSubArea(String exciseSubArea) {
		this.exciseSubArea = exciseSubArea;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public void setRiskTypeDesc(String riskTypeDesc) {
		this.riskTypeDesc = riskTypeDesc;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setFlagDesc(String flagDesc) {
		this.flagDesc = flagDesc;
	}

}
