
package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.*;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_SUB_RISK")
public class TaWorksheetCondSubRisk
        extends BaseEntity {

    private static final long serialVersionUID = -5088481495803377426L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_RISK_GEN")
    @SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_RISK_GEN", sequenceName = "TA_MAS_COND_SUB_RISK_SEQ", allocationSize = 1)
    @Column(name = "WORKSHEET_COND_SUB_RISK_ID")
    private Long worksheetCondSubRiskId;
    @Column(name = "DUTY_CODE")
    private String dutyCode;
    @Column(name = "RISK_LEVEL")
    private String riskLevel;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "DRAFT_NUMBER")
    private String draftNumber;
    @Column(name = "ANALYSIS_NUMBER")
    private String analysisNumber;

    public Long getWorksheetCondSubRiskId() {
        return worksheetCondSubRiskId;
    }

    public void setWorksheetCondSubRiskId(Long worksheetCondSubRiskId) {
        this.worksheetCondSubRiskId = worksheetCondSubRiskId;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
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
