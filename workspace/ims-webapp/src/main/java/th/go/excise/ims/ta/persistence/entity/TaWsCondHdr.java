package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_HDR")
public class TaWsCondHdr extends BaseEntity {

	private static final long serialVersionUID = 775358352767190422L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_HDR_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_HDR_GEN", sequenceName = "TA_WORKSHEET_COND_HDR_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_HDR_ID")
	private Long worksheetCondHdrId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "MONTH_NUM")
	private Integer monthNum;
	@Column(name = "YEAR_MONTH_START")
	private String yearMonthStart;
	@Column(name = "YEAR_MONTH_END")
	private String yearMonthEnd;
	@Column(name = "AREA_SEE_FLAG")
	private String areaSeeFlag;
	@Column(name = "AREA_SELECT_FLAG")
	private String areaSelectFlag;
	@Column(name = "NO_AUDIT_YEAR_NUM")
	private Integer noAuditYearNum;
	
	public Long getWorksheetCondHdrId() {
		return worksheetCondHdrId;
	}
	public void setWorksheetCondHdrId(Long worksheetCondHdrId) {
		this.worksheetCondHdrId = worksheetCondHdrId;
	}
	public String getAnalysisNumber() {
		return analysisNumber;
	}
	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public Integer getMonthNum() {
		return monthNum;
	}
	public void setMonthNum(Integer monthNum) {
		this.monthNum = monthNum;
	}
	public String getYearMonthStart() {
		return yearMonthStart;
	}
	public void setYearMonthStart(String yearMonthStart) {
		this.yearMonthStart = yearMonthStart;
	}
	public String getYearMonthEnd() {
		return yearMonthEnd;
	}
	public void setYearMonthEnd(String yearMonthEnd) {
		this.yearMonthEnd = yearMonthEnd;
	}
	public String getAreaSeeFlag() {
		return areaSeeFlag;
	}
	public void setAreaSeeFlag(String areaSeeFlag) {
		this.areaSeeFlag = areaSeeFlag;
	}
	public String getAreaSelectFlag() {
		return areaSelectFlag;
	}
	public void setAreaSelectFlag(String areaSelectFlag) {
		this.areaSelectFlag = areaSelectFlag;
	}
	public Integer getNoAuditYearNum() {
		return noAuditYearNum;
	}
	public void setNoAuditYearNum(Integer noAuditYearNum) {
		this.noAuditYearNum = noAuditYearNum;
	}
	
}
