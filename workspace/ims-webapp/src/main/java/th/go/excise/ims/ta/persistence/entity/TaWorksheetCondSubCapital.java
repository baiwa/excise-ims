
package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_SUB_CAPITAL")
public class TaWorksheetCondSubCapital extends BaseEntity {

    private static final long serialVersionUID = 6113664532857101577L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_CAPITAL_GEN")
    @SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_CAPITAL_GEN", sequenceName = "TA_COND_SUB_CAPITAL_SEQ", allocationSize = 1)
    @Column(name = "WORKSHEET_COND_SUB_CAPITAL_ID")
    private Long worksheetCondSubCapitalId;
    @Column(name = "DUTY_CODE")
    private String dutyCode;
    @Column(name = "HUGE_CAPITAL_AMOUNT")
    private BigDecimal hugeCapitalAmount;
    @Column(name = "LARGE_CAPITAL_AMOUNT")
    private BigDecimal largeCapitalAmount;
    @Column(name = "MEDIUM_CAPITAL_AMOUNT")
    private BigDecimal mediumCapitalAmount;
    @Column(name = "SMALL_CAPITAL_AMOUNT")
    private BigDecimal smallCapitalAmount;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "DRAFT_NUMBER")
    private String draftNumber;
    @Column(name = "ANALYSIS_NUMBER")
    private String analysisNumber;

    public Long getWorksheetCondSubCapitalId() {
        return worksheetCondSubCapitalId;
    }

    public void setWorksheetCondSubCapitalId(Long worksheetCondSubCapitalId) {
        this.worksheetCondSubCapitalId = worksheetCondSubCapitalId;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public BigDecimal getHugeCapitalAmount() {
        return hugeCapitalAmount;
    }

    public void setHugeCapitalAmount(BigDecimal hugeCapitalAmount) {
        this.hugeCapitalAmount = hugeCapitalAmount;
    }

    public BigDecimal getLargeCapitalAmount() {
        return largeCapitalAmount;
    }

    public void setLargeCapitalAmount(BigDecimal largeCapitalAmount) {
        this.largeCapitalAmount = largeCapitalAmount;
    }

    public BigDecimal getMediumCapitalAmount() {
        return mediumCapitalAmount;
    }

    public void setMediumCapitalAmount(BigDecimal mediumCapitalAmount) {
        this.mediumCapitalAmount = mediumCapitalAmount;
    }

    public BigDecimal getSmallCapitalAmount() {
        return smallCapitalAmount;
    }

    public void setSmallCapitalAmount(BigDecimal smallCapitalAmount) {
        this.smallCapitalAmount = smallCapitalAmount;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
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

}
