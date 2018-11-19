package th.co.baiwa.excise.cop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.entity.CopCheckFiscalReport;
import th.co.baiwa.excise.cop.persistence.repository.CopCheckFiscalReportRepository;
import th.co.baiwa.excise.cop.persistence.vo.Cop091FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop091Vo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Service
public class Cop091Service {
	private static Logger log = LoggerFactory.getLogger(Cop091Service.class);

	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;

	@Autowired
	private CopCheckFiscalReportRepository copCheckFiscalReportRepository;

	public DataTableAjax<Cop091Vo> findAll(Cop091FormVo formVo) {
		formVo.setActionPlan("1871");
		// query data
		List<Cop091Vo> list = copCheckFiscalYearDao.findAllCop091(formVo);
		Long count = Long.valueOf(list.size());

		// set data table
		DataTableAjax<Cop091Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public DataTableAjax<Cop091Vo> findAll2(Cop091FormVo formVo) {
		formVo.setActionPlan("1872");
		// query data
		List<Cop091Vo> list = copCheckFiscalYearDao.findAllCop091(formVo);
		Long count = Long.valueOf(list.size());

		// set data table
		DataTableAjax<Cop091Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public CopCheckFiscalReport dataReport(Long id) {
		
		CopCheckFiscalReport report = copCheckFiscalReportRepository.findByFiscalYearId(id);
		log.info("Cop091Service method dataReport ");
		return report;
	}

}
