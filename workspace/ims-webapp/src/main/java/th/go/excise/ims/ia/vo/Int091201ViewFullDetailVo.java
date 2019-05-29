package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int091201ViewFullDetailVo {

	private List<Int091201DayDetailVo> dayList;
	private List<Int091201ViewFullDetailVo> lineData;
	
	public List<Int091201DayDetailVo> getDayList() {
		return dayList;
	}
	public void setDayList(List<Int091201DayDetailVo> dayList) {
		this.dayList = dayList;
	}
	public List<Int091201ViewFullDetailVo> getLineData() {
		return lineData;
	}
	public void setLineData(List<Int091201ViewFullDetailVo> lineData) {
		this.lineData = lineData;
	}
	
	
}
