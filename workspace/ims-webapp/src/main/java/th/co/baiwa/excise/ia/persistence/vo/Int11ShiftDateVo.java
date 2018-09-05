package th.co.baiwa.excise.ia.persistence.vo;

import java.io.Serializable;

public class Int11ShiftDateVo implements Serializable {

	private static final long serialVersionUID = -6109833603823408827L;
	
	private String date;
	private String shift45Date;
	private String shift60Date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getShift45Date() {
		return shift45Date;
	}
	public void setShift45Date(String shift45Date) {
		this.shift45Date = shift45Date;
	}
	public String getShift60Date() {
		return shift60Date;
	}
	public void setShift60Date(String shift60Date) {
		this.shift60Date = shift60Date;
	}
	
}
