package th.go.excise.ims.mockup.domain;

public class DataTableRequest {

	private Integer start;
	private Integer length;
	private Integer month;
	private Long draw;
	private long startBackDate;
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public long getStartBackDate() {
		return startBackDate;
	}
	public void setStartBackDate(long startBackDate) {
		this.startBackDate = startBackDate;
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
	public Long getDraw() {
		return draw;
	}
	public void setDraw(Long draw) {
		this.draw = draw;
	}
	
}
