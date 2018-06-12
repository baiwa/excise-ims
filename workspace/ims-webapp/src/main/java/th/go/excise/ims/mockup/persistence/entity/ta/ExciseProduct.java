package th.go.excise.ims.mockup.persistence.entity.ta;

import java.util.Date;

public class ExciseProduct {
	private Integer taExciseProductId;
	private Integer taExciseProductTypeId;
	private String productValue;
	private String productText;
	private String createdBy;
	private String updateBy;
	private Date createdDatetime;
	private Date updateDatetime;

	public Integer getTaExciseProductId() {
		return taExciseProductId;
	}

	public void setTaExciseProductId(Integer taExciseProductId) {
		this.taExciseProductId = taExciseProductId;
	}

	public Integer getTaExciseProductTypeId() {
		return taExciseProductTypeId;
	}

	public void setTaExciseProductTypeId(Integer taExciseProductTypeId) {
		this.taExciseProductTypeId = taExciseProductTypeId;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	public String getProductText() {
		return productText;
	}

	public void setProductText(String productText) {
		this.productText = productText;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
