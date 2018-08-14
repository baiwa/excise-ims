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
@Table(name="OA_REV_RAW_MAT_WS_DTL")
public class OaRevRawMatWsDtl extends BaseEntity {
	
	private static final long serialVersionUID = -5025634597578950590L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_REV_RAW_MAT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_REV_RAW_MAT_WS_DTL_GEN", sequenceName = "OA_REV_RAW_MAT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="REV_RAW_MAT_WS_DTL_ID")
	private Long revRawMatWsDtlId;	
	
	@Column(name="DAY_BOOK_07_01")
	private Long dayBook0701;
	
	@Column(name="EXTERNAL_DATA")
	private Long externalData;

	@Column(name="MONTH_BOOK_07_04")
	private Long monthBook0704;
	
	@Column(name="PURCHASE_TAX_INV")
	private String purchaseTaxInv;
	
	@Column(name="REV_RAW_MAT_DTL_NO")
	private Long revRawMatDtlNo;
	
	@Column(name="REV_RAW_MAT_DTL_ORDER")
	private String revRawMatDtlOrder;
	
	@Column(name="REV_RAW_MAT_HDR_ID")
	private Long revRawMatHdrId;

	public Long getRevRawMatWsDtlId() {
		return revRawMatWsDtlId;
	}

	public void setRevRawMatWsDtlId(Long revRawMatWsDtlId) {
		this.revRawMatWsDtlId = revRawMatWsDtlId;
	}

	public Long getDayBook0701() {
		return dayBook0701;
	}

	public void setDayBook0701(Long dayBook0701) {
		this.dayBook0701 = dayBook0701;
	}

	public Long getExternalData() {
		return externalData;
	}

	public void setExternalData(Long externalData) {
		this.externalData = externalData;
	}

	public Long getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(Long monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public String getPurchaseTaxInv() {
		return purchaseTaxInv;
	}

	public void setPurchaseTaxInv(String purchaseTaxInv) {
		this.purchaseTaxInv = purchaseTaxInv;
	}

	public Long getRevRawMatDtlNo() {
		return revRawMatDtlNo;
	}

	public void setRevRawMatDtlNo(Long revRawMatDtlNo) {
		this.revRawMatDtlNo = revRawMatDtlNo;
	}

	public String getRevRawMatDtlOrder() {
		return revRawMatDtlOrder;
	}

	public void setRevRawMatDtlOrder(String revRawMatDtlOrder) {
		this.revRawMatDtlOrder = revRawMatDtlOrder;
	}

	public Long getRevRawMatHdrId() {
		return revRawMatHdrId;
	}

	public void setRevRawMatHdrId(Long revRawMatHdrId) {
		this.revRawMatHdrId = revRawMatHdrId;
	}

	
}