package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

public interface TaPlanWorksheetHisRepositoryCustom {

	public Map<String, String> findAuditPlanCodeByOfficeCodeAndBudgetYearList(String officeCode, List<String> budgetYearList);
	public Map<String, String> findMaxTaxAuditYear();
}
