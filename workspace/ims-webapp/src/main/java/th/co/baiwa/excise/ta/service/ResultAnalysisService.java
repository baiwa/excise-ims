package th.co.baiwa.excise.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.dao.ResultAnalysisDao;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisRequestvo;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisVo;

@Service
public class ResultAnalysisService {

	@Autowired
	private ResultAnalysisDao resultAnalysisDao;

	/* findProdcutsTax */
	public DataTableAjax<ResultAnalysisVo> findProdcutsTax(ResultAnalysisRequestvo vo) {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findProdcutsTax(vo);
		Long count = resultAnalysisDao.countfindProdcutsTax(vo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

	/* findprice */
	public DataTableAjax<ResultAnalysisVo> findPrice(ResultAnalysisRequestvo vo) {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findPrice(vo);
		Long count = resultAnalysisDao.countfindPrice(vo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}

	/*findValueProductTax*/
	public DataTableAjax<ResultAnalysisVo> findValueProductTax(ResultAnalysisRequestvo vo) {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findValueProductTax(vo);
		Long count = resultAnalysisDao.countfindValueProductTax(vo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
	/*tax rate*/
	public DataTableAjax<ResultAnalysisVo> findTaxRate(ResultAnalysisRequestvo vo) {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findTaxRate(vo);
		Long count = resultAnalysisDao.countfindTaxRate(vo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
	/*Submit payment*/
	public DataTableAjax<ResultAnalysisVo> findSubmitPayment(ResultAnalysisRequestvo vo) {

		DataTableAjax<ResultAnalysisVo> dataTableAjax = new DataTableAjax<>();

		List<ResultAnalysisVo> list = resultAnalysisDao.findSubmitPayment(vo);
		Long count = resultAnalysisDao.countfindSubmitPayment(vo);

		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setData(list);

		return dataTableAjax;
	}
	
}
