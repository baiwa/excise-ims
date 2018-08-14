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
@Table(name="OA_ANALYSIS_DATA_HDR")
public class OaAnalysisDataHdr extends BaseEntity {
	
	private static final long serialVersionUID = -6604135401455176190L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ANALYSIS_DATA_HDR_GEN")
	@SequenceGenerator(name = "OA_ANALYSIS_DATA_HDR_GEN", sequenceName = "OA_ANALYSIS_DATA_HDR_SEQ", allocationSize = 1)
	@Column(name="ANALYSIS_DATA_HDR_ID")
	private Long analysisDataHdrId;
	
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
	
	@Column(name="FACTORY_NAME")
	private String factoryName;
	
	@Column(name="FACTORY_ADDRESS")
	private String factoryAddress;
	
	@Column(name="EXCISE_OWNER_AREA")
	private String exciseOwnerArea;
	
	@Column(name="PRODUCT_TYPE")
	private String productType;

	public Long getAnalysisDataHdrId() {
		return analysisDataHdrId;
	}

	public void setAnalysisDataHdrId(Long analysisDataHdrId) {
		this.analysisDataHdrId = analysisDataHdrId;
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getExciseOwnerArea() {
		return exciseOwnerArea;
	}

	public void setExciseOwnerArea(String exciseOwnerArea) {
		this.exciseOwnerArea = exciseOwnerArea;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	

	
	

	


}