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
@Table(name="OA_EXCISE_PRODUCT")
public class OaExciseProduct extends BaseEntity {

	private static final long serialVersionUID = 1332865434562375050L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_EXCISE_PRODUCT_GEN")
	@SequenceGenerator(name = "OA_EXCISE_PRODUCT_GEN", sequenceName = "OA_EXCISE_PRODUCT_SEQ", allocationSize = 1)
	@Column(name="EXCISE_PRODUCT_ID")
	private Long exciseProductId;
	
	@Column(name="EXCISE_PRODUCT_TYPE_ID")
	private Long exciseProductTypeId;

	@Column(name="PRODUCT_VALUE")
	private String productValue;
	
	@Column(name="PRODUCT_TEXT")
	private String productText;
	
	public Long getExciseProductId() {
		return exciseProductId;
	}

	public void setExciseProductId(Long exciseProductId) {
		this.exciseProductId = exciseProductId;
	}

	public Long getExciseProductTypeId() {
		return exciseProductTypeId;
	}

	public void setExciseProductTypeId(Long exciseProductTypeId) {
		this.exciseProductTypeId = exciseProductTypeId;
	}

	public String getProductText() {
		return productText;
	}

	public void setProductText(String productText) {
		this.productText = productText;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}

	
}