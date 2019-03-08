
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
@Table(name = "TA_WORKSHEET_COND_SUB_NO_AUDIT")
public class TaWorksheetCondSubNoAudit
        extends BaseEntity {

    private static final long serialVersionUID = -6133514554765240333L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_NO_AUDIT_GEN")
    @SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_NO_AUDIT_GEN", sequenceName = "TA_COND_SUB_NO_AUDIT_SEQ", allocationSize = 1)
    @Column(name = "WORKSHEET_COND_SUB_NO_AUDIT_ID")
    private Long worksheetCondSubNoAuditId;
    @Column(name = "NO_TAX_AUDIT_YEAR_NUM")
    private BigDecimal noTaxAuditYearNum;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "DRAFT_NUMBER")
    private String draftNumber;
    @Column(name = "ANALYSIS_NUMBER")
    private String analysisNumber;

    public Long getWorksheetCondSubNoAuditId() {
        return worksheetCondSubNoAuditId;
    }

    public void setWorksheetCondSubNoAuditId(Long worksheetCondSubNoAuditId) {
        this.worksheetCondSubNoAuditId = worksheetCondSubNoAuditId;
    }

    public BigDecimal getNoTaxAuditYearNum() {
        return noTaxAuditYearNum;
    }

    public void setNoTaxAuditYearNum(BigDecimal noTaxAuditYearNum) {
        this.noTaxAuditYearNum = noTaxAuditYearNum;
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
