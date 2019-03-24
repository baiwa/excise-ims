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
@Table(name = "TA_FORM_TS0112")
public class TaFormTs0112 extends BaseEntity {

	private static final long serialVersionUID = -7073757080160773263L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0112_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0112_GEN", sequenceName = "TA_FORM_TS0112_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0112_ID")
	private Long formTs0112Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DOC_PLACE")
	private String docPlace;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "HEAD_OFFICER_POSITION")
	private String headOfficerPosition;
	@Column(name = "HEAD_OFFICER_OFFICE_NAME")
	private String headOfficerOfficeName;
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
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
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
	@Column(name = "OWNER_FULL_NAME1")
	private String ownerFullName1;
	@Column(name = "OWNER_POSITION")
	private String ownerPosition;
	@Column(name = "OWNER_OTHER")
	private String ownerOther;
	@Column(name = "LAW_GROUP")
	private String lawGroup;
	@Column(name = "SEIZE_DESC")
	private String seizeDesc;
	@Column(name = "CONTACT_DESC")
	private String contactDesc;
	@Column(name = "OWNER_FULL_NAME2")
	private String ownerFullName2;
	@Column(name = "OWNER_POSITION2")
	private String ownerPosition2;
	@Column(name = "OWNER_OTHER2")
	private String ownerOther2;
	@Column(name = "SIGN_AUTH_FULL_NAME")
	private String signAuthFullName;
	@Column(name = "SIGN_INSPECTOR_FULL_NAME")
	private String signInspectorFullName;
	@Column(name = "SIGN_WITNESS_FULL_NAME1")
	private String signWitnessFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME2")
	private String signWitnessFullName2;

	public Long getFormTs0112Id() {
		return formTs0112Id;
	}

	public void setFormTs0112Id(Long formTs0112Id) {
		this.formTs0112Id = formTs0112Id;
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

	public String getDocPlace() {
		return docPlace;
	}

	public void setDocPlace(String docPlace) {
		this.docPlace = docPlace;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
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

	public String getHeadOfficerOfficeName() {
		return headOfficerOfficeName;
	}

	public void setHeadOfficerOfficeName(String headOfficerOfficeName) {
		this.headOfficerOfficeName = headOfficerOfficeName;
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

	public String getOwnerFullName1() {
		return ownerFullName1;
	}

	public void setOwnerFullName1(String ownerFullName1) {
		this.ownerFullName1 = ownerFullName1;
	}

	public String getOwnerPosition() {
		return ownerPosition;
	}

	public void setOwnerPosition(String ownerPosition) {
		this.ownerPosition = ownerPosition;
	}

	public String getOwnerOther() {
		return ownerOther;
	}

	public void setOwnerOther(String ownerOther) {
		this.ownerOther = ownerOther;
	}

	public String getLawGroup() {
		return lawGroup;
	}

	public void setLawGroup(String lawGroup) {
		this.lawGroup = lawGroup;
	}

	public String getSeizeDesc() {
		return seizeDesc;
	}

	public void setSeizeDesc(String seizeDesc) {
		this.seizeDesc = seizeDesc;
	}

	public String getContactDesc() {
		return contactDesc;
	}

	public void setContactDesc(String contactDesc) {
		this.contactDesc = contactDesc;
	}

	public String getOwnerFullName2() {
		return ownerFullName2;
	}

	public void setOwnerFullName2(String ownerFullName2) {
		this.ownerFullName2 = ownerFullName2;
	}

	public String getOwnerPosition2() {
		return ownerPosition2;
	}

	public void setOwnerPosition2(String ownerPosition2) {
		this.ownerPosition2 = ownerPosition2;
	}

	public String getOwnerOther2() {
		return ownerOther2;
	}

	public void setOwnerOther2(String ownerOther2) {
		this.ownerOther2 = ownerOther2;
	}

	public String getSignAuthFullName() {
		return signAuthFullName;
	}

	public void setSignAuthFullName(String signAuthFullName) {
		this.signAuthFullName = signAuthFullName;
	}

	public String getSignInspectorFullName() {
		return signInspectorFullName;
	}

	public void setSignInspectorFullName(String signInspectorFullName) {
		this.signInspectorFullName = signInspectorFullName;
	}

	public String getSignWitnessFullName1() {
		return signWitnessFullName1;
	}

	public void setSignWitnessFullName1(String signWitnessFullName1) {
		this.signWitnessFullName1 = signWitnessFullName1;
	}

	public String getSignWitnessFullName2() {
		return signWitnessFullName2;
	}

	public void setSignWitnessFullName2(String signWitnessFullName2) {
		this.signWitnessFullName2 = signWitnessFullName2;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
