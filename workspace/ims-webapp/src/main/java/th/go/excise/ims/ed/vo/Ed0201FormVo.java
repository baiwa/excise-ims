package th.go.excise.ims.ed.vo;

import java.math.BigDecimal;

public class Ed0201FormVo {

	private BigDecimal edPersonSeq;
	private String edPositionName;
	private String allowancesHalfDay;
	private String allowancesDay;
	private String accomFeeSingle;
	private String accomFeeDouble;
	private String accomFeePackages;

	public BigDecimal getEdPersonSeq() {
		return edPersonSeq;
	}

	public void setEdPersonSeq(BigDecimal edPersonSeq) {
		this.edPersonSeq = edPersonSeq;
	}

	public String getEdPositionName() {
		return edPositionName;
	}

	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}

	public String getAllowancesHalfDay() {
		return allowancesHalfDay;
	}

	public void setAllowancesHalfDay(String allowancesHalfDay) {
		this.allowancesHalfDay = allowancesHalfDay;
	}

	public String getAllowancesDay() {
		return allowancesDay;
	}

	public void setAllowancesDay(String allowancesDay) {
		this.allowancesDay = allowancesDay;
	}

	public String getAccomFeeSingle() {
		return accomFeeSingle;
	}

	public void setAccomFeeSingle(String accomFeeSingle) {
		this.accomFeeSingle = accomFeeSingle;
	}

	public String getAccomFeeDouble() {
		return accomFeeDouble;
	}

	public void setAccomFeeDouble(String accomFeeDouble) {
		this.accomFeeDouble = accomFeeDouble;
	}

	public String getAccomFeePackages() {
		return accomFeePackages;
	}

	public void setAccomFeePackages(String accomFeePackages) {
		this.accomFeePackages = accomFeePackages;
	}

}
