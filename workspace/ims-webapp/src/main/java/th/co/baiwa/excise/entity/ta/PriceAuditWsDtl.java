package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class PriceAuditWsDtl {
	private BigDecimal taPriceAuditWsDtlId;
	private BigDecimal taPriceAuditWsHeaderId;
	private String taPriceAuditWsDtlNo;
	private String taPriceAuditWsDtlOrder;
	private String price0201;
	private String externalData;
	private String investigateData;
	private String tax0307;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaPriceAuditWsDtlId() {
		return taPriceAuditWsDtlId;
	}
	public void setTaPriceAuditWsDtlId(BigDecimal taPriceAuditWsDtlId) {
		this.taPriceAuditWsDtlId = taPriceAuditWsDtlId;
	}
	public BigDecimal getTaPriceAuditWsHeaderId() {
		return taPriceAuditWsHeaderId;
	}
	public void setTaPriceAuditWsHeaderId(BigDecimal taPriceAuditWsHeaderId) {
		this.taPriceAuditWsHeaderId = taPriceAuditWsHeaderId;
	}
	public String getTaPriceAuditWsDtlNo() {
		return taPriceAuditWsDtlNo;
	}
	public void setTaPriceAuditWsDtlNo(String taPriceAuditWsDtlNo) {
		this.taPriceAuditWsDtlNo = taPriceAuditWsDtlNo;
	}
	public String getTaPriceAuditWsDtlOrder() {
		return taPriceAuditWsDtlOrder;
	}
	public void setTaPriceAuditWsDtlOrder(String taPriceAuditWsDtlOrder) {
		this.taPriceAuditWsDtlOrder = taPriceAuditWsDtlOrder;
	}
	public String getPrice0201() {
		return price0201;
	}
	public void setPrice0201(String price0201) {
		this.price0201 = price0201;
	}
	public String getExternalData() {
		return externalData;
	}
	public void setExternalData(String externalData) {
		this.externalData = externalData;
	}
	public String getInvestigateData() {
		return investigateData;
	}
	public void setInvestigateData(String investigateData) {
		this.investigateData = investigateData;
	}
	public String getTax0307() {
		return tax0307;
	}
	public void setTax0307(String tax0307) {
		this.tax0307 = tax0307;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
}
