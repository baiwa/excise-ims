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
@Table(name="OA_PLAN_WS_HDR_TEMP")
public class OaPlanWsHdrTemp extends BaseEntity {
	
	private static final long serialVersionUID = 8732629650021266661L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PLAN_WS_HDR_TEMP_GEN")
	@SequenceGenerator(name = "OA_PLAN_WS_HDR_TEMP_GEN", sequenceName = "OA_PLAN_WS_HDR_TEMP_SEQ", allocationSize = 1)
	@Column(name="WS_HDR_TEMP_ID")
	private Long wsHdrTempId;
	
	@Column(name="ANALYS_NUMBER")
	private Long analysNumber;
	
	@Column(name="EXCISE_ID")
	private String exciseId;
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	
	@Column(name="FACTORY_NAME")
	private String factoryName;

	@Column(name="FACTORY_ADDRESS")
	private String factoryAddress;
	
	@Column(name="EXCISE_OWNER_AREA")
	private String exciseOwnerArea;
	
	@Column(name="PRODUCT_TYPE")
	private String productType;

	public Long getWsHdrTempId() {
		return wsHdrTempId;
	}

	public void setWsHdrTempId(Long wsHdrTempId) {
		this.wsHdrTempId = wsHdrTempId;
	}

	public Long getAnalysNumber() {
		return analysNumber;
	}

	public void setAnalysNumber(Long analysNumber) {
		this.analysNumber = analysNumber;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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