package th.co.baiwa.excise.ia.persistence.entity;

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
@Table(name = "IA_TUITION_FEE_CHILD")

public class TuitionFeeChild extends BaseEntity {

	private static final long serialVersionUID = -1974992892970285582L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_TUITION_FEE_CHILD_GEN")
	@SequenceGenerator(name = "IA_TUITION_FEE_CHILD_GEN", sequenceName = "IA_TUITION_FEE_CHILD_SEQ", allocationSize = 1)

	@Column(name = "ID")
	private Long id;

	@Column(name = "IA_TUITION_FEE_ID")
	private Long iaTuitionFeeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BIRTH")
	private Date birth;

	@Column(name = "ORDER_FATHER")
	private BigDecimal orderFather;

	@Column(name = "ORDER_MATHER")
	private BigDecimal orderMather;

	@Column(name = "ORDER_REPLACE")
	private BigDecimal orderReplace;

	@Column(name = "NAME_REPLACE")
	private String nameReplace;

	@Column(name = "BIRTH_REPLACE")
	private Date birthReplace;

	@Column(name = "DATE_DEAD_REPLACE")
	private Date dateDeadReplace;

	@Column(name = "EDUCATION")
	private String education;

	@Column(name = "EDUCATION_PROVINCE")
	private String educationProvince;

	@Column(name = "EDUCATION_DISTRICT")
	private String educationDistrict;

	@Column(name = "EDUCATION_CLASS")
	private String educationClass;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIaTuitionFeeId() {
		return iaTuitionFeeId;
	}

	public void setIaTuitionFeeId(Long iaTuitionFeeId) {
		this.iaTuitionFeeId = iaTuitionFeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public BigDecimal getOrderFather() {
		return orderFather;
	}

	public void setOrderFather(BigDecimal orderFather) {
		this.orderFather = orderFather;
	}

	public BigDecimal getOrderMather() {
		return orderMather;
	}

	public void setOrderMather(BigDecimal orderMather) {
		this.orderMather = orderMather;
	}

	public BigDecimal getOrderReplace() {
		return orderReplace;
	}

	public void setOrderReplace(BigDecimal orderReplace) {
		this.orderReplace = orderReplace;
	}

	public String getNameReplace() {
		return nameReplace;
	}

	public void setNameReplace(String nameReplace) {
		this.nameReplace = nameReplace;
	}

	public Date getBirthReplace() {
		return birthReplace;
	}

	public void setBirthReplace(Date birthReplace) {
		this.birthReplace = birthReplace;
	}

	public Date getDateDeadReplace() {
		return dateDeadReplace;
	}

	public void setDateDeadReplace(Date dateDeadReplace) {
		this.dateDeadReplace = dateDeadReplace;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationProvince() {
		return educationProvince;
	}

	public void setEducationProvince(String educationProvince) {
		this.educationProvince = educationProvince;
	}

	public String getEducationDistrict() {
		return educationDistrict;
	}

	public void setEducationDistrict(String educationDistrict) {
		this.educationDistrict = educationDistrict;
	}

	public String getEducationClass() {
		return educationClass;
	}

	public void setEducationClass(String educationClass) {
		this.educationClass = educationClass;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
