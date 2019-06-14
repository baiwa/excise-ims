package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.persistence.repository.IaExpensesRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaChartOfAccJdbcRepository;
import th.go.excise.ims.ia.vo.Int12040101SaveFormVo;

@Service
public class Int12040101Service {

//	@Autowired
//	private IaChartOfAccRepository iaChartOfAccRepository;

	@Autowired
	private IaExpensesRepository iaExpensesRepository;

	@Autowired
	private IaChartOfAccJdbcRepository iaChartOfAccJdbcRepository;

	public List<IaChartOfAcc> findAll() {
		List<IaChartOfAcc> data = new ArrayList<>();
//		data = iaChartOfAccRepository.findAll();
		data = iaChartOfAccJdbcRepository.findAll();
		for (IaChartOfAcc iaChartOfAccData : data) {
			iaChartOfAccData.setCreatedBy(null);
			iaChartOfAccData.setCreatedDate(null);
			iaChartOfAccData.setUpdatedBy(null);
			iaChartOfAccData.setUpdatedDate(null);
			iaChartOfAccData.setVersion(null);
		}
		return data;
	}

	public void saveExpenses(Int12040101SaveFormVo form) {
		IaExpenses data = new IaExpenses();
		if (form.getId() != null) {
			data = iaExpensesRepository.findById(form.getId()).get();
		}
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

		data.setAverageCostOut(form.getAverageCostOut());
		data.setAverageGiveOut(form.getAverageGiveOut());
		data.setAverageFromOut(form.getAverageFromOut());
		data.setAverageComeCostOut(form.getAverageComeCostOut());

		String month = form.getExpenseDateStr().split("/")[0];
		String year = form.getExpenseDateStr().split("/")[1];
		data.setExpenseDate(ConvertDateUtils.parseStringToDate(form.getExpenseDateStr(), ConvertDateUtils.MM_YYYY,
				ConvertDateUtils.LOCAL_TH));
		if (Long.parseLong(month) > 10) {
			year = Long.toString(Long.parseLong(year) + 1);
		}
		data.setExpenseMonth(month);
		data.setExpenseYear(year);
		
		iaExpensesRepository.save(data);
	}

	public IaExpenses findExpensesById(BigDecimal id) {
		IaExpenses dataRes = new IaExpenses();
		dataRes = iaExpensesRepository.findById(id).get();
		return dataRes;
	}
}
