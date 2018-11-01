package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingReportDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa014FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa014Vo;

@Service
public class Epa014Service {

	@Autowired
	private ExportCheckingReportDao exportCheckingReportDao;
	
	public DataTableAjax<Epa014Vo> search(Epa014FormVo epa014FormVo) {
		DataTableAjax<Epa014Vo> dataTableAjax = new DataTableAjax<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa014FormVo.getSearchFlag())) {
			List<Epa014Vo> list = exportCheckingReportDao.search(epa014FormVo);
			long count = exportCheckingReportDao.count(epa014FormVo);
			
			dataTableAjax.setDraw(epa014FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
		
		return dataTableAjax;
	}

	public List<Epa014Vo> getInformation(Epa014FormVo epa014FormVo) {
		List<Epa014Vo> list = exportCheckingReportDao.getInformation(epa014FormVo);
		return list;
	}

}
