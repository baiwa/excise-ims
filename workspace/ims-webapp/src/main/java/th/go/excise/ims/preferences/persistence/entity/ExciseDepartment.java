package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_DEPARTMENT")
public class ExciseDepartment extends BaseEntity {

	private static final long serialVersionUID = 7708307533948442803L;

	public static class Field {

		public static final String DEPARTMENT_ID = "DEPARTMENT_ID";
		public static final String OFFICE_CODE = "OFFICE_CODE";
		public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";
		public static final String DEPARTMENT_SHOT_NAME = "DEPARTMENT_SHOT_NAME";
		public static final String DEPARTMENT_SHOT_NAME2 = "DEPARTMENT_SHOT_NAME2";
	}

	@Id
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;

	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;

	@Column(name = "DEPARTMENT_SHOT_NAME")
	private String departmentShotName;

	@Column(name = "DEPARTMENT_SHOT_NAME2")
	private String departmentShotName2;

	@Column(name = "OFFICE_CODE")
	private String officeCode;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentShotName() {
		return departmentShotName;
	}

	public void setDepartmentShotName(String departmentShotName) {
		this.departmentShotName = departmentShotName;
	}

	public String getDepartmentShotName2() {
		return departmentShotName2;
	}

	public void setDepartmentShotName2(String departmentShotName2) {
		this.departmentShotName2 = departmentShotName2;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}