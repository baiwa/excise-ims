package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int020301DataVo {
	BigDecimal acceptValue;
	BigDecimal declineValue;
	String riskName;
	String risk;

	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	public BigDecimal getAcceptValue() {
		return acceptValue;
	}

	public void setAcceptValue(BigDecimal acceptValue) {
		this.acceptValue = acceptValue;
	}

	public BigDecimal getDeclineValue() {
		return declineValue;
	}

	public void setDeclineValue(BigDecimal declineValue) {
		this.declineValue = declineValue;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
}
