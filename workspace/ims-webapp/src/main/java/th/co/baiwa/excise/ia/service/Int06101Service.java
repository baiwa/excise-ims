package th.co.baiwa.excise.ia.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ia.persistence.dao.BudgetListDao;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.persistence.repository.BudgetListRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int06101FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int06101Service {
	
	@Autowired
	private BudgetListDao budgetListDao;
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private BudgetListRepository budgetListRepository;
	
	public List<Lov> pmmethod() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("PAYMENT_METHOD");
	}
	
	public List<Lov> activity() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("ACTIVITY");
	}

	public List<Lov> budge() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("BUDGET_TYPE");
	}
	
	public List<Lov> title() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("TITLE");
	}
	
	public List<Lov> bank() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("BANK");
	}
	
//	public List<BudgetList> budged() {
//		// TODO Auto-generated method stub
//		return budgetListRepository.findDistinctByBudgetId("");
//	}
	
	public List<BudgetList> budged(){
		List<BudgetList> data = new ArrayList<BudgetList>();
		data = budgetListDao.quryBudgetName();
		return data;
	}
	
	public List<BudgetList> getCtgBudget(BudgetList en) {
		List<BudgetList> data = new ArrayList<BudgetList>();
		if (BeanUtils.isNotEmpty(en.getBudgetId())) {
			data = budgetListDao.getCtgBudget(en);
		}
		return data;
	}

	public List<BudgetList> getListName(BudgetList en) {
		List<BudgetList> data = new ArrayList<BudgetList>();
		if (BeanUtils.isNotEmpty(en.getCategoryId())) {
			data = budgetListDao.getListName(en);
		}
		return data;
	}

	public void add(Int06101FormVo formVo) {

		budgetListDao.add06101(formVo);
	}

}
