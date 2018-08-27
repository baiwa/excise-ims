package th.co.baiwa.excise.oa.persistence.vo;

import java.util.Date;

public class COP013SearchResponseFormVo {

	private Date createdDate;
	private String planName;
	private String planStart;

	 public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanStart() {
		return planStart;
	}

	public void setPlanStart(String planStart) {
		this.planStart = planStart;
	}

}
