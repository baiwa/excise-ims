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
@Table(name = "TA_PAPER_PR03_D")
public class TaPaperPr03D extends BaseEntity {

	private static final long serialVersionUID = -3818160727510573738L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR03_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR03_D_GEN", sequenceName = "TA_PAPER_PR03_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR03_D_SEQ")
	private Long paperPr03DSeq;
	@Column(name = "PAPER_PR_NUMBER")
	private String paperPrNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "BALANCE_BY_ACCOUNT_QTY")
	private BigDecimal balanceByAccountQty;
	@Column(name = "BALANCE_BY_COUNT_QTY")
	private BigDecimal balanceByCountQty;
	@Column(name = "MAX_DIFF_QTY")
	private BigDecimal maxDiffQty;

	public Long getPaperPr03DSeq() {
		return paperPr03DSeq;
	}

	public void setPaperPr03DSeq(Long paperPr03DSeq) {
		this.paperPr03DSeq = paperPr03DSeq;
	}

	public String getPaperPrNumber() {
		return paperPrNumber;
	}

	public void setPaperPrNumber(String paperPrNumber) {
		this.paperPrNumber = paperPrNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getBalanceByAccountQty() {
		return balanceByAccountQty;
	}

	public void setBalanceByAccountQty(BigDecimal balanceByAccountQty) {
		this.balanceByAccountQty = balanceByAccountQty;
	}

	public BigDecimal getBalanceByCountQty() {
		return balanceByCountQty;
	}

	public void setBalanceByCountQty(BigDecimal balanceByCountQty) {
		this.balanceByCountQty = balanceByCountQty;
	}

	public BigDecimal getMaxDiffQty() {
		return maxDiffQty;
	}

	public void setMaxDiffQty(BigDecimal maxDiffQty) {
		this.maxDiffQty = maxDiffQty;
	}

}
