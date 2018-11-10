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
				d.setStatus(ids.getStatus());
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
				d.setAmount(new BigDecimal(vo.getTotalMoney()));
				d.setStatus(ids.getStatus());
				dataList.add(d);
			}

		} else if ("2060".equals(ids.getWithdrawRequest())) {
			// ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
			List<TuitionFee> result = int061105Dao.findInTuitionFee(ids);
			DisbursementRequest d;
			for (TuitionFee t : result) {
				d = new DisbursementRequest();
				d.setId(new BigDecimal(t.getId()));
				d.setCreatedBy(t.getCreatedBy());
				d.setPosition(t.getPition());
				d.setAffiliation(t.getBelong());
				d.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(t.getCreatedDate()));
				d.setAmount(t.getSumAmount());
				d.setStatus(ids.getStatus());
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

	public Message approve(Int061105FormSearchVo ids) {
		Message msg;
		msg = ApplicationCache.getMessage("MSG_00003");
		
		if( BeanUtils.isNotEmpty(ids.getWithdrawRequest() ) && BeanUtils.isNotEmpty(ids.getIdSelect() )) {
			if("2058".equals(ids.getWithdrawRequest())) {
				/**
				 * แบบขอเบิกเงินค่าเช่าบ้าน (แบบ 6006)
			     */
				RentHouse update = rentHouseRepository.findOne(ids.getIdSelect());
				update.setStatus(ExciseConstants.IA.STATUS.PASS);
				rentHouseRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else if("2059".equals(ids.getWithdrawRequest())) {
				/**
			     * ใบเบิกเงินสวัสดิการเกี่ยวกับการรักษาพยาบาล (แบบ 7131)
			     */
				TuitionFee update = tuitionFeeRepository.findOne(ids.getIdSelect().longValue());
				update.setStatusCheck(ExciseConstants.IA.STATUS.PASS);
				tuitionFeeRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else if("2060".equals(ids.getWithdrawRequest())) {
				 /**
			      * ใบเบิกเงินสวัสดิการเกี่ยวกับการศึกษาบุตร (แบบ 7223)
			      */
				HealthCareWelFareEntity update = healthCareWelFareRepository.findOne(ids.getIdSelect().longValue());
				update.setStatusCheck(ExciseConstants.IA.STATUS.PASS);
				healthCareWelFareRepository.save(update);
				msg = ApplicationCache.getMessage("MSG_00002");
				
			}else {
				//not thing
				msg = ApplicationCache.getMessage("MSG_00003");
			}
		}
		
		return msg;
		
	}

}
