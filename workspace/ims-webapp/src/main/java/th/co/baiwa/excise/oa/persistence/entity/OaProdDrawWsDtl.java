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
@Table(name="OA_PROD_DRAW_WS_DTL")
public class OaProdDrawWsDtl extends BaseEntity {
	
	private static final long serialVersionUID = 5744836066488689009L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PROD_DRAW_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_PROD_DRAW_WS_DTL_GEN", sequenceName = "OA_PROD_DRAW_WS_DTL_SEQ", allocationSize = 1)
	@Column(name="PRODUCT_DRAWING_WS_DTL_ID")
	private Long productDrawingWsDtlId;
	
	@Column(name="BILL_NO")
	private String billNo;

	@Column(name="DRAW")
	private Long draw;
	
	@Column(name="DRAWING_DATE")
	private String drawingDate;
	
	@Column(name="FORMULA_DRAW")
	private Long formulaDraw;
	
	@Column(name="PRODUCT_DRAWING_WS_DTL_OWDER")
	private String productDrawingWsDtlOwder;
	
	@Column(name="PRODUCT_DRAWING_WS_HDR_ID")
	private Long productDrawingWsHdrId;
	
	@Column(name="PRODUCT_FORMULA")
	private Long productFormula;
	
	@Column(name="RAW_MAT_REV")
	private Long rawMatRev;

	public Long getProductDrawingWsDtlId() {
		return productDrawingWsDtlId;
	}

	public void setProductDrawingWsDtlId(Long productDrawingWsDtlId) {
		this.productDrawingWsDtlId = productDrawingWsDtlId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public String getDrawingDate() {
		return drawingDate;
	}

	public void setDrawingDate(String drawingDate) {
		this.drawingDate = drawingDate;
	}

	public Long getFormulaDraw() {
		return formulaDraw;
	}

	public void setFormulaDraw(Long formulaDraw) {
		this.formulaDraw = formulaDraw;
	}

	public String getProductDrawingWsDtlOwder() {
		return productDrawingWsDtlOwder;
	}

	public void setProductDrawingWsDtlOwder(String productDrawingWsDtlOwder) {
		this.productDrawingWsDtlOwder = productDrawingWsDtlOwder;
	}

	public Long getProductDrawingWsHdrId() {
		return productDrawingWsHdrId;
	}

	public void setProductDrawingWsHdrId(Long productDrawingWsHdrId) {
		this.productDrawingWsHdrId = productDrawingWsHdrId;
	}

	public Long getProductFormula() {
		return productFormula;
	}

	public void setProductFormula(Long productFormula) {
		this.productFormula = productFormula;
	}

	public Long getRawMatRev() {
		return rawMatRev;
	}

	public void setRawMatRev(Long rawMatRev) {
		this.rawMatRev = rawMatRev;
	}
	
	


	
}