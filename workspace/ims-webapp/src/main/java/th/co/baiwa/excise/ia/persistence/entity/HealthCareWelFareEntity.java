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
	
	@Column(name = "BIRTHDATE_2")
	private Date birthDate2;

	@Column(name = "BIRTHDATE_3")
	private Date birthDate3;

	@Column(name = "SIBLINGS_ORDER")
	private Integer siblingsOrder;
	
	@Column(name = "SIBLINGS_ORDER_2")
	private Integer siblingsOrder2;

	@Column(name = "SIBLINGS_ORDER_3")
	private Integer siblingsOrder3;

	@Column(name = "POSITION")
	private String position;

	@Column(name = "AFFILIATION")
	private String affiliation;

	@Column(name = "PHONE_NO")
	private String phoneNumber;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "STATUS_2")
	private String status2;
	
	@Column(name = "STATUS_3")
	private String status3;

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
	
	@Column(name = "CHILD_NAME_2")
	private String childName2;

	@Column(name = "CHILD_CITIZEN_ID_2")
	private String childCitizenId2;
	
	@Column(name = "CHILD_NAME_3")
	private String childName3;

	@Column(name = "CHILD_CITIZEN_ID_3")
	private String childCitizenId3;

	@Column(name = "FILE_ID")
	private BigDecimal fileId;
	
	@Column(name = "STATUS_CHECK")
	private String statusCheck;
	
	@Column(name = "IA_DIS_REQ_ID")
	private BigDecimal iaDisReqId;

	public Integer getSiblingsOrder2() {
		return siblingsOrder2;
	}

	public void setSiblingsOrder2(Integer siblingsOrder2) {
		this.siblingsOrder2 = siblingsOrder2;
	}

	public Integer getSiblingsOrder3() {
		return siblingsOrder3;
	}

	public void setSiblingsOrder3(Integer siblingsOrder3) {
		this.siblingsOrder3 = siblingsOrder3;
	}

	public String getStatus2() {
		return status2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStatus3() {
		return status3;
	}

	public void setStatus3(String status3) {
		this.status3 = status3;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDate2() {
		return birthDate2;
	}

	public void setBirthDate2(Date birthDate2) {
		this.birthDate2 = birthDate2;
	}

	public Date getBirthDate3() {
		return birthDate3;
	}

	public void setBirthDate3(Date birthDate3) {
		this.birthDate3 = birthDate3;
	}

	public Integer getSiblingsOrder() {
		return siblingsOrder;
	}

	public void setSiblingsOrder(Integer siblingsOrder) {
		this.siblingsOrder = siblingsOrder;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalOwner() {
		return hospitalOwner;
	}

	public void setHospitalOwner(String hospitalOwner) {
		this.hospitalOwner = hospitalOwner;
	}

	public Date getTreatedDateFrom() {
		return treatedDateFrom;
	}

	public void setTreatedDateFrom(Date treatedDateFrom) {
		this.treatedDateFrom = treatedDateFrom;
	}

	public Date getTreatedDateTo() {
		return treatedDateTo;
	}

	public void setTreatedDateTo(Date treatedDateTo) {
		this.treatedDateTo = treatedDateTo;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getReceiptQt() {
		return receiptQt;
	}

	public void setReceiptQt(String receiptQt) {
		this.receiptQt = receiptQt;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getClaimMoney() {
		return claimMoney;
	}

	public void setClaimMoney(String claimMoney) {
		this.claimMoney = claimMoney;
	}

	public String getOwnerClaim() {
		return ownerClaim;
	}

	public void setOwnerClaim(String ownerClaim) {
		this.ownerClaim = ownerClaim;
	}

	public String getOtherClaim() {
		return otherClaim;
	}

	public void setOtherClaim(String otherClaim) {
		this.otherClaim = otherClaim;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMateCitizenId() {
		return mateCitizenId;
	}

	public void setMateCitizenId(String mateCitizenId) {
		this.mateCitizenId = mateCitizenId;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherCitizenId() {
		return fatherCitizenId;
	}

	public void setFatherCitizenId(String fatherCitizenId) {
		this.fatherCitizenId = fatherCitizenId;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherCitizenId() {
		return motherCitizenId;
	}

	public void setMotherCitizenId(String motherCitizenId) {
		this.motherCitizenId = motherCitizenId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getChildCitizenId() {
		return childCitizenId;
	}

	public void setChildCitizenId(String childCitizenId) {
		this.childCitizenId = childCitizenId;
	}

	public String getChildName2() {
		return childName2;
	}

	public void setChildName2(String childName2) {
		this.childName2 = childName2;
	}

	public String getChildCitizenId2() {
		return childCitizenId2;
	}

	public void setChildCitizenId2(String childCitizenId2) {
		this.childCitizenId2 = childCitizenId2;
	}

	public String getChildName3() {
		return childName3;
	}

	public void setChildName3(String childName3) {
		this.childName3 = childName3;
	}

	public String getChildCitizenId3() {
		return childCitizenId3;
	}

	public void setChildCitizenId3(String childCitizenId3) {
		this.childCitizenId3 = childCitizenId3;
	}

	public BigDecimal getFileId() {
		return fileId;
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

	
	public BigDecimal getIaDisReqId() {
		return iaDisReqId;
	}

	public void setIaDisReqId(BigDecimal iaDisReqId) {
		this.iaDisReqId = iaDisReqId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
