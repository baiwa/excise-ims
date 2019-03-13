package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;

@Service
public class AnalysisIncomeCompareLastMonthService {
	public DataTableAjax<AnalysisIncomeCompareLastMonthVo> GetAnalysisIncomeCompareLastMonth( AnalysisFormVo request) {
		int total = 35;
		DataTableAjax<AnalysisIncomeCompareLastMonthVo> dataTableAjax = new DataTableAjax<AnalysisIncomeCompareLastMonthVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisIncomeCompareLastMonth(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<AnalysisIncomeCompareLastMonthVo> listAnalysisIncomeCompareLastMonth(int start,int length,int total) {
	
		List<AnalysisIncomeCompareLastMonthVo> datalist = new ArrayList<AnalysisIncomeCompareLastMonthVo>();
		AnalysisIncomeCompareLastMonthVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new AnalysisIncomeCompareLastMonthVo();
			data.setTaxMonth("พ.ค. 2560");
			data.setIncomeAmt("24000.00");
			data.setDiffIncomeAmt("-480");
			data.setDiffIncomePnt("-4.35");
		
		}
		

		return datalist;
	}
}
