package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesD1JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;

@Service
public class Int090101Service {
	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;
	
	@Autowired IaExpensesD1JdbcRepository iaExpensesD1JdbcRepository;

	public List<Int090101Vo> findCompare(Int090101CompareFormVo form) {

		List<Int090101Vo> data = new ArrayList<Int090101Vo>();
		form.setStartYear((Long.toString(Long.valueOf(form.getStartYear()) - 543)));
		form.setEndYear((Long.toString(Long.valueOf(form.getEndYear()) - 543)));
		if (StringUtils.isNotBlank(form.getYear())) {
			form.setYear((Long.toString(Long.valueOf(form.getYear()) - 543)));
		}
//		if (Long.valueOf(form.getPeriodMonthStart()) <= 3) {
//			form.setYear((Long.toString(Long.valueOf(form.getStartYear()) - 1)));
//		}
//		if (Long.valueOf(form.getPeriodMonthEnd()) <= 3) {
//			form.setYear((Long.toString(Long.valueOf(form.getEndYear()) - 1)));
//		}
//		form.setPeriodMonthStart(this.monthMap(StringUtils.leftPad(form.getPeriodMonthStart(), 2,"0")));
//		form.setPeriodMonthEnd(this.monthMap(StringUtils.leftPad(form.getPeriodMonthEnd(), 2,"0")));
		data = iaExpensesJdbcRepository.findCompare(form);
		
		List<Int090101Vo> dataRes = new ArrayList<>();
		List<IaExpensesD1> detail = null;
		
		for (Int090101Vo headder : data) {
			detail = iaExpensesD1JdbcRepository.findDetailTime(headder.getOfficeCode(), headder.getAccountId(), form.getPeriodMonthStart(), form.getStartYear(), form.getPeriodMonthEnd(), form.getEndYear());
//			detail = iaExpensesD1JdbcRepository.findDetail(headder.getOfficeCode(), headder.getBudgetYear(), headder.getExpenseMonth(), headder.getExpenseYear(), headder.getAccountId());
			int index = 0;
			for (IaExpensesD1 iaExpensesD1 : detail) {
				if(index == 0) {
					headder.setAverageCost(new BigDecimal(iaExpensesD1.getAverageCost()));
					headder.setAverageGive(iaExpensesD1.getAverageGive());
					headder.setAverageFrom(new BigDecimal(iaExpensesD1.getAverageFrom()));
					dataRes.add(headder);
				}else if(index > 0) {
					Int090101Vo	dataBlank = new Int090101Vo();
					dataBlank.setAverageCost(new BigDecimal(iaExpensesD1.getAverageCost()));
					dataBlank.setAverageGive(iaExpensesD1.getAverageGive());
					dataBlank.setAverageFrom(new BigDecimal(iaExpensesD1.getAverageFrom()));
					dataRes.add(dataBlank);
				}
				index++;
			}
		}
		
		return dataRes;
	}

	private String monthMap(String inputStr) {
		String[][] monthMapping = { { "01", "10" }, { "02", "11" }, { "03", "12" }, { "04", "01" }, { "05", "02" },
				{ "06", "03" }, { "07", "04" }, { "08", "05" }, { "09", "06" }, { "10", "07" }, { "11", "08" },
				{ "12", "09" } };
		String outStr = new String();
		for (String[] monthtest : monthMapping) {
			if (monthtest[0].equals(inputStr)) {
				outStr = monthtest[1];
			}
		}
		return outStr;
	}
}
