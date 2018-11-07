package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
@Entity
@Table(name="RULE_ID")
public class RentHouseRule extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8986338797579216204L;
	@Id
	@SequenceGenerator(name="IA_RENT_HOUSE_RULE_GEN", sequenceName="IA_RENT_HOUSE_RULE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_RENT_HOUSE_RULE_GEN")
	@Column(name="RULE_ID")
	private Long ruleId;
	
	@Column(name="YEAR")
	private String year;
	
	@Column(name="COPY_NUMBER")
	private String copyNumber;
	
	@Column(name="POSITION")
	private String position;
	
	@Column(name="LEVELS")
	private String levels;
	
	@Column(name="SALARY_FROM")
	private BigDecimal salaryFrom;
	
	@Column(name="SALARY_TO")
	private BigDecimal salaryTo;
	
	@Column(name="RENT_AMOUNT")
	private BigDecimal rentAmount;

	

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public BigDecimal getSalaryFrom() {
		return salaryFrom;
	}

	public void setSalaryFrom(BigDecimal salaryFrom) {
		this.salaryFrom = salaryFrom;
	}

	public BigDecimal getSalaryTo() {
		return salaryTo;
	}

	public void setSalaryTo(BigDecimal salaryTo) {
		this.salaryTo = salaryTo;
	}

	public BigDecimal getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(BigDecimal rentAmount) {
		this.rentAmount = rentAmount;
	}


}
