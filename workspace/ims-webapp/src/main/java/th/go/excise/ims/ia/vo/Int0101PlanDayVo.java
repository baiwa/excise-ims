package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0101PlanDayVo {
	private BigDecimal planDayActivityId;
	private BigDecimal planHdrId;
	private BigDecimal planDtlId;
	private String dateStartActivity;
	private String dateEndActivity;
	private String activity;
	private String activityStatus;
	private String activityShort;
	private String colorCode;

	public String getDateStartActivity() {
		return dateStartActivity;
	}

	public void setDateStartActivity(String dateStartActivity) {
		this.dateStartActivity = dateStartActivity;
	}

	public String getDateEndActivity() {
		return dateEndActivity;
	}

	public void setDateEndActivity(String dateEndActivity) {
		this.dateEndActivity = dateEndActivity;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public BigDecimal getPlanDayActivityId() {
		return planDayActivityId;
	}

	public void setPlanDayActivityId(BigDecimal planDayActivityId) {
		this.planDayActivityId = planDayActivityId;
	}

	public BigDecimal getPlanHdrId() {
		return planHdrId;
	}

	public void setPlanHdrId(BigDecimal planHdrId) {
		this.planHdrId = planHdrId;
	}

	public BigDecimal getPlanDtlId() {
		return planDtlId;
	}

	public void setPlanDtlId(BigDecimal planDtlId) {
		this.planDtlId = planDtlId;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getActivityShort() {
		return activityShort;
	}

	public void setActivityShort(String activityShort) {
		this.activityShort = activityShort;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

}
