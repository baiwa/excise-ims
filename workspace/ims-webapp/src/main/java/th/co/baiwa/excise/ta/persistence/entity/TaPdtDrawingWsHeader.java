package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PDT_DRAWING_WS_HEADER")

public class TaPdtDrawingWsHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3473093498223621573L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PDT_DRAWING_WS_HEADER_GEN")
	@SequenceGenerator(name = "TA_PDT_DRAWING_WS_HEADER_GEN", sequenceName = "TA_PDT_DRAWING_WS_HEADER_SEQ", allocationSize = 1)

	@Column(name = "TA_PDT_DRAWING_WS_HEADER_ID")
	private BigDecimal taPdtDrawingWsHeaderId;

	@Column(name = "TAX_PLAN_ID")
	private BigDecimal taxPlanId;

	@Column(name = "EXCISE_ID")
	private String exciseId;

	@Column(name = "TAXATION_ID")
	private String taxationId;

	@Column(name = "TA_ANALYSIS_ID")
	private String taAnalysisId;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "END_DATE")
	private String endDate;

	@Column(name = "PDT_TYPE")
	private String pdtType;

	@Column(name = "SUB_PDT_TYPE")
	private String subPdtType;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	public BigDecimal getTaPdtDrawingWsHeaderId() {
		return taPdtDrawingWsHeaderId;
	}

	public void setTaPdtDrawingWsHeaderId(BigDecimal taPdtDrawingWsHeaderId) {
		this.taPdtDrawingWsHeaderId = taPdtDrawingWsHeaderId;
	}

	public BigDecimal getTaxPlanId() {
		return taxPlanId;
	}

	public void setTaxPlanId(BigDecimal taxPlanId) {
		this.taxPlanId = taxPlanId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getTaxationId() {
		return taxationId;
	}

	public void setTaxationId(String taxationId) {
		this.taxationId = taxationId;
	}

	public String getTaAnalysisId() {
		return taAnalysisId;
	}

	public void setTaAnalysisId(String taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPdtType() {
		return pdtType;
	}

	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}

	public String getSubPdtType() {
		return subPdtType;
	}

	public void setSubPdtType(String subPdtType) {
		this.subPdtType = subPdtType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
