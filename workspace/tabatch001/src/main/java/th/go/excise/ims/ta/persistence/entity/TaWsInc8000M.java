package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_WS_INC8000_M")
public class TaWsInc8000M extends BaseEntity {

	private static final long serialVersionUID = 2162840180558825946L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WS_INC8000_M_GEN")
	@SequenceGenerator(name = "TA_WS_INC8000_M_GEN", sequenceName = "TA_WS_INC8000_M_SEQ", allocationSize = 1)
	@Column(name = "WS_INC8000_M_ID")
	private Long wsInc8000MId;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "TAX_YEAR")
	private String taxYear;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;
	@Column(name = "DUTY_CODE")
	private String dutyCode;
	@Column(name = "NET_TAX_AMOUNT")
	private BigDecimal netTaxAmount;
	@Column(name = "TAX_ADD_AMT")
	private BigDecimal taxAddAmt;
	@Column(name = "TAX_PEN_AMT")
	private BigDecimal taxPenAmt;
	@Column(name = "TAX_REDUCE_AMT")
	private BigDecimal taxReduceAmt;
	@Column(name = "TAX_CREDIT_ED_AMT")
	private BigDecimal taxCreditEdAmt;
	@Column(name = "TAX_YEAR_T")
	private String taxYearT;
	@Column(name = "TAX_MONTH_T")
	private String taxMonthT;
	@Column(name = "TAX_EXCEPT_AMT")
	private BigDecimal taxExceptAmt;
	@Column(name = "CUS_ID")
	private String cusId;
	@Column(name = "FAC_ID")
	private String facId;

	public Long getWsInc8000MId() {
		return wsInc8000MId;
	}

	public void setWsInc8000MId(Long wsInc8000MId) {
		this.wsInc8000MId = wsInc8000MId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public BigDecimal getNetTaxAmount() {
		return netTaxAmount;
	}

	public void setNetTaxAmount(BigDecimal netTaxAmount) {
		this.netTaxAmount = netTaxAmount;
	}

	public BigDecimal getTaxAddAmt() {
		return taxAddAmt;
	}

	public void setTaxAddAmt(BigDecimal taxAddAmt) {
		this.taxAddAmt = taxAddAmt;
	}

	public BigDecimal getTaxPenAmt() {
		return taxPenAmt;
	}

	public void setTaxPenAmt(BigDecimal taxPenAmt) {
		this.taxPenAmt = taxPenAmt;
	}

	public BigDecimal getTaxReduceAmt() {
		return taxReduceAmt;
	}

	public void setTaxReduceAmt(BigDecimal taxReduceAmt) {
		this.taxReduceAmt = taxReduceAmt;
	}

	public BigDecimal getTaxCreditEdAmt() {
		return taxCreditEdAmt;
	}

	public void setTaxCreditEdAmt(BigDecimal taxCreditEdAmt) {
		this.taxCreditEdAmt = taxCreditEdAmt;
	}

	public String getTaxYearT() {
		return taxYearT;
	}

	public void setTaxYearT(String taxYearT) {
		this.taxYearT = taxYearT;
	}

	public String getTaxMonthT() {
		return taxMonthT;
	}

	public void setTaxMonthT(String taxMonthT) {
		this.taxMonthT = taxMonthT;
	}

	public BigDecimal getTaxExceptAmt() {
		return taxExceptAmt;
	}

	public void setTaxExceptAmt(BigDecimal taxExceptAmt) {
		this.taxExceptAmt = taxExceptAmt;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

}
