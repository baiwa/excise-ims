package th.co.baiwa.excise.epa.service;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa021FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa021Vo;

@Service
public class Epa021Service {

	public DataTableAjax<Epa021Vo> search(Epa021FormVo epa021FormVo) {
		DataTableAjax<Epa021Vo> dataTableAjax = new DataTableAjax<Epa021Vo>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa021FormVo.getSearchFlag())) {
//			dataTableAjax.setDraw(epa021FormVo.getDraw() + 1);
//			dataTableAjax.setRecordsTotal(count);
//			dataTableAjax.setRecordsFiltered(count);
//			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

}
