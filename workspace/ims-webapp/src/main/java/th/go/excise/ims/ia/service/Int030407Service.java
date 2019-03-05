package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskIncomePerform;
import th.go.excise.ims.ia.persistence.repository.IaRiskIncomePerformRepository;

@Service
public class Int030407Service {
	
	@Autowired
	private IaRiskIncomePerformRepository iaRiskIncomePerformRep;
	
	public List<IaRiskIncomePerform> findByBudgetYear(String budgetYear) {
		return iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
	}
	
//	public List<IaRiskIncomePerform> pullDataWebService(String budgetYear){
//		List<IaRiskIncomePerform> data = iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
//		if (data.size() > 0) {
//			
//		}
//		return iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
//	}
	
}
