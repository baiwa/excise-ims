package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.Tsl010600Dao;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600Vo;

@Service
public class Tsl010600Service {

	@Autowired
	private Tsl010600Dao tsl010300Dao;

	public DataTableAjax<Tsl010600Vo> search(Tsl010600FormVo tsl010600FormVo) {
		DataTableAjax<Tsl010600Vo> dataTableAjax = new DataTableAjax<Tsl010600Vo>();

		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(tsl010600FormVo.getSearchFlag())) {
			List<Tsl010600Vo> list = tsl010300Dao.search(tsl010600FormVo);
			long count = tsl010300Dao.count(tsl010600FormVo);

			dataTableAjax.setDraw(tsl010600FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

}
