package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_INT_CTRL_ASS")
public class IntCtrlAss extends BaseEntity {

	private static final long serialVersionUID = 8665380474747491357L;

	@Id
	@SequenceGenerator(name = "IA_INT_CTRL_ASS_GEN", sequenceName = "IA_INT_CTRL_ASS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_INT_CTRL_ASS_GEN")
	@Column(name = "INT_CTRL_ASS_ID")
	private Long intCtrlAssId;
	
	@Column(name = "INT_CTRL_ASS_NAME")
	private String intCtrlAssName;

	@Column(name = "BUDGET_YEAR")
	private String budgetYear;

	@Column(name = "OFFICE_CODE")
	private String officeCode;

	public Long getIntCtrlAssId() {
		return intCtrlAssId;
	}

	public void setIntCtrlAssId(Long intCtrlAssId) {
		this.intCtrlAssId = intCtrlAssId;
	}

	public String getIntCtrlAssName() {
		return intCtrlAssName;
	}

	public void setIntCtrlAssName(String intCtrlAssName) {
		this.intCtrlAssName = intCtrlAssName;
	}

	
	

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

}
