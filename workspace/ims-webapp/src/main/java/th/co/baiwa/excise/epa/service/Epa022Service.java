package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa022FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa022Vo;

@Service
public class Epa022Service {

	public DataTableAjax<Epa022Vo> search(Epa022FormVo epa022FormVo) {
		DataTableAjax<Epa022Vo> dataTableAjax = new DataTableAjax<Epa022Vo>();
		List<Epa022Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa022FormVo.getSearchFlag())) {
			
			for (int i = 0; i < 4; i++) {
				Epa022Vo vo = new Epa022Vo();
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
