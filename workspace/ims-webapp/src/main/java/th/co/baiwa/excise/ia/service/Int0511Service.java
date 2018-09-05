package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckStampAreaDao;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;

@Service
public class Int0511Service {

	@Autowired
	private CheckStampAreaDao checkStampAreaDao;

	public DataTableAjax<Int0511Vo> findAll(Int0511FormVo formVo) {
		List<Int0511Vo> list = checkStampAreaDao.findAll(formVo);
		Long count = checkStampAreaDao.count(formVo);
		DataTableAjax<Int0511Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<LabelValueBean> sector() {
		return checkStampAreaDao.sector();
	}

	public List<LabelValueBean> area(String id) {
		return checkStampAreaDao.area(id);
	}
}
