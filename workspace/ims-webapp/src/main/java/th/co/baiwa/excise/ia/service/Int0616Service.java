package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0614FormSearchVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;

@Service
public class Int0616Service {

	public DataTableAjax<Int0614FormSearchVo> list1(Int0614FormSearchVo vo,List<Int0614FormSearchVo> dataSession){
		DataTableAjax<Int0614FormSearchVo> dataTableAjax = new DataTableAjax<>();
		List<Int0614FormSearchVo> list = new ArrayList<>();
		Long count = 0l;
		
		if (!dataSession.isEmpty()) {
			list = dataSession;
		}
		
		count = Long.valueOf(list.size());
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);
		
		return dataTableAjax;
	}
	
	public DataTableAjax<Int0615Vo> list2(Int0615FormVo formVo, List<Int0615Vo> dataSession) {

		DataTableAjax<Int0615Vo> dataTableAjax = new DataTableAjax<>();
		List<Int0615Vo> list = new ArrayList<>();
		Long count = 0l;
		
		if (!dataSession.isEmpty()) {
			list = dataSession;
		}		
		count = Long.valueOf(list.size());
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
}
