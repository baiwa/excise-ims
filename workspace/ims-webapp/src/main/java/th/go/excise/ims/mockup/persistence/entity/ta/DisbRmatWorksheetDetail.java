package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class DisbRmatWorksheetDetail {
    private Integer taDisbRmatWsDtlId;
    private Integer taDisburseRawMatHeadId;
    private Integer disburseRawMatDtlNo;
    private String disburseRawMatDtlOrder;
    private String rawMatRequisition;
    private String dayBook0701;
    private String monthBook0704;
    private String monthlyReport;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public Integer getTaDisbRmatWsDtlId() {
        return taDisbRmatWsDtlId;
    }

    public void setTaDisbRmatWsDtlId(Integer taDisbRmatWsDtlId) {
        this.taDisbRmatWsDtlId = taDisbRmatWsDtlId;
    }

    public Integer getTaDisburseRawMatHeadId() {
        return taDisburseRawMatHeadId;
    }

    public void setTaDisburseRawMatHeadId(Integer taDisburseRawMatHeadId) {
        this.taDisburseRawMatHeadId = taDisburseRawMatHeadId;
    }

    public Integer getDisburseRawMatDtlNo() {
        return disburseRawMatDtlNo;
    }

    public void setDisburseRawMatDtlNo(Integer disburseRawMatDtlNo) {
        this.disburseRawMatDtlNo = disburseRawMatDtlNo;
    }

    public String getDisburseRawMatDtlOrder() {
        return disburseRawMatDtlOrder;
    }

    public void setDisburseRawMatDtlOrder(String disburseRawMatDtlOrder) {
        this.disburseRawMatDtlOrder = disburseRawMatDtlOrder;
    }

    public String getRawMatRequisition() {
        return rawMatRequisition;
    }

    public void setRawMatRequisition(String rawMatRequisition) {
        this.rawMatRequisition = rawMatRequisition;
    }

    public String getDayBook0701() {
        return dayBook0701;
    }

    public void setDayBook0701(String dayBook0701) {
        this.dayBook0701 = dayBook0701;
    }

    public String getMonthBook0704() {
        return monthBook0704;
    }

    public void setMonthBook0704(String monthBook0704) {
        this.monthBook0704 = monthBook0704;
    }

    public String getMonthlyReport() {
        return monthlyReport;
    }

    public void setMonthlyReport(String monthlyReport) {
        this.monthlyReport = monthlyReport;
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
