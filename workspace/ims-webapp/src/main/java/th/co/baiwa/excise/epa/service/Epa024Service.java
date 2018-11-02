package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa024FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa024Vo;

@Service
public class Epa024Service {

	public DataTableAjax<Epa024Vo> search(@RequestBody Epa024FormVo epa024FormVo) {
		DataTableAjax<Epa024Vo> dataTableAjax = new DataTableAjax<Epa024Vo>();
		List<Epa024Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa024FormVo.getSearchFlag())) {
			for (int i = 0; i < 4; i++) {
				Epa024Vo vo = new Epa024Vo();
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
