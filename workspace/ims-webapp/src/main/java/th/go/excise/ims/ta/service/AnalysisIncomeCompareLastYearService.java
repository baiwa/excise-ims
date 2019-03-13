package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastYearVo;
@Service
public class AnalysisIncomeCompareLastYearService {
	public DataTableAjax<AnalysisIncomeCompareLastYearVo> GetAnalysisIncomeCompareLastYear( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisIncomeCompareLastYearVo> dataTableAjax = new DataTableAjax<AnalysisIncomeCompareLastYearVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisIncomeCompareLastYear(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisIncomeCompareLastYearVo> listAnalysisIncomeCompareLastYear(int start,int length,int total) {
	
		List<AnalysisIncomeCompareLastYearVo> datalist = new ArrayList<AnalysisIncomeCompareLastYearVo>();
		AnalysisIncomeCompareLastYearVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisIncomeCompareLastYearVo();
			data.setTaxMonth("พ.ค. 2560");
			data.setIncomeCurrentYear("25000.00");
			data.setIncomeLastYear("23520.00");
			data.setDiffIncomeAmt("-1480.00");
			data.setDiffIncomePnt("-5.92");
		}
		

		return datalist;
	}
}
