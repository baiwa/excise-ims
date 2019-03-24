package th.go.excise.ims.ta.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_FORM_TS0107")
public class TaFormTs0107 extends BaseEntity {

	private static final long serialVersionUID = -4297806203979136227L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0107_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0107_GEN", sequenceName = "TA_FORM_TS0107_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0107_ID")
	private Long formTs0107Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "BOOK_NUMBER1")
	private String bookNumber1;
	@Column(name = "BOOK_NUMBER2")
	private String bookNumber2;
	@Column(name = "OFFICE_NAME1")
	private String officeName1;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "OFFICE_NAME2")
	private String officeName2;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "HEAD_OFFICER_POSITION")
	private String headOfficerPosition;
	@Column(name = "OFFICER_FULL_NAME1")
	private String officerFullName1;
	@Column(name = "OFFICER_POSITION1")
	private String officerPosition1;
	@Column(name = "OFFICER_FULL_NAME2")
	private String officerFullName2;
	@Column(name = "OFFICER_POSITION2")
	private String officerPosition2;
	@Column(name = "OFFICER_FULL_NAME3")
	private String officerFullName3;
	@Column(name = "OFFICER_POSITION3")
	private String officerPosition3;
	@Column(name = "OFFICER_FULL_NAME4")
	private String officerFullName4;
	@Column(name = "OFFICER_POSITION4")
	private String officerPosition4;
	@Column(name = "OFFICER_FULL_NAME5")
	private String officerFullName5;
	@Column(name = "OFFICER_POSITION5")
	private String officerPosition5;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_MOO_NO")
	private String facMooNo;
	@Column(name = "FAC_SOI_NAME")
	private String facSoiName;
	@Column(name = "FAC_THN_NAME")
	private String facThnName;
	@Column(name = "FAC_TAMBOL_NAME")
	private String facTambolName;
	@Column(name = "FAC_AMPHUR_NAME")
	private String facAmphurName;
	@Column(name = "FAC_PROVINCE_NAME")
	private String facProvinceName;
	@Column(name = "FAC_ZIP_CODE")
	private String facZipCode;
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	@Column(name = "LAW_SECTION")
	private String lawSection;
	@Column(name = "HEAD_OFFICER_PHONE")
	private String headOfficerPhone;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "OTHER_TEXT")
	private String otherText;
	@Column(name = "OTHER_PHONE")
	private String otherPhone;

	public Long getFormTs0107Id() {
		return formTs0107Id;
	}

	public void setFormTs0107Id(Long formTs0107Id) {
		this.formTs0107Id = formTs0107Id;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getBookNumber1() {
		return bookNumber1;
	}

	public void setBookNumber1(String bookNumber1) {
		this.bookNumber1 = bookNumber1;
	}

	public String getBookNumber2() {
		return bookNumber2;
	}

	public void setBookNumber2(String bookNumber2) {
		this.bookNumber2 = bookNumber2;
	}

	public String getOfficeName1() {
		return officeName1;
	}

	public void setOfficeName1(String officeName1) {
		this.officeName1 = officeName1;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getOfficeName2() {
		return officeName2;
	}

	public void setOfficeName2(String officeName2) {
		this.officeName2 = officeName2;
	}

	public String getHeadOfficerFullName() {
		return headOfficerFullName;
	}

	public void setHeadOfficerFullName(String headOfficerFullName) {
		this.headOfficerFullName = headOfficerFullName;
	}

	public String getHeadOfficerPosition() {
		return headOfficerPosition;
	}

	public void setHeadOfficerPosition(String headOfficerPosition) {
		this.headOfficerPosition = headOfficerPosition;
	}

	public String getOfficerFullName1() {
		return officerFullName1;
	}

	public void setOfficerFullName1(String officerFullName1) {
		this.officerFullName1 = officerFullName1;
	}

	public String getOfficerPosition1() {
		return officerPosition1;
	}

	public void setOfficerPosition1(String officerPosition1) {
		this.officerPosition1 = officerPosition1;
	}

	public String getOfficerFullName2() {
		return officerFullName2;
	}

	public void setOfficerFullName2(String officerFullName2) {
		this.officerFullName2 = officerFullName2;
	}

	public String getOfficerPosition2() {
		return officerPosition2;
	}

	public void setOfficerPosition2(String officerPosition2) {
		this.officerPosition2 = officerPosition2;
	}

	public String getOfficerFullName3() {
		return officerFullName3;
	}

	public void setOfficerFullName3(String officerFullName3) {
		this.officerFullName3 = officerFullName3;
	}

	public String getOfficerPosition3() {
		return officerPosition3;
	}

	public void setOfficerPosition3(String officerPosition3) {
		this.officerPosition3 = officerPosition3;
	}

	public String getOfficerFullName4() {
		return officerFullName4;
	}

	public void setOfficerFullName4(String officerFullName4) {
		this.officerFullName4 = officerFullName4;
	}

	public String getOfficerPosition4() {
		return officerPosition4;
	}

	public void setOfficerPosition4(String officerPosition4) {
		this.officerPosition4 = officerPosition4;
	}

	public String getOfficerFullName5() {
		return officerFullName5;
	}

	public void setOfficerFullName5(String officerFullName5) {
		this.officerFullName5 = officerFullName5;
	}

	public String getOfficerPosition5() {
		return officerPosition5;
	}

	public void setOfficerPosition5(String officerPosition5) {
		this.officerPosition5 = officerPosition5;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
	}

	public String getFacMooNo() {
		return facMooNo;
	}

	public void setFacMooNo(String facMooNo) {
		this.facMooNo = facMooNo;
	}

	public String getFacSoiName() {
		return facSoiName;
	}

	public void setFacSoiName(String facSoiName) {
		this.facSoiName = facSoiName;
	}

	public String getFacThnName() {
		return facThnName;
	}

	public void setFacThnName(String facThnName) {
		this.facThnName = facThnName;
	}

	public String getFacTambolName() {
		return facTambolName;
	}

	public void setFacTambolName(String facTambolName) {
		this.facTambolName = facTambolName;
	}

	public String getFacAmphurName() {
		return facAmphurName;
	}

	public void setFacAmphurName(String facAmphurName) {
		this.facAmphurName = facAmphurName;
	}

	public String getFacProvinceName() {
		return facProvinceName;
	}

	public void setFacProvinceName(String facProvinceName) {
		this.facProvinceName = facProvinceName;
	}

	public String getFacZipCode() {
		return facZipCode;
	}

	public void setFacZipCode(String facZipCode) {
		this.facZipCode = facZipCode;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getLawSection() {
		return lawSection;
	}

	public void setLawSection(String lawSection) {
		this.lawSection = lawSection;
	}

	public String getHeadOfficerPhone() {
		return headOfficerPhone;
	}

	public void setHeadOfficerPhone(String headOfficerPhone) {
		this.headOfficerPhone = headOfficerPhone;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public String getSignOfficerPosition() {
		return signOfficerPosition;
	}

	public void setSignOfficerPosition(String signOfficerPosition) {
		this.signOfficerPosition = signOfficerPosition;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
