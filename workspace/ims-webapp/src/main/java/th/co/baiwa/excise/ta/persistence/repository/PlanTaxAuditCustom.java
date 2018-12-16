
package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;

public interface PlanTaxAuditCustom {
	
	public List<PlanTaxAudit> findPlanTaxAuditByCriteriaForDataTable(PlanTaxAudit planTaxAudit , int start , int length);
	public long countPlanTaxAuditByCriteriaForDataTable(PlanTaxAudit planTaxAudit);
}
