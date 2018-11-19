package th.co.baiwa.excise.cop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.Cop092Dao;
import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.entity.CopCheckFiscalReport;
import th.co.baiwa.excise.cop.persistence.repository.CopCheckFiscalReportRepository;
import th.co.baiwa.excise.cop.persistence.vo.Cop064FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductVo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Service
public class Cop092Service {

	@Autowired
	private CopCheckFiscalReportRepository copCheckFiscalReportRepository;

	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;

	@Autowired
	private Cop092Dao cop092Dao;

	public void updateFlag(Cop064FormVo formVo) {
		copCheckFiscalYearDao.updateStatusCopCheckFiscalYearDtlById(formVo.getId());
	}

	public DataTableAjax<Cop092ProductVo> findProductAll(Cop092ProductFormVo formVo) {
		// query data
		List<Cop092ProductVo> list = cop092Dao.findAll(formVo);
		Long count = Long.valueOf(list.size());
		// set data table
		DataTableAjax<Cop092ProductVo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public DataTableAjax<Cop092BudgetVo> findBudgetAll(Cop092BudgetFormVo formVo) {
		// query data
		List<Cop092BudgetVo> list = cop092Dao.findAll(formVo);
		Long count = Long.valueOf(list.size());
		// set data table
		DataTableAjax<Cop092BudgetVo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public void saveCopCheckFiscalReport(CopCheckFiscalReport data) {
		copCheckFiscalReportRepository.save(data);
	}

}
