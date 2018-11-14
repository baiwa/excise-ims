package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.Tsl010700Dao;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700Vo;

@Service
public class Tsl010700Service {
	
	@Autowired
	private Tsl010700Dao tsl010700Dao;

	public DataTableAjax<Tsl010700Vo> search(Tsl010700FormVo tsl010700FormVo) {
		DataTableAjax<Tsl010700Vo> dataTableAjax = new DataTableAjax<Tsl010700Vo>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(tsl010700FormVo.getSearchFlag())) {
			List<Tsl010700Vo> list = tsl010700Dao.search(tsl010700FormVo);
			long count = tsl010700Dao.count(tsl010700FormVo);
			
			dataTableAjax.setDraw(tsl010700FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

}
