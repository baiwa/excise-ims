package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.BudgetDao;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int09211FormVo;

@Service
public class Int09211Service {	
	
	@Autowired
	private BudgetDao budgetDao; 
	
	public DataTableAjax<Budget> findAll(Int09211FormVo formVo){			
		
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
	
	//department dropdown
	public List<LabelValueBean> departmentDropdown(){
		return budgetDao.departmentDropdown();
	}

}
