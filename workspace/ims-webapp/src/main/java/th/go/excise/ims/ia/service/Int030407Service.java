package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskIncomePerform;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskIncomePerformRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int030407Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030407Service {

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRep;

	@Autowired
	private IaRiskIncomePerformRepository iaRiskIncomePerformRep;

	public List<Int030407Vo> findByBudgetYear(String budgetYear, String idConfigStr) {
		BigDecimal idConfig = new BigDecimal(idConfigStr);
		List<IaRiskIncomePerform> incomes = iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
		Optional<IaRiskFactorsConfig> config = iaRiskFactorsConfigRep.findById(idConfig);
		List<Int030407Vo> lists = new ArrayList<>();
		Int030407Vo list;
		for(IaRiskIncomePerform income : incomes) {
			BigDecimal diffAmount = income.getSumAmount().subtract(income.getForecaseAmount());
			BigDecimal rateAmount = new BigDecimal((diffAmount.doubleValue()*100)/income.getForecaseAmount().doubleValue());
			list = new Int030407Vo();
			list.setArea(income.getArea());
			list.setBudgetYear(income.getBudgetYear());
			list.setDiffAmount(diffAmount);
			list.setForecaseAmount(income.getForecaseAmount());
			list.setId(income.getId());
			list.setIdFactors(income.getIdFactors());
			list.setOfficeCode(income.getOfficeCode());
			list.setRateAmount(rateAmount);
			list.setSector(income.getSector());
			list.setSumAmount(income.getSumAmount());
			// CALCULATE
			if (config.isPresent()) {
				IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(rateAmount, config.get());
				list.setColorRisk(risk.getColor());
				list.setRateRisk(risk.getRiskRate());
				list.setTextRisk(risk.getTranslatingRisk());
			}
			lists.add(list);
		}
		return lists;
	}

}
