package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_DISB_RMAT_WORKSHEET_DETAIL database table.
 * 
 */
@Entity
@Table(name="TA_DISB_RMAT_WORKSHEET_DETAIL")
public class DisbRmatWorksheetDetail extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1379789233129240585L;

	@Id
	@SequenceGenerator(name="TA_DISB_RMAT_WS_DTL_GEN", sequenceName="TA_DISB_RMAT_WS_DTL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_DISB_RMAT_WS_DTL_GEN")
	@Column(name="TA_DISB_RMAT_WS_DTL_ID")
	private long taDisbRmatWsDtlId;

	@Column(name="DAY_BOOK_07_01")
	private Long dayBook0701;

	@Column(name="DISBURSE_RAW_MAT_DTL_NO")
	private Long disburseRawMatDtlNo;

	@Column(name="DISBURSE_RAW_MAT_DTL_ORDER")
	private String disburseRawMatDtlOrder;

	@Column(name="MAX_VALUES")
	private Long maxValues;

	@Column(name="MONTH_BOOK_07_04")
	private Long monthBook0704;

	@Column(name="MONTHLY_REPORT")
	private Long monthlyReport;

	@Column(name="RAW_MAT_REQUISITION")
	private Long rawMatRequisition;

	@Column(name="RESULT")
	private Long result;

	@Column(name="TA_DISBURSE_RMAT_HEADER_ID")
	private Long taDisburseRmatHeaderId;

	public long getTaDisbRmatWsDtlId() {
		return taDisbRmatWsDtlId;
	}

	public void setTaDisbRmatWsDtlId(long taDisbRmatWsDtlId) {
		this.taDisbRmatWsDtlId = taDisbRmatWsDtlId;
	}

	public Long getDayBook0701() {
		return dayBook0701;
	}

	public void setDayBook0701(Long dayBook0701) {
		this.dayBook0701 = dayBook0701;
	}

	public Long getDisburseRawMatDtlNo() {
		return disburseRawMatDtlNo;
	}

	public void setDisburseRawMatDtlNo(Long disburseRawMatDtlNo) {
		this.disburseRawMatDtlNo = disburseRawMatDtlNo;
	}

	public String getDisburseRawMatDtlOrder() {
		return disburseRawMatDtlOrder;
	}

	public void setDisburseRawMatDtlOrder(String disburseRawMatDtlOrder) {
		this.disburseRawMatDtlOrder = disburseRawMatDtlOrder;
	}

	public Long getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(Long maxValues) {
		this.maxValues = maxValues;
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

	public Long getRawMatRequisition() {
		return rawMatRequisition;
	}

	public void setRawMatRequisition(Long rawMatRequisition) {
		this.rawMatRequisition = rawMatRequisition;
	}

	public Long getResult() {
		return result;
	}

	public void setResult(Long result) {
		this.result = result;
	}

	public Long getTaDisburseRmatHeaderId() {
		return taDisburseRmatHeaderId;
	}

	public void setTaDisburseRmatHeaderId(Long taDisburseRmatHeaderId) {
		this.taDisburseRmatHeaderId = taDisburseRmatHeaderId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}