package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class ExciseProductType extends BaseEntity {
	private BigDecimal taExciseRegisNumberId;
	private String productTypeValue;
	private String productTypeText;

	public BigDecimal getTaExciseRegisNumberId() {
		return taExciseRegisNumberId;
	}

	public void setTaExciseRegisNumberId(BigDecimal taExciseRegisNumberId) {
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

}
