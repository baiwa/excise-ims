package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class MaterialsWorksheetDetail extends BaseEntity {
	private BigDecimal materialsWsDtlId;
	private BigDecimal materialsWsHeaderId;
	private String materialsWsDtlNo;
	private String materialsWsDtlOrder;
	private String materialsWsDtlBalance;
	private String materialsWsDtlCounting;

	public BigDecimal getMaterialsWsDtlId() {
		return materialsWsDtlId;
	}

	public void setMaterialsWsDtlId(BigDecimal materialsWsDtlId) {
		this.materialsWsDtlId = materialsWsDtlId;
	}

	public BigDecimal getMaterialsWsHeaderId() {
		return materialsWsHeaderId;
	}

	public void setMaterialsWsHeaderId(BigDecimal materialsWsHeaderId) {
		this.materialsWsHeaderId = materialsWsHeaderId;
	}

	public String getMaterialsWsDtlNo() {
		return materialsWsDtlNo;
	}

	public void setMaterialsWsDtlNo(String materialsWsDtlNo) {
		this.materialsWsDtlNo = materialsWsDtlNo;
	}

	public String getMaterialsWsDtlOrder() {
		return materialsWsDtlOrder;
	}

	public void setMaterialsWsDtlOrder(String materialsWsDtlOrder) {
		this.materialsWsDtlOrder = materialsWsDtlOrder;
	}

	public String getMaterialsWsDtlBalance() {
		return materialsWsDtlBalance;
	}

	public void setMaterialsWsDtlBalance(String materialsWsDtlBalance) {
		this.materialsWsDtlBalance = materialsWsDtlBalance;
	}

	public String getMaterialsWsDtlCounting() {
		return materialsWsDtlCounting;
	}

	public void setMaterialsWsDtlCounting(String materialsWsDtlCounting) {
		this.materialsWsDtlCounting = materialsWsDtlCounting;
	}

}
