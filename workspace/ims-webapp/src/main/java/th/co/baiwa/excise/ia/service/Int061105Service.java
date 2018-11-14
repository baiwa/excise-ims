package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int061105Dao;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.repository.DisbursementRequestRepository;
import th.co.baiwa.excise.ia.persistence.repository.HealthCareWelFareRepository;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRepository;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int061105Service {

	@Autowired
	private DisbursementRequestRepository DRRepository;

	@Autowired
	private Int061105Dao int061105Dao;
	
	@Autowired
	private RentHouseRepository rentHouseRepository;
	
	@Autowired
	private TuitionFeeRepository tuitionFeeRepository;
	
	@Autowired
	private HealthCareWelFareRepository healthCareWelFareRepository;

	public DataTableAjax<DisbursementRequest> search(Int061105FormSearchVo ids) {
		List<DisbursementRequest> dataList = new ArrayList<DisbursementRequest>();

		if ("2058".equals(ids.getWithdrawRequest())) {
			// แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)
			List<RentHouse> result = int061105Dao.findInRentHouse(ids);
			DisbursementRequest d;
			for (RentHouse r : result) {
				d = new DisbursementRequest();
				d.setId(r.getRentHouseId());
				d.setCreatedBy(r.getCreatedBy());
				d.setPosition(r.getPosition());
				d.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(r.getCreatedDate()));
				d.setAmount(r.getTotalWithdraw());
				d.setStatus(r.getStatus());
				dataList.add(d);
			}

		} else if ("2059".equals(ids.getWithdrawRequest())) {
			// ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)
			List<HealthCareWelFareEntity> result = int061105Dao.findInMedicalWelfare(ids);
			DisbursementRequest d;
			for (HealthCareWelFareEntity vo : result) {
				d = new DisbursementRequest();
				d.setId(vo.getId());
				d.setCreatedBy(vo.getCreatedBy());
				d.setPosition(vo.getPosition());
				d.setAffiliation(vo.getAffiliation());
				d.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(vo.getCreatedDate()));
				d.setAmount(BeanUtils.isNotEmpty(vo.getTotalMoney()) ?new BigDecimal(vo.getTotalMoney()) : BigDecimal.ZERO);
				d.setStatus(vo.getStatusCheck());
				dataList.add(d);
			}

		} else if ("2060".equals(ids.getWithdrawRequest())) {
			// ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
			List<TuitionFee> result = int061105Dao.findInTuitionFee(ids);
			DisbursementRequest d;
			for (TuitionFee t : result) {
				d = new DisbursementRequest();
				d.setId(t.getId());
				d.setCreatedBy(t.getCreatedBy());
				d.setPosition(t.getPition());
				d.setAffiliation(t.getBelong());
				d.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(t.getCreatedDate()));
				d.setAmount(t.getSumAmount());
				d.setStatus(t.getStatusCheck());
				dataList.add(d);
			}

		}else {
			//not thing
		}
	
		DataTableAjax<DisbursementRequest> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}

	public Message comment(Int061105FormSearchVo ids) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		
		if( BeanUtils.isNotEmpty(ids.getWithdrawRequest() ) && BeanUtils.isNotEmpty(ids.getIdSelect() )) {
			if("2058".equals(ids.getWithdrawRequest())) {
				/**
				 * แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)
			     */
				RentHouse update = rentHouseRepository.findOne(ids.getIdSelect());
				/* check status */
				if("APPROVE".equals(ids.getComment())) {
					update.setStatus(ExciseConstants.IA.STATUS.PASS);
				}else {
					update.setStatus(ExciseConstants.IA.STATUS.NOT_PASS);
				}
				
				rentHouseRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else if("2059".equals(ids.getWithdrawRequest())) {
				/**
			     * ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)
			     */
				HealthCareWelFareEntity update = healthCareWelFareRepository.findOne(ids.getIdSelect().longValue());
				/* check status */
				if("APPROVE".equals(ids.getComment())) {
					update.setStatusCheck(ExciseConstants.IA.STATUS.PASS);
				}else {
					update.setStatusCheck(ExciseConstants.IA.STATUS.NOT_PASS);
				}
				
				healthCareWelFareRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else if("2060".equals(ids.getWithdrawRequest())) {
				 /**
			      * ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
			      */
				TuitionFee update = tuitionFeeRepository.findOne(ids.getIdSelect());
				
				/* check status */
				if("APPROVE".equals(ids.getComment())) {
					update.setStatusCheck(ExciseConstants.IA.STATUS.PASS);
				}else {
					update.setStatusCheck(ExciseConstants.IA.STATUS.NOT_PASS);
				}
				
				tuitionFeeRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else {
				//not thing
				msg = ApplicationCache.getMessage("MSG_00003");
			}
		}
		
		return msg;
		
	}

	public void save(Int061105FormSearchVo ids) {
		int061105Dao.insertDisbursementRequest(ids.getDisbursementRequest());
		//insert iaDisReqId in table child
		if(BeanUtils.isNotEmpty(ids.getWithdrawRequest())) {
			if("2058".equals(ids.getWithdrawRequest())) {
				//แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)
				List<RentHouse> dataList = rentHouseRepository.findByStatusAndIaDisReqIdIsNull("2055");
				for (RentHouse r : dataList) {
					r.setIaDisReqId(ids.getDisbursementRequest().getId());
					rentHouseRepository.save(r);
				}
			}else if("2059".equals(ids.getWithdrawRequest())) {
				// ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)
				List<HealthCareWelFareEntity> dataList = healthCareWelFareRepository.findByStatusCheckAndIaDisReqIdIsNull("2055");
				for (HealthCareWelFareEntity r : dataList) {
					r.setIaDisReqId(ids.getDisbursementRequest().getId());
					healthCareWelFareRepository.save(r);
				}
			}else {
				//ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
				List<TuitionFee> dataList = tuitionFeeRepository.findByStatusCheckAndIaDisReqIdIsNull("2055");
				for (TuitionFee r : dataList) {
					r.setIaDisReqId(ids.getDisbursementRequest().getId());
					tuitionFeeRepository.save(r);
				}
			}
		}
	}

	public Long getNextval() {
		return int061105Dao.getNextval();
	}

}
