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
@Table(name = "TA_PAPER_SV01_D")
public class TaPaperSv01D extends BaseEntity {

	private static final long serialVersionUID = -2904794431425584723L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV01_D_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV01_D_GEN", sequenceName = "TA_PAPER_SV01_D_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV01_D_SEQ")
	private Long paperSv01DSeq;
	@Column(name = "PAPER_SV_NUMBER")
	private String paperSvNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "GOODS_DESC")
	private String goodsDesc;
	@Column(name = "SERVICE_DOC_NO")
	private String serviceDocNo;
	@Column(name = "INCOME_DAILY_ACCOUNT_AMT")
	private BigDecimal incomeDailyAccountAmt;
	@Column(name = "PAYMENT_DOC_NO")
	private String paymentDocNo;
	@Column(name = "AUDIT_AMT")
	private BigDecimal auditAmt;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "DIFF_AMT")
	private BigDecimal diffAmt;

	public Long getPaperSv01DSeq() {
		return paperSv01DSeq;
	}

	public void setPaperSv01DSeq(Long paperSv01DSeq) {
		this.paperSv01DSeq = paperSv01DSeq;
	}

	public String getPaperSvNumber() {
		return paperSvNumber;
	}

	public void setPaperSvNumber(String paperSvNumber) {
		this.paperSvNumber = paperSvNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getServiceDocNo() {
		return serviceDocNo;
	}

	public void setServiceDocNo(String serviceDocNo) {
		this.serviceDocNo = serviceDocNo;
	}

	public BigDecimal getIncomeDailyAccountAmt() {
		return incomeDailyAccountAmt;
	}

	public void setIncomeDailyAccountAmt(BigDecimal incomeDailyAccountAmt) {
		this.incomeDailyAccountAmt = incomeDailyAccountAmt;
	}

	public String getPaymentDocNo() {
		return paymentDocNo;
	}

	public void setPaymentDocNo(String paymentDocNo) {
		this.paymentDocNo = paymentDocNo;
	}

	public BigDecimal getAuditAmt() {
		return auditAmt;
	}

	public void setAuditAmt(BigDecimal auditAmt) {
		this.auditAmt = auditAmt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getDiffAmt() {
		return diffAmt;
	}

	public void setDiffAmt(BigDecimal diffAmt) {
		this.diffAmt = diffAmt;
	}

}
