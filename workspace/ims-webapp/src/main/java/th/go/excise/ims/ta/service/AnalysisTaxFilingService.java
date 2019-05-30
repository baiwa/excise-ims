package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxFilingVo;

@Service
public class AnalysisTaxFilingService {
	
	public DataTableAjax<AnalysisTaxFilingVo> GetAnalysisTaxFiling( AnalysisFormVo request) {
		int total = 0;
		DataTableAjax<AnalysisTaxFilingVo> dataTableAjax = new DataTableAjax<AnalysisTaxFilingVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxFiling());
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxFilingVo> listAnalysisTaxFiling() {
		List<AnalysisTaxFilingVo> datalist = new ArrayList<AnalysisTaxFilingVo>();
		AnalysisTaxFilingVo data = null;
	
			data = new AnalysisTaxFilingVo();
			data.setTaxMonth("พ.ค. 2560");
			data.setTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 5, 10))));
			data.setAnaTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 6, 12))));
			data.setResultTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 7, 14))));
			datalist.add(data);
			
			data = new AnalysisTaxFilingVo();
			data.setTaxMonth("มี.ค. 2560");
			data.setTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 5, 11))));
			data.setAnaTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 8, 20))));
			data.setResultTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 9, 18))));
			datalist.add(data);
		
			data = new AnalysisTaxFilingVo();
			data.setTaxMonth("ธ.ค 2560");
			data.setTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 5, 13))));
			data.setAnaTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 6, 25))));
			data.setResultTaxSubmissionDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 7, 27))));
			datalist.add(data);
		

		return datalist;
	}

}
