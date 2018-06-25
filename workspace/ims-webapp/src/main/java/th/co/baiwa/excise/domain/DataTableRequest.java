package th.co.baiwa.excise.domain;

public class DataTableRequest {

	private Integer start;
	private Integer length;
	private Integer month;
	private Long draw;
	private String startBackDate;
	private String exciseProductType;
	
	
	public String getExciseProductType() {
		return exciseProductType;
	}
	public void setExciseProductType(String exciseProductType) {
		this.exciseProductType = exciseProductType;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}
	public String getStartBackDate() {
		return startBackDate;
	}
	public void setStartBackDate(String startBackDate) {
		this.startBackDate = startBackDate;
	}
	
	
	
}
