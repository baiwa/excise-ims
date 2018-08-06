package th.co.baiwa.excise.ia.persistence.entity;

public class Budget {

	private String id;
	private String workSheetHeaderName;
	private String department;
	private String startDate;
	private String endDate;
	private String description;
	private String createdBy;
	private String craetedDate;
	private String updatedDate;
	private String isDate;
	private String version;
	private String updatedBy;

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkSheetHeaderName() {
		return workSheetHeaderName;
	}

	public void setWorkSheetHeaderName(String workSheetHeaderName) {
		this.workSheetHeaderName = workSheetHeaderName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCraetedDate() {
		return craetedDate;
	}

	public void setCraetedDate(String craetedDate) {
		this.craetedDate = craetedDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIsDate() {
		return isDate;
	}

	public void setIsDate(String isDate) {
		this.isDate = isDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
