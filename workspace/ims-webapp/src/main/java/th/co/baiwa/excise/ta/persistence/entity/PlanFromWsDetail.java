package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_PLAN_FROM_WS_DETAIL database table.
 * 
 */
@Entity
@Table(name="TA_PLAN_FROM_WS_DETAIL")
public class PlanFromWsDetail extends BaseEntity{

	
	private static final long serialVersionUID = -1325056966508157038L;

	@Id
	@SequenceGenerator(name="TA_PLAN_FROM_WS_DETAIL_GEN", sequenceName="TA_PLAN_FROM_WS_DETAIL_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_PLAN_FROM_WS_DETAIL_GEN")
	@Column(name="WORK_SHEET_DETAIL_ID")
	private long workSheetDetailId;

	@Column(name="AMOUNT")
	private BigDecimal amount;

	@Column(name="ANALYS_NUMBER")
	private String analysNumber;

	@Column(name="EXCISE_ID")
	private String exciseId;

	@Column(name="MAX_VALUES")
	private BigDecimal maxValues;

	@Column(name="MONTH")
	private String month;

	@Column(name="RESULT")
	private BigDecimal result;

	@Column(name="YEAR")
	private String year;

	public long getWorkSheetDetailId() {
		return workSheetDetailId;
	}

	public void setWorkSheetDetailId(long workSheetDetailId) {
		this.workSheetDetailId = workSheetDetailId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAnalysNumber() {
		return analysNumber;
	}

	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public BigDecimal getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(BigDecimal maxValues) {
		this.maxValues = maxValues;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	

}