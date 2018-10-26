package th.co.baiwa.excise.cop.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.cop.persistence.dao.CopCheckFiscalYearDao;
import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;

@Service
public class Cop071Service {
	
	private static Logger log = LoggerFactory.getLogger(Cop071Service.class);
	

	@Autowired
	private CopCheckFiscalYearDao copCheckFiscalYearDao;

	public DataTableAjax<Cop071Vo> findAll(Cop071FormVo formVo) {
		DataTableAjax<Cop071Vo> dataTableAjax = new DataTableAjax<>();
		List<Cop071Vo> listReturn = new ArrayList<Cop071Vo>();
		List<Cop071Vo> list = new ArrayList<Cop071Vo>();
		
		list = copCheckFiscalYearDao.findAllCop071(formVo);
		Long count = copCheckFiscalYearDao.countCop071(formVo);

		
		for(int i=0;i<12;i++){
			Cop071Vo voAdd = new Cop071Vo();
			
			for (Cop071Vo voL : list) {
				int month = Integer.parseInt(voL.getFiscalYear().split("/")[0]);
				if((i+1)==month) {
					voAdd = voL;
				}
			}
			String mm = (i<9)?"0"+String.valueOf(i+1):String.valueOf(i+1);
			
			voAdd.setFiscalYear(mm+"/"+formVo.getFiscalYear());
			voAdd.setFiscalYearText(DateConstant.MONTH_NAMES[i]);
			listReturn.add(voAdd);
			
		}
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(listReturn);
		}

		return dataTableAjax;
	}
	
	public void checkAddAndEdit(Cop071FormVo formVo) throws ParseException{
		if(formVo.getCop071Vo().getId()!=0) {
			log.info("Edit copCheckFiscalYear");
			edit(formVo);
		}else {
			log.info("Add copCheckFiscalYear");
			add(formVo);
		}
		
		
	}

	public void edit(Cop071FormVo formVo) throws ParseException {
		formVo.getCop071Vo().setAsPlanWait(formVo.getCop071Vo().getAsPlanNumber()-((formVo.getCop071Vo().getAsPlanSuccess()==null)?0:formVo.getCop071Vo().getAsPlanSuccess()));
		formVo.getCop071Vo().setOutsidePlanWait(formVo.getCop071Vo().getOutsidePlanNumber()-((formVo.getCop071Vo().getOutsidePlanSuccess()==null)?0:formVo.getCop071Vo().getOutsidePlanSuccess()));
		copCheckFiscalYearDao.editCop071(formVo.getCop071Vo());
	}
	
	public void add(Cop071FormVo formVo) throws ParseException {
		formVo.getCop071Vo().setAsPlanWait(formVo.getCop071Vo().getAsPlanNumber()-((formVo.getCop071Vo().getAsPlanSuccess()==null)?0:formVo.getCop071Vo().getAsPlanSuccess()));
		formVo.getCop071Vo().setOutsidePlanWait(formVo.getCop071Vo().getOutsidePlanNumber()-((formVo.getCop071Vo().getOutsidePlanSuccess()==null)?0:formVo.getCop071Vo().getOutsidePlanSuccess()));
		
		copCheckFiscalYearDao.addCop071(formVo.getCop071Vo());
	}
	
	
}
