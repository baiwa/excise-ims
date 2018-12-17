package th.co.baiwa.excise.ta.persistence.vo;
import th.co.baiwa.excise.ta.persistence.entity.PlanCriteria;




public class PlanCriteriaVo extends PlanCriteria{
	

	private static final long serialVersionUID = -3146458738577106397L;
	
	private Long workSheetCount;

	public Long getWorkSheetCount() {
		return workSheetCount;
	}

	public void setWorkSheetCount(Long workSheetCount) {
		this.workSheetCount = workSheetCount;
	}

}