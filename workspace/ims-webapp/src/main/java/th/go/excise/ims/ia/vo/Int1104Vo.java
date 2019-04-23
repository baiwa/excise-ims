package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Int1104Vo {
	/* header */
	private BigDecimal id;
	private String projectCode;
	private String projectName;
	private String sector;
	private String area;
	private String systemCode;
	private String systemName;
	private String budgetYear;
	private String checkType;
	private Date reportDate;
	private Date approveDate;
	private String notifyNo;
	private Date notifyDateFrom;
	private Date notifyDateTo;
	private String status;
	private Date dateClosedWork;
	private String noteClosedWork;
	private String inspectionWork;
	private String exciseCode;
	private BigDecimal idConcludeFollowHdr;
	
	/* deetails */
	private List<Int110401DtlVo> details;

	/* ExciseDepartmentVo */
	private ExciseDepartmentVo exciseDepartmentVo;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getNotifyNo() {
		return notifyNo;
	}

	public void setNotifyNo(String notifyNo) {
		this.notifyNo = notifyNo;
	}

	public Date getNotifyDateFrom() {
		return notifyDateFrom;
	}

	public void setNotifyDateFrom(Date notifyDateFrom) {
		this.notifyDateFrom = notifyDateFrom;
	}

	public Date getNotifyDateTo() {
		return notifyDateTo;
	}

	public void setNotifyDateTo(Date notifyDateTo) {
		this.notifyDateTo = notifyDateTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateClosedWork() {
		return dateClosedWork;
	}

	public void setDateClosedWork(Date dateClosedWork) {
		this.dateClosedWork = dateClosedWork;
	}

	public String getNoteClosedWork() {
		return noteClosedWork;
	}

	public void setNoteClosedWork(String noteClosedWork) {
		this.noteClosedWork = noteClosedWork;
	}

	public String getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(String inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public BigDecimal getIdConcludeFollowHdr() {
		return idConcludeFollowHdr;
	}

	public void setIdConcludeFollowHdr(BigDecimal idConcludeFollowHdr) {
		this.idConcludeFollowHdr = idConcludeFollowHdr;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public List<Int110401DtlVo> getDetails() {
		return details;
	}

	public void setDetails(List<Int110401DtlVo> details) {
		this.details = details;
	}

}
