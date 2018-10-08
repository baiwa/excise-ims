package th.co.baiwa.excise.ia.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.ExpensesDao;
import th.co.baiwa.excise.ia.persistence.repository.ExpensesRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int06121FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06121Vo;

import java.util.Date;
import java.util.List;

@Service
public class Int06121Service {

    @Autowired
    private ExpensesDao expensesDao;

    @Autowired
    private ExpensesRepository expensesRepository;

    public DataTableAjax<Int06121Vo> findAll(Int06121FormVo formVo){

        DataTableAjax<Int06121Vo> dataTableAjax = new DataTableAjax<>();
        if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())){
        	
        	if(StringUtils.isNotBlank(formVo.getYear())) {
        		Date date = DateConstant.convertStrToDate(formVo.getYear(), ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.TH);
                String yyyy = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);            
                Date previousYearDate = DateUtils.addYears(date, -1);
                
                String previousYear = DateConstant.convertDateToStr(previousYearDate, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);
                String yearFrom = previousYear+"1001";
                String yearTo = yyyy+"0930";
                
                formVo.setYear(yyyy);
                formVo.setYearFrom(yearFrom);
                formVo.setYearTo(yearTo);
        	}
        	
            List<Int06121Vo> datas = expensesDao.findAll(formVo);
            Long count = expensesDao.count(formVo);
            dataTableAjax.setDraw(formVo.getDraw()+1);
            dataTableAjax.setRecordsFiltered(count);
            dataTableAjax.setRecordsTotal(count);
            dataTableAjax.setData(datas);
        }
        return dataTableAjax;
    }

    public void delete(String id){
        expensesRepository.delete(Long.valueOf(id));
    }
    
    public List<LabelValueBean> year() {
		return expensesDao.year();
    }

}

