package th.co.baiwa.excise.ia.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.BudgetListDao;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalList;
import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalPersons;
import th.co.baiwa.excise.ia.persistence.repository.BudgetListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaWithdrawalPersonsRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int06101FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int06101Service {
	
	@Autowired
	private BudgetListDao budgetListDao;
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private IaWithdrawalPersonsRepository personsRep;
	
	@Autowired
	private IaWithdrawalListRepository listRep;
	
	@Autowired
	private BudgetListRepository budgetListRepository;
	
	public List<Lov> pmmethod() {
		// TODO Auto-generated method stub
		return lovRepository.findByType("PAYMENT_METHOD");
	}
	
	public List<Lov> pmmethodPersonType() {
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
	
	public List<Lov> persontitle() {
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
	
	public IaWithdrawalList findById(Long id) {
		return listRep.findOne(id);
	}
	
	public List<IaWithdrawalPersons> findByListId(Long id) {
		return personsRep.findByWithDrawalId(id);
	}

	public List<BudgetList> getListName(BudgetList en) {
		List<BudgetList> data = new ArrayList<BudgetList>();
		if (BeanUtils.isNotEmpty(en.getCategoryId())) {
			data = budgetListDao.getListName(en);
		}
		return data;
	}
	
	private BigDecimal stringToDecimal(String str) {
		return BigDecimal.valueOf(Double.parseDouble(str));
	}

	public void add(Int06101FormVo formVo) {

		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
			String officeDesc = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", officeCode).get(0).getTypeDescription();
			IaWithdrawalList data = new IaWithdrawalList();
			data.setOfficeCode(officeCode);
			data.setOFFICEDesc(officeDesc);
			data.setWithdrawalDate(DateConstant.convertStrDDMMYYYYToDate(formVo.getWithdrawal()));
			data.setActivities(formVo.getActivity());
			data.setBudgetName(formVo.getBudgetName());
			data.setRefNum(formVo.getRefnum());
			data.setBudgetId(formVo.getBudged());
			data.setBudgetType(formVo.getBudget());
			data.setCategoryId(formVo.getCategory());
			data.setWithdrawalAmount(stringToDecimal(formVo.getAmountOfMoney()));
			data.setSocialSecurity(stringToDecimal(formVo.getDeductSocial()));
			data.setWithholdingTax(stringToDecimal(formVo.getWithholding()));
			data.setAnotherAmount(stringToDecimal(formVo.getOther()));
			data.setReceivedAmount(stringToDecimal(formVo.getAmountOfMoney1()));
			data.setWithdrawalDocNum(formVo.getNumberRequested());
			data.setPaymentDocNum(formVo.getDocumentNumber());
			data.setItemDesc(formVo.getItemDescription());
			data.setNote(formVo.getNote());
			data.setListId(formVo.getList());
			data.setPayee(formVo.getPayee());
			data.setPersonType(formVo.getPersonType());
			data.setListName(formVo.getListName());
			data.setCategoryName(formVo.getCategoryName());
			Long id = listRep.save(data).getWithdrawalId();
			if (formVo.getPersons().size() > 0) {
				for(IaWithdrawalPersons vo : formVo.getPersons()) {
					vo.setWithdrawalId(id);
					vo.setOfficeCode(officeCode);
					vo.setOFFICEDesc(officeDesc);
				}
				personsRep.save(formVo.getPersons());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void update(Int06101FormVo formVo, Long id) {

		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
			String officeDesc = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", officeCode).get(0).getTypeDescription();
			IaWithdrawalList data = listRep.findOne(id);
			data.setOfficeCode(officeCode);
			data.setOFFICEDesc(officeDesc);
			data.setWithdrawalDate(DateConstant.convertStrDDMMYYYYToDate(formVo.getWithdrawal()));
			data.setActivities(formVo.getActivity());
			data.setBudgetName(formVo.getBudgetName());
			data.setRefNum(formVo.getRefnum());
			data.setBudgetId(formVo.getBudged());
			data.setBudgetType(formVo.getBudget());
			data.setCategoryId(formVo.getCategory());
			data.setWithdrawalAmount(stringToDecimal(formVo.getAmountOfMoney()));
			data.setSocialSecurity(stringToDecimal(formVo.getDeductSocial()));
			data.setWithholdingTax(stringToDecimal(formVo.getWithholding()));
			data.setAnotherAmount(stringToDecimal(formVo.getOther()));
			data.setReceivedAmount(stringToDecimal(formVo.getAmountOfMoney1()));
			data.setWithdrawalDocNum(formVo.getNumberRequested());
			data.setPaymentDocNum(formVo.getDocumentNumber());
			data.setItemDesc(formVo.getItemDescription());
			data.setNote(formVo.getNote());
			data.setListId(formVo.getList());
			data.setPayee(formVo.getPayee());
			data.setPersonType(formVo.getPersonType());
			data.setListName(formVo.getListName());
			data.setCategoryName(formVo.getCategoryName());
			if (formVo.getPersons().size() > 0) {
//				List<IaWithdrawalPersons> persons = personsRep.findByWithDrawalId(id);
//				for(IaWithdrawalPersons vo : formVo.getPersons()) {
//					if (BeanUtils.isEmpty(vo.getWithdrawalPersonsId())) {
//						personsRep.save(vo);
//					} else {
//						personsRep.delete();
//					}
//				}
//				personsRep.save(formVo.getPersons());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
