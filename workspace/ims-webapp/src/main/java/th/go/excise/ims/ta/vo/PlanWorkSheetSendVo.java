package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;

public class PlanWorkSheetSendVo {

	private ExciseDept sector;
	private ExciseDept area;
	private TaPlanWorksheetSend planWorksheetSend;
	private Integer totalFacNum;

	public ExciseDept getSector() {
		return sector;
	}

	public void setSector(ExciseDept sector) {
		this.sector = sector;
	}

	public ExciseDept getArea() {
		return area;
	}

	public void setArea(ExciseDept area) {
		this.area = area;
	}

	public TaPlanWorksheetSend getPlanWorksheetSend() {
		return planWorksheetSend;
	}

	public void setPlanWorksheetSend(TaPlanWorksheetSend planWorksheetSend) {
		this.planWorksheetSend = planWorksheetSend;
	}

	public Integer getTotalFacNum() {
		return totalFacNum;
	}

	public void setTotalFacNum(Integer totalFacNum) {
		this.totalFacNum = totalFacNum;
	}

}
