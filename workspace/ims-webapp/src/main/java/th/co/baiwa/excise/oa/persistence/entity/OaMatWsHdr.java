package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="OA_MAT_WS_HDR")
public class OaMatWsHdr extends BaseEntity {
	
	private static final long serialVersionUID = -4521775354194995632L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_MAT_WS_HDR_GEN")
	@SequenceGenerator(name = "OA_MAT_WS_HDR_GEN", sequenceName = "OA_MAT_WS_HDR_SEQ", allocationSize = 1)
	@Column(name="MAT_WS_HDR_ID")
	private Long matWsHdrId;
	
	@Column(name="OPER_PLAN_ID")
	private Long operPlanId;
	
	@Column(name="EXCISE_ID")
	private String exciseId;
	
	@Column(name="TAXATION_ID")
	private String taxationId;
	
	@Column(name="ANALYSIS_ID")
	private Long analysisId;
	
	@Column(name="START_DATE")
	private String startDate;
	
	@Column(name="END_DATE")
	private String endDate;
	
	@Column(name="PRODUCT_TYPE")
	private String productType;
	
	@Column(name="SUB_PRODUCT_TYPE")
	private String subProductType;

	public Long getMatWsHdrId() {
		return matWsHdrId;
	}

	public void setMatWsHdrId(Long matWsHdrId) {
		this.matWsHdrId = matWsHdrId;
	}

	public Long getOperPlanId() {
		return operPlanId;
	}

	public void setOperPlanId(Long operPlanId) {
		this.operPlanId = operPlanId;
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

	public Long getAnalysisId() {
		return analysisId;
	}

	public void setAnalysisId(Long analysisId) {
		this.analysisId = analysisId;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSubProductType() {
		return subProductType;
	}

	public void setSubProductType(String subProductType) {
		this.subProductType = subProductType;
	}

	
}