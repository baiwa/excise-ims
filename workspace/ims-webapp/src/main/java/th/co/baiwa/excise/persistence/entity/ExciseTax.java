package th.co.baiwa.excise.persistence.entity;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ExciseTax {
    private Integer exciseTaxReceiveId;
    private String exciseId;
    private String exciseTaxReceiveMonth;
    private String exciseTaxReceiveAmount;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String updateBy;
    private  LocalDateTime  updateDatetime;

    public Integer getExciseTaxReceiveId() {
        return exciseTaxReceiveId;
    }

    public void setExciseTaxReceiveId(Integer exciseTaxReceiveId) {
        this.exciseTaxReceiveId = exciseTaxReceiveId;
    }

    public String getExciseId() {
        return exciseId;
    }

    public void setExciseId(String exciseId) {
        this.exciseId = exciseId;
    }

    public String getExciseTaxReceiveMonth() {
        return exciseTaxReceiveMonth;
    }

    public void setExciseTaxReceiveMonth(String exciseTaxReceiveMonth) {
        this.exciseTaxReceiveMonth = exciseTaxReceiveMonth;
    }

    public String getExciseTaxReceiveAmount() {
        return exciseTaxReceiveAmount;
    }

    public void setExciseTaxReceiveAmount(String exciseTaxReceiveAmount) {
        this.exciseTaxReceiveAmount = exciseTaxReceiveAmount;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}