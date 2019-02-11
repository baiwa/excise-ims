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
@Table(name = "TA_MAS_COND_HDR")
public class TaMasCondHdr extends BaseEntity {

	private static final long serialVersionUID = -6675585915167087980L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_HDR_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_HDR_GEN", sequenceName = "TA_MAS_COND_HDR_SEQ", allocationSize = 1)
	@Column(name = "COND_HDR_ID")
	private Long condHdrId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "MONTH_NUM")
	private Integer monthNum;
	@Column(name = "AREA_SEE_FLAG")
	private String areaSeeFlag;
	@Column(name = "AREA_SELECT_FLAG")
	private String areaSelectFlag;
	@Column(name = "NO_AUDIT_YEAR_NUM")
	private Integer noAuditYearNum;

	public Long getCondHdrId() {
		return condHdrId;
	}

	public void setCondHdrId(Long condHdrId) {
		this.condHdrId = condHdrId;
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
