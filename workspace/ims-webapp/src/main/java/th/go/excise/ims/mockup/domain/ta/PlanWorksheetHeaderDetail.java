package th.go.excise.ims.mockup.domain.ta;

import java.util.List;

import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetDetail;
import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetHeader;

public class PlanWorksheetHeaderDetail extends PlanWorksheetHeader{
	private List<PlanWorksheetDetail> planDetailList;

	public List<PlanWorksheetDetail> getPlanDetailList() {
		return planDetailList;
	}

	public void setPlanDetailList(List<PlanWorksheetDetail> planDetailList) {
		this.planDetailList = planDetailList;
	}
	
	
}
