package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.TaxHomeDao;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeFormVo;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeVo;

@Service
public class TaxHomeService {
	
	@Autowired
	private TaxHomeDao taxHomeDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TaxHomeService.class);
	
	public DataTableAjax<TaxHomeVo> selectType(TaxHomeFormVo formvo) {
		logger.info("selectType");
		List<TaxHomeVo> list = taxHomeDao.selectType(formvo);
		Long count = taxHomeDao.countTableTyp(formvo);
		
		DataTableAjax<TaxHomeVo> dataTableAjax = new DataTableAjax<>();
		
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);
		
		return dataTableAjax;
	}
}
