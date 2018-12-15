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



@Entity
@Table(name="TA_PLAN_CRITERIA")
public class PlanCriteria extends BaseEntity{
	
	
	private static final long serialVersionUID = 1727270580202244537L;

	@Id
	@SequenceGenerator(name="TA_PLAN_CRITERIA_GEN", sequenceName="TA_PLAN_CRITERIA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_PLAN_CRITERIA_GEN")
	@Column(name="TA_PLAN_CRITERIA_ID")
	private Long taPlanCriteriaId;
	
	@Column(name="TA_PLAN_TAX_AUDIT_ID")
	private Long taPlanTaxAuditId;
	
	@Column(name="MONTH_MAX")
	private BigDecimal monthMax;

	@Column(name="MONTH_MIN")
	private BigDecimal monthMin;

	@Column(name="PERCEN_TAGE_FROM")
	private BigDecimal percenTageFrom;

	@Column(name="PERCEN_TAGE_TO")
	private BigDecimal percenTageTo;
	

	public Long getTaPlanCriteriaId() {
		return taPlanCriteriaId;
	}

	public void setTaPlanCriteriaId(Long taPlanCriteriaId) {
		this.taPlanCriteriaId = taPlanCriteriaId;
	}

	public BigDecimal getMonthMax() {
		return monthMax;
	}

	public void setMonthMax(BigDecimal monthMax) {
		this.monthMax = monthMax;
	}

	public BigDecimal getMonthMin() {
		return monthMin;
	}

	public void setMonthMin(BigDecimal monthMin) {
		this.monthMin = monthMin;
	}

	public BigDecimal getPercenTageFrom() {
		return percenTageFrom;
	}

	public void setPercenTageFrom(BigDecimal percenTageFrom) {
		this.percenTageFrom = percenTageFrom;
	}

	public BigDecimal getPercenTageTo() {
		return percenTageTo;
	}

	public void setPercenTageTo(BigDecimal percenTageTo) {
		this.percenTageTo = percenTageTo;
	}

	public Long getTaPlanTaxAuditId() {
		return taPlanTaxAuditId;
	}

	public void setTaPlanTaxAuditId(Long taPlanTaxAuditId) {
		this.taPlanTaxAuditId = taPlanTaxAuditId;
	}

	
	
}