package th.co.baiwa.excise.ia.persistence.entity;

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
 * The persistent class for the IA_CONDITION database table.
 * 
 */
@Entity
@Table(name="IA_CONDITION")
public class Condition extends BaseEntity{

	
	private static final long serialVersionUID = 1491577113715501522L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CONDITION_GEN")
	@SequenceGenerator(name = "IA_CONDITION_GEN", sequenceName = "IA_CONDITION_SEQ", allocationSize = 1)
	@Column(name="CONDITION_ID")
	private Long conditionId;

	@Column(name="PARENT_ID")
	private Long parentId;
	
	@Column(name="CONDITION")
	private String condition;
	
	@Column(name="VALUE1")
	private BigDecimal value1;

	@Column(name="VALUE2")
	private BigDecimal value2;

	@Column(name="COLOR")
	private String color;

	@Column(name="VALUE_RL")
	private String valueRl;

	@Column(name="CONVERT_VALUE")
	private String convertValue;
	
	@Column(name="RISK_TYPE")
	private String riskType;
	
	@Column(name="PAGE")
	private String page;
	
	public Long getConditionId() {
		return conditionId;
	}

	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	

	public String getValueRl() {
		return valueRl;
	}

	public void setValueRl(String valueRl) {
		this.valueRl = valueRl;
	}

	public BigDecimal getValue1() {
		return value1;
	}

	public void setValue1(BigDecimal value1) {
		this.value1 = value1;
	}

	public BigDecimal getValue2() {
		return value2;
	}

	public void setValue2(BigDecimal value2) {
		this.value2 = value2;
	}

	public String getConvertValue() {
		return convertValue;
	}

	public void setConvertValue(String convertValue) {
		this.convertValue = convertValue;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	



}