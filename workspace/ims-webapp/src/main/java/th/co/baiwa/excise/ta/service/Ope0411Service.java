package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayRawMaterialChrckerHeaderDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0411Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;

@Service
public class Ope0411Service {

		
	@Autowired
	private DisplayRawMaterialChrckerHeaderDao displayRawMaterialChrckerHeaderDao;


	public DataTableAjax<Ope0411Vo> findAll(Ope0462FormVo formVo) {

		DataTableAjax<Ope0411Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Ope0411Vo> list = displayRawMaterialChrckerHeaderDao.findAll(formVo);
			Long count = displayRawMaterialChrckerHeaderDao.count(formVo);

			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}	
		return dataTableAjax;
	}

	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = displayRawMaterialChrckerHeaderDao.findExciseId();
		return dataList;
	}

	

}
