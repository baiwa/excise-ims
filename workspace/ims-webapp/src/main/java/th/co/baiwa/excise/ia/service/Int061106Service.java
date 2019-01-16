package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.Int061106Dao;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.vo.Int061106FormVo;

@Service
public class Int061106Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private Int061106Dao int061106Dao;

	public DataTableAjax<DisbursementRequest> search(Int061106FormVo form) {
		List<DisbursementRequest> dataList = new ArrayList<DisbursementRequest>();
		
		dataList = int061106Dao.findAllInt061106(form);

		DataTableAjax<DisbursementRequest> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setRecordsTotal(Long.valueOf(dataList.size()));
		dataTableAjax.setRecordsFiltered(Long.valueOf(dataList.size()));
		dataTableAjax.setData(dataList);
		return dataTableAjax;
	}
	
	public void edit(Int061106FormVo form) {
	
		int061106Dao.updateBillLadingInt061106(form);
	
	}
	
	public void add(Int061106FormVo form) {
		
		int061106Dao.updateBillPayInt061106(form);
	
	}
	
	public void approve(Int061106FormVo form) {
		
		int061106Dao.updateApproveInt061106(form);
	
	}


}
