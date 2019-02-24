
package th.go.excise.ims.preferences.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.TaWorksheetSeqCtrl;

public interface TaWorksheetSeqCtrlRepository extends CommonJpaCrudRepository<TaWorksheetSeqCtrl, Long> {

	public TaWorksheetSeqCtrl findByOfficeCodeAndBudgetYearAndRunningType(String officeCode , String budgetYear , String runningType);
	
}
