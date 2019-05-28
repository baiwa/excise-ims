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
@Table(name = "TA_PAPER_BA_D7")
public class TaPaperBaD7 extends BaseEntity {

	private static final long serialVersionUID = 8890219942059419954L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D7_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D7_GEN", sequenceName = "TA_PAPER_BA_D7_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D7_SEQ")
	private Long paperBaD7Seq;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "INCOME_AMT")
	private BigDecimal incomeAmt;
	@Column(name = "DIFF_INCOME_AMT")
	private BigDecimal diffIncomeAmt;
	@Column(name = "DIFF_INCOME_PNT")
	private BigDecimal diffIncomePnt;

	public Long getPaperBaD7Seq() {
		return paperBaD7Seq;
	}

	public void setPaperBaD7Seq(Long paperBaD7Seq) {
		this.paperBaD7Seq = paperBaD7Seq;
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

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public BigDecimal getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(BigDecimal incomeAmt) {
		this.incomeAmt = incomeAmt;
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
