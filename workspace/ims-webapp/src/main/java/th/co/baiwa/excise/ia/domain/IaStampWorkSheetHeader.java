package th.co.baiwa.excise.ia.domain;

import java.math.BigDecimal;
import java.util.Date;

public class IaStampWorkSheetHeader {

	private BigDecimal workSheetHeaderId;
	private String workSheetType;
	private String workSheetExanminationLevel;
	private String workSheetHeaderName;
	private String departmentName;
	private String auditorName;
	private String checkDatetime;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	
	
	public BigDecimal getWorkSheetHeaderId() {
		return workSheetHeaderId;
	}
	public void setWorkSheetHeaderId(BigDecimal workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
	}
	public String getWorkSheetType() {
		return workSheetType;
	}
	public void setWorkSheetType(String workSheetType) {
		this.workSheetType = workSheetType;
	}
	public String getWorkSheetExanminationLevel() {
		return workSheetExanminationLevel;
	}
	public void setWorkSheetExanminationLevel(String workSheetExanminationLevel) {
		this.workSheetExanminationLevel = workSheetExanminationLevel;
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
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	public String getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(String checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
	
	
}
