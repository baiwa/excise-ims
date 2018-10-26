package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_TAX_REDUCE_WS_DTL")
public class TaxReduceWsDtlS extends BaseEntity {

	private static final long serialVersionUID = 5053248345315888162L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_TAX_REDUCE_WS_DTL_GEN")
	@SequenceGenerator(name = "TA_TAX_REDUCE_WS_DTL_GEN", sequenceName = "TA_TAX_REDUCE_WS_DTL_SEQ", allocationSize = 1)

	@Column(name = "TA_TAX_REDUCE_WS_DTL_ID")
	private Long taTaxReduceWsDtlId;

	@Column(name = "TA_TAX_REDUCE_WS_HEADER_ID")
	private Long taTaxReduceWsHeaderId;

	@Column(name = "MONTH")
	private String month;

	@Column(name = "TA_TAX_REDUCE_WS_DTL_ORDER")
	private String taTaxReduceWsDtlOrder;

	@Column(name = "TOTAL_TAX")
	private String totalTax;

	@Column(name = "PDT_AMOUNT_1")
	private String pdtAmount1;

	@Column(name = "TAX_PER_PDT")
	private String taxPerPdt;

	@Column(name = "BILL_NO")
	private String billNo;

	@Column(name = "TAX_AMOUNT")
	private String taxAmount;

	@Column(name = "PDT_AMOUNT_2")
	private String pdtAmount2;

	@Column(name = "MAX_VALUES")
	private String maxValues;

	@Column(name = "RESULT")
	private String result;

	@Column(name = "LIST")
	private String list;

	public Long getTaTaxReduceWsDtlId() {
		return taTaxReduceWsDtlId;
	}

	public void setTaTaxReduceWsDtlId(Long taTaxReduceWsDtlId) {
		this.taTaxReduceWsDtlId = taTaxReduceWsDtlId;
	}

	public Long getTaTaxReduceWsHeaderId() {
		return taTaxReduceWsHeaderId;
	}

	public void setTaTaxReduceWsHeaderId(Long taTaxReduceWsHeaderId) {
		this.taTaxReduceWsHeaderId = taTaxReduceWsHeaderId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getTaTaxReduceWsDtlOrder() {
		return taTaxReduceWsDtlOrder;
	}

	public void setTaTaxReduceWsDtlOrder(String taTaxReduceWsDtlOrder) {
		this.taTaxReduceWsDtlOrder = taTaxReduceWsDtlOrder;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getPdtAmount1() {
		return pdtAmount1;
	}

	public void setPdtAmount1(String pdtAmount1) {
		this.pdtAmount1 = pdtAmount1;
	}

	public String getTaxPerPdt() {
		return taxPerPdt;
	}

	public void setTaxPerPdt(String taxPerPdt) {
		this.taxPerPdt = taxPerPdt;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}

	public String getPdtAmount2() {
		return pdtAmount2;
	}

	public void setPdtAmount2(String pdtAmount2) {
		this.pdtAmount2 = pdtAmount2;
	}

	public String getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(String maxValues) {
		this.maxValues = maxValues;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

}
