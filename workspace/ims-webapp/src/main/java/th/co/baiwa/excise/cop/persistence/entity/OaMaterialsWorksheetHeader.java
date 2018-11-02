package th.co.baiwa.excise.cop.persistence.entity;

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
 * The persistent class for the OA_MATERIALS_WORK_SHEET_HEADER database table.
 * 
 */
@Entity
@Table(name="OA_MATERIALS_WORK_SHEET_HEADER")
public class OaMaterialsWorksheetHeader extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4890640980196443415L;

	@Id
	@SequenceGenerator(name="OA_MTRA_WORK_SHEET_HEADER_GEN", sequenceName="OA_MATERIALS_WS_HEADE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OA_MTRA_WORK_SHEET_HEADER_GEN")
	@Column(name="OA_MATERIALS_WS_HEADER_ID")
	private long oaMaterialsWsHeaderId;

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

	@Column(name="OA_ANALYSIS_ID")
	private String oaAnalysisId;

	@Column(name="TAX_PLAN_ID")
	private BigDecimal taxPlanId;

	@Column(name="TAXATION_ID")
	private String taxationId;

	public OaMaterialsWorksheetHeader() {
	}

	public long getOaMaterialsWsHeaderId() {
		return this.oaMaterialsWsHeaderId;
	}

	public void setOaMaterialsWsHeaderId(long oaMaterialsWsHeaderId) {
		this.oaMaterialsWsHeaderId = oaMaterialsWsHeaderId;
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

	public String getOaAnalysisId() {
		return this.oaAnalysisId;
	}

	public void setOaAnalysisId(String oaAnalysisId) {
		this.oaAnalysisId = oaAnalysisId;
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