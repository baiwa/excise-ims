package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa025FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa025Vo;

@Service
public class Epa025Service {

	public DataTableAjax<Epa025Vo> search(Epa025FormVo epa025FormVo) {
		DataTableAjax<Epa025Vo> dataTableAjax = new DataTableAjax<Epa025Vo>();
		List<Epa025Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa025FormVo.getSearchFlag())) {
			for (int i = 0; i < 4; i++) {
				Epa025Vo vo = new Epa025Vo();
				vo.setExciseId("id");
				vo.setExciseName("name");
				list.add(vo);
			}
			
//			dataTableAjax.setDraw(epa021FormVo.getDraw() + 1);
//			dataTableAjax.setRecordsTotal(count);
//			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

}
