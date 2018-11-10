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
@Table(name = "IA_MEDICAL_WELFARE")
public class HealthCareWelFareEntity extends BaseEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 718228582267282642L;

	@Id
	@SequenceGenerator(name = "IA_MEDICAL_WELFARE_GEN", sequenceName = "IA_MEDICAL_WELFARE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_MEDICAL_WELFARE_GEN")
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "BIRTHDATE")
	private Date birthDate;

	@Column(name = "SIBLINGS_ORDER")
	private Integer siblingsOrder;

	@Column(name = "POSITION")
	private String position;

	@Column(name = "AFFILIATION")
	private String affiliation;

	@Column(name = "PHONE_NO")
	private String phoneNumber;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DISEASE")
	private String disease;

	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;

	@Column(name = "HOSPITAL_OWNER")
	private String hospitalOwner;

	@Column(name = "TREATED_DATE_FROM")
	private Date treatedDateFrom;

	@Column(name = "TREATED_DATE_TO")
	private Date treatedDateTo;

	@Column(name = "TOTAL_MONEY")
	private String totalMoney;

	@Column(name = "RECEIPT_QT")
	private String receiptQt;

	@Column(name = "CLAIM_STATUS")
	private String claimStatus;

	@Column(name = "CLAIM_MONEY")
	private String claimMoney;

	@Column(name = "OWNER_CLAIM")
	private String ownerClaim;

	@Column(name = "OTHER_CLAIM")
	private String otherClaim;

	@Column(name = "MATE_NAME")
	private String mateName;

	@Column(name = "MATE_CITIZEN_ID")
	private String mateCitizenId;

	@Column(name = "FATHER_NAME")
	private String fatherName;

	@Column(name = "FATHER_CITIZEN_ID")
	private String fatherCitizenId;

	@Column(name = "MOTHER_NAME")
	private String motherName;

	@Column(name = "MOTHER_CITIZEN_ID")
	private String motherCitizenId;

	@Column(name = "CHILD_NAME")
	private String childName;

	@Column(name = "CHILD_CITIZEN_ID")
	private String childCitizenId;

	@Column(name = "FILE_ID")
	private BigDecimal fileId;
	
	@Column(name = "STATUS_CHECK")
	private String statusCheck;

	public BigDecimal getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Integer getSiblingsOrder() {
		return siblingsOrder;
	}

	public String getPosition() {
		return position;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getDisease() {
		return disease;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public String getHospitalOwner() {
		return hospitalOwner;
	}

	public Date getTreatedDateFrom() {
		return treatedDateFrom;
	}

	public Date getTreatedDateTo() {
		return treatedDateTo;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public String getReceiptQt() {
		return receiptQt;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public String getClaimMoney() {
		return claimMoney;
	}

	public String getOwnerClaim() {
		return ownerClaim;
	}

	public String getOtherClaim() {
		return otherClaim;
	}

	public String getMateName() {
		return mateName;
	}

	public String getMateCitizenId() {
		return mateCitizenId;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getFatherCitizenId() {
		return fatherCitizenId;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getMotherCitizenId() {
		return motherCitizenId;
	}

	public String getChildName() {
		return childName;
	}

	public String getChildCitizenId() {
		return childCitizenId;
	}

	public BigDecimal getFileId() {
		return fileId;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setSiblingsOrder(Integer siblingsOrder) {
		this.siblingsOrder = siblingsOrder;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public void setHospitalOwner(String hospitalOwner) {
		this.hospitalOwner = hospitalOwner;
	}

	public void setTreatedDateFrom(Date treatedDateFrom) {
		this.treatedDateFrom = treatedDateFrom;
	}

	public void setTreatedDateTo(Date treatedDateTo) {
		this.treatedDateTo = treatedDateTo;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public void setReceiptQt(String receiptQt) {
		this.receiptQt = receiptQt;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public void setClaimMoney(String claimMoney) {
		this.claimMoney = claimMoney;
	}

	public void setOwnerClaim(String ownerClaim) {
		this.ownerClaim = ownerClaim;
	}

	public void setOtherClaim(String otherClaim) {
		this.otherClaim = otherClaim;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public void setMateCitizenId(String mateCitizenId) {
		this.mateCitizenId = mateCitizenId;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public void setFatherCitizenId(String fatherCitizenId) {
		this.fatherCitizenId = fatherCitizenId;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public void setMotherCitizenId(String motherCitizenId) {
		this.motherCitizenId = motherCitizenId;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public void setChildCitizenId(String childCitizenId) {
		this.childCitizenId = childCitizenId;
	}

	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getStatusCheck() {
		return statusCheck;
	}

	public void setStatusCheck(String statusCheck) {
		this.statusCheck = statusCheck;
	}

}
