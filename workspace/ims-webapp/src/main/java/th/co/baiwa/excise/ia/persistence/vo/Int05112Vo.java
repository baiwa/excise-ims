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

	private Integer octoberRecieve = 0;
	private Integer octoberPay = 0;

	private Integer novemberRecieve = 0;
	private Integer novemberPay = 0;

	private Integer decemberRecieve = 0;
	private Integer decemberPay = 0;

	private Integer januaryRecieve = 0;
	private Integer januaryPay = 0; 

	private Integer februaryRecieve = 0;
	private Integer februaryPay = 0;

	private Integer marchRecieve = 0;
	private Integer marchPay = 0;

	private Integer aprilRecieve = 0;
	private Integer aprilPay = 0;

	private Integer mayRecieve = 0;
	private Integer mayPay;

	private Integer juneRecieve = 0;
	private Integer junePay = 0;

	private Integer julyRecieve = 0;
	private Integer julyPay = 0;

	private Integer augustRecieve = 0;
	private Integer augustPay = 0;

	private Integer septemberRecieve = 0;
	private Integer septemberPay = 0;

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

	public Integer getOctoberRecieve() {
		return octoberRecieve;
	}

	public void setOctoberRecieve(Integer octoberRecieve) {
		this.octoberRecieve = octoberRecieve;
	}

	public Integer getOctoberPay() {
		return octoberPay;
	}

	public void setOctoberPay(Integer octoberPay) {
		this.octoberPay = octoberPay;
	}

	public Integer getNovemberRecieve() {
		return novemberRecieve;
	}

	public void setNovemberRecieve(Integer novemberRecieve) {
		this.novemberRecieve = novemberRecieve;
	}

	public Integer getNovemberPay() {
		return novemberPay;
	}

	public void setNovemberPay(Integer novemberPay) {
		this.novemberPay = novemberPay;
	}

	public Integer getDecemberRecieve() {
		return decemberRecieve;
	}

	public void setDecemberRecieve(Integer decemberRecieve) {
		this.decemberRecieve = decemberRecieve;
	}

	public Integer getDecemberPay() {
		return decemberPay;
	}

	public void setDecemberPay(Integer decemberPay) {
		this.decemberPay = decemberPay;
	}

	public Integer getJanuaryRecieve() {
		return januaryRecieve;
	}

	public void setJanuaryRecieve(Integer januaryRecieve) {
		this.januaryRecieve = januaryRecieve;
	}

	public Integer getJanuaryPay() {
		return januaryPay;
	}

	public void setJanuaryPay(Integer januaryPay) {
		this.januaryPay = januaryPay;
	}

	public Integer getFebruaryRecieve() {
		return februaryRecieve;
	}

	public void setFebruaryRecieve(Integer februaryRecieve) {
		this.februaryRecieve = februaryRecieve;
	}

	public Integer getFebruaryPay() {
		return februaryPay;
	}

	public void setFebruaryPay(Integer februaryPay) {
		this.februaryPay = februaryPay;
	}

	public Integer getMarchRecieve() {
		return marchRecieve;
	}

	public void setMarchRecieve(Integer marchRecieve) {
		this.marchRecieve = marchRecieve;
	}

	public Integer getMarchPay() {
		return marchPay;
	}

	public void setMarchPay(Integer marchPay) {
		this.marchPay = marchPay;
	}

	public Integer getAprilRecieve() {
		return aprilRecieve;
	}

	public void setAprilRecieve(Integer aprilRecieve) {
		this.aprilRecieve = aprilRecieve;
	}

	public Integer getAprilPay() {
		return aprilPay;
	}

	public void setAprilPay(Integer aprilPay) {
		this.aprilPay = aprilPay;
	}

	public Integer getMayRecieve() {
		return mayRecieve;
	}

	public void setMayRecieve(Integer mayRecieve) {
		this.mayRecieve = mayRecieve;
	}

	public Integer getMayPay() {
		return mayPay;
	}

	public void setMayPay(Integer mayPay) {
		this.mayPay = mayPay;
	}

	public Integer getJuneRecieve() {
		return juneRecieve;
	}

	public void setJuneRecieve(Integer juneRecieve) {
		this.juneRecieve = juneRecieve;
	}

	public Integer getJunePay() {
		return junePay;
	}

	public void setJunePay(Integer junePay) {
		this.junePay = junePay;
	}

	public Integer getJulyRecieve() {
		return julyRecieve;
	}

	public void setJulyRecieve(Integer julyRecieve) {
		this.julyRecieve = julyRecieve;
	}

	public Integer getJulyPay() {
		return julyPay;
	}

	public void setJulyPay(Integer julyPay) {
		this.julyPay = julyPay;
	}

	public Integer getAugustRecieve() {
		return augustRecieve;
	}

	public void setAugustRecieve(Integer augustRecieve) {
		this.augustRecieve = augustRecieve;
	}

	public Integer getAugustPay() {
		return augustPay;
	}

	public void setAugustPay(Integer augustPay) {
		this.augustPay = augustPay;
	}

	public Integer getSeptemberRecieve() {
		return septemberRecieve;
	}

	public void setSeptemberRecieve(Integer septemberRecieve) {
		this.septemberRecieve = septemberRecieve;
	}

	public Integer getSeptemberPay() {
		return septemberPay;
	}

	public void setSeptemberPay(Integer septemberPay) {
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
