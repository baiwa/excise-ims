package th.co.baiwa.excise.ta.persistence.vo;

import java.util.Date;

public class PlanFromWsVo{
	
	private Date startDate;
	private Date endDate;
	private Integer monthNotPayRisk;
	private Integer percentDiff;
	
	

	public Integer getMonthNotPayRisk() {
		return monthNotPayRisk;
	}
	public void setMonthNotPayRisk(Integer monthNotPayRisk) {
		this.monthNotPayRisk = monthNotPayRisk;
	}
	public Integer getPercentDiff() {
		return percentDiff;
	}
	public void setPercentDiff(Integer percentDiff) {
		this.percentDiff = percentDiff;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
