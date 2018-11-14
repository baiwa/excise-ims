package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int061108Dao;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.repository.HealthCareWelFareRepository;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRepository;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061108FormVo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int061108Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private Int061108Dao int061108Dao;
	
	@Autowired
	private RentHouseRepository rentHouseRepository;
	
	@Autowired
	private TuitionFeeRepository tuitionFeeRepository;
	
	@Autowired
	private HealthCareWelFareRepository healthCareWelFareRepository;


	public DataTableAjax<DisbursementRequest> search(Int061108FormVo form) {
		List<DisbursementRequest> dataList = new ArrayList<DisbursementRequest>();
		
		dataList = int061108Dao.findAllInt061108(form);

		DataTableAjax<DisbursementRequest> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}
	
	public void edit(Int061108FormVo form) {
	
		int061108Dao.updateBillLadingInt061108(form);
	
	}
	
	public void add(Int061108FormVo form) {
		
		int061108Dao.updateBillPayInt061108(form);
	
	}
	
	public void approve(Int061108FormVo form) {
		
		int061108Dao.updateApproveInt061108(form);
		
		if(BeanUtils.isNotEmpty(form.getId())) {
			if("2058".equals(form.getWithdrawRequest())) {
			
				List<RentHouse> updateList = rentHouseRepository.findByIaDisReqId(form.getId());
				for (RentHouse rentHouse : updateList) {
					
					rentHouse.setStatus(form.getStatus());
					rentHouseRepository.save(rentHouse);
				}
				
				
			}else if("2059".equals(form.getWithdrawRequest())) {
				
				List<HealthCareWelFareEntity> updateList = healthCareWelFareRepository.findByIaDisReqId(form.getId());
				
				for (HealthCareWelFareEntity healthCareWelFareEntity : updateList) {
					
					healthCareWelFareEntity.setStatusCheck(form.getStatus());
					healthCareWelFareRepository.save(healthCareWelFareEntity);
				}
				
				
			}else if("2060".equals(form.getWithdrawRequest())) {
				
				List<TuitionFee> updateList = tuitionFeeRepository.findByIaDisReqId(form.getId());
				
				for (TuitionFee tuitionFee : updateList) {
					
					tuitionFee.setStatusCheck(form.getStatus());
					tuitionFeeRepository.save(tuitionFee);
				}
				
			}
		}
		
		
	
	}


}
