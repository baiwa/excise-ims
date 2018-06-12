package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class ExciseTaxReceive {
    private Integer taExciseTaxReceiveId;
    private String taExciseId;
    private String taExciseTaxReceiveMonth;
    private String taExciseTaxReceiveAmount;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public Integer getTaExciseTaxReceiveId() {
        return taExciseTaxReceiveId;
    }

    public void setTaExciseTaxReceiveId(Integer taExciseTaxReceiveId) {
        this.taExciseTaxReceiveId = taExciseTaxReceiveId;
    }

    public String getTaExciseId() {
        return taExciseId;
    }

    public void setTaExciseId(String taExciseId) {
        this.taExciseId = taExciseId;
    }

    public String getTaExciseTaxReceiveMonth() {
        return taExciseTaxReceiveMonth;
    }

    public void setTaExciseTaxReceiveMonth(String taExciseTaxReceiveMonth) {
        this.taExciseTaxReceiveMonth = taExciseTaxReceiveMonth;
    }

    public String getTaExciseTaxReceiveAmount() {
        return taExciseTaxReceiveAmount;
    }

    public void setTaExciseTaxReceiveAmount(String taExciseTaxReceiveAmount) {
        this.taExciseTaxReceiveAmount = taExciseTaxReceiveAmount;
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
