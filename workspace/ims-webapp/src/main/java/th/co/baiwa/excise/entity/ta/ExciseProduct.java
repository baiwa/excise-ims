package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class ExciseProduct {
	private BigDecimal taExciseProductId;
	private BigDecimal taExciseProductTypeId;
	private String productValue;
	private String productText;
	private String createdBy;
	private String updateBy;
	private Date createdDatetime;
	private Date updateDatetime;

	public BigDecimal getTaExciseProductId() {
		return taExciseProductId;
	}

	public void setTaExciseProductId(BigDecimal taExciseProductId) {
		this.taExciseProductId = taExciseProductId;
	}

	public BigDecimal getTaExciseProductTypeId() {
		return taExciseProductTypeId;
	}

	public void setTaExciseProductTypeId(BigDecimal taExciseProductTypeId) {
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
