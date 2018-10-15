package th.co.baiwa.excise.ia.persistence.entity.tax;

@SuppressWarnings("serial")
public class IaTaxReceiptVo extends TaxReceipt {

	private String dateFrom;
	
	private String dateTo;
	
	private Long count;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
