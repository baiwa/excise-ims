package th.co.baiwa.excise.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiveRmatWsHeader {
	private BigDecimal taReceiveRmatHeaderId;
	private BigDecimal taxPlanId;
	private String exciseId;
	private String taxationId;
	private BigDecimal taAnalysisId;
	private String startDate;
	private String endDate;
	private String pdtType;
	private String subPdtType;
	private String createdBy;
	private Date createdDatetime;
	private String updateBy;
	private Date updateDatetime;
	public BigDecimal getTaReceiveRmatHeaderId() {
		return taReceiveRmatHeaderId;
	}
	public void setTaReceiveRmatHeaderId(BigDecimal taReceiveRmatHeaderId) {
		this.taReceiveRmatHeaderId = taReceiveRmatHeaderId;
	}
	public BigDecimal getTaxPlanId() {
		return taxPlanId;
	}
	public void setTaxPlanId(BigDecimal taxPlanId) {
		this.taxPlanId = taxPlanId;
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
	public BigDecimal getTaAnalysisId() {
		return taAnalysisId;
	}
	public void setTaAnalysisId(BigDecimal taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
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
	public String getPdtType() {
		return pdtType;
	}
	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}
	public String getSubPdtType() {
		return subPdtType;
	}
	public void setSubPdtType(String subPdtType) {
		this.subPdtType = subPdtType;
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
