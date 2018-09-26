package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.AllocatedBudget;
import th.co.baiwa.excise.ia.persistence.entity.PublicUtility;
import th.co.baiwa.excise.ia.persistence.repository.AllocatedBudgetRepository;
import th.co.baiwa.excise.ia.persistence.repository.PublicUtilityRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int068FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int068Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AllocatedBudgetRepository allocatedBudgetRepository;
	
	@Autowired
	private PublicUtilityRepository publicUtilityRepository;
	
	public CommonMessage<AllocatedBudget> saveAB(AllocatedBudget ab) {
		Message msg;
		CommonMessage<AllocatedBudget> response = new CommonMessage<>();
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		AllocatedBudget data = new AllocatedBudget();
		
		if(BeanUtils.isNotEmpty(ab.getAllocatedBudget())) {
			ab.setUpdatedBy(user);
			ab.setUpdatedDate(date);
			data = allocatedBudgetRepository.save(ab);
		}
		
		if (data != null) {
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
			
		response.setMsg(msg);
		response.setData(data);
		
	return response;
	}

	public CommonMessage<PublicUtility> savePU(List<Int068FormVo> listVo) {
		Message msg;
		CommonMessage<PublicUtility> response = new CommonMessage<>();
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		PublicUtility data = new PublicUtility();
		PublicUtility pu;
		
		if(listVo.size() != 0) {
			for (Int068FormVo obj : listVo) {
				pu = new PublicUtility();
				pu.setAllocatedBudgetId(new BigDecimal(obj.getAllocatedBudgetId()));
				pu.setPublicUtilityType(obj.getPublicUtilityType());
				pu.setMonthInvoice(obj.getMonthInvoice());
				pu.setInvoiceNumber(obj.getInvoiceNumber());
				pu.setInvoiceDate(DateConstant.convertStrDDMMYYYYToDate(obj.getInvoiceDate()));
				pu.setWithdrawalNumber(obj.getWithdrawalNumber());
				pu.setWithdrawalDate(DateConstant.convertStrDDMMYYYYToDate(obj.getWithdrawalDate()));
				pu.setAmount(new BigDecimal(obj.getAmount()));
				pu.setUpdatedBy(user);
				pu.setUpdatedDate(date);
				pu.setExciseDepartment("สาขาอยุธยา 1");
				pu.setExciseDistrict("สำนักงานสรรพสามิตภาคที่ 1");
				pu.setExciseRegion("สาขาอยุธยา 1");
				
				data = publicUtilityRepository.save(pu);
			}
		}
		
//		Int068FormVo resData = new Int068FormVo();
		if (data != null) {
//			resData.setAllocatedBudgetId((data.getAllocatedBudgetId()).longValue());
//			resData.setPublicUtilityType(data.getPublicUtilityType());
//			resData.setMonthInvoice(data.getMonthInvoice());
//			resData.setInvoiceNumber(data.getInvoiceNumber());
//			resData.setInvoiceDate(DateConstant.convertDateToStrDDMMYYYY(data.getInvoiceDate()));
//			resData.setWithdrawalNumber(data.getWithdrawalNumber());
//			resData.setWithdrawalDate(DateConstant.convertDateToStrDDMMYYYY(data.getWithdrawalDate()));
//			resData.setAmount(data.getAmount().longValue());
			
			msg = ApplicationCache.getMessage("MSG_00002");
		} else {
			msg = ApplicationCache.getMessage("MSG_00003");
		}
			
		response.setMsg(msg);
		response.setData(data);
		
	return response;
	}

}
