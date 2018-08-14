package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.ctc.wstx.util.StringUtil;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.BudgetDao;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int09211FormVo;

@Service
public class IaTravelCostWorkSheetHeaderService {	
	
	@Autowired
	private BudgetDao budgetDao; 
	
	public DataTableAjax<Budget> findAll(Int09211FormVo formVo){			
		
		///Year - 543
		if (StringUtils.isNotBlank(formVo.getYear())) {
			int year = NumberUtils.toInt(formVo.getYear());
			formVo.setYear(Integer.toString(year-543));
		}
				
		//query data
		List<Budget> list = budgetDao.findAll(formVo);
		Long count = budgetDao.count(formVo);
		
		//set data table
		DataTableAjax<Budget> dataTableAjax = new DataTableAjax<>();
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			dataTableAjax.setDraw(formVo.getDraw() + 1);
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
