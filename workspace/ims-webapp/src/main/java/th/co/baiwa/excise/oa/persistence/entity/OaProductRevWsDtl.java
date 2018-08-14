package th.co.baiwa.excise.oa.persistence.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="OA_PRODUCT_REV_WS_DTL")
public class OaProductRevWsDtl implements Serializable {
	
	private static final long serialVersionUID = 3439661279330248716L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PRODUCT_REV_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_PRODUCT_REV_WS_DTL_GEN", sequenceName = "OA_PRODUCT_REV_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="PRODUCT_WS_DTL_ID")
	private Long productWsDtlId;
	
	@Column(name="ACCOUNT_07_02")
	private Long account0702;

	@Column(name="MONTH_BOOK_07_04")
	private Long monthBook0704;
	
	@Column(name="PRODUCT_REV_BILL")
	private String productRevBill;
	
	@Column(name="PRODUCT_WS_DTL_NO")
	private Long productWsDtlNo;
	
	@Column(name="PRODUCT_WS_DTL_ORDER")
	private String productWsDtlOrder;
	
	@Column(name="PRODUCT_WS_HDR_ID")
	private Long productWsHdrId;

	public Long getProductWsDtlId() {
		return productWsDtlId;
	}

	public void setProductWsDtlId(Long productWsDtlId) {
		this.productWsDtlId = productWsDtlId;
	}

	public Long getAccount0702() {
		return account0702;
	}

	public void setAccount0702(Long account0702) {
		this.account0702 = account0702;
	}

	public Long getMonthBook0704() {
		return monthBook0704;
	}

	public void setMonthBook0704(Long monthBook0704) {
		this.monthBook0704 = monthBook0704;
	}

	public String getProductRevBill() {
		return productRevBill;
	}

	public void setProductRevBill(String productRevBill) {
		this.productRevBill = productRevBill;
	}

	public Long getProductWsDtlNo() {
		return productWsDtlNo;
	}

	public void setProductWsDtlNo(Long productWsDtlNo) {
		this.productWsDtlNo = productWsDtlNo;
	}

	public String getProductWsDtlOrder() {
		return productWsDtlOrder;
	}

	public void setProductWsDtlOrder(String productWsDtlOrder) {
		this.productWsDtlOrder = productWsDtlOrder;
	}

	public Long getProductWsHdrId() {
		return productWsHdrId;
	}

	public void setProductWsHdrId(Long productWsHdrId) {
		this.productWsHdrId = productWsHdrId;
	}
	
	
	
}