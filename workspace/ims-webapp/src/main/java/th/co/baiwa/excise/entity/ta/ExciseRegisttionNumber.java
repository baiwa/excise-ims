package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class ExciseRegisttionNumber {
    private BigDecimal taExciseRegisttionNumberId;
    private String taExciseId;
    private String taExciseOperatorName;
    private String taExciseIdenNumber;
    private String taExciseFacName;
    private String taExciseFacAddress;
    private String taExciseArea;
    private BigDecimal taExciseRegisCapital;
    private String taExciseRemark;
    private String taExciseProductType;
    private String taExciseSectorArea;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public BigDecimal getTaExciseRegisttionNumberId() {
        return taExciseRegisttionNumberId;
    }

    public void setTaExciseRegisttionNumberId(BigDecimal taExciseRegisttionNumberId) {
        this.taExciseRegisttionNumberId = taExciseRegisttionNumberId;
    }

    public String getTaExciseId() {
        return taExciseId;
    }

    public void setTaExciseId(String taExciseId) {
        this.taExciseId = taExciseId;
    }

    public String getTaExciseOperatorName() {
        return taExciseOperatorName;
    }

    public void setTaExciseOperatorName(String taExciseOperatorName) {
        this.taExciseOperatorName = taExciseOperatorName;
    }

    public String getTaExciseIdenNumber() {
        return taExciseIdenNumber;
    }

    public void setTaExciseIdenNumber(String taExciseIdenNumber) {
        this.taExciseIdenNumber = taExciseIdenNumber;
    }

    public String getTaExciseFacName() {
        return taExciseFacName;
    }

    public void setTaExciseFacName(String taExciseFacName) {
        this.taExciseFacName = taExciseFacName;
    }

    public String getTaExciseFacAddress() {
        return taExciseFacAddress;
    }

    public void setTaExciseFacAddress(String taExciseFacAddress) {
        this.taExciseFacAddress = taExciseFacAddress;
    }

    public String getTaExciseArea() {
        return taExciseArea;
    }

    public void setTaExciseArea(String taExciseArea) {
        this.taExciseArea = taExciseArea;
    }

    public BigDecimal getTaExciseRegisCapital() {
        return taExciseRegisCapital;
    }

    public void setTaExciseRegisCapital(BigDecimal taExciseRegisCapital) {
        this.taExciseRegisCapital = taExciseRegisCapital;
    }

    public String getTaExciseRemark() {
        return taExciseRemark;
    }

    public void setTaExciseRemark(String taExciseRemark) {
        this.taExciseRemark = taExciseRemark;
    }

    public String getTaExciseProductType() {
        return taExciseProductType;
    }

    public void setTaExciseProductType(String taExciseProductType) {
        this.taExciseProductType = taExciseProductType;
    }

    public String getTaExciseSectorArea() {
        return taExciseSectorArea;
    }

    public void setTaExciseSectorArea(String taExciseSectorArea) {
        this.taExciseSectorArea = taExciseSectorArea;
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
