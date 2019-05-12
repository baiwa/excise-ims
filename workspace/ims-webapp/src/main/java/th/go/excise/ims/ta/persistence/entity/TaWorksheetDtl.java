package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_DTL")
public class TaWorksheetDtl extends BaseEntity {

	private static final long serialVersionUID = 6358831230496999116L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_DTL_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_DTL_GEN", sequenceName = "TA_WORKSHEET_DTL_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_DTL_ID")
	private Long worksheetDtlId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_ID")
	private String cusId;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "CUS_ADDRESS")
	private String cusAddress;
	@Column(name = "CUS_TELNO")
	private String cusTelno;
	@Column(name = "CUS_EMAIL")
	private String cusEmail;
	@Column(name = "CUS_URL")
	private String cusUrl;
	@Column(name = "FAC_ID")
	private String facId;
	@Column(name = "FAC_FULLNAME")
	private String facFullname;
	@Column(name = "FAC_ADDRESS")
	private String facAddress;
	@Column(name = "FAC_TELNO")
	private String facTelno;
	@Column(name = "FAC_EMAIL")
	private String facEmail;
	@Column(name = "FAC_URL")
	private String facUrl;
	@Column(name = "FAC_TYPE")
	private String facType;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_STATUS")
	private String regStatus;
	@Column(name = "REG_DATE")
	private LocalDate regDate;
	@Column(name = "REG_CAPITAL")
	private BigDecimal regCapital;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "DUTY_GROUP_ID")
	private String dutyGroupId;
	@Column(name = "SYNC_DATE")
	private LocalDateTime syncDate;
	@Column(name = "SUM_TAX_AMT_G1")
	private String sumTaxAmtG1;
	@Column(name = "SUM_TAX_AMT_G2")
	private String sumTaxAmtG2;
	@Column(name = "TAX_AMT_CHN_PNT")
	private String taxAmtChnPnt;
	@Column(name = "TAX_AMT_SD")
	private String taxAmtSd;
	@Column(name = "TAX_MONTH_NO")
	private String taxMonthNo;
	@Column(name = "TAX_AUDIT_LAST3")
	private String taxAuditLast3;
	@Column(name = "TAX_AUDIT_LAST2")
	private String taxAuditLast2;
	@Column(name = "TAX_AUDIT_LAST1")
	private String taxAuditLast1;
	@Column(name = "TAX_AMT_MEAN")
	private String taxAmtMean;
	@Column(name = "TAX_AMT_MAX_PNT")
	private String taxAmtMaxPnt;
	@Column(name = "TAX_AMT_MIN_PNT")
	private String taxAmtMinPnt;
	@Column(name = "TAX_AMT_G1_M1")
	private String taxAmtG1M1;
	@Column(name = "TAX_AMT_G1_M2")
	private String taxAmtG1M2;
	@Column(name = "TAX_AMT_G1_M3")
	private String taxAmtG1M3;
	@Column(name = "TAX_AMT_G1_M4")
	private String taxAmtG1M4;
	@Column(name = "TAX_AMT_G1_M5")
	private String taxAmtG1M5;
	@Column(name = "TAX_AMT_G1_M6")
	private String taxAmtG1M6;
	@Column(name = "TAX_AMT_G1_M7")
	private String taxAmtG1M7;
	@Column(name = "TAX_AMT_G1_M8")
	private String taxAmtG1M8;
	@Column(name = "TAX_AMT_G1_M9")
	private String taxAmtG1M9;
	@Column(name = "TAX_AMT_G1_M10")
	private String taxAmtG1M10;
	@Column(name = "TAX_AMT_G1_M11")
	private String taxAmtG1M11;
	@Column(name = "TAX_AMT_G1_M12")
	private String taxAmtG1M12;
	@Column(name = "TAX_AMT_G2_M1")
	private String taxAmtG2M1;
	@Column(name = "TAX_AMT_G2_M2")
	private String taxAmtG2M2;
	@Column(name = "TAX_AMT_G2_M3")
	private String taxAmtG2M3;
	@Column(name = "TAX_AMT_G2_M4")
	private String taxAmtG2M4;
	@Column(name = "TAX_AMT_G2_M5")
	private String taxAmtG2M5;
	@Column(name = "TAX_AMT_G2_M6")
	private String taxAmtG2M6;
	@Column(name = "TAX_AMT_G2_M7")
	private String taxAmtG2M7;
	@Column(name = "TAX_AMT_G2_M8")
	private String taxAmtG2M8;
	@Column(name = "TAX_AMT_G2_M9")
	private String taxAmtG2M9;
	@Column(name = "TAX_AMT_G2_M10")
	private String taxAmtG2M10;
	@Column(name = "TAX_AMT_G2_M11")
	private String taxAmtG2M11;
	@Column(name = "TAX_AMT_G2_M12")
	private String taxAmtG2M12;
	@Column(name = "COND_MAIN_GRP")
	private String condMainGrp;
	@Column(name = "COND_SUB_CAPITAL")
	private String condSubCapital;
	@Column(name = "COND_SUB_RISK")
	private String condSubRisk;
	@Column(name = "COND_SUB_NO_AUDIT")
	private String condSubNoAudit;
	@Column(name = "LAST_AUDIT_YEAR")
	private String lastAuditYear;

	public Long getWorksheetDtlId() {
		return worksheetDtlId;
	}

	public void setWorksheetDtlId(Long worksheetDtlId) {
		this.worksheetDtlId = worksheetDtlId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusTelno() {
		return cusTelno;
	}

	public void setCusTelno(String cusTelno) {
		this.cusTelno = cusTelno;
	}

	public String getCusEmail() {
		return cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusUrl() {
		return cusUrl;
	}

	public void setCusUrl(String cusUrl) {
		this.cusUrl = cusUrl;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getFacFullname() {
		return facFullname;
	}

	public void setFacFullname(String facFullname) {
		this.facFullname = facFullname;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public String getFacTelno() {
		return facTelno;
	}

	public void setFacTelno(String facTelno) {
		this.facTelno = facTelno;
	}

	public String getFacEmail() {
		return facEmail;
	}

	public void setFacEmail(String facEmail) {
		this.facEmail = facEmail;
	}

	public String getFacUrl() {
		return facUrl;
	}

	public void setFacUrl(String facUrl) {
		this.facUrl = facUrl;
	}

	public String getFacType() {
		return facType;
	}

	public void setFacType(String facType) {
		this.facType = facType;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public LocalDateTime getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(LocalDateTime syncDate) {
		this.syncDate = syncDate;
	}

	public String getSumTaxAmtG1() {
		return sumTaxAmtG1;
	}

	public void setSumTaxAmtG1(String sumTaxAmtG1) {
		this.sumTaxAmtG1 = sumTaxAmtG1;
	}

	public String getSumTaxAmtG2() {
		return sumTaxAmtG2;
	}

	public void setSumTaxAmtG2(String sumTaxAmtG2) {
		this.sumTaxAmtG2 = sumTaxAmtG2;
	}

	public String getTaxAmtChnPnt() {
		return taxAmtChnPnt;
	}

	public void setTaxAmtChnPnt(String taxAmtChnPnt) {
		this.taxAmtChnPnt = taxAmtChnPnt;
	}

	public String getTaxAmtSd() {
		return taxAmtSd;
	}

	public void setTaxAmtSd(String taxAmtSd) {
		this.taxAmtSd = taxAmtSd;
	}

	public String getTaxMonthNo() {
		return taxMonthNo;
	}

	public void setTaxMonthNo(String taxMonthNo) {
		this.taxMonthNo = taxMonthNo;
	}

	public String getTaxAuditLast3() {
		return taxAuditLast3;
	}

	public void setTaxAuditLast3(String taxAuditLast3) {
		this.taxAuditLast3 = taxAuditLast3;
	}

	public String getTaxAuditLast2() {
		return taxAuditLast2;
	}

	public void setTaxAuditLast2(String taxAuditLast2) {
		this.taxAuditLast2 = taxAuditLast2;
	}

	public String getTaxAuditLast1() {
		return taxAuditLast1;
	}

	public void setTaxAuditLast1(String taxAuditLast1) {
		this.taxAuditLast1 = taxAuditLast1;
	}

	public String getTaxAmtMean() {
		return taxAmtMean;
	}

	public void setTaxAmtMean(String taxAmtMean) {
		this.taxAmtMean = taxAmtMean;
	}

	public String getTaxAmtMaxPnt() {
		return taxAmtMaxPnt;
	}

	public void setTaxAmtMaxPnt(String taxAmtMaxPnt) {
		this.taxAmtMaxPnt = taxAmtMaxPnt;
	}

	public String getTaxAmtMinPnt() {
		return taxAmtMinPnt;
	}

	public void setTaxAmtMinPnt(String taxAmtMinPnt) {
		this.taxAmtMinPnt = taxAmtMinPnt;
	}

	public String getTaxAmtG1M1() {
		return taxAmtG1M1;
	}

	public void setTaxAmtG1M1(String taxAmtG1M1) {
		this.taxAmtG1M1 = taxAmtG1M1;
	}

	public String getTaxAmtG1M2() {
		return taxAmtG1M2;
	}

	public void setTaxAmtG1M2(String taxAmtG1M2) {
		this.taxAmtG1M2 = taxAmtG1M2;
	}

	public String getTaxAmtG1M3() {
		return taxAmtG1M3;
	}

	public void setTaxAmtG1M3(String taxAmtG1M3) {
		this.taxAmtG1M3 = taxAmtG1M3;
	}

	public String getTaxAmtG1M4() {
		return taxAmtG1M4;
	}

	public void setTaxAmtG1M4(String taxAmtG1M4) {
		this.taxAmtG1M4 = taxAmtG1M4;
	}

	public String getTaxAmtG1M5() {
		return taxAmtG1M5;
	}

	public void setTaxAmtG1M5(String taxAmtG1M5) {
		this.taxAmtG1M5 = taxAmtG1M5;
	}

	public String getTaxAmtG1M6() {
		return taxAmtG1M6;
	}

	public void setTaxAmtG1M6(String taxAmtG1M6) {
		this.taxAmtG1M6 = taxAmtG1M6;
	}

	public String getTaxAmtG1M7() {
		return taxAmtG1M7;
	}

	public void setTaxAmtG1M7(String taxAmtG1M7) {
		this.taxAmtG1M7 = taxAmtG1M7;
	}

	public String getTaxAmtG1M8() {
		return taxAmtG1M8;
	}

	public void setTaxAmtG1M8(String taxAmtG1M8) {
		this.taxAmtG1M8 = taxAmtG1M8;
	}

	public String getTaxAmtG1M9() {
		return taxAmtG1M9;
	}

	public void setTaxAmtG1M9(String taxAmtG1M9) {
		this.taxAmtG1M9 = taxAmtG1M9;
	}

	public String getTaxAmtG1M10() {
		return taxAmtG1M10;
	}

	public void setTaxAmtG1M10(String taxAmtG1M10) {
		this.taxAmtG1M10 = taxAmtG1M10;
	}

	public String getTaxAmtG1M11() {
		return taxAmtG1M11;
	}

	public void setTaxAmtG1M11(String taxAmtG1M11) {
		this.taxAmtG1M11 = taxAmtG1M11;
	}

	public String getTaxAmtG1M12() {
		return taxAmtG1M12;
	}

	public void setTaxAmtG1M12(String taxAmtG1M12) {
		this.taxAmtG1M12 = taxAmtG1M12;
	}

	public String getTaxAmtG2M1() {
		return taxAmtG2M1;
	}

	public void setTaxAmtG2M1(String taxAmtG2M1) {
		this.taxAmtG2M1 = taxAmtG2M1;
	}

	public String getTaxAmtG2M2() {
		return taxAmtG2M2;
	}

	public void setTaxAmtG2M2(String taxAmtG2M2) {
		this.taxAmtG2M2 = taxAmtG2M2;
	}

	public String getTaxAmtG2M3() {
		return taxAmtG2M3;
	}

	public void setTaxAmtG2M3(String taxAmtG2M3) {
		this.taxAmtG2M3 = taxAmtG2M3;
	}

	public String getTaxAmtG2M4() {
		return taxAmtG2M4;
	}

	public void setTaxAmtG2M4(String taxAmtG2M4) {
		this.taxAmtG2M4 = taxAmtG2M4;
	}

	public String getTaxAmtG2M5() {
		return taxAmtG2M5;
	}

	public void setTaxAmtG2M5(String taxAmtG2M5) {
		this.taxAmtG2M5 = taxAmtG2M5;
	}

	public String getTaxAmtG2M6() {
		return taxAmtG2M6;
	}

	public void setTaxAmtG2M6(String taxAmtG2M6) {
		this.taxAmtG2M6 = taxAmtG2M6;
	}

	public String getTaxAmtG2M7() {
		return taxAmtG2M7;
	}

	public void setTaxAmtG2M7(String taxAmtG2M7) {
		this.taxAmtG2M7 = taxAmtG2M7;
	}

	public String getTaxAmtG2M8() {
		return taxAmtG2M8;
	}

	public void setTaxAmtG2M8(String taxAmtG2M8) {
		this.taxAmtG2M8 = taxAmtG2M8;
	}

	public String getTaxAmtG2M9() {
		return taxAmtG2M9;
	}

	public void setTaxAmtG2M9(String taxAmtG2M9) {
		this.taxAmtG2M9 = taxAmtG2M9;
	}

	public String getTaxAmtG2M10() {
		return taxAmtG2M10;
	}

	public void setTaxAmtG2M10(String taxAmtG2M10) {
		this.taxAmtG2M10 = taxAmtG2M10;
	}

	public String getTaxAmtG2M11() {
		return taxAmtG2M11;
	}

	public void setTaxAmtG2M11(String taxAmtG2M11) {
		this.taxAmtG2M11 = taxAmtG2M11;
	}

	public String getTaxAmtG2M12() {
		return taxAmtG2M12;
	}

	public void setTaxAmtG2M12(String taxAmtG2M12) {
		this.taxAmtG2M12 = taxAmtG2M12;
	}

	public String getCondMainGrp() {
		return condMainGrp;
	}

	public void setCondMainGrp(String condMainGrp) {
		this.condMainGrp = condMainGrp;
	}

	public String getCondSubCapital() {
		return condSubCapital;
	}

	public void setCondSubCapital(String condSubCapital) {
		this.condSubCapital = condSubCapital;
	}

	public String getCondSubRisk() {
		return condSubRisk;
	}

	public void setCondSubRisk(String condSubRisk) {
		this.condSubRisk = condSubRisk;
	}

	public String getCondSubNoAudit() {
		return condSubNoAudit;
	}

	public void setCondSubNoAudit(String condSubNoAudit) {
		this.condSubNoAudit = condSubNoAudit;
	}

	public String getLastAuditYear() {
		return lastAuditYear;
	}

	public void setLastAuditYear(String lastAuditYear) {
		this.lastAuditYear = lastAuditYear;
	}

}
