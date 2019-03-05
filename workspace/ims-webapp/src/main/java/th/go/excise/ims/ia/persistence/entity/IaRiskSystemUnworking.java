
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_RISK_SYSTEM_UNWORKING")
public class IaRiskSystemUnworking extends BaseEntity {

	private static final long serialVersionUID = -742764404301564140L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_SYSTEM_UNWORKING_GEN")
	@SequenceGenerator(name = "IA_RISK_SYSTEM_UNWORKING_GEN", sequenceName = "IA_RISK_SYSTEM_UNWORKING_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "SYSTEMCODE")
	private String systemcode;
	@Column(name = "SYSTEMNAME")
	private String systemname;
	@Column(name = "COUNTALL")
	private String countall;
	@Column(name = "COUNTERROR")
	private String counterror;
	@Column(name = "ERRORDETAIL_ERROR10")
	private String errordetailError10;
	@Column(name = "ERRORDETAIL_ERROR11")
	private String errordetailError11;
	@Column(name = "ERRORDETAIL_ERROR12")
	private String errordetailError12;
	@Column(name = "ERRORDETAIL_ERROR01")
	private String errordetailError01;
	@Column(name = "ERRORDETAIL_ERROR02")
	private String errordetailError02;
	@Column(name = "ERRORDETAIL_ERROR03")
	private String errordetailError03;
	@Column(name = "ERRORDETAIL_ERROR04")
	private String errordetailError04;
	@Column(name = "ERRORDETAIL_ERROR05")
	private String errordetailError05;
	@Column(name = "ERRORDETAIL_ERROR06")
	private String errordetailError06;
	@Column(name = "ERRORDETAIL_ERROR07")
	private String errordetailError07;
	@Column(name = "ERRORDETAIL_ERROR08")
	private String errordetailError08;
	@Column(name = "ERRORDETAIL_ERROR09")
	private String errordetailError09;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	public String getSystemname() {
		return systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public String getCountall() {
		return countall;
	}

	public void setCountall(String countall) {
		this.countall = countall;
	}

	public String getCounterror() {
		return counterror;
	}

	public void setCounterror(String counterror) {
		this.counterror = counterror;
	}

	public String getErrordetailError10() {
		return errordetailError10;
	}

	public void setErrordetailError10(String errordetailError10) {
		this.errordetailError10 = errordetailError10;
	}

	public String getErrordetailError11() {
		return errordetailError11;
	}

	public void setErrordetailError11(String errordetailError11) {
		this.errordetailError11 = errordetailError11;
	}

	public String getErrordetailError12() {
		return errordetailError12;
	}

	public void setErrordetailError12(String errordetailError12) {
		this.errordetailError12 = errordetailError12;
	}

	public String getErrordetailError01() {
		return errordetailError01;
	}

	public void setErrordetailError01(String errordetailError01) {
		this.errordetailError01 = errordetailError01;
	}

	public String getErrordetailError02() {
		return errordetailError02;
	}

	public void setErrordetailError02(String errordetailError02) {
		this.errordetailError02 = errordetailError02;
	}

	public String getErrordetailError03() {
		return errordetailError03;
	}

	public void setErrordetailError03(String errordetailError03) {
		this.errordetailError03 = errordetailError03;
	}

	public String getErrordetailError04() {
		return errordetailError04;
	}

	public void setErrordetailError04(String errordetailError04) {
		this.errordetailError04 = errordetailError04;
	}

	public String getErrordetailError05() {
		return errordetailError05;
	}

	public void setErrordetailError05(String errordetailError05) {
		this.errordetailError05 = errordetailError05;
	}

	public String getErrordetailError06() {
		return errordetailError06;
	}

	public void setErrordetailError06(String errordetailError06) {
		this.errordetailError06 = errordetailError06;
	}

	public String getErrordetailError07() {
		return errordetailError07;
	}

	public void setErrordetailError07(String errordetailError07) {
		this.errordetailError07 = errordetailError07;
	}

	public String getErrordetailError08() {
		return errordetailError08;
	}

	public void setErrordetailError08(String errordetailError08) {
		this.errordetailError08 = errordetailError08;
	}

	public String getErrordetailError09() {
		return errordetailError09;
	}

	public void setErrordetailError09(String errordetailError09) {
		this.errordetailError09 = errordetailError09;
	}

}
