package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.DisplayCreatePeperChrckerDetailDao;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461Vo;

@Service
public class Ope0461Service {

	@Autowired
	private DisplayCreatePeperChrckerDetailDao displayCreatePeperChrckerDao;

	public DataTableAjax<Ope0461Vo> findAll(Ope0461FormVo formVo) {

		DataTableAjax<Ope0461Vo> dataTableAjax = new DataTableAjax<>();

		List<Ope0461Vo> list = displayCreatePeperChrckerDao.findAll(formVo);
		Long count = displayCreatePeperChrckerDao.count(formVo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

}
