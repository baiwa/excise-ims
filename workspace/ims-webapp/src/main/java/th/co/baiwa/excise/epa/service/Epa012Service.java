package th.co.baiwa.excise.epa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.dao.ExportCheckingConnectDao;
import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;

@Service
public class Epa012Service {
	
	@Autowired
	private ExportCheckingConnectDao exportCheckingConnectDao;

	public DataTableAjax<Epa012Vo> search(Epa012FormVo epa012FormVo) {
		DataTableAjax<Epa012Vo> dataTableAjax = new DataTableAjax<>();
		
		if (ExciseConstants.SEARCH_FLAG.TRUE.equalsIgnoreCase(epa012FormVo.getSearchFlag())) {
			List<Epa012Vo> list = exportCheckingConnectDao.search(epa012FormVo);
			long count = exportCheckingConnectDao.count(epa012FormVo);
			
			dataTableAjax.setDraw(epa012FormVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}
	
		return dataTableAjax;
	}

	public List<Epa012Vo> getInformation(Epa012FormVo epa012FormVo) {
		List<Epa012Vo> list = exportCheckingConnectDao.getInformation(epa012FormVo);
		return list;
	}

	public void saveTaxDatas(Epa012FormVo epa012FormVo) {
		exportCheckingConnectDao.saveTaxDatas(epa012FormVo);
	}

	public void saveFactoryDatas(Epa012FormVo epa012FormVo) {
		exportCheckingConnectDao.saveFactoryDatas(epa012FormVo);
	}

}
