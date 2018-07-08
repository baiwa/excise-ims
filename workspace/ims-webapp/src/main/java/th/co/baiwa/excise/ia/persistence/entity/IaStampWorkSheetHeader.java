package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class IaStampWorkSheetHeader extends BaseEntity {

	private BigDecimal workSheetHeaderId;
	private String workSheetType;
	private String workSheetExanminationLevel;
	private String workSheetHeaderName;
	private String departmentName;
	private String auditorName;
	private String checkDatetime;
	
	
	
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
	
	
}
