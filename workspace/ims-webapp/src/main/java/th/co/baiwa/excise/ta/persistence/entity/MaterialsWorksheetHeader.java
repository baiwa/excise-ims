package th.co.baiwa.excise.ta.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_MATERIALS_WORK_SHEET_HEADER database table.
 * 
 */
@Entity
@Table(name="TA_MATERIALS_WORK_SHEET_HEADER")
public class MaterialsWorksheetHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4890640980196443415L;

	@Id
	@SequenceGenerator(name="TA_MTRA_WORK_SHEET_HEADER_GEN", sequenceName="TA_MTRA_WORK_SHEET_HEADER_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_MTRA_WORK_SHEET_HEADER_GEN")
	@Column(name="TA_MATERIALS_WS_HEADER_ID")
	private long taMaterialsWsHeaderId;

	@Column(name="END_DATE")
	private String endDate;

	@Column(name="EXCISE_ID")
	private String exciseId;

	@Column(name="PRODUCT_TYPE")
	private String productType;

	@Column(name="START_DATE")
	private String startDate;

	@Column(name="SUB_PRODUCT_TYPE")
	private String subProductType;

	@Column(name="TA_ANALYSIS_ID")
	private String taAnalysisId;

	@Column(name="TAX_PLAN_ID")
	private BigDecimal taxPlanId;

	@Column(name="TAXATION_ID")
	private String taxationId;

	public MaterialsWorksheetHeader() {
	}

	public long getTaMaterialsWsHeaderId() {
		return this.taMaterialsWsHeaderId;
	}

	public void setTaMaterialsWsHeaderId(long taMaterialsWsHeaderId) {
		this.taMaterialsWsHeaderId = taMaterialsWsHeaderId;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExciseId() {
		return this.exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubProductType() {
		return this.subProductType;
	}

	public void setSubProductType(String subProductType) {
		this.subProductType = subProductType;
	}

	public String getTaAnalysisId() {
		return this.taAnalysisId;
	}

	public void setTaAnalysisId(String taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
	}

	public BigDecimal getTaxPlanId() {
		return this.taxPlanId;
	}

	public void setTaxPlanId(BigDecimal taxPlanId) {
		this.taxPlanId = taxPlanId;
	}

	public String getTaxationId() {
		return this.taxationId;
	}

	public void setTaxationId(String taxationId) {
		this.taxationId = taxationId;
	}

}