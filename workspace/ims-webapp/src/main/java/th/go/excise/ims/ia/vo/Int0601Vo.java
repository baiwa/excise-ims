package th.go.excise.ims.ia.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public class Int0601Vo {

	private String officeReceive;
	private String receiptDateFrom;
	private String receiptDateTo;
	private IaAuditIncH iaAuditIncH;

	public String getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(String officeReceive) {
		this.officeReceive = officeReceive;
	}

	public String getReceiptDateFrom() {
		return receiptDateFrom;
	}

	public void setReceiptDateFrom(String receiptDateFrom) {
		this.receiptDateFrom = receiptDateFrom;
	}

	public String getReceiptDateTo() {
		return receiptDateTo;
	}

	public void setReceiptDateTo(String receiptDateTo) {
		this.receiptDateTo = receiptDateTo;
	}

	public IaAuditIncH getIaAuditIncH() {
		return iaAuditIncH;
	}

	public void setIaAuditIncH(IaAuditIncH iaAuditIncH) {
		this.iaAuditIncH = iaAuditIncH;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
