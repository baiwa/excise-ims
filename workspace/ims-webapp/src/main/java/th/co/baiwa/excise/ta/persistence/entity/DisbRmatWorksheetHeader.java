package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_DISB_RMAT_WORKSHEET_HEADER database table.
 * 
 */
@Entity
@Table(name="TA_DISB_RMAT_WORKSHEET_HEADER")
public class DisbRmatWorksheetHeader extends BaseEntity  {

	private static final long serialVersionUID = 2811600588954004220L;

	@Id
	@SequenceGenerator(name="TA_DISB_RMAT_WS_HEADER_GEN", sequenceName="TA_DISB_RMAT_WS_HDR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_DISB_RMAT_WS_HEADER_GEN")
	@Column(name="TA_DISBURSE_RMAT_HEADER_ID")
	private long taDisburseRmatHeaderId;

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
	private Long taxPlanId;

	@Column(name="TAXATION_ID")
	private String taxationId;
	
	@Column(name="PATH_FILE")
	private String pathFile;

	public long getTaDisburseRmatHeaderId() {
		return taDisburseRmatHeaderId;
	}

	public void setTaDisburseRmatHeaderId(long taDisburseRmatHeaderId) {
		this.taDisburseRmatHeaderId = taDisburseRmatHeaderId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubProductType() {
		return subProductType;
	}

	public void setSubProductType(String subProductType) {
		this.subProductType = subProductType;
	}

	public String getTaAnalysisId() {
		return taAnalysisId;
	}

	public void setTaAnalysisId(String taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
	}

	public Long getTaxPlanId() {
		return taxPlanId;
	}

	public void setTaxPlanId(Long taxPlanId) {
		this.taxPlanId = taxPlanId;
	}

	public String getTaxationId() {
		return taxationId;
	}

	public void setTaxationId(String taxationId) {
		this.taxationId = taxationId;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	
}