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
@Table(name = "TA_FORM_TS0303_HDR")
public class TaFormTs0303Hdr extends BaseEntity {

	private static final long serialVersionUID = -785951656283210820L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0303_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0303_HDR_GEN", sequenceName = "TA_FORM_TS0303_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0303_HDR_ID")
	private Long formTs0303HdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;

	public Long getFormTs0303HdrId() {
		return formTs0303HdrId;
	}

	public void setFormTs0303HdrId(Long formTs0303HdrId) {
		this.formTs0303HdrId = formTs0303HdrId;
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

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

}
