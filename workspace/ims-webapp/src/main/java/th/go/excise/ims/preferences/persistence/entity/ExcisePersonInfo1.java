
package th.go.excise.ims.preferences.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_PERSON_INFO1")
public class ExcisePersonInfo1 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2015913669536995811L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_PERSON_INFO1_GEN")
	@SequenceGenerator(name = "EXCISE_PERSON_INFO1_GEN", sequenceName = "EXCISE_PERSON_INFO1_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	@Column(name = "PERSON_LOGIN")
	private String personLogin;
	@Column(name = "CHILD_NO")
	private BigDecimal childNo;
	@Column(name = "CHILD_FULL_NAME")
	private String childFullName;
	@Column(name = "CHILD_PID")
	private String childPid;
	@Column(name = "CHILD_BIRTH_DATE")
	private Date childBirthDate;
	@Column(name = "INSTITUTE_DESC")
	private String instituteDesc;
	@Column(name = "INSTITUTE_AMPHUR_CODE")
	private String instituteAmphurCode;
	@Column(name = "INSTITUTE_PROVINCE_CODE")
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

	public Date getChildBirthDate() {
		return childBirthDate;
	}

	public void setChildBirthDate(Date childBirthDate) {
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
