package th.go.excise.ims.ia.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public class Int0601Vo {

	private String officeReceive;
	private String receiptDateFrom;
	private String receiptDateTo;
	private IaAuditIncH iaAuditIncH;
	private List<IaAuditIncD1> iaAuditIncD1List;
	private List<IaAuditIncD2> iaAuditIncD2List;

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

	public List<IaAuditIncD1> getIaAuditIncD1List() {
		return iaAuditIncD1List;
	}

	public void setIaAuditIncD1List(List<IaAuditIncD1> iaAuditIncD1List) {
		this.iaAuditIncD1List = iaAuditIncD1List;
	}

	public List<IaAuditIncD2> getIaAuditIncD2List() {
		return iaAuditIncD2List;
	}

	public void setIaAuditIncD2List(List<IaAuditIncD2> iaAuditIncD2List) {
		this.iaAuditIncD2List = iaAuditIncD2List;
	}


}
