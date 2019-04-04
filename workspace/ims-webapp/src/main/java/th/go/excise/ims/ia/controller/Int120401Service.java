package th.go.excise.ims.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaExpensesJdbcRepository;

@Service
public class Int120401Service {
	@Autowired
	private IaExpensesJdbcRepository iaExpensesJdbcRepository;
	
//	public DataTableAjax<Int120401Vo> findAll(Int120401FormVo formVo){
//
//        DataTableAjax<Int120401Vo> dataTableAjax = new DataTableAjax<>();
//        if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())){
//        	
//        	if(StringUtils.isNotBlank(formVo.getYear())) {
//        		Date date = ConvertDateUtils.parseStringToDate(formVo.getYear(), ConvertDateUtils.YYYY);
////        		Date date = DateConstant.convertStrToDate(formVo.getYear(), ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.TH);
//        		String yyyy = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY);
////        		String yyyy = DateConstant.convertDateToStr(date, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);            
//                Date previousYearDate = DateUtils.addYears(date, -1);
//                
//                String previousYear = ConvertDateUtils.formatDateToString(previousYearDate, ConvertDateUtils.YYYY);
////              String previousYear = DateConstant.convertDateToStr(previousYearDate, ExciseConstants.FORMAT_DATE.YYYY, ExciseConstants.LOCALE.EN);
//                String yearFrom = previousYear+"1001";
//                String yearTo = yyyy+"0930";
//                
//                formVo.setYear(yyyy);
//                formVo.setYearFrom(yearFrom);
//                formVo.setYearTo(yearTo);
//        	}
//        	
//            List<Int120401Vo> datas = iaExpensesJdbcRepository.findAll(formVo);
//            Long count = iaExpensesJdbcRepository.count(formVo);
//            dataTableAjax.setDraw(formVo.getDraw()+1);
//            dataTableAjax.setRecordsFiltered(count);
//            dataTableAjax.setRecordsTotal(count);
//            dataTableAjax.setData(datas);
//        }
//        return dataTableAjax;
//    }
}
