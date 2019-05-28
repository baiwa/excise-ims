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

	private static final long serialVersionUID = 5577527807050641488L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D8_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D8_GEN", sequenceName = "TA_PAPER_BA_D8_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D8_SEQ")
	private Long paperBaD8Seq;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "REC_NO")
	private String recNo;
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
