package th.co.baiwa.excise.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/**
 * The persistent class for the IA_TRAVEL_COST_WS_HEADER database table.
 * 
 */
@Entity
@Table(name = "IA_TRAVEL_COST_WS_HEADER")
public class IaTravelCostWsHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8702880439266680797L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_TRAVEL_COST_WS_HEADER_GEN")
	@SequenceGenerator(name = "IA_TRAVEL_COST_WS_HEADER_GEN", sequenceName = "IA_TRAVEL_COST_WS_HEADER_SEQ", allocationSize = 1)
	@Column(name = "WORK_SHEET_HEADER_ID")
	private long workSheetHeaderId;

	@Column(name = "WORK_SHEET_HEADER_NAME")
	private String workSheetHeaderName;

	@Column(name = "BRANCH_ID")
	private String branchId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE")
	private Date endDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE")
	private Date startDate;

	public long getWorkSheetHeaderId() {
		return workSheetHeaderId;
	}

	public void setWorkSheetHeaderId(long workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
	}

	public String getWorkSheetHeaderName() {
		return workSheetHeaderName;
	}

	public void setWorkSheetHeaderName(String workSheetHeaderName) {
		this.workSheetHeaderName = workSheetHeaderName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


}