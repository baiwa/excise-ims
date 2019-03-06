package th.go.excise.ims.ta.vo;

import javax.persistence.Column;
import java.math.BigDecimal;

public class MasCondMainResponseVo {

    private Long masCondMainDtlId;
    private String officeCode;
    private String budgetYear;
    private String condNumber;
    private String condGroup;
    private String taxFreqType;
    private Integer taxMonthStart;
    private Integer taxMonthEnd;
    private String rangeTypeStart;
    private BigDecimal rangeStart;
    private String rangeTypeEnd;
    private BigDecimal rangeEnd;
    private String riskLevel;
    private String condType;
    private String condTypeDesc;

    public Long getMasCondMainDtlId() {
        return masCondMainDtlId;
    }

    public void setMasCondMainDtlId(Long masCondMainDtlId) {
        this.masCondMainDtlId = masCondMainDtlId;
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

    public String getCondNumber() {
        return condNumber;
    }

    public void setCondNumber(String condNumber) {
        this.condNumber = condNumber;
    }

    public String getCondGroup() {
        return condGroup;
    }

    public void setCondGroup(String condGroup) {
        this.condGroup = condGroup;
    }

    public String getTaxFreqType() {
        return taxFreqType;
    }

    public void setTaxFreqType(String taxFreqType) {
        this.taxFreqType = taxFreqType;
    }

    public Integer getTaxMonthStart() {
        return taxMonthStart;
    }

    public void setTaxMonthStart(Integer taxMonthStart) {
        this.taxMonthStart = taxMonthStart;
    }

    public Integer getTaxMonthEnd() {
        return taxMonthEnd;
    }

    public void setTaxMonthEnd(Integer taxMonthEnd) {
        this.taxMonthEnd = taxMonthEnd;
    }

    public String getRangeTypeStart() {
        return rangeTypeStart;
    }

    public void setRangeTypeStart(String rangeTypeStart) {
        this.rangeTypeStart = rangeTypeStart;
    }

    public BigDecimal getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(BigDecimal rangeStart) {
        this.rangeStart = rangeStart;
    }

    public String getRangeTypeEnd() {
        return rangeTypeEnd;
    }

    public void setRangeTypeEnd(String rangeTypeEnd) {
        this.rangeTypeEnd = rangeTypeEnd;
    }

    public BigDecimal getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(BigDecimal rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getCondType() {
        return condType;
    }

    public void setCondType(String condType) {
        this.condType = condType;
    }

    public String getCondTypeDesc() {
        return condTypeDesc;
    }

    public void setCondTypeDesc(String condTypeDesc) {
        this.condTypeDesc = condTypeDesc;
    }
}
