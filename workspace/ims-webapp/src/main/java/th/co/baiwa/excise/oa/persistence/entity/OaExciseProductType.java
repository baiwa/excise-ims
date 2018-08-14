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
@Table(name="OA_EXCISE_PRODUCT_TYPE")
public class OaExciseProductType extends BaseEntity {

	private static final long serialVersionUID = 3984642422660382012L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_EXCISE_PRODUCT_TYPE_GEN")
	@SequenceGenerator(name = "OA_EXCISE_PRODUCT_TYPE_GEN", sequenceName = "OA_EXCISE_PRODUCT_TYPE_SEQ", allocationSize = 1)
	@Column(name="EXCISE_PRODUCT_TYPE_ID")
	private Long exciseProductTypeId;
	
	@Column(name="PRODUCT_TYPE_TEXT")
	private String productTypeText;
	
	@Column(name="PRODUCT_TYPE_VALUE")
	private String productTypeValue;

	public Long getExciseProductTypeId() {
		return exciseProductTypeId;
	}

	public void setExciseProductTypeId(Long exciseProductTypeId) {
		this.exciseProductTypeId = exciseProductTypeId;
	}

	public String getProductTypeText() {
		return productTypeText;
	}

	public void setProductTypeText(String productTypeText) {
		this.productTypeText = productTypeText;
	}

	public String getProductTypeValue() {
		return productTypeValue;
	}

	public void setProductTypeValue(String productTypeValue) {
		this.productTypeValue = productTypeValue;
	}
	
}