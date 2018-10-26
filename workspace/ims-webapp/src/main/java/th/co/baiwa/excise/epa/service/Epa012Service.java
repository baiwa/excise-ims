package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingConnectDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;

@Service
public class Epa012Service {
	
	@Autowired
	private ExportCheckingConnectDao exportCheckingConnectDao;

	public DataTableAjax<Epa012Vo> search(Epa012FormVo epa012FormVo) {
		DataTableAjax<Epa012Vo> dataTableAjax = new DataTableAjax<>();
		List<Epa012Vo> list = exportCheckingConnectDao.search(epa012FormVo);
		
		dataTableAjax.setDraw(epa012FormVo.getDraw() + 1);
//		dataTableAjax.setRecordsTotal(count);
//		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);
	
		return dataTableAjax;
	}

}
