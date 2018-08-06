package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class ExciseTaxReceive extends BaseEntity {
	private BigDecimal exciseTaxReceiveId;
	private String exciseId;
	private String exciseTaxReceiveMonth;
	private String exciseTaxReceiveAmount;

	public BigDecimal getExciseTaxReceiveId() {
		return exciseTaxReceiveId;
	}

	public void setExciseTaxReceiveId(BigDecimal exciseTaxReceiveId) {
		this.exciseTaxReceiveId = exciseTaxReceiveId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getExciseTaxReceiveMonth() {
		return exciseTaxReceiveMonth;
	}

	public void setExciseTaxReceiveMonth(String exciseTaxReceiveMonth) {
		this.exciseTaxReceiveMonth = exciseTaxReceiveMonth;
	}

	public String getExciseTaxReceiveAmount() {
		return exciseTaxReceiveAmount;
	}

	public void setExciseTaxReceiveAmount(String exciseTaxReceiveAmount) {
		this.exciseTaxReceiveAmount = exciseTaxReceiveAmount;
	}

}
