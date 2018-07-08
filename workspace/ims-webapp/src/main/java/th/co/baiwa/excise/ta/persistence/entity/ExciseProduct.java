package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class ExciseProduct extends BaseEntity {
	private BigDecimal taExciseProductId;
	private BigDecimal taExciseProductTypeId;
	private String productValue;
	private String productText;

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

}
