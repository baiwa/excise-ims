package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ExciseTaxReceive {
    private BigDecimal exciseTaxReceiveId;
    private String exciseId;
    private String exciseTaxReceiveMonth;
    private String exciseTaxReceiveAmount;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;
	public BigDecimal getExciseTaxReceiveId() {
		return exciseTaxReceiveId;
	}
	public void setExciseTaxReceiveId(BigDecimal exciseTaxReceiveId) {
		this.exciseTaxReceiveId = exciseTaxReceiveId;
	}
	public String getExciseId() {
		return exciseId;
	}
	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}
	public String getExciseTaxReceiveMonth() {
		return exciseTaxReceiveMonth;
	}
	public void setExciseTaxReceiveMonth(String exciseTaxReceiveMonth) {
		this.exciseTaxReceiveMonth = exciseTaxReceiveMonth;
	}
	public String getExciseTaxReceiveAmount() {
		return exciseTaxReceiveAmount;
	}
	public void setExciseTaxReceiveAmount(String exciseTaxReceiveAmount) {
		this.exciseTaxReceiveAmount = exciseTaxReceiveAmount;
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
