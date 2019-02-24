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
@Table(name = "TA_MAS_COND_MAIN_HDR")
public class TaMasCondMainHdr extends BaseEntity {

	private static final long serialVersionUID = -6675585915167087980L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_MAIN_HDR_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_MAIN_HDR_GEN", sequenceName = "TA_MAS_COND_MAIN_HDR_SEQ", allocationSize = 1)
	@Column(name = "COND_MAIN_HDR_ID")
	private Long condMainHdrId;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "MONTH_NUM")
	private Integer monthNum;

	public Long getCondMainHdrId() {
		return condMainHdrId;
	}

	public void setCondMainHdrId(Long condMainHdrId) {
		this.condMainHdrId = condMainHdrId;
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

}
