package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_DRAFT_WORKSHEET_HDR")
public class TaDraftWorksheetHdr extends BaseEntity {

    private static final long serialVersionUID = 9124745681603965177L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_DRAFT_WORKSHEET_HDR_GEN")
    @SequenceGenerator(name = "TA_DRAFT_WORKSHEET_HDR_GEN", sequenceName = "TA_DRAFT_WORKSHEET_HDR_SEQ", allocationSize = 1)
    @Column(name = "DRAFT_WORKSHEET_HDR_ID")
    private Long draftWorksheetHdrId;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "DRAFT_NUMBER")
    private String draftNumber;
    @Column(name = "YEAR_MONTH_START")
    private String yearMonthStart;
    @Column(name = "YEAR_MONTH_END")
    private String yearMonthEnd;
    @Column(name = "MONTH_NUM")
    private Integer monthNum;
    @Column(name = "WORKSHEET_STATUS")
    private String worksheetStatus;
    @Column(name = "COND_SUB_CAPITAL_FLAG")
    private String condSubCapitalFlag;
    @Column(name = "COND_SUB_RISK_FLAG")
    private String condSubRiskFlag;
    @Column(name = "COND_SUB_NO_AUDIT_FLAG")
    private String condSubNoAuditFlag;

    public String getCondSubCapitalFlag() {
        return condSubCapitalFlag;
    }

    public void setCondSubCapitalFlag(String condSubCapitalFlag) {
        this.condSubCapitalFlag = condSubCapitalFlag;
    }

    public String getCondSubRiskFlag() {
        return condSubRiskFlag;
    }

    public void setCondSubRiskFlag(String condSubRiskFlag) {
        this.condSubRiskFlag = condSubRiskFlag;
    }

    public String getCondSubNoAuditFlag() {
        return condSubNoAuditFlag;
    }

    public void setCondSubNoAuditFlag(String condSubNoAuditFlag) {
        this.condSubNoAuditFlag = condSubNoAuditFlag;
    }

    public Long getDraftWorksheetHdrId() {
        return draftWorksheetHdrId;
    }

    public void setDraftWorksheetHdrId(Long draftWorksheetHdrId) {
        this.draftWorksheetHdrId = draftWorksheetHdrId;
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

    public String getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(String draftNumber) {
        this.draftNumber = draftNumber;
    }

    public String getYearMonthStart() {
        return yearMonthStart;
    }

    public void setYearMonthStart(String yearMonthStart) {
        this.yearMonthStart = yearMonthStart;
    }

    public String getYearMonthEnd() {
        return yearMonthEnd;
    }

    public void setYearMonthEnd(String yearMonthEnd) {
        this.yearMonthEnd = yearMonthEnd;
    }

    public Integer getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(Integer monthNum) {
        this.monthNum = monthNum;
    }

    public String getWorksheetStatus() {
        return worksheetStatus;
    }

    public void setWorksheetStatus(String worksheetStatus) {
        this.worksheetStatus = worksheetStatus;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
