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
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int0541Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IaProcurementRepository iaPcmRepository;
	
	@Autowired
	private IaProcurementListRepository iaPcmListRepository;

	public CommonMessage<IaProcurement> saveProcurement(Int0541Vo vo) {
		Message msg;
		CommonMessage<IaProcurement> response = new CommonMessage<>();
//		msg = ApplicationCache.getMessage("MSG_00003");
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		
		IaProcurement pcm;
		IaProcurementList pcmList;
		IaProcurement data;
		if(BeanUtils.isNotEmpty(vo)) {
			pcm = new IaProcurement();
			pcm.setExciseDepartment("สำนักงานสรรพสามิตภาคที่ 1");
			pcm.setExciseRegion("ภาคที่ 3");
			pcm.setExciseDistrict("พื้นที่นนทบุรี");
			pcm.setBudgetYear(vo.getBudgetYear());
			pcm.setBudgetType(vo.getBudgetType());
			pcm.setProjectName(vo.getProjectName());
			pcm.setProjectCodeEgp(vo.getProjectCodeEgp());
			pcm.setPoNumber(vo.getPoNumber());
			pcm.setSupplyChoice(vo.getSupplyChoice());
			pcm.setTenderResults(new BigDecimal(vo.getTenderResults()));
			pcm.setJobDescription(vo.getJobDescription());
			pcm.setSupplyType(vo.getSupplyType());
			pcm.setApproveDatePlan(BeanUtils.isNotEmpty(vo.getApproveDatePlan())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getApproveDatePlan()): null);
			pcm.setContractDatePlan(BeanUtils.isNotEmpty(vo.getApproveDatePlan())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getContractDatePlan()): null);
			pcm.setExpireDatePlan(BeanUtils.isNotEmpty(vo.getExpireDatePlan())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getExpireDatePlan()): null);
			pcm.setDisbursementFinalPlan(BeanUtils.isNotEmpty(vo.getDisbursementFinalPlan())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getDisbursementFinalPlan()): null);
			pcm.setApproveDateReport(BeanUtils.isNotEmpty(vo.getApproveDateReport())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getApproveDateReport()): null);
			pcm.setContractDateReport(BeanUtils.isNotEmpty(vo.getContractDateReport())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getContractDateReport()): null);
			pcm.setExpireDateReport(BeanUtils.isNotEmpty(vo.getExpireDateReport())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getExpireDateReport()): null);
			pcm.setDisbursementFinalReport(BeanUtils.isNotEmpty(vo.getDisbursementFinalReport())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getDisbursementFinalReport()): null);
			pcm.setContractPartiesNum(vo.getContractPartiesNum());
			pcm.setContractPartiesName(vo.getContractPartiesName());
			pcm.setUpdatedBy(user);
			pcm.setUpdatedDate(date);
			if(
					BeanUtils.isNotEmpty(pcm.getApproveDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getContractDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getExpireDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getDisbursementFinalReport())
			){
					pcm.setStatus("Y");
			}else {
					pcm.setStatus("N");
			}
			data = iaPcmRepository.save(pcm);
			
			if (data != null) {
				msg = ApplicationCache.getMessage("MSG_00002");
			} else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
				
			response.setMsg(msg);
			response.setData(data);
		}
			
	
			//check update status
//			if(
//					BeanUtils.isNotEmpty(pcm.get(0).getApproveDateReport()) ||
//					BeanUtils.isNotEmpty(pcm.get(0).getContractDateReport()) ||
//					BeanUtils.isNotEmpty(pcm.get(0).getExpireDateReport()) ||
//					BeanUtils.isNotEmpty(pcm.get(0).getDisbursementFinalReport())
//			) {
//				p = new IaProcurement();
//				p = iaPcmRepository.findOne(pcmData.getProcurementId());
//				p.setStatus("Y");
//				iaPcmRepository.save(p);
//			}
		
		
		return response;
	}

	public Message savePcmList(List<Int0541Vo> vo) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		
		if(BeanUtils.isNotEmpty(vo)) {
			IaProcurementList pcmList;
			for (Int0541Vo p : vo) {
				pcmList = new IaProcurementList();
				pcmList.setProcurementId(new BigDecimal(p.getProcurementId()));
				pcmList.setProcurementList(p.getProcurementList());
				pcmList.setAmount(p.getAmount());
				pcmList.setUnit(p.getUnit());
				pcmList.setPresetPrice(p.getPresetPrice());
				pcmList.setAppraisalPrice(p.getAppraisalPrice());
				pcmList.setUnitPrice(p.getUnitPrice());
				pcmList.setPrice(p.getPrice());
				pcmList.setUpdatedBy(user);
				pcmList.setUpdatedDate(date);
				iaPcmListRepository.save(pcmList);
			}
			msg = ApplicationCache.getMessage("MSG_00002");
		}
		return msg;
	}

}
