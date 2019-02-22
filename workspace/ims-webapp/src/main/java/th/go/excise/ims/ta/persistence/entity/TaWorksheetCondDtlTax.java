
package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_DTL_TAX")
public class TaWorksheetCondDtlTax extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5026626898547053068L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_DTL_TAX_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_DTL_TAX_GEN", sequenceName = "TA_WORKSHEET_COND_DTL_TAX_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_DTL_TAX_ID")
	private BigDecimal worksheetCondDtlTaxId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "COND_GROUP")
	private String condGroup;
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	@Column(name = "TAX_MONTH_START")
	private BigDecimal taxMonthStart;
	@Column(name = "TAX_MONTH_END")
	private BigDecimal taxMonthEnd;
	@Column(name = "RANGE_START")
	private BigDecimal rangeStart;
	@Column(name = "RANGE_END")
	private BigDecimal rangeEnd;
	@Column(name = "RISK_LEVEL")
	private String riskLevel;
	@Column(name = "COND_TYPE")
	private String condType;

	

	public BigDecimal getWorksheetCondDtlTaxId() {
		return worksheetCondDtlTaxId;
	}

	public void setWorksheetCondDtlTaxId(BigDecimal worksheetCondDtlTaxId) {
		this.worksheetCondDtlTaxId = worksheetCondDtlTaxId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
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

	public BigDecimal getTaxMonthStart() {
		return taxMonthStart;
	}

	public void setTaxMonthStart(BigDecimal taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}

	public BigDecimal getTaxMonthEnd() {
		return taxMonthEnd;
	}

	public void setTaxMonthEnd(BigDecimal taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}

	public BigDecimal getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(BigDecimal rangeStart) {
		this.rangeStart = rangeStart;
	}

	public BigDecimal getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(BigDecimal rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	
	public String getCondType() {
		return condType;
	}

	public void setCondType(String condType) {
		this.condType = condType;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
