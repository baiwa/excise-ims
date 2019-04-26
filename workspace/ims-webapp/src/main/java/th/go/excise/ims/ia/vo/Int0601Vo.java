package th.go.excise.ims.ia.vo;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ws.client.persistence.entity.WsIncfri8020Inc;

public class Int0601Vo extends WsIncfri8020Inc{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9028920557799300449L;
	
	
	private Date receiptDateFrom;
	private Date receiptDateTo;
	private IaAuditIncH iaAuditIncH;
	
	public Date getReceiptDateFrom() {
		return receiptDateFrom;
	}
	
	public void setReceiptDateFrom(Date receiptDateFrom) {
		this.receiptDateFrom = receiptDateFrom;
	}
	
	public Date getReceiptDateTo() {
		return receiptDateTo;
	}
	
	public void setReceiptDateTo(Date receiptDateTo) {
		this.receiptDateTo = receiptDateTo;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public IaAuditIncH getIaAuditIncH() {
		return iaAuditIncH;
	}

	public void setIaAuditIncH(IaAuditIncH iaAuditIncH) {
		this.iaAuditIncH = iaAuditIncH;
	}
	
}
