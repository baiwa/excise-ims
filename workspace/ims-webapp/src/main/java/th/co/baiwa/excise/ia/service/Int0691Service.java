package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.BudgetListDao;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;
import th.co.baiwa.excise.ia.persistence.repository.BudgetListRepository;
import th.co.baiwa.excise.ia.persistence.repository.TransferListRepository;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int0691Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TransferListRepository transferListRepository;

	@Autowired
	private BudgetListRepository budgetListRepository;

	@Autowired
	private BudgetListDao budgetListDao;

	@Autowired
	private LovRepository lovRepository;

	public List<BudgetList> queryBudgetList() {
		List<BudgetList> data = new ArrayList<BudgetList>();
		data = budgetListRepository.findAll();
		return data;
	}

	public void saveTable(List<TransferList> entity) {
		String officeId = UserLoginUtils.getCurrentUserBean().getOfficeId();
		Lov lov = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", officeId).get(0);
		for (TransferList t : entity) {
			t.setRefDate(DateConstant.convertStrDDMMYYYYToDate(t.getRefDateStr()));
			t.setOfficeCode(officeId);
			t.setOfficeDesc(lov.getSubTypeDescription());
			transferListRepository.save(t);
		}

	}

	public TransferList queryByIdToEdit(TransferList en) {
		TransferList data = new TransferList();
		data = transferListRepository.findOne(en.getTransferId());
		data.setRefDateStr(DateConstant.convertDateToStrDDMMYYYY(data.getRefDate()));
		return data;
	}

	public void update(TransferList entity) {
		TransferList data = new TransferList();
		String officeId = UserLoginUtils.getCurrentUserBean().getOfficeId();
		Lov lov = lovRepository.findBySubType(officeId);
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();

		data = transferListRepository.findOne(entity.getTransferId());
		data.setTransferId(entity.getTransferId());
		data.setActivities(entity.getActivities());
		data.setAmount(entity.getAmount());
		data.setBudget(entity.getBudget());
		data.setBudgetCode(entity.getBudgetCode());
		data.setBudgetType(entity.getBudgetType());
		data.setCtgBudget(entity.getCtgBudget());
		data.setSubCtgBudget(entity.getSubCtgBudget());
		data.setDescriptionList(entity.getDescriptionList());
		data.setMofNum(entity.getMofNum());
		data.setNote(entity.getNote());
		data.setRefDate(DateConstant.convertStrDDMMYYYYToDate(entity.getRefDateStr()));
		data.setRefNum(entity.getRefNum());
		data.setTransferList(entity.getTransferList());
		data.setUpdatedBy(user);
		data.setUpdatedDate(date);
		data.setOfficeCode(officeId);
		data.setOfficeDesc(lov.getSubTypeDescription());

		transferListRepository.save(data);
	}

	public List<BudgetList> quryBudgetName() {
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

}
