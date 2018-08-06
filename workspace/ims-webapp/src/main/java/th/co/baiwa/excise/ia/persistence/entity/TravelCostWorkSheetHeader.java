package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class TravelCostWorkSheetHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5680513329445529721L;
	private BigDecimal workSheetHeaderId;
	private String workSheetHeaderName;
	private String departmentName;
	private Date startDate;
	private Date endDate;
	private String description;

	public BigDecimal getWorkSheetHeaderId() {
		return workSheetHeaderId;
	}

	public void setWorkSheetHeaderId(BigDecimal workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
	}

	public String getWorkSheetHeaderName() {
		return workSheetHeaderName;
	}

	public void setWorkSheetHeaderName(String workSheetHeaderName) {
		this.workSheetHeaderName = workSheetHeaderName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
