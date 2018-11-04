package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayRawMaterialChrckerDetailDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0412Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;

@Service
public class Ope0412Service {

	@Autowired
	private DisplayRawMaterialChrckerDetailDao displayRawMaterialChrckerDetailDao;

	public DataTableAjax<Ope0412Vo> findAll(Ope0461FormVo formVo) {

		DataTableAjax<Ope0412Vo> dataTableAjax = new DataTableAjax<>();

		List<Ope0412Vo> list = displayRawMaterialChrckerDetailDao.findAll(formVo);
		Long count = displayRawMaterialChrckerDetailDao.count(formVo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

}
