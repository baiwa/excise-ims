package th.go.excise.ims.ia.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_INC_H")
public class IaAuditIncH extends BaseEntity {

	private static final long serialVersionUID = 6269722064821822351L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_H_GEN", sequenceName = "IA_AUDIT_INC_H_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_INC_H_ID")
	private Long iaAuditIncHId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "RECEIPT_DATE_FROM")
	private Date receiptDateFrom;
	@Column(name = "RECEIPT_DATE_TO")
	private Date receiptDateTo;
	@Column(name = "AUDIT_INC_NUMBER")
	private String auditIncNumber;

	public Long getIaAuditIncHId() {
		return iaAuditIncHId;
	}

	public void setIaAuditIncHId(Long iaAuditIncHId) {
		this.iaAuditIncHId = iaAuditIncHId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

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

	public String getAuditIncNumber() {
		return auditIncNumber;
	}

	public void setAuditIncNumber(String auditIncNumber) {
		this.auditIncNumber = auditIncNumber;
	}

}
