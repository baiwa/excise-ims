package th.go.excise.ims.mockup.persistence.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class ExciseTaxReceive {
    private BigDecimal taExciseTaxReceiveId;
    private String taExciseId;
    private String taExciseTaxReceiveMonth;
    private String taExciseTaxReceiveAmount;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public BigDecimal getTaExciseTaxReceiveId() {
        return taExciseTaxReceiveId;
    }

    public void setTaExciseTaxReceiveId(BigDecimal taExciseTaxReceiveId) {
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
