package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaIncomeExciseAudDao;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int085Vo;

@Service
public class Int085Service {
	private static Logger log = LoggerFactory.getLogger(Int085Service.class);
	
	@Autowired
	private IaIncomeExciseAudDao iaIncomeExciseAudDao;

	public DataTableAjax<Int085Vo> findAll(Int085FormVo formVo) {
		// query data
		List<Int085Vo> list = iaIncomeExciseAudDao.findAllInt085(formVo);
		Long count = iaIncomeExciseAudDao.countInt085(formVo);
		
		// set data table
		DataTableAjax<Int085Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	
}
