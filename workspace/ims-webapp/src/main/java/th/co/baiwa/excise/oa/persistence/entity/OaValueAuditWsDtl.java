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
@Table(name="OA_VALUE_AUDIT_WS_DTL")
public class OaValueAuditWsDtl extends BaseEntity  {
	
	private static final long serialVersionUID = 4779396185173041423L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_VALUE_AUDIT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_VALUE_AUDIT_WS_DTL_GEN", sequenceName = "OA_VALUE_AUDIT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="VALUE_AUDIT_WS_DTL_ID")
	private Long valueAuditWsDtlId;
	
	@Column(name="BILL_AMOUNT")
	private Long billAmount;

	@Column(name="DAY_BOOK_07_05")
	private Long dayBook0705;
	
	@Column(name="EXTERNAL_DATA")
	private Long externalData;
	
	@Column(name="MONTH")
	private String month;
	
	@Column(name="MONTH_BOOK_03_08")
	private Long monthBook0308;
	
	@Column(name="SERVICE_BILL")
	private Long serviceBill;

	@Column(name="VALUE_AUDIT_WS_DTL_ORDER")
	private String valueAuditWsDtlOrder;
	
	@Column(name="VALUE_AUDIT_WS_HDR_ID")
	private Long valueAuditWsHdrId;

	public Long getValueAuditWsDtlId() {
		return valueAuditWsDtlId;
	}

	public void setValueAuditWsDtlId(Long valueAuditWsDtlId) {
		this.valueAuditWsDtlId = valueAuditWsDtlId;
	}

	public Long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}

	public Long getDayBook0705() {
		return dayBook0705;
	}

	public void setDayBook0705(Long dayBook0705) {
		this.dayBook0705 = dayBook0705;
	}

	public Long getExternalData() {
		return externalData;
	}

	public void setExternalData(Long externalData) {
		this.externalData = externalData;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getMonthBook0308() {
		return monthBook0308;
	}

	public void setMonthBook0308(Long monthBook0308) {
		this.monthBook0308 = monthBook0308;
	}

	public Long getServiceBill() {
		return serviceBill;
	}

	public void setServiceBill(Long serviceBill) {
		this.serviceBill = serviceBill;
	}

	public String getValueAuditWsDtlOrder() {
		return valueAuditWsDtlOrder;
	}

	public void setValueAuditWsDtlOrder(String valueAuditWsDtlOrder) {
		this.valueAuditWsDtlOrder = valueAuditWsDtlOrder;
	}

	public Long getValueAuditWsHdrId() {
		return valueAuditWsHdrId;
	}

	public void setValueAuditWsHdrId(Long valueAuditWsHdrId) {
		this.valueAuditWsHdrId = valueAuditWsHdrId;
	}


}