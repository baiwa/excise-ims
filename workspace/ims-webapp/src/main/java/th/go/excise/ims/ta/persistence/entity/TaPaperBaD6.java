package th.go.excise.ims.ta.persistence.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_BA_D6")
public class TaPaperBaD6 extends BaseEntity {

	private static final long serialVersionUID = 904936228710735826L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_BA_D6_GEN")
	@SequenceGenerator(name = "TA_PAPER_BA_D6_GEN", sequenceName = "TA_PAPER_BA_D6_SEQ", allocationSize = 1)
	@Column(name = "PAPER_BA_D6_SEQ")
	private Long paperBaD6Seq;
	@Column(name = "PAPER_BA_CODE")
	private String paperBaCode;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "TAX_MONTH")
	private String taxMonth;
	@Column(name = "TAX_SUBMISSION_DATE")
	private Date taxSubmissionDate;
	@Column(name = "ANA_TAX_SUBMISSION_DATE")
	private Date anaTaxSubmissionDate;
	@Column(name = "RESULT_TAX_SUBMISSION")
	private String resultTaxSubmission;

	public Long getPaperBaD6Seq() {
		return paperBaD6Seq;
	}

	public void setPaperBaD6Seq(Long paperBaD6Seq) {
		this.paperBaD6Seq = paperBaD6Seq;
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

	public Date getTaxSubmissionDate() {
		return taxSubmissionDate;
	}

	public void setTaxSubmissionDate(Date taxSubmissionDate) {
		this.taxSubmissionDate = taxSubmissionDate;
	}

	public Date getAnaTaxSubmissionDate() {
		return anaTaxSubmissionDate;
	}

	public void setAnaTaxSubmissionDate(Date anaTaxSubmissionDate) {
		this.anaTaxSubmissionDate = anaTaxSubmissionDate;
	}

	public String getResultTaxSubmission() {
		return resultTaxSubmission;
	}

	public void setResultTaxSubmission(String resultTaxSubmission) {
		this.resultTaxSubmission = resultTaxSubmission;
	}

}
