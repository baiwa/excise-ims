package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa031FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa031Vo;

@Service
public class Epa031Service {

	public DataTableAjax<Epa031Vo> search(Epa031FormVo epa031FormVo) {
		DataTableAjax<Epa031Vo> dataTableAjax = new DataTableAjax<Epa031Vo>();
		List<Epa031Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa031FormVo.getSearchFlag())) {
			
			for (int i = 0; i < 4; i++) {
				Epa031Vo vo = new Epa031Vo();
				vo.setExciseName("name");
				vo.setDestination("destination");
				vo.setDateDestination("date destination");
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
