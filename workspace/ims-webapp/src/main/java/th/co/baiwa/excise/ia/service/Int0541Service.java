package th.co.baiwa.excise.ia.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementUlFile;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementListRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaProcurementUlFileRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int0541Service {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.datasource.path.upload}")
	private String pathed;
	
	@Autowired
	private IaProcurementRepository iaPcmRepository;
	
	@Autowired
	private IaProcurementListRepository iaPcmListRepository;
	
	@Autowired
	private IaProcurementUlFileRepository UlFileRepository;
	
	@Autowired
	private Int0541UploadService int0541UploadService;

	public CommonMessage<IaProcurement> saveProcurement(Int0541Vo vo) throws IOException {
		logger.info("saveProcurement Service");
		Message msg;
		CommonMessage<IaProcurement> response = new CommonMessage<>();
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		
		IaProcurement pcm;
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
			pcm.setSignedDatePlan(BeanUtils.isNotEmpty(vo.getSignedDatePlan())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getSignedDatePlan()): null);
			pcm.setSignedDateReport(BeanUtils.isNotEmpty(vo.getSignedDateReport())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getSignedDateReport()): null);
			pcm.setContractPartiesNum(vo.getContractPartiesNum());
			pcm.setContractPartiesName(vo.getContractPartiesName());
			pcm.setUpdatedBy(user);
			pcm.setUpdatedDate(date);
			if(
					BeanUtils.isNotEmpty(pcm.getApproveDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getContractDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getExpireDateReport()) ||
					BeanUtils.isNotEmpty(pcm.getDisbursementFinalReport())||
					BeanUtils.isNotEmpty(pcm.getSignedDateReport())
			){
					pcm.setStatus("Y");
			}else {
					pcm.setStatus("N");
			}
			data = iaPcmRepository.save(pcm);
			
			IaProcurementUlFile upload = new IaProcurementUlFile();
			if (data != null) {
				upload.setUpdatedBy(user);
				upload.setProcurementId(data.getProcurementId());
				upload.setNameFile(vo.getFile().getOriginalFilename());
				upload.setUpdatedDate(date);
				long size = vo.getFile().getSize();
				upload.setSizeFile(size/1000);
				UlFileRepository.save(upload);
				int0541UploadService.saveFileUpload(upload, vo);
				msg = ApplicationCache.getMessage("MSG_00002");
			} else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
				
			response.setMsg(msg);
			response.setData(data);
		}
		return response;
	}

	public void savePcmList(List<Int0541Vo> vo) {
		logger.info("savePcmList Service");
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
		}
	}
}
