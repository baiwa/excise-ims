package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class PdtDrawingWsHeader {
    private Integer taPdtDrawingWsHeaderId;
    private Integer taxPlanId;
    private String exciseId;
    private String taxationId;
    private Integer taAnalysisId;
    private String startDate;
    private String endDate;
    private String pdtType;
    private String subPdtType;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public Integer getTaPdtDrawingWsHeaderId() {
        return taPdtDrawingWsHeaderId;
    }

    public void setTaPdtDrawingWsHeaderId(Integer taPdtDrawingWsHeaderId) {
        this.taPdtDrawingWsHeaderId = taPdtDrawingWsHeaderId;
    }

    public Integer getTaxPlanId() {
        return taxPlanId;
    }

    public void setTaxPlanId(Integer taxPlanId) {
        this.taxPlanId = taxPlanId;
    }

    public String getExciseId() {
        return exciseId;
    }

    public void setExciseId(String exciseId) {
        this.exciseId = exciseId;
    }

    public String getTaxationId() {
        return taxationId;
    }

    public void setTaxationId(String taxationId) {
        this.taxationId = taxationId;
    }

    public Integer getTaAnalysisId() {
        return taAnalysisId;
    }

    public void setTaAnalysisId(Integer taAnalysisId) {
        this.taAnalysisId = taAnalysisId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPdtType() {
        return pdtType;
    }

    public void setPdtType(String pdtType) {
        this.pdtType = pdtType;
    }

    public String getSubPdtType() {
        return subPdtType;
    }

    public void setSubPdtType(String subPdtType) {
        this.subPdtType = subPdtType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
