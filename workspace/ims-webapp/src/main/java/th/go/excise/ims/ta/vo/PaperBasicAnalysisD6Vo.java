package th.go.excise.ims.ta.vo;

import java.util.Date;

public class PaperBasicAnalysisD6Vo {
	private String paperBaNo;
	private String recNo;
	private String taxMonth;
	private Date taxSubmissionDate;
	private Date anaTaxSubmissionDate;
	private Date resultTaxSubmissionDate;

	public String getPaperBaNo() {
		return paperBaNo;
	}

	public void setPaperBaNo(String paperBaNo) {
		this.paperBaNo = paperBaNo;
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

	public Date getResultTaxSubmissionDate() {
		return resultTaxSubmissionDate;
	}

	public void setResultTaxSubmissionDate(Date resultTaxSubmissionDate) {
		this.resultTaxSubmissionDate = resultTaxSubmissionDate;
	}

}
