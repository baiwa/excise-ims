package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;
import th.go.excise.ims.ia.persistence.repository.IaExpensesRepository;

@Service
public class Int12040101Service {

	@Autowired
	private IaChartOfAccRepository iaChartOfAccRepository;
	
	@Autowired
	private IaExpensesRepository iaExpensesRepository;

	public List<IaChartOfAcc> findAll() {
		List<IaChartOfAcc> data = new ArrayList<>();
		data = iaChartOfAccRepository.findAll();
		for (IaChartOfAcc iaChartOfAccData : data) {
			iaChartOfAccData.setCreatedBy(null);
			iaChartOfAccData.setCreatedDate(null);
			iaChartOfAccData.setUpdatedBy(null);
			iaChartOfAccData.setUpdatedDate(null);
			iaChartOfAccData.setVersion(null);
		}
		return data;
	}
	
	public void saveExpenses(IaExpenses form) {
		IaExpenses data = new IaExpenses();
		data.setAccountId(form.getAccountId());
		data.setAccountName(form.getAccountName());
		data.setServiceReceive(form.getServiceReceive());
		data.setServiceWithdraw(form.getServiceWithdraw());
		data.setServiceBalance(form.getServiceBalance());
		data.setSuppressReceive(form.getSuppressReceive());
		data.setSuppressWithdraw(form.getSuppressWithdraw());
		data.setSuppressBalance(form.getSuppressBalance());
		data.setBudgetReceive(form.getBudgetReceive());
		data.setBudgetWithdraw(form.getBudgetWithdraw());
		data.setBudgetBalance(form.getBudgetBalance());
		data.setSumReceive(form.getSumReceive());
		data.setSumWithdraw(form.getSumWithdraw());
		data.setSumBalance(form.getSumBalance());
		data.setMoneyBudget(form.getMoneyBudget());
		data.setMoneyOut(form.getMoneyOut());
		data.setAverageCost(form.getAverageCost());
		data.setAverageGive(form.getAverageGive());
		data.setAverageFrom(form.getAverageFrom());
		data.setAverageComeCost(form.getAverageComeCost());
		data.setNote(form.getNote());
		data.setOfficeCode(form.getOfficeCode());
		data.setOfficeDesc(form.getOfficeDesc());
		iaExpensesRepository.save(data);
	}
}
