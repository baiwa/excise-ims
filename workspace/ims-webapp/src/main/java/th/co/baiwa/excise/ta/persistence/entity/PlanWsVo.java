package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

public class PlanWsVo extends PlanFromWsHeader{

	private static final long serialVersionUID = 1L;
	
	
	private BigDecimal avgAmount;


	public BigDecimal getAvgAmount() {
		return avgAmount;
	}


	public void setAvgAmount(BigDecimal avgAmount) {
		this.avgAmount = avgAmount;
	}

}
