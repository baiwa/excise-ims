package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class ExciseProductType {
    private Integer taExciseRegisNumberId;
    private String productTypeValue;
    private String productTypeText;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public Integer getTaExciseRegisNumberId() {
        return taExciseRegisNumberId;
    }

    public void setTaExciseRegisNumberId(Integer taExciseRegisNumberId) {
        this.taExciseRegisNumberId = taExciseRegisNumberId;
    }

    public String getProductTypeValue() {
        return productTypeValue;
    }

    public void setProductTypeValue(String productTypeValue) {
        this.productTypeValue = productTypeValue;
    }

    public String getProductTypeText() {
        return productTypeText;
    }

    public void setProductTypeText(String productTypeText) {
        this.productTypeText = productTypeText;
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
