package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_MEDICAL_RECEIPT")
public class MedicalReceipt extends BaseEntity {

	private static final long serialVersionUID = -471063600165016539L;

	@Id
	@SequenceGenerator(name = "IA_MEDICAL_RECEIPT_GEN", sequenceName = "IA_MEDICAL_RECEIPT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_MEDICAL_RECEIPT_GEN")
	@Column(name = "RECEIPT_ID")
	private BigDecimal receiptId;
	
	@Column(name="RECEIPT_NO")
	private String receiptNo;
	
	@Column(name="RECEIPT_AMOUNT")
	private BigDecimal receiptAmount;
	
	@Column(name="RECEIPT_DATE")
	private Date receiptDate;
	
	@Column(name="ID")
	private BigDecimal id;
	
	@Column(name="RECEIPT_TYPE")
	private String receiptType;
	
	
}
