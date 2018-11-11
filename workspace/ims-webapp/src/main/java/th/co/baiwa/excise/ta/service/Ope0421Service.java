package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayPayRawMaterialCheckerDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0421Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0422Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;

@Service
public class Ope0421Service {

		
	@Autowired
	private DisplayPayRawMaterialCheckerDao displayPayRawMaterialChrckerHeaderDao;


	public DataTableAjax<Ope0421Vo> findAll(Ope0462FormVo formVo) {

		DataTableAjax<Ope0421Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Ope0421Vo> list = displayPayRawMaterialChrckerHeaderDao.findAll(formVo);
			Long count = displayPayRawMaterialChrckerHeaderDao.count(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}	
		return dataTableAjax;
	}

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = displayPayRawMaterialChrckerHeaderDao.findExciseId();
		return dataList;
	}

	
	public DataTableAjax<Ope0422Vo> findDetails(Ope0461FormVo formVo){
		DataTableAjax<Ope0422Vo> dataTableAjax = new DataTableAjax<>();
		
			List<Ope0422Vo> list = displayPayRawMaterialChrckerHeaderDao.findDetails(formVo);
			Long count = displayPayRawMaterialChrckerHeaderDao.countDetails(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);		
		return dataTableAjax;
	}
	
	

}
