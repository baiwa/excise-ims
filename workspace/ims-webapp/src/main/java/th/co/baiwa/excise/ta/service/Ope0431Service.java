package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayBalanceRawMaterialChrckerHeaderDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0431Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;

@Service
public class Ope0431Service {

		
	@Autowired
	private DisplayBalanceRawMaterialChrckerHeaderDao displayBalanceRawMaterialChrckerHeaderDao;


	public DataTableAjax<Ope0431Vo> findAll(Ope0462FormVo formVo) {

		DataTableAjax<Ope0431Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Ope0431Vo> list = displayBalanceRawMaterialChrckerHeaderDao.findAll(formVo);
			Long count = displayBalanceRawMaterialChrckerHeaderDao.count(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}	
		return dataTableAjax;
	}

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = displayBalanceRawMaterialChrckerHeaderDao.findExciseId();
		return dataList;
	}

	

}