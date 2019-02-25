package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetSeqCtrl;

public interface TaWorksheetSeqCtrlRepository extends CommonJpaCrudRepository<TaWorksheetSeqCtrl, Long> {
	
	// TODO
	public TaWorksheetSeqCtrl findByOfficeCodeAndBudgetYearAndRunningType(String officeCode, String budgetYear, String runningType);
	
}
