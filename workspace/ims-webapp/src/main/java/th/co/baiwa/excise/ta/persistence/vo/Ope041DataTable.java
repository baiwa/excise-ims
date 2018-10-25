package th.co.baiwa.excise.ta.persistence.vo;

public class Ope041DataTable extends Ope041Vo {
	private Long no;
	private String product;
	private Long taxInvoice;
	private Long dayRecieve;
	private Long monthRecieve;
	private Long exd1;
	private Long calMax;
	private Long diff;
	private String columnName;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Long getTaxInvoice() {
		return taxInvoice;
	}
	public void setTaxInvoice(Long taxInvoice) {
		this.taxInvoice = taxInvoice;
	}
	public Long getDayRecieve() {
		return dayRecieve;
	}
	public void setDayRecieve(Long dayRecieve) {
		this.dayRecieve = dayRecieve;
	}
	public Long getMonthRecieve() {
		return monthRecieve;
	}
	public void setMonthRecieve(Long monthRecieve) {
		this.monthRecieve = monthRecieve;
	}
	public Long getExd1() {
		return exd1;
	}
	public void setExd1(Long exd1) {
		this.exd1 = exd1;
	}
	public Long getCalMax() {
		return calMax;
	}
	public void setCalMax(Long calMax) {
		this.calMax = calMax;
	}
	public Long getDiff() {
		return diff;
	}
	public void setDiff(Long diff) {
		this.diff = diff;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	@Override
	public String toString() {
		return "Ope041DataTable [no=" + no + ", product=" + product + ", taxInvoice=" + taxInvoice + ", dayRecieve=" + dayRecieve + ", monthRecieve=" + monthRecieve + ", exd1=" + exd1 + ", calMax=" + calMax + ", diff=" + diff + ", columnName=" + columnName + ", getNo()=" + getNo()
				+ ", getProduct()=" + getProduct() + ", getTaxInvoice()=" + getTaxInvoice() + ", getDayRecieve()=" + getDayRecieve() + ", getMonthRecieve()=" + getMonthRecieve() + ", getExd1()=" + getExd1() + ", getCalMax()=" + getCalMax() + ", getDiff()=" + getDiff() + ", getColumnName()="
				+ getColumnName() + ", getId()=" + getId() + ", getExciseId()=" + getExciseId() + ", getStartDate()=" + getStartDate() + ", getEndDate()=" + getEndDate() + ", getType()=" + getType() + ", getAccMonth()=" + getAccMonth() + ", getProduct1()=" + getProduct1() + ", getProduct2()="
				+ getProduct2() + ", getProduct3()=" + getProduct3() + ", getProduct4()=" + getProduct4() + ", getProduct5()=" + getProduct5() + ", getProduct6()=" + getProduct6() + ", getMonthRecieve1()=" + getMonthRecieve1() + ", getMonthRecieve2()=" + getMonthRecieve2() + ", getMonthRecieve3()="
				+ getMonthRecieve3() + ", getMonthRecieve4()=" + getMonthRecieve4() + ", getMonthRecieve5()=" + getMonthRecieve5() + ", getMonthRecieve6()=" + getMonthRecieve6() + ", getDate()=" + getDate() + ", getAnalysNumber()=" + getAnalysNumber() + ", getFileExel()=" + getFileExel()
				+ ", getColumn1()=" + getColumn1() + ", getColumn2()=" + getColumn2() + ", getColumn3()=" + getColumn3() + ", getColumn4()=" + getColumn4() + ", getColumn5()=" + getColumn5() + ", getColumn6()=" + getColumn6() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}