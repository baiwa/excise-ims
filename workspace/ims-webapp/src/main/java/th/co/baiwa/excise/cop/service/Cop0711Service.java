package th.co.baiwa.excise.cop.service;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711Vo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Service
public class Cop0711Service {
	private static Logger log = LoggerFactory.getLogger(Cop0711Service.class);
	
	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;

	public DataTableAjax<Cop0711Vo> findAll(Cop0711FormVo formVo) {
		formVo.setActionPlan("1871");
		// query data
		List<Cop0711Vo> list = copCheckFiscalYearDao.findAllCop0711(formVo);
		Long count = copCheckFiscalYearDao.countCop0711(formVo);

		// set data table
		DataTableAjax<Cop0711Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public DataTableAjax<Cop0711Vo> findAll2(Cop0711FormVo formVo) {
		formVo.setActionPlan("1872");
		// query data
		List<Cop0711Vo> list = copCheckFiscalYearDao.findAllCop0711(formVo);
		Long count = copCheckFiscalYearDao.countCop0711(formVo);

		// set data table
		DataTableAjax<Cop0711Vo> dataTableAjax = new DataTableAjax<>();

		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}
	
	public Long save(Cop0711FormVo formVo) throws ParseException {
		List<Cop0711Vo> entrepreneurList = copCheckFiscalYearDao.getEntrepreneurCop0711(formVo.getCop0711Vo().getEntrepreneurNo());
		
		if(entrepreneurList.size()!=0) {
			formVo.getCop0711Vo().setEntrepreneurName(entrepreneurList.get(0).getEntrepreneurName());
			formVo.getCop0711Vo().setEntrepreneurLoca(entrepreneurList.get(0).getEntrepreneurLoca());
		}
		

		Long id = copCheckFiscalYearDao.saveDataCop0711(formVo.getCop0711Vo());
		return id ;
	}
	
	public void edit(Cop0711FormVo formVo) throws ParseException {
		List<Cop0711Vo> entrepreneurList = copCheckFiscalYearDao.getEntrepreneurCop0711(formVo.getCop0711Vo().getEntrepreneurNo());
		
		if(entrepreneurList.size()!=0) {
			formVo.getCop0711Vo().setEntrepreneurName(entrepreneurList.get(0).getEntrepreneurName());
			formVo.getCop0711Vo().setEntrepreneurLoca(entrepreneurList.get(0).getEntrepreneurLoca());
		}
		copCheckFiscalYearDao.editDataCop0711(formVo.getCop0711Vo());
		
	}
	
	public void delete(Long id) {
		copCheckFiscalYearDao.deleteCop0711(id);
	}
	
	
	

	
}
