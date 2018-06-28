package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MaterialsWorkSheetHeader {
    private BigDecimal taMaterialsWsHeaderId;
    private BigDecimal taxPlanId;
    private String exciseId;
    private String taxationId;
    private String taAnalysisId;
    private String startDate;
    private String endDate;
    private String productType;
    private String subProductType;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public BigDecimal getTaMaterialsWsHeaderId() {
        return taMaterialsWsHeaderId;
    }

    public void setTaMaterialsWsHeaderId(BigDecimal taMaterialsWsHeaderId) {
        this.taMaterialsWsHeaderId = taMaterialsWsHeaderId;
    }

    public BigDecimal getTaxPlanId() {
        return taxPlanId;
    }

    public void setTaxPlanId(BigDecimal taxPlanId) {
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

    public String getTaAnalysisId() {
        return taAnalysisId;
    }

    public void setTaAnalysisId(String taAnalysisId) {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSubProductType() {
        return subProductType;
    }

    public void setSubProductType(String subProductType) {
        this.subProductType = subProductType;
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
