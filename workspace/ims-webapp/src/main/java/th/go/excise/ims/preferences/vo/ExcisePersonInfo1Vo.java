package th.go.excise.ims.preferences.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ExcisePersonInfo1Vo {

	private Long id;
	private String personLogin;
	private BigDecimal childNo;
	private String childFullName;
	private String childPid;
	private String childBirthDate;
	private String instituteDesc;
	private String instituteAmphurCode;
	private String instituteProvinceCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonLogin() {
		return personLogin;
	}

	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}

	public BigDecimal getChildNo() {
		return childNo;
	}

	public void setChildNo(BigDecimal childNo) {
		this.childNo = childNo;
	}

	public String getChildFullName() {
		return childFullName;
	}

	public void setChildFullName(String childFullName) {
		this.childFullName = childFullName;
	}

	public String getChildPid() {
		return childPid;
	}

	public void setChildPid(String childPid) {
		this.childPid = childPid;
	}

	public String getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(String childBirthDate) {
		this.childBirthDate = childBirthDate;
	}

	public String getInstituteDesc() {
		return instituteDesc;
	}

	public void setInstituteDesc(String instituteDesc) {
		this.instituteDesc = instituteDesc;
	}

	public String getInstituteAmphurCode() {
		return instituteAmphurCode;
	}

	public void setInstituteAmphurCode(String instituteAmphurCode) {
		this.instituteAmphurCode = instituteAmphurCode;
	}

	public String getInstituteProvinceCode() {
		return instituteProvinceCode;
	}

	public void setInstituteProvinceCode(String instituteProvinceCode) {
		this.instituteProvinceCode = instituteProvinceCode;
	}

}
