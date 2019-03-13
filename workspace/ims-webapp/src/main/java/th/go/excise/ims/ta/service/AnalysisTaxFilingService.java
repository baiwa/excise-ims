package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisTaxFilingVo;
@Service
public class AnalysisTaxFilingService {
	public DataTableAjax<AnalysisTaxFilingVo> GetAnalysisTaxFiling( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisTaxFilingVo> dataTableAjax = new DataTableAjax<AnalysisTaxFilingVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisTaxFiling(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisTaxFilingVo> listAnalysisTaxFiling(int start,int length,int total) {
		List<AnalysisTaxFilingVo> datalist = new ArrayList<AnalysisTaxFilingVo>();
		AnalysisTaxFilingVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisTaxFilingVo();
			data.setTaxMonth("พ.ค. 2560");
			data.setTaxSubmissionDate("15 มิ.ย. 2560");
			data.setAnaTaxSubmissionDate("14 มิ.ย. 2560");
			data.setResultTaxSubmissionDate("ชำระเกินเวลา");
		}
		

		return datalist;
	}

}
