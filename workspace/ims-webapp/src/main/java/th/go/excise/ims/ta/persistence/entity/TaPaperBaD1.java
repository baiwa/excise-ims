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
@Table(name = "TA_PAPER_BA_D1")
public class TaPaperBaD1 extends BaseEntity {

	private static final long serialVersionUID = 5220171592428312443L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D1_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D1_GEN", sequenceName = "TA_PAPER_BA_D1_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D1_SEQ")
	private Long paperBaD1Seq;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "TAX_QTY")
	private BigDecimal taxQty;
	@Column(name = "MONTH_STMT_TAX_QTY")
	private BigDecimal monthStmtTaxQty;
	@Column(name = "DIFF_TAX_QTY")
	private BigDecimal diffTaxQty;

	public Long getPaperBaD1Seq() {
		return paperBaD1Seq;
	}

	public void setPaperBaD1Seq(Long paperBaD1Seq) {
		this.paperBaD1Seq = paperBaD1Seq;
	}

	public String getPaperBaCode() {
		return paperBaCode;
	}

	public void setPaperBaCode(String paperBaCode) {
		this.paperBaCode = paperBaCode;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public BigDecimal getTaxQty() {
		return taxQty;
	}

	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}

	public BigDecimal getMonthStmtTaxQty() {
		return monthStmtTaxQty;
	}

	public void setMonthStmtTaxQty(BigDecimal monthStmtTaxQty) {
		this.monthStmtTaxQty = monthStmtTaxQty;
	}

	public BigDecimal getDiffTaxQty() {
		return diffTaxQty;
	}

	public void setDiffTaxQty(BigDecimal diffTaxQty) {
		this.diffTaxQty = diffTaxQty;
	}

}
