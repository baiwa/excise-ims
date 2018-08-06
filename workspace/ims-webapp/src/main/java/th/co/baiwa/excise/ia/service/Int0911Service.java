package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.BudgetDao;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;

@Service
public class Int0911Service {	
	
	@Autowired
	private BudgetDao budgetDao; 
	
	public DataTableAjax<Budget> findAll(Int0911FormVo formVo){			
		
		List<Budget> list = budgetDao.findAll(formVo);
		Long count = budgetDao.count(formVo);
		
		DataTableAjax<Budget> dataTableAjax = new DataTableAjax<>();
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
		
		
		return dataTableAjax;		
	}

}
