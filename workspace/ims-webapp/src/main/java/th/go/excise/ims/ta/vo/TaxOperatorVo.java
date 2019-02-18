package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxOperatorVo {

    private List<String> condGroups;
    private List<TaxOperatorVoList> datas;

    // TODO vo list
    public static class TaxOperatorVoList {
        // private String newRegId;
        // private String condGroup;
        // private String cusFullname;
        // private String facFullname;
        // private String facAddress;
        // private String officeCode;
        private Integer countTaxPayOfMonth = 0;
        private List<String> taxYear = new ArrayList<>();
        private List<String> taxMonth = new ArrayList<>();
        private BigDecimal taxAmountBefore = BigDecimal.ZERO;
        private BigDecimal taxAmountAfter = BigDecimal.ZERO;
        private BigDecimal diffTaxAmount = BigDecimal.ZERO;
        private List<BigDecimal> taxAmount = new ArrayList<>();
        private String sectorName;
        // private String areaShotName;

        private String cusFullname;
        private String facFullname;
        private String facAddress;
        private String officeCode;
        private String secCode;
        private String secDesc;
        private String areaCode;
        private String areaDesc;
        private String worksheetHdrId;
        private String analysisNumber;
        private String newRegId;
        private BigDecimal sumTaxAmtG1;
        private BigDecimal sumTaxAmtG2;
        private BigDecimal taxAmtChnPnt;
        private BigDecimal taxMonthNo;
        private BigDecimal taxAmtG1M1;
        private BigDecimal taxAmtG1M2;
        private BigDecimal taxAmtG1M3;
        private BigDecimal taxAmtG1M4;
        private BigDecimal taxAmtG1M5;
        private BigDecimal taxAmtG1M6;
        private BigDecimal taxAmtG1M7;
        private BigDecimal taxAmtG1M8;
        private BigDecimal taxAmtG1M9;
        private BigDecimal taxAmtG1M10;
        private BigDecimal taxAmtG1M11;
        private BigDecimal taxAmtG1M12;
        private BigDecimal taxAmtG2M1;
        private BigDecimal taxAmtG2M2;
        private BigDecimal taxAmtG2M3;
        private BigDecimal taxAmtG2M4;
        private BigDecimal taxAmtG2M5;
        private BigDecimal taxAmtG2M6;
        private BigDecimal taxAmtG2M7;
        private BigDecimal taxAmtG2M8;
        private BigDecimal taxAmtG2M9;
        private BigDecimal taxAmtG2M10;
        private BigDecimal taxAmtG2M11;
        private BigDecimal taxAmtG2M12;
        private String condTaxGrp;

        public Integer getCountTaxPayOfMonth() {
            return countTaxPayOfMonth;
        }

        public void setCountTaxPayOfMonth(Integer countTaxPayOfMonth) {
            this.countTaxPayOfMonth = countTaxPayOfMonth;
        }

        public List<String> getTaxYear() {
            return taxYear;
        }

        public void setTaxYear(List<String> taxYear) {
            this.taxYear = taxYear;
        }

        public List<String> getTaxMonth() {
            return taxMonth;
        }

        public void setTaxMonth(List<String> taxMonth) {
            this.taxMonth = taxMonth;
        }

        public BigDecimal getTaxAmountBefore() {
            return taxAmountBefore;
        }

        public void setTaxAmountBefore(BigDecimal taxAmountBefore) {
            this.taxAmountBefore = taxAmountBefore;
        }

        public BigDecimal getTaxAmountAfter() {
            return taxAmountAfter;
        }

        public void setTaxAmountAfter(BigDecimal taxAmountAfter) {
            this.taxAmountAfter = taxAmountAfter;
        }

        public BigDecimal getDiffTaxAmount() {
            return diffTaxAmount;
        }

        public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
            this.diffTaxAmount = diffTaxAmount;
        }

        public List<BigDecimal> getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(List<BigDecimal> taxAmount) {
            this.taxAmount = taxAmount;
        }

        public String getSectorName() {
            return sectorName;
        }

        public void setSectorName(String sectorName) {
            this.sectorName = sectorName;
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

        public BigDecimal getSumTaxAmtG1() {
            return sumTaxAmtG1;
        }

        public void setSumTaxAmtG1(BigDecimal sumTaxAmtG1) {
            this.sumTaxAmtG1 = sumTaxAmtG1;
        }

        public BigDecimal getSumTaxAmtG2() {
            return sumTaxAmtG2;
        }

        public void setSumTaxAmtG2(BigDecimal sumTaxAmtG2) {
            this.sumTaxAmtG2 = sumTaxAmtG2;
        }

        public BigDecimal getTaxAmtChnPnt() {
            return taxAmtChnPnt;
        }

        public void setTaxAmtChnPnt(BigDecimal taxAmtChnPnt) {
            this.taxAmtChnPnt = taxAmtChnPnt;
        }

        public BigDecimal getTaxMonthNo() {
            return taxMonthNo;
        }

        public void setTaxMonthNo(BigDecimal taxMonthNo) {
            this.taxMonthNo = taxMonthNo;
        }

        public BigDecimal getTaxAmtG1M1() {
            return taxAmtG1M1;
        }

        public void setTaxAmtG1M1(BigDecimal taxAmtG1M1) {
            this.taxAmtG1M1 = taxAmtG1M1;
        }

        public BigDecimal getTaxAmtG1M2() {
            return taxAmtG1M2;
        }

        public void setTaxAmtG1M2(BigDecimal taxAmtG1M2) {
            this.taxAmtG1M2 = taxAmtG1M2;
        }

        public BigDecimal getTaxAmtG1M3() {
            return taxAmtG1M3;
        }

        public void setTaxAmtG1M3(BigDecimal taxAmtG1M3) {
            this.taxAmtG1M3 = taxAmtG1M3;
        }

        public BigDecimal getTaxAmtG1M4() {
            return taxAmtG1M4;
        }

        public void setTaxAmtG1M4(BigDecimal taxAmtG1M4) {
            this.taxAmtG1M4 = taxAmtG1M4;
        }

        public BigDecimal getTaxAmtG1M5() {
            return taxAmtG1M5;
        }

        public void setTaxAmtG1M5(BigDecimal taxAmtG1M5) {
            this.taxAmtG1M5 = taxAmtG1M5;
        }

        public BigDecimal getTaxAmtG1M6() {
            return taxAmtG1M6;
        }

        public void setTaxAmtG1M6(BigDecimal taxAmtG1M6) {
            this.taxAmtG1M6 = taxAmtG1M6;
        }

        public BigDecimal getTaxAmtG1M7() {
            return taxAmtG1M7;
        }

        public void setTaxAmtG1M7(BigDecimal taxAmtG1M7) {
            this.taxAmtG1M7 = taxAmtG1M7;
        }

        public BigDecimal getTaxAmtG1M8() {
            return taxAmtG1M8;
        }

        public void setTaxAmtG1M8(BigDecimal taxAmtG1M8) {
            this.taxAmtG1M8 = taxAmtG1M8;
        }

        public BigDecimal getTaxAmtG1M9() {
            return taxAmtG1M9;
        }

        public void setTaxAmtG1M9(BigDecimal taxAmtG1M9) {
            this.taxAmtG1M9 = taxAmtG1M9;
        }

        public BigDecimal getTaxAmtG1M10() {
            return taxAmtG1M10;
        }

        public void setTaxAmtG1M10(BigDecimal taxAmtG1M10) {
            this.taxAmtG1M10 = taxAmtG1M10;
        }

        public BigDecimal getTaxAmtG1M11() {
            return taxAmtG1M11;
        }

        public void setTaxAmtG1M11(BigDecimal taxAmtG1M11) {
            this.taxAmtG1M11 = taxAmtG1M11;
        }

        public BigDecimal getTaxAmtG1M12() {
            return taxAmtG1M12;
        }

        public void setTaxAmtG1M12(BigDecimal taxAmtG1M12) {
            this.taxAmtG1M12 = taxAmtG1M12;
        }

        public BigDecimal getTaxAmtG2M1() {
            return taxAmtG2M1;
        }

        public void setTaxAmtG2M1(BigDecimal taxAmtG2M1) {
            this.taxAmtG2M1 = taxAmtG2M1;
        }

        public BigDecimal getTaxAmtG2M2() {
            return taxAmtG2M2;
        }

        public void setTaxAmtG2M2(BigDecimal taxAmtG2M2) {
            this.taxAmtG2M2 = taxAmtG2M2;
        }

        public BigDecimal getTaxAmtG2M3() {
            return taxAmtG2M3;
        }

        public void setTaxAmtG2M3(BigDecimal taxAmtG2M3) {
            this.taxAmtG2M3 = taxAmtG2M3;
        }

        public BigDecimal getTaxAmtG2M4() {
            return taxAmtG2M4;
        }

        public void setTaxAmtG2M4(BigDecimal taxAmtG2M4) {
            this.taxAmtG2M4 = taxAmtG2M4;
        }

        public BigDecimal getTaxAmtG2M5() {
            return taxAmtG2M5;
        }

        public void setTaxAmtG2M5(BigDecimal taxAmtG2M5) {
            this.taxAmtG2M5 = taxAmtG2M5;
        }

        public BigDecimal getTaxAmtG2M6() {
            return taxAmtG2M6;
        }

        public void setTaxAmtG2M6(BigDecimal taxAmtG2M6) {
            this.taxAmtG2M6 = taxAmtG2M6;
        }

        public BigDecimal getTaxAmtG2M7() {
            return taxAmtG2M7;
        }

        public void setTaxAmtG2M7(BigDecimal taxAmtG2M7) {
            this.taxAmtG2M7 = taxAmtG2M7;
        }

        public BigDecimal getTaxAmtG2M8() {
            return taxAmtG2M8;
        }

        public void setTaxAmtG2M8(BigDecimal taxAmtG2M8) {
            this.taxAmtG2M8 = taxAmtG2M8;
        }

        public BigDecimal getTaxAmtG2M9() {
            return taxAmtG2M9;
        }

        public void setTaxAmtG2M9(BigDecimal taxAmtG2M9) {
            this.taxAmtG2M9 = taxAmtG2M9;
        }

        public BigDecimal getTaxAmtG2M10() {
            return taxAmtG2M10;
        }

        public void setTaxAmtG2M10(BigDecimal taxAmtG2M10) {
            this.taxAmtG2M10 = taxAmtG2M10;
        }

        public BigDecimal getTaxAmtG2M11() {
            return taxAmtG2M11;
        }

        public void setTaxAmtG2M11(BigDecimal taxAmtG2M11) {
            this.taxAmtG2M11 = taxAmtG2M11;
        }

        public BigDecimal getTaxAmtG2M12() {
            return taxAmtG2M12;
        }

        public void setTaxAmtG2M12(BigDecimal taxAmtG2M12) {
            this.taxAmtG2M12 = taxAmtG2M12;
        }

        public String getCondTaxGrp() {
            return condTaxGrp;
        }

        public void setCondTaxGrp(String condTaxGrp) {
            this.condTaxGrp = condTaxGrp;
        }

    }


    public List<String> getCondGroups() {
        return condGroups;
    }

    public void setCondGroups(List<String> condGroups) {
        this.condGroups = condGroups;
    }

    public List<TaxOperatorVoList> getDatas() {
        return datas;
    }

    public void setDatas(List<TaxOperatorVoList> datas) {
        this.datas = datas;
    }

}
