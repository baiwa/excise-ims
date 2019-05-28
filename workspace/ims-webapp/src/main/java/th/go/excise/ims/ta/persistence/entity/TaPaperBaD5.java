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
@Table(name = "TA_PAPER_BA_D5")
public class TaPaperBaD5 extends BaseEntity {

	private static final long serialVersionUID = 7864471801773694792L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D5_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D5_GEN", sequenceName = "TA_PAPER_BA_D5_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D5_SEQ")
	private Long paperBaD5Seq;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "TAX_BY_VAL_AMT")
	private BigDecimal taxByValAmt;
	@Column(name = "TAX_BY_QTY_AMT")
	private BigDecimal taxByQtyAmt;
	@Column(name = "SUM_TAX_AMT")
	private BigDecimal sumTaxAmt;
	@Column(name = "ANA_TAX_BY_VAL_AMT")
	private BigDecimal anaTaxByValAmt;
	@Column(name = "ANA_TAX_BY_QTY_AMT")
	private BigDecimal anaTaxByQtyAmt;
	@Column(name = "SUM_ANA_TAX_AMT")
	private BigDecimal sumAnaTaxAmt;
	@Column(name = "DIFF_TAX_BY_VAL_AMT")
	private BigDecimal diffTaxByValAmt;
	@Column(name = "DIFF_TAX_BY_QTY_AMT")
	private BigDecimal diffTaxByQtyAmt;
	@Column(name = "DIFF_SUM_TAX_AMT")
	private BigDecimal diffSumTaxAmt;

	public Long getPaperBaD5Seq() {
		return paperBaD5Seq;
	}

	public void setPaperBaD5Seq(Long paperBaD5Seq) {
		this.paperBaD5Seq = paperBaD5Seq;
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

	public BigDecimal getTaxByValAmt() {
		return taxByValAmt;
	}

	public void setTaxByValAmt(BigDecimal taxByValAmt) {
		this.taxByValAmt = taxByValAmt;
	}

	public BigDecimal getTaxByQtyAmt() {
		return taxByQtyAmt;
	}

	public void setTaxByQtyAmt(BigDecimal taxByQtyAmt) {
		this.taxByQtyAmt = taxByQtyAmt;
	}

	public BigDecimal getSumTaxAmt() {
		return sumTaxAmt;
	}

	public void setSumTaxAmt(BigDecimal sumTaxAmt) {
		this.sumTaxAmt = sumTaxAmt;
	}

	public BigDecimal getAnaTaxByValAmt() {
		return anaTaxByValAmt;
	}

	public void setAnaTaxByValAmt(BigDecimal anaTaxByValAmt) {
		this.anaTaxByValAmt = anaTaxByValAmt;
	}

	public BigDecimal getAnaTaxByQtyAmt() {
		return anaTaxByQtyAmt;
	}

	public void setAnaTaxByQtyAmt(BigDecimal anaTaxByQtyAmt) {
		this.anaTaxByQtyAmt = anaTaxByQtyAmt;
	}

	public BigDecimal getSumAnaTaxAmt() {
		return sumAnaTaxAmt;
	}

	public void setSumAnaTaxAmt(BigDecimal sumAnaTaxAmt) {
		this.sumAnaTaxAmt = sumAnaTaxAmt;
	}

	public BigDecimal getDiffTaxByValAmt() {
		return diffTaxByValAmt;
	}

	public void setDiffTaxByValAmt(BigDecimal diffTaxByValAmt) {
		this.diffTaxByValAmt = diffTaxByValAmt;
	}

	public BigDecimal getDiffTaxByQtyAmt() {
		return diffTaxByQtyAmt;
	}

	public void setDiffTaxByQtyAmt(BigDecimal diffTaxByQtyAmt) {
		this.diffTaxByQtyAmt = diffTaxByQtyAmt;
	}

	public BigDecimal getDiffSumTaxAmt() {
		return diffSumTaxAmt;
	}

	public void setDiffSumTaxAmt(BigDecimal diffSumTaxAmt) {
		this.diffSumTaxAmt = diffSumTaxAmt;
	}

}
