package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Int05112Vo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8547333355566493196L;
	private String order;
	private String stampType;

	private Integer branchLastYeatNumberOfStamp = 0;
	private BigDecimal branchLastYeatMoneyOfStamp = new BigDecimal(0);

	private String octoberRecieve;
	private String octoberPay;

	private String novemberRecieve;
	private String novemberPay;

	private String decemberRecieve;
	private String decemberPay;

	private String januaryRecieve;
	private String januaryPay;

	private String februaryRecieve;
	private String februaryPay;

	private String marchRecieve;
	private String marchPay;

	private String aprilRecieve;
	private String aprilPay;

	private String mayRecieve;
	private String mayPay;

	private String juneRecieve;
	private String junePay;

	private String julyRecieve;
	private String julyPay;

	private String augustRecieve;
	private String augustPay;

	private String septemberRecieve;
	private String septemberPay;

	private Integer summaryYearRecieve = 0;
	private Integer summaryYearPay = 0;
	private BigDecimal summaryYearMoneyRecieve = new BigDecimal(0);
	private BigDecimal summaryYearMoneyPay = new BigDecimal(0);

	private Integer summaryTotalRecieve = 0;
	private Integer summaryTotalPay = 0;
	private BigDecimal summaryTotalMoneyRecieve = new BigDecimal(0);
	private BigDecimal summaryTotalMoneyPay = new BigDecimal(0);;

	private Integer branchUpToDateNumberOfStamp = 0;
	private BigDecimal branchUpToDateMoneyOfStamp = new BigDecimal(0);

	private String note;

	private String columnId;
	private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getStampType() {
		return stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public Integer getBranchLastYeatNumberOfStamp() {
		return branchLastYeatNumberOfStamp;
	}

	public void setBranchLastYeatNumberOfStamp(Integer branchLastYeatNumberOfStamp) {
		this.branchLastYeatNumberOfStamp = branchLastYeatNumberOfStamp;
	}

	public BigDecimal getBranchLastYeatMoneyOfStamp() {
		return branchLastYeatMoneyOfStamp;
	}

	public void setBranchLastYeatMoneyOfStamp(BigDecimal branchLastYeatMoneyOfStamp) {
		this.branchLastYeatMoneyOfStamp = branchLastYeatMoneyOfStamp;
	}

	public String getOctoberRecieve() {
		return octoberRecieve;
	}

	public void setOctoberRecieve(String octoberRecieve) {
		this.octoberRecieve = octoberRecieve;
	}

	public String getOctoberPay() {
		return octoberPay;
	}

	public void setOctoberPay(String octoberPay) {
		this.octoberPay = octoberPay;
	}

	public String getNovemberRecieve() {
		return novemberRecieve;
	}

	public void setNovemberRecieve(String novemberRecieve) {
		this.novemberRecieve = novemberRecieve;
	}

	public String getNovemberPay() {
		return novemberPay;
	}

	public void setNovemberPay(String novemberPay) {
		this.novemberPay = novemberPay;
	}

	public String getDecemberRecieve() {
		return decemberRecieve;
	}

	public void setDecemberRecieve(String decemberRecieve) {
		this.decemberRecieve = decemberRecieve;
	}

	public String getDecemberPay() {
		return decemberPay;
	}

	public void setDecemberPay(String decemberPay) {
		this.decemberPay = decemberPay;
	}

	public String getJanuaryRecieve() {
		return januaryRecieve;
	}

	public void setJanuaryRecieve(String januaryRecieve) {
		this.januaryRecieve = januaryRecieve;
	}

	public String getJanuaryPay() {
		return januaryPay;
	}

	public void setJanuaryPay(String januaryPay) {
		this.januaryPay = januaryPay;
	}

	public String getFebruaryRecieve() {
		return februaryRecieve;
	}

	public void setFebruaryRecieve(String februaryRecieve) {
		this.februaryRecieve = februaryRecieve;
	}

	public String getFebruaryPay() {
		return februaryPay;
	}

	public void setFebruaryPay(String februaryPay) {
		this.februaryPay = februaryPay;
	}

	public String getMarchRecieve() {
		return marchRecieve;
	}

	public void setMarchRecieve(String marchRecieve) {
		this.marchRecieve = marchRecieve;
	}

	public String getMarchPay() {
		return marchPay;
	}

	public void setMarchPay(String marchPay) {
		this.marchPay = marchPay;
	}

	public String getAprilRecieve() {
		return aprilRecieve;
	}

	public void setAprilRecieve(String aprilRecieve) {
		this.aprilRecieve = aprilRecieve;
	}

	public String getAprilPay() {
		return aprilPay;
	}

	public void setAprilPay(String aprilPay) {
		this.aprilPay = aprilPay;
	}

	public String getMayRecieve() {
		return mayRecieve;
	}

	public void setMayRecieve(String mayRecieve) {
		this.mayRecieve = mayRecieve;
	}

	public String getMayPay() {
		return mayPay;
	}

	public void setMayPay(String mayPay) {
		this.mayPay = mayPay;
	}

	public String getJuneRecieve() {
		return juneRecieve;
	}

	public void setJuneRecieve(String juneRecieve) {
		this.juneRecieve = juneRecieve;
	}

	public String getJunePay() {
		return junePay;
	}

	public void setJunePay(String junePay) {
		this.junePay = junePay;
	}

	public String getJulyRecieve() {
		return julyRecieve;
	}

	public void setJulyRecieve(String julyRecieve) {
		this.julyRecieve = julyRecieve;
	}

	public String getJulyPay() {
		return julyPay;
	}

	public void setJulyPay(String julyPay) {
		this.julyPay = julyPay;
	}

	public String getAugustRecieve() {
		return augustRecieve;
	}

	public void setAugustRecieve(String augustRecieve) {
		this.augustRecieve = augustRecieve;
	}

	public String getAugustPay() {
		return augustPay;
	}

	public void setAugustPay(String augustPay) {
		this.augustPay = augustPay;
	}

	public String getSeptemberRecieve() {
		return septemberRecieve;
	}

	public void setSeptemberRecieve(String septemberRecieve) {
		this.septemberRecieve = septemberRecieve;
	}

	public String getSeptemberPay() {
		return septemberPay;
	}

	public void setSeptemberPay(String septemberPay) {
		this.septemberPay = septemberPay;
	}

	public Integer getSummaryYearRecieve() {
		return summaryYearRecieve;
	}

	public void setSummaryYearRecieve(Integer summaryYearRecieve) {
		this.summaryYearRecieve = summaryYearRecieve;
	}

	public Integer getSummaryYearPay() {
		return summaryYearPay;
	}

	public void setSummaryYearPay(Integer summaryYearPay) {
		this.summaryYearPay = summaryYearPay;
	}

	public BigDecimal getSummaryYearMoneyRecieve() {
		return summaryYearMoneyRecieve;
	}

	public void setSummaryYearMoneyRecieve(BigDecimal summaryYearMoneyRecieve) {
		this.summaryYearMoneyRecieve = summaryYearMoneyRecieve;
	}

	public BigDecimal getSummaryYearMoneyPay() {
		return summaryYearMoneyPay;
	}

	public void setSummaryYearMoneyPay(BigDecimal summaryYearMoneyPay) {
		this.summaryYearMoneyPay = summaryYearMoneyPay;
	}

	public Integer getSummaryTotalRecieve() {
		return summaryTotalRecieve;
	}

	public void setSummaryTotalRecieve(Integer summaryTotalRecieve) {
		this.summaryTotalRecieve = summaryTotalRecieve;
	}

	public Integer getSummaryTotalPay() {
		return summaryTotalPay;
	}

	public void setSummaryTotalPay(Integer summaryTotalPay) {
		this.summaryTotalPay = summaryTotalPay;
	}

	public BigDecimal getSummaryTotalMoneyRecieve() {
		return summaryTotalMoneyRecieve;
	}

	public void setSummaryTotalMoneyRecieve(BigDecimal summaryTotalMoneyRecieve) {
		this.summaryTotalMoneyRecieve = summaryTotalMoneyRecieve;
	}

	public BigDecimal getSummaryTotalMoneyPay() {
		return summaryTotalMoneyPay;
	}

	public void setSummaryTotalMoneyPay(BigDecimal summaryTotalMoneyPay) {
		this.summaryTotalMoneyPay = summaryTotalMoneyPay;
	}

	public Integer getBranchUpToDateNumberOfStamp() {
		return branchUpToDateNumberOfStamp;
	}

	public void setBranchUpToDateNumberOfStamp(Integer branchUpToDateNumberOfStamp) {
		this.branchUpToDateNumberOfStamp = branchUpToDateNumberOfStamp;
	}

	public BigDecimal getBranchUpToDateMoneyOfStamp() {
		return branchUpToDateMoneyOfStamp;
	}

	public void setBranchUpToDateMoneyOfStamp(BigDecimal branchUpToDateMoneyOfStamp) {
		this.branchUpToDateMoneyOfStamp = branchUpToDateMoneyOfStamp;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

}
