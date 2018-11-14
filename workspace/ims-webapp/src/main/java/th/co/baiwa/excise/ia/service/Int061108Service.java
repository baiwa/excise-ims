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
import th.co.baiwa.excise.ia.persistence.vo.Int061108FormVo;

@Service
public class Int061108Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private Int061108Dao int061108Dao;

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
	
	}


}
