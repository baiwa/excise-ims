package th.go.excise.ims.ta.vo;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class AnalysisTaxFilingVo extends DataTableRequest {

	private static final long serialVersionUID = 9133141236206101098L;

	private String taxMonth;
	private Date taxSubmissionDate;
	private Date anaTaxSubmissionDate;
	private Date resultTaxSubmissionDate;

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
