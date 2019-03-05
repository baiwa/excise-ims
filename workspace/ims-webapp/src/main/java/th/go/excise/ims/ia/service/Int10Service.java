package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaInspectionPlanJdbcRepository;

@Service
public class Int10Service {
	@Autowired
	private IaInspectionPlanJdbcRepository iaInspectionPlanJdbcRepository;

	public List<IaInspectionPlan> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaInspectionPlan> response = iaInspectionPlanJdbcRepository.getDataFilter(budgetYear, inspectionWork, status);
		
		return response;
	}
}
