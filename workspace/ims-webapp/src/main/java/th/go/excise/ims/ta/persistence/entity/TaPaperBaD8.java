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
@Table(name = "TA_PAPER_BA_D8")
public class TaPaperBaD8 extends BaseEntity {

	private static final long serialVersionUID = -6777608496697759226L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D8_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D8_GEN", sequenceName = "TA_PAPER_BA_D8_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D8_SEQ")
	private Long paperBaD8Seq;
	@Column(name = "PAPER_BA_NUMBER")
	private String paperBaNumber;
	@Column(name = "SEQ_NO")
	private Integer seqNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "INCOME_LAST_YEAR_AMT")
	private BigDecimal incomeLastYearAmt;
	@Column(name = "INCOME_CURRENT_YEAR_AMT")
	private BigDecimal incomeCurrentYearAmt;
	@Column(name = "DIFF_INCOME_AMT")
	private BigDecimal diffIncomeAmt;
	@Column(name = "DIFF_INCOME_PNT")
	private BigDecimal diffIncomePnt;

	public Long getPaperBaD8Seq() {
		return paperBaD8Seq;
	}

	public void setPaperBaD8Seq(Long paperBaD8Seq) {
		this.paperBaD8Seq = paperBaD8Seq;
	}

	public String getPaperBaNumber() {
		return paperBaNumber;
	}

	public void setPaperBaNumber(String paperBaNumber) {
		this.paperBaNumber = paperBaNumber;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getIncomeLastYearAmt() {
		return incomeLastYearAmt;
	}

	public void setIncomeLastYearAmt(BigDecimal incomeLastYearAmt) {
		this.incomeLastYearAmt = incomeLastYearAmt;
	}

	public BigDecimal getIncomeCurrentYearAmt() {
		return incomeCurrentYearAmt;
	}

	public void setIncomeCurrentYearAmt(BigDecimal incomeCurrentYearAmt) {
		this.incomeCurrentYearAmt = incomeCurrentYearAmt;
	}

	public BigDecimal getDiffIncomeAmt() {
		return diffIncomeAmt;
	}

	public void setDiffIncomeAmt(BigDecimal diffIncomeAmt) {
		this.diffIncomeAmt = diffIncomeAmt;
	}

	public BigDecimal getDiffIncomePnt() {
		return diffIncomePnt;
	}

	public void setDiffIncomePnt(BigDecimal diffIncomePnt) {
		this.diffIncomePnt = diffIncomePnt;
	}

}
