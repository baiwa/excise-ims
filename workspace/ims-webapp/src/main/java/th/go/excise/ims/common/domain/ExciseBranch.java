package th.go.excise.ims.common.domain;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseBranch extends BaseVo{
	
	public static class Field {
		
		public static final String BRANCH_ID = "BRANCH_ID";
		public static final String AREA_ID = "AREA_ID";
		public static final String OFFICE_CODE = "OFFICE_CODE";
		public static final String BRANCH_NAME = "BRANCH_NAME";
		public static final String BRANCH_SHOT_NAME = "BRANCH_SHOT_NAME";
		public static final String BRANCH_SHOT_NAME2 = "BRANCH_SHOT_NAME2";
		
	}
	
	private BigDecimal branchId; 
	private BigDecimal areaId; 
	private String officeCode; 
	private String branchName; 
	private String branchShotName; 
	private String branchShotName2;
	
	public BigDecimal getBranchId() {
		return branchId;
	}
	public void setBranchId(BigDecimal branchId) {
		this.branchId = branchId;
	}
	public BigDecimal getAreaId() {
		return areaId;
	}
	public void setAreaId(BigDecimal areaId) {
		this.areaId = areaId;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchShotName() {
		return branchShotName;
	}
	public void setBranchShotName(String branchShotName) {
		this.branchShotName = branchShotName;
	}
	public String getBranchShotName2() {
		return branchShotName2;
	}
	public void setBranchShotName2(String branchShotName2) {
		this.branchShotName2 = branchShotName2;
	}
	
	
	

}
