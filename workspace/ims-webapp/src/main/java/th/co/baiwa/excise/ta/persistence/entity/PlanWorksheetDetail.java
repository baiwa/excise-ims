package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Repository
public class PlanWorksheetDetail extends BaseEntity {

	private BigDecimal worksheetDetailId;
	private String exciseId;
	private String analysNumber;
	private String month;
	private String year;
	private BigDecimal amount;

	public BigDecimal getWorksheetDetailId() {
		return worksheetDetailId;
	}

	public void setWorksheetDetailId(BigDecimal worksheetDetailId) {
		this.worksheetDetailId = worksheetDetailId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getAnalysNumber() {
		return analysNumber;
	}

	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
