package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ReportExportCheckingDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa013FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa013Vo;

@Service
public class Epa013Service {

	@Autowired
	private ReportExportCheckingDao reportExportCheckingDao;
	
	public DataTableAjax<Epa013Vo> search(Epa013FormVo epa013FormVo) {
		DataTableAjax<Epa013Vo> dataTableAjax = new DataTableAjax<>();
		
//		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa013FormVo.getSearchFlag())) {
//			List<Epa013Vo> list = reportExportCheckingDao.search(epa013FormVo);
//		
//			dataTableAjax.setDraw(epa013FormVo.getDraw() + 1);
////			dataTableAjax.setRecordsTotal(count);
////			dataTableAjax.setRecordsFiltered(count);
//			dataTableAjax.setData(list);
//		}
		
		return dataTableAjax;
	}

}
