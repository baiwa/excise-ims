package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_MAS_COND_DTL_TAX")
public class TaMasCondDtlTax extends BaseEntity {

	private static final long serialVersionUID = 7980671915299106648L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_DTL_TAX_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_DTL_TAX_GEN", sequenceName = "TA_MAS_COND_DTL_TAX_SEQ", allocationSize = 1)
	@Column(name = "COND_DTL_ID")
	private Long condDtlId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "COND_GROUP")
	private String condGroup;
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	@Column(name = "TAX_MONTH_START")
	private Integer taxMonthStart;
	@Column(name = "TAX_MONTH_END")
	private Integer taxMonthEnd;
	@Column(name = "RANGE_START")
	private Integer rangeStart;
	@Column(name = "RANGE_END")
	private Integer rangeEnd;
	@Column(name = "RISK_LEVEL")
	private String riskLevel;

	public Long getCondDtlId() {
		return condDtlId;
	}

	public void setCondDtlId(Long condDtlId) {
		this.condDtlId = condDtlId;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getCondGroup() {
		return condGroup;
	}

	public void setCondGroup(String condGroup) {
		this.condGroup = condGroup;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getTaxMonthStart() {
		return taxMonthStart;
	}

	public void setTaxMonthStart(Integer taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}

	public Integer getTaxMonthEnd() {
		return taxMonthEnd;
	}

	public void setTaxMonthEnd(Integer taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}

	public Integer getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(Integer rangeStart) {
		this.rangeStart = rangeStart;
	}

	public Integer getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(Integer rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

}
