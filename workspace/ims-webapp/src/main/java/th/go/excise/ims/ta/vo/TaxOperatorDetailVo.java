package th.go.excise.ims.ta.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaxOperatorDetailVo {

    private String cusFullname;
    private String facFullname;
    private String facAddress;
    private String officeCode;
    private String secCode;
    private String secDesc;
    private String areaCode;
    private String areaDesc;
    private String worksheetHdrId;
    private String draftNumber;
    private String analysisNumber;
    private String newRegId;
    private String sumTaxAmtG1;
    private String sumTaxAmtG2;
    private String taxAmtChnPnt;
    private String taxAmtSd;
    private String taxMonthNo;
    private String taxAuditLast3;
    private String taxAuditLast2;
    private String taxAuditLast1;
    private String oldRegId;
    private String lastedStatus;
    private String regStatus;
    private String taxAmtMean;
    private String taxAmtMaxPnt;
    private String taxAmtMinPnt;
    private String taxAmtG1M1;
    private String taxAmtG1M2;
    private String taxAmtG1M3;
    private String taxAmtG1M4;
    private String taxAmtG1M5;
    private String taxAmtG1M6;
    private String taxAmtG1M7;
    private String taxAmtG1M8;
    private String taxAmtG1M9;
    private String taxAmtG1M10;
    private String taxAmtG1M11;
    private String taxAmtG1M12;
    private String taxAmtG2M1;
    private String taxAmtG2M2;
    private String taxAmtG2M3;
    private String taxAmtG2M4;
    private String taxAmtG2M5;
    private String taxAmtG2M6;
    private String taxAmtG2M7;
    private String taxAmtG2M8;
    private String taxAmtG2M9;
    private String taxAmtG2M10;
    private String taxAmtG2M11;
    private String taxAmtG2M12;
    private String condTaxGrp;
    private String dutyCode;
    private String dutyName;
    private String otherDutyCode;
    private String otherDutyName;
    private String checked = "N";
    private String centralSelFlag;
    private String sectorSelFlag;
    private String selectBy;

    public String getSelectBy() {
        return selectBy;
    }

    public void setSelectBy(String selectBy) {
        this.selectBy = selectBy;
    }

    public String getCentralSelFlag() {
        return centralSelFlag;
    }

    public void setCentralSelFlag(String centralSelFlag) {
        this.centralSelFlag = centralSelFlag;
    }

    public String getSectorSelFlag() {
        return sectorSelFlag;
    }

    public void setSectorSelFlag(String sectorSelFlag) {
        this.sectorSelFlag = sectorSelFlag;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getCusFullname() {
        return cusFullname;
    }

    public void setCusFullname(String cusFullname) {
        this.cusFullname = cusFullname;
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

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getSecDesc() {
        return secDesc;
    }

    public void setSecDesc(String secDesc) {
        this.secDesc = secDesc;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getWorksheetHdrId() {
        return worksheetHdrId;
    }

    public void setWorksheetHdrId(String worksheetHdrId) {
        this.worksheetHdrId = worksheetHdrId;
    }

    public String getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(String draftNumber) {
        this.draftNumber = draftNumber;
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

    public String getOldRegId() {
        return oldRegId;
    }

    public void setOldRegId(String oldRegId) {
        this.oldRegId = oldRegId;
    }

    public String getLastedStatus() {
        return lastedStatus;
    }

    public void setLastedStatus(String lastedStatus) {
        this.lastedStatus = lastedStatus;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
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

    public String getCondTaxGrp() {
        return condTaxGrp;
    }

    public void setCondTaxGrp(String condTaxGrp) {
        this.condTaxGrp = condTaxGrp;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getOtherDutyCode() {
        return otherDutyCode;
    }

    public void setOtherDutyCode(String otherDutyCode) {
        this.otherDutyCode = otherDutyCode;
    }

    public String getOtherDutyName() {
        return otherDutyName;
    }

    public void setOtherDutyName(String otherDutyName) {
        this.otherDutyName = otherDutyName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
