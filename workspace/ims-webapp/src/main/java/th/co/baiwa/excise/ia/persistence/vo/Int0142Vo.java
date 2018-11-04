package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class Int0142Vo {

	private String sector;
	private String area;
	private String startdate;
	private String endDate;
	public List<TrialBalanceCalcRow> getDatas() {
		return datas;
	}

	public void setDatas(List<TrialBalanceCalcRow> datas) {
		this.datas = datas;
	}

	private List<TrialBalanceCalcRow> datas;

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
