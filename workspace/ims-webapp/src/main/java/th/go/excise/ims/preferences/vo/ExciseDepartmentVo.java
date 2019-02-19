package th.go.excise.ims.preferences.vo;

import th.co.baiwa.buckwaframework.support.domain.ExciseDept;

public class ExciseDepartmentVo implements ExciseDept {

	private String officeCode;
	private String deptName;
	private String deptShortName;
	private String deptShortName2;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptShortName() {
		return deptShortName;
	}

	public void setDeptShortName(String deptShortName) {
		this.deptShortName = deptShortName;
	}

	public String getDeptShortName2() {
		return deptShortName2;
	}

	public void setDeptShortName2(String deptShortName2) {
		this.deptShortName2 = deptShortName2;
	}

}
