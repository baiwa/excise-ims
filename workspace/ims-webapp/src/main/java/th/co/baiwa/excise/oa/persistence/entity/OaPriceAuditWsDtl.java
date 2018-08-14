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
@Table(name="OA_PRICE_AUDIT_WS_DTL")
public class OaPriceAuditWsDtl extends BaseEntity {
	
	private static final long serialVersionUID = 5942956135661361527L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PRICE_AUDIT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_PRICE_AUDIT_WS_DTL_GEN", sequenceName = "OA_PRICE_AUDIT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="PRICE_AUDIT_WS_DTL_ID")
	private Long priceAuditWsDtlId;
	
	@Column(name="PRICE_AUDIT_WS_HDR_ID")
	private Long priceAuditWsHdrId;
	
	@Column(name="PRICE_AUDIT_WS_DTL_NO")
	private Long priceAuditWsDtlNo;
	
	@Column(name="PRICE_AUDIT_WS_DTL_ORDER")
	private String priceAuditWsDtlOrder;
	
	@Column(name="EXTERNAL_DATA")
	private Long externalData;
	
	@Column(name="INVESTIGATE_DATA")
	private Long investigateData;
	
	@Column(name="PRICE_02_01")
	private Long price0201;
	
	@Column(name="TAX_03_07")
	private Long tax0307;

	public Long getPriceAuditWsDtlId() {
		return priceAuditWsDtlId;
	}

	public void setPriceAuditWsDtlId(Long priceAuditWsDtlId) {
		this.priceAuditWsDtlId = priceAuditWsDtlId;
	}

	public Long getPriceAuditWsHdrId() {
		return priceAuditWsHdrId;
	}

	public void setPriceAuditWsHdrId(Long priceAuditWsHdrId) {
		this.priceAuditWsHdrId = priceAuditWsHdrId;
	}

	public Long getPriceAuditWsDtlNo() {
		return priceAuditWsDtlNo;
	}

	public void setPriceAuditWsDtlNo(Long priceAuditWsDtlNo) {
		this.priceAuditWsDtlNo = priceAuditWsDtlNo;
	}

	public String getPriceAuditWsDtlOrder() {
		return priceAuditWsDtlOrder;
	}

	public void setPriceAuditWsDtlOrder(String priceAuditWsDtlOrder) {
		this.priceAuditWsDtlOrder = priceAuditWsDtlOrder;
	}

	public Long getExternalData() {
		return externalData;
	}

	public void setExternalData(Long externalData) {
		this.externalData = externalData;
	}

	public Long getInvestigateData() {
		return investigateData;
	}

	public void setInvestigateData(Long investigateData) {
		this.investigateData = investigateData;
	}

	public Long getPrice0201() {
		return price0201;
	}

	public void setPrice0201(Long price0201) {
		this.price0201 = price0201;
	}

	public Long getTax0307() {
		return tax0307;
	}

	public void setTax0307(Long tax0307) {
		this.tax0307 = tax0307;
	}

	
}