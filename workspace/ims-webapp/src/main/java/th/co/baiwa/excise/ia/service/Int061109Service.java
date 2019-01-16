package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int061109Dao;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;
import th.co.baiwa.excise.ia.persistence.repository.HealthCareWelFareRepository;
import th.co.baiwa.excise.ia.persistence.repository.RentHouseRepository;
import th.co.baiwa.excise.ia.persistence.repository.TuitionFeeRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int061109FormVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class Int061109Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private Int061109Dao int061109Dao;
	

	public DataTableAjax<DisbursementRequest> search(Int061109FormVo form) {
		List<DisbursementRequest> dataList = new ArrayList<DisbursementRequest>();
		
		dataList = int061109Dao.findAllInt061109(form);

		DataTableAjax<DisbursementRequest> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
		
	}
	
	public void edit(Int061109FormVo form) {
	
		int061109Dao.updateBillLadingInt061109(form);
	
	}
	
	public void add(Int061109FormVo form) {
		
		int061109Dao.updateBillPayInt061109(form);
	
	}
	
	public void approve(Int061109FormVo form) {
		
		int061109Dao.updateApproveInt061109(form);

	}


}
