package th.co.baiwa.excise.ta.persistence.entity;

import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;



@Repository
public class ExciseTax extends BaseEntity{
	private Integer exciseTaxReceiveId;
	private String exciseId;
	private String exciseTaxReceiveMonth;
	private String exciseTaxReceiveAmount;

	public Integer getExciseTaxReceiveId() {
		return exciseTaxReceiveId;
	}

	public void setExciseTaxReceiveId(Integer exciseTaxReceiveId) {
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