package th.co.baiwa.excise.ia.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.ExpensesDao;
import th.co.baiwa.excise.ia.persistence.vo.Int06112ExcelVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06113FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int06113Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0611ExcelVo;

@Service
public class Int06113Service {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LovRepository lovRepository;

    @Autowired
    private ExpensesDao expensesDao;

    public DataTableAjax<Int06113Vo> findAll(Int06113FormVo formVo){

        DataTableAjax<Int06113Vo> dataTableAjax = new DataTableAjax<>();
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

        	String officeCode = office(formVo);
        	formVo.setOfficeCode(officeCode);
        	
            Long count = expensesDao.countCheckCost(formVo);
            List<Int06113Vo> datas = expensesDao.findAllCheckCost(formVo);
            
           /*add data from excel*/
            if (!datas.isEmpty()) {
            	/*loop budget*/
				for (Int06113Vo data : datas) {
					List<Int0611ExcelVo> budgets = formVo.getDataBudget();
					for (Int0611ExcelVo budget : budgets) {
						if (data.getAccountId().equals(budget.getColum0())) {
							data.setExperimentalBudget(budget.getColum9());
							BigDecimal sumWithdraw = new BigDecimal(data.getSumWithdraw());
							BigDecimal experimentalBudget = new BigDecimal(data.getExperimentalBudget());
							BigDecimal result = sumWithdraw.subtract(experimentalBudget);
							data.setDifferenceExperimentalBudget(result.toString());							
						}
					}
				}
				/*loop ledger*/
				for (Int06113Vo data : datas) {
					List<Int06112ExcelVo> ledgers = formVo.getDataLedger();
					for (Int06112ExcelVo ledger : ledgers) {
						if (data.getAccountId().equals(ledger.getAccountId())) {
							data.setLedger(ledger.getAmount().toString());
							BigDecimal sumWithdraw = new BigDecimal(data.getSumWithdraw());
							BigDecimal amountLedger = new BigDecimal(data.getLedger());
							BigDecimal result = sumWithdraw.subtract(amountLedger);
							data.setDifferenceledger(result.toString());
						}
					}
				}
			}
            

            dataTableAjax.setDraw(formVo.getDraw()+1);
            dataTableAjax.setRecordsFiltered(count);
            dataTableAjax.setRecordsTotal(count);
            dataTableAjax.setData(datas);
        }
        return dataTableAjax;
    }

    public List<Lov> sector() {
        return lovRepository.findByTypeAndLovIdMasterIsNullOrderBySubType("SECTOR_VALUE");
    }

    public List<Lov> area(Long idMaster) {
        return lovRepository.findByLovIdMasterOrderBySubType(idMaster);
    }

    public List<LabelValueBean> year() {
		return expensesDao.year();
    }
    
    public String office(Int06113FormVo formVo) {    	
    	StringBuilder strOfficeCode = new StringBuilder();   
    	try {
    		Lov sector = lovRepository.findByTypeAndLovId("SECTOR_VALUE", Long.valueOf(formVo.getSector()));
        	Lov area = lovRepository.findByTypeAndLovId("SECTOR_VALUE", Long.valueOf(formVo.getArea()));
        	strOfficeCode.append(sector.getSubType());
        	strOfficeCode.append(area.getSubType());
		} catch (Exception e) {
			strOfficeCode = null;
			logger.error(e.getMessage());
		}
    	
    	return strOfficeCode.toString();
	}
}
