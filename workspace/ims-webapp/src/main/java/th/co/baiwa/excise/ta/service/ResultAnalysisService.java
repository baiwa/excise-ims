package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.ResultAnalysisDao;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisVo;

@Service
public class ResultAnalysisService {

	@Autowired
	private ResultAnalysisDao resultAnalysisDao;

	/* findProdcutsTax */
	public DataTableAjax<ResultAnalysisVo> findProdcutsTax() {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findProdcutsTax();
		Long count = resultAnalysisDao.countfindProdcutsTax();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

	/* findprice */
	public DataTableAjax<ResultAnalysisVo> findPrice() {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findPrice();
		Long count = resultAnalysisDao.countfindPrice();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

	/*findValueProductTax*/
	public DataTableAjax<ResultAnalysisVo> findValueProductTax() {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findValueProductTax();
		Long count = resultAnalysisDao.countfindValueProductTax();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
	/*tax rate*/
	public DataTableAjax<ResultAnalysisVo> findTaxRate() {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findTaxRate();
		Long count = resultAnalysisDao.countfindTaxRate();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
	/*Submit payment*/
	public DataTableAjax<ResultAnalysisVo> findSubmitPayment() {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findSubmitPayment();
		Long count = resultAnalysisDao.countfindSubmitPayment();

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
}
