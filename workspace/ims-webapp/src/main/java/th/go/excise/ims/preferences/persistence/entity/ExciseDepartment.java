package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;

@Entity
@Table(name = "EXCISE_DEPARTMENT")
public class ExciseDepartment extends BaseEntity implements ExciseDept {

	private static final long serialVersionUID = 7708307533948442803L;

	public static class Field {
		public static final String DEPT_ID = "DEPT_ID";
		public static final String OFFICE_CODE = "OFFICE_CODE";
		public static final String DEPT_NAME = "DEPT_NAME";
		public static final String DEPT_SHOT_NAME = "DEPT_SHORT_NAME";
		public static final String DEPT_SHOT_NAME2 = "DEPT_SHORT_NAME2";
	}

	@Id
	@Column(name = "DEPT_ID")
	private Long deptId;

	@Column(name = "OFFICE_CODE")
	private String officeCode;

	@Column(name = "DEPT_NAME")
	private String deptName;

	@Column(name = "DEPT_SHORT_NAME")
	private String deptShortName;

	@Column(name = "DEPT_SHORT_NAME2")
	private String deptShortName2;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}