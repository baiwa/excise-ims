package th.co.baiwa.excise.oa.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name="OA_TAX_REDUCE_WS_DTL")
public class OaTaxReduceWsDtl extends BaseEntity {

	private static final long serialVersionUID = 3069715907932994258L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_TAX_REDUCE_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_TAX_REDUCE_WS_DTL_GEN", sequenceName = "OA_TAX_REDUCE_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="TAX_REDUCE_WS_DTL_ID")
	private Long taxReduceWsDtlId;
	
	@Column(name="BILL_NO")
	private String billNo;

	@Column(name="MONTH")
	private String month;
	
	@Column(name="PRODUCT_AMOUNT_1")
	private Long productAmount1;
	
	@Column(name="PRODUCT_AMOUNT_2")
	private Long productAmount2;
	
	@Column(name="TAX_AMOUNT")
	private Long taxAmount;
	
	@Column(name="TAX_PER_PRODUCT")
	private Long taxPerProduct;
	
	@Column(name="TAX_REDUCE_WS_DTL_ORDER")
	private String taxReduceWsDtlOrder;
	
	@Column(name="TAX_REDUCE_WS_HDR_ID")
	private Long taxReduceWsHdrId;
	
	@Column(name="TOTAL_TAX")
	private Long totalTax;

	public Long getTaxReduceWsDtlId() {
		return taxReduceWsDtlId;
	}

	public void setTaxReduceWsDtlId(Long taxReduceWsDtlId) {
		this.taxReduceWsDtlId = taxReduceWsDtlId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getProductAmount1() {
		return productAmount1;
	}

	public void setProductAmount1(Long productAmount1) {
		this.productAmount1 = productAmount1;
	}

	public Long getProductAmount2() {
		return productAmount2;
	}

	public void setProductAmount2(Long productAmount2) {
		this.productAmount2 = productAmount2;
	}

	public Long getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Long taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Long getTaxPerProduct() {
		return taxPerProduct;
	}

	public void setTaxPerProduct(Long taxPerProduct) {
		this.taxPerProduct = taxPerProduct;
	}

	public String getTaxReduceWsDtlOrder() {
		return taxReduceWsDtlOrder;
	}

	public void setTaxReduceWsDtlOrder(String taxReduceWsDtlOrder) {
		this.taxReduceWsDtlOrder = taxReduceWsDtlOrder;
	}

	public Long getTaxReduceWsHdrId() {
		return taxReduceWsHdrId;
	}

	public void setTaxReduceWsHdrId(Long taxReduceWsHdrId) {
		this.taxReduceWsHdrId = taxReduceWsHdrId;
	}

	public Long getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Long totalTax) {
		this.totalTax = totalTax;
	}

	


	
}