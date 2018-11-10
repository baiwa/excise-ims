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
@Table(name = "TA_PDT_DRAWING_WS_DTL")

public class TaPdtDrawingWsDtl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2752672647625237905L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PDT_DRAWING_WS_DTL_GEN")
	@SequenceGenerator(name = "TA_PDT_DRAWING_WS_DTL_GEN", sequenceName = "TA_PDT_DRAWING_WS_DTL_SEQ", allocationSize = 1)

	@Column(name = "TA_PDT_DRAWING_WS_DTL_ID")
	private BigDecimal taPdtDrawingWsDtlId;

	@Column(name = "TA_PDT_DRAWING_WS_HEADER_ID")
	private BigDecimal taPdtDrawingWsHeaderId;

	@Column(name = "TA_PDT_DRAWING_WS_DTL_NO")
	private String taPdtDrawingWsDtlNo;

	@Column(name = "TA_PDT_DRAWING_WS_DTL_ORDER")
	private String taPdtDrawingWsDtlOrder;

	@Column(name = "VALUES_IN_BILL")
	private String valuesInBill;

	@Column(name = "VALUES_OF_PRODUCT")
	private String valuesOfProduct;

	@Column(name = "VALUES_OF_07_04")
	private String valuesOf0704;

	@Column(name = "MAX_VALUES")
	private String maxValues;

	@Column(name = "VALUES_OF_DRAWING")
	private String valuesOfDrawing;

	@Column(name = "RESULT")
	private String result;

	public BigDecimal getTaPdtDrawingWsDtlId() {
		return taPdtDrawingWsDtlId;
	}

	public void setTaPdtDrawingWsDtlId(BigDecimal taPdtDrawingWsDtlId) {
		this.taPdtDrawingWsDtlId = taPdtDrawingWsDtlId;
	}

	public BigDecimal getTaPdtDrawingWsHeaderId() {
		return taPdtDrawingWsHeaderId;
	}

	public void setTaPdtDrawingWsHeaderId(BigDecimal taPdtDrawingWsHeaderId) {
		this.taPdtDrawingWsHeaderId = taPdtDrawingWsHeaderId;
	}

	public String getTaPdtDrawingWsDtlNo() {
		return taPdtDrawingWsDtlNo;
	}

	public void setTaPdtDrawingWsDtlNo(String taPdtDrawingWsDtlNo) {
		this.taPdtDrawingWsDtlNo = taPdtDrawingWsDtlNo;
	}

	public String getTaPdtDrawingWsDtlOrder() {
		return taPdtDrawingWsDtlOrder;
	}

	public void setTaPdtDrawingWsDtlOrder(String taPdtDrawingWsDtlOrder) {
		this.taPdtDrawingWsDtlOrder = taPdtDrawingWsDtlOrder;
	}

	public String getValuesInBill() {
		return valuesInBill;
	}

	public void setValuesInBill(String valuesInBill) {
		this.valuesInBill = valuesInBill;
	}

	public String getValuesOfProduct() {
		return valuesOfProduct;
	}

	public void setValuesOfProduct(String valuesOfProduct) {
		this.valuesOfProduct = valuesOfProduct;
	}

	public String getValuesOf0704() {
		return valuesOf0704;
	}

	public void setValuesOf0704(String valuesOf0704) {
		this.valuesOf0704 = valuesOf0704;
	}

	public String getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(String maxValues) {
		this.maxValues = maxValues;
	}

	public String getValuesOfDrawing() {
		return valuesOfDrawing;
	}

	public void setValuesOfDrawing(String valuesOfDrawing) {
		this.valuesOfDrawing = valuesOfDrawing;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
