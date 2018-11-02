package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa032FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa032Vo;

@Service
public class Epa032Service {

	public DataTableAjax<Epa032Vo> search(Epa032FormVo epa032FormVo) {
		DataTableAjax<Epa032Vo> dataTableAjax = new DataTableAjax<Epa032Vo>();
		List<Epa032Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa032FormVo.getSearchFlag())) {
			for (int i = 0; i < 4; i++) {
				Epa032Vo vo = new Epa032Vo();
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
