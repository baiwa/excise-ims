package th.co.baiwa.excise.epa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa031FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa031Vo;

@Service
public class Epa031Service {

	public DataTableAjax<Epa031Vo> search(Epa031FormVo epa031FormVo) {
		DataTableAjax<Epa031Vo> dataTableAjax = new DataTableAjax<Epa031Vo>();
		List<Epa031Vo> list = new ArrayList<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa031FormVo.getSearchFlag())) {
			
			for (int i = 0; i < 1; i++) {
				Epa031Vo vo = new Epa031Vo();
				vo.setExciseName("บริษัท สยามแก๊ส แอนด์ ปิโตรเคมีคัลส์ จำกัด (มหาชน)");
				vo.setDestination("แม่สอด");
				vo.setDateDestination("30/11/2018");
				list.add(vo);
			}
			
//			dataTableAjax.setDraw(epa031FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(1l);
			dataTableAjax.setRecordsFiltered(1l);
			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

}
