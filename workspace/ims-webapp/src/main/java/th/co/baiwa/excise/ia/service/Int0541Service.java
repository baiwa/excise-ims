package th.co.baiwa.excise.ia.service;

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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
			if(BeanUtils.isNotEmpty(vo.getProcurementId())) {
				pcm = iaPcmRepository.findOne(vo.getProcurementId());
			}
			pcm.setExciseDepartment("สำนักงานสรรพสามิตภาคที่ 1");
			pcm.setExciseRegion("สาขาอยุธยา 1");
			pcm.setExciseDistrict("สำนักงานสรรพสามิตพื้นที่อยุธยา");
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
			pcm.setRespondepartment(vo.getRespondepartment());
			pcm.setBudget(vo.getBudget());
			pcm.setInstallmentjob(vo.getInstallmentjob());
			pcm.setOperationstart(BeanUtils.isNotEmpty(vo.getOperationstart())? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getOperationstart()): null);
			pcm.setOperationend(BeanUtils.isNotEmpty(vo.getOperationend()) ? DateConstant.convertStringDDMMYYYYHHmmToDate(vo.getOperationend()): null);
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
				if(vo.getFile().getSize() > 0) {
					upload.setUpdatedBy(user);
					upload.setProcurementId(data.getProcurementId());
					upload.setNameFile(vo.getFile().getOriginalFilename());
					upload.setUpdatedDate(date);
					long size = vo.getFile().getSize();
					upload.setSizeFile(size/1000);
					UlFileRepository.save(upload);
					int0541UploadService.saveFileUpload(upload, vo);
				}
				
				msg = ApplicationCache.getMessage("MSG_00002");
			} else {
				msg = ApplicationCache.getMessage("MSG_00003");
			}
				
			response.setMsg(msg);
			response.setData(data);
		}
		return response;
	}

	public void savePcmList(List<IaProcurementList> vo) {
		logger.info("savePcmList Service");
		String user = UserLoginUtils.getCurrentUsername();
		Date date = new Date();
		
		if(BeanUtils.isNotEmpty(vo)) {
			IaProcurementList pcmList;
			boolean del = true;
			/* save */
			for (IaProcurementList p : vo) {
				boolean flag = true;
				
				if(p.getIds().size() > 0 && del) {
					/* delete */
					iaPcmListRepository.delete(p.getIds());
					del = false;
				}
				pcmList = new IaProcurementList();
				/* update */
				if( BeanUtils.isNotEmpty(p.getProcurementId()) && (BeanUtils.isNotEmpty(p.getProcurementListId())) ) {
					flag = false;
					pcmList = iaPcmListRepository.findOne(p.getProcurementListId());
						pcmList.setProcurementId(p.getProcurementId());
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
//					}
				}
				
				/* save */
				if(BeanUtils.isNotEmpty(p.getProcurementId()) && flag) {
					pcmList.setProcurementId(p.getProcurementId());
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

	public Int0541Vo findByid(IaProcurement pcm) {
		Int0541Vo data = new Int0541Vo();
		if(BeanUtils.isNotEmpty(pcm.getProcurementId())) {
			IaProcurement dataPcm = iaPcmRepository.findOne(pcm.getProcurementId());
			data.setProcurementId(dataPcm.getProcurementId());
			data.setExciseDepartment(dataPcm.getExciseDepartment());
			data.setExciseRegion(dataPcm.getExciseRegion());
			data.setExciseDistrict(dataPcm.getExciseDistrict());
			data.setBudgetYear(dataPcm.getBudgetYear());
			data.setBudgetType(dataPcm.getBudgetType());
			data.setProjectName(dataPcm.getProjectName());
			data.setProjectCodeEgp(dataPcm.getProjectCodeEgp());
			data.setPoNumber(dataPcm.getPoNumber());
			data.setSupplyChoice(dataPcm.getSupplyChoice());
			data.setTenderResults((dataPcm.getTenderResults()).longValue());
			data.setJobDescription(dataPcm.getJobDescription());
			data.setSupplyType(dataPcm.getSupplyType());
			data.setApproveDatePlan(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getApproveDatePlan()));
			data.setContractDatePlan(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getContractDatePlan()));
			data.setExpireDatePlan(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getExpireDatePlan()));
			data.setDisbursementFinalPlan(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getDisbursementFinalPlan()));
			data.setApproveDateReport(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getApproveDateReport()));
			data.setContractDateReport(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getContractDateReport()));
			data.setExpireDateReport(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getExpireDateReport()));
			data.setDisbursementFinalReport(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getDisbursementFinalReport()));
			data.setSignedDatePlan(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getSignedDatePlan()));
			data.setSignedDateReport(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getSignedDateReport()));
			data.setContractPartiesNum(dataPcm.getContractPartiesNum());
			data.setContractPartiesName(dataPcm.getContractPartiesName());
			data.setRespondepartment(dataPcm.getRespondepartment());
			data.setBudget(dataPcm.getBudget());
			data.setInstallmentjob(dataPcm.getInstallmentjob());
			data.setOperationstart(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getOperationstart()));
			data.setOperationend(DateConstant.convertDateToStrDDMMYYYYHHmm(dataPcm.getOperationend()));
			
			data.setPcmList(iaPcmListRepository.findByIdFilter(pcm.getProcurementId()));
		}
		
		return data;
	}
	
}
