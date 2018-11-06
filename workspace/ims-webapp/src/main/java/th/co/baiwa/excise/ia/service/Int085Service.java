package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaIncomeExciseAudRptDao;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int085Vo;

@Service
public class Int085Service {
	private static Logger log = LoggerFactory.getLogger(Int085Service.class);
	
	@Autowired
	private IaIncomeExciseAudRptDao iaIncomeExciseAudRptDao;

	public DataTableAjax<Int085Vo> findAll(Int085FormVo formVo) {
		List<Int085Vo> listReturn = new ArrayList<Int085Vo>();
		// query data
		List<Int085Vo> list = iaIncomeExciseAudRptDao.findAllInt085(formVo);
		Long count = iaIncomeExciseAudRptDao.countInt085(formVo);
		
		for (Int085Vo vo : list) {
			vo.setStartDate(formVo.getStartDate());
			vo.setEndDate(formVo.getEndDate());
			vo.setRiskNumber("1");
			vo.setRiskList("ใบเสร็จเสีย  5 %");
			listReturn.add(vo);
		}
		
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
