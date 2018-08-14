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
@Table(name="OA_DIS_RAW_MAT_WS_DTL")
public class OaDisRawMatWsDtl extends BaseEntity {

	private static final long serialVersionUID = -625720094268547453L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_DIS_RAW_MAT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_DIS_RAW_MAT_WS_DTL_GEN", sequenceName = "OA_DIS_RAW_MAT_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="DIS_RAW_MAT_WS_DTL_ID")
	private Long disRawMatWsDtlId;
	
	@Column(name="DIS_RAW_MAT_HDR_ID")
	private Long disRawMatHdrId;
		
	@Column(name="DIS_RAW_MAT_DTL_NO")
	private Long disRawMatDtlNo;
	
	@Column(name="DIS_RAW_MAT_DTL_ORDER")
	private String disRawMatDtlOrder;
	
	@Column(name="RAW_MAT_REQUISITION")
	private String rawMatRequisition;
	
	@Column(name="DAY_BOOK_07_01")
	private Long dayBook0701;

	@Column(name="MONTH_BOOK_07_04")
	private Long monthBook0704;
	
	@Column(name="MONTHLY_REPORT")
	private Long monthlyReport;

	public Long getDisRawMatWsDtlId() {
		return disRawMatWsDtlId;
	}

	public void setDisRawMatWsDtlId(Long disRawMatWsDtlId) {
		this.disRawMatWsDtlId = disRawMatWsDtlId;
	}

	public Long getDisRawMatHdrId() {
		return disRawMatHdrId;
	}

	public void setDisRawMatHdrId(Long disRawMatHdrId) {
		this.disRawMatHdrId = disRawMatHdrId;
	}

	public Long getDisRawMatDtlNo() {
		return disRawMatDtlNo;
	}

	public void setDisRawMatDtlNo(Long disRawMatDtlNo) {
		this.disRawMatDtlNo = disRawMatDtlNo;
	}

	public String getDisRawMatDtlOrder() {
		return disRawMatDtlOrder;
	}

	public void setDisRawMatDtlOrder(String disRawMatDtlOrder) {
		this.disRawMatDtlOrder = disRawMatDtlOrder;
	}

	public String getRawMatRequisition() {
		return rawMatRequisition;
	}

	public void setRawMatRequisition(String rawMatRequisition) {
		this.rawMatRequisition = rawMatRequisition;
	}

	public Long getDayBook0701() {
		return dayBook0701;
	}

	public void setDayBook0701(Long dayBook0701) {
		this.dayBook0701 = dayBook0701;
	}

	public Long getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(Long monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public Long getMonthlyReport() {
		return monthlyReport;
	}

	public void setMonthlyReport(Long monthlyReport) {
		this.monthlyReport = monthlyReport;
	}
	
}